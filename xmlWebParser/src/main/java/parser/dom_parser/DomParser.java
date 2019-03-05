package parser.dom_parser;

import director.Director;
import exception.XMLParsingException;
import model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import parser.Parser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class DomParser implements Parser {

    static final String FILLED_SWEET_TAG = "filled-sweet";
    static final String CHOCOLATE_SWEET_TAG = "chocolate-sweet";
    static final String ENERGY_TAG = "energy";
    static final String VALUE_TAG = "value";
    static final String PROTEINS_TAG = "proteins";
    static final String FATS_TAG = "fats";
    static final String CARBOHYDRATES_TAG = "carbohydrates";
    static final String FILLING_WEIGHT_TAG = "filling-weight";
    static final String CHOCOLATE_TYPE_TAG = "chocolate-type";
    static final String PRODUCTION_TAG = "production";
    static final String NAME_ATTRIBUTE = "name";
    private static final Logger LOGGER = LogManager.getLogger(Director.class);
    @Override
    public List<Sweet> parse(String PATH) throws XMLParsingException {
        List<Sweet> sweets = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);

        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(PATH);
            Element root = document.getDocumentElement();
            NodeList nodesList = root.getChildNodes();

            for (int i = 0; i < nodesList.getLength(); i++) {
                Node node = nodesList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Sweet sweet = parseSweet(nodesList.item(i));
                    sweets.add(sweet);
                }
            }
        } catch (ParserConfigurationException ex) {
            LOGGER.error("Wrong parser configuration", ex);
            throw new XMLParsingException("ParserConfigurationException ", ex);
        } catch (SAXException ex) {
            LOGGER.error("Wrong file", ex);
            throw new XMLParsingException("SAXException ", ex);
        } catch (IOException ex) {
            LOGGER.error("Wrong file", ex);
            throw new XMLParsingException("IOException ", ex);
        }

        return sweets;
    }

    private Element getChildNode(String tagName, Element parent) {
        NodeList nodes = parent.getElementsByTagName(tagName);
        return (Element) nodes.item(0);
    }

    private String getChildNodeValue(String tagName, Element parent) {
        Element child = getChildNode(tagName, parent);
        Node childNode = child.getFirstChild();
        return childNode.getNodeValue();
    }

    private Sweet parseSweet(Node node) throws XMLParsingException {
        Sweet currentSweet;
        SweetValue value = new SweetValue();
        switch (node.getLocalName()) {
            case FILLED_SWEET_TAG:
                currentSweet = new FilledSweet();
                break;
            case CHOCOLATE_SWEET_TAG:
                currentSweet = new ChocolateSweet();
                break;
            default:
                LOGGER.error("Wrong tag");
                throw new XMLParsingException("Wrong tag");
        }
        Element element = (Element) node;
        String name = element.getAttribute(NAME_ATTRIBUTE);
        currentSweet.setName(name);

        String energy = getChildNodeValue(ENERGY_TAG, element);
        double energyValue = Double.valueOf(energy);
        currentSweet.setEnergy(energyValue);

        NodeList valuesList = element.getElementsByTagName(VALUE_TAG);

        String proteins = getChildNodeValue(PROTEINS_TAG, (Element)valuesList.item(0));
        int proteinsValue = Integer.valueOf(proteins);
        value.setProteins(proteinsValue);

        String fats = getChildNodeValue(FATS_TAG, (Element)valuesList.item(0));
        int fatsValue = Integer.valueOf(fats);
        value.setFats(fatsValue);

        String carbohydrates = getChildNodeValue(CARBOHYDRATES_TAG, (Element)valuesList.item(0));
        int carbohydratesValue = Integer.valueOf(carbohydrates);
        value.setCarbohydrates(carbohydratesValue);

        currentSweet.setValue(value);

        if (CHOCOLATE_SWEET_TAG.equals(node.getLocalName())) {
            String chocoSweet = getChildNodeValue(CHOCOLATE_TYPE_TAG, element);
            ChocolateTypes choco = ChocolateTypes.valueOf(chocoSweet);
            ((ChocolateSweet) currentSweet).setChocolate(choco);
        }
        if(FILLED_SWEET_TAG.equals(node.getLocalName())) {
            String fillSweet = getChildNodeValue(FILLING_WEIGHT_TAG, element);
            int fillingWeight = Integer.valueOf(fillSweet);
            ((FilledSweet) currentSweet).setFillingWeight(fillingWeight);
        }

        String production = getChildNodeValue(PRODUCTION_TAG, element);
        Producers producer = Producers.valueOf(production);
        currentSweet.setProducer(producer);

        return currentSweet;
    }
}
