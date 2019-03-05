package director;


import exception.WrongFileException;
import exception.XMLParsingException;
import model.*;
import org.junit.Assert;

import org.junit.Test;
import org.mockito.Mockito;
import org.xml.sax.SAXException;
import parser.Parser;
import parser.parser_factory.Type;
import parser.parser_factory.XMLParserFactory;

import validator.XMLValidator;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class DirectorTest {
    private static final String FILE_PATH = "src/test/resources/file.xml";
    private final SweetValue value = new SweetValue(15,15,15);
    private final Sweet firstSweet = new FilledSweet("Golden Key",100, value, Producers.KOMMUNARKA,10);
    private final Sweet secondSweet = new ChocolateSweet("Alenka", 150, value, Producers.KOMMUNARKA, ChocolateTypes.MILK);
    private final List<Sweet> expected = Arrays.asList(firstSweet, secondSweet);

    private static final XMLParserFactory FACTORY = mock(XMLParserFactory.class);
    private static final Parser PARSER = mock(Parser.class);
    private static final XMLValidator VALIDATOR = mock(XMLValidator.class);
    private static final Type TYPE = Type.DOM;

    private final Director director = new Director(TYPE, VALIDATOR);

    @Test
    public void directorShouldValidateAndParse() throws XMLParsingException, WrongFileException {
        when(VALIDATOR.validate(FILE_PATH)).thenReturn(true);
        when(FACTORY.getParser(TYPE)).thenReturn(PARSER);
        when(PARSER.parse(FILE_PATH)).thenReturn(expected);

        List<Sweet> result = director.process(FILE_PATH);
        Assert.assertEquals(expected, result);

        Mockito.verify(VALIDATOR).validate(FILE_PATH);
        verifyNoMoreInteractions(VALIDATOR);
    }
}
