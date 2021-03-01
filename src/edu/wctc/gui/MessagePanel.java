package edu.wctc.gui;

import javax.swing.*;
import java.awt.*;

public class MessagePanel extends JPanel {
    private JTextField txtPlaintext = new JTextField(),
            txtCiphertext = new JTextField(),
            txtOriginal = new JTextField();
    private JButton btnEncrypt = new JButton();
    private JButton btnDecrypt = new JButton();
    private String from, to;
    private Controller controller;

    public MessagePanel(Controller controller, String from, String to) {
        this.controller = controller;
        this.from = from;
        this.to = to;

        init();
        addActionListeners();
    }

    private void addActionListeners() {
        btnEncrypt.addActionListener(e -> controller.encryptMessage(from));
        btnDecrypt.addActionListener(e -> controller.decryptMessage(from));
    }

    public String getCiphertext() {
        return txtCiphertext.getText();
    }

    public void setCiphertext(String text) {
        txtCiphertext.setText(text);
    }

    public String getOriginalMessage() {
        return txtOriginal.getText();
    }

    private void init() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        btnEncrypt.setText("Encrypt Using " + to + "'s Public Key");
        btnDecrypt.setText("Decrypt Using " + to + "'s Private Key");

        int row = 0;
        add(new JLabel("Hello, " + from + "! Want to send a message to " + to + "?"), new GridBagConstraints(0, row++, 1, 1, 1, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        add(new JLabel("Type "+from+"'s message to " + to + " here:"), new GridBagConstraints(0, row++, 1, 1, 1, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(30, 0, 0, 0), 0, 0));
        add(txtOriginal, new GridBagConstraints(0, row++, 1, 1, 1, 0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 0, 0), 0, 0));
        add(new JLabel(from + " uses " + to + "'s public key to encrypt the message."), new GridBagConstraints(0, row++, 1, 1, 1, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
        add(btnEncrypt, new GridBagConstraints(0, row++, 1, 1, 1, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));

        add(new JLabel("The ciphertext from " + from + " to " + to + " is:"), new GridBagConstraints(0, row++, 1, 1, 1, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(45, 0, 0, 0), 0, 0));
        add(txtCiphertext, new GridBagConstraints(0, row++, 1, 1, 1, 0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 0, 0), 0, 0));
        add(new JLabel(to + " uses " + to + "'s private key to decrypt the message."), new GridBagConstraints(0, row++, 1, 1, 1, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
        add(btnDecrypt, new GridBagConstraints(0, row++, 1, 1, 1, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));

        add(new JLabel("The plaintext is:"), new GridBagConstraints(0, row++, 1, 1, 1, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(45, 0, 0, 0), 0, 0));
        add(txtPlaintext, new GridBagConstraints(0, row++, 1, 1, 1, 0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 0, 0), 0, 0));

        add(new JPanel(), new GridBagConstraints(0, row++, 1, 1, 1, 1,
                GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    }

    public void setPlaintext(String text) {
        txtPlaintext.setText(text);
    }

}
