package Model;

import com.opencsv.CSVReader;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

public class Person implements IdManager{ // Помнить что этот класс может бить null, переделать конструкторы при необходимости
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.time.LocalDateTime birthday; //Поле может быть null
    private float weight; //Значение поля должно быть больше 0
    private String passportID; //Длина строки не должна быть больше 33, Значение этого поля должно быть уникальным, Поле может быть null
    private Set<Integer> idPersonBuffer = new LinkedHashSet<>();


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
        this.passportID = Integer.toString(setID(Math.abs(UUID.randomUUID().hashCode())));
        saveID(Integer.parseInt(passportID));
    }

    @Override
    public int setID(Integer id) {
        int passportID0 = id;
        while (true){
            if (idPersonBuffer.contains(passportID0)) {
                passportID0=changeId(passportID0);
            }
            else {
                break;
            }
        }
        return passportID0;
    }

    @Override
    public void saveID(Integer id) {
        idPersonBuffer.add(id);
    }

    @Override
    public String toString() {
        return  "name=" + name+
                ", birthday=" + birthday +
                ", weight=" + weight +
                ", passportID=" + passportID;
    }
}
