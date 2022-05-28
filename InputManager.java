package io;

import java.util.Scanner;

import modules.*;
import exceptions.*;

/**
 * Абстрактный класс для управления потоками ввода.
 */
public abstract class InputManager {
    private Scanner scanner;
    public boolean askRepeat = true;

    public InputManager(Scanner scanner) {
        this.scanner = scanner;
        scanner.useDelimiter("\n");
    }

    public Scanner getScanner() {
        return scanner;
    }

    /**
     * Проверяет пользовательский ввод name
     * @return
     * @throws EmptyStringException
     */
    public String readName() throws EmptyStringException {
            String str = scanner.nextLine().trim();
            if (str.equals("")) {
                throw new EmptyStringException();
            }
            askRepeat = false;
            return str;
    }

    /**
     * Проверяет пользовательский ввод координаты x
      * @return координату x
     * @throws InvalidNumberException
     */
    public double readCoordinateX() throws InvalidNumberException {
double x;
try {
    x = Double.parseDouble(scanner.nextLine());
}
catch (NumberFormatException e) {
    throw new InvalidNumberException("Ошибка. Введено не число.");
}
if (Double.isInfinite(x) || Double.isNaN(x)) throw new InvalidNumberException("Недопустимое значение с плавающей запятой");
if (x > 517.0) throw new InvalidNumberException("Должно быть не больше 517");
askRepeat = false;
return x;
    }

    /**
     * Проверяет пользовательский ввод координаты y
      * @return координату y
     * @throws InvalidNumberException
     */
    public double readCoordinateY() throws InvalidNumberException {
        double y;
        try {
            y = Double.parseDouble(scanner.nextLine());
        }
        catch (NumberFormatException e) {
            throw new InvalidNumberException("Ошибка. Введено не число.");
        }
        if (Double.isInfinite(y) || Double.isNaN(y)) throw new InvalidNumberException("Ошибка. Повторите ввод.");
        askRepeat = false;
        return y;
    }

    /**
     * Создает объект Coordinates из пользовательского ввода
      * @return объектCoordinates
     * @throws InvalidNumberException
     */
    public Coordinates readCoordinates() throws InvalidNumberException {
        double x = readCoordinateX();
        double y = readCoordinateY();
        return new Coordinates(x, y);
}

    /**
     * Проверяет пользовательский ввод поля minimalPoint
     * @return Double minimalPoint
     * @throws InvalidNumberException
     */
    public Double readMinimalPoint() throws InvalidNumberException {
        String str = scanner.nextLine().trim();
        if (str.equals("")) {
            askRepeat = false;
            return null;
        }
        Double mp;
        try {
            mp = Double.parseDouble(str);
        }
        catch (NumberFormatException e) {
throw new InvalidNumberException();
        }
        if (mp <= 0) throw new InvalidNumberException("Должно быть больше 0");
        askRepeat = false;
        return mp;
}

    /**
     * Проверяет польззовательский ввод перечисления difficulty
     * @return константу  Difficulty
     * @throws InvalidEnumException
     */
    public Difficulty readDifficulty() throws InvalidEnumException {
        String str = scanner.nextLine().trim();
        Difficulty d;
        try {
            d = Difficulty.valueOf(str);
        }
        catch (IllegalArgumentException e) {
            throw new InvalidEnumException();
        }
        askRepeat = false;
        return d;
}

    /**
     * Проверяет польззовательский ввод названия дисциплины
      * @return строку с именемдисциплины
     * @throws EmptyStringException
     */
    public String readNameDiscipline() throws EmptyStringException {
        String str = scanner.nextLine().trim();
        if (str.equals("")) {
            throw new EmptyStringException();
        }
        askRepeat = false;
        return str;
    }

    /**
     * Проверяет пользовательский ввод счетчика дисциплин
     * @return целое число
     * @throws InvalidNumberException
     */
    public Integer readLabsCount() throws InvalidNumberException {
        Integer lc;
        String str = scanner.nextLine().trim();
        if (str.equals("")) {
            askRepeat = false;
            return null;
        }
        try {
            lc = Integer.parseInt(str);
        }
        catch (NumberFormatException e) {
            throw new InvalidNumberException();
        }
        askRepeat = false;
        return lc;
}

    /**
     * Создает объект Discipline
      * @returnобъектDiscipline
     * @throws InvalidDataException
     */
    public Discipline readDiscipline() throws InvalidDataException {
        Integer lc = readLabsCount();
        String nd = readNameDiscipline();
        return new Discipline(nd, lc);
}

public LabWork readLabWork() throws InvalidDataException {
        LabWork lw;
        String name = readName();
        Coordinates coordinates = readCoordinates();
        Double minimalPoint = readMinimalPoint();
        Difficulty difficulty = readDifficulty();
        Discipline discipline = readDiscipline();
        lw = new LabWork(name, coordinates, minimalPoint, difficulty, discipline);
        return lw;
}

    public String[] readCommand() throws EmptyStringException {
        String str = scanner.nextLine().trim();
        String [] words = new String[2];

        if (str.equals("")) throw new EmptyStringException();
        else words = str.split("\\s+", 2);
        return words;
    }

    }


