package org.wcscda.worms.board.weapons;

import org.wcscda.worms.Helper;
import org.wcscda.worms.board.ARBEWIthHandlerWithGravity;
import org.wcscda.worms.gamemechanism.WormSoundPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import java.io.IOException;

public class GrenadeBananeAmmo extends AbstractAmmo {
    private static final int EXPLOSION_RADIUS = 150;
    private static final int EXPLOSION_DAMAGE = 60;
    private static final int GRENADE_RECT_SIZE = 10;
    private final double initialX;
    private final double initialY;
    private static final Image[] grenadeBanane = new Image[23];
    private static final String[] imagePath = {
            "src/resources/grenade-banane/banane-1.png",
            "src/resources/grenade-banane/banane-2.png",
            "src/resources/grenade-banane/banane-3.png",
            "src/resources/grenade-banane/banane-4.png",
            "src/resources/grenade-banane/banane-5.png",
            "src/resources/grenade-banane/banane-6.png",
            "src/resources/grenade-banane/banane-7.png",
            "src/resources/grenade-banane/banane-8.png",
            "src/resources/grenade-banane/banane-9.png",
            "src/resources/grenade-banane/banane-10.png",
            "src/resources/grenade-banane/banane-11.png",
            "src/resources/grenade-banane/banane-12.png",
            "src/resources/grenade-banane/banane-13.png",
            "src/resources/grenade-banane/banane-14.png",
            "src/resources/grenade-banane/banane-15.png",
            "src/resources/grenade-banane/banane-16.png",
            "src/resources/grenade-banane/banane-17.png",
            "src/resources/grenade-banane/banane-18.png",
            "src/resources/grenade-banane/banane-19.png",
            "src/resources/grenade-banane/banane-20.png",
            "src/resources/grenade-banane/banane-21.png",
            "src/resources/grenade-banane/banane-22.png",
            "src/resources/grenade-banane/banane-23.png",
    };

    private static void initImages() {
        for (int i = 0; i < imagePath.length; i++) {
            grenadeBanane[i] = new ImageIcon(imagePath[i]).getImage().getScaledInstance(50, 50, 0);
        }
    }

    public GrenadeBananeAmmo(Double angle) {
        super(EXPLOSION_RADIUS, EXPLOSION_DAMAGE);
        createMovableRect(GRENADE_RECT_SIZE, GRENADE_RECT_SIZE);
        getMovable().setDirection(angle);
        getMovable().setSpeed(7);

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
        if (grenadeBanane[0] == null) {
            initImages();
        }

        if (Helper.getActiveWorm().getDirection() > Math.PI / 2) {
            AffineTransform trans =
                    AffineTransform.getTranslateInstance(getMovable().getX(), getMovable().getY());
            trans.scale(-1, 1);

            g.drawImage(grenadeBanane[Helper.getClock() % grenadeBanane.length],trans, io);
        } else {
            g.drawImage(grenadeBanane[Helper.getClock() % grenadeBanane.length], (int) getMovable().getCenterX(), (int) getMovable().getCenterY() - 18, io);
        }
    }

    @Override
    protected void explode() {
        super.explode();
        try {
            new WormSoundPlayer().grenadeBananeSound();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
