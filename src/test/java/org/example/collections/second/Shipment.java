package org.example.collections.second;

import org.example.collections.frist.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Shipment implements Iterable<Product> {

    private static final int LIGHT_VAL_MAX_WEIGHT = 20;
    private static final int PRODUCT_NOT_PRESENT = -1;
    private final List<Product> products = new ArrayList<>();

    private List<Product> lightVanProducts;
    private List<Product> heavyVanProducts;

    public void add(Product product) {
        products.add(product);
    }

    public void replace(Product oldProduct, Product newProduct) {
        final int index = products.indexOf(oldProduct);
        //System.out.println(index);
        if(index != PRODUCT_NOT_PRESENT) {
            products.set(index, newProduct);
        }
    }

    public void prepare() {
        // sort our list of products by weight
        // Collections.sort(products, Product.BY_WEIGHT);
        products.sort(Product.BY_WEIGHT);

        // find the product index that needs the heavy van
        int splitPoint = findSplitPoint();
        System.out.println(splitPoint);

        // assign views of the product list for heavy and light vans
        lightVanProducts = products.subList(0, splitPoint);
        heavyVanProducts = products.subList(splitPoint, products.size());
    }

    private int findSplitPoint() {
        for (int i = 0; i < products.size(); i++) {
            final Product product = products.get(i);
            if(product.getWeight() > LIGHT_VAL_MAX_WEIGHT) {
                return i;
            }
        }
        return 0;
    }

    public List<Product> getLightVanProducts() {
        return lightVanProducts;
    }

    public List<Product> getHeavyVanProducts() {
        return heavyVanProducts;
    }

    @Override
    public Iterator<Product> iterator() {
        return products.iterator();
    }


}
