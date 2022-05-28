package io;

import utility.*;

import java.util.*;

/**
 * Работа с файлом скрипта
 */
public class FileInputManager extends InputManager {
    public FileInputManager(String path) {
        super(new Scanner(new FileManager(path).read()));
        getScanner().useDelimiter("\n");
    }
}
