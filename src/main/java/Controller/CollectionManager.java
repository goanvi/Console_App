package Controller;

import Model.Exceptions.EmptyCollectionException;
import Model.Semester;
import Model.StudyGroup;
import View.ConsoleClient.ConsoleClient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class CollectionManager { //Надо будет дописать loadCollection
    private StudyGroupComparator comparator = new StudyGroupComparator();
    private TreeSet<StudyGroup> studyGroupCollection = new TreeSet<>(comparator);
    FileWorker fileWorker;
    ParserCSV parser;
    LocalDateTime lastSaveTime;
    LocalDateTime lastLoadTime;

    public CollectionManager(FileWorker fileWorker) {
        this.fileWorker = fileWorker;
        this.parser = new ParserCSV();
        lastLoadTime= LocalDateTime.now();
    }

    public void clearCollection() {
        studyGroupCollection.clear();
    }

    public void addToCollection(StudyGroup studyGroup) {
        studyGroupCollection.add(studyGroup);
    }

    public void addCollection(TreeSet<StudyGroup> collection) {
        this.studyGroupCollection = collection;
    }

    public void removeGreater(StudyGroup studyGroup) {
        try {
            if (studyGroupCollection.size() == 0) throw new EmptyCollectionException();
            for (StudyGroup group : studyGroupCollection) {
                if (group.compareTo(studyGroup) > 0) {
                    IdManager.removeStudyGroupID(group.getID());
                    studyGroupCollection.remove(group);
                }
            }
        } catch (EmptyCollectionException exception) {
            ConsoleClient.printError(exception.getMessage());
        }
    }

    public void removeLower(StudyGroup studyGroup) {
        try {
            if (studyGroupCollection.size() == 0) throw new EmptyCollectionException();
            for (StudyGroup group : studyGroupCollection) {
                if (group.compareTo(studyGroup) < 0) {
                    IdManager.removeStudyGroupID(group.getID());
                    studyGroupCollection.remove(group);
                }
            }
        } catch (EmptyCollectionException exception) {
            ConsoleClient.printError(exception.getMessage());
        }
    }

    public void saveCollection(String file) {
        fileWorker.writer(parser.toCSV(studyGroupCollection),file);
        lastSaveTime = LocalDateTime.now();
    }

    public void loadCollection(String file) {
        studyGroupCollection = parser.csvFromData(fileWorker.reader(file));
        lastLoadTime = LocalDateTime.now();
    }

    public StudyGroup getAnyBySemesterEnum(Semester semester) {
        try {
            if (studyGroupCollection.isEmpty()) throw new EmptyCollectionException();
            for (StudyGroup studyGroup : studyGroupCollection) {
                if (studyGroup.getSemesterEnum().equals(semester)) {
                    return studyGroup;
                }
            }
        } catch (EmptyCollectionException exception) {
            ConsoleClient.printError(exception.getMessage());
        }
        return null;
    }

    public StudyGroup getByValue(StudyGroup studyGroup) {
        try {
            if (studyGroupCollection.size() == 0) throw new EmptyCollectionException();
            for (StudyGroup group : studyGroupCollection) {
                if (group.equals(studyGroup)) return group;
            }
        } catch (EmptyCollectionException exception) {
            ConsoleClient.printError(exception.getMessage());
        }
        return null;
    }

    public StudyGroup getByID(Integer id) {
        try {
            if (studyGroupCollection.size() == 0) throw new EmptyCollectionException();
            for (StudyGroup studyGroup : studyGroupCollection) {
                if (studyGroup.getID().equals(id)) return studyGroup;
            }
        } catch (EmptyCollectionException exception) {
            ConsoleClient.printError(exception.getMessage());
        }
        return null;
    }

    public long getSumOfStudentsCount() {
        long sum = 0;
        try {
            if (studyGroupCollection.size() == 0) throw new EmptyCollectionException();
            for (StudyGroup group : studyGroupCollection) {
                sum += group.getStudentsCount();
            }
        } catch (EmptyCollectionException exception) {
            ConsoleClient.printError(exception.getMessage());
        }
        return sum;
    }

    public List<StudyGroup> getLessThanStudentsCount(long studCount) {
        List<StudyGroup> groups = new ArrayList<>();
        try {
            if (studyGroupCollection.size() == 0) throw new EmptyCollectionException();
            for (StudyGroup group : studyGroupCollection) {
                if (group.getStudentsCount() < studCount) {
                    groups.add(group);
                }
            }

        } catch (EmptyCollectionException exception) {
            ConsoleClient.printError(exception.getMessage());
        }
        return groups;
    }

    public void remove(StudyGroup studyGroup){
        studyGroupCollection.remove(studyGroup);
    }

    public TreeSet<StudyGroup> getCollection() {
        return studyGroupCollection;
    }

    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }

    public String getCollectionType() {
        return studyGroupCollection.getClass().getName();
    }

    public int getCollectionSize() {
        return studyGroupCollection.size();
    }

    public LocalDateTime getLastLoadTime() {
        return lastLoadTime;
    }
}
