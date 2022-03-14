package Model;

import Model.Exceptions.IncorrectNameEnumException;

public enum Semester {
    THIRD,
    FIFTH,
    SEVENTH;
    public static Semester equals(String name) throws IncorrectNameEnumException {
        return switch (name.toLowerCase()) {
            case "third" -> Semester.THIRD;
            case "fifth" -> Semester.FIFTH;
            case "seventh" -> Semester.SEVENTH;
            default -> throw new IncorrectNameEnumException();
        };
    }
}
