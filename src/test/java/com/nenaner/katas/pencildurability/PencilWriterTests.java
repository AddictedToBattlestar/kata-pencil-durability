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
        assertEquals("simple text", subject.write("simple text"));
    }

    @Test
    public void itCanContinueToWriteAdditionalText() {
        subject.write("simple text");
        assertEquals("simple text along with a bit more text", subject.write("along with a bit more text"));
    }

    @Test
    public void itCanWriteUntilFullyDegraded() {
        subject = new PencilWriter(20);

        String actualTextWritten = subject.write("simple text that should eventually fade out due to pencil durability");

        assertEquals("simple text that should                                             ", actualTextWritten);
    }

    @Test
    public void itCanWriteWithADifferentDegradationSetting() {
        subject = new PencilWriter(40);

        String actualTextWritten = subject.write("simple text that should eventually fade out due to pencil durability");

        assertEquals("simple text that should eventually fade out due                     ", actualTextWritten);
    }
}
