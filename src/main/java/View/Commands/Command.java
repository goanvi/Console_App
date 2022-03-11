package View.Commands;

public interface Command {
    String getDescroption();
    String getName();
    boolean execute (String argument);
}
