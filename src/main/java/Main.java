import Controller.StudyGroupComparator;
import Controller.TreeSetCollection;
import Model.Exceptions.IncorrectNameEnumException;
import Model.FormOfEducation;
import Model.Semester;
import Model.StudyGroup;
import View.ConsoleClient.ConsoleClient;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    /*Переделать реализацию задания ID в StudyGroup
    * написать остальные вспомогательные классы для классов команд
    * потребуется переписать конструкторы для StudyGroup, чтобы Person мог быть null
    * при проверке работоспособности программы отловить все оставшиеся ошибки
    * спросить про LocalDateTime и обобщить все заметки в классах здесь
    * почистить классы от мусора*/

    public static void main(String[] args) { // Доделать ДР админа
        StudyGroup group1 = new StudyGroup("P3133", 100, 50, 27,
                3.7, FormOfEducation.FULL_TIME_EDUCATION, Semester.THIRD,
                "Seva", "Friday, Mar 11, 2022 12:10", 90.3f);
        StudyGroup group2 = new StudyGroup("P3133", 100, 50, 27,
                3.8, FormOfEducation.FULL_TIME_EDUCATION, Semester.THIRD,
                "Seva", "Tuesday, Aug 16, 2016 12:10", 90.3f);
        StudyGroup group3 = new StudyGroup("P3133", 100, 50, 26,
                3.7, FormOfEducation.FULL_TIME_EDUCATION, Semester.THIRD,
                "Seva", "Tuesday, Aug 16, 2016 12:10", 90.3f);

        Comparator<StudyGroup> sgc = new StudyGroupComparator();
        TreeSetCollection<StudyGroup> collection = new TreeSetCollection<>(sgc);
        collection.add(group1);
        collection.add(group2);
        collection.add(group3);
        FileWorker fileWorker = new FileWorker("CSV.csv");
        fileWorker.writer(ParserCSV.toCSV(collection));
        String input = fileWorker.reader();
        TreeSetCollection<StudyGroup> collection1 = ParserCSV.csvFromData(input);
        System.out.println(collection);
        System.out.println(collection1);

    }


}
