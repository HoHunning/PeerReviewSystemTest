package com.example.demo.PeerReviewSystem;
import org.junit.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class PeerReviewSystemTest {
    private PeerReviewSystem ps;
    private  ByteArrayOutputStream outContent;
    private final PrintStream origOut = System.out;
    private String actual;
    private String expected;
    <#--  private final String tcFolder= "src/test/java/com/example/demo/PeerReviewSystem/TestCase/";  -->
    
    @BeforeEach
    public void setUp(){
        ps = new PeerReviewSystem();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    
<#list 0..numOfTestCases-1 as i>
    @Test
    public void Case${i}(){
        ps.CommandHandler("TC${i}.in");
        actual = OutputParser.parse(outContent.toString());
        System.setOut(origOut);
        try{
            expected = OutputParser.parse(OutputParser.fileToString("TC${i}.out"));
        }catch(Exception e){
        }
        assertEquals(expected, actual);
    }
</#list>
}