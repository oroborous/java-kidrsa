package edu.wctc;

import java.math.BigInteger;

public class KeyPair {

    private Key publicKey;
    private Key privateKey;

    public KeyPair(int a, int b, int aPrime, int bPrime) {
        BigInteger bigA = BigInteger.valueOf(a);
        BigInteger bigB = BigInteger.valueOf(b);
        BigInteger M = bigA.multiply(bigB).subtract(BigInteger.ONE);
        BigInteger e = BigInteger.valueOf(aPrime).multiply(M).add(bigA);
        BigInteger d = BigInteger.valueOf(bPrime).multiply(M).add(bigB);
        BigInteger n = e.multiply(d).subtract(BigInteger.ONE).divide(M);

        publicKey = new Key(e, n);
        privateKey = new Key(d, n);
    }

    public Key getPublicKey() {
        return publicKey;
    }

    public String decrypt(BigInteger cipherText) {
        return privateKey.decrypt(cipherText);
    }
}
