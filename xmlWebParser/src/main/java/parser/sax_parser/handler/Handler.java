package parser.sax_parser.handler;

import model.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class Handler extends DefaultHandler {
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

    private List<Sweet> candies = new ArrayList<>();
    private Sweet currentSweet;
    private SweetValue currentValue;
    private String currentElement;

    public List<Sweet> getData(){ return candies; }

    @Override
    public void startDocument() throws SAXException {

    }

    @Override
    public void endDocument() throws SAXException {

    }

    @Override
    public void startElement(String namespace, String localName, String qName, Attributes attributes){
        currentElement = qName;
        switch(qName){
            case FILLED_SWEET_TAG:{
                currentSweet = new FilledSweet();
                currentSweet.setName(attributes.getValue(NAME_ATTRIBUTE));
            } break;

            case CHOCOLATE_SWEET_TAG:{
                currentSweet = new ChocolateSweet();
                currentSweet.setName(attributes.getValue(NAME_ATTRIBUTE));
            } break;

            case VALUE_TAG:{
                currentValue = new SweetValue();
                currentSweet.setValue(currentValue);
            } break;

        }
    }

    @Override
    public void characters(char[] chars, int start, int length){
        String text = new String(chars, start, length).trim();

        if (text.length() == 0) {
            return;
        }

        switch (currentElement){
            case ENERGY_TAG: {
                currentSweet.setEnergy(Double.valueOf(text));
            } break;

            case PROTEINS_TAG: {
                currentValue.setProteins(Integer.valueOf(text));
            } break;

            case FATS_TAG: {
                currentValue.setFats(Integer.valueOf(text));
            } break;

            case CARBOHYDRATES_TAG: {
                currentValue.setCarbohydrates(Integer.valueOf(text));

            } break;

            case FILLING_WEIGHT_TAG: {
                int value = Integer.valueOf(text);
                ((FilledSweet)currentSweet).setFillingWeight(value);
            } break;

            case CHOCOLATE_TYPE_TAG: {
                ChocolateTypes choco = ChocolateTypes.valueOf(text);
                ((ChocolateSweet)currentSweet).setChocolate(choco);
            } break;

            case PRODUCTION_TAG: {
                Producers prod = Producers.valueOf(text);
                currentSweet.setProducer(prod);
            } break;
        }
    }

    @Override
    public void endElement(String namespace, String localName, String qName){
        switch (qName){
            case FILLED_SWEET_TAG:{
                candies.add(currentSweet);
            } break;

            case CHOCOLATE_SWEET_TAG:{
                candies.add(currentSweet);
            } break;
        }
    }
}
