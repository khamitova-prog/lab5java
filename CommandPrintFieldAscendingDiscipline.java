package commands;

import utility.*;
import modules.*;

import java.util.*;

/**
 * Команда "print_field_ascending_discipline". выводит значения поля discipline всех элементов в порядке возрастания
 */
public class CommandPrintFieldAscendingDiscipline extends Command {
    CollectionManager collectionManager;

    public CommandPrintFieldAscendingDiscipline(CollectionManager collectionManager) {
        super("print_field_ascending_discipline", "Вывести значения поля discipline всех элементов в порядке возрастания.");
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду print_field_ascending_discipline
      * @param arg пустая строка
     * @return true
     */
    @Override
    public boolean execute(String arg) {
        if (!arg.equals("")) {
            System.out.println("Ошибка ввода. Команда print_field_ascending_discipline не должна иметь аргумента.");
            return true;
        }

        if (collectionManager.getLabWorkCollection().size() == 0) {
            System.out.println("Коллекция пуста.");
            return true;
        }

        ArrayList<String> alc = new ArrayList<>(collectionManager.getLabWorkCollection().size());
        for (LabWork lw : collectionManager.getLabWorkCollection()) {
            alc.add(lw.getDisciplineName());
        }
        Collections.sort(alc);
        for (String str : alc) {
            System.out.println(str);
        }

        return true;
    }
}
