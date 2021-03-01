package edu.wctc;

import edu.wctc.exception.KeyFormatException;
import edu.wctc.exception.KeySizeException;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class Key {
    private BigInteger num1, num2;

    public Key(BigInteger num1, BigInteger num2) {
        this.num1 = num1; // e in public, d in private
        this.num2 = num2; // n in both public and private
    }

    public static Key parse(String formattedKey) throws KeyFormatException {
        formattedKey = formattedKey.replaceAll("[^0-9,]", "");
        String[] halves = formattedKey.split(",");
        if (halves.length == 2) {
            try {
                return new Key(new BigInteger(halves[0]), new BigInteger(halves[1]));
            } catch (NumberFormatException e) {
                throw new KeyFormatException(formattedKey, "Numbers cannot be parsed.");
            }
        } else {
            throw new KeyFormatException(formattedKey, "Does not contain two numbers.");
        }
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

        System.out.print("Decrypt: Processing byte chunks to characters... ");
        // Grab chunks of 8 bits and convert to an ASCII character
        for (int i = 0; i < bits.length(); i += 8) {
            String byteChunk = bits.substring(i, i + 8);
            int decimal = Integer.parseInt(byteChunk, 2);
            String character = Character.toString(decimal);
            System.out.print(character + " ");
            message += character;
        }
        System.out.println();

        return message;
    }

    public BigInteger e() {
        return num1;
    }

    /*
    Encryption is performed with the public key.
     */
    public BigInteger encrypt(String message) throws KeySizeException {
        byte[] bytes = message.getBytes(StandardCharsets.US_ASCII);
        StringBuilder binary = new StringBuilder();

        System.out.print("Encrypt: Processing characters to byte chunks...");
        for (byte b : bytes) {
            System.out.print(b + " ");
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }
        System.out.println();

        System.out.printf("Encrypt: binary %s%n", binary.toString());
        BigInteger m = new BigInteger(binary.toString(), 2);

        if (m.compareTo(n()) > 0) {
            throw new KeySizeException(m, n());
        } else {
            BigInteger result = m.multiply(e()).mod(n());
            System.out.printf("Encrypt: %d * %d (%d) %% %d = %d%n", m, e(), m.multiply(e()), n(), result);

            return result;
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
