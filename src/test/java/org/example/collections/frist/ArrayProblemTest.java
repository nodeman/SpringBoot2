package org.example.collections.frist;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class ArrayProblemTest {

    @Test
    public void array_test() {
        System.out.println("test");

        Product door = new Product("Wooden Door", 35);
        Product floorPanel = new Product("Floor Panel", 25);

        // Create
        Product[] products = {door, floorPanel};

        // Print
        System.out.println(Arrays.toString(products));

        // Add
        System.out.println(products[1]);
        final Product window = new Product("window", 15);
        //products[2] = window;
        products = add(window, products);
        System.out.println(Arrays.toString(products));

        // Duplicate
        products = add(window, products);
        System.out.println(Arrays.toString(products));

        //int nums[] = {1, 2, 3, 4, 5, 6};
        //Collections.shuffle(Arrays.asList(nums));
    }

    private static Product[] add(Product product, Product[] array){
        int length = array.length;
        Product[] newArray = Arrays.copyOf(array, length + 1);
        newArray[length] = product;
        return newArray;
    }

    @Test
    public void collection_test() {

        // 배열을 다루기쉬운 메쏘드들이 있는 컬렉션객체로 만들어쓰고 루핑하기쉽게 컬렉션을 이터레이터로 만들어 쓴다.
        // 한 컬렉션의 이터레이터는 그 컬렉션과 같은놈이다.

        Product door = new Product("Wooden Door", 35);
        Product floorPanel = new Product("Floor Panel", 25);
        Product window = new Product("window", 15);

        Collection<Product> products = new ArrayList<>();

        products.add(door);
        products.add(floorPanel);
        products.add(window);

        System.out.println(products);

        final Iterator<Product> productIterator = products.iterator();
        System.out.println(products.size());


        while(productIterator.hasNext()) {
            Product product = productIterator.next();
            if(product.getWeight() > 20) {
                System.out.println(product);
            } else {
                productIterator.remove();
            }
        }

        /*
        for(Product product: products) {
            if(product.getWeight() > 20) {
                System.out.println(product);
            } else {
                //products.clear();  //에러
                //products.remove(product); //에러
                //products.add(window);  //에러
            }
        }
        */

        System.out.println(products.size());
        System.out.println(products.isEmpty());
        System.out.println(products.contains(window));
        System.out.println(products.contains(door));



        System.out.println(products);


        Collection<Product> otherProducts = new ArrayList<>();
        otherProducts.add(window);
        otherProducts.add(door);
        products.removeAll(otherProducts);
        products.remove(floorPanel);

        System.out.println(products);


    }


}
