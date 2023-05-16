import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JPanel {

    private List<Card> cards;
    private JButton[][] cardButtons;
    private Card flippedCard = null; // Holder styr på de kort, der er blevet vendt

    public Board() {
        setLayout(new GridLayout(4, 3));

        cards = new ArrayList<>();
        cards.add(new Card("C:\\Users\\Ingrid\\Desktop\\untitled1\\lion.jpg", "lion"));
        cards.add(new Card("C:\\Users\\Ingrid\\Desktop\\untitled1\\lion.jpg", "lion"));
        cards.add(new Card("C:\\Users\\Ingrid\\Desktop\\untitled1\\pig.jpg", "pig"));
        cards.add(new Card("C:\\Users\\Ingrid\\Desktop\\untitled1\\pig.jpg", "pig"));
        cards.add(new Card("C:\\Users\\Ingrid\\Desktop\\untitled1\\sheep.jpg", "Sheep"));
        cards.add(new Card("C:\\Users\\Ingrid\\Desktop\\untitled1\\sheep.jpg", "Sheep"));
        cards.add(new Card("C:\\Users\\Ingrid\\Desktop\\untitled1\\dog.jpg", "dogs"));
        cards.add(new Card("C:\\Users\\Ingrid\\Desktop\\untitled1\\dog.jpg", "dogs"));
        cards.add(new Card("C:\\Users\\Ingrid\\Desktop\\untitled1\\cat.jpg", "cat"));
        cards.add(new Card("C:\\Users\\Ingrid\\Desktop\\untitled1\\cat.jpg", "cat"));
        cards.add(new Card("C:\\Users\\Ingrid\\Desktop\\untitled1\\cow.jpg", "cow"));
        cards.add(new Card("C:\\Users\\Ingrid\\Desktop\\untitled1\\cow.jpg", "cow"));

        // Shuffle cards
        Collections.shuffle(cards);

        ImageIcon cardback = new ImageIcon("C:\\Users\\Ingrid\\Desktop\\untitled1\\download_4.jpg");

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
                JOptionPane.showMessageDialog(null, "Du har fundet et par!");
                flippedCard = null; // Nulstil flippedCard
            } else {
                // Kortene matcher ikke
                JOptionPane.showMessageDialog(null, "Beklager, de kort matcher ikke.");
                card.cardFlipped = false;
                flippedCard.cardFlipped = false;
                flippedCard = null; // Nulstil flippedCard
            }
        }

        updateBoard();
    }

    private void updateBoard() {
        for (int i = 0; i < cards.size(); i++) {
            JButton button = cardButtons[i / 3][i % 3];
            if (cards.get(i).cardFlipped) {
                button.setIcon(new ImageIcon(cards.get(i).getPicture()));
            } else {
                button.setIcon(new ImageIcon("C:\\Users\\Ingrid\\Desktop\\untitled1\\download_4.jpg"));
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Memory Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Board board = new Board();
            frame.add(board);

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
