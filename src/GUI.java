import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI {
    private static GUI gui;
    private ArrayList<JButton> squares;
    public static ArrayList<BoardElements> board;

    private GUI() {
        starterPanel();
    }

    private void starterPanel() {
        JFrame jFrame = new JFrame("pick a side!");

        ImageIcon unscaledNought = new ImageIcon("src/nought.png");
        Image noughtImage = unscaledNought.getImage();  // transfers the image icon to image
        Image scaledNought = noughtImage.getScaledInstance(133, 120, Image.SCALE_SMOOTH);
        ImageIcon scaledNoughtIcon = new ImageIcon(scaledNought);

        JButton nought = new JButton(scaledNoughtIcon);
        JButton cross = new JButton(new ImageIcon("src/cross.png"));

        nought.setBackground(Color.white);
        cross.setBackground(Color.white);
        nought.setSize(50, 50);
        cross.setSize(50, 50);

        nought.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Players.setPlayer(BoardElements.O);
                Players.lockPlayer(true);
                Players.setRival(BoardElements.X);
                Players.lockRival(true);
                mainPanel();
                jFrame.dispose();
            }
        });

        cross.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Players.setPlayer(BoardElements.X);
                Players.lockPlayer(true);
                Players.setRival(BoardElements.O);
                Players.lockRival(true);
                mainPanel();
                jFrame.dispose();
            }
        });

        jFrame.add(nought);
        jFrame.add(cross);

        jFrame.setSize(500, 178);
        jFrame.setResizable(false);
        jFrame.setLayout(new FlowLayout());
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void mainPanel() {
        System.out.println("player : " + Players.getPlayer());
        System.out.println("rival : " + Players.getRival());

        JFrame frame = new JFrame("Tic Tac Toe!!");
        squares = new ArrayList<>();
        board = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            squares.add(new JButton());
            board.add(BoardElements.Empty);
            frame.add(squares.get(i));
            squares.get(i).setBackground(Color.WHITE);


            int finalI = i;
            squares.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {

                    if (board.get(finalI) == BoardElements.Empty) {

                        board.set(finalI, Players.getPlayer());

                        if (Players.getPlayer() == BoardElements.X) {

                            squares.get(finalI).setIcon(new ImageIcon("src/cross.png"));

                            GameStatus gameStatus = Utility.getGameStatus(board);
                            if (gameStatus == GameStatus.onProgress) {
                                int AI_nextMove = AI.nextMove();
                                squares.get(AI_nextMove).setIcon(new ImageIcon("src/nought.png"));
                                board.set(AI_nextMove, Players.getRival());
                                if(Utility.getGameStatus(board) == GameStatus.rivalWon){
                                    endingPanel("Loser!! :P");
                                    frame.dispose();
                                }
                            } else if (gameStatus == GameStatus.playerWon) {
                                endingPanel("Victorious!!");
                                frame.dispose();
                            } else if (gameStatus == GameStatus.rivalWon) {
                                endingPanel("Loser!! :P");
                                frame.dispose();
                            } else if (gameStatus == GameStatus.draw) {
                                endingPanel("Draw!! :(");
                                frame.dispose();
                            }

                        } else {

                            GameStatus gameStatus = Utility.getGameStatus(board);
                            if(gameStatus == GameStatus.onProgress) {
                                squares.get(finalI).setIcon(new ImageIcon("src/nought.png"));
                                int AI_nextMove = AI.nextMove();
                                squares.get(AI_nextMove).setIcon(new ImageIcon("src/cross.png"));
                                board.set(AI_nextMove, Players.getRival());
                                if (Utility.getGameStatus(board) == GameStatus.rivalWon){
                                    endingPanel("Loser!! :P");
                                    frame.dispose();
                                }
                            } else if (gameStatus == GameStatus.playerWon) {
                                endingPanel("Victorious!!");
                                frame.dispose();
                            } else if (gameStatus == GameStatus.rivalWon) {
                                endingPanel("Loser!! :P");
                                frame.dispose();
                            } else if (gameStatus == GameStatus.draw) {
                                endingPanel("Draw!! :(");
                                frame.dispose();
                            }
                        }


                    } else {
                        System.out.println("The button is already signed!");
                    }
                }
            });
        }


        frame.setLayout(new GridLayout(3, 3, 3, 3));
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.CYAN);
    }


    private void endingPanel(String message) {
        JFrame endingFrame = new JFrame();
        JLabel endingMessage = new JLabel(message);
        JButton restart = new JButton("Restart");

        restart.setBackground(Color.white);
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                board = null;
                Players.lockRival(false);
                Players.lockPlayer(false);
                starterPanel();
                endingFrame.dispose();
            }
        });

        endingMessage.setFont(new Font("Serif", Font.ROMAN_BASELINE, 50));
        endingMessage.setHorizontalAlignment(JLabel.CENTER);
        endingMessage.setVerticalAlignment(JLabel.CENTER);

        endingFrame.add(endingMessage, BorderLayout.CENTER);
        endingFrame.add(restart, BorderLayout.SOUTH);

        endingFrame.setSize(300, 300);
        endingFrame.setLocationRelativeTo(null);
        endingFrame.setVisible(true);
        endingFrame.setResizable(false);
        endingFrame.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);

    }

    public static GUI getInstance() {
        if (gui == null) {
            gui = new GUI();
        }
        return gui;
    }


}
