import Controller.StudyGroupComparator;
import Controller.TreeSetCollection;
import Model.FormOfEducation;
import Model.Semester;
import Model.StudyGroup;

import java.time.LocalDateTime;
import java.util.Comparator;

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
//        try (CSVWriter writer = new CSVWriter(new FileWriter("CSV.csv", true))) {
//            for (StudyGroup st : collection) {
//                String[] record = st.toString().split(",");
//                writer.writeNext(record);
//            }
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        System.out.println(collection.toString());
    }
}
