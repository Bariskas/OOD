package Editor;

public interface DocumentCommand {
    void execute();

    void unexecute();
}
