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
    public void addProduct(Product x) {
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
        
    
    public int assignPartID() {
        System.out.println(allParts.size());
        if (allParts.isEmpty()) {
            return 1;
        } 
        return allParts.get(allParts.size() - 1).getPartID() + 1;
    }
    
    public ArrayList getAllParts() {
        return allParts;
    }
    
    public ArrayList getProducts() {
        return products;
    }
    
} //end 

