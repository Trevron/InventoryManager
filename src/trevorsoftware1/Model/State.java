/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trevorsoftware1.Model;

/**
 *
 * @author TrevTop
 */
public class State {
    private static final State state = new State();

    private Inventory inventory;
    private Part selectedPart, modifiedPart;
    
    private State() {
       inventory = new Inventory();
    }
    
    public static State getInstance() {
        return state;
    }
    
    public Inventory getInventory(){
        return inventory;
    }
    
    public Part getSelectedPart() {
        return selectedPart;
    }
    
    public void setSelectedPart(Part selectedPart) {
        this.selectedPart = selectedPart;
    }
    
    public Part getModifiedPart() {
        return modifiedPart;
    }
    
    public void setModifiedPart(Part modifiedPart) {
        this.modifiedPart = modifiedPart;
    }
    
}
