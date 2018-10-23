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
        JTextField textField;
        for(int i=0; i<columns; i++) {
            for(int j=0; j<rows; j++) {
                con.gridx=i; con.gridy=j+1;
                textField = new JTextField("0");
                matrixFields[i][j] = textField;
                add(textField, con);
            }
        }
    }

    private boolean validateTextField(int x, int y) {
        try {
            float f = Float.parseFloat(matrixFields[x][y].getText());
            return true;
        }
        catch(NumberFormatException e) {
            matrixFields[x][y].setText("Blad");
            return false;
        }
    }

    public boolean validatePanel() {
        for (int i=0; i<columns; i++) {
            for (int j=0; j<rows; j++) {
                if(!validateTextField(i,j))
                    return false;
            }
        }
        return true;
    }

    public float getFieldValue(int x, int y) {
        return Float.parseFloat(matrixFields[x][y].getText());
    }

    public void setFieldValue(int x, int y, float value) {
        matrixFields[x][y].setText(String.valueOf(value));
    }

    public void disableEditing() {
        for (int i = 0; i<columns; i++) {
            for (int j = 0; j<rows; j++) {
                matrixFields[i][j].setEditable(false);
            }
        }
    }
}