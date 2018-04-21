package com.nenaner.katas.pencildurability;

public class PencilWriter {
    private String textWritten;
    private Integer pencilLength;
    private Integer durability;
    private Integer durabilityPointsRemaining;

    public PencilWriter(int desiredPencilLength) {
        pencilLength = desiredPencilLength;
        textWritten = "";
    }

    public PencilWriter(int desiredPencilLength, int desiredDurabilityInCharacters) {
        pencilLength = desiredPencilLength;
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
        if (pencilLength > 0) {
            durabilityPointsRemaining = durability;
            pencilLength--;
        }
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
            durabilityPointsRemaining -= getCostOfWritingAGivenCharacter(textToBeWritten.charAt(x));
        }
        return degradedTextToBeWritten.toString();
    }

    private int getCostOfWritingAGivenCharacter(Character characterWritten) {
        int cost = 0;
        if (characterWritten != ' ') {
            cost++;
            if (Character.isUpperCase(characterWritten)) {
                cost++;
            }
        }
        return cost;
    }
}
