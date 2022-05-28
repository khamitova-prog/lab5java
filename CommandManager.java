package utility;

import exceptions.EmptyStringException;
import io.ConsoleInputManager;
import io.FileInputManager;
import io.InputManager;
import commands.*;

import java.util.*;

/**
 * Управляет командами
 */
public class CommandManager {
    private final Map<String, Command> map;
private InputManager inputManager;
private boolean isRunning;
public ArrayDeque<String> deque = new ArrayDeque<>();

    public CommandManager(CollectionManager collectionManager, InputManager inputManager, FileManager fileManager) {
        this.isRunning = false;
        inputManager = inputManager;
        this.map = new HashMap<>();
        addCommand("help", new CommandHelp());
        addCommand("info", new CommandInfo(collectionManager));
        addCommand("show", new CommandShow(collectionManager));
        addCommand("add", new CommandAdd(collectionManager));
        addCommand("update", new CommandUpdate(collectionManager));
        addCommand("remove_by_id", new CommandRemoveById(collectionManager));
        addCommand("clear", new CommandClear(collectionManager));
      addCommand("save", new CommandSave(collectionManager, fileManager));
       addCommand("execute_script", new CommandExecuteScript(collectionManager, inputManager, fileManager));
        addCommand("exit", new CommandExit());
      addCommand("remove_greater", new CommandRemoveGreaterFrom(collectionManager, inputManager));
       addCommand("remove_lower", new CommandRemoveLower(collectionManager, inputManager));
       addCommand("history", new CommandHistory(deque));
        addCommand("group_counting_by_coordinates", new CommandGroupCountingByCoordinates(collectionManager));
        addCommand("count_by_discipline", new CommandCountByDiscipline(collectionManager));
       addCommand("print_field_ascending_discipline", new CommandPrintFieldAscendingDiscipline(collectionManager));
    }

    /**
     * Создает объект команды и добавляет имя команды как ключ, саму команду как значение в коллекцию map.
      * @param key строка с именем команды
     * @param command
     */
    public void addCommand(String key, Command command) {
        map.put(key, command);
    }

    /**
     * Режим работы с командами в консоли
      */
    public void consoleMode() {
        inputManager = new ConsoleInputManager();
        isRunning = true;

        while (isRunning) {
             System.out.println("Введите команду.");
             try {
                 String[] words = inputManager.readCommand();
                 String key = words[0];
                 String arg = "";

                 if (deque.size() == 11) deque.remove();
                 deque.addLast(key);

                 if (words.length == 2) arg = words[1];

                 if (!map.containsKey(key)) {
                     System.out.println("Введенная команда отсутствует. Повторите ввод.");
                     continue;
                 }

                 Command cmd = map.get(key);
                 isRunning = (cmd.execute(arg));
             }
             catch (EmptyStringException x) {
                 System.out.println("Команда не была введена. Повторите ввод.");
             }
        }

    }

    /**
     * Режим файловоо ввода команд при чтении команд из скрипта
      * @param pfs
     * @throws EmptyStringException
     */
    public void fileMode(String pfs) throws EmptyStringException {
        inputManager = new FileInputManager(pfs); //присваиваем поток из файла со скриптом.
        isRunning = true;

        while (isRunning && inputManager.getScanner().hasNextLine()) {
            try {
            String [] words = inputManager.readCommand();
            String key = words[0];
            String arg = "";

            if (words.length == 2) arg = words[1];
            if(deque.size() == 11) deque.remove();
            deque.addLast(key);

            if (arg.equals("") && key.equals("execute_script")) {
                System.out.println("Не указано имя файла содержащего скрипт.");
                continue;
            }

                if (!map.containsKey(key)) {
                    System.out.println("Введенная команда отсутствует. Повторите ввод.");
                    continue;
                }

                Command cmd = map.get(key);
            isRunning = cmd.execute(arg);
          }
            catch (EmptyStringException x) {
                System.out.println("Команда отсутствует.");
            }
    }


}
    }


