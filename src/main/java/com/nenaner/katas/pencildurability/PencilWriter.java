package com.nenaner.katas.pencildurability;

public class PencilWriter {
    private String textOnPaper;
    private Integer pencilLength;
    private Integer durability;
    private Integer durabilityPointsRemaining;
    private Integer eraserDurability;

    private PencilWriter(PencilWriterBuilder pencilBuilder) {
        pencilLength = pencilBuilder.pencilLength;
        textOnPaper = "";
        if (pencilBuilder.durability != null) {
            durability = pencilBuilder.durability;
            durabilityPointsRemaining = durability;
        }
        eraserDurability = pencilBuilder.eraserDurability;
    }

    public void write(String textToWrite) {
        String textToBeWritten = buildNewTextToBeWritten(textToWrite);
        textOnPaper += getDegradedTextToBeWritten(textToBeWritten);
    }

    public String getTextOnPaper() {
        return textOnPaper;
    }

    public void sharpenPencil() {
        if (pencilLength > 0) {
            durabilityPointsRemaining = durability;
            pencilLength--;
        }
    }

    public void erase(String textToErase) {
        int indexForStartingLocationOfText = textOnPaper.lastIndexOf(textToErase);
        if (indexForStartingLocationOfText != -1) {
            textOnPaper = textOnPaper.substring(0, indexForStartingLocationOfText)
                    + getErasedText(textToErase)
                    + textOnPaper.substring(indexForStartingLocationOfText + textToErase.length(), textOnPaper.length());
        }
    }

    public void edit(String textBeingEdited, String newTextToWrite) {
        StringBuilder textOnPaperBeingEdited = new StringBuilder(textOnPaper);
        int indexForTextOnPaperBeingEdited = textOnPaper.lastIndexOf(textBeingEdited);
        for (int indexOnWordBeingEdited = 0; indexOnWordBeingEdited < newTextToWrite.length(); indexOnWordBeingEdited++) {
            Character characterAboutToBeWritten = newTextToWrite.charAt(indexOnWordBeingEdited);
            if (isTheCharacterBeingWrittenCollidingWithExistingText(textBeingEdited, indexForTextOnPaperBeingEdited, indexOnWordBeingEdited, characterAboutToBeWritten)) {
                characterAboutToBeWritten = '@';
            }
            updateOrAppendEditedText(indexForTextOnPaperBeingEdited + indexOnWordBeingEdited, textOnPaperBeingEdited, characterAboutToBeWritten);
        }
        textOnPaper = textOnPaperBeingEdited.toString();
    }

    private boolean isTheCharacterBeingWrittenCollidingWithExistingText(String textBeingEdited, int indexForTextOnPaperBeingEdited, int indexOnWordBeingEdited, Character characterAboutToBeWritten) {
        return indexOnWordBeingEdited > textBeingEdited.length()
                && (indexForTextOnPaperBeingEdited + indexOnWordBeingEdited) < textOnPaper.length()
                && !characterAboutToBeWritten.equals(textOnPaper.charAt(indexForTextOnPaperBeingEdited))
                && textOnPaper.charAt(indexForTextOnPaperBeingEdited + indexOnWordBeingEdited) != ' ';
    }

    private void updateOrAppendEditedText(int indexToTryToEdit, StringBuilder textOnPaperBeingEdited, Character characterAboutToBeWritten) {
        if (indexToTryToEdit < textOnPaperBeingEdited.length())
            textOnPaperBeingEdited.setCharAt(indexToTryToEdit, characterAboutToBeWritten);
        else
            textOnPaperBeingEdited.append(characterAboutToBeWritten);
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
        if (textOnPaper.isEmpty() || System.lineSeparator().equals(getLastCharacterWritten()))
            return textToWrite;
        else
            return " " + textToWrite;
    }

    private String getLastCharacterWritten() {
        return textOnPaper.substring(textOnPaper.length() - 1, textOnPaper.length());
    }

    private String getDegradedTextToBeWritten(String textToBeWritten) {
        if (durability == null) return textToBeWritten;
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
