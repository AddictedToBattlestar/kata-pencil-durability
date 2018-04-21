package com.nenaner.katas.pencildurability;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PencilWriterTests {
    private PencilWriter subject;

    @Before
    public void setup() {
        subject = new PencilWriter(1);
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
        subject = new PencilWriter(1, 20);

        subject.write("simple text that should eventually fade out due to pencil durability");

        assertEquals("simple text that should                                             ", subject.getTextWritten());
    }

    @Test
    public void itCanWriteWithADifferentDegradationSetting() {
        subject = new PencilWriter(1, 40);

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
        subject = new PencilWriter(1, 20);

        subject.write("Simple Text That Should Eventually Fade Out Due To Pencil Durability");

        assertEquals("Simple Text That Sh                                                 ", subject.getTextWritten());
    }

    @Test
    public void sharpeningAPencilRestoresDurability() {
        subject = new PencilWriter(1, 15);

        subject.write("Simple text that fades out");
        subject.sharpenPencil();
        subject.write("but now continues");

        assertEquals("Simple text that           but now continues", subject.getTextWritten());
    }

    @Test
    public void aShortPencilCanOnlyBeSharpenedAFewTimes() {
        subject = new PencilWriter(2, 15);

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

        assertEquals("simple text along with a bit more ", subject.getTextWritten());
    }
}
