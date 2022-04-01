package Controller;

import java.io.*;

public class FileWorker {
    String file;

    public FileWorker(String file) {
        this.file = file;
    }

    public void writer(String data) {
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.write(data);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void writer(String data, String file) {
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.write(data);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public String reader() {
        byte[] buffer;
        StringBuilder input = new StringBuilder();
        try (FileInputStream fileInput = new FileInputStream(file);
             BufferedInputStream buffInput = new BufferedInputStream(fileInput)) {

            buffer = new byte[buffInput.available()];
            buffInput.read(buffer, 0, buffInput.available());
            for (byte letter : buffer) {
                input.append((char)letter);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return String.valueOf(input);

    }

    public String reader(String file) {
        byte[] buffer;
        StringBuilder input = new StringBuilder();
        try (FileInputStream fileInput = new FileInputStream(file);
             BufferedInputStream buffInput = new BufferedInputStream(fileInput)) {

            buffer = new byte[buffInput.available()];
            buffInput.read(buffer, 0, buffInput.available());
            for (byte letter : buffer) {
                input.append((char)letter);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return String.valueOf(input);

    }
}
