package com.nenaner.katas.pencildurability;

public class PencilWriter {
    private String textWritten;
    private Integer pencilLength;
    private Integer durability;
    private Integer durabilityPointsRemaining;
    private Integer eraserDurability;

    private PencilWriter(PencilWriterBuilder pencilBuilder) {
        pencilLength = pencilBuilder.pencilLength;
        textWritten = "";
        if (pencilBuilder.durability != null) {
            durability = pencilBuilder.durability;
            durabilityPointsRemaining = durability;
        }
        eraserDurability = pencilBuilder.eraserDurability;
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

    public void erase(String textToErase) {
        int indexForStartingLocationOfText = textWritten.lastIndexOf(textToErase);
        if (indexForStartingLocationOfText != -1) {
            textWritten = textWritten.substring(0, indexForStartingLocationOfText)
                    + getErasedText(textToErase)
                    + textWritten.substring(indexForStartingLocationOfText + textToErase.length(), textWritten.length());
        }
    }

    public void edit(String textToEdit, String newTextToWrite) {
        StringBuilder resultingTextEdited = new StringBuilder(textWritten);
        int indexOfCharacterBeingEdited = 0;
        int indexForTextBeingEdited = textWritten.lastIndexOf(textToEdit);
        for (int indexForTextWritten = textWritten.lastIndexOf(textToEdit); indexForTextWritten < (indexForTextBeingEdited + newTextToWrite.length()); indexForTextWritten++) {
            Character characterAboutToBeWritten = newTextToWrite.charAt(indexOfCharacterBeingEdited);
            if (isTheCharacterBeingWrittenCollidingWithExistingText(textToEdit, indexOfCharacterBeingEdited, indexForTextWritten, characterAboutToBeWritten)) {
                characterAboutToBeWritten = '@';
            }
            resultingTextEdited.setCharAt(indexForTextWritten, characterAboutToBeWritten);
            indexOfCharacterBeingEdited++;
        }

        textWritten = resultingTextEdited.toString();
    }

    private boolean isTheCharacterBeingWrittenCollidingWithExistingText(String textToEdit, int indexOfCharacterBeingWritten, int x, Character characterAboutToBeWritten) {
        return indexOfCharacterBeingWritten > textToEdit.length() && !characterAboutToBeWritten.equals(textWritten.charAt(x)) && textWritten.charAt(x) != ' ';
    }

    private String getErasedText(String textToErase) {
        StringBuilder resultingTextErased = new StringBuilder(textToErase);
        for (int x = textToErase.length() - 1; x >= 0; x--) {
            if (eraserDurability == null || eraserDurability > 0)
                resultingTextErased.setCharAt(x, ' ');
            decrementEraserDurability();
        }
        return resultingTextErased.toString();
    }

    private void decrementEraserDurability() {
        if (eraserDurability != null)
            eraserDurability--;
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

    public static class PencilWriterBuilder {
        private Integer pencilLength;
        private Integer durability;
        private Integer eraserDurability;

        public PencilWriterBuilder(int desiredPencilLength) {
            pencilLength = desiredPencilLength;
        }

        public PencilWriterBuilder setDurability(int desiredDurabilityInCharacters) {
            durability = desiredDurabilityInCharacters;
            return this;
        }

        public PencilWriterBuilder setEraserDurability(int desiredEraserDurability) {
            eraserDurability = desiredEraserDurability;
            return this;
        }

        public PencilWriter build() {
            return new PencilWriter(this);
        }

    }
}
