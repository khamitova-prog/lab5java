package commands;

import utility.*;
import modules.*;

import   java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Команда "count_by_discipline". Вывадит количество элементов, значение поля discipline которых равно заданному
 */
public class CommandCountByDiscipline extends Command {
    CollectionManager collectionManager;

    public CommandCountByDiscipline(CollectionManager collectionManager) {
        super("count_by_discipline", "Вывести количество элементов, значение поля discipline которых равно заданному.");
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду count_by_discipline
     * @param arg значение поля discipline
     * @return true
     */
    @Override
    public boolean execute(String arg) {
        if (arg.equals("")) {
            System.out.println("Ошибка ввода. Не указано значение поля discipline");
            return true;
        }

        if (collectionManager.getLabWorkCollection().size() == 0) {
            System.out.println("Коллекция пуста.");
            return true;
        }

        HashMap<String, AtomicInteger> map = new HashMap<>();
        HashSet<LabWork> labWorkCollection = collectionManager.getLabWorkCollection();

        for (LabWork lw : labWorkCollection) {
            String d = lw.getDisciplineName();
            if (!d.equals(arg)) continue;
            if (map.containsKey(d)) {
                map.get(d).incrementAndGet(); //счетчик. Если не попало, то уыеличивает на 1.
            }
            else {
                map.put(d, new AtomicInteger(1));
            } //кладем ключ с именем дисциплины. В Atomic кладем единицу
        }
        if(map.size() == 0) System.out.println(" В коллекции нет элементов с указанным полем.");
        else {
            Iterator<Map.Entry<String, AtomicInteger>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, AtomicInteger> pair = it.next();
                String d = pair.getKey();
                int count = (map.get(d)).intValue();
                System.out.println(d + ": " + count);
            }
        }
        return true;

    }
}
