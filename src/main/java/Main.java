import Controller.CollectionManager;
import Controller.CommandManager;
import Controller.FileWorker;
import Controller.ParserCSV;
import Model.Exceptions.IncorrectNameEnumException;
import Model.Semester;
import Model.StudyGroup;
import View.Asker;
import View.Commands.*;
import View.ConsoleClient.ConsoleClient;

import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    /* при проверке работоспособности программы отловить все оставшиеся ошибки,
     * спросить про LocalDateTime и обобщить все заметки в классах здесь,
     * почистить классы от мусора,
     * Дописать исключения для Controller.FileWorker
     * проверить IgnoreCase в AskPerson*/

    public static void main(String[] args) {
        CollectionManager collectionManager = new CollectionManager(System.getenv("LABA5"));
        Scanner scanner = new Scanner(System.in);
        Asker asker = new Asker(scanner);
        Map<String, AbstractCommand> commandMap = new LinkedHashMap<>();
        Add add = new Add(asker,collectionManager);
        commandMap.put(add.getName(),add);
        Clear clear = new Clear(collectionManager);
        commandMap.put(clear.getName(),clear);
        Exit exit = new Exit();
        commandMap.put(exit.getName(),exit);
        FilterLessThanStudentsCount filterLessThanStudentsCount = new FilterLessThanStudentsCount(collectionManager,scanner);
        commandMap.put(filterLessThanStudentsCount.getName(),filterLessThanStudentsCount);
        Help help = new Help(commandMap);
        commandMap.put(help.getName(),help);
        History history = new History();
        commandMap.put(history.getName(),history);
        Info info = new Info(collectionManager);
        commandMap.put(info.getName(),info);
        RemoveAnyBySemesterEnum removeAnyBySemesterEnum = new RemoveAnyBySemesterEnum(collectionManager,scanner);
        commandMap.put(removeAnyBySemesterEnum.getName(),removeAnyBySemesterEnum);
        RemoveById removeById = new RemoveById(collectionManager,scanner);
        commandMap.put(removeById.getName(),removeById);
        RemoveGreater removeGreater = new RemoveGreater(collectionManager,scanner);
        commandMap.put(removeGreater.getName(), removeGreater);
        RemoveLower removeLower = new RemoveLower(collectionManager,scanner);
        commandMap.put(removeLower.getName(),removeLower);
        Save save = new Save(scanner, collectionManager);
        commandMap.put(save.getName(),save);
        Show show = new Show(collectionManager);
        commandMap.put(show.getName(),show);
        SumOfStudentsCount sumOfStudentsCount = new SumOfStudentsCount(collectionManager);
        commandMap.put(sumOfStudentsCount.getName(), sumOfStudentsCount);
        UpdateId updateId = new UpdateId(collectionManager,scanner, asker);
        commandMap.put(updateId.getName(),updateId);
        CommandManager commandManager = new CommandManager(commandMap);
        ConsoleClient consoleClient = new ConsoleClient(commandManager, scanner);
        ExecuteScript executeScript = new ExecuteScript(consoleClient, scanner);
        commandMap.put(executeScript.getName(),executeScript);
        consoleClient.interactiveMode();
//        collectionManager.loadCollection("CSV.csv");
//        Scanner scanner = new Scanner(System.in);
//        RemoveById removeById = new RemoveById(collectionManager, scanner);
//        removeById.execute("");
//        try {
//        FileWorker fileWorker = new FileWorker("CSV.csv");
//        ParserCSV parser = new ParserCSV();
//        CollectionManager collectionManager = new CollectionManager(fileWorker);
//        TreeSet<StudyGroup> coll = parser.csvFromData(fileWorker.reader());
//        collectionManager.addCollection(coll);
//        Scanner scanner = new Scanner(System.in);
//        System.out.println(scanner.nextLine());
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
//            collection.addCommand(group11);
//            collection.addCommand(group12);
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

//        collection.addCommand(group1);
//        collection.addCommand(group2);
//        collection.addCommand(group3);
//        Controller.FileWorker fileWorker = new Controller.FileWorker("CSV.csv");
//        fileWorker.writer(Controller.ParserCSV.toCSV(collection));
//        String input = fileWorker.reader();
//        TreeSetCollection<StudyGroup> collection1 = Controller.ParserCSV.csvFromData(input);
//        System.out.println(collection);
//        System.out.println(collection1);

    }


}
