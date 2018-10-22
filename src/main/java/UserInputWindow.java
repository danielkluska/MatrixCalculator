import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class UserInputWindow extends JFrame {

    private MainWindow parent_window;
    private Mode mode;
    private int rows1, columns1, rows2, columns2;
    private JButton button;

    UserInputWindow(MainWindow parent_window, Mode mode, int rows1, int columns1, int rows2,  int columns2) {
        this.parent_window = parent_window;
        this.mode = mode;
        this.rows1 = rows1;
        this.columns1 = columns1;
        this.rows2 = rows2;
        this.columns2 = columns2;
        initiateWindow();
    }

    private void initiateWindow() {
        setTitle("Kalkulator macierzy");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button = new JButton("Dalej");
        button.addActionListener(new ButtonListener());
        if (mode == Mode.DETERMINANT) {
            createSinglePanel(mode);
        }
        else {
            createDoublePanel(mode);
        }
    }

    private void createSinglePanel(Mode mode) {
        int width = 50 + 40*rows1;
        int height = 75 + 40*rows1;
        String name = getModeName(mode);
        setBounds(300, 100, width, height);
        MatrixPanel panel = new MatrixPanel(columns1, rows1, "Macierz");
        setLayout(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();
        con.fill=GridBagConstraints.HORIZONTAL;
        con.weightx =0.5; con.weighty = 0.2;
        con.gridx=0; con.gridy=0; add(new JLabel(name), con);
        con.weighty=0.7;
        con.gridx=0; con.gridy=1; add(panel, con);
        con.weighty = 0.1;
        con.gridx=0; con.gridy=2; add(button, con);
    }

    private void createDoublePanel(Mode mode) {
        int max_row = Math.max(rows1, rows2);
        int max_column = Math.max(columns1, columns2);
        int width = 50 + (50 + 40 * max_column) * 2;
        int height = 75 + (50 + 40 * max_row) * 2;
        String name = getModeName(mode);
        setBounds(300,100, width, height);
        MatrixPanel panel1 = new MatrixPanel(columns1, rows1, "Macierz 1");
        MatrixPanel panel2 = new MatrixPanel(columns2, rows2, "Macierz 2");
        setLayout(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();
        con.fill=GridBagConstraints.HORIZONTAL;
        con.weightx =0.5; con.weighty = 0.2;
        con.gridx=0; con.gridy=0; add(new JLabel(name), con);
        con.weightx = 0.5; con.weighty = 0.7;
        con.gridx=0; con.gridy=1; add(panel1, con);
        con.gridx=1; con.gridy=1; add(panel2, con);
        con.gridx=1; con.gridy=2; add(button, con);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    private String getModeName(Mode mode) {
        switch (mode) {
            case ADDITION:
                return "Dodawanie";
            case MULTIPLICATION:
                return "Mnozenie";
            case DETERMINANT:
                return "Wyznacznik";
            default:
                return "";
        }
    }

}