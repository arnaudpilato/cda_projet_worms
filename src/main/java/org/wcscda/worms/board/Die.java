package org.wcscda.worms.board;

import org.wcscda.worms.Helper;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.HashMap;
import java.util.Map;

public class Die extends AbstractDrawableElement {
    private static final String imagePath = "src/resources/explosion/explosion-1.png";
    private static Image image = null;
    private final double[] positionX = new double[Helper.getTC().getPlayers().size()];
    private final double[] positionY = new double[Helper.getTC().getPlayers().size()];
    private final int iterator;
    public static Map<Double, Double> essai = new HashMap<>();

    public Die(double diePositionX, double diePositionY, int iterator) {
        this.positionX[iterator] = diePositionX;
        this.positionY[iterator] = diePositionY;
        this.iterator = iterator;
    }

    private static void initImages() {
        image = new ImageIcon(imagePath).getImage().getScaledInstance(30, 30, 0);
    }

    @Override
    protected void drawMain(Graphics2D g, ImageObserver io) {
        if (image == null) {
            initImages();
        }

        for (int i = 0; i < iterator; i++) {
            g.drawImage(image, (int) positionX[i], (int) positionY[i], io);
        }
        removeSelf();

    }
}
