package exceptions;

/**
 * Пустая строка
 */
public class EmptyStringException extends InvalidDataException {
    public EmptyStringException() {
        super("Строка не может быть пустой.");
    }
}
