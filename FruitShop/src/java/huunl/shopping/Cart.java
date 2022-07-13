/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huunl.shopping;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class Cart {
    private Map<String, Fruit> cart;

    public Cart() {
    }

    public Cart(Map<String, Fruit> cart) {
        this.cart = cart;
    }

    public Map<String, Fruit> getCart() {
        return cart;
    }

    public void setCart(Map<String, Fruit> cart) {
        this.cart = cart;
    }
    
    public void add(Fruit fruit){
        if(this.cart==null){
            this.cart = new HashMap<>();
        }
        if(this.cart.containsKey(fruit.getId())){
            int currentQuantity = this.cart.get(fruit.getId()).getQuantity();
            fruit.setQuantity(currentQuantity + fruit.getQuantity());
        }
        cart.put(fruit.getId(), fruit);
    }
    
    public void delete(String id){
        if(this.cart==null){
            return;
        }
        if(this.cart.containsKey(id)){
            this.cart.remove(id);
        }
    }
    
    public void update(String id, Fruit newFruit){
        if(this.cart == null) return;
        if(this.cart.containsKey(id)){
            this.cart.replace(id, newFruit);
        }
    }
    
    public void cleanCart(){
        if(this.cart != null){
            this.cart.clear();
        }
    }
}
