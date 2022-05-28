package exceptions;

/**
 * Бросается, когда аргумент команды недействителен.
 */
public class InvalidCommandArgumentException extends CommandException {

    public InvalidCommandArgumentException(String message) {
        super(message);
        System.out.println(message);
    }

}

