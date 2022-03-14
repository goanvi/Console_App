package Model;

import com.opencsv.CSVReader;

import java.time.LocalDateTime;
import java.util.UUID;

public class Person{ // Помнить что этот класс может бить null, переделать конструкторы при необходимости
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.time.LocalDateTime birthday; //Поле может быть null
    private float weight; //Значение поля должно быть больше 0
    private String passportID; //Длина строки не должна быть больше 33, Значение этого поля должно быть уникальным, Поле может быть null

    public Person(String name, LocalDateTime birthday, float weight, String passportID) {
        this.name = name;
        this.birthday = birthday;
        this.weight = weight;
        this.passportID = passportID;
    }
    public Person(String name, LocalDateTime birthday, float weight) {
        this.name = name;
        this.birthday = birthday;
        this.weight = weight;
        this.passportID = Integer.toString(Math.abs(UUID.randomUUID().hashCode()));
    }

    @Override
    public String toString() {
        return  "name=" + name+
                ", birthday=" + birthday +
                ", weight=" + weight +
                ", passportID=" + passportID;
    }
}
