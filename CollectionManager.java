package utility;

import java.time.*;

import com.google.gson.*;
import  com.google.gson.reflect.TypeToken;

import java.lang.reflect.*;

import java.util.*;

import modules.*;

/**
 * Управляет коллекцией .
 */
public class CollectionManager {
    private HashSet<LabWork> labWorkCollection = new HashSet<>();
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;
    private FileManager fileManager;
    private HashSet<Integer> idSet = new HashSet<>();

    public CollectionManager(FileManager fileManager) {
        this.fileManager = fileManager;
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.load();
    }

    /**
     * @return collection.
     */
    public HashSet<LabWork> getLabWorkCollection() {
        return labWorkCollection;
    }

    /**
     * @return Время последней инициализации или null, если инициализации не было.
     */
    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }

    /**
     * @return Время последнего сохранения или null, если сохранения не было.
     */
    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }

    public void setLastSaveTime(LocalDateTime lastSaveTime) {
        this.lastSaveTime = lastSaveTime;
    }

    /**
     *Возвращает Set коллекцию из использованных id при создании элементов LabWork.
      * @return HashSet id
     */
    public HashSet<Integer> getIdSet() {
        return idSet;
    }

    /**
     * Загружает коллекцию из файла.
     */
public void load() {
        Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class, new JsonDeserializer<ZonedDateTime>() {
            @Override
            public ZonedDateTime deserialize(JsonElement json, Type type,
                                             JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

                JsonObject jsonObj = json.getAsJsonObject();

                JsonObject dateTime = jsonObj.getAsJsonObject("dateTime");
                JsonObject date = dateTime.getAsJsonObject("date");
                int year = date.get("year").getAsInt();
                int month = date.get("month").getAsInt();
                int day = date.get("day").getAsInt();

                JsonObject time = dateTime.getAsJsonObject("time");
                int hour = time.get("hour").getAsInt();
                int minute = time.get("minute").getAsInt();
                int second = time.get("second").getAsInt();
                int nano = time.get("nano").getAsInt();

                JsonObject zone = jsonObj.getAsJsonObject("zone");
                String id = zone.get("id").getAsString();

                return ZonedDateTime.of(year, month, day, hour, minute, second, nano, ZoneId.of(id));
            }
        }).create();

        String str = fileManager.read();

    if (str.equals("")) System.out.println("Коллекция пуста.");
    else {
        Type collectionType = new TypeToken<HashSet<LabWork>>() {}.getType();

        try {
            labWorkCollection = gson.fromJson(str, collectionType);
        }
        catch (JsonSyntaxException e) {
            System.out.println("Ошибка. Не верный формат файла json. Коллекция не загружена.");
        }

        lastInitTime = LocalDateTime.now();

        for (LabWork lw : labWorkCollection) {
            idSet.add(lw.getId());
        }
    }
}

    /**
     * Генерирует уникальный id.
      * @return значение id нового элемента LabWork.
     */
    public Integer createId() {
        if (labWorkCollection.isEmpty()) {
            return 1;
        }
        else {
            int id = labWorkCollection.size() + 1;
            while (!idSet.add(id)) {
                id-= 1;
            }
            return id;
        }
    }

    /**
     * Возвращает  Элемент LabWork с заданным полем id.
      * @param id
     * @return элемент  LabWork с заданным полем id или null, если такого элемента нет.
     */
    public LabWork getById(Integer id) {
        for (LabWork lw : labWorkCollection) {
            if (lw.getId().equals(id)) return lw;
        }
        return null;
    }

    /**
     * Удаляет из коллекции элемент
      * @param lw элемент, который нужно удалить
     */
    public void removeFromCollection(LabWork lw) {
        labWorkCollection.remove(lw);
    }

    /**
     * Удаляет из коллекции все элементы больше заданного.
      * @param lw элемент с которым сравнивают элементы коллекции
     */
    public void removeGreaterFrom(LabWork lw) {
        labWorkCollection.removeIf(x -> x.compareTo(lw) > -1);
    }

    /**
     *
     * Удаляет все элементы коллекции меньше заданного.
      * @param lw элемент с которым сравнивают
     */
    public void removeLowerFrom(LabWork lw) {
        labWorkCollection.removeIf(labWork -> (labWork.compareTo(lw) == -1));
    }

    /**
     * Выводит информацию о коллекции.
      * @return строка с информацией о коллекции
     */
    @Override
    public String toString() {
        if (labWorkCollection.isEmpty()) return "Коллекция пуста.";

        String info = "";
        for (LabWork lw : labWorkCollection) {
            info += lw.toString();
            info += "\n";
        }
        return info;
    }


}

