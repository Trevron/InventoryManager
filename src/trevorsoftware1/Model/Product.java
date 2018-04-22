package trevorsoftware1.Model;

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Product {
    // Initialize variables
    private final ArrayList<Part> associatedParts;
    private final IntegerProperty productID = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final DoubleProperty price = new SimpleDoubleProperty();
    private final IntegerProperty inStock = new SimpleIntegerProperty();
    private final IntegerProperty min = new SimpleIntegerProperty();
    private final IntegerProperty max = new SimpleIntegerProperty();
    
    public Product() {
        this(0, "default", 0.00, 0, 0, 0, new ArrayList());
    }
    
    
    
    public Product(int productID, String name, double price, int inStock, int min, int max, ArrayList associatedParts) {
        this.productID.set(productID);
        this.name.set(name);
        this.price.set(price);
        this.inStock.set(inStock);
        this.min.set(min);
        this.max.set(max);
        this.associatedParts = associatedParts;
    }
    
    public void setName(String x) {
        name.set(x);
    }
    
    public String getName() {
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
    
    public int getMin(){
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
    
    public IntegerProperty maxProperty() {
        return max;
    }
    
    public void addAssociatedPart(Part x) {
        associatedParts.add(x);
    }
    
    public boolean removeAssociatedPart(Part x) {
        return associatedParts.remove(x);
    }
    
    public Part lookupAssociatedPart(int x) {
        for (Part part : associatedParts) {
            if (part.getPartID() == x) {
                System.out.println("The name of the part with the ID " + x +" is " + part.getName());
                return part;
            } 
        } 
        return null;
    }
    
    public ArrayList getAssociatedParts() {
        return associatedParts;
    }
    
    public void setAssociatedParts(ArrayList associatedParts) {
        this.associatedParts.clear();
        this.associatedParts.addAll(associatedParts);
    }
    
    public void setProductID(int x) {
        productID.set(x);
    }
    
    public int getProductID() {
        return productID.get();
    }
    
    public IntegerProperty productIDProperty() {
        return productID;
    }
    
}
