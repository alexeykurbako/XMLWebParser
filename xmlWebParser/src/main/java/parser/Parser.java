package parser;
import exception.XMLParsingException;
import model.Sweet;
import java.util.List;

public interface Parser {
    List<Sweet> parse(String PATH) throws XMLParsingException;
}
