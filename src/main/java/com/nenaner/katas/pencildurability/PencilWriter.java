package com.nenaner.katas.pencildurability;

public class PencilWriter {
    private String textWritten;
    private Integer durability;

    public PencilWriter() {
        textWritten = "";
    }

    public PencilWriter(int desiredDurabilityInCharacters) {
        textWritten = "";
        durability = desiredDurabilityInCharacters;
    }

    public void write(String textToWrite) {
        textWritten = textWritten.isEmpty() ? textToWrite : appendToTextAlreadyWritten(textToWrite);
    }

    private String appendToTextAlreadyWritten(String textToWrite) {
        if (System.lineSeparator().equals(getLastCharacterWritten()))
            return textWritten + textToWrite;
        else
            return textWritten + " " + textToWrite;
    }

    private String getLastCharacterWritten() {
        return textWritten.substring(textWritten.length() - 1, textWritten.length());
    }

    private String getDegradedWrittenText() {
        StringBuilder degradedTextWritten = new StringBuilder();
        int characterWritten = 0;
        for (int x = 0; x < textWritten.length(); x++) {
            degradedTextWritten.append(characterWritten < durability ? textWritten.charAt(x) : " ");
            if (textWritten.charAt(x) != ' ') characterWritten++;
        }
        return degradedTextWritten.toString();
    }

    public String getTextWritten() {
        return durability == null ? textWritten : getDegradedWrittenText();
    }
}
