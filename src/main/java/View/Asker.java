package View;

import Model.*;
import Model.Exceptions.*;
import View.ConsoleClient.ConsoleClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.UUID;

public class Asker { // Кажется закончил, останется только дописать геттеры и сеттеры по необходимости, проверить все exceptions
    private Scanner userScan;
    private static boolean fileMode;
    private final int MAX_COORD_X = 811;
    private final int MIN_STUDENTS_COUNT = 0;
    private final int MIN_AVERAGE_MARK = 0;
    private final int MIN_WEIGHT_ADMIN = 0;
    private boolean personExist;

    public Asker(Scanner usr) {
        this.userScan = usr;
        fileMode = false;
        personExist = true;
    }


    public String askName() throws IncorrectScriptException {
        String name;
        while (true) {
            try {
                ConsoleClient.println("Введите название группы");
                name = userScan.nextLine().trim();
                if (fileMode) ConsoleClient.println(name);
                if (name.equals("")||name.equalsIgnoreCase("null"))throw new CannotBeNullException();
                break;
            } catch (CannotBeNullException exception) {
                ConsoleClient.printError(exception.getMessage());
                if (fileMode) throw new IncorrectScriptException();
            } catch (NoSuchElementException exception){
                ConsoleClient.printError("Значение поля не распознано!");
                if (fileMode) throw new IncorrectScriptException();
            } catch (IllegalStateException exception) {
                ConsoleClient.printError("Непредвиденная ошибка!");
                System.exit(0);
            }

        }
        return name;
    }

    public Integer askCoordinateX() throws IncorrectScriptException {
        String strX;
        Integer x;
        while (true) {
            try {
                ConsoleClient.println("Введите координату X.");
                ConsoleClient.println("Максимальное значение координаты X = " + MAX_COORD_X);
                strX = userScan.nextLine().trim();
                if (fileMode) ConsoleClient.println(strX);
                if (strX.equals("")) throw new CannotBeNullException();
                x = Integer.parseInt(strX);
                if (x > MAX_COORD_X) throw new GoingBeyondLimitsException();
                break;
            }catch (CannotBeNullException exception) {
                ConsoleClient.printError(exception.getMessage());
                if (fileMode) throw new IncorrectScriptException();
            }catch (GoingBeyondLimitsException exception){
                ConsoleClient.printError(exception.getMessage());
                if (fileMode) throw new IncorrectScriptException();
            }catch (NumberFormatException exception){
                ConsoleClient.printError("Значением поля должно являться число!");
                if (fileMode) throw new IncorrectScriptException();
            } catch (IllegalStateException exception){
                ConsoleClient.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return x;
    }

    public int askCoordinateY() throws IncorrectScriptException {
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
            catch (NumberFormatException exception){
                ConsoleClient.printError("Значением поля должно являться число!");
                if (fileMode) throw new IncorrectScriptException();
            } catch (IllegalStateException exception){
                ConsoleClient.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return y;
    }

    public Coordinates askCoordinates() throws IncorrectScriptException{
        Integer x = askCoordinateX();
        int y = askCoordinateY();
        return new Coordinates(x,y);
    }

    public long askStudentsCount() throws IncorrectScriptException{
        String srtStud;
        long stud;
        while (true){
            try {
                ConsoleClient.println("Введите количество студентов в группе.");
                ConsoleClient.println("Значение должно быть больше " + MIN_STUDENTS_COUNT);
                srtStud = userScan.nextLine().trim();
                if (fileMode) ConsoleClient.println(srtStud);
                stud = Long.parseLong(srtStud);
                if (stud<=0) throw new GoingBeyondLimitsException();
                break;
            }catch (GoingBeyondLimitsException exception){
                ConsoleClient.printError(exception.getMessage());
                if (fileMode) throw new IncorrectScriptException();
            }catch (NumberFormatException exception){
                ConsoleClient.printError("Значением поля должно являться число!");
                if (fileMode) throw new IncorrectScriptException();
            } catch (IllegalStateException exception){
                ConsoleClient.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return stud;
    }

    public double askAverageMark() throws IncorrectScriptException{
        String strMark;
        double mark;
        while (true){
            try {
                ConsoleClient.println("Введите среднюю оценку в группе.");
                ConsoleClient.println("Значение должно быть больше " + MIN_AVERAGE_MARK);
                strMark = userScan.nextLine().trim();
                if (fileMode) ConsoleClient.println(strMark);
                mark = Double.parseDouble(strMark);
                if (mark<=0) throw new GoingBeyondLimitsException();
                break;
            }catch (GoingBeyondLimitsException exception){
                ConsoleClient.printError(exception.getMessage());
                if (fileMode) throw new IncorrectScriptException();
            }catch (NumberFormatException exception){
                ConsoleClient.printError("Значением поля должно являться число!");
                if (fileMode) throw new IncorrectScriptException();
            } catch (IllegalStateException exception){
                ConsoleClient.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return mark;
    }

    public FormOfEducation askFromOfEducation() throws IncorrectScriptException{
        String strEduc;
        FormOfEducation educ;
        while (true){
            try {
                ConsoleClient.println("Введите форму обучения");
                ConsoleClient.println("Доступные формы обучения: Distance, Full time, Evening");
                strEduc = userScan.nextLine().trim();
                if(strEduc.equals("")) throw new CannotBeNullException();
                if (fileMode) ConsoleClient.println(strEduc);
                educ = FormOfEducation.equals(strEduc);
                break;
            }catch (IncorrectNameEnumException exception){
                ConsoleClient.printError("Введено неправильное имя формы обучения!");
                if (fileMode) throw new IncorrectScriptException();
            }catch (CannotBeNullException exception) {
                ConsoleClient.printError(exception.getMessage());
                if (fileMode) throw new IncorrectScriptException();
            }catch (IllegalStateException exception){
                ConsoleClient.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return educ;
    }

    public Semester askSemester() throws IncorrectScriptException{
        String strSem;
        Semester sem;
        while (true){
            try {
                ConsoleClient.println("Введите семестр обучения");
                ConsoleClient.println("Доступные семестры обучения: Third, Fifth, Seventh");
                strSem = userScan.nextLine().trim();
                if(strSem.equals("")) throw new CannotBeNullException();
                if (fileMode) ConsoleClient.println(strSem);
                sem= Semester.equals(strSem);
                break;
            }catch (IncorrectNameEnumException exception){
                ConsoleClient.printError("Введено неправильное имя семестра обучения!");
                if (fileMode) throw new IncorrectScriptException();
            }catch (CannotBeNullException exception) {
                ConsoleClient.printError(exception.getMessage());
                if (fileMode) throw new IncorrectScriptException();
            }catch (IllegalStateException exception){
                ConsoleClient.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return sem;
    }

    public String askAdminName() throws IncorrectScriptException{
        String admin;
        while (true){
            try {
                ConsoleClient.println("Введите имя админа");
                admin = userScan.nextLine().trim();
                if (admin.equals("")) throw new CannotBeNullException();
                if (fileMode) ConsoleClient.println(admin);
                break;
            }catch (NoSuchElementException exception){
                ConsoleClient.printError("Значение поля не распознано!");
                if (fileMode) throw new IncorrectScriptException();
            }catch (CannotBeNullException exception){
                ConsoleClient.printError(exception.getMessage());
                if (fileMode) throw new IncorrectScriptException();
            }catch (IllegalStateException exception){
                ConsoleClient.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return admin;
    }

    public LocalDateTime askAdminBirthday() throws IncorrectScriptException{
        String strTime;
        LocalDateTime time;
        while (true){
            try {
                ConsoleClient.println("Введите день рождения админа.");
                ConsoleClient.println("Шаблон ввода: день недели, месяц день, год час:минута");
                ConsoleClient.println("Пример ввода: Friday, Mar 11, 2022 12:10");
                strTime = userScan.nextLine().trim();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy HH:mm");
                if (strTime.equals("")) time = null;
                else time = LocalDateTime.parse(strTime,dtf);
                if (fileMode) ConsoleClient.println(strTime);
                break;
            }
            catch (DateTimeParseException exception){ //Обработать исключения
                ConsoleClient.printError("Некорректный ввод даты рождения!");
                ConsoleClient.println("Обратите внимание на ввод дня недели и месяца с заглавной буквы и сокращение названия месяца 3 буквами!");
                if (fileMode) throw new IncorrectScriptException();
            }catch (IllegalStateException exception){
                ConsoleClient.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return time;
    }

    public float askAdminWeight()throws IncorrectScriptException{
        String strWeight;
        float weight;
        while (true){
            try {
                ConsoleClient.println("Введите вес админа");
                ConsoleClient.println("Значение поля должно быть больше " + MIN_WEIGHT_ADMIN);
                strWeight = userScan.nextLine().trim();
                if (fileMode) ConsoleClient.println(strWeight);
                weight = Float.parseFloat(strWeight);
                if (weight<=0f) throw new GoingBeyondLimitsException();
                break;
            }catch (NumberFormatException exception){
                ConsoleClient.printError("Значением поля должно являться число!");
                if (fileMode) throw new IncorrectScriptException();
            }catch (GoingBeyondLimitsException exception){
                ConsoleClient.printError(exception.getMessage());
                if (fileMode) throw new IncorrectScriptException();
            }catch (IllegalStateException exception){
                ConsoleClient.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return weight;
    }

    public Person askPerson() throws IncorrectScriptException{
        Person person = null;
        while (true) {
            try {
                ConsoleClient.println("Вы хотите указать админа группы? Y/N");
                String answer = userScan.nextLine().trim();
                if (answer.equalsIgnoreCase("Y")) { // поставить LowerCase
                    setPersonExists();
                    String adminName = askAdminName();
                    LocalDateTime adminBirthday = askAdminBirthday();
                    float adminWeight = askAdminWeight();
                    String adminID = Integer.toString(Math.abs(UUID.randomUUID().hashCode()));
                    person = new Person(adminName, adminBirthday, adminWeight, adminID);
                }
                else if (answer.equalsIgnoreCase("N")) {
                    setPersonDoesntExist();
                }
                else throw new IncorrectInputException();
                break;
            }catch (IncorrectInputException exception) {
                ConsoleClient.printError("Некорректный ввод ответа!");
                ConsoleClient.println("Введите Y, если согласный и N, если не согласны!");
            }catch (IllegalStateException exception){
                ConsoleClient.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return person;
    }

    public static void setFileMode() {
        fileMode = true;
    }


    public static void setUserMode() {
        fileMode = false;
    }


    public boolean getPersonExist() {
        return personExist;
    }

    public void setPersonExists(){
        personExist = true;
    }

    public void setPersonDoesntExist(){
        personExist = false;
    }
}
