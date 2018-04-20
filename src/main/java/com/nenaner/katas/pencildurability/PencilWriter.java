package com.nenaner.katas.pencildurability;

public class PencilWriter {
    String textWritten = "";

    public String write(String textToWrite) {
        textWritten = textWritten.isEmpty() ? textToWrite : textWritten + " " + textToWrite;
        return textWritten;
    }
}
