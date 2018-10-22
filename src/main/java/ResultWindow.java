import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ResultWindow extends JFrame{

    private MainWindow parent_window;
    private int columns, rows;
    private Matrix result;
    private JButton button;

    ResultWindow (MainWindow parent_window, Matrix result){
        this.parent_window = parent_window;
        this.result = result;
        columns = result.getColumns();
        rows = result.getRows();
        initiateWindow();
        createGui();
    }

    private void initiateWindow() {
        setTitle("Kalkulator macierzy");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int width = 50 + 40*columns;
        int height = 75 + 40*rows;
        setBounds(300, 100, width, height);
        button = new JButton("Powrot");
        button.addActionListener(new ButtonListener());
    }

    private void createGui() {
        setLayout(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();
        con.fill=GridBagConstraints.HORIZONTAL;
        con.weightx=0.5; con.weighty = 0.9;
        MatrixPanel panel = new MatrixPanel(columns, rows, "Wynik");
        writeResult(panel);
        con.gridx=0; con.gridy=0; add(panel,con);
        con.weighty = 0.1;
        con.gridx=0; con.gridy=1; add(button, con);
    }

    private void writeResult(MatrixPanel panel) {
        for (int i = 0; i<columns; i++) {
            for (int j = 0; j<rows; j++) {
                panel.setFieldValue(i, j, result.getValue(i,j));
            }
        }
        panel.disableEditing();
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            switchWindow(parent_window);
        }
    }

    private void switchWindow(JFrame window) {
        setVisible(false);
        window.setVisible(true);
    }

}