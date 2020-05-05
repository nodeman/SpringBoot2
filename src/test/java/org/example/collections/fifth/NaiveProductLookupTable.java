package org.example.collections.fifth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NaiveProductLookupTable implements ProductLookupTable{

    private List<Product> products = new ArrayList<>();

    @Override
    public Product lookupById(int id) {
        for(Product product: products) {
            if(product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void addProduct(Product addProduct) {
        for(Product product: products) {
            if(product.getId() == addProduct.getId()) {
                throw new IllegalArgumentException("Unable to add product. Duplicate id: " + addProduct.getId());
            }
        }
        products.add(addProduct);
    }

    @Override
    public void clear() {
        products.clear();
    }

}
