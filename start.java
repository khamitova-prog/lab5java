package run;

import io.*;
import utility.*;


public class start {
    public static void main(String[] args) {
        String path ;
        if (args.length != 0) path = args[0];
        else {
            System.out.println("Отсутствует аргумент командной строки . Используем файл по умолчанию resources/file.json .");
             path = "resources/file.json";
        }

            FileManager fileManager = new FileManager(path);
            CollectionManager cm = new CollectionManager(fileManager);
            InputManager consoleManager = new ConsoleInputManager();
            CommandManager commandManager = new CommandManager(cm,consoleManager, fileManager);
            commandManager.consoleMode();

    }

}

