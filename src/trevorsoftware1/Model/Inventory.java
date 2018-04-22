package trevorsoftware1.Model;

import java.util.ArrayList;

/**
 *
 * @author Trevor Metcalf
 */
public class Inventory {
    
    //  Initialize array lists
    private ArrayList<Product> products;
    private ArrayList<Part> allParts;


    public Inventory(){
        products = new ArrayList<>();
        allParts = new ArrayList<>();
    }

    //  Interact with products array list   
    public void addProduct(Product x) {
        products.add(x);
    }
    // if product is found, it is removed and true is returned
    public boolean removeProduct(int x){
        boolean wasRemoved = false;
        for (Product product : products) {
            if (product.getProductID() == x) {
                System.out.println(product.getName() + " was removed.");
                products.remove(product);
                wasRemoved = true;
                break;
            } 
        } 
        return wasRemoved;  
    }
    // returns an array list of products that match the String input
    public ArrayList<Product> lookupProduct(String name) {
        ArrayList<Product> productList = new ArrayList();
        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i).getName());
            if (products.get(i).getName().contains(name)) { //try contains/equals
                productList.add(products.get(i));
            }
        }
        return productList;
    }
    
    private Product lookupProduct(int productID) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductID() == productID) {
                return products.get(i);
            }
        }
        return null;
    }
    
    public void updateProduct(int x) {
        for (Product product: products) {
            if (product.getProductID() == x) {
                System.out.println("Product" + product.getName() + "updated.");
            }
        }
    }
    
    // Interact with allParts arraylist
    public void addPart(Part x){
        allParts.add(x);
    }
    
    public boolean deletePart(int partID) {
        boolean wasRemoved = false;
        for (Part part : allParts) {
            if (part.getPartID() == partID) {
                System.out.println(part.getName() + " was removed.");
                allParts.remove(part);
                wasRemoved = true;
                break;
            } 
        } 
        return wasRemoved;
    }
    
    // returns an arraylist of parts matching the String input
    public ArrayList<Part> lookupPart(String name) {
        ArrayList<Part> parts = new ArrayList();
        for (int i = 0; i < allParts.size(); i++) {
            System.out.println(allParts.get(i).getName());
            if (allParts.get(i).getName().contains(name)) { //try contains/equals
                parts.add(allParts.get(i));
            }
        }
        return parts;
    }
    
    // looks up parts based off of partID
    private Part lookupPart(int partID) {
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).getPartID() == partID) {
                return allParts.get(i);
            }
        }
        return null;
    }
 
/*  This update part is no longer utilized
    public void updatePart(int lookupPart, String name, int inStock, int max, int min, double price) {
        Part part = lookupPart(lookupPart);
        part.setName(name);
        part.setInStock(inStock);
        part.setMax(max);
        part.setMin(min);
        part.setPrice(price);  
    }
*/
    // replace old part with new part
    public void updatePart(Part oldPart, Part newPart) {
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).equals(oldPart)) {
                allParts.set(i, newPart);
                System.out.println("Part updated.");
            } else {
                System.out.println("Part not found. Could not replace.");
            }
        }
    }
        
    // used for ID autogeneration
    public int assignPartID() {
        if (allParts.isEmpty()) {
            return 1;
        } 
        return allParts.get(allParts.size() - 1).getPartID() + 1;
    }
    
    // used for ID autogeneration
    public int assignProductID() {
        if (products.isEmpty()) {
            return 1;
        }
        return products.get(products.size() - 1).getProductID() + 1;
    }
    
    // used to access allParts
    public ArrayList getAllParts() {
        return allParts;
    }
    // used to access products
    public ArrayList getProducts() {
        return products;
    }
    
} 

