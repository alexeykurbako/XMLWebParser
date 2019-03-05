package validator;

import exception.WrongFileException;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidatorTest {
    private static final String INVALID_XML_PATH = "src/test/resources/invalid_file.xml";
    private static final String XML_PATH = "src/test/resources/file.xml";
    private static final String XSD_PATH = "src/main/resources/scheme.xsd";
    private final XMLValidator validator = new XMLValidator(XSD_PATH);
    @Test
    public void validatorShouldReturnTrue() throws WrongFileException {
        assertTrue(validator.validate(XML_PATH));
    }

    @Test
    public void validatorShouldReturnFalse() throws WrongFileException {
        assertFalse(validator.validate(INVALID_XML_PATH));
    }

}
