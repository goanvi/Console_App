package model;

import model.Exceptions.IncorrectNameEnumException;

public enum FormOfEducation {
    DISTANCE_EDUCATION("Дистанционная"),
    FULL_TIME_EDUCATION("Очная"),
    EVENING_CLASSES("Вечерняя");

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

    public String getName() {
        return name;
    }
}
