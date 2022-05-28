package commands;

import utility.*;

/**
 * Команда "remove_by_id". Удаляет элемент коллекции по его id.
 */
public class CommandRemoveById extends Command {
    CollectionManager collectionManager;

    public CommandRemoveById(CollectionManager collectionManager) {
        super("remove_by_id", "Удалить элемент из коллекции по его id.");
        this.collectionManager = collectionManager;
    }

    /**
     * Удаляет элемент коллекции по id
      * @param arg строка с id элемента, который надо удалить из коллекции
     * @return true
     */
    @Override
    public boolean execute(String arg) {
        int id;

        if (arg.isEmpty()) {
            System.out.println("Ошибка ввода. Не указан id элемента, который необходимо удалить из коллекции.");
            return true;
        }
        if(collectionManager.getLabWorkCollection().size() == 0) {
            System.out.println("Коллекция пуста.");
            return true;
        }

        try {
            id = Integer.parseInt(arg);
        }
        catch (NumberFormatException  e) {
            System.out.println("Ошибка ввода. Id должно быть целым числом.");
            return true;
        }

        if (collectionManager.getLabWorkCollection().contains(collectionManager.getById(id))) {
            collectionManager.getLabWorkCollection().remove(collectionManager.getById(id));
            System.out.println("Элементс id=" + id + " удален из коллекции.");
            collectionManager.getIdSet().remove(id);
        }
        else System.out.println("Элемент с id " + arg + " в коллекции не найден");
        return true;
    }
}

