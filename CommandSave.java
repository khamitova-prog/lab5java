package commands;

import com.google.gson.*;

import exceptions.CommandException;
import utility.*;

import java.time.LocalDateTime;

/**
 * Команда "save". Сохраняет коллекцию.
 */
public class CommandSave extends Command {
    CollectionManager collectionManager;
    FileManager fileManager;

    public CommandSave(CollectionManager collectionManager, FileManager fileManager) {
        super("save", "Сохранить коллекцию в файл.");
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
    }

    /**
     * Сохраняет коллекцию в файл.
      * @param arg пустая строка
     * @return true
     */
    @Override
    public boolean execute(String arg) {
        if (!arg.isEmpty()) {
            System.out.println("Ошибка ввода. У команды save не должно быть аргумента.");
            return true;
        }

        Gson gson = new Gson();
        String strGson = gson.toJson(collectionManager.getLabWorkCollection());

        if (collectionManager.getLabWorkCollection().isEmpty()) System.out.println("Коллекция пуста.");

        try {
            if (!fileManager.write(strGson)) throw new CommandException("Не могу сохранить коллекцию");
            collectionManager.setLastSaveTime(LocalDateTime.now());
        } catch (CommandException e) {
            System.out.println(e.getMessage());
            return true;
        }
        return true;
    }
}

