package model;

import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="ChocolateSweet",namespace = "http://www.example.com/candies")
@XmlRootElement(name="chocolate-sweet",namespace ="http://www.example.com/candies" )
public class ChocolateSweet extends Sweet {
    @XmlElement(name = "chocolate-type", namespace = "http://www.example.com/candies")
    private ChocolateTypes chocolate;

    public ChocolateSweet(){}

    public ChocolateSweet(String name, double energy, SweetValue value, Producers producer, ChocolateTypes chocolate) {
        super(name, energy, value, producer);
        this.chocolate = chocolate;
    }

    public ChocolateTypes getChocolate() {
        return chocolate;
    }

    public void setChocolate(ChocolateTypes chocolate) {
        this.chocolate = chocolate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ChocolateSweet that = (ChocolateSweet) o;
        return chocolate == that.chocolate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), chocolate);
    }

    @Override
    public String toString() {
        return "ChocolateSweet{" +
                " name=" + super.getName() +
                " energy=" + super.getEnergy() +
                " value=" + super.getValue() +
                " producer=" + super.getProducer() +
                " chocolate=" + chocolate +
                '}';
    }
}
