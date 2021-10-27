package org.wcscda.worms.board.weapons;

import org.wcscda.worms.Helper;
import org.wcscda.worms.board.ARBEWIthHandlerWithGravity;
import org.wcscda.worms.board.AbstractBoardElement;
import org.wcscda.worms.gamemechanism.WormSoundPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.ImageObserver;
import java.io.IOException;

public class SuperGrenadeAmmo extends AbstractAmmo {
    private static final int EXPLOSION_RADIUS = 150;
    private static final int EXPLOSION_DAMAGE = 15;
    private static final int GRENADE_RECT_SIZE = 10;
    private final double initialX;
    private final double initialY;
    private static final Image[] superGrenade = new Image[16];
    private final int timer = Helper.getClock();
    private static final String[] imagePath = {
            "src/resources/super-grenade/grenade-1.png",
            "src/resources/super-grenade/grenade-2.png",
            "src/resources/super-grenade/grenade-3.png",
            "src/resources/super-grenade/grenade-4.png",
            "src/resources/super-grenade/grenade-5.png",
            "src/resources/super-grenade/grenade-6.png",
            "src/resources/super-grenade/grenade-7.png",
            "src/resources/super-grenade/grenade-8.png",
            "src/resources/super-grenade/grenade-9.png",
            "src/resources/super-grenade/grenade-10.png",
            "src/resources/super-grenade/grenade-11.png",
            "src/resources/super-grenade/grenade-12.png",
            "src/resources/super-grenade/grenade-13.png",
            "src/resources/super-grenade/grenade-14.png",
            "src/resources/super-grenade/grenade-15.png",
            "src/resources/super-grenade/grenade-16.png",
    };

    private static void initImages() {
        for (int i = 0; i < imagePath.length; i++) {
            superGrenade[i] = new ImageIcon(imagePath[i]).getImage().getScaledInstance(30, 30, 0);
        }
    }

    public SuperGrenadeAmmo(Double angle) {
        super(EXPLOSION_RADIUS, EXPLOSION_DAMAGE);
        createMovableRect(GRENADE_RECT_SIZE, GRENADE_RECT_SIZE);
        getMovable().setDirection(angle);
        getMovable().setSpeed(4);

        initialX = Helper.getWormX();
        initialY = Helper.getWormY();

        setInitialPosition();
    }

    protected void createMovableRect(int rectWidth, int rectHeight) {
        setMovable(new ARBEWIthHandlerWithGravity(
                Helper.getWormX() - rectWidth / 2,
                Helper.getWormY() - rectHeight / 2,
                rectWidth,
                rectHeight,
                this));

    }

    @Override
    public void drawMain(Graphics2D g, ImageObserver io) {
        // TODO Auto-generated method stub
        if (superGrenade[0] == null) {
            initImages();
        }

        if (Helper.getActiveWorm().getDirection() > Math.PI / 2) {
            g.drawImage(superGrenade[Helper.getClock() % superGrenade.length], (int) getMovable().getCenterX(), (int) getMovable().getCenterY() - 18, io);
        } else {
            AffineTransform trans =
                    AffineTransform.getTranslateInstance(getMovable().getX(), getMovable().getY());
            trans.scale(-1, 1);

            g.drawImage(superGrenade[Helper.getClock() % superGrenade.length], trans, io);
        }
    }

    @Override
    protected void explode() {
        super.explode();
        try {
            new WormSoundPlayer().grenadeSound();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void colideWith(AbstractBoardElement movable, Point2D prevPosition) {
        this.getMovable().setPosition(prevPosition);
        if (Helper.getClock() >= timer + 100) {
            super.colideWith(movable, prevPosition);
        }
    }
}
