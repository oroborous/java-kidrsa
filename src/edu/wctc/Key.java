package edu.wctc;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class Key {
    private BigInteger num1, num2;

    public Key(BigInteger num1, BigInteger num2) {
        this.num1 = num1; // e in public, d in private
        this.num2 = num2; // n in both public and private
    }

    public static Key parse(String formattedKey) {
        formattedKey = formattedKey.replaceAll("[^0-9,]", "");
        String[] halves = formattedKey.split(",");
        return new Key(new BigInteger(halves[0]), new BigInteger(halves[1]));
    }

    public BigInteger d() {
        return num1;
    }

    public String decrypt(BigInteger cipherText) {
        String message = "";

        BigInteger decryptedNum = cipherText.multiply(d()).mod(n());
        System.out.printf("Decrypt: %d * %d (%d) %% %d = %d%n", cipherText, d(), cipherText.multiply(d()), n(), decryptedNum);

        String bits = decryptedNum.toString(2);
        System.out.printf("Decrypt: binary %s%n", bits);

        while (bits.length() % 8 != 0) {
            bits = "0" + bits; // left pad with zeroes to ensure groups of 8
        }

        // Grab chunks of 8 bits and convert to an ASCII character
        for (int i = 0; i < bits.length(); i += 8) {
            String byteChunk = bits.substring(i, i + 8);
            int decimal = Integer.parseInt(byteChunk, 2);
            message += Character.toString(decimal);
        }
        return message;
    }

    public BigInteger e() {
        return num1;
    }

    /*
    Encryption is performed with the public key.
     */
    public BigInteger encrypt(String message) {
        try {
            byte[] bytes = message.getBytes("US-ASCII");
            StringBuilder binary = new StringBuilder();
            for (byte b : bytes) {
                int val = b;
                for (int i = 0; i < 8; i++) {
                    binary.append((val & 128) == 0 ? 0 : 1);
                    val <<= 1;
                }
            }
            System.out.printf("Encrypt: binary %s%n", binary.toString());
            BigInteger m = new BigInteger(binary.toString(), 2);

            if (m.compareTo(n()) > 0) {
                System.out.printf("WARNING: Message (%d) is greater than n (%d). Cannot proceed.%n", m, n());
                return BigInteger.ZERO;
            } else {
                BigInteger result = m.multiply(e()).mod(n());
                System.out.printf("Encrypt: %d * %d (%d) %% %d = %d%n", m, e(), m.multiply(e()), n(), result);

                return result;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return BigInteger.ZERO;
        }
    }

    public BigInteger n() {
        return num2;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", num1, num2);
    }
}
