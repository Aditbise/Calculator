import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main extends JFrame implements ActionListener {

    private JTextField textfield;
    private JPanel panel;
    private double num1, num2, result;
    private String operator;

    Main() {
        this.setLayout(new BorderLayout());

        textfield = new JTextField();
        this.add(textfield, BorderLayout.NORTH);

        String[] buttons = {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "/", "+", "-", "*", "=", "C"
        };

        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));

        for (String label : buttons) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            panel.add(button);
        }
        this.add(panel, BorderLayout.CENTER);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 400);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        try {
            if ((s.charAt(0) >= '0' && s.charAt(0) <= '9') || s.equals(".")) {
                textfield.setText(textfield.getText() + s);
            } else if (s.equals("=")) {
                num2 = Double.parseDouble(textfield.getText());
                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            textfield.setText("Error: Divide by 0");
                            return;
                        }
                        break;
                }
                textfield.setText(String.valueOf(result));
                num1 = result;
            } else if (s.equals("C")) {
                textfield.setText("");
                num1 = num2 = result = 0;
                operator = "";
            } else {
                num1 = Double.parseDouble(textfield.getText());
                operator = s;
                textfield.setText("");
            }
        } catch (Exception ex) {
            textfield.setText("Error");
        }
    }
}
