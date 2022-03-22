import Controller.CollectionManager;
import Controller.FileWorker;
import Controller.ParserCSV;
import Model.Exceptions.IncorrectNameEnumException;
import Model.Semester;
import Model.StudyGroup;
import View.ConsoleClient.ConsoleClient;

import java.util.TreeSet;

public class Main {

    /*переписать IdManager, коллекция должна быть одна на все объекты,
    * написать остальные вспомогательные классы для классов команд,
    * при проверке работоспособности программы отловить все оставшиеся ошибки,
    * спросить про LocalDateTime и обобщить все заметки в классах здесь,
    * почистить классы от мусора,
    * Дописать исключения для Controller.FileWorker */

    public static void main(String[] args) { // Доделать ДР админа
//        try {

        FileWorker fileWorker = new FileWorker("CSV.csv");
        ParserCSV parser = new ParserCSV();
        CollectionManager collectionManager = new CollectionManager(fileWorker);
        TreeSet<StudyGroup> coll = parser.csvFromData(fileWorker.reader());
        collectionManager.addCollection(coll);
            //System.out.println(collectionManager.removeGreater());
//        }
//        catch (IncorrectNameEnumException exception){
//            ConsoleClient.printError("gg");
//        }
//        StudyGroup group1 = new StudyGroup("P3133", 100, 50, 27,
//                3.7, FormOfEducation.FULL_TIME_EDUCATION, Semester.THIRD,
//                "Seva", "Friday, Mar 11, 2022 12:10", 90.3f);
//        StudyGroup group2 = new StudyGroup("P3133", 100, 50, 27,
//                3.8, FormOfEducation.FULL_TIME_EDUCATION, Semester.THIRD,
//                "Seva", "Tuesday, Aug 16, 2016 12:10", 90.3f);
//        StudyGroup group3 = new StudyGroup("P3133", 100, 50, 26,
//                3.7, FormOfEducation.FULL_TIME_EDUCATION, Semester.THIRD,
//                "Seva", "Tuesday, Aug 16, 2016 12:10", 90.3f);
//        try {
//            Comparator<StudyGroup> sgc = new StudyGroupComparator();
//            TreeSetCollection<StudyGroup> collection = new TreeSetCollection<>(sgc);
//            Asker asker = new Asker(new Scanner(System.in));
//            StudyGroup group11 = new StudyGroup(asker.askName(),asker.askCoordinates(),
//                    asker.askStudentsCount(),asker.askAverageMark(),asker.askFromOfEducation(),asker.askSemester(),asker.askPerson());
//            StudyGroup group12 = new StudyGroup(asker.askName(),asker.askCoordinates(),
//                    asker.askStudentsCount(),asker.askAverageMark(),asker.askFromOfEducation(),asker.askSemester(),asker.askPerson());
//            System.out.println(group11);
//            System.out.println(group12);
//            collection.add(group11);
//            collection.add(group12);
//            Controller.FileWorker fileWorker = new Controller.FileWorker("CSV.csv");
//            fileWorker.writer(Controller.ParserCSV.toCSV(collection));
//            String input = fileWorker.reader();
//            TreeSetCollection<StudyGroup> collection1 = Controller.ParserCSV.csvFromData(input);
//            System.out.println(collection);
//            System.out.println(collection1);
//        } catch (IncorrectScriptException e) {
//            ConsoleClient.printError("gg");
//        }

//        LocalDateTime localDateTime;
//        String time = "                    Saturday, Mar 19, 2022 17:25";
//        String time1 = "Friday, Mar 11, 2022 12:10";
//        System.out.println(time);
//        System.out.println(time.trim());
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy HH:mm");
//        localDateTime = LocalDateTime.parse(time.trim(),dtf);
//        System.out.println(localDateTime);

//        collection.add(group1);
//        collection.add(group2);
//        collection.add(group3);
//        Controller.FileWorker fileWorker = new Controller.FileWorker("CSV.csv");
//        fileWorker.writer(Controller.ParserCSV.toCSV(collection));
//        String input = fileWorker.reader();
//        TreeSetCollection<StudyGroup> collection1 = Controller.ParserCSV.csvFromData(input);
//        System.out.println(collection);
//        System.out.println(collection1);

    }


}
