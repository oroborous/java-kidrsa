package edu.wctc.exception;

public class KeyFormatException extends Exception {
    public KeyFormatException(String formattedKey, String msg) {
        super(String.format("Invalid key format: %s%n%n%s", formattedKey, msg));
    }
}
