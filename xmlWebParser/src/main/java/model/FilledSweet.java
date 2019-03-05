package model;

import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="FilledSweet",namespace = "http://www.example.com/candies")
@XmlRootElement(name="filled-sweet",namespace ="http://www.example.com/candies" )
public class FilledSweet extends Sweet {
    @XmlElement(name = "filling-weight", namespace = "http://www.example.com/candies")
    private int fillingWeight;

    public FilledSweet(){}


    public int getFillingWeight() {
        return fillingWeight;
    }


    public void setFillingWeight(int fillingWeight) {
        this.fillingWeight = fillingWeight;
    }


    public FilledSweet(String name, double energy, SweetValue value, Producers producer, int fillingWeight) {
        super(name, energy, value, producer);

        this.fillingWeight = fillingWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FilledSweet that = (FilledSweet) o;
        return fillingWeight == that.fillingWeight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fillingWeight);
    }

    @Override
    public String toString() {
        return "FilledSweet{" +
                " name=" + super.getName() +
                " energy=" + super.getEnergy() +
                " value=" + super.getValue() +
                " producer=" + super.getProducer() +
                " fillingWeight=" + fillingWeight +
                '}';
    }
}
