package edu.wctc;

import java.math.BigInteger;

public class Main {

    /*
    KidRSA https://macs358.org/chapters/11/1/kidrsa.html
     */
    public static void main(String[] args) {
        int a = 200587;
        int b = 222659;
        int aPrime = 286547;
        int bPrime = 296971;

        KeyPair alice = new KeyPair(a, b, aPrime, bPrime);
        KeyPair bob = new KeyPair(b, a, bPrime, aPrime);

        String message = "x";

        System.out.println("Alice to Bob");
        System.out.println("-------------------------------------------");
        BigInteger encryptedToBob = bob.getPublicKey().encrypt(message);
        System.out.printf("Ciphertext of %s: %s%n%n", message, encryptedToBob);

        String messageFromAlice = bob.decrypt(encryptedToBob);
        System.out.printf("Plaintext of %s: %s%n%n", message, messageFromAlice);

        message = "Hello worl";

        System.out.println("Bob to Alice");
        System.out.println("-------------------------------------------");

        BigInteger encryptedToAlice = alice.getPublicKey().encrypt(message);
        System.out.printf("Ciphertext of %s: %s%n%n", message, encryptedToAlice);

        String messageFromBob = alice.decrypt(encryptedToAlice);
        System.out.printf("Plaintext of %s: %s%n", message, messageFromBob);

        message = "Hello world";


    }

}
