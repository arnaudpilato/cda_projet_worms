package org.wcscda.worms.gamemechanism.phases;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.io.IOException;

import org.wcscda.worms.Config;
import org.wcscda.worms.Helper;
import org.wcscda.worms.Worm;
import org.wcscda.worms.board.weapons.AbstractWeapon;
import org.wcscda.worms.board.weapons.GrenadeBanane;
import org.wcscda.worms.board.weapons.Shotgun;
import org.wcscda.worms.gamemechanism.KeyboardController;
import org.wcscda.worms.gamemechanism.WormSoundPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.wcscda.worms.board.weapons.SuperGrenade;

public class WormMovingPhase extends AbstractPhase {
    private static final double WORM_STEP_SPEED = 3.0;
    private static final double WEAPON_LINE_LENGTH = 30.0;
    private static final double WEAPON_ANGLE_INCR = Math.PI / 8;

    @Override
    protected int getMaxDurationSeconds() {
        return Config.getMaxWormTurnDuration();
    }

    @Override
    public void forwardKeyPressed(String key) {
        if (key.equals("Left")) {
            moveWorm(Math.PI);
        }

        if (key.equals("Right")) {
            moveWorm(0);
        }

        if (key.equals("Up")) {
            moveWeapon(-1);
        }

        if (key.equals("Down")) {
            moveWeapon(1);
        }

        if (key.equals("I")) {
            Helper.getTC().setCurrentPhase(new ItemPhase());
            try {
                new WormSoundPlayer().openItemSound();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (key.equals("Space")) {
            System.out.println("Ammunition : " + Helper.getActiveWorm().getAmmunition());
            System.out.println("Munition SP:" + Helper.getActiveWorm().getAmmunitionSuperGrenade());
            if ((Helper.getActivePlayer().getCurrentWeapon() instanceof Shotgun) &&
                    (Helper.getActiveWorm().getAmmunition() < 1)) {
                Helper.getActivePlayer().changeWeapon();
            } else {
                if ((Helper.getActivePlayer().getCurrentWeapon() instanceof GrenadeBanane) &&
                        (Helper.getActiveWorm().getAmmunitionSuperGrenade() == 1)) {
                    Helper.getActiveWorm().setAmmunitionSuperGrenade(Helper.getActiveWorm().getAmmunitionSuperGrenade() - 1);
                }
                Helper.getCurrentWeapon().fire();
            }

        }

        if (key.equals("W")) {
            Helper.getActivePlayer().changeWeapon();
            System.out.println(Helper.getActivePlayer().getCurrentWeapon() + " :" + Helper.getActiveWorm().getAmmunitionSuperGrenade());
            if ((Helper.getActivePlayer().getCurrentWeapon() instanceof GrenadeBanane) &&
                    (Helper.getActiveWorm().getAmmunitionSuperGrenade() == 0)) {
                Helper.getActivePlayer().changeWeapon();
            }
        }
    }

    private void moveWeapon(int direction) {
        AbstractWeapon weapon = Helper.getCurrentWeapon();
        Worm worm = Helper.getActiveWorm();
        double angle = weapon.getAngle();

        weapon.incrementAngle(direction * Math.cos(worm.getDirection()) * WEAPON_ANGLE_INCR);
        if (Math.abs(weapon.getAngle() - worm.getDirection()) >= Math.PI / 2 + 1e-3) {
            weapon.setAngle(angle);
        }
    }

    private void moveWorm(double angle) {
        if (Helper.getPC().getFirstStandingOn(Helper.getActiveWorm()).isEmpty()) {
            return;
        }

        if (!Helper.getActiveWorm().isStandingOn(Helper.getField().getShape())) {
            return;
        }

        Helper.getCurrentWeapon().setAngle(angle);
        Worm worm = Helper.getActiveWorm();

        worm.setDirection(angle);
        worm.setUserMoving(true);
        worm.singleMove(Helper.getPC(), Math.cos(angle) * WORM_STEP_SPEED, 0.0);
    }

    @Override
    protected void drawMain(Graphics2D g, ImageObserver io) {
        Worm activeWorm = Helper.getActiveWorm();
        if (!activeWorm.isUserMoving()) {
            Helper.getCurrentWeapon().draw(g, io);

            drawWeaponDirectionLine(g, io);
        }
        activeWorm.setUserMoving(false);
    }

    private void drawWeaponDirectionLine(Graphics2D g, ImageObserver io) {
        double angle = Helper.getCurrentWeapon().getAngle();

        g.setColor(Helper.getActivePlayer().getColor());
        int x = (int) Helper.getWormX();
        int y = (int) Helper.getWormY();

        g.drawLine(
                x,
                y,
                x + (int) (WEAPON_LINE_LENGTH * Math.cos(angle)),
                y + (int) (WEAPON_LINE_LENGTH * Math.sin(angle)));
    }
}
