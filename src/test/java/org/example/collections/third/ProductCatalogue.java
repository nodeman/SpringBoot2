package org.example.collections.third;

import org.example.collections.frist.Product;

import java.util.*;

public class ProductCatalogue implements Iterable<Product>{


    //private List<Product> products = new ArrayList<>();
    //private Set<Product> products = new HashSet<>();
    //private final Set<Product> products = new TreeSet<>(Product.BY_NAME);
    private final SortedSet<Product> products = new TreeSet<>(Product.BY_WEIGHT);

    public void isSuppliedBy(Supplier supplier) {
        products.addAll(supplier.getProducts());
    }

    public Set<Product> lightVanProducts() {

        Product heaviestLightValProduct = findHeaviestLightValProduct();
        System.out.println(heaviestLightValProduct);
        return products.headSet(heaviestLightValProduct);

    }

    private Product findHeaviestLightValProduct() {
        for (Product product: products) {
            if (product.getWeight() > 20) {
                return product;
            }
        }
        return products.last();
    }

    @Override
    public String toString() {
        return "ProductCatalogue{" +
                "products=" + products +
                '}';
    }

    @Override
    public Iterator<Product> iterator() {
        return products.iterator();
    }
}
