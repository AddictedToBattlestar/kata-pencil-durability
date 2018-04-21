package com.nenaner.katas.pencildurability;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PencilWriterTests {
    private PencilWriter subject;

    @Before
    public void setup() {
        subject = new PencilWriter();
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
        subject = new PencilWriter(20);

        subject.write("simple text that should eventually fade out due to pencil durability");

        assertEquals("simple text that should                                             ", subject.getTextWritten());
    }

    @Test
    public void itCanWriteWithADifferentDegradationSetting() {
        subject = new PencilWriter(40);

        subject.write("simple text that should eventually fade out due to pencil durability");

        assertEquals("simple text that should eventually fade out due                     ", subject.getTextWritten());
    }

    @Test
    public void itCanWriteWhenNewLinesArePresent() {
        subject.write("text-for-line-1" + System.lineSeparator());
        subject.write("text-for-line-2");

        assertEquals("text-for-line-1" + System.lineSeparator() + "text-for-line-2", subject.getTextWritten());
    }
}
