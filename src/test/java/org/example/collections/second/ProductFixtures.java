package org.example.collections.second;

import org.example.collections.frist.Product;
import org.example.collections.third.Supplier;

public class ProductFixtures {
    public static Product door = new Product("Wooden Door", 35);
    public static Product floorPanel = new Product("Floor Panel", 25);
    public static Product window = new Product("Glass Window", 15);

    public static Supplier bobs = new Supplier("Bob's Supplies");
    public static Supplier kates = new Supplier("Kate's Goods");

    static {
        bobs.getProducts().add(door);
        bobs.getProducts().add(floorPanel);

        kates.getProducts().add(floorPanel);
        kates.getProducts().add(door);
    }
}
