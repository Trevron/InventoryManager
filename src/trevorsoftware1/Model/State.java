package trevorsoftware1.Model;

/**
 *
 * @author Trevor Metcalf
 */
public class State {
    /* This state class is used as a singleton to allow data to pass 
    // between multiple FXML controllers. This allows for less clutter
    // and is primarily used to access the inventory, as well as setting
    // a selected product or part that can be accessed globally through
    // a get method. 
    */
    
    // initialize the singleton instance of state
    private static final State state = new State();
    // initialize variables
    private Inventory inventory;
    private Part selectedPart;
    private Product selectedProduct;
    
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
    
    
    public Product getSelectedProduct() {
        return selectedProduct;
    }
    
    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }
}
