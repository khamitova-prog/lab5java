package commands;

/**
 * Абстрактный класс, его расширяют классы с командами
 */
public abstract class Command {
    private  String name;
    private String description;

    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public abstract boolean execute(String arg);
    @Override
    public String toString() {
        return name + " (" + description + ")";
    }
}


