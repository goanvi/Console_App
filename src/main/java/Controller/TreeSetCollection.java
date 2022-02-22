package Controller;

import java.util.Comparator;
import java.util.TreeSet;

public class TreeSetCollection<T> extends TreeSet<T> {
    public TreeSetCollection(Comparator comparator){
        super(comparator);
    }


}
