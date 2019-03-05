package parser.parser_factory;

import exception.XMLParsingException;
import parser.Parser;
import parser.dom_parser.DomParser;
import parser.jaxb_parser.JaxbParser;
import parser.sax_parser.SaxParser;

public class XMLParserFactory implements Factory {
    public Parser getParser(Type type){
        switch (type) {
            case JAXB:
                return new JaxbParser();
            case SAX:
                return new SaxParser();
            case DOM:
                return new DomParser();
        }
        return null;
    }
}
