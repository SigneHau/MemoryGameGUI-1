import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Animal extends JPanel {

    private List<Card> cards;
    private JButton[][] cardButtons;
    private Card flippedCard = null; // Holder styr på de kort, der er blevet vendt
    private long start; //Tidspunktet, hvor spillet starter

    public Animal() {
        start = System.currentTimeMillis(); //gem starttidspunktet
        setLayout(new GridLayout(4, 3));


        cards = new ArrayList<>();
        cards.add(new Card("C:\\Users\\signe\\IdeaProjects\\MemoryGameGUI\\lion.jpg", "lion"));
        cards.add(new Card("C:\\Users\\signe\\IdeaProjects\\MemoryGameGUI\\lion.jpg", "lion"));
        cards.add(new Card("C:\\Users\\signe\\IdeaProjects\\MemoryGameGUI\\pig.jpg", "pig"));
        cards.add(new Card("C:\\Users\\signe\\IdeaProjects\\MemoryGameGUI\\pig.jpg", "pig"));
        cards.add(new Card("C:\\Users\\signe\\IdeaProjects\\MemoryGameGUI\\sheep.jpg", "Sheep"));
        cards.add(new Card("C:\\Users\\signe\\IdeaProjects\\MemoryGameGUI\\sheep.jpg", "Sheep"));
        cards.add(new Card("C:\\Users\\signe\\IdeaProjects\\MemoryGameGUI\\dog.jpg", "dogs"));
        cards.add(new Card("C:\\Users\\signe\\IdeaProjects\\MemoryGameGUI\\dog.jpg", "dogs"));
        cards.add(new Card("C:\\Users\\signe\\IdeaProjects\\MemoryGameGUI\\cat.jpg", "cat"));
        cards.add(new Card("C:\\Users\\signe\\IdeaProjects\\MemoryGameGUI\\cat.jpg", "cat"));
        cards.add(new Card("C:\\Users\\signe\\IdeaProjects\\MemoryGameGUI\\cow.jpg", "cow"));
        cards.add(new Card("C:\\Users\\signe\\IdeaProjects\\MemoryGameGUI\\cow.jpg", "cow"));

        // Shuffle cards
        Collections.shuffle(cards);

        ImageIcon cardback = new ImageIcon("C:\\Users\\signe\\IdeaProjects\\MemoryGameGUI\\download_4.jpg");

        cardButtons = new JButton[4][3];
        for (int i = 0; i < cardButtons.length; i++) {
            for (int j = 0; j < cardButtons[i].length; j++) {
                cardButtons[i][j] = new JButton(cardback);

                int index = i * 3 + j;
                cardButtons[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int row = -1;
                        int col = -1;

                        // Find knappens position i 2D-arrayet
                        for (int i = 0; i < cardButtons.length; i++) {
                            for (int j = 0; j < cardButtons[i].length; j++) {
                                if (cardButtons[i][j] == e.getSource()) {
                                    row = i;
                                    col = j;
                                    break;
                                }
                            }
                        }

                        if (row != -1 && col != -1) {
                            int index = row * 3 + col;
                            handleCardFlip(index);
                        }
                    }
                });
                add(cardButtons[i][j]);
            }
        }

    }

    private void handleCardFlip(int index) {
        Card card = cards.get(index);

        if (flippedCard == null) {
            // Vend det første kort
            flippedCard = card;
            card.cardFlipped = true;
        } else {
            // Vend det andet kort
            card.cardFlipped = true;
            updateBoard();

            if (card.getDescription().equals(flippedCard.getDescription())) {
                // Kortene matcher
                // JOptionPane.showMessageDialog(null, "Du har fundet et par!");
                flippedCard = null; // Nulstil flippedCard
            } else {
                // Kortene matcher ikke
                JOptionPane.showMessageDialog(null, "Beklager, kortene matcher ikke.");
                card.cardFlipped = false;
                flippedCard.cardFlipped = false;
                flippedCard = null; // Nulstil flippedCard
            }
        }

        updateBoard();

        //Kontroller om alle par er fundet
        int pairsFound = 0;
        for (Card c : cards) {
            if (c.cardFlipped) {
                pairsFound++;
            }
        }
        int requiredPairs = 12; //Dette nummer kan ændres, hvis man skal have flere eller færre stik for at vinde spillet.
        if (pairsFound == requiredPairs) {
            //Alle par er fundet, det ønskede antal af stik er opnået.
            long finish = System.currentTimeMillis();
            long timeElapsed = (finish - start) / 1000;
            JOptionPane.showMessageDialog(null, "Tillykke, du vandt! \n Din tid: " + timeElapsed + " sekunder!");
            //JOptionPane.showMessageDialog(null, "Congratulations, you won!\n Your time: " + timeElapsed + " seconds");
        }
    }

    private void updateBoard() {
        for (int i = 0; i < cards.size(); i++) {
            JButton button = cardButtons[i / 3][i % 3];
            if (cards.get(i).cardFlipped) {
                button.setIcon(new ImageIcon(cards.get(i).getPicture()));
            } else {
                button.setIcon(new ImageIcon("C:\\Users\\signe\\IdeaProjects\\MemoryGameGUI\\download_4.jpg"));
            }
        }
    }

    public static void main() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Memory Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);
            frame.setResizable(true);

            Animal animal = new Animal();
            frame.add(animal);

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

