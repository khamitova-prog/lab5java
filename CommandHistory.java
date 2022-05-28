package commands;

import java.util.ArrayDeque;

/**
 * Команда "history". выводит последние 11 команд.
 */
public class CommandHistory extends Command {
    ArrayDeque<String> dq;

    public CommandHistory(ArrayDeque<String> deque) {
        super("history", "Вывести последние 11 команд (без их аргументов).");
        dq = deque;
    }

    /**
     * Выполняет команду "history" и выводит список последних 11 выполненных команд.
      * @param arg пустая строка
     * @return true
     */
    @Override
    public boolean execute(String arg) {
        if (!arg.equals("")) {
            System.out.println("Ошибка ввода. Команда history должно быть без аргумента.");
            return true;
        }
        System.out.println(dq.toString());

        return true;
    }
}
