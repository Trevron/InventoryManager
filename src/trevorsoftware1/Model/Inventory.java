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

    //  Add product    
    private void addProduct(Product x) {
        products.add(x);
    }

    public boolean removeProduct(int x){
        boolean wasRemoved = false;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).equals(x)){
                products.remove(x);
                wasRemoved = true;
            }
        }
        return wasRemoved;  
    }

    public Product lookupProduct(int x) {
        Product product = null;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).equals(x)) {
                product = products.get(i);
            }
        }
        return product;
    }
    
    public void updateProduct(int x) {
        //IDK
    }
    
    public void addPart(Part x){
        allParts.add(x);
    }
    
    public boolean deletePart(int x) {
        boolean wasRemoved = false;
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).equals(x)) {
                allParts.remove(x);
                wasRemoved = true;
            }
        }
        return wasRemoved;
    }
    
    public Part lookupPart(int x) {
        Part part = null;
        for(int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).equals(x)) {
                part = allParts.get(i);
            }
        }
        return part;
    }
    
    public void updatePart(int x) {
        //IDK
    }
    
} //end 

