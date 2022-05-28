package exceptions;

/**
 * Исключение при вводе константы.
 */
public class InvalidEnumException extends InvalidDataException {

    public InvalidEnumException () {
        super("Неправильная константа");
    }
}
