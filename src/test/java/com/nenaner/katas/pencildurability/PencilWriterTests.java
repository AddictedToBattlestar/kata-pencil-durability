package com.nenaner.katas.pencildurability;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PencilWriterTests {

    @Test
    public void itCanWriteSimpleText() {
        PencilWriter pencilWriter = new PencilWriter();
        assertEquals("simple text", pencilWriter.write("simple text"));
    }
}
