package org.wcscda.worms.board.weapons;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
// import java.awt.geom.Ellipse2D;
import java.awt.image.ImageObserver;
import java.io.IOException;

import org.wcscda.worms.Helper;
import org.wcscda.worms.gamemechanism.WormSoundPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class Hadoken extends AbstractWeapon {
  private static final int hadokenRadius = 50;
  private static final Image[] halo = new Image[10];
  private final int timer = Helper.getClock();
  private static final String[] imagePath = {
          "src/resources/halo/halo-1.png",
          "src/resources/halo/halo-2.png",
          "src/resources/halo/halo-3.png",
          "src/resources/halo/halo-4.png",
          "src/resources/halo/halo-5.png",
          "src/resources/halo/halo-6.png",
          "src/resources/halo/halo-7.png",
          "src/resources/halo/halo-8.png",
          "src/resources/halo/halo-9.png",
          "src/resources/halo/halo-10.png",
  };

  private static void initImages() {
    for (int i = 0; i < imagePath.length; i++) {
      halo[i] = new ImageIcon(imagePath[i]).getImage().getScaledInstance(50, 50, 0);
    }
  }

  @Override
  public void draw(Graphics2D g, ImageObserver io) {
    /*Ellipse2D circle =
        new Ellipse2D.Double(
            Helper.getWormX() - hadokenRadius,
            Helper.getWormY() - hadokenRadius,
            2 * hadokenRadius,
            2 * hadokenRadius);

    g.setColor(Color.BLUE);
    g.setStroke(new BasicStroke(10));
    g.draw(circle);*/

    if (halo[0] == null) {
      initImages();
    }


    if (Helper.getActiveWorm().getDirection() > Math.PI / 2) {
      AffineTransform trans =
              AffineTransform.getTranslateInstance(Helper.getWormX() + 5, Helper.getWormY() - 5);
      trans.scale(-1, 1);

      g.drawImage(halo[Helper.getClock() % halo.length], trans, io);
    } else {
      g.drawImage(halo[Helper.getClock() % halo.length], (int) Helper.getWormX() - 5, (int) Helper.getWormY() - 5, io);
    }
  }

  @Override
  public void triggerAmmoExplosion() {
    super.triggerAmmoExplosion();
    try {
      new WormSoundPlayer().hadokenImpactSound();
    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
      e.printStackTrace();
    }
  }
}
