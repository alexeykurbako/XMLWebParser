package validator;

import director.Director;
import exception.WrongFileException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.validation.Validator;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class XMLValidator {
    private final String xsdPath;
    private static final Logger LOGGER = LogManager.getLogger(Director.class);
    public XMLValidator(String xsdPath) {
        this.xsdPath = xsdPath;
    }

    public boolean validate(String xmlFilePath) throws WrongFileException {
        boolean result=false;
        try{
            URL schemaURL=getClass().getClassLoader().getResource("scheme.xsd");
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(schemaURL);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(xmlFilePath);
            validator.validate(source);
            result = true;
        } catch (SAXException ex) {
            LOGGER.error("Wrong scheme", ex);
        } catch (IOException ex) {
            throw new WrongFileException("Wrong file", ex);
        }
        return result;
    }
}


