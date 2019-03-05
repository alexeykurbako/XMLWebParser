package parser.sax_parser;


import director.Director;
import exception.XMLParsingException;
import model.Sweet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import parser.Parser;
import parser.sax_parser.handler.Handler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import java.io.IOException;

import java.util.List;

public class SaxParser implements Parser {
    private static final Logger LOGGER = LogManager.getLogger(Director.class);
    @Override
    public List<Sweet> parse(String PATH) throws XMLParsingException {
        Handler handler;
        try {
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = parserFactory.newSAXParser();
            XMLReader xmlReader = saxParser.getXMLReader();
            handler = new Handler();
            xmlReader.setContentHandler(handler);
            xmlReader.parse(PATH);
        } catch (ParserConfigurationException ex) {
            LOGGER.error("Wrong parser configuration", ex);
            throw new XMLParsingException("ParserConfigurationException", ex);
        } catch (SAXException ex) {
            LOGGER.error("Wrong file", ex);
            throw new XMLParsingException("SAXException", ex);
        } catch (IOException ex) {
            LOGGER.error("Wrong file", ex);
            throw new XMLParsingException("IOException", ex);
        }
        return handler.getData();
    }
}
