package utility;

import java.util.*;
import java.io.*;

import exceptions.*;

/**
 * Управляет файлом для сохранения/загрузки коллекции.
 */
public class FileManager {
    private final String path;

    public FileManager(String path) {
        this.path = path;
    }

    /**
     * Читает файл.
      * @return строковое содержимое файла
     */
    public     String read() {
        String  str = "";

        try {
            if (path == null) throw new EmptyPathException();

            File file = new File(path);

            if (!file.exists()) {
                if (file.createNewFile()) System.out.println("По заданному пути файл отсутствует. Файл успешно создан.");
                else throw  new FileNotExistsException();
            }

            if (file.isDirectory()) throw new FileWrongPermissionsException("Ошибка. Путь указывает на директорию.");

            if (!file.canRead()) {
                if (file.setReadable(true)) System.out.println("Нет прав на чтение файла. Права на чтение файла установлены успешно.");
                else throw new FileWrongPermissionsException("Не могу прочитать файл.");
            }

            Scanner sr = new Scanner(file);
            while (sr.hasNext()) {
                str += sr.nextLine().trim() + "\n";
            }
        }
        catch (FileException e)  {
            System.out.println(e.getMessage());
            System.out.println("Программа будет завершена.");
            System.exit(0);
        }
        catch (IOException e) {
            System.out.println("Ошибка. Не могу получить доступ к файлу.");
            System.out.println("Программа будет завершена.");
            System.exit(0);
        }
        return str;
    }

    /**
     * Сохраняет строку в файл.
     * @param str строка, которую надо сохранить.
     * @return true, если файл успешно записан, иначе   false
     */
    public boolean write(String str) {
        boolean executing = true;
        try {
            if (path == null) throw new EmptyPathException();

            File file = new File(path);

            if (!file.exists()) {
                if (file.createNewFile())System.out.println("Файл для записи не найден. Файл создан успешно.");
                else throw new FileNotExistsException();
            }
            if (file.isDirectory()) throw new FileWrongPermissionsException("Ошибка записи. По заданному пути находится директория.");

            if (!file.canWrite()) {
                if (file.setWritable(true)) System.out.println("Нет прав на запись в файл. Права на записсь установлены успешно.");
                else throw new FileWrongPermissionsException("не могу записать файл");
            }

            PrintWriter pwc = new PrintWriter(file);
            pwc.println(str);
            if (!pwc.checkError()) System.out.println("Коллекция успешно сохранена в файл.");
            else executing = false;
        }
        catch (FileException e) {
            System.out.println(e.getMessage());
            executing = false;
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            executing = false;
        }
        return executing;
    }

    @Override
    public String toString() {
        return "FileManager (класс для работы с  файлами)";
    }

}

