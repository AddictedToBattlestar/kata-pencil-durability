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
        return durability == null ? textWritten : getDegradedWrittenText();
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
}
