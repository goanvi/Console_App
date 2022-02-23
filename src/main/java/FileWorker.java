import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileWorker {
    String file;

    public FileWorker (String file){
        this.file = file;
    }
    public void writer (String data){
        try(PrintWriter writer = new PrintWriter(file)){
            writer.write(data);
        }
        catch (Exception e) {
            e.getMessage();
        }
    }
}
