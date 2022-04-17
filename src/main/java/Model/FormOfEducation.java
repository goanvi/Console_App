package Model;

import Model.Exceptions.IncorrectNameEnumException;

public enum FormOfEducation {
    DISTANCE_EDUCATION("Distance"),
    FULL_TIME_EDUCATION("Full time"),
    EVENING_CLASSES("Evening");

    private final String name;

    FormOfEducation (String name){
        this.name = name;
    }

    public static FormOfEducation convert(String name) throws IncorrectNameEnumException {
        switch (name.toLowerCase()) {
            case "distance": return FormOfEducation.DISTANCE_EDUCATION;
            case "full time": return FormOfEducation.FULL_TIME_EDUCATION;
            case "evening": return FormOfEducation.EVENING_CLASSES;
            default: throw new IncorrectNameEnumException();
        }
    }
}
