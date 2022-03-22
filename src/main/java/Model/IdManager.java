package Model;

import java.util.LinkedHashSet;
import java.util.Set;

public class IdManager {
    private static Set<Integer> idStudyGroupBuffer = new LinkedHashSet<>();
    private static Set<Integer> idPersonBuffer = new LinkedHashSet<>();

    public static int setStudyGroupID (Integer id){
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

    public static int setPersonID(Integer id){
        int passportID0 = id;
        while (true){
            if (idPersonBuffer.contains(passportID0)) {
                passportID0=changeId(passportID0);
            }
            else {
                break;
            }
        }
        return passportID0;
    }

    public static void removePersonID(Integer id){
        idPersonBuffer.remove(id);
    }

    public static void savePersonID(Integer id){
        idPersonBuffer.add(id);
    }


    public static void saveStudyGroupID(Integer id){
        idStudyGroupBuffer.add(id);
    }
    public static void removeStudyGroupID(Integer id){
        idStudyGroupBuffer.remove(id);
    }


    public static int changeId (int id){
        return id + (int)((Math.random()*1000)+1);
    }
}