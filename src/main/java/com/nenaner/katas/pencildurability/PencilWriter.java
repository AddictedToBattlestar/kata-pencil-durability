package com.nenaner.katas.pencildurability;

public class PencilWriter {
    private String textWritten;
    private Integer durability;
    private Integer durabilityPointsRemaining;

    public PencilWriter() {
        textWritten = "";
    }

    public PencilWriter(int desiredDurabilityInCharacters) {
        textWritten = "";
        durability = desiredDurabilityInCharacters;
        durabilityPointsRemaining = durability;

    }

    public void write(String textToWrite) {
        String textToBeWritten = buildNewTextToBeWritten(textToWrite);
        textWritten += getDegradedTextToBeWritten(textToBeWritten);
    }

    public String getTextWritten() {
        return textWritten;
    }

    public void sharpenPencil() {
        durabilityPointsRemaining = durability;
    }

    private String buildNewTextToBeWritten(String textToWrite) {
        if (textWritten.isEmpty() || System.lineSeparator().equals(getLastCharacterWritten()))
            return textToWrite;
        else
            return " " + textToWrite;
    }

    private String getLastCharacterWritten() {
        return textWritten.substring(textWritten.length() - 1, textWritten.length());
    }

    private String getDegradedTextToBeWritten(String textToBeWritten) {
        if (durability == null)
            return textToBeWritten;
        StringBuilder degradedTextToBeWritten = new StringBuilder();
        for (int x = 0; x < textToBeWritten.length(); x++) {
            degradedTextToBeWritten.append(durabilityPointsRemaining > 0 ? textToBeWritten.charAt(x) : " ");
            if (textToBeWritten.charAt(x) != ' ') {
                durabilityPointsRemaining--;
                if (Character.isUpperCase(textToBeWritten.charAt(x))) {
                    durabilityPointsRemaining--;
                }
            }
        }
        return degradedTextToBeWritten.toString();
    }
}
