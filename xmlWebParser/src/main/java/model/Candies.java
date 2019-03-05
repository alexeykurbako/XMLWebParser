package model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="candies", namespace = "http://www.example.com/candies")
public class Candies {
    @XmlElementRefs(value = {
            @XmlElementRef(name="sweet", type = Sweet.class,namespace = "http://www.example.com/candies"),
            @XmlElementRef(name="filled-sweet", type = FilledSweet.class,namespace ="http://www.example.com/candies" ),
            @XmlElementRef(name="chocolate-sweet", type = ChocolateSweet.class,namespace ="http://www.example.com/candies")})
    List<Sweet> list=new ArrayList<Sweet>();

    public Candies(){}
    public List<Sweet> getList(){
        return list;
    }

}