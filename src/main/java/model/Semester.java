package model;

import model.Exceptions.IncorrectNameEnumException;

public enum Semester {
    THIRD("Третий"),
    FIFTH("Пятый"),
    SEVENTH("Седьмой");

    private final String name;

    Semester(String name){
        this.name =name;
    }

    public static Semester equals(String name) throws IncorrectNameEnumException {
        switch (name.toLowerCase()) {
            case "third" : return Semester.THIRD;
            case "fifth" : return Semester.FIFTH;
            case "seventh" : return Semester.SEVENTH;
            default : throw new IncorrectNameEnumException();
        }
    }

    public String getName() {
        return name;
    }
}