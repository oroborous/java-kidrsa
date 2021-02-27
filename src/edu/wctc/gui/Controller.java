package edu.wctc.gui;

import edu.wctc.Key;
import edu.wctc.KeyPair;

import java.math.BigInteger;

public class Controller {
    private MainWindow mainWindow;

    public Controller() {
        mainWindow = new MainWindow(this);

        mainWindow.setAliceA(200587);
        mainWindow.setAliceB(222659);
        mainWindow.setAliceAPrime(286547);
        mainWindow.setAliceBPrime(296971);

        mainWindow.setBobA(286547);
        mainWindow.setBobB(296971);
        mainWindow.setBobAPrime(200587);
        mainWindow.setBobBPrime(222659);

        mainWindow.setVisible(true);
    }

    public void generateKeysForAlice() {
        int a = Integer.parseInt(mainWindow.getAliceA());
        int aPrime = Integer.parseInt(mainWindow.getAliceAPrime());
        int b = Integer.parseInt(mainWindow.getAliceB());
        int bPrime = Integer.parseInt(mainWindow.getAliceBPrime());

        KeyPair keyPair = new KeyPair(a, b, aPrime, bPrime);

        mainWindow.setAlicePublicKey(keyPair.getPublicKey());
        mainWindow.setAlicePrivateKey(keyPair.getPrivateKey());
    }

    public void generateKeysForBob() {
        int a = Integer.parseInt(mainWindow.getBobA());
        int aPrime = Integer.parseInt(mainWindow.getBobAPrime());
        int b = Integer.parseInt(mainWindow.getBobB());
        int bPrime = Integer.parseInt(mainWindow.getBobBPrime());

        KeyPair keyPair = new KeyPair(a, b, aPrime, bPrime);

        mainWindow.setBobPublicKey(keyPair.getPublicKey());
        mainWindow.setBobPrivateKey(keyPair.getPrivateKey());
    }

    public void sendMessageFromAliceToBob() {
        Key bobPublic = mainWindow.getBobPublicKey();

        String message = mainWindow.getMessageForBob();
        
        BigInteger cipherText = bobPublic.encrypt(message);
        
        mainWindow.setCiphertextFromAlice(cipherText.toString());
        
        Key bobPrivate = mainWindow.getBobPrivateKey();
        
        String plainText = bobPrivate.decrypt(cipherText);
        
        mainWindow.setPlaintextFromAlice(plainText);

    }

    public void sendMessageFromBobToAlice() {
        Key alicePublic = mainWindow.getAlicePublicKey();

        String message = mainWindow.getMessageForAlice();

        BigInteger cipherText = alicePublic.encrypt(message);

        mainWindow.setCiphertextFromBob(cipherText.toString());

        Key alicePrivate = mainWindow.getAlicePrivateKey();

        String plainText = alicePrivate.decrypt(cipherText);

        mainWindow.setPlaintextFromBob(plainText);
    }
}
