package gui.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Mohammed Aouf ZOUAG on 06/03/2016.
 */
public class Record {
    private StringProperty arraylistStat;
    private StringProperty linkedlistStat;
    private StringProperty setStat;

    public Record(double arraylistStat, double linkedlistStat, double setStat) {
        this.arraylistStat = new SimpleStringProperty(arraylistStat + "s");
        this.linkedlistStat = new SimpleStringProperty(linkedlistStat + "s");
        this.setStat = new SimpleStringProperty(setStat + "s");
    }

    public StringProperty arrayListProperty() {
        return arraylistStat;
    }

    public StringProperty linkedlistProperty() {
        return linkedlistStat;
    }

    public StringProperty setProperty() {
        return setStat;
    }

    public String getArraylistStat() {
        return arraylistStat.get();
    }

    public String getLinkedlistStat() {
        return linkedlistStat.get();
    }

    public String getSetStat() {
        return setStat.get();
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
