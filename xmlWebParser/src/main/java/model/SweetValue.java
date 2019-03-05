package model;

import javax.xml.bind.annotation.*;
import java.util.Objects;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="SweetValue", namespace = "http://www.example.com/candies")
@XmlRootElement(name="value", namespace ="http://www.example.com/candies" )
public class SweetValue {
    @XmlElement(name = "proteins", namespace = "http://www.example.com/candies")
    private int proteins;
    @XmlElement(name = "fats", namespace = "http://www.example.com/candies")
    private int fats;
    @XmlElement(name = "carbohydrates", namespace = "http://www.example.com/candies")
    private int carbohydrates;

    public SweetValue(){}

    public SweetValue(int proteins, int fats, int carbohydrates) {
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }

    public int getProteins() {
        return proteins;
    }

    public int getFats() {
        return fats;
    }

    public int getCarbohydrates() {
        return carbohydrates;
    }


    public void setProteins(int proteins) {
        this.proteins = proteins;
    }


    public void setFats(int fats) {
        this.fats = fats;
    }


    public void setCarbohydrates(int carbohydrates) {
        this.carbohydrates = carbohydrates;
    }


    @Override
    public String toString() {
        return "SweetValue{" +
                "proteins=" + proteins +
                ", fats=" + fats +
                ", carbohydrates=" + carbohydrates +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SweetValue that = (SweetValue) o;

        return proteins == that.proteins &&
                fats == that.fats &&
                carbohydrates == that.carbohydrates;
    }

    @Override
    public int hashCode() {
        return Objects.hash(proteins, fats, carbohydrates);
    }
}
