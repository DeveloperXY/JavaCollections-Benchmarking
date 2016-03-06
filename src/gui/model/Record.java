package gui.model;

/**
 * Created by Mohammed Aouf ZOUAG on 06/03/2016.
 */
public class Record {
    private double arraylistStat;
    private double linkedlistStat;
    private double setStat;

    public Record(double arraylistStat, double linkedlistStat, double setStat) {
        this.arraylistStat = arraylistStat;
        this.linkedlistStat = linkedlistStat;
        this.setStat = setStat;
    }

    public double getArraylistStat() {
        return arraylistStat;
    }

    public double getLinkedlistStat() {
        return linkedlistStat;
    }

    public double getSetStat() {
        return setStat;
    }

    @Override
    public String toString() {
        return "Record{" +
                "arraylistStat=" + arraylistStat +
                ", linkedlistStat=" + linkedlistStat +
                ", setStat=" + setStat +
                '}';
    }
}
