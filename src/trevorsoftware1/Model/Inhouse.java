package trevorsoftware1.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Inhouse extends Part {
    
    private final IntegerProperty machineID = new SimpleIntegerProperty();
    
    // Contstuctors
    public Inhouse() {
        super();
        this.machineID.set(0);
    }
    
    public Inhouse(int machineID) {
        super();
        this.machineID.set(machineID);   
    }
    
    public Inhouse (int partID, String name, double price, int inStock, int min, int max, int machineID) {
        super(partID, name, price, inStock, min, max);
        this.machineID.set(machineID);
        
    }
    
    // getters and setters
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
