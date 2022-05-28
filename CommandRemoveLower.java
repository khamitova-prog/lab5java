package commands;

import utility.*;
import io.*;
import modules.*;
import exceptions.*;

/**
 * Команда "remove_lower". Удаляет из коллекции все элементы меньше заданного.
 */
public class CommandRemoveLower  extends Command {
    CollectionManager collectionManager;
    InputManager inputManager;

    public CommandRemoveLower(CollectionManager collectionManager, InputManager inputManager) {
        super("remove_lower", "Удалить из коллекции все элементы, меньшие, чем заданный.");
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
    }

    /**
     * Выполняет команду "remove_lower"
      * @param arg пустая строка
     * @return true
     */
    @Override
    public boolean execute(String arg) {
        if (!arg.equals("")) {
            System.out.println("Ошибка ввода. Команда remove_lower не должна иметь аргумента.");
            return true;
        }

        if (collectionManager.getLabWorkCollection().size() == 0) {
            System.out.println("Коллекция пуста.");
            return true;
        }

        try {

            LabWork lw = inputManager.readLabWork();
            lw.setId(collectionManager.createId());
            collectionManager.removeLowerFrom(lw);

            for (LabWork l : collectionManager.getLabWorkCollection()) {
                (collectionManager.getIdSet()).add(l.getId());
            }

            System.out.println("Элементы коллекции меньше заданного успешно удалены из коллекции.");
        }
        catch (InvalidDataException x) {
            System.out.println(x.getMessage());

        }

return true;    }
}

