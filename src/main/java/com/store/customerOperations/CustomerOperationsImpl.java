package com.store.customerOperations;

import com.store.exceptions.ProductNotInStockException;
import com.store.exceptions.ProductOutOfStockException;
import com.store.models.Customer;
import com.store.models.Product;
import com.store.models.Store;
import java.util.Map;
public class CustomerOperationsImpl implements CustomerOperations{

    @Override
    public Map<Product, Integer> buyProduct(Customer customer, Store store, Product product, int quantityWanted) throws ProductOutOfStockException, ProductNotInStockException {
         Map<Product, Integer> productMapPlaceHolder = store.getProductMap();

             if (productMapPlaceHolder.containsKey(product) && productMapPlaceHolder.get(product) >= quantityWanted) {
                 if (customer.getCartMap().containsKey(product)) {
                     int totalQuantityWanted = customer.getCartMap().get(product) + quantityWanted;
                     if (totalQuantityWanted > productMapPlaceHolder.get(product) ) {

                         throw new ProductOutOfStockException("Sorry, we don't have up to " + quantityWanted + " " + product.getProductName() +" left in stock");
                     } else {
                         customer.getCartMap().put(product,totalQuantityWanted);

                     }

                 }else {
                     customer.getCartMap().put(product,quantityWanted);
                 }

             } else if (productMapPlaceHolder.containsKey(product) && productMapPlaceHolder.get(product) < quantityWanted) {
                 throw new ProductOutOfStockException("Sorry, we only have " + productMapPlaceHolder.get(product) + " in stock");
             } else {
                 throw new ProductNotInStockException("We don't have "  + product.getProductName());
             }

         return customer.getCartMap();
    }


}
