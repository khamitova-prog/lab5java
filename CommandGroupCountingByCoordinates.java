package commands;

import utility.*;
import modules.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Команда "group_counting_by_coordinates". Группирует элементы коллекции по значению поля Coordinates и выводит их количество
 */
public class CommandGroupCountingByCoordinates extends Command {
    CollectionManager collectionManager;

    public CommandGroupCountingByCoordinates(CollectionManager collectionManager) {
        super("group_counting_by_coordinates", "Сгруппировать элементы коллекции по значению поля coordinates, вывести количество элементов в каждой группе.");
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду group_counting_by_coordinates
      * @param arg пустая строка
     * @return true
     */
    @Override
    public boolean execute(String arg) {
        if (!arg.equals("")) {
            System.out.println("Ошибка ввода. команда group_counting_by_coordinates не должна иметь аргумента.");
            return true;
        }

        if (collectionManager.getLabWorkCollection().size() == 0) {
            System.out.println("Коллекция пуста.");
            return true;
        }

        HashMap<Double, AtomicInteger> map = new HashMap<>();
        HashSet<LabWork> labWorkCollection = collectionManager.getLabWorkCollection();

        for (LabWork lw : labWorkCollection) {
             Double coordinateX= lw.getCoordinates().getX();
             if(map.containsKey(coordinateX)) map.get(coordinateX).incrementAndGet();
             else {
                 map.put(coordinateX, new AtomicInteger(1));
             }
        }

        if (map.size() == 0) System.out.println("Коллекция не содержит элементы с указанным полем.");
        else {
            Iterator<Map.Entry<Double, AtomicInteger>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Double, AtomicInteger> pair = it.next();
                Double coordinateX = pair.getKey();
                int count = (map.get(coordinateX)).intValue();
                System.out.println("x=" + coordinateX + " содержат " + count + " элемента коллекции:");
                for (LabWork lw : labWorkCollection) {
                    if (coordinateX.equals(lw.getCoordinates().getX())) System.out.println(lw);
                }
                System.out.println();
            }
        }
            return true;
        }
}

