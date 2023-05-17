import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener {
    JLabel label;
    JRadioButton animals, fruits, people;
    JButton b;

    public Window() {
        label = new JLabel("Press on which type of memory game you want");
        label.setBounds(50, 50, 300, 20);
        animals = new JRadioButton("Animals");
        animals.setBounds(100, 100, 150, 20);

        people = new JRadioButton("People");
        people.setBounds(100, 200, 150, 20);
        ButtonGroup bgroup = new ButtonGroup();
        bgroup.add(animals);
        bgroup.add(people);

        b = new JButton("Continue");
        b.setBounds(100, 250, 80, 30);
        b.addActionListener(this);
        add(label);
        add(animals);
        add(people);
        add(b);
        setSize(400, 400);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (animals.isSelected()) {
            JOptionPane.showMessageDialog(this, "You selected animals.");
            Animal.main();// kalder fra Animal så spillet starter


        }

        if (people.isSelected()) {
            JOptionPane.showMessageDialog(this, "You selected people.");

            People.main();
        }
    }

}