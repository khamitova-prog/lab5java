package commands;

import utility.CollectionManager;
import io.*;
import modules.*;
import exceptions.*;

/**
 * Команда ""remove_greater_. Удаляет все элементы превышающий заданный.
 */
public class CommandRemoveGreaterFrom extends Command {
    CollectionManager collectionManager;
    InputManager inputManager;

    public CommandRemoveGreaterFrom(CollectionManager collectionManager, InputManager inputManager) {
        super("remove_greater", "Удалить из коллекции все элементы, превышающие заданный.");
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
    }

    /**
     * Выполняет команду, удаляющую все элементы больше заданного.
      * @param arg пустая строка
     * @return true
     */
    @Override
    public boolean execute(String arg) {
        if (!arg.equals("")) {
            System.out.println("Ошибка ввода. Команда remove_greater не должна иметь акгумента.");
            return true;
        }
        if (collectionManager.getLabWorkCollection().isEmpty()) {
            System.out.println("Коллекция пуста.");
            return true;
        }

            try {
            LabWork lw =inputManager.readLabWork();
                    lw.setId(collectionManager.createId());

                    collectionManager.removeGreaterFrom(lw);

                (collectionManager.getIdSet()).clear();

                    for (LabWork l : collectionManager.getLabWorkCollection()) {
                        (collectionManager.getIdSet()).add(l.getId());
                    }
            }
            catch (InvalidDataException x) {
                System.out.println(x.getMessage());
                return  true;
            }

        System.out.println("Элементы коллекции больше заданного успешно удалены из коллекции.");

        return true;
    }
}
