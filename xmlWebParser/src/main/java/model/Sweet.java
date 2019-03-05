package model;

import javax.xml.bind.annotation.*;
import java.util.Objects;
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "sweet",propOrder = {"energy", "value", "production"})
@XmlRootElement(name="sweet",namespace ="http://www.example.com/candies" )
@XmlSeeAlso({FilledSweet.class, ChocolateSweet.class})
public class Sweet {
    @XmlAttribute
    private String name;
    @XmlElement(name = "energy", namespace = "http://www.example.com/candies")
    private double energy;
    @XmlElement(name = "value", namespace = "http://www.example.com/candies")
    private SweetValue value;
    @XmlElement(name = "production", namespace = "http://www.example.com/candies")
    private Producers producer;


    public Sweet(){}

    public Sweet(String name, double energy, SweetValue value, Producers producer) {
        this.name = name;
        this.energy = energy;
        this.value = value;
        this.producer = producer;

    }

    public String getName() {
        return name;
    }

    public double getEnergy() {
        return energy;
    }

    public SweetValue getValue() {
        return value;
    }

    public Producers getProducer() {
        return producer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public void setValue(SweetValue value) {
        this.value = value;
    }

    public void setProducer(Producers producer) {
        this.producer = producer;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sweet sweet = (Sweet) o;
        return Double.compare(sweet.energy, energy) == 0 &&
                name.equals(sweet.name) &&
                producer == sweet.producer &&
                value.equals(sweet.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, energy, producer, value);
    }

    @Override
    public String toString() {
        return "Sweet{" +
                "name='" + name + '\'' +
                ", energy=" + energy +
                ", producer=" + producer +
                ", value=" + value +
                '}';
    }
}
