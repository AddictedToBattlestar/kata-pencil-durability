package com.nenaner.katas.pencildurability;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PencilWriterTests {
    private PencilWriter subject;

    @Before
    public void setup() {
        subject = new PencilWriter.PencilWriterBuilder(1).build();
    }

    @Test
    public void itCanWriteSimpleText() {
        subject.write("simple text");

        assertEquals("simple text", subject.getTextWritten());
    }

    @Test
    public void itCanContinueToWriteAdditionalText() {
        subject.write("simple text");
        subject.write("along with a bit more text");

        assertEquals("simple text along with a bit more text", subject.getTextWritten());
    }

    @Test
    public void itCanWriteUntilFullyDegraded() {
        subject = new PencilWriter.PencilWriterBuilder(1)
                .setDurability(20)
                .build();

        subject.write("simple text that should eventually fade out due to pencil durability");

        assertEquals("simple text that should                                             ", subject.getTextWritten());
    }

    @Test
    public void itCanWriteWithADifferentDegradationSetting() {
        subject = new PencilWriter.PencilWriterBuilder(1)
                .setDurability(40)
                .build();

        subject.write("simple text that should eventually fade out due to pencil durability");

        assertEquals("simple text that should eventually fade out due                     ", subject.getTextWritten());
    }

    @Test
    public void itCanWriteWhenNewLinesArePresent() {
        subject.write("text-for-line-1" + System.lineSeparator());
        subject.write("text-for-line-2");

        assertEquals("text-for-line-1" + System.lineSeparator() + "text-for-line-2", subject.getTextWritten());
    }

    @Test
    public void upperCaseCharactersCausePencilDegradationMoreQuickly() {
        subject = new PencilWriter.PencilWriterBuilder(1)
                .setDurability(20)
                .build();

        subject.write("Simple Text That Should Eventually Fade Out Due To Pencil Durability");

        assertEquals("Simple Text That Sh                                                 ", subject.getTextWritten());
    }

    @Test
    public void sharpeningAPencilRestoresDurability() {
        subject = new PencilWriter.PencilWriterBuilder(1)
                .setDurability(15)
                .build();

        subject.write("Simple text that fades out");
        subject.sharpenPencil();
        subject.write("but now continues");

        assertEquals("Simple text that           but now continues", subject.getTextWritten());
    }

    @Test
    public void aShortPencilCanOnlyBeSharpenedAFewTimes() {
        subject = new PencilWriter.PencilWriterBuilder(2)
                .setDurability(15)
                .build();

        subject.write("We hold these truths to be self-evident,");
        subject.sharpenPencil();
        subject.write("that all men are created equal,");
        subject.sharpenPencil();
        subject.write("that they are endowed by their Creator with certain unalienable Rights,");
        subject.sharpenPencil();
        subject.write("that among these are Life, Liberty and the pursuit of Happiness.");

        assertEquals("We hold these tru                       " +
                " that all men are cr            " +
                " that they are endo                                                     " +
                "                                                                 ", subject.getTextWritten());
    }

    @Test
    public void itCanEraseText() {
        subject.write("simple text along with a bit more text");
        subject.erase("text");

        assertEquals("simple text along with a bit more     ", subject.getTextWritten());
    }

    @Test
    public void itCanEraseTextInTheMiddleOfWhatWasWritten() {
        subject.write("simple text along with a bit more text");
        subject.erase("bit");

        assertEquals("simple text along with a     more text", subject.getTextWritten());
    }

    @Test
    public void itCanHandleTryingToEraseTextNotWritten() {
        subject.write("simple text along with a bit more text");
        subject.erase("apple");

        assertEquals("simple text along with a bit more text", subject.getTextWritten());
    }

    @Test
    public void itCanEraseUntilFullyDegraded() {
        subject = new PencilWriter.PencilWriterBuilder(1)
                .setEraserDurability(3)
                .build();

        subject.write("simple text along with a bit more text");
        subject.erase("text");

        assertEquals("simple text along with a bit more t   ", subject.getTextWritten());
    }

    @Test
    public void itCanEraseAPhrase() {
        subject.write("simple text along with a bit more text");
        subject.erase("a bit");

        assertEquals("simple text along with       more text", subject.getTextWritten());
    }

    @Test
    public void itCanEditText() {
        subject.write("simple text along with a bit more text");
        subject.edit("along with", "as well as");

        assertEquals("simple text as well as a bit more text", subject.getTextWritten());
    }
}
