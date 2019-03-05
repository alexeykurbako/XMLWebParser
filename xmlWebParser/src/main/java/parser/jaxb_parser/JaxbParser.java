package parser.jaxb_parser;

import director.Director;
import exception.WrongFileException;
import exception.XMLParsingException;
import model.Candies;
import model.FilledSweet;
import model.Sweet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parser.Parser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

import java.util.List;

public class JaxbParser implements Parser {
    private static final Logger LOGGER = LogManager.getLogger(Director.class);
    @Override
    public List<Sweet> parse(String PATH) throws XMLParsingException {

        Candies candies;
        try {
            File sourceFile = new File(PATH);
            JAXBContext context = JAXBContext.newInstance(Candies.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            candies = (Candies) unmarshaller.unmarshal(sourceFile);
        } catch (JAXBException exception) {
            LOGGER.error("Wrong file", exception);
            throw new XMLParsingException("Wrong file", exception);
        }
        return candies.getList();
    }
}
