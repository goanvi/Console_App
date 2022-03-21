package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

public class StudyGroup implements Serializable, IdManager { //Потребуется переделать конструкторы для корректной работы по ТЗ
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long studentsCount; //Значение поля должно быть больше 0
    private double averageMark; //Значение поля должно быть больше 0
    private FormOfEducation formOfEducation; //Поле может быть null
    private Semester semesterEnum; //Поле не может быть null
    private Person groupAdmin; //Поле может быть null
    private Set<Integer> idStudyGroupBuffer = new LinkedHashSet<>();

    public StudyGroup(String name, Coordinates coordinates, long studentsCount,
                      double averageMark, FormOfEducation formOfEducation, Semester semesterEnum, String adminName,
                      String adminBirthday, float adminWeight) {
        this.id = setID(Math.abs(UUID.randomUUID().hashCode()));
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDate.now();
        this.studentsCount = studentsCount;
        this.averageMark = averageMark;
        this.formOfEducation = formOfEducation;
        this.semesterEnum = semesterEnum;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy HH:mm"); //Доделать День рождения админа, придумать, как сделать запись проще
        this.groupAdmin = new Person(adminName, LocalDateTime.parse(adminBirthday, dtf), adminWeight);
        saveID(this.id);
    }

    public StudyGroup(String name, Coordinates coordinates, long studentsCount,
                      double averageMark, FormOfEducation formOfEducation, Semester semesterEnum, Person person) {
        this.id = setID(Math.abs(UUID.randomUUID().hashCode()));
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDate.now();
        this.studentsCount = studentsCount;
        this.averageMark = averageMark;
        this.formOfEducation = formOfEducation;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = person;
        saveID(this.id);
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
        if (!string[10].equals("null")) {
            if (!string[11].equalsIgnoreCase("null")) {
                this.groupAdmin = new Person(string[10],
                        LocalDateTime.parse(string[11]), Float.parseFloat(string[12]), string[13]);
            } else {
                this.groupAdmin = new Person(string[10],
                        null, Float.parseFloat(string[12]), string[13]);
            }

        } else groupAdmin = null;
    }

    @Override
    public int setID(Integer id) {
        int id0 = id;
        while (true){
            if (idStudyGroupBuffer.contains(id0)) {
                id0=changeId(id0);
            }
            else {
                break;
            }
        }
        return id0;
    }

    @Override
    public void saveID(Integer id) {
        idStudyGroupBuffer.add(id);
    }

    public long getStudentsCount() {
        return studentsCount;
    }

    public double getAverageMark() {
        return averageMark;
    }

    @Override
    public String toString() {
        String ret = "";
        if (groupAdmin == null) {
            ret = "class=" + getClass() +
                    ", id=" + id +
                    ", name=" + name +
                    ", coordinatesX=" + coordinates.getX() +
                    ", coordinatesY=" + coordinates.getY() +
                    ", creationDate=" + creationDate +
                    ", studentsCount=" + studentsCount +
                    ", averageMark=" + averageMark +
                    ", formOfEducation=" + formOfEducation +
                    ", semesterEnum=" + semesterEnum +
                    ", groupAdmin=null";
        } else {
            ret = "class=" + getClass() +
                    ", id=" + id +
                    ", name=" + name +
                    ", coordinatesX=" + coordinates.getX() +
                    ", coordinatesY=" + coordinates.getY() +
                    ", creationDate=" + creationDate +
                    ", studentsCount=" + studentsCount +
                    ", averageMark=" + averageMark +
                    ", formOfEducation=" + formOfEducation +
                    ", semesterEnum=" + semesterEnum +
                    ", groupAdmin=" + groupAdmin.toString();
        }
        return ret;
    }
}

