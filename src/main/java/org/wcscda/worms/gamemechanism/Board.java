package org.wcscda.worms.gamemechanism;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.*;

import org.wcscda.worms.Helper;
import org.wcscda.worms.board.*;
import org.wcscda.worms.gamemechanism.events.EndOfTurnEvent;
import org.wcscda.worms.gamemechanism.phases.EndOfGamePhase;

public abstract class Board extends JPanel {

    /* NRO 2021-09-30 : Asked by JPanel */
    private static final long serialVersionUID = 1L;

    private static final int BOARD_WIDTH = 1200;
    private static final int BOARD_HEIGHT = 800;

    private WormField wormField;

    public Board() {
        initBoard();
    }

    private void initBoard() {
        setBackground(Color.BLACK);
        setFocusable(true);

        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));

        wormField = new WormField(BOARD_WIDTH, BOARD_HEIGHT);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing((Graphics2D) g);
    }

    private void doDrawing(Graphics2D g) {
        for (AbstractDrawableElement drawable : AbstractDrawableElement.getAllDrawable()) {
            drawable.draw(g, this);
        }
        int nbLooser = 0;
        for (int i = 0; i < Helper.getTC().getPlayers().size(); i++) {
            if ((Helper.getActivePlayer().getWorms().size() > 0) && (Helper.getTC().getPlayers().get(i).getWorms().size() < 1)) {
                nbLooser++;
                if (nbLooser == Helper.getTC().getPlayers().size() - 1) {
                    String fontName = "Microsoft YaHei";
                    g.scale(2,2);
                    g.setColor(Color.GREEN);
                    g.drawString(" L'EQUIPE " + Helper.getActivePlayer().getName() + " A GAGNE LA PARTIE ", 150, 100);
                }
            }
        }

        Toolkit.getDefaultToolkit().sync();
    }

    public void actionPerformed(ActionEvent e) {
        if (isGameFinished()) {
            Helper.getTC().setCurrentPhase(new EndOfGamePhase());
        }

        AbstractDrawableElement.getAllDrawable().forEach(AbstractDrawableElement::onIterationBegin);

        Helper.getTC().getKeyboardController().onIterationBegin();

        repaint();
        doMoves();

        AbstractDrawableElement.processToBeRemovedAndAdded();
        Helper.getTC().delayedActions();

        new EndOfTurnEvent(Helper.getClock());
    }


    /* NRO : TODO-Student : choose when to decide the game is finished
     */
    private boolean isGameFinished() {
        return false;
    }

  public void makeScreenshot() {
    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    makeScreenshot("screenshot_" + timeStamp + ".png");
  }

  public void makeScreenshot(String filename) {
    Rectangle rec = getBounds();
    BufferedImage bufferedImage =
        new BufferedImage(rec.width, rec.height, BufferedImage.TYPE_INT_ARGB);
    paint(bufferedImage.getGraphics());

    try {
      // Create temp file
      File filePath = new File(filename);

      // Use the ImageIO API to write the bufferedImage to a temporary file
      ImageIO.write(bufferedImage, "png", filePath);
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }



    protected abstract void doMoves();

    public static int getBWIDTH() {
        return BOARD_WIDTH;
    }


    public static int getBHEIGHT() {
        return BOARD_HEIGHT;
    }

    public WormField getWormField() {
        return wormField;
    }



  public void addWindowListener(WindowListener w) {
    WormLauncher.getInstance().addWindowListener(w);
  }
}
