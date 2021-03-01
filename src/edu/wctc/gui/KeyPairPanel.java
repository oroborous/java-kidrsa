package edu.wctc.gui;

import edu.wctc.Key;

import javax.swing.*;
import java.awt.*;

public class KeyPairPanel extends JPanel {
    private JTextField txtPrivateKey = new JTextField(),
            txtPublicKey = new JTextField();
    private JButton btnSaveToFile = new JButton();
    private JButton btnLoadFromFile = new JButton();
    private Controller controller;
    private String keyOwner;

    public KeyPairPanel(Controller controller, String keyOwner) {
        this.controller = controller;
        this.keyOwner = keyOwner;

        init();
        addActionListeners();
    }

    private void addActionListeners() {
        btnSaveToFile.addActionListener(e -> controller.saveKeysToFile(keyOwner));
        btnLoadFromFile.addActionListener(e -> controller.loadKeysFromFile(keyOwner));
    }

    public String getPrivateKey() {
        return txtPrivateKey.getText();
    }

    public void setPrivateKey(Key key) {
        txtPrivateKey.setText(key.toString());
    }

    public String getPublicKey() {
        return txtPublicKey.getText();
    }

    public void setPublicKey(Key key) {
        txtPublicKey.setText(key.toString());
    }

    private void init() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        txtPrivateKey.setForeground(Color.RED);

        btnSaveToFile.setText("Save " + keyOwner + "'s Keys to File");
        btnLoadFromFile.setText("Load " + keyOwner + "'s Keys from File");

        int row = 0;
        add(new JLabel(keyOwner + "'s private key is:"), new GridBagConstraints(0, row++, 2, 1, 1, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
        add(txtPrivateKey, new GridBagConstraints(0, row++, 2, 1, 1, 0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 0, 0), 0, 0));

        add(new JLabel(keyOwner + "'s public key is:"), new GridBagConstraints(0, row++, 2, 1, 1, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
        add(txtPublicKey, new GridBagConstraints(0, row++, 2, 1, 1, 0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 0, 0), 0, 0));

        add(btnSaveToFile, new GridBagConstraints(0, row, 1, 1, 1, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 5), 0, 0));
        add(btnLoadFromFile, new GridBagConstraints(1, row++, 1, 1, 1, 0,
                GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(10, 5, 0, 0), 0, 0));

        add(new JPanel(), new GridBagConstraints(0, row++, 2, 1, 1, 1,
                GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    }
}
