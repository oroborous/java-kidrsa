package edu.wctc.exception;

import java.math.BigInteger;

public class KeySizeException extends Exception {
    public KeySizeException(BigInteger message, BigInteger n) {
        super(String.format("Message (%,d) is greater than n (%,d).\n\nCannot proceed with encryption.", message, n));
    }
}
