package trevorsoftware1.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Inhouse extends Part {
    
    private final IntegerProperty machineID = new SimpleIntegerProperty();
    
    public Inhouse() {
        // default constructor
    }
    
    public Inhouse(int partID, String name, double price, int inStock, int min, int max, int machineID) {
        super();
        this.setPartID(partID);
        this.setName(name);
        this.setPrice(price);
        this.setInStock(inStock);
        this.setMin(min);
        this.setMax(max);
        this.setMachineID(machineID);
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
