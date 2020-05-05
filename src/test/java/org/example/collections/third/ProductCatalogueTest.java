package org.example.collections.third;

import org.example.collections.frist.Product;
import org.junit.Test;
import sun.reflect.generics.tree.Tree;

import java.util.*;

import static org.example.collections.second.ProductFixtures.*;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class ProductCatalogueTest {

    @Test
    public void shouldOnlyHoldUniqueProducts() throws Exception {

        ProductCatalogue catalogue = new ProductCatalogue();

        catalogue.isSuppliedBy(bobs);
        catalogue.isSuppliedBy(kates);

        System.out.println(catalogue);

        assertThat(catalogue, containsInAnyOrder(door, floorPanel));

    }

    @Test
    public void sortedSet_test() throws Exception {
        List<String> animals = Arrays.asList("Dog", "Cat", "Tiger", "Lion", "Elephant");
        System.out.println(animals);

        SortedSet<String> animalSet = new TreeSet<>(animals);
        System.out.println(animalSet.toString());

        Set<String> headSet = animalSet.headSet("Elephant");
        System.out.println("#headSet: " + headSet);

        Set<String> tailSet = animalSet.tailSet("Elephant");
        System.out.println("#tailSet: " + tailSet);

        Set<String> subSet = animalSet.subSet("Dog", "Lion");
        System.out.println("#SubSet: " + subSet);

        String first = animalSet.first();
        System.out.println("#first: " + first);

        String last = animalSet.last();
        System.out.println("#last: " + last);

    }




}
