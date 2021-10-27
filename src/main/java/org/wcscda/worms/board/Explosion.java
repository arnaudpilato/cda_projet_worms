package org.wcscda.worms.board;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import java.io.IOException;

import org.wcscda.worms.Helper;
import org.wcscda.worms.gamemechanism.WormSoundPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import org.wcscda.worms.utils.DrawHelper;

public class Explosion extends AbstractDrawableElement {
  private static final int LIFE_DURATION = 18;
  private final double centerX;
  private final double centerY;
  private final double radius;
  private final int createdPhase;
  private static final String[] imageGrenadePath = {
          "src/resources/explosion/explosion-1.png",
          "src/resources/explosion/explosion-2.png",
          "src/resources/explosion/explosion-3.png",
          "src/resources/explosion/explosion-4.png",
          "src/resources/explosion/explosion-5.png",
          "src/resources/explosion/explosion-6.png",
          "src/resources/explosion/explosion-7.png",
          "src/resources/explosion/explosion-8.png",
          "src/resources/explosion/explosion-9.png",
          "src/resources/explosion/explosion-10.png",
          "src/resources/explosion/explosion-11.png",
          "src/resources/explosion/explosion-12.png",
          "src/resources/explosion/explosion-13.png",
          "src/resources/explosion/explosion-14.png",
          "src/resources/explosion/explosion-15.png",
          "src/resources/explosion/explosion-16.png",
          "src/resources/explosion/explosion-17.png",
          "src/resources/explosion/explosion-18.png",
          "src/resources/explosion/explosion-19.png",
          "src/resources/explosion/explosion-20.png",
          "src/resources/explosion/explosion-21.png",
          "src/resources/explosion/explosion-22.png",
          "src/resources/explosion/explosion-23.png"
  };

  private static final String[] imageSuperGrenadePath = {
          "src/resources/super-explosion/big-explosion-1.png",
          "src/resources/super-explosion/big-explosion-2.png",
          "src/resources/super-explosion/big-explosion-3.png",
          "src/resources/super-explosion/big-explosion-4.png",
          "src/resources/super-explosion/big-explosion-5.png",
          "src/resources/super-explosion/big-explosion-6.png",
          "src/resources/super-explosion/big-explosion-7.png",
          "src/resources/super-explosion/big-explosion-8.png",
          "src/resources/super-explosion/big-explosion-9.png",
          "src/resources/super-explosion/big-explosion-10.png",
          "src/resources/super-explosion/big-explosion-11.png",
          "src/resources/super-explosion/big-explosion-12.png",
          "src/resources/super-explosion/big-explosion-13.png",
          "src/resources/super-explosion/big-explosion-14.png",
          "src/resources/super-explosion/big-explosion-15.png",
          "src/resources/super-explosion/big-explosion-16.png",
          "src/resources/super-explosion/big-explosion-17.png",
          "src/resources/super-explosion/big-explosion-18.png",
          "src/resources/super-explosion/big-explosion-19.png",
          "src/resources/super-explosion/big-explosion-20.png",
          "src/resources/super-explosion/big-explosion-21.png",
          "src/resources/super-explosion/big-explosion-22.png",
          "src/resources/super-explosion/big-explosion-23.png",
          "src/resources/super-explosion/big-explosion-24.png",
          "src/resources/super-explosion/big-explosion-25.png",
          "src/resources/super-explosion/big-explosion-26.png",
          "src/resources/super-explosion/big-explosion-27.png",
          "src/resources/super-explosion/big-explosion-28.png",
          "src/resources/super-explosion/big-explosion-29.png",
          "src/resources/super-explosion/big-explosion-30.png",
  };

  private static final Image[] grenade = new Image[imageGrenadePath.length];
  private static final Image[] superGrenade = new Image[imageSuperGrenadePath.length];

  private static void initImages() {
    for (int i = 0; i < imageGrenadePath.length; i++) {
      grenade[i] = new ImageIcon(imageGrenadePath[i]).getImage().getScaledInstance(200, 200, 0);
    }

    for (int i = 0; i < imageSuperGrenadePath.length; i++) {
      superGrenade[i] = new ImageIcon(imageSuperGrenadePath[i]).getImage().getScaledInstance(400, 400, 0);
    }
  }

  public Explosion(double centerX, double centerY, int explosionRadius) {
    super(true);
    this.centerX = centerX;
    this.centerY = centerY;
    this.radius = explosionRadius;
    this.createdPhase = Helper.getClock();
  }

  private int getLifeTime() {
    return Helper.getClock() - createdPhase;
  }

  @Override
  protected void drawMain(Graphics2D g, ImageObserver io) {
     /*Shape explosion =

        DrawHelper.getCircle(
            centerX, centerY, (int) (radius * (0.1 + getLifeTime() * 0.9 / LIFE_DURATION)));*/
    AffineTransform trans =
            AffineTransform.getTranslateInstance(centerX, centerY);
    //trans.scale(-1, 1);

    if ((radius > 50) && (radius <= 100)) {
      if (grenade[0] == null) {
        initImages();
      }

      g.drawImage(grenade[(Helper.getClock()) % grenade.length], (int)centerX - 100, (int)centerY - 100, io);
      g.setColor(Color.RED);
      //g.fill(explosion);
      // new WormSoundPlayer().playWav();
    }

    if (radius > 100) {
      if (superGrenade[0] == null) {
        initImages();
      }

      g.drawImage(superGrenade[(Helper.getClock()) % superGrenade.length], (int)centerX - 200, (int)centerY - 200, io);
      g.setColor(Color.RED);
    }

    if (getLifeTime() == LIFE_DURATION) {
      removeSelf();
    }
  }
}

