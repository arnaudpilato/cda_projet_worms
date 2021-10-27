package org.wcscda.worms.gamemechanism.phases;

import org.wcscda.worms.Helper;
import org.wcscda.worms.Player;
import org.wcscda.worms.Worm;
import org.wcscda.worms.board.weapons.*;
import org.wcscda.worms.gamemechanism.WormSoundPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.IOException;

public class ItemPhase extends AbstractPhase{
    private static final String imagePath1 = "src/resources/items/arrow.png";
    private static final String imagePath2 = "src/resources/items/border.png";
    private static final String imagePath3 = "src/resources/items/selector.png";
    private static final String imagePath4 = "src/resources/items/bullet-green.png";
    private static final String imagePath5 = "src/resources/items/infinite.png";
    private static final String imagePath6 = "src/resources/items/hadoken-white.png";
    private static final String imagePath7 = "src/resources/items/shotgun-white.png";
    private static final String imagePath8 = "src/resources/items/heart-white.png";
    private static final String imagePath9 = "src/resources/items/grenade-white.png";
    private static final String imagePath10 = "src/resources/items/time-grenade-white.png";
    private static final String imagePath11 = "src/resources/items/banana-white.png";

    private static Image arrow = null;
    private static Image border = null;
    private static Image selector = null;
    private static Image bulletGreen = null;
    private static Image infinite = null;
    private static Image hadokenWhite = null;
    private static Image shotgunWhite = null;
    private static Image heartWhite = null;
    private static Image grenadeWhite = null;
    private static Image timeGrenadeWhite = null;
    private static Image bananaWhite = null;

    private int positionX = 0;
    private int positionY = 0;

    private Player weapon = Helper.getActivePlayer();
    private int superGrenade = Helper.getActiveWorm().getAmmunitionSuperGrenade();
    private int life = Helper.getActiveWorm().getLife();

    private static void initImages() {
        arrow = new ImageIcon(imagePath1).getImage().getScaledInstance(20, 15, 0);
        border = new ImageIcon(imagePath2).getImage().getScaledInstance(180, 170, 0);
        selector = new ImageIcon(imagePath3).getImage().getScaledInstance(50, 72, 0);
        bulletGreen = new ImageIcon(imagePath4).getImage().getScaledInstance(5, 10, 0);
        infinite = new ImageIcon(imagePath5).getImage().getScaledInstance(20, 10, 0);
        hadokenWhite = new ImageIcon(imagePath6).getImage().getScaledInstance(40, 40, 0);
        shotgunWhite = new ImageIcon(imagePath7).getImage().getScaledInstance(40, 40, 0);
        heartWhite = new ImageIcon(imagePath8).getImage().getScaledInstance(40, 40, 0);
        grenadeWhite = new ImageIcon(imagePath9).getImage().getScaledInstance(40, 40, 0);
        timeGrenadeWhite = new ImageIcon(imagePath10).getImage().getScaledInstance(40, 40, 0);
        bananaWhite = new ImageIcon(imagePath11).getImage().getScaledInstance(40, 40, 0);
    }

    @Override
    protected int getMaxDurationSeconds() {
        return 0;
    }

    @Override
    public void forwardKeyPressed(String key) {
        if (key.equals("I")) {
            Helper.getTC().setCurrentPhase(new WormMovingPhase());
        }

        if (key.equals("Left") && positionX > 0) {
            positionX -= 1;
            try {
                new WormSoundPlayer().cursorSound();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (key.equals("Right") && positionX < 2) {
            positionX += 1;
            try {
                new WormSoundPlayer().cursorSound();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (key.equals("Up") && positionY > 0) {
            positionY -= 1;
            try {
                new WormSoundPlayer().cursorSound();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (key.equals("Down") && positionY < 1) {
            positionY += 1;
            try {
                new WormSoundPlayer().cursorSound();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void drawMain(Graphics2D g, ImageObserver io) {
        if (arrow == null) {
            initImages();
        }

        // Inventaire : Ligne du haut
        super.drawMain(g, io);
        g.drawImage(arrow, (int) Helper.getWormX() - 10, (int) Helper.getWormY() - 80, io);
        g.drawImage(border, (int) Helper.getWormX() - 90, (int) Helper.getWormY() - 242, io);

        g.drawImage(hadokenWhite, (int) Helper.getWormX() - 75, (int) Helper.getWormY() - 227, io);
        g.drawImage(bulletGreen, (int) Helper.getWormX() - 75, (int) Helper.getWormY() - 177, io);
        g.drawImage(infinite, (int) Helper.getWormX() - 55, (int) Helper.getWormY() - 176, io);
       // g.drawString("100", (int) Helper.getWormX() - 60, (int)Helper.getWormY() - 167);
        if (positionX == 0 && positionY == 0) {
            g.drawImage(selector, (int) Helper.getWormX() - 80, (int) Helper.getWormY() - 232, io);
            weapon.setCurrentWeapon(new Hadoken());
        }

        g.drawImage(shotgunWhite, (int) Helper.getWormX() - 20, (int) Helper.getWormY() - 227, io);
        g.drawImage(bulletGreen, (int) Helper.getWormX() - 20, (int) Helper.getWormY() - 177, io);
        g.drawString(Helper.getActiveWorm().getAmmunition() + "", (int) Helper.getWormX() + 13, (int)Helper.getWormY() - 167);
        if (positionX == 1 && positionY == 0) {
            g.drawImage(selector, (int) Helper.getWormX() - 25, (int) Helper.getWormY() - 232, io);
            weapon.setCurrentWeapon(new Shotgun());
        }

        g.drawImage(heartWhite, (int) Helper.getWormX() + 35, (int) Helper.getWormY() - 227, io);
        g.drawImage(bulletGreen, (int) Helper.getWormX() + 35, (int) Helper.getWormY() - 177, io);
        g.drawString(life + "", (int) Helper.getWormX() + 50, (int)Helper.getWormY() - 167);
        if (positionX == 2 && positionY == 0) {
            g.drawImage(selector, (int) Helper.getWormX() + 30, (int) Helper.getWormY() - 232, io);
        }

        // Inventaire : Ligne du bas
        g.drawImage(grenadeWhite, (int) Helper.getWormX() - 75, (int) Helper.getWormY() - 150, io);
        g.drawImage(bulletGreen, (int) Helper.getWormX() - 75, (int) Helper.getWormY() - 100, io);
        g.drawString("1", (int) Helper.getWormX() - 42, (int)Helper.getWormY() -90);
        if (positionX == 0 && positionY == 1) {
            g.drawImage(selector, (int) Helper.getWormX() - 80, (int) Helper.getWormY() - 155, io);
            weapon.setCurrentWeapon(new Grenade());
        }

        g.drawImage(timeGrenadeWhite, (int) Helper.getWormX() - 20, (int) Helper.getWormY() - 150, io);
        g.drawImage(bulletGreen, (int) Helper.getWormX() - 20, (int) Helper.getWormY() - 100, io);
        g.drawString("1", (int) Helper.getWormX() + 13, (int)Helper.getWormY() -90);
        if (positionX == 1 && positionY == 1) {
            g.drawImage(selector, (int) Helper.getWormX() - 25, (int) Helper.getWormY() - 155, io);
            weapon.setCurrentWeapon(new SuperGrenade());
        }

        g.drawImage(bananaWhite, (int) Helper.getWormX() + 35, (int) Helper.getWormY() - 150, io);
        g.drawImage(bulletGreen, (int) Helper.getWormX() + 35, (int) Helper.getWormY() - 100, io);
        g.drawString(superGrenade + "", (int) Helper.getWormX() + 68, (int)Helper.getWormY() -90);
        if (positionX == 2 && positionY == 1) {
            g.drawImage(selector, (int) Helper.getWormX() + 30, (int) Helper.getWormY() - 155, io);
            if (superGrenade > 0) {
                weapon.setCurrentWeapon(new GrenadeBanane());
            }
        }
    }
}
