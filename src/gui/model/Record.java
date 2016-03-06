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
        this.arraylistStat = new SimpleStringProperty(
                String.format("%fs", arraylistStat));
        this.linkedlistStat = new SimpleStringProperty(
                String.format("%fs", linkedlistStat));
        this.setStat = new SimpleStringProperty(
                String.format("%fs", setStat));
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

    @Override
    public String toString() {
        return "Record{" +
                "arraylistStat=" + arraylistStat +
                ", linkedlistStat=" + linkedlistStat +
                ", setStat=" + setStat +
                '}';
    }
}
