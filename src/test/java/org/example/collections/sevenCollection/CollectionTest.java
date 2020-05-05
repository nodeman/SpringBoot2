package org.example.collections.sevenCollection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionTest {

    @Test
    public void rotate_test() throws Exception {
        List<Product> products = new ArrayList<>();
        products.add(ProductFixtures.door);
        products.add(ProductFixtures.floorPanel);
        products.add(ProductFixtures.window);

        System.out.println(products);

        Collections.rotate(products, 1);
        System.out.println(products);

        Collections.rotate(products, 1);
        System.out.println(products);

        Collections.rotate(products, 1);
        System.out.println(products);
    }

    @Test
    public void shuffle_test() throws Exception {
        List<Product> products = new ArrayList<>();
        products.add(ProductFixtures.door);
        products.add(ProductFixtures.floorPanel);
        products.add(ProductFixtures.window);

        System.out.println(products);

        Collections.shuffle(products);
        System.out.println(products);

        Collections.shuffle(products);
        System.out.println(products);

        Collections.shuffle(products);
        System.out.println(products);
    }

    @Test
    public void sort_test() throws Exception {
        List<Product> products = new ArrayList<>();
        products.add(ProductFixtures.door);
        products.add(ProductFixtures.floorPanel);
        products.add(ProductFixtures.window);

        System.out.println(products);

        Collections.sort(products, Product.BY_NAME);
        System.out.println(products);

        Collections.sort(products, Product.BY_WEIGHT);
        System.out.println(products);

    }

    @Test
    public void utility_test() throws Exception {
        Product door = ProductFixtures.door;
        Product floorPanel = ProductFixtures.floorPanel;
        Product window = ProductFixtures.window;

        List<Product> products = new ArrayList<>();
        Collections.addAll(products, door, floorPanel, window);
        System.out.println(products);
    }

}
