package trevorsoftware1.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Inhouse extends Part {
    
    private final IntegerProperty machineID = new SimpleIntegerProperty();
    
    public Inhouse() {
        // default constructor
    }
    
    public void setMachineID(int x) {
        machineID.set(x);
    }
    
    public int getMachineID() {
        return machineID.get();
    }
    
    public IntegerProperty machineIDProperty() {
        return machineID;
    }
}
