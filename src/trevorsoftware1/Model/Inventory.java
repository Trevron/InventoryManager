/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trevorsoftware1.Model;

import java.util.ArrayList;

/**
 *
 * @author treth
 */
public class Inventory {
    
    //  Initialize array lists
    private ArrayList<Product> products;
    private ArrayList<Part> allParts;
    private int partIDCounter = 0;

    public Inventory(){

        products = new ArrayList<>();
        allParts = new ArrayList<>();
    }

    //  Interact with products array list   
    private void addProduct(Product x) {
        products.add(x);
    }

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

    public int lookupProduct(String name) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().contains(name)) { //try contains/equals
                return i;
            }
        } return -1;
    }
    
    public Product lookupProduct(int x) {
        if (x >= 0) {           
            System.out.println("Product: " + products.get(x).getName() + "found.");
            return products.get(x);
        } else {
            System.out.println("Part not found.");
            return null;
        }
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
    
    public boolean deletePart(int x) {
        boolean wasRemoved = false;
        for (Part part : allParts) {
            if (part.getPartID() == x) {
                System.out.println(part.getName() + " was removed.");
                allParts.remove(part);
                wasRemoved = true;
                break;
            } 
        } 
        return wasRemoved;
    }
    
    
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
    
    private Part lookupPart(int x) {
        if (x >= 0) {           
            System.out.println("Part: " + allParts.get(x).getName() + "found.");
            return allParts.get(x);
        } else {
            System.out.println("Part not found.");
            return null;
        }
    }
 
    
    public void updatePart(int lookupPart, int partID, String name, int inStock, int max, int min, double price) {
        Part part = lookupPart(lookupPart);
        part.setPartID(partID);
        part.setName(name);
        part.setInStock(inStock);
        part.setMax(max);
        part.setMin(min);
        part.setPrice(price);
        
    } 
        
    
    public int assignPartID() {
        this.partIDCounter++;
        return partIDCounter;
    }
    
    public ArrayList getAllParts() {
        return allParts;
    }
    
    public ArrayList getProducts() {
        return products;
    }
    
} //end 

