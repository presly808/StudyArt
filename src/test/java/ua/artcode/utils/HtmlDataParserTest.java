package ua.artcode.utils;


import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class HtmlDataParserTest {

    @Test
    public void testParseInt(){
        String arg3 = "4,5,6,2.87";
        List<String> lines3 = CodingBatHtmlDataParser.parseTestData(arg3);
        Assert.assertEquals(lines3.stream().count(), 4);
    }

    @Test
    public void testParseMixedDataTypes(){
        String arg1 = "{1,2,3,4},4,5,6,\"sdfsd\",\"sdfsdf\",2.87,{2,2,2,2},2,2";
        List<String> lines1 = CodingBatHtmlDataParser.parseTestData(arg1);
        Assert.assertEquals(lines1.stream().count(),10);;
    }

    @Test
    public void testParseMixedDataTypesEndWithMas(){
        String arg1 = "{1,2,3,4},4,5,6,\"word1\",\"word2\",2.87,2,2,{2,2,2,2}";

        List<String> lines1 = CodingBatHtmlDataParser.parseTestData(arg1);

        Assert.assertEquals(lines1.stream().count(), 10);;
    }

    @Test
    public void testParseOnlyString(){
        String arg1 = "{1,2,3,4},4,5,6,\"word1\",\"word2\",2.87,2,2,{2,2,2,2}";

        List<String> lines1 = CodingBatHtmlDataParser.parseTestData(arg1);

        Assert.assertEquals(lines1.stream().count(), 10);;
    }

    @Test
    public void testParseOnlyArrays(){
        String arg1 = "{1,2,3,4},{2,2,2,2},{2,2,2,2}";

        List<String> lines1 = CodingBatHtmlDataParser.parseTestData(arg1);

        Assert.assertEquals(lines1.stream().count(), 3);;
    }

    @Test
    public void testParseOnlyArraysOtherBraces(){
        String arg1 = "[1,2,3,4],[2,2,2,2],[2,2,2,2]";

        List<String> lines1 = CodingBatHtmlDataParser.parseTestData(arg1);

        Assert.assertEquals(lines1.stream().count(), 3);;
    }

    @Test
    public void testParseMixedDataTypesWithSpaces() {
        String arg1 = "8.7, {\"cat\", \"dog\", \"monkey\"}, 77, 69, \"fish\", {1, 2, 3}";

        List<String> lines1 = CodingBatHtmlDataParser.parseTestData(arg1);

        Assert.assertEquals(lines1.stream().count(), 6);;
    }

    @Test
    public void testParsedDataEquals() {
        String arg1 = "8.7, {\"cat\", \"dog\", \"monkey\"}, 77, 69, \"fish\", {1, 2, 3}";

        List<String> lines1 = CodingBatHtmlDataParser.parseTestData(arg1);

        Assert.assertEquals(lines1.get(3), "69");;

    }

}
