package shosafoev.organizm;

public class DataOrganisms {
    private final double weight;// Animal/plant weight in kg
    private final int maxPopulation;// Maximum number of animal/plant species per 1 cell
    private final String name; // Name of the animal/plant
    private int row;
    private int column;

    /**
     * DataOrganisms class constructor
     *
     * @param weight        Animal/plant weight in kg
     * @param maxPopulation Maximum number of animal/plant species per 1 cell
     * @param name          The name of the animal/plant
     */
    public DataOrganisms(double weight, int maxPopulation, String name) {
        this.weight = weight;
        this.maxPopulation = maxPopulation;
        this.name = name;

    }

    public double getWeight() {
        return weight;
    }

    public int getMaxPopulation() {
        return maxPopulation;
    }

    public String getName() {
        return name;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}