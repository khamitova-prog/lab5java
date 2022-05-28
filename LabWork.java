package modules;

import java.time.*;

/**
 * Основной класс коллекции.
 */
public class LabWork implements Comparable<LabWork> {
    private Integer id;
    private String name;
    private Coordinates coordinates;
    private ZonedDateTime creationDate;
    private Double minimalPoint;
    private Difficulty difficulty;
    private Discipline discipline;

    public LabWork(String name, Coordinates coordinates, Double minimalPoint, Difficulty difficulty, Discipline discipline) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = ZonedDateTime.now();
       this.minimalPoint = minimalPoint;
        this.difficulty = difficulty;
        this.discipline = discipline;
    }

    /**
     * Возвращает  id
     * @return id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Устанавливает значение поля id
      * @param id Integer
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Возвращает строку с значением поля name
     * @return String name.
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает объект поля Coordinates
      * @return объект Coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Возвращает объект перечисления
      * @return список констант
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Возвращает значение поля Discipline
      * @return объект Discipline
     */
    public Discipline getDiscipline() {
        return discipline;
    }

    /**
     * Возвращает значение поля minimalPoint
     * @return Double.
     */
    public Double getMinimalPoint() {
        return minimalPoint;
    }

    /**
     * Устанавливает значение поля minimalPoint
     * @param minimalPoint Double
     */
    public void setMinimalPoint(Double minimalPoint) {
        this.minimalPoint = minimalPoint;
    }

    public String getDisciplineName() {
        return discipline.getName();
    }


    @Override
    public int compareTo(LabWork obj) {
        int result;
        result = Double.compare(coordinates.getX(), obj.getCoordinates().getX());
        return result;
    }

    public String toString() {
        return "LabWork{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates.toString() +
                ", creationDate=" + creationDate +
                ", minimalPoint=" + minimalPoint +
                ", difficulty=" + difficulty +
                ", discipline=" + discipline.toString() +
                '}';
    }


}

