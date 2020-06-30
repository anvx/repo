package com.jtmog.alukyanov;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.NoSuchFileException;
import java.util.Arrays;

public class ParserTest {
    private Parser test;

    @Before
    public void setUp() {
        test = new Parser("src/main/java/com/jtmog/alukyanov/test.txt");
    }

    @Test
    public void testSetPath() {
        test.setPath("src/main/java/com/jtmog/alukyanov/test.txt");
        Assert.assertEquals("src/main/java/com/jtmog/alukyanov/test.txt", test.getPath());
    }

    @Test
    public void testGetPath() {
        test.setPath("C:/test.txt");
        Assert.assertEquals("C:/test.txt", test.getPath());
    }

    @Test
    public void testRead() {
        Assert.assertEquals("Once upon ,,, ... ... " + System.lineSeparator() +
                "a,. time..", test.read());
    }

    @Test
    public void testCorrect() {
        test.setContent("Once ...... ,,, upon, a,, time");
        Assert.assertEquals("once   upon a time", test.correct());
    }

    @Test (expected = NoSuchFileException.class)
    public void testFileNotFound() {
        test.setPath("src/main/java/com/jtmog/alukyanov/NoSuchFile.txt");
        test.read();
    }

    @Test
    public void testEmptyFile() {
        test.setPath("src/main/java/com/jtmog/alukyanov/Empty.txt");
        test.read();
    }
}