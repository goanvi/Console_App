package View.Commands;

public interface Command {
    String getDescription();
    String getName();
    boolean execute (String argument);
}
