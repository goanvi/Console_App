package View.ConsoleClient;

public class ConsoleClient {


    public static void println(Object out){
        System.out.println(out);
    }

    public static void print(Object out){
        System.out.print(out);
    }

    public static void printError(Object out){
        System.out.println("ERROR: " + out);
    }

}
