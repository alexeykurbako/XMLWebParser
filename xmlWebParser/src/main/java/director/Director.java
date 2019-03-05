package director;

import exception.WrongFileException;
import exception.XMLParsingException;
import model.Sweet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import parser.Parser;
import parser.parser_factory.Type;
import parser.parser_factory.XMLParserFactory;
import validator.XMLValidator;

import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Director {
    private final Parser parser;
    private final XMLValidator validator;
    private final XMLParserFactory parserFactory;
    private static final Logger LOGGER = LogManager.getLogger(Director.class);

    public Director(Type parserType, XMLValidator validator) {
        parserFactory = new XMLParserFactory();
        this.parser = parserFactory.getParser(parserType);
        this.validator = validator;
    }

    public List<Sweet> process(String PATH) {
        List<Sweet> candies = new ArrayList<>();
        try {
            if (validator.validate(PATH)) {
                candies = parser.parse(PATH);
            }
        } catch (XMLParsingException ex) {
            LOGGER.error("Wrong data", ex);
        } catch (WrongFileException ex) {
            LOGGER.error("Wrong file", ex);
        }
        return candies;
    }
}
