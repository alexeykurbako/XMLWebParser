package parser.parser_factory;

import parser.Parser;

public interface Factory {
        Parser getParser(Type type);
}
