package trevorsoftware1.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class Part {
    
    private final IntegerProperty partID = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final DoubleProperty price = new SimpleDoubleProperty();
    private final IntegerProperty inStock = new SimpleIntegerProperty();
    private final IntegerProperty min = new SimpleIntegerProperty();
    private final IntegerProperty max = new SimpleIntegerProperty();
    
    public Part(){
       // default constructor 
    }
    
    
    public void setName(String x){
        name.set(x);
    }
    
    public String getName(){
        return name.get();
    }
    
    public StringProperty nameProperty() {
        return name;
    }
    
    public void setPrice(double x) {
        price.set(x);
    }
    
    public double getPrice() {
        return price.get();
    }
    
    public DoubleProperty priceProperty() {
        return price;
    }
    
    public void setInStock(int x) {
        inStock.set(x);
    }
    
    public int getInStock() {
        return inStock.get();
    }
    
    public IntegerProperty inStockProperty() {
        return inStock;
    }
    
    public void setMin(int x) {
        min.set(x);
    }
    
    public int getMin() {
        return min.get();
    }
    
    public IntegerProperty minProperty() {
        return min;
    }
    
    public void setMax(int x) {
        max.set(x);
    }
    
    public int getMax() {
        return max.get();
    }
    
    public IntegerProperty maxProperty(){
        return max;
    }
    
    public void setPartID(int x) {
        partID.set(x);
    }
    
    public int getPartID() {
        return partID.get();
    }
    
    public IntegerProperty partIDProperty() {
        return partID;
    }
    
}
