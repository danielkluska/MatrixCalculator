import javax.swing.*;
import java.awt.*;

class MatrixPanel extends JPanel {

    private int columns, rows;
    private String title;
    private JTextField[][] matrixFields;

    MatrixPanel(int columns, int rows, String title) {
        this.columns = columns;
        this.rows = rows;
        this.title = title;
        matrixFields = new JTextField[columns][rows];
        createElements();
    }

    private void createElements() {
        setLayout(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();
        con.fill = GridBagConstraints.HORIZONTAL;
        con.weightx = 0.5; con.weighty=0.5;
        con.gridheight = 1; con.gridwidth = columns;
        con.gridx=0; con.gridy=0; add(new JLabel(title), con);
        con.gridwidth = 1;
        for(int i=0; i<columns; i++) {
            for(int j=0; j<rows; j++) {
                con.gridx=i; con.gridy=j+1;
                JTextField textField = new JTextField("0");
                matrixFields[i][j] = textField;
                add(textField, con);
            }
        }
    }
}