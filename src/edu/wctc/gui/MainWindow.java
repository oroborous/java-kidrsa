package edu.wctc.gui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private JTabbedPane tabs = new JTabbedPane();

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

        setPreferredSize(new Dimension(800, 600));
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addTab(String label, JPanel tab) {
        tabs.addTab(label, tab);
    }

    private void init() {
        tabs.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        getContentPane().add(tabs, BorderLayout.CENTER);
    }


}
