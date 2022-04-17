package View.Utility;

import Model.StudyGroup;

import java.time.format.DateTimeFormatter;

public class Formatter {
     public static String format(StudyGroup group){
         if (group.getGroupAdmin() == null)
             return "идентификатор: " + group.getID() +
                 "\nимя: " + group.getName()+
                 "\nкоординаты: "+
                 "\n    X: " + group.getCoordinates().getX() +
                 "\n    Y: " + group.getCoordinates().getY() +
                 "\nдата создания: " + group.getCreationDate() +
                 "\nколичество учеников: " + group.getStudentsCount() +
                 "\nсредняя оценка: " + group.getAverageMark() +
                 "\nформа обучения: " + group.getFormOfEducation() +
                 "\nсеместр обучения: " + group.getSemesterEnum() + "\n";

         else
             return "идентификатор: " + group.getID() +
                 "\nимя: " + group.getName()+
                 "\nкоординаты: "+
                 "\n    X: " + group.getCoordinates().getX() +
                 "\n    Y: " + group.getCoordinates().getY() +
                 "\nдата создания: " + group.getCreationDate() +
                 "\nколичество учеников: " + group.getStudentsCount() +
                 "\nсредняя оценка: " + group.getAverageMark() +
                 "\nформа обучения: " + group.getFormOfEducation() +
                 "\nсеместр обучения: " + group.getSemesterEnum() +
                 "\nадмин группы: " +
                 "\n    идентификатор: " + group.getGroupAdmin().getPassportID() +
                 "\n    имя: " + group.getGroupAdmin().getName() +
                 "\n    день рождения: " + group.getGroupAdmin().getBirthday().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) +
                 "\n    вес: " + group.getGroupAdmin().getWeight() + "\n";
     }
}
