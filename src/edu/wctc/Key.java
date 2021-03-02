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

        // Perform the decryption
        BigInteger decryptedNum = cipherText.multiply(d()).mod(n());
        System.out.printf("Decrypt: %d * %d (%d) %% %d = %d%n", cipherText, d(), cipherText.multiply(d()), n(), decryptedNum);

        // Turn the BigInteger into a string of 1's and 0's by specifying radix of base 2
        String bits = decryptedNum.toString(2);
        System.out.printf("Decrypt: binary %s%n", bits);

        // Ensure the string can be chopped even into groups of eight 1's and 0's
        while (bits.length() % 8 != 0) {
            bits = "0" + bits; // left pad with zeroes to ensure groups of 8
        }

        System.out.print("Decrypt: Processing byte chunks to characters... ");
        // Grab chunks of 8 bits and convert to an ASCII character
        for (int i = 0; i < bits.length(); i += 8) {
            // Eight 1's and 0's
            String byteChunk = bits.substring(i, i + 8);
            // Parse the string using radix base 2
            int decimal = Integer.parseInt(byteChunk, 2);
            // Convert to a character
            String character = Character.toString(decimal);
            System.out.print(character + " ");
            // Append to plaintext message
            message += character;
        }
        System.out.println(); // just a newline

        return message;
    }

    public BigInteger e() {
        return num1;
    }

    /*
    Encryption is performed with the public key.
     */
    public BigInteger encrypt(String message) throws KeySizeException {
        // Turn string into array of bytes (numbers)
        byte[] bytes = message.getBytes(StandardCharsets.US_ASCII);
        StringBuilder binary = new StringBuilder();

        // Do some bitwise operations to translate decimal to binary
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

        // Print out the resulting binary string. 1's and 0's...
        System.out.printf("Encrypt: binary %s%n", binary.toString());

        // Create a BigInteger from the string, specific radix of base 2
        BigInteger m = new BigInteger(binary.toString(), 2);

        // Check if the message is too big for this key to encrypt
        if (m.compareTo(n()) > 0) {
            throw new KeySizeException(m, n());
        } else {
            // Perform the encryption
            BigInteger result = m.multiply(e()).mod(n());
            // Print out the calculations being done on the line above
            System.out.printf("Encrypt: %d * %d (%d) %% %d = %d%n", m, e(), m.multiply(e()), n(), result);
            // Return encrypted string as a number
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
