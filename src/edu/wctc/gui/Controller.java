package edu.wctc.gui;

import edu.wctc.Key;
import edu.wctc.KeyPair;
import edu.wctc.exception.KeyFormatException;
import edu.wctc.exception.KeySizeException;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Enumeration;
import java.util.Scanner;

public class Controller {
    private MainWindow mainWindow;
    private MessagePanel pnlAliceMessage, pnlBobMessage;
    private KeyPairPanel pnlAliceKeys, pnlBobKeys;
    private GenKeysPanel pnlAliceGen, pnlBobGen;

    public Controller() {
        setUIFont(new javax.swing.plaf.FontUIResource("Sans", Font.PLAIN, 18));

        mainWindow = new MainWindow(this);

        pnlAliceGen = new GenKeysPanel(this, "Alice");
        pnlBobGen = new GenKeysPanel(this, "Bob");
        pnlAliceKeys = new KeyPairPanel(this, "Alice");
        pnlBobKeys = new KeyPairPanel(this, "Bob");

        JPanel pnlBothAlice = new JPanel(new GridLayout(2, 1, 0, 20));
        pnlBothAlice.add(pnlAliceGen);
        pnlBothAlice.add(pnlAliceKeys);
        mainWindow.addTab("Alice's Keys", pnlBothAlice);

        JPanel pnlBothBob = new JPanel(new GridLayout(2, 1, 0, 20));
        pnlBothBob.add(pnlBobGen);
        pnlBothBob.add(pnlBobKeys);
        mainWindow.addTab("Bob's Keys", pnlBothBob);

        pnlAliceMessage = new MessagePanel(this, "Alice", "Bob");
        mainWindow.addTab("Alice " + "\u2192" + " Bob", pnlAliceMessage);
        pnlBobMessage = new MessagePanel(this, "Bob", "Alice");
        mainWindow.addTab("Bob " + "\u2192" + " Alice", pnlBobMessage);

        pnlAliceGen.setA(200587);
        pnlAliceGen.setB(222659);
        pnlAliceGen.setAPrime(286547);
        pnlAliceGen.setBPrime(296971);

        pnlBobGen.setA(286547);
        pnlBobGen.setB(296971);
        pnlBobGen.setAPrime(200587);
        pnlBobGen.setBPrime(222659);

        mainWindow.setVisible(true);
    }

    public static void setUIFont(javax.swing.plaf.FontUIResource f) {
        Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put(key, f);
        }
    }

    public void decryptMessage(String from) {
        boolean aliceSender = from.equals("Alice");
        MessagePanel messagePanel = aliceSender ? pnlAliceMessage : pnlBobMessage;
        KeyPairPanel keyPairPanel = aliceSender ? pnlBobKeys : pnlAliceKeys;

        BigInteger cipherText = new BigInteger(messagePanel.getCiphertext());

        try {
            Key privateKey = Key.parse(keyPairPanel.getPrivateKey());
            String plainText = privateKey.decrypt(cipherText);
            messagePanel.setPlaintext(plainText);
        } catch (KeyFormatException e) {
            messagePanel.setPlaintext("");
            JOptionPane.showMessageDialog(mainWindow, e.getMessage());
        }
    }

    public void encryptMessage(String from) {
        boolean aliceSender = from.equals("Alice");
        MessagePanel messagePanel = aliceSender ? pnlAliceMessage : pnlBobMessage;
        KeyPairPanel keyPairPanel = aliceSender ? pnlBobKeys : pnlAliceKeys;

        try {
            Key publicKey = Key.parse(keyPairPanel.getPublicKey());
            String message = messagePanel.getOriginalMessage();

            BigInteger cipherText = publicKey.encrypt(message);
            messagePanel.setCiphertext(cipherText.toString());
        } catch (KeyFormatException | KeySizeException e) {
            messagePanel.setCiphertext("");
            JOptionPane.showMessageDialog(mainWindow, e.getMessage());
        }
    }

    public void generateKeys(String keyOwner) {
        boolean aliceOwner = keyOwner.equals("Alice");

        GenKeysPanel genKeysPanel = aliceOwner ? pnlAliceGen : pnlBobGen;

        int a = Integer.parseInt(genKeysPanel.getA());
        int aPrime = Integer.parseInt(genKeysPanel.getAPrime());
        int b = Integer.parseInt(genKeysPanel.getB());
        int bPrime = Integer.parseInt(genKeysPanel.getBPrime());

        KeyPair keyPair = new KeyPair(a, b, aPrime, bPrime);

        KeyPairPanel keyPairPanel = aliceOwner ? pnlAliceKeys : pnlBobKeys;
        keyPairPanel.setPublicKey(keyPair.getPublicKey());
        keyPairPanel.setPrivateKey(keyPair.getPrivateKey());
    }

    public void loadKeysFromFile(String keyOwner) {
        KeyPairPanel keyPairPanel = keyOwner.equals("Alice") ? pnlAliceKeys : pnlBobKeys;

        try (Scanner input = new Scanner(new File(keyOwner + ".txt"))) {
            input.nextLine();
            keyPairPanel.setPublicKey(Key.parse(input.nextLine()));
            input.nextLine();
            keyPairPanel.setPrivateKey(Key.parse(input.nextLine()));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(mainWindow, "Could not write keys to file: " + keyOwner + ".txt");
        } catch (KeyFormatException e) {
            JOptionPane.showMessageDialog(mainWindow, e.getMessage());
        }
    }

    public void saveKeysToFile(String keyOwner) {
        KeyPairPanel keyPairPanel = keyOwner.equals("Alice") ? pnlAliceKeys : pnlBobKeys;

        try (PrintWriter pw = new PrintWriter(keyOwner + ".txt")) {
            pw.println("Public key:");
            pw.println(keyPairPanel.getPublicKey());
            pw.println("Private key:");
            pw.println(keyPairPanel.getPrivateKey());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(mainWindow, "Could not write keys to file: " + keyOwner + ".txt");
        }
    }
}
