package edu.wctc;

import java.math.BigInteger;

public class KeyPair {

    private Key publicKey;
    private Key privateKey;

    public KeyPair(int a, int b, int aPrime, int bPrime) {
        this(BigInteger.valueOf(a),
                BigInteger.valueOf(b),
                BigInteger.valueOf(aPrime),
                BigInteger.valueOf(bPrime));
    }

    public KeyPair(BigInteger a, BigInteger b, BigInteger aPrime, BigInteger bPrime) {
        BigInteger M = a.multiply(b).subtract(BigInteger.ONE);
        BigInteger e = aPrime.multiply(M).add(a);
        BigInteger d = bPrime.multiply(M).add(b);
        BigInteger n = e.multiply(d).subtract(BigInteger.ONE).divide(M);

        publicKey = new Key(e, n);
        privateKey = new Key(d, n);
    }

    public String decrypt(BigInteger cipherText) {
        return privateKey.decrypt(cipherText);
    }

    public Key getPrivateKey() {
        return privateKey;
    }

    public Key getPublicKey() {
        return publicKey;
    }
}
