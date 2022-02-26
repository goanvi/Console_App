import Controller.StudyGroupComparator;
import Controller.TreeSetCollection;
import Model.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParserCSV {
    public static <T> String toCSV(Collection<T> collection) {
        StringBuilder stringBuilder = new StringBuilder("");
        Object[] collArray = collection.toArray();
        String[] header = makeHeader(collArray[0]);
        stringBuilder.append(Arrays.toString(header).replace("[","").replace("]",""));
        stringBuilder.append("\n");
        for (Object obj : collArray) {
            stringBuilder.append(Arrays.toString(makeData(obj)).replace("[","").replace("]",""));
            stringBuilder.append("\n");
        }
        return String.valueOf(stringBuilder);
    }

//    public static TreeSetCollection<StudyGroup> csvFromData (String string){
//        Comparator<StudyGroup> sgc = new StudyGroupComparator(); //Будут использовать в main, добовление коллекции через addAll
//        TreeSetCollection<StudyGroup> collection = new TreeSetCollection<>(sgc);
//        String[] bigData = string.split("\n");
//        //String[] header = bigData[0].split(",");
//        for (int i = 1; i< bigData.length; i++ ){
//            String[] data = bigData[i].replace(" ","").split(",");
//            collection.add(new StudyGroup(data));
//        }
//        return collection;
//    }

//    public static  <T extends CreateObjectFromString> TreeSetCollection<T>  csvFromData (String string){
//        TreeSetCollection<T> collection = new TreeSetCollection<>();
//        String[] bigData = string.split("\n");
//        Constructor<T> constructor
//                = T.class.getDeclaredConstructor();
//        for (int i = 1; i< bigData.length; i++ ){
//            String[] data = bigData[i].replace(" ","").split(",");
//            collection.add(createObject(T.class.getDeclaredConstructor(), fromStudyGroup(data)));
//        }
//        return collection;
//    }
//
//    private static <T> Object createObject(Constructor<T> constructor,
//                                      Object[] arguments) {
//
//        System.out.println("Constructor: " + constructor.toString());
//        Object object = null;
//
//        try {
//            object = constructor.newInstance(arguments);
//            System.out.println("Object: " + object.toString());
//            return object;
//        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException e) {
//            //handle it
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        return object;
//    }
//
//    private static Object[] fromStudyGroup (String[] string){
//        Object[] obj = new Object[10];
//        obj[0]= string[0];
//        obj[1]= Integer.parseInt(string[1]);
//        obj[2]= string[2];
//        obj[3]= new Coordinates(Integer.parseInt(string[3]), Integer.parseInt(string[4]));
//        obj[4]= LocalDate.parse(string[5]);
//        obj[5]= Long.parseLong(string[6]);
//        obj[6]= Double.parseDouble(string[7]);
//        obj[7]= FormOfEducation.valueOf(string[8]);
//        obj[8]= Semester.valueOf(string[9]);
//        obj[9]= new Person(string[10], LocalDateTime.parse(string[11]), Float.parseFloat(string[12]), string[13]);
//        return obj;
//    }



    private static String[] makeHeader(Object object) {
        String[] recordHd = object.toString().split(",");
        String[] header = new String[recordHd.length];
        for (int i = 0; i < recordHd.length; i++) {
            String[] sword = recordHd[i].split("=");
            header[i] = sword[0];
        }
        return header;
    }

    private static String[] makeData(Object object) {
        String[] recordDt = object.toString().split(",");
        String[] data = new String[recordDt.length];
        for (int i = 0; i < recordDt.length; i++) {
            String[] sword = recordDt[i].split("=");
            data[i] = sword[sword.length -1];
        }
        return data;
    }

}
