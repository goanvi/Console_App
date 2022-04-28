package model;

import model.Exceptions.IncorrectNameEnumException;

public enum FormOfEducation {
    DISTANCE_EDUCATION("Дистанционно"),
    FULL_TIME_EDUCATION("Очно"),
    EVENING_CLASSES("Вечер");

    private final String name;

    FormOfEducation (String name){
        this.name = name;
    }

    public static FormOfEducation convert(String name) throws IncorrectNameEnumException {
        switch (name.toLowerCase()) {
            case "дистанционно": return FormOfEducation.DISTANCE_EDUCATION;
            case "очно": return FormOfEducation.FULL_TIME_EDUCATION;
            case "вечер": return FormOfEducation.EVENING_CLASSES;
            default: throw new IncorrectNameEnumException();
        }
    }

    public String getName() {
        return name;
    }
}
