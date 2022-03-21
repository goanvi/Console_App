package Model;

public interface IdManager {

    int setID (Integer id);

    void saveID(Integer id);

    default int changeId (int id){
        return id + (int)((Math.random()*1000)+1);
    }
}