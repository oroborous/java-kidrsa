package edu.wctc.gui;

import edu.wctc.Key;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private JTextField txtPlainFromAlice = new JTextField(),
            txtCipherFromAlice = new JTextField(),
            txtPlainFromBob = new JTextField(),
            txtCipherFromBob = new JTextField();
    private JTextField txtAliceA = new JTextField(),
            txtAliceB = new JTextField(),
            txtAliceAPrime = new JTextField(),
            txtAliceBPrime = new JTextField(),
            txtBobA = new JTextField(),
            txtBobB = new JTextField(),
            txtBobAPrime = new JTextField(),
            txtBobBPrime = new JTextField();
    private JTextField txtPrivateKeyBob = new JTextField(),
            txtPublicKeyBob = new JTextField(),
            txtPrivateKeyAlice = new JTextField(),
            txtPublicKeyAlice = new JTextField();
    private JTextField txtMessageForBob = new JTextField(),
            txtMessageForAlice = new JTextField();
    private JButton btnSendToBob = new JButton("\u2193" + " Send to Bob " + "\u2193"),
            btnSendToAlice = new JButton("\u2191" + " Send to Alice " + "\u2191");
    private JButton btnGenerateBobKeys = new JButton("Generate Bob's Key Pair"),
            btnGenerateAliceKeys = new JButton("Generate Alice's Key Pair");

    private Controller controller;


    public MainWindow(Controller controller) {
        super("KidRSA");
        this.controller = controller;

        init();
        addActionListeners();

        setPreferredSize(new Dimension(800, 600));
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addActionListeners() {
        btnGenerateAliceKeys.addActionListener(e -> controller.generateKeysForAlice());
        btnGenerateBobKeys.addActionListener(e -> controller.generateKeysForBob());
        btnSendToAlice.addActionListener(e -> controller.sendMessageFromBobToAlice());
        btnSendToBob.addActionListener(e -> controller.sendMessageFromAliceToBob());
    }

    public String getAliceA() {
        return txtAliceA.getText();
    }

    public String getMessageForBob() {
        return txtMessageForBob.getText();
    }

    public String getMessageForAlice() {
        return txtMessageForAlice.getText();
    }

    public void setAliceA(int val) {
        txtAliceA.setText(Integer.toString(val));
    }

    public String getAliceAPrime() {
        return txtAliceAPrime.getText();
    }

    public void setAliceAPrime(int val) {
        txtAliceAPrime.setText(Integer.toString(val));
    }

    public String getAliceB() {
        return txtAliceB.getText();
    }

    public void setAliceB(int val) {
        txtAliceB.setText(Integer.toString(val));
    }

    public String getAliceBPrime() {
        return txtAliceBPrime.getText();
    }

    public void setAliceBPrime(int val) {
        txtAliceBPrime.setText(Integer.toString(val));
    }

    public Key getAlicePrivateKey() {
        return Key.parse(txtPrivateKeyAlice.getText());
    }

    public void setAlicePrivateKey(Key key) {
        txtPrivateKeyAlice.setText(key.toString());
    }

    public Key getAlicePublicKey() {
        return Key.parse(txtPublicKeyAlice.getText());
    }

    public void setAlicePublicKey(Key key) {
        txtPublicKeyAlice.setText(key.toString());
    }

    public String getBobA() {
        return txtBobA.getText();
    }

    public void setBobA(int val) {
        txtBobA.setText(Integer.toString(val));
    }

    public String getBobAPrime() {
        return txtBobAPrime.getText();
    }

    public void setBobAPrime(int val) {
        txtBobAPrime.setText(Integer.toString(val));
    }

    public String getBobB() {
        return txtBobB.getText();
    }

    public void setBobB(int val) {
        txtBobB.setText(Integer.toString(val));
    }

    public String getBobBPrime() {
        return txtBobBPrime.getText();
    }

    public void setBobBPrime(int val) {
        txtBobBPrime.setText(Integer.toString(val));
    }

    public Key getBobPrivateKey() {
        return Key.parse(txtPrivateKeyBob.getText());
    }

    public void setBobPrivateKey(Key key) {
        txtPrivateKeyBob.setText(key.toString());
    }

    public Key getBobPublicKey() {
        return Key.parse(txtPublicKeyBob.getText());
    }

    public void setBobPublicKey(Key key) {
        txtPublicKeyBob.setText(key.toString());
    }

    private void init() {
        JPanel pnlMain = new JPanel(new GridBagLayout());
        pnlMain.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        getContentPane().add(pnlMain, BorderLayout.CENTER);


        int row = 0;
        pnlMain.add(makeLabelledTextPanel("Alice A", txtAliceA),
                new GridBagConstraints(0, row, 1, 1, 1, 0, GridBagConstraints.WEST,
                        GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        pnlMain.add(makeLabelledTextPanel("Alice A'", txtAliceAPrime),
                new GridBagConstraints(1, row++, 1, 1, 1, 0, GridBagConstraints.WEST,
                        GridBagConstraints.HORIZONTAL, new Insets(0, 10, 0, 0), 0, 0));
        pnlMain.add(makeLabelledTextPanel("Alice B", txtAliceB),
                new GridBagConstraints(0, row, 1, 1, 1, 0, GridBagConstraints.WEST,
                        GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        pnlMain.add(makeLabelledTextPanel("Alice B'", txtAliceBPrime),
                new GridBagConstraints(1, row++, 1, 1, 1, 0, GridBagConstraints.WEST,
                        GridBagConstraints.HORIZONTAL, new Insets(0, 10, 0, 0), 0, 0));

        pnlMain.add(btnGenerateAliceKeys,
                new GridBagConstraints(0, row++, 2, 1, 1, 0, GridBagConstraints.CENTER,
                        GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));

        pnlMain.add(makeLabelledTextPanel("Alice's Public Key", txtPublicKeyAlice),
                new GridBagConstraints(0, row, 1, 1, 1, 0, GridBagConstraints.WEST,
                        GridBagConstraints.HORIZONTAL, new Insets(10, 0, 0, 0), 0, 0));
        pnlMain.add(makeLabelledTextPanel("Alice's Private Key", txtPrivateKeyAlice),
                new GridBagConstraints(1, row++, 1, 1, 1, 0, GridBagConstraints.WEST,
                        GridBagConstraints.HORIZONTAL, new Insets(10, 10, 0, 0), 0, 0));

        pnlMain.add(makeLabelledTextPanel("Message for Bob", txtMessageForBob),
                new GridBagConstraints(0, row++, 2, 1, 1, 0, GridBagConstraints.CENTER,
                        GridBagConstraints.HORIZONTAL, new Insets(10, 0, 0, 0), 0, 0));
        pnlMain.add(btnSendToBob,
                new GridBagConstraints(0, row++, 2, 1, 1, 0, GridBagConstraints.CENTER,
                        GridBagConstraints.HORIZONTAL, new Insets(10, 0, 0, 0), 0, 0));

        pnlMain.add(makeLabelledTextPanel("Ciphertext Message from Bob", txtCipherFromBob),
                new GridBagConstraints(0, row++, 2, 1, 1, 0, GridBagConstraints.WEST,
                        GridBagConstraints.HORIZONTAL, new Insets(20, 0, 0, 0), 0, 0));
        pnlMain.add(makeLabelledTextPanel("Plaintext Message from Bob", txtPlainFromBob),
                new GridBagConstraints(0, row++, 2, 1, 1, 0, GridBagConstraints.WEST,
                        GridBagConstraints.HORIZONTAL, new Insets(10, 0, 0, 0), 0, 0));


        pnlMain.add(new JPanel(),
                new GridBagConstraints(0, row++, 2, 1, 1, 1, GridBagConstraints.CENTER,
                        GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        pnlMain.add(makeLabelledTextPanel("Ciphertext Message from Alice", txtCipherFromAlice),
                new GridBagConstraints(0, row++, 2, 1, 1, 0, GridBagConstraints.WEST,
                        GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        pnlMain.add(makeLabelledTextPanel("Plaintext Message from Alice", txtPlainFromAlice),
                new GridBagConstraints(0, row++, 2, 1, 1, 0, GridBagConstraints.WEST,
                        GridBagConstraints.HORIZONTAL, new Insets(10, 0, 0, 0), 0, 0));

        pnlMain.add(btnSendToAlice,
                new GridBagConstraints(0, row++, 2, 1, 1, 0, GridBagConstraints.CENTER,
                        GridBagConstraints.HORIZONTAL, new Insets(20, 0, 0, 0), 0, 0));

        pnlMain.add(makeLabelledTextPanel("Bob A", txtBobA),
                new GridBagConstraints(0, row, 1, 1, 1, 0, GridBagConstraints.WEST,
                        GridBagConstraints.HORIZONTAL, new Insets(10, 0, 0, 0), 0, 0));
        pnlMain.add(makeLabelledTextPanel("Bob A'", txtBobAPrime),
                new GridBagConstraints(1, row++, 1, 1, 1, 0, GridBagConstraints.WEST,
                        GridBagConstraints.HORIZONTAL, new Insets(10, 10, 0, 0), 0, 0));
        pnlMain.add(makeLabelledTextPanel("Bob B", txtBobB),
                new GridBagConstraints(0, row, 1, 1, 1, 0, GridBagConstraints.WEST,
                        GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        pnlMain.add(makeLabelledTextPanel("Bob B'", txtBobBPrime),
                new GridBagConstraints(1, row++, 1, 1, 1, 0, GridBagConstraints.WEST,
                        GridBagConstraints.HORIZONTAL, new Insets(0, 10, 0, 0), 0, 0));

        pnlMain.add(btnGenerateBobKeys,
                new GridBagConstraints(0, row++, 2, 1, 1, 0, GridBagConstraints.CENTER,
                        GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));

        pnlMain.add(makeLabelledTextPanel("Bob's Public Key", txtPublicKeyBob),
                new GridBagConstraints(0, row, 1, 1, 1, 0, GridBagConstraints.WEST,
                        GridBagConstraints.HORIZONTAL, new Insets(10, 0, 0, 0), 0, 0));
        pnlMain.add(makeLabelledTextPanel("Bob's Private Key", txtPrivateKeyBob),
                new GridBagConstraints(1, row++, 1, 1, 1, 0, GridBagConstraints.WEST,
                        GridBagConstraints.HORIZONTAL, new Insets(10, 10, 0, 0), 0, 0));

    }

    private JPanel makeLabelledTextPanel(String label, JTextField txtField) {
        JPanel pnl = new JPanel(new BorderLayout(5, 0));
        pnl.add(new JLabel(label), BorderLayout.WEST);
        pnl.add(txtField, BorderLayout.CENTER);
        return pnl;
    }

    public void setCiphertextFromAlice(String text) {
        txtCipherFromAlice.setText(text);
    }

    public void setCiphertextFromBob(String text) {
        txtCipherFromBob.setText(text);
    }

    public void setPlaintextFromAlice(String text) {
        txtPlainFromAlice.setText(text);
    }

    public void setPlaintextFromBob(String text) {
        txtPlainFromBob.setText(text);
    }

}
