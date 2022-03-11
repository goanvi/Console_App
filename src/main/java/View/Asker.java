package View;

import Model.*;
import View.ConsoleClient.ConsoleClient;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.UUID;

public class Asker { // Проверить все ограничения на переменные, написать исключения для каждого метода
    private Scanner userScan;
    private boolean fileMode;
    private final int MAX_COORD_X = 811;

    public Asker(Scanner usr) {
        this.userScan = usr;
        fileMode = false;
    }

    public String askName() { //Написать исключения
        String name;
        while (true) {
            try {
                ConsoleClient.println("Введите имя");
                name = userScan.nextLine().trim();
                if (fileMode) ConsoleClient.println(name);
                /*if (name.equals("")) Написать исключения*/
                break;
            } catch (Exception ex) { //Обработать исключения
                System.out.println(ex.getMessage());
            }
        }
        return name;
    }

    public Integer askCoordinateX() { //Написать исключения
        String strX;
        Integer x;
        while (true) {
            try {
                ConsoleClient.println("Введите координату X");
                strX = userScan.nextLine().trim();
                if (fileMode) ConsoleClient.println(strX);
                /*if (strX.equals("")) Написать исключения*/
                x = Integer.parseInt(strX);
                /*if (x > MAX_COORD_X) Написать исключения*/
                break;
            } catch (Exception ex) { //Обработать исключения
                System.out.println(ex.getMessage());
            }
        }
        return x;
    }

    public int askCoordinateY() { //Написать исключения (?)
        String strY;
        int y;
        while (true) {
            try {
                ConsoleClient.println("Введите координату Y");
                strY = userScan.nextLine().trim();
                if (fileMode) ConsoleClient.println(strY);
                y = Integer.parseInt(strY);
                break;
            }
            catch (Exception ex){ //Обработать исключения (?)
                System.out.println(ex.getMessage());
            }
        }
        return y;
    }

    public Coordinates askCoordinates(){ //Написать исключения (?)
        Integer x = askCoordinateX();
        int y = askCoordinateY();
        return new Coordinates(x,y);
    }

    public long askStudentsCount(){ //Написать исключения
        String srtStud;
        long stud;
        while (true){
            try {
                ConsoleClient.println("Введите количество студентов в группе");
                srtStud = userScan.nextLine().trim();
                if (fileMode) ConsoleClient.println(srtStud);
                stud = Long.parseLong(srtStud);
                /*if (stud<=0) Написать исключения */
                break;
            }
            catch (Exception ex){ //Обработать исключения
                System.out.println(ex.getMessage());
            }
        }
        return stud;
    }

    public double askAverageMark(){ //Написать исключения
        String strMark;
        double mark;
        while (true){
            try {
                ConsoleClient.println("Введите среднюю оценку в группе");
                strMark = userScan.nextLine().trim();
                if (fileMode) ConsoleClient.println(strMark);
                mark = Double.parseDouble(strMark);
                /*if (mark<=0) Написать исключения */
                break;
            }
            catch (Exception ex){ //Обработать исключения
                System.out.println(ex.getMessage());
            }
        }
        return mark;
    }

    public FormOfEducation askFromOfEducation(){ //Написать исключения
        String strEduc;
        FormOfEducation educ;
        while (true){
            try {
                ConsoleClient.println("Введите форму обучения");
                strEduc = userScan.nextLine().trim();
                /*if(strEduc.equals("")) Написать исключения */
                if (fileMode) ConsoleClient.println(strEduc);
                educ = FormOfEducation.valueOf(strEduc); // Стоит добавить enum имя и реализовывать через них
                break;
            }
            catch (Exception ex){ //Обработать исключения
                System.out.println(ex.getMessage());
            }
        }
        return educ;
    }

    public Semester askSemester(){ //Написать исключения
        String strSem;
        Semester sem;
        while (true){
            try {
                ConsoleClient.println("Введите семестр обучения");
                strSem = userScan.nextLine().trim();
                /*if(strEduc.equals("")) Написать исключения */
                if (fileMode) ConsoleClient.println(strSem);
                sem= Semester.valueOf(strSem); // Стоит добавить enum имя и реализовывать через них
                break;
            }
            catch (Exception ex){ //Обработать исключения
                System.out.println(ex.getMessage());
            }
        }
        return sem;
    }

    public String askAdminName(){ //Написать исключения
        String admin;
        while (true){
            try {
                ConsoleClient.println("Введите имя админа");
                admin = userScan.nextLine().trim();
                if (fileMode) ConsoleClient.println(admin);
                break;
            }
            catch (Exception ex){ //Обработать исключения
                System.out.println(ex.getMessage());
            }
        }
        return admin;
    }

    public LocalDateTime askAdminBirthday(){ //Написать исключения
        String strTime;
        LocalDateTime time;
        while (true){
            try {
                ConsoleClient.println("Введите день рождения админа: ГГГГ-ММ-ДД");
                strTime = userScan.nextLine().trim(); // переделать в StudyGroup способ задания дня рождения
                if (fileMode) ConsoleClient.println(strTime);
                time = LocalDateTime.parse(strTime);
                break;
            }
            catch (Exception ex){ //Обработать исключения
                System.out.println(ex.getMessage());
            }
        }
        return time;
    }

    public float askAdminWeight(){//Написать исключения
        String strWeight;
        float weight;
        while (true){
            try {
                ConsoleClient.println("Введите вес админа");
                strWeight = userScan.nextLine().trim();
                if (fileMode) ConsoleClient.println(strWeight); //Переделать реализацию задания ID в StudyGroup
                weight = Float.parseFloat(strWeight);
                break;
            }
            catch (Exception ex){ //Обработать исключения
                System.out.println(ex.getMessage());
            }
        }
        return weight;
    }

    public Person askPerson(){
        String adminName = askAdminName();
        LocalDateTime adminBirthday = askAdminBirthday();
        float adminWeight = askAdminWeight();
        String adminID = Integer.toString(Math.abs(UUID.randomUUID().hashCode()));
        return new Person(adminName,adminBirthday,adminWeight,adminID);
    }
}
