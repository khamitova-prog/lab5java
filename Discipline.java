package modules;

/**
 * Дисциплина лабораторной работы.
 */
public class Discipline {
    private String name;
    private Integer labsCount;

    public Discipline(String name, Integer labsCount) {
        this.name = name;
        this.labsCount = labsCount;
    }

    public Discipline(String name) {
        this.name = name;
    }

    /**
     * @return name of discipline.
     */
    public String getName() {
        return name;
    }

    /**
     * @return number of labWorks in the discipline.
     */
    public Integer getLabsCount() {
        return labsCount;
    }

    public int compareTo(Discipline obj) {
        return name.compareTo(obj.getName());
    }

    @Override
    public String toString() {
        return name + " (" + labsCount+ " работ)";
    }

}

