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

    public String write(String textToWrite) {
        textWritten = textWritten.isEmpty() ? textToWrite : textWritten + " " + textToWrite;
        return getTextWritten(textToWrite);
    }

    private String getTextWritten(String textToWrite) {
        return durability == null ? textWritten : textToWrite.substring(0, durability) + getABunchOfSpaces(textToWrite.length() - durability);
    }

    private String getABunchOfSpaces(int numberOfCharacters) {
        StringBuilder textToReturn = new StringBuilder();
        for (int x = 0; x < numberOfCharacters; x++) {
            textToReturn.append(" ");
        }
        return textToReturn.toString();
    }
}
