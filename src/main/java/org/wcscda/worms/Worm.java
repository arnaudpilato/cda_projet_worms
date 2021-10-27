package org.wcscda.worms;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

import org.wcscda.worms.board.*;
import org.wcscda.worms.gamemechanism.Board;
import org.wcscda.worms.gamemechanism.StartGame;
import org.wcscda.worms.gamemechanism.TimeController;
import org.wcscda.worms.gamemechanism.WormSoundPlayer;


public class Worm extends ARBEWithGravity implements IVisitable {
  private static final String[] wormFacings = {
          "src/resources/worm/worm-1.png",
          "src/resources/worm/worm-2.png",
          "src/resources/worm/worm-3.png",
          "src/resources/worm/worm-4.png",
          "src/resources/worm/worm-5.png",
          "src/resources/worm/worm-6.png",
          "src/resources/worm/worm-7.png",
          "src/resources/worm/worm-8.png",
          "src/resources/worm/worm-9.png"
  };
  public static boolean winner = false;

  public int getLife() {
    return life;
  }

  public void setLife(int life) {
    this.life = life;
  }

  private static final int imageHeight = 58;
  private static final int imageWidth = 50;
  private static final int rectPadding = 15;
  private int[] positionX = new int[Helper.getTC().getPlayers().size()];
  private double[] positionY = new double[Helper.getTC().getPlayers().size()];

  private static final Image[] wormImagesFacing = new Image[9];
  private int shownLife = 100;
  private int life = 100;
  private final String name;
  private final Player player;
  private boolean isUserMoving;
  private int ammunition = 3 ;
  private int ammunitionSuperGrenade = 0 ;

  public int getAmmunition() {
    return ammunition;
  }

  public void setAmmunition(int ammunition) {
    this.ammunition = ammunition;
  }

  public static double numberOfDies = 0;

  private static void initImages() {
      for (int i = 0; i < wormFacings.length; i++) {
        wormImagesFacing[i] = new ImageIcon(wormFacings[i]).getImage().getScaledInstance(imageWidth, imageHeight, 0);
      }
  }

  // NRO 2021-09-28 : Player is the Worm factory
  protected Worm(Player player, String name) {
    this(player, name, getRandomStartingX(), getRandomStartingY());
  }

  // Idem
  protected Worm(Player player, String name, int x, int y) {
    super(x, y, imageWidth - 2 * rectPadding, imageHeight - 2 * rectPadding);

    this.player = player;
    this.name = name;
  }

  private static int getRandomStartingX() {
    return RandomGenerator.getInstance().nextInt(Board.getBWIDTH() - imageWidth);
  }

  private static int getRandomStartingY() {
    return RandomGenerator.getInstance().nextInt(Board.getBHEIGHT() - imageHeight);
  }

  @Override
  protected void drawMain(Graphics2D g, ImageObserver io) {
    if (wormImagesFacing[0] == null) {
      initImages();
    }

    AffineTransform reverseWorm =
            AffineTransform.getTranslateInstance(getX() + 35,getY() - rectPadding);
    reverseWorm.scale(-1, 1);

    if (winner == false) {
      if ((isUserMoving) && (isRightFacing())) {
        g.drawImage(wormImagesFacing[(Helper.getClock()) % wormImagesFacing.length], getX() - rectPadding, getY() - rectPadding, io);
      } else if (isRightFacing()) {
        g.drawImage(wormImagesFacing[0], getX() - rectPadding, getY() - rectPadding, io);
      }

      if ((isUserMoving) && (!isRightFacing())){
        g.drawImage(wormImagesFacing[(Helper.getClock()) % wormImagesFacing.length], reverseWorm, io);
      } else if (!isRightFacing()) {
        g.drawImage(wormImagesFacing[0], reverseWorm, io);
      }
    }


    // Drawing the life
    g.setColor(player.getColor());
    g.drawString(getName(), (int) getX(), (int) getY() - 30);

    g.drawString("" + getShownLife(), (int) getX(), (int) getY() - 15);
  }

  private int getShownLife() {
    if (life < shownLife) {
      shownLife--;
    } else if (life > shownLife) {
      shownLife++;
    }
    return this.shownLife;
  }

  private boolean isRightFacing() {
    return Math.abs(getDirection()) < 1e-6;
  }

  public Player getPlayer() {
    return player;
  }

  public boolean isUserMoving() {
    if ((Helper.getWormX() < 0) || (Helper.getWormX() > Board.getBWIDTH())) {
      die();
    }
    return isUserMoving;
  }

  public void setUserMoving(boolean isUserMoving) {
    this.isUserMoving = isUserMoving;
  }


  public int getAmmunitionSuperGrenade() {
    return ammunitionSuperGrenade;
  }

  public void setAmmunitionSuperGrenade(int ammunitionSuperGrenade) {
    this.ammunitionSuperGrenade = ammunitionSuperGrenade;
  }

  @Override
  public void collideWith(AbstractBoardElement movable, Point2D prevPosition) {

    if (movable instanceof AmmunitionBox) {
      AmmunitionBox box = (AmmunitionBox) movable;
      this.setAmmunition(box.getAmmunitions());
      this.setAmmunitionSuperGrenade(box.getAmmunitionsSuperGrenade());
      box.removeSelf();
    }
    setPosition(prevPosition);
  }

  @Override
  public String toString() {
    return "Worm " + this.getName() + " / player : " + this.getPlayer();
  }

  public String getName() {
    if(name == null) return "null";
    return name;
  }

  @Override
  public void takeDamage(int damage) {
    if(Helper.getActivePlayer().isBeginner()) {
      damage = (int) (damage * 1.25);
    }

    life -= damage;

    try {
      new WormSoundPlayer().screamSound();
    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
      e.printStackTrace();
    }

    if (life <= 0) {
      /*new Die(Helper.getWormX(), Helper.getWormY(), (int) numberOfDies);
      this.numberOfDies += 1;*/
      die();
    }
  }

  public void die() {
    removeSelf();
  }

  @Override
  protected void onRemoval() {
    player.getWorms().remove(this);
  }

  @Override
  public void accept(Point2D prevPosition, IMovableVisitor visitor) {
    visitor.visit(this, prevPosition);
  }

}
