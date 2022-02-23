package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class StudyGroup implements Serializable {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long studentsCount; //Значение поля должно быть больше 0
    private double averageMark; //Значение поля должно быть больше 0
    private FormOfEducation formOfEducation; //Поле может быть null
    private Semester semesterEnum; //Поле не может быть null
    private Person groupAdmin; //Поле может быть null

    public StudyGroup(String name, int coordinatesX, int coordinatesY, long studentsCount,
                      double averageMark, FormOfEducation formOfEducation, Semester semesterEnum, String adminName,
                      java.time.LocalDateTime adminBirthday, float adminWeight, String adminPassportID) {
        this.id = Math.abs(UUID.randomUUID().hashCode());
        this.name = name;
        this.coordinates = new Coordinates(coordinatesX, coordinatesY);
        this.creationDate = LocalDate.now();
        this.studentsCount = studentsCount;
        this.averageMark = averageMark;
        this.formOfEducation = formOfEducation;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = new Person(adminName, adminBirthday, adminWeight, adminPassportID);
    }
    public StudyGroup(String name, int coordinatesX, int coordinatesY, long studentsCount,
                      double averageMark, FormOfEducation formOfEducation, Semester semesterEnum,Person person) {
        this.id = Math.abs(UUID.randomUUID().hashCode());
        this.name = name;
        this.coordinates = new Coordinates(coordinatesX, coordinatesY);
        this.creationDate = LocalDate.now();
        this.studentsCount = studentsCount;
        this.averageMark = averageMark;
        this.formOfEducation = formOfEducation;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = person;
    }

    public long getStudentsCount() {
        return studentsCount;
    }

    public double getAverageMark() {
        return averageMark;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", name=" + name  +
                ", coordinatesX=" + coordinates.getX() +
                ", coordinatesY=" + coordinates.getY() +
                ", creationDate=" + creationDate +
                ", studentsCount=" + studentsCount +
                ", averageMark=" + averageMark +
                ", formOfEducation=" + formOfEducation +
                ", semesterEnum=" + semesterEnum +
                ", groupAdmin=" + groupAdmin.toString();
    }
}

