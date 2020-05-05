package org.example.collections.sevenCollection;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Comparator;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Product {

    public static Comparator<Product> BY_NAME = Comparator.comparing(Product::getName);
    public static Comparator<Product> BY_WEIGHT = Comparator.comparing(Product::getWeight);

    private final int id;
    private final String name;
    private final int weight;

}
