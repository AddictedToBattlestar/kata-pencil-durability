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
        String textToBeWritten = textWritten.isEmpty() ? textToWrite : buildNewTextToBeWritten(textToWrite);
        textWritten = durability == null ? textToBeWritten : getDegradedTextToBeWritten(textToBeWritten);
    }

    public String getTextWritten() {
        return textWritten;
    }

    private String buildNewTextToBeWritten(String textToWrite) {
        if (System.lineSeparator().equals(getLastCharacterWritten()))
            return textWritten + textToWrite;
        else
            return textWritten + " " + textToWrite;
    }

    private String getLastCharacterWritten() {
        return textWritten.substring(textWritten.length() - 1, textWritten.length());
    }

    private String getDegradedTextToBeWritten(String textToBeWritten) {
        StringBuilder degradedTextToBeWritten = new StringBuilder();
        int durabilityPointsWritten = 0;
        for (int x = 0; x < textToBeWritten.length(); x++) {
            degradedTextToBeWritten.append(durabilityPointsWritten < durability ? textToBeWritten.charAt(x) : " ");
            if (textToBeWritten.charAt(x) != ' ') {
                durabilityPointsWritten++;
                if(Character.isUpperCase(textToBeWritten.charAt(x))) {
                    durabilityPointsWritten++;
                }
            }
        }
        return degradedTextToBeWritten.toString();
    }

}
