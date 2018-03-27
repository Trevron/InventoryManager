package trevorsoftware1.Model;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Outsourced extends Part {
    private final StringProperty companyName = new SimpleStringProperty();
    
    // Constructors
    public Outsourced() {
        super();
        this.companyName.set("default");
    }
    
    public Outsourced(String companyName) {
        super();
        this.companyName.set(companyName);   
    }
    
    public Outsourced(int partID, String name, double price, int inStock, int min, int max, String companyName) {
        super(partID, name, price, inStock, min, max);
        this.companyName.set(companyName);
        
    }
    
    // Getters and setters
    public String getCompanyName() {
        return companyName.get();
    }
    
    public void setCompanyName(String x) {
        companyName.set(x);
    }
    
    public StringProperty companyNameProperty() {
        return companyName;
    }
}
