package Model;

import Model.Exceptions.IncorrectNameEnumException;

public enum Semester {
    THIRD,
    FIFTH,
    SEVENTH;
    public static Semester equals(String name) throws IncorrectNameEnumException {
        switch (name.toLowerCase()) {
            case "third" : return Semester.THIRD;
            case "fifth" : return Semester.FIFTH;
            case "seventh" : return Semester.SEVENTH;
            default : throw new IncorrectNameEnumException();
        }
    }
}