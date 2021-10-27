package org.wcscda.worms.board.weapons;

import org.wcscda.worms.Helper;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;


public class GrenadeBanane extends AbstractWeapon {
    private static final String imagePath = "src/resources/grenade-banane/banane-5.png";
    private static Image image = null;

    private static void initImages() {
        image = new ImageIcon(imagePath).getImage().getScaledInstance(50, 50, 0);
    }

    @Override
    public void draw(Graphics2D g, ImageObserver io) {
        if (image == null) {
            initImages();
        }

        if (getAngle() > Math.PI / 2) {
            AffineTransform trans =
                    AffineTransform.getTranslateInstance(Helper.getWormX() + 10, Helper.getWormY() - 10);
            trans.scale(-1, 1);

            g.drawImage(image, trans, io);
        } else {
            g.drawImage(image, (int) Helper.getWormX() - 10, (int) Helper.getWormY() -10, io);
        }
    }
}
