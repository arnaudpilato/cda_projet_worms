package org.wcscda.worms.gamemechanism;

import org.wcscda.worms.Helper;
import org.wcscda.worms.Player;
import org.wcscda.worms.Worm;
import org.wcscda.worms.board.AbstractDrawableElement;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class StartGame extends AbstractDrawableElement {
    private boolean teamAndWormsMenu = false;
    private boolean optionsMenu = false;
    private boolean nameOfWormsMenu1 = false;
    private boolean nameOfWormsMenu2 = false;
    private boolean nameOfWormsMenu3 = false;

    private boolean beginner = false;

    private TimeController initialize;
    private int numberOfTeams = 1;
    private int numberOfWorms = 1;
    private boolean background = true;
    private final Icon iconValidate = new ImageIcon("src/resources/validate.png");
    private final Icon iconBack = new ImageIcon("src/resources/back.png");
    private final Icon iconFictif = new ImageIcon("src/resources/begin.png");

    private static final String imagePath1 = "src/resources/start.jpeg";
    private static final String imagePath2 = "src/resources/background.png";
    private static final String imagePath3 = "src/resources/number-of-players.png";
    private static final String imagePath4 = "src/resources/number-of-worms.png";
    private static final String imagePath5 = "src/resources/name-of-worms.png";
    private static final String imagePath6 = "src/resources/begin-title.png";
    private static final String imagePath7 = "src/resources/validate-logo.png";
    private static final String[] imagePath8 = {
            "src/resources/name-of-player-1.png",
            "src/resources/name-of-player-2.png",
            "src/resources/name-of-player-3.png",
            "src/resources/name-of-player-4.png",
    };

    private static final String[] imagePath9 = {
            "src/resources/numbers/1.png",
            "src/resources/numbers/2.png",
            "src/resources/numbers/3.png",
            "src/resources/numbers/4.png",
            "src/resources/numbers/5.png",
    };

    private static Image image = null;
    private static Image imageBackground = null;
    private static Image imageNumberOfPlayers = null;
    private static Image imageNumberOfWorms = null;
    private static Image nameOfWorms = null;
    private static Image beginTitle = null;
    private static Image check = null;
    private static final Image[] nameOfPlayer = new Image[4];
    private static final Image[] numbers = new Image[5];

    public boolean isBeginner() {
        return beginner;
    }

    private WormSoundPlayer theme = new WormSoundPlayer();

    private static void initImages() {
        image = new ImageIcon(imagePath1).getImage().getScaledInstance(1200, 800, 0);
        imageBackground = new ImageIcon(imagePath2).getImage().getScaledInstance(800, 500, 0);
        imageNumberOfPlayers = new ImageIcon(imagePath3).getImage().getScaledInstance(423, 35, 0);
        imageNumberOfWorms = new ImageIcon(imagePath4).getImage().getScaledInstance(381, 35, 0);
        nameOfWorms = new ImageIcon(imagePath5).getImage().getScaledInstance(328, 35, 0);
        beginTitle = new ImageIcon(imagePath6).getImage().getScaledInstance(341, 35, 0);
        check = new ImageIcon(imagePath7).getImage().getScaledInstance(50, 50, 0);

        for (int i = 0; i < imagePath8.length; i++) {
            nameOfPlayer[i] = new ImageIcon(imagePath8[i]).getImage().getScaledInstance(344, 36, 0);
        }

        for (int i = 0; i < imagePath9.length; i++) {
            numbers[i] = new ImageIcon(imagePath9[i]).getImage().getScaledInstance(40, 37, 0);
        }
    }

    public StartGame(TimeController tc) {
        this.initialize = tc;
        JButton start = new JButton("Start Game");
        JPanel pnlButton = new JPanel();
        Helper.getPC().setLayout(null);
        pnlButton.setLayout(null);

        start.setFont(new Font("Arial", Font.BOLD, 30));
        start.setSize(300,50);
        start.setLocation(700,600);
        pnlButton.add(start);
        Helper.getPC().add(start);
        pnlButton.setLocation(0,0);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teamAndWormsMenu = true;
                start.setVisible(false);
                newGame();
                try {
                    new WormSoundPlayer().chooseSound();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void menu() {
        JPanel pnlButton = new JPanel();
        Helper.getPC().setLayout(null);
        pnlButton.setLayout(null);
        pnlButton.setLocation(0,0);

        JButton newGame = new JButton("New Game");
        newGame.setFont(new Font("Arial", Font.BOLD, 30));
        newGame.setSize(300,50);
        newGame.setLocation(700,600);
        newGame.setVisible(true);
        pnlButton.add(newGame);
        Helper.getPC().add(newGame);

        JButton options = new JButton("Options");
        options.setFont(new Font("Arial", Font.BOLD, 30));
        options.setSize(300,50);
        options.setLocation(700,650);
        options.setVisible(true);
        pnlButton.add(options);
        Helper.getPC().add(options);

        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teamAndWormsMenu = true;
                newGame.setVisible(false);
                options.setVisible(false);
                newGame();
                try {
                    new WormSoundPlayer().chooseSound();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        options.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                optionsMenu = true;
                newGame.setVisible(false);
                options.setVisible(false);
                options();
            }
        });
    }

    public void options() {
        JPanel pnlButton = new JPanel();
        Helper.getPC().setLayout(null);
        pnlButton.setLayout(null);
        pnlButton.setLocation(0,0);

        // Boutton yes
        JButton fictif = new JButton(iconFictif);
        fictif.setSize(341,35);
        fictif.setLocation(400,300);
        fictif.setBorderPainted(false);
        pnlButton.add(fictif);
        Helper.getPC().add(fictif);

        // Boutton yes
        JButton yes = new JButton("YES");
        yes.setSize(100,50);
        yes.setLocation(460,400);
        yes.setFont(new Font("Roboto", Font.BOLD, 30));
        pnlButton.add(yes);
        Helper.getPC().add(yes);

        // Boutton no
        JButton no = new JButton("NON");
        no.setSize(100,50);
        no.setLocation(580,400);
        no.setFont(new Font("Roboto", Font.BOLD, 30));
        no.setForeground(Color.green);
        pnlButton.add(no);
        Helper.getPC().add(no);

        // Boutton retour
        JButton back = new JButton(iconBack);
        back.setSize(169,35);
        back.setLocation(800,550);
        back.setBorderPainted(false);
        pnlButton.add(back);
        Helper.getPC().add(back);

        yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                yes.setForeground(Color.green);
                no.setForeground(Color.black);
                beginner = true;
            }
        });

        no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                no.setForeground(Color.green);
                yes.setForeground(Color.black);
                beginner = false;
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnlButton.remove(pnlButton);
                no.remove(pnlButton);
                back.remove(pnlButton);

                pnlButton.revalidate();
                pnlButton.repaint();
               // yes.setVisible(false);
               // no.setVisible(false);
               // back.setVisible(false);
                menu();
            }
        });
    }

    public void newGame() {
        JPanel pnlButton = new JPanel();
        Helper.getPC().setLayout(null);
        pnlButton.setLayout(null);
        pnlButton.setLocation(0,0);

        // Ajout 1 joueur
        JButton player1 = new JButton("1");
        player1.setSize(50,50);
        player1.setLocation(510,320);
        player1.setFont(new Font("Roboto", Font.BOLD, 20));
        player1.setForeground(Color.green);
        pnlButton.add(player1);
        Helper.getPC().add(player1);

        // Ajout 2 joueurs
        JButton player2 = new JButton("2");
        player2.setSize(50,50);
        player2.setLocation(560,320);
        player2.setFont(new Font("Roboto", Font.BOLD, 20));
        pnlButton.add(player2);
        Helper.getPC().add(player2);

        // Ajout 3 joueurs
        JButton player3 = new JButton("3");
        player3.setSize(50,50);
        player3.setLocation(610,320);
        player3.setFont(new Font("Roboto", Font.BOLD, 20));
        pnlButton.add(player3);
        Helper.getPC().add(player3);


        // Ajout 1 worms
        JButton worms1 = new JButton("1");
        worms1.setSize(50,50);
        worms1.setLocation(460,500);
        worms1.setFont(new Font("Roboto", Font.BOLD, 20));
        worms1.setForeground(Color.green);
        pnlButton.add(worms1);
        Helper.getPC().add(worms1);

        // Ajout 2 worms
        JButton worms2 = new JButton("2");
        worms2.setSize(50,50);
        worms2.setLocation(510,500);
        worms2.setFont(new Font("Roboto", Font.BOLD, 20));
        pnlButton.add(worms2);
        Helper.getPC().add(worms2);

        // Ajout 3 worms
        JButton worms3 = new JButton("3");
        worms3.setSize(50,50);
        worms3.setLocation(560,500);
        worms3.setFont(new Font("Roboto", Font.BOLD, 20));
        pnlButton.add(worms3);
        Helper.getPC().add(worms3);

        // Ajout 4 worms
        JButton worms4 = new JButton("4");
        worms4.setSize(50,50);
        worms4.setLocation(610,500);
        worms4.setFont(new Font("Roboto", Font.BOLD, 20));
        pnlButton.add(worms4);
        Helper.getPC().add(worms4);

        // Ajout 5 worms
        JButton worms5 = new JButton("5");
        worms5.setSize(50,50);
        worms5.setLocation(660,500);
        worms5.setFont(new Font("Roboto", Font.BOLD, 20));
        pnlButton.add(worms5);
        Helper.getPC().add(worms5);

        // Boutton de validation
        JButton validate = new JButton(iconValidate);
        validate.setSize(171,34);
        validate.setLocation(500,600);
        validate.setBorderPainted(false);
        pnlButton.add(validate);
        Helper.getPC().add(validate);

        // Boutton fictif
        JButton fictif = new JButton(iconFictif);
        fictif.setSize(221,20);
        fictif.setLocation(100,720);
        fictif.setBorderPainted(false);
        pnlButton.add(fictif);
        Helper.getPC().add(fictif);
        fictif.setVisible(true);

        // Boutton yes
        JButton yes = new JButton("OUI");
        yes.setSize(70,35);
        yes.setLocation(140,750);
        yes.setFont(new Font("Roboto", Font.BOLD, 18));
        pnlButton.add(yes);
        Helper.getPC().add(yes);
        yes.setVisible(true);

        // Boutton no
        JButton no = new JButton("NON");
        no.setSize(70,35);
        no.setLocation(210,750);
        no.setFont(new Font("Roboto", Font.BOLD, 18));
        no.setForeground(Color.green);
        pnlButton.add(no);
        Helper.getPC().add(no);
        no.setVisible(true);

        player1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player1.setForeground(Color.green);
                player2.setForeground(Color.black);
                player3.setForeground(Color.black);

                numberOfTeams = 1;
                try {
                    new WormSoundPlayer().numberSound();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        player2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player1.setForeground(Color.black);
                player2.setForeground(Color.green);
                player3.setForeground(Color.black);

                numberOfTeams = 2;
                try {
                    new WormSoundPlayer().numberSound();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        player3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player1.setForeground(Color.black);
                player2.setForeground(Color.black);
                player3.setForeground(Color.green);

                numberOfTeams = 3;
                try {
                    new WormSoundPlayer().numberSound();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        worms1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                worms1.setForeground(Color.green);
                worms2.setForeground(Color.black);
                worms3.setForeground(Color.black);
                worms4.setForeground(Color.black);
                worms5.setForeground(Color.black);

                numberOfWorms = 1;
                try {
                    new WormSoundPlayer().numberSound();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        worms2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                worms1.setForeground(Color.black);
                worms2.setForeground(Color.green);
                worms3.setForeground(Color.black);
                worms4.setForeground(Color.black);
                worms5.setForeground(Color.black);

                numberOfWorms = 2;
                try {
                    new WormSoundPlayer().numberSound();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        worms3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                worms1.setForeground(Color.black);
                worms2.setForeground(Color.black);
                worms3.setForeground(Color.green);
                worms4.setForeground(Color.black);
                worms5.setForeground(Color.black);

                numberOfWorms = 3;
                try {
                    new WormSoundPlayer().numberSound();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        worms4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                worms1.setForeground(Color.black);
                worms2.setForeground(Color.black);
                worms3.setForeground(Color.black);
                worms4.setForeground(Color.green);
                worms5.setForeground(Color.black);

                numberOfWorms = 4;
                try {
                    new WormSoundPlayer().numberSound();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        worms5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                worms1.setForeground(Color.black);
                worms2.setForeground(Color.black);
                worms3.setForeground(Color.black);
                worms4.setForeground(Color.black);
                worms5.setForeground(Color.green);

                numberOfWorms = 5;
                try {
                    new WormSoundPlayer().numberSound();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                yes.setForeground(Color.green);
                no.setForeground(Color.black);
                beginner = true;
                try {
                    new WormSoundPlayer().numberSound();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                no.setForeground(Color.green);
                yes.setForeground(Color.black);
                beginner = false;
                try {
                    new WormSoundPlayer().numberSound();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Bouton validation
        validate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] players = new String[numberOfTeams];
                String[][] worms = new String[numberOfTeams][numberOfWorms];
                teamAndWormsMenu = false;
                nameOfWormsMenu1 = true;

                player1.setVisible(false);
                player2.setVisible(false);
                player3.setVisible(false);

                worms1.setVisible(false);
                worms2.setVisible(false);
                worms3.setVisible(false);
                worms4.setVisible(false);
                worms5.setVisible(false);

                fictif.setVisible(false);
                yes.setVisible(false);
                no.setVisible(false);

                validate.setVisible(false);
                nameTeam1(players, worms);
                try {
                    new WormSoundPlayer().chooseSound();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void nameTeam1(String[] players,String[][] worms) {
        JPanel pnlButton = new JPanel();
        Helper.getPC().setLayout(null);
        pnlButton.setLayout(null);
        pnlButton.setLocation(0,0);

        // Boutton de validation
        JButton validate = new JButton(iconValidate);
        validate.setSize(171,34);
        validate.setLocation(500,600);
        validate.setBorderPainted(false);
        pnlButton.add(validate);
        Helper.getPC().add(validate);

        // Nom de la team 1
        JTextArea setNameOfPlayer1 = new JTextArea("BROCOLI");
        setNameOfPlayer1.setSize(300, 40);
        setNameOfPlayer1.setFont(new Font("Roboto", Font.BOLD, 20));
        setNameOfPlayer1.setLocation(90, 300);
        setNameOfPlayer1.setMargin(new Insets(8, 10, 0, 0));
        players[0] = setNameOfPlayer1.getText();
        Helper.getPC().add(setNameOfPlayer1);

        //Nom du ver 1
        JTextArea nameOfWorm1 = new JTextArea("Michel");
        nameOfWorm1.setSize(300, 40);
        nameOfWorm1.setLocation(492, 300);
        nameOfWorm1.setFont(new Font("Roboto", Font.BOLD, 20));
        nameOfWorm1.setMargin(new Insets(8, 10, 0, 0));
        worms[0][0] = nameOfWorm1.getText();
        Helper.getPC().add(nameOfWorm1);

        // Nom du ver 2
        JTextArea nameOfWorm2 = new JTextArea("Roger");
        nameOfWorm2.setSize(300, 40);
        nameOfWorm2.setLocation(492, 350);
        nameOfWorm2.setFont(new Font("Roboto", Font.BOLD, 20));
        nameOfWorm2.setMargin(new Insets(8, 10, 0, 0));
        if (numberOfWorms >= 2) {
            Helper.getPC().add(nameOfWorm2);
            worms[0][1] = nameOfWorm2.getText();
        }

        // Nom du ver 3
        JTextArea nameOfWorm3 = new JTextArea("Denis");
        nameOfWorm3.setSize(300, 40);
        nameOfWorm3.setLocation(492, 400);
        nameOfWorm3.setFont(new Font("Roboto", Font.BOLD, 20));
        nameOfWorm3.setMargin(new Insets(8, 10, 0, 0));
        if (numberOfWorms >= 3) {
        Helper.getPC().add(nameOfWorm3);
            worms[0][2] = nameOfWorm3.getText();
        }

        // Nom du ver 4
        JTextArea nameOfWorm4 = new JTextArea("Bernard");
        nameOfWorm4.setSize(300, 40);
        nameOfWorm4.setLocation(492, 450);
        nameOfWorm4.setFont(new Font("Roboto", Font.BOLD, 20));
        nameOfWorm4.setMargin(new Insets(8, 10, 0, 0));
        if (numberOfWorms >= 4) {
            Helper.getPC().add(nameOfWorm4);
            worms[0][3] = nameOfWorm4.getText();
        }

        // Nom du ver 5
        JTextArea nameOfWorm5 = new JTextArea("Alice");
        nameOfWorm5.setSize(300, 40);
        nameOfWorm5.setLocation(492, 500);
        nameOfWorm5.setFont(new Font("Roboto", Font.BOLD, 20));
        nameOfWorm5.setMargin(new Insets(8, 10, 0, 0));
        if (numberOfWorms >= 5) {
            Helper.getPC().add(nameOfWorm5);
            worms[0][4] = nameOfWorm5.getText();
        }

        // Bouton validation
        validate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameOfWormsMenu1 = false;
                nameOfWormsMenu2 = true;
                setNameOfPlayer1.setVisible(false);
                nameOfWorm1.setVisible(false);
                nameOfWorm2.setVisible(false);
                nameOfWorm3.setVisible(false);
                nameOfWorm4.setVisible(false);
                nameOfWorm5.setVisible(false);
                validate.setVisible(false);

                if (numberOfTeams == 1) {
                    background = false;
                    initialize.initGame(players, worms, beginner);
                } else {
                    nameTeam2(players, worms);
                    try {
                        new WormSoundPlayer().chooseSound();
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    public void nameTeam2(String[] players,String[][] worms) {
        JPanel pnlButton = new JPanel();
        Helper.getPC().setLayout(null);
        pnlButton.setLayout(null);
        pnlButton.setLocation(0,0);

        // Boutton de validation
        JButton validate = new JButton(iconValidate);
        validate.setSize(171,34);
        validate.setLocation(500,600);
        validate.setBorderPainted(false);
        pnlButton.add(validate);
        Helper.getPC().add(validate);

        // Nom de la team 2
        JTextArea setNameOfPlayer2 = new JTextArea("TARTIFLETTE");
        setNameOfPlayer2.setSize(300, 40);
        setNameOfPlayer2.setFont(new Font("Roboto", Font.BOLD, 20));
        setNameOfPlayer2.setLocation(90, 300);
        setNameOfPlayer2.setMargin(new Insets(8, 10, 0, 0));
        players[1] = setNameOfPlayer2.getText();
        Helper.getPC().add(setNameOfPlayer2);

        //Nom du ver 1
        JTextArea nameOfWorm1 = new JTextArea("Gérard");
        nameOfWorm1.setSize(300, 40);
        nameOfWorm1.setLocation(492, 300);
        nameOfWorm1.setFont(new Font("Roboto", Font.BOLD, 20));
        nameOfWorm1.setMargin(new Insets(8, 10, 0, 0));
        worms[1][0] = nameOfWorm1.getText();
        Helper.getPC().add(nameOfWorm1);

        // Nom du ver 2
        JTextArea nameOfWorm2 = new JTextArea("Robin");
        nameOfWorm2.setSize(300, 40);
        nameOfWorm2.setLocation(492, 350);
        nameOfWorm2.setFont(new Font("Roboto", Font.BOLD, 20));
        nameOfWorm2.setMargin(new Insets(8, 10, 0, 0));
        if (numberOfWorms >= 2) {
            Helper.getPC().add(nameOfWorm2);
            worms[1][1] = nameOfWorm2.getText();
        }

        // Nom du ver 3
        JTextArea nameOfWorm3 = new JTextArea("Pil");
        nameOfWorm3.setSize(300, 40);
        nameOfWorm3.setLocation(492, 400);
        nameOfWorm3.setFont(new Font("Roboto", Font.BOLD, 20));
        nameOfWorm3.setMargin(new Insets(8, 10, 0, 0));
        if (numberOfWorms >= 3) {
            Helper.getPC().add(nameOfWorm3);
            worms[1][2] = nameOfWorm3.getText();
        }

        // Nom du ver 4
        JTextArea nameOfWorm4 = new JTextArea("Eléonore");
        nameOfWorm4.setSize(300, 40);
        nameOfWorm4.setLocation(492, 450);
        nameOfWorm4.setFont(new Font("Roboto", Font.BOLD, 20));
        nameOfWorm4.setMargin(new Insets(8, 10, 0, 0));
        if (numberOfWorms >= 4) {
            Helper.getPC().add(nameOfWorm4);
            worms[1][3] = nameOfWorm4.getText();
        }

        // Nom du ver 5
        JTextArea nameOfWorm5 = new JTextArea("Zurabi");
        nameOfWorm5.setSize(300, 40);
        nameOfWorm5.setLocation(492, 500);
        nameOfWorm5.setFont(new Font("Roboto", Font.BOLD, 20));
        nameOfWorm5.setMargin(new Insets(8, 10, 0, 0));
        if (numberOfWorms >= 5) {
            Helper.getPC().add(nameOfWorm5);
            worms[1][4] = nameOfWorm5.getText();
        }

        // Bouton validation
        validate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameOfWormsMenu2 = false;
                nameOfWormsMenu3 = true;
                setNameOfPlayer2.setVisible(false);
                nameOfWorm1.setVisible(false);
                nameOfWorm2.setVisible(false);
                nameOfWorm3.setVisible(false);
                nameOfWorm4.setVisible(false);
                nameOfWorm5.setVisible(false);
                validate.setVisible(false);

                if (numberOfTeams == 2) {
                    background = false;
                    initialize.initGame(players, worms, beginner);
                } else {
                    nameTeam3(players, worms);
                    try {
                        new WormSoundPlayer().chooseSound();
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    public void nameTeam3(String[] players,String[][] worms) {
        JPanel pnlButton = new JPanel();
        Helper.getPC().setLayout(null);
        pnlButton.setLayout(null);
        pnlButton.setLocation(0,0);

        // Boutton de validation
        JButton validate = new JButton(iconValidate);
        validate.setSize(171,34);
        validate.setLocation(500,600);
        validate.setBorderPainted(false);
        pnlButton.add(validate);
        Helper.getPC().add(validate);

        // Nom de la team 3
        JTextArea setNameOfPlayer3 = new JTextArea("CRACK");
        setNameOfPlayer3.setSize(300, 40);
        setNameOfPlayer3.setFont(new Font("Roboto", Font.BOLD, 20));
        setNameOfPlayer3.setLocation(90, 300);
        setNameOfPlayer3.setMargin(new Insets(8, 10, 0, 0));
        players[2] = setNameOfPlayer3.getText();
        Helper.getPC().add(setNameOfPlayer3);

        //Nom du ver 1
        JTextArea nameOfWorm1 = new JTextArea("Mathias");
        nameOfWorm1.setSize(300, 40);
        nameOfWorm1.setLocation(492, 300);
        nameOfWorm1.setFont(new Font("Roboto", Font.BOLD, 20));
        nameOfWorm1.setMargin(new Insets(8, 10, 0, 0));
        worms[2][0] = nameOfWorm1.getText();
        Helper.getPC().add(nameOfWorm1);

        // Nom du ver 2
        JTextArea nameOfWorm2 = new JTextArea("Mathieu");
        nameOfWorm2.setSize(300, 40);
        nameOfWorm2.setLocation(492, 350);
        nameOfWorm2.setFont(new Font("Roboto", Font.BOLD, 20));
        nameOfWorm2.setMargin(new Insets(8, 10, 0, 0));
        if (numberOfWorms >= 2) {
            Helper.getPC().add(nameOfWorm2);
            worms[2][1] = nameOfWorm2.getText();
        }

        // Nom du ver 3
        JTextArea nameOfWorm3 = new JTextArea("Raphaël");
        nameOfWorm3.setSize(300, 40);
        nameOfWorm3.setLocation(492, 400);
        nameOfWorm3.setFont(new Font("Roboto", Font.BOLD, 20));
        nameOfWorm3.setMargin(new Insets(8, 10, 0, 0));
        if (numberOfWorms >= 3) {
            Helper.getPC().add(nameOfWorm3);
            worms[2][2] = nameOfWorm3.getText();
        }

        // Nom du ver 4
        JTextArea nameOfWorm4 = new JTextArea("Thuy");
        nameOfWorm4.setSize(300, 40);
        nameOfWorm4.setLocation(492, 450);
        nameOfWorm4.setFont(new Font("Roboto", Font.BOLD, 20));
        nameOfWorm4.setMargin(new Insets(8, 10, 0, 0));
        if (numberOfWorms >= 4) {
            Helper.getPC().add(nameOfWorm4);
            worms[2][3] = nameOfWorm4.getText();
        }

        // Nom du ver 5
        JTextArea nameOfWorm5 = new JTextArea("Gersey");
        nameOfWorm5.setSize(300, 40);
        nameOfWorm5.setLocation(492, 500);
        nameOfWorm5.setFont(new Font("Roboto", Font.BOLD, 20));
        nameOfWorm5.setMargin(new Insets(8, 10, 0, 0));
        if (numberOfWorms >= 5) {
            Helper.getPC().add(nameOfWorm5);
            worms[2][4] = nameOfWorm5.getText();
        }

        // Bouton validation
        validate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameOfWormsMenu3 = false;
                setNameOfPlayer3.setVisible(false);
                nameOfWorm1.setVisible(false);
                nameOfWorm2.setVisible(false);
                nameOfWorm3.setVisible(false);
                nameOfWorm4.setVisible(false);
                nameOfWorm5.setVisible(false);
                validate.setVisible(false);

                if (numberOfTeams == 3) {
                    background = false;
                    initialize.initGame(players, worms, beginner);
                }
            }
        });
    }


    @Override
    protected void drawMain(Graphics2D g, ImageObserver io) {
        if (image == null) {
            initImages();
        }

        if (background) {
            g.drawImage(image, 0,0, io);

            if (optionsMenu) {
                // Mode débutant
                //g.drawImage(beginTitle, 400, 300, io);
            }

            if (teamAndWormsMenu) {
                // Ajouter des joueurs
                g.drawImage(imageNumberOfPlayers, 380, 250, io);

                // Ajouter des worms
                g.drawImage(imageNumberOfWorms, 400, 430, io);
            }

            if (nameOfWormsMenu1) {
                // Nom du joueur 1
                g.drawImage(nameOfPlayer[0], 70, 250, io);

                // Nom des worms
                g.drawImage(nameOfWorms, 480, 250, io);
            }

            if (nameOfWormsMenu2) {
                // Nom du joueur 2
                g.drawImage(nameOfPlayer[1], 70, 250, io);

                // Nom des worms
                g.drawImage(nameOfWorms, 480, 250, io);
            }

            if (nameOfWormsMenu3) {
                // Nom du joueur 3
                g.drawImage(nameOfPlayer[2], 70, 250, io);

                // Nom des worms
                g.drawImage(nameOfWorms, 480, 250, io);
            }
        }
    }
}
