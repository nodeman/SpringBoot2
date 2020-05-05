package org.example.collections.fifth;

import java.util.HashMap;
import java.util.Map;

public class MapProductLookupTable implements ProductLookupTable{
    //V get(Object key)
    //V remove(Object key)
    //V put(K key, V value)
    //void clear()
    //boolean containsKey(Object key)
    //boolean containsValue(Object value)

    private final Map<Integer, Product> productsMap = new HashMap<>();

    @Override
    public Product lookupById(int id) {
        return productsMap.get(id);
    }

    @Override
    public void addProduct(Product addProduct) {
        if(productsMap.containsKey(addProduct.getId())) {
            throw new IllegalArgumentException("Unable to add product. Duplicate id: " + addProduct.getId());
        }
        productsMap.put(addProduct.getId(), addProduct);
    }

    @Override
    public void clear() {
        productsMap.clear();
    }

}
