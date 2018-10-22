import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private final int sizeX = 600;
    private final int sizeY = 450;
    private CheckboxGroup checkbox_group;
    private JTextField rows1, columns1, rows2, columns2;
    private JButton button;
    private Mode mode = Mode.ADDITION;

    private MainWindow() {
        initiateWindow();
        createGui();
    }

    private boolean validateTextField(JTextField textField){
        try {
            int i = Integer.parseInt(textField.getText());
            if (i>0 && i<=10)
                return true;
            else {
                textField.setText("Zla wartosc");
                return false;
            }
        }
        catch (NumberFormatException ex){
            textField.setText("Zla wartosc");
            return false;
        }
    }

    private boolean validateCalculation () {
        int r1 = Integer.parseInt(rows1.getText());
        int c1 = Integer.parseInt(columns1.getText());
        int r2 = Integer.parseInt(rows2.getText());
        int c2 = Integer.parseInt(columns2.getText());
        switch(mode) {
            case ADDITION:
                return (r1 == r2 && c1 == c2);
            case MULTIPLICATION:
                return (c1 == r2);
            case DETERMINANT:
                return (r1==c1);
            default:
                return false;
        }
    }

    private void switchWindow(JFrame window) {
        setVisible(false);
        window.setVisible(true);
    }

    private void switchMode(){
        Checkbox selected = checkbox_group.getSelectedCheckbox();
        String name = selected.getLabel();
        if(name.equals("Dodawanie"))
            this.mode = Mode.ADDITION;
        else if (name.equals("Mnozenie"))
            this.mode = Mode.MULTIPLICATION;
        else if (name.equals("Wyznacznik"))
            this.mode = Mode.DETERMINANT;
    }

    private UserInputWindow createInputWindow() {
        int r1 = Integer.parseInt(rows1.getText());
        int c1 = Integer.parseInt(columns1.getText());
        int r2 = Integer.parseInt(rows2.getText());
        int c2 = Integer.parseInt(columns2.getText());
        return new UserInputWindow(this, mode, r1, c1, r2, c2);
    }

    private void initiateWindow() {
        setTitle("Kalkulator macierzy");
        setBounds(300, 100, sizeX, sizeY);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createGui() {
        setLayout(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();
        con.fill = GridBagConstraints.HORIZONTAL;
        JPanel left_panel = new JPanel();
        JPanel right_panel = new JPanel();
        rows1 = new JTextField("2");
        columns1 = new JTextField("2");
        rows2 = new JTextField("2");
        columns2 = new JTextField("2");
        button = new JButton("Dalej");
        button.addActionListener(new ButtonListener());
        con.gridx = 0; con.gridy = 0; con.weightx = 0.3; add(left_panel, con);
        con.gridx = 1; con.gridy = 0; con.weightx = 0.7; add(right_panel, con);
        organizeLeftPanel(left_panel);
        organizeRightPanel(right_panel);
    }

    private void organizeLeftPanel(JPanel left_panel) {
        Checkbox addition_checkbox, multiplication_checkbox, determinant_checkbox;
        left_panel.setLayout(new GridLayout(4, 1));
        left_panel.setBackground(Color.WHITE);
        checkbox_group = new CheckboxGroup();
        addition_checkbox = new Checkbox("Dodawanie", checkbox_group, true);
        multiplication_checkbox = new Checkbox("Mnozenie", checkbox_group, false);
        determinant_checkbox = new Checkbox("Wyznacznik", checkbox_group, false);
        left_panel.add(new JLabel("Operacja"));
        left_panel.add(addition_checkbox);
        left_panel.add(multiplication_checkbox);
        left_panel.add(determinant_checkbox);
    }

    private void organizeRightPanel(JPanel right_panel) {
        right_panel.setLayout(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();
        con.gridwidth = 2; con.gridheight = 1;
        con.weightx = 0.5; con.weighty = 0.5;
        con.fill = GridBagConstraints.HORIZONTAL;
        con.ipadx = 80; con.ipady = 35;

        con.gridx = 0; con.gridy = 0;
        right_panel.add(new JLabel("Pierwsza macierz"), con);
        con.gridwidth = 1;
        con.gridy = 1; right_panel.add(new Label("Liczba kolumn"), con);
        con.gridx = 1; right_panel.add(new Label("Liczba wierszy"), con);
        con.gridx=0; con.gridy = 2; right_panel.add(columns1, con);
        con.gridx=1; right_panel.add(rows1, con);
        con.gridwidth = 2;
        con.gridx=0; con.gridy=3; right_panel.add(new JLabel("Druga macierz " +
                "(ignorowana w przypadku liczenia wyznacznika)"), con);
        con.gridwidth = 1;
        con.gridy=4; right_panel.add(new Label("Liczba kolumn"), con);
        con.gridx=1; right_panel.add(new Label("Liczba wierszy"), con);
        con.gridx=0; con.gridy=5; right_panel.add(columns2, con);
        con.gridx=1; right_panel.add(rows2, con);
        con.gridy=6; right_panel.add(button, con);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JTextField[] array = {rows1, columns1, rows2, columns2};
            for (JTextField textField : array) {
                if(!validateTextField(textField))
                    return;
            }
            switchMode();
            if(!validateCalculation())
                return;
            UserInputWindow window = createInputWindow();
            switchWindow(window);
        }
    }

    public static void main (String[] args) {
        MainWindow window = new MainWindow();
        window.setVisible(true);
    }
}