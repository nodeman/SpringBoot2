package org.example.collections.fifth;

public interface ProductLookupTable {

    Product lookupById(int id);

    void addProduct(Product addProduct);

    void clear();

}
