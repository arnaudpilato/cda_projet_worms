package org.wcscda.worms.gamemechanism;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.Timer;

import org.wcscda.worms.Config;
import org.wcscda.worms.Helper;
import org.wcscda.worms.Player;
import org.wcscda.worms.Worm;
import org.wcscda.worms.board.AmmunitionBox;
import org.wcscda.worms.board.Score;
import org.wcscda.worms.board.Winner;
import org.wcscda.worms.gamemechanism.phases.AbstractPhase;
import org.wcscda.worms.gamemechanism.phases.EndOfGamePhase;
import org.wcscda.worms.gamemechanism.phases.WormMovingPhase;
import org.wcscda.worms.gamemechanism.playerrecorder.KeyboardControllerPlayer;
import org.wcscda.worms.gamemechanism.playerrecorder.KeyboardControllerRecorder;

public class TimeController implements ActionListener {
    private final KeyboardController keyboardController;
    private static TimeController instance;
    private PhysicalController board;
    private Timer timer;
    private ArrayList<Player> players = new ArrayList<Player>();
    private int activePlayerIndex = 0;
    private AbstractPhase currentPhase;
    private int phaseCount = 0;
    private boolean delayedSetNextWorm;


    public static int random_int(int Min, int Max)
    {
        return (int) (Math.random()*(Max-Min))+Min;
    }



    public TimeController() {
        instance = this;
        board = new PhysicalController();
        keyboardController = createController();
        StartGame initialize = new StartGame(this);
        board.addKeyListener(keyboardController);
    }

    /* package */ void postInitGame() {
        timer = new Timer(Config.getClockDelay(), this);
        timer.start();
    }

    private KeyboardController createController() {
        if (Config.getRecordGame()) {
            return new KeyboardControllerRecorder(this.board);
        } else if (Config.getPlayRecord()) {
            return new KeyboardControllerPlayer();
        } else {
            return new KeyboardController();
        }
    }

    public void initGame(String[]playerName, String[][] wormsName, Boolean beginner) {
        Random random = new Random();
        Player[] player = new Player[playerName.length];
        Worm[][] worms = new Worm[playerName.length][wormsName[0].length];

        for (int i = 0; i < playerName.length; i++) {
            float r = random.nextFloat();
            float g = random.nextFloat();
            float b = random.nextFloat();

            Color randomColor = new Color(r, g, b);

            player[i] = createPlayer(playerName[i], randomColor);
            player[0].setBeginner(beginner);
            for (int j = 0; j < wormsName[i].length; j++) {
                worms[i][j] = player[i].createWorm(wormsName[i][j]);
                board.wormInitialPlacement(worms[i][j]);
            }
        }

        doSetNextWorm();
        Score score = new Score();
        score.setPlayers(player);

        AmmunitionBox box = new AmmunitionBox(500, -2000,60,50);
//        box.onIterationBegin();
        try {
            new WormSoundPlayer().startSound();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }

        try {
            new WormSoundPlayer().ambientSound();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }

        postInitGame();
    }


    public KeyboardController getKeyboardController() {
        return keyboardController;


    }

    public void setNextWorm() {
        delayedSetNextWorm = true;
    }

    protected void delayedActions() {
        if (delayedSetNextWorm) {
            delayedSetNextWorm = false;
            doSetNextWorm();
        }
    }

    protected void doSetNextWorm() {
        for (int i = 0; i < players.size(); ++i) {
            activePlayerIndex += 1;
            activePlayerIndex %= players.size();
            if (getActivePlayer().hasWorms()) break;
        }

        // No player have any worm, it is sad ...
        if (!getActivePlayer().hasWorms()) {
            return;
        }

        int nbLooser = 0;
        for (int i = 0; i < Helper.getTC().getPlayers().size(); i++) {
            if ((Helper.getActivePlayer().getWorms().size() > 0) && (Helper.getTC().getPlayers().get(i).getWorms().size() < 1)) {
                System.out.println(" l'équipe " + Helper.getTC().getPlayers().get(i).getName() + " à perdu");
                nbLooser++;
                if (nbLooser == players.size() - 1) {
                    System.out.println(" l'équipe " + Helper.getActivePlayer().getName() + " à gagné");
                    Worm.winner = true;
                    new Winner((int) Helper.getWormX(), (int) Helper.getWormY());
                    try {
                        new WormSoundPlayer().winSound();
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        getActivePlayer().setNextWorm();
        getActivePlayer().initWeapon();

        AbstractPhase phase = new WormMovingPhase();
        this.setCurrentPhase(phase);
    }

    private Player createPlayer(String name, Color color) {
        Player player = new Player(name, color);
        players.add(player);

        return player;
    }

    public Component getBoard() {
        return board;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        phaseCount++;
        board.actionPerformed(e);
    }

    public static TimeController getInstance() {
        if (instance == null) {
            instance = new TimeController();
        }
        return instance;
    }

    public AbstractPhase getCurrentPhase() {
        return currentPhase;
    }

    public void setCurrentPhase(AbstractPhase currentPhase) {
        if ((this.currentPhase != null) && this.currentPhase != currentPhase) {
            this.currentPhase.removeSelf();
        }
        this.currentPhase = currentPhase;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getPhaseCount() {
        return phaseCount;
    }

    public void setPhaseCount(int phaseCount) {
        this.phaseCount = phaseCount;
    }

    public Player getActivePlayer() {
        return players.get(activePlayerIndex);
    }
}