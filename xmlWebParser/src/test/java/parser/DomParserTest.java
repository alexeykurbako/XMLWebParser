package parser;

import exception.XMLParsingException;
import model.*;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;
import parser.dom_parser.DomParser;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class DomParserTest {
    private static final String PATH = "src/test/resources/file.xml";
    private final DomParser parser = new DomParser();
    private final SweetValue value = new SweetValue(15,15,15);
    private final Sweet firstSweet = new FilledSweet("Golden Key",100, value, Producers.KOMMUNARKA,10);
    private final Sweet secondSweet = new ChocolateSweet("Alenka", 150, value, Producers.KOMMUNARKA, ChocolateTypes.MILK);
    @Test
    public void parserShouldReturnList() throws XMLParsingException {
        List<Sweet> expected = Arrays.asList(firstSweet, secondSweet);
        List<Sweet> result = parser.parse(PATH);

        Assert.assertEquals(expected, result);
    }
}
