package commands;

import utility.*;
import io.*;
import exceptions.*;

import java.util.Stack;

/**
 * Команда "execute_script". Запускает файл с командами.
 */
public class CommandExecuteScript extends Command {
    CollectionManager collectionManager;
    InputManager inputManager;
    FileManager fileManager;
    private static Stack<String> stack = new Stack<>();

    public CommandExecuteScript(CollectionManager collectionManager, InputManager inputManager, FileManager fileManager) {
        super("execute_script", "Считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
        this.fileManager = fileManager;
    }

    /**
     * Запускает файл с каомандами.
     * @param arg имя файла
     * @return true
     */
    @Override
    public boolean execute(String arg) {
        if (arg.equals("")) {
            System.out.println("Не указано имя файла, содержащего скрипт.");
            return true;
        }

        try{
    if (stack.contains(arg)) throw new RecursiveScriptExecuteException();
    else             System.out.println("Скрипт успешно запущен.");

            stack.push(arg); //проверяю  был ли аргумент с этим именем
        CommandManager process = new CommandManager(collectionManager, inputManager, fileManager);
            process.fileMode(arg);
            stack.pop();

            return true;
        }
        catch (exceptions.EmptyStringException x) {
            System.out.println(x.getMessage());
        }
        catch (RecursiveScriptExecuteException e)  {
            System.out.println("Выполнение скрипта execute_script " + arg + " прервано.");
            System.out.println(e.getMessage());
            return true;
        }
        return true;
    }
}

