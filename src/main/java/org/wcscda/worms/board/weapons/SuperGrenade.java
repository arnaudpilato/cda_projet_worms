package org.wcscda.worms.board.weapons;

import org.wcscda.worms.Helper;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;


public class SuperGrenade extends AbstractWeapon {
    private static final String imagePath = "src/resources/weapons/super-grenade-R.png";
    private static Image image = null;

    private static void initImages() {
        image = new ImageIcon(imagePath).getImage().getScaledInstance(30, 30, 0);
    }

    @Override
    public void draw(Graphics2D g, ImageObserver io) {
        if (image == null) {
            initImages();
        }

        if (getAngle() > Math.PI / 2) {
            AffineTransform trans =
                    AffineTransform.getTranslateInstance(Helper.getWormX(), Helper.getWormY());
            trans.scale(-1, 1);

            g.drawImage(image, trans, io);
        } else {
            g.drawImage(image, (int) Helper.getWormX(), (int) Helper.getWormY(), io);
        }
    }
}
