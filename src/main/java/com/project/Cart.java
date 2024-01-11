package com.project;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class Cart implements Serializable {
    
      private long cartId;
   
      private String type;  

      private Set<Item> items;

      public Cart() {}
    
      public Cart(String type) {
         this.type = type;
      }

      public long getCartId() {
         return cartId;
      }
    
      public void setCartId(long id) {
         this.cartId = id;
      }
    
      public String getType() {
         return type;
      }
    
      public void setType(String type) {
         this.type = type;
      }
    
      public Set<Item> getItems () {
         return this.items;
      }

      public void setItems (Set<Item> items) {
         this.items = items;
      }

	   public List<Object[]> queryItems () {
		   long id = this.getCartId();
		   return Manager.queryTable("SELECT DISTINCT i.cartId, i.name FROM Item i, Cart c WHERE c.cartId = i.cartId AND c.cartId = " + id);
	   }

      @Override
      public String toString () {
         String str = Manager.tableToString(queryItems()).replaceAll("\n", " | ");
         return this.getCartId() + ": " + this.getType() + ", Items: [" + str + "]";
      }
 }