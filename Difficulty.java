package modules;

/**
 * Перечисление с константами labsWork.
 */
public enum Difficulty {
    VERY_EASY,
    IMPOSSIBLE,
    HOPELESS;

    /**
     * Генерирует список строк констант
     */
    public static String list() {
        String list = "";
        for (Difficulty difficultyType : values()) {
            list += difficultyType.name() + ", ";
        }
        return list.substring(0, list.length() - 2);
    }

}

