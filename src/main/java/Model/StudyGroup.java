package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.UUID;

public class StudyGroup implements Serializable,CreateObjectFromString{
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
                      String adminBirthday, float adminWeight) {
        this.id = Math.abs(UUID.randomUUID().hashCode());
        this.name = name;
        this.coordinates = new Coordinates(coordinatesX, coordinatesY);
        this.creationDate = LocalDate.now();
        this.studentsCount = studentsCount;
        this.averageMark = averageMark;
        this.formOfEducation = formOfEducation;
        this.semesterEnum = semesterEnum;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy HH:mm"); //Доделать День рождения админа, придумать, как сделать запись проще
        this.groupAdmin = new Person(adminName, LocalDateTime.parse(adminBirthday, dtf), adminWeight, Integer.toString(Math.abs(UUID.randomUUID().hashCode())));
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

    public StudyGroup(String[] string) {
        this.id = Integer.parseInt(string[1]);
        this.name = string[2];
        this.coordinates = new Coordinates(Integer.parseInt(string[3]), Integer.parseInt(string[4]));
        this.creationDate = LocalDate.parse(string[5]);
        this.studentsCount = Long.parseLong(string[6]);
        this.averageMark = Double.parseDouble(string[7]);
        this.formOfEducation = FormOfEducation.valueOf(string[8]);
        this.semesterEnum = Semester.valueOf(string[9]);
        this.groupAdmin = new Person(string[10], LocalDateTime.parse(string[11]), Float.parseFloat(string[12]), string[13]);
    }

    @Override
    public Object createObjectFromString(String[] strings) {
        return new StudyGroup(strings);
    }

    public long getStudentsCount() {
        return studentsCount;
    }

    public double getAverageMark() {
        return averageMark;
    }

    @Override
    public String toString() {
        return  "class=" + getClass() +
                ", id=" + id +
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

