package commands;

import utility.*;
import modules.*;
import io.*;

/**
 * Команда "update id". Обновляет значения элемента коллекции по id.
 */
public class CommandUpdate extends Command {
    CollectionManager collectionManager;

    public CommandUpdate(CollectionManager collectionManager) {
        super("update", "Обновить значение элемента коллекции, id которого равен заданному.");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String arg) {
        int id;

            if (arg.equals("")) {
                System.out.println("Ошибка ввода. Не указан id элемента коллекции.");
                return true;
            }
            if (collectionManager.getLabWorkCollection().size() == 0) {
                System.out.println("Коллекция пуста.");
                return true;
            }

            try {
                 id = Integer.parseInt(arg);
            }
            catch (NumberFormatException  e) {
                System.out.println("Ошибка ввода. Id должно быть целым числом.");
                return  true;
            }

            LabWork lw = collectionManager.getById(id);
            if (lw == null) {
                System.out.println("Элемент с введенным id в коллекции отсутствует.");
                return true;
            }

                    collectionManager.removeFromCollection(lw);
                    lw = (new ConsoleInputManager()).readLabWork();
                    lw.setId(id);
                    collectionManager.getLabWorkCollection().add(lw);

            return true;
    }
}

