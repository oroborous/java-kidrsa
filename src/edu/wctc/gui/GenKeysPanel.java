package edu.wctc.gui;

import javax.swing.*;
import java.awt.*;

public class GenKeysPanel extends JPanel {
    private JTextField txtA = new JTextField(),
            txtB = new JTextField(),
            txtAPrime = new JTextField(),
            txtBPrime = new JTextField();
    private JButton btnGenKeys = new JButton();
    private Controller controller;
    private String keyOwner;

    public GenKeysPanel(Controller controller, String keyOwner) {
        this.controller = controller;
        this.keyOwner = keyOwner;

        init();
        addActionListeners();
    }

    private void init() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        btnGenKeys.setText("Generate Key Pair for " + keyOwner);

        int row = 0;
        add(new JLabel("Hello, " + keyOwner + "! Enter four prime numbers below to generate your key pair."), new GridBagConstraints(0, row++, 4, 1, 1, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        add(new JLabel("A"), new GridBagConstraints(0, row, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(20, 0, 0, 0), 0, 0));
        add(txtA, new GridBagConstraints(1, row, 1, 1, 0.5, 0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(20, 5, 0, 0), 0, 0));
        add(new JLabel("A'"), new GridBagConstraints(2, row, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(20, 30, 0, 0), 0, 0));
        add(txtAPrime, new GridBagConstraints(3, row++, 1, 1, 0.5, 0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(20, 5, 0, 0), 0, 0));

        add(new JLabel("B"), new GridBagConstraints(0, row, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(20, 0, 0, 0), 0, 0));
        add(txtB, new GridBagConstraints(1, row, 1, 1, 0.5, 0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(20, 5, 0, 0), 0, 0));
        add(new JLabel("B'"), new GridBagConstraints(2, row, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(20, 30, 0, 0), 0, 0));
        add(txtBPrime, new GridBagConstraints(3, row++, 1, 1, 0.5, 0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(20, 5, 0, 0), 0, 0));

        add(btnGenKeys, new GridBagConstraints(0, row++, 4, 1, 1, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));

        add(new JPanel(), new GridBagConstraints(0, row++, 4, 1, 1, 1,
                GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    }

    private void addActionListeners() {
        btnGenKeys.addActionListener(e -> controller.generateKeys(keyOwner));
    }


    public String getA() {
        return txtA.getText();
    }

    public void setA(int val) {
        txtA.setText(Integer.toString(val));
    }

    public String getAPrime() {
        return txtAPrime.getText();
    }

    public void setAPrime(int val) {
        txtAPrime.setText(Integer.toString(val));
    }

    public String getB() {
        return txtB.getText();
    }

    public void setB(int val) {
        txtB.setText(Integer.toString(val));
    }

    public String getBPrime() {
        return txtBPrime.getText();
    }

    public void setBPrime(int val) {
        txtBPrime.setText(Integer.toString(val));
    }
}
