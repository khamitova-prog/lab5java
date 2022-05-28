package exceptions;

/**
 * Не указан путь к файлу.
 */
public class EmptyPathException extends FileException{
    public EmptyPathException(){
        super("Ошибка. Путь к файлу отсутствует.");
    }
}
