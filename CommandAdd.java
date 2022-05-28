package commands;

import io.ConsoleInputManager;
import modules.*;
import utility.*;

/**
 * Команда "add". Добавляет новый элемент коллекции.
 */
public class    CommandAdd extends Command {
    private CollectionManager collectionManager;

    public CommandAdd(CollectionManager collectionManager) {
        super("add {element} ", "добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду add.
      * @param arg пустая строка
     * @return true
     */
    @Override
    public boolean execute(String arg) {
            if (!arg.isEmpty()) {
                System.out.println("Ошибка ввода . У команды add не должно быть аргумента. ");
                return true;
            }

            LabWork lw = (new ConsoleInputManager()).readLabWork();
            lw.setId(collectionManager.createId());
            collectionManager.getLabWorkCollection().add(lw);
            System.out.println(lw.getName() + " добавлен в коллекцию.");
            return true;
    }
}

