package Model;

import Model.Exceptions.IncorrectNameEnumException;
import Model.Exceptions.IncorrectScriptException;

import java.util.Locale;

public enum FormOfEducation {
    DISTANCE_EDUCATION("Distance"),
    FULL_TIME_EDUCATION("Full time"),
    EVENING_CLASSES("Evening");

    private final String name;

    FormOfEducation (String name){
        this.name = name;
    }

    public static FormOfEducation convert(String name) throws IncorrectNameEnumException {
        return switch (name.toLowerCase()) {
            case "distance" -> FormOfEducation.DISTANCE_EDUCATION;
            case "full time" -> FormOfEducation.FULL_TIME_EDUCATION;
            case "evening" -> FormOfEducation.EVENING_CLASSES;
            default -> throw new IncorrectNameEnumException();
        };
    }
}
