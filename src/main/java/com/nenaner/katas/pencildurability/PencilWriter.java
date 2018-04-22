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

    public void edit(String textBeingEdited, String newTextToWrite) {
        StringBuilder writtenTextBeingEdited = new StringBuilder(textWritten);
        int indexForTextBeingEdited = textWritten.lastIndexOf(textBeingEdited);
        for (int indexBeingEdited = 0; indexBeingEdited < newTextToWrite.length(); indexBeingEdited++) {
            Character characterAboutToBeWritten = newTextToWrite.charAt(indexBeingEdited);
            if (isTheCharacterBeingWrittenCollidingWithExistingText(textBeingEdited, indexForTextBeingEdited, indexBeingEdited, characterAboutToBeWritten)) {
                characterAboutToBeWritten = '@';
            }

            updateOrAppendEditedText(indexForTextBeingEdited + indexBeingEdited, writtenTextBeingEdited, characterAboutToBeWritten);
        }
        textWritten = writtenTextBeingEdited.toString();
    }

    private void updateOrAppendEditedText(int indexToTryToEdit, StringBuilder resultingTextEdited, Character characterAboutToBeWritten) {
        if (indexToTryToEdit < resultingTextEdited.length())
            resultingTextEdited.setCharAt(indexToTryToEdit, characterAboutToBeWritten);
        else
            resultingTextEdited.append(characterAboutToBeWritten);
    }

    private boolean isTheCharacterBeingWrittenCollidingWithExistingText(String textBeingEdited, int indexForTextBeingEdited, int indexBeingEdited, Character characterAboutToBeWritten) {
        return indexBeingEdited > textBeingEdited.length()
                && (indexForTextBeingEdited + indexBeingEdited) < textWritten.length()
                && !characterAboutToBeWritten.equals(textWritten.charAt(indexForTextBeingEdited))
                && textWritten.charAt(indexForTextBeingEdited + indexBeingEdited) != ' ';
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
