import Controller.StudyGroupComparator;
import Controller.TreeSetCollection;
import Model.FormOfEducation;
import Model.Semester;
import Model.StudyGroup;

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
    public static void main(String[] args) {
        StudyGroup group1 = new StudyGroup("P3133", 100, 50, 27,
                3.7, FormOfEducation.FULL_TIME_EDUCATION, Semester.THIRD,
                "Seva", LocalDateTime.now(), 90.3f, "336497");
        StudyGroup group2 = new StudyGroup("P3133", 100, 50, 27,
                3.8, FormOfEducation.FULL_TIME_EDUCATION, Semester.THIRD,
                "Seva", LocalDateTime.now(), 90.3f, "336497");
        StudyGroup group3 = new StudyGroup("P3133", 100, 50, 26,
                3.7, FormOfEducation.FULL_TIME_EDUCATION, Semester.THIRD,
                "Seva", LocalDateTime.now(), 90.3f, "336497");

        Comparator<StudyGroup> sgc = new StudyGroupComparator();
        TreeSetCollection<StudyGroup> collection = new TreeSetCollection<>(sgc);
        collection.add(group1);
        collection.add(group2);
        collection.add(group3);
        FileWorker fileWorker = new FileWorker("CSV.csv");
        fileWorker.writer(ParserCSV.toCSV(collection));
        String input = fileWorker.reader();
//        TreeSetCollection<StudyGroup> collection1 = ParserCSV.csvFromData(input);
        System.out.println(collection);
//        System.out.println(collection1);


    }


}
