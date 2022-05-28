package io;

import java.util.*;

import exceptions.*;
import modules.*;

/**
 * Работа с  пользовательским вводом из консоли
 */
public class ConsoleInputManager extends InputManager {

    public ConsoleInputManager () {
        super(new Scanner(System.in));
        getScanner().useDelimiter("\n");
    }

    /**
     * Предлагает ввести значение поля name в консоли
      * @return строку
     */
    @Override
    public String readName() {
        askRepeat = true;
        String name = "";
        while (askRepeat) {
            try {
                System.out.println("Введите название лабораторной работы:");
                name = super.readName();
            }
            catch (EmptyStringException e) {
                System.out.println(e.getMessage());
            }
        }
        return name;
    }

/**
 * Предлагает ввести значение координаты X
  */
@Override
    public double readCoordinateX() {
        String str = "Введите координату x," + "\n" +
                "действительное число не более 517:";
        askRepeat = true;
        double x= 0;
        while (askRepeat ) {
            try {
                System.out.println(str);
                x = super.readCoordinateX();
            }
            catch (InvalidNumberException e) {
                System.out.println(e.getMessage());
            }
        }
        return x;
    }

    /**
     * Предлагает ввести значение координаты Y
      * @return double
     */
    @Override
    public double readCoordinateY() {
        String str = "Введите координату y," + "\n" +
                " любое действительное число:";
        double y = 0;
        askRepeat = true;
        while (askRepeat) {
            try {
                System.out.println(str);
                y = super.readCoordinateY();
            }
            catch (InvalidNumberException e) {
                System.out.println(e.getMessage());
            }
        }
        return y;
    }

    @Override
    public Coordinates readCoordinates() {
        double x = readCoordinateX();
        double y = readCoordinateY();
        return new Coordinates(x, y);
    }

    /**
     * Предлагает ввести значене поля minimalPoint
      * @return Double
     */
    @Override
    public Double readMinimalPoint() {
        String  str = "Введите значение minimalPoint, " + "\n" +
                "действительное число больше нуля. (может быть пустым)";
        Double mp = null;
        askRepeat = true;

        while (askRepeat) {
            try {
                System.out.println(str);
                mp = super.readMinimalPoint();
            }
            catch (InvalidNumberException e) {
                System.out.println(e.getMessage());
            }
        }

        return mp;
    }

    /**
     * Предлагает ввести константу из предложенного списка
      */
       @Override
    public Difficulty readDifficulty() {
           Difficulty d = null;
           askRepeat = true;
           while (askRepeat) {
               try {
                   System.out.println("Введите константу " + Difficulty.list() + " :");
                   d = super.readDifficulty();
               }
               catch (InvalidEnumException e) {
                   System.out.println(e.getMessage());
               }
           }
        return d;
    }

    /**
     * Предлагает ввести значения поля name из класса Discipline
     * @return строку
     */
    @Override
    public String readNameDiscipline() {
        askRepeat = true;
        String name = "";
        while (askRepeat) {
            try {
                System.out.println("Введите имя дисциплины:");
                name = super.readNameDiscipline();
            }
            catch (EmptyStringException e) {
                System.out.println(e.getMessage());
            }

        }
        return name;
    }

    /**
     * Предлагает ввести значение count из класса Discipline
      * @return Integer
     */
    @Override
    public Integer readLabsCount() {
        askRepeat = true;
        Integer i = null;
        while (askRepeat) {
            try {
                System.out.println("Введите целое число labsCount (не обязательное поле):");
                i = super.readLabsCount();
            }
            catch (InvalidNumberException e) {
                System.out.println(e.getMessage());
            }
        }
        return i;
    }

    /**
     * Создает объект Discipline, используя введеные ранее значения полей
      * @return Discipline
     */
    @Override
    public Discipline readDiscipline() {
        String nameDiscipline = readNameDiscipline();
        Integer labsCount = readLabsCount();
        return new Discipline(nameDiscipline, labsCount);
    }

    /**
     * Создает объект LabWork, используя полученные данные, введенные из кансоли
      * @return LabWork
     */
    @Override
    public LabWork readLabWork() {
        String name = readName();
        Coordinates coordinates = readCoordinates();
        Double minimalPoint = readMinimalPoint();
        Difficulty difficulty = readDifficulty();
        Discipline discipline = readDiscipline();
        return new LabWork(name, coordinates, minimalPoint, difficulty, discipline);
    }
}

