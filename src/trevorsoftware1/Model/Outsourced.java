package trevorsoftware1.Model;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Outsourced extends Part {
    private final StringProperty companyName = new SimpleStringProperty();
    
    public Outsourced(){
        // default constructor
    }
    
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
