package org.wcscda.worms.board;

import org.wcscda.worms.Helper;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.ImageObserver;
import java.util.Random;

public class AmmunitionBox extends ARBEWithGravity {
    private static final String imagePath = "src/resources/parachuteBox.png";
    private static final String imagePath2 = "src/resources/ammunitionBox.png";
    private static Image img;
    private int ammunitions = 5;
    private int ammunitionsSuperGrenade = 1;
    private Random rand = new Random();
    private int randX = rand.nextInt(1150);
    private boolean nextOneCreated = false;

    public AmmunitionBox(int x, int y, int rectWidth, int rectHeight) {
        super(x, y, rectWidth, rectHeight);

    }


    public static void initImages() {

            img = new ImageIcon(imagePath).getImage().getScaledInstance(145, 200, 0);

    }


    public int getAmmunitions() {
        return ammunitions;
    }

    public void setAmmunitions(int ammunitions) {
        this.ammunitions = ammunitions;
    }

    @Override
    public String toString() {
        return "AmmunitionBox{" +
                "ammunitions=" + ammunitions +
                '}';
    }

    @Override
    protected void drawMain(Graphics2D g, ImageObserver io) {
        if (img == null){
            initImages();
        }
        g.drawImage(img, getX()-50, getY()-145, io);

    }
    public void remove() {
        removeSelf();
    }

    @Override
    public void collideWith(AbstractBoardElement movable, Point2D prevPosition) {

    }
    public static int random_int(int Min, int Max)
    {
        return (int) (Math.random()*(Max-Min))+Min;
    }

    public int getAmmunitionsSuperGrenade() {
        return ammunitionsSuperGrenade;
    }

    public void setAmmunitionsSuperGrenade(int ammunitionsSuperGrenade) {
        this.ammunitionsSuperGrenade = ammunitionsSuperGrenade;
    }

    @Override
    public void onIterationBegin(){
        if (Helper.getClock() == random_int(0, 600) ){
            setPosition(new Point2D.Double(randX, 3));
        }
    }
}
