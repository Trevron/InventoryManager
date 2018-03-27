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

    public Product lookupProduct(int x) {
        for (Product product : products) {
            if (product.getProductID() == x) {
                System.out.println("The product with the ID " + x + " is called " + product.getName());
                return product;
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
    
    
    public int lookupPart(String name) {
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).getName().equals(name)) {
                return i;
            }
        } return -1;
    }
    
    public Part lookupPart(int x) {
        if (x >= 0) {           
            System.out.println("Part: " + allParts.get(x).getName() + "found.");
            return allParts.get(x);
        } else {
            System.out.println("Part not found.");
            return null;
        }
    }
/*    public Part lookupPart(int x) {
        for (Part part : allParts) {
            if (part.getPartID() == x) {
                System.out.println("The name of the part with the ID " + x +" is " + part.getName());
                return part;
            } 
        }
        System.out.println("Part not found.");
        return null;
    }
 */   
    public void updatePart(int x) {
        for (Part part : allParts) {
            if (part.getPartID() == x) {
                System.out.println("The name of the part with the ID " + x +" was updated");
                
            } 
        } 
        
    }
    
} //end 

