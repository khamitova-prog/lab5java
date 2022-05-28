package commands;

import java.time.*;

import utility.CollectionManager;

/**
 * Команда «info». Выводит информацию о коллекции.
 */
public class CommandInfo extends Command {
    private CollectionManager collectionManager;

    public CommandInfo(CollectionManager collectionManager) {
        super("info", "Вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.).");
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду "info".
     * @param arg пустая строка
     * @return true
     */
    @Override
    public boolean execute(String arg) {
        if (!arg.equals("")) {
            System.out.println("Ошибка ввода. У команды info не должно быть аргумента.");
            return true;
        }

        LocalDateTime lastInitTime = collectionManager.getLastInitTime();
        String lit = (lastInitTime == null) ? "В данной сессии инициализация еще не происходила.":
                lastInitTime.toLocalDate().toString() + " " + lastInitTime.toLocalTime().toString();

        LocalDateTime lastSaveTime = collectionManager.getLastSaveTime();
        String lst = (lastSaveTime == null) ? "В данной сессии сохранение еще не происходило.":
                lastSaveTime.toLocalDate().toString() + " " + lastSaveTime.toLocalTime().toString();

        System.out.println("Сведения о коллекции: ");
        System.out.println("тип: " + collectionManager.getLabWorkCollection().getClass().getName());
        System.out.println("количество элементов: " + collectionManager.getLabWorkCollection().size());
        System.out.println("дата последнего сохранения: " + lst);
        System.out.println("дата последней инициализации: " + lit);

        return true;
    }
}
