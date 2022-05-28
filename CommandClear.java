package commands;

import utility.*;
import modules.*;

import java.util.HashSet;

/**
 * Комагнда clear. Очищает коллекцию.
 */
public class CommandClear extends Command {
    CollectionManager collectionManager;

    public CommandClear(CollectionManager collectionManager) {
        super("clear", "Очистить коллекцию.");
        this.collectionManager = collectionManager;
    }

    /**
     * Очищает коллекцию.
      * @param arg пустая строка
     * @return true
     */
    @Override
    public boolean execute(String arg) {
        if (!arg.equals("")) {
            System.out.println("Ошибка ввода. Команда clear должна вводиться без аргумента.");
            return true;
        }

        HashSet<LabWork> cm = collectionManager.getLabWorkCollection();
        cm.clear();
        System.out.println("Коллекция очищена.");
        return true;
    }
}
