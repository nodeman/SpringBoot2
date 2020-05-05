package org.example.collections.fifth;

import org.junit.Test;

import java.util.*;

import static org.example.collections.fifth.ProductFixtures.door;
import static org.example.collections.fifth.ProductFixtures.floorPanel;
import static org.example.collections.fifth.ProductFixtures.window;

public class MapTest {

    private final int ITERATIONS = 5;
    private final int NUMBER_OF_PRODUCTS = 20000;
    private List<Product> products = generateProducts();

    @Test
    public void mapTest() throws Exception {
        runLookups(new NaiveProductLookupTable());
        runLookups(new MapProductLookupTable());
    }

    public List<Product> generateProducts() {
        final List<Product> products = new ArrayList<>();
        final Random weightGenerator = new Random();
        for (int i = 0; i < NUMBER_OF_PRODUCTS; i++) {
            products.add(new Product(i ,"Product"+i, weightGenerator.nextInt(10)));
            //System.out.println("weightGenerator: "  + products.get(i).getWeight());
        }
        Collections.shuffle(products);
        Collections.shuffle(products);
        Collections.shuffle(products);

        System.out.println(products);

        return products;
    }

    public void runLookups(final ProductLookupTable productLookupTable) {

        final List<Product> products = this.products;
        System.out.println("Running lookups with " + productLookupTable.getClass().getSimpleName());

        for(int i = 0; i < ITERATIONS; i++) {
           final long startTime = System.currentTimeMillis();

           for(Product product: products) {
               productLookupTable.addProduct(product);
           }

           for(Product product: products) {
               final Product result = productLookupTable.lookupById(product.getId());
               if(result != product) {
                   throw new IllegalStateException("Lookup Table returned wrong result for " + product);
               }
           }

           productLookupTable.clear();
           System.out.println(System.currentTimeMillis() - startTime + "ms");
        }
    }

    @Test
    public void viewsOverMaps_test() throws Exception {
        final Map<Integer, Product> idToProduct = new HashMap<>();

        idToProduct.put(1, door);
        idToProduct.put(2, floorPanel);
        idToProduct.put(3, window);

        System.out.println(idToProduct);

        final Set<Integer> ids = idToProduct.keySet();
        System.out.println(ids);
        ids.remove(1);

        System.out.println(ids);
        System.out.println(idToProduct);


        final Collection<Product> products = idToProduct.values();
        System.out.println(products);
        //products.add(floorPanel);

        final Set<Map.Entry<Integer, Product>> entires = idToProduct.entrySet();
        System.out.println(entires);

        for(Map.Entry<Integer, Product> entry:entires) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
            entry.setValue(floorPanel);
        }
        System.out.println(idToProduct);

        //SortedMap
            //K firstKey();
            //K lastKey();
            //SortedMap<K, V> tailMap(E formKey);
            //SortedMap<K, V> headMap(E toKey);
            //SortedMap<K, V> subMap(K fromKey, to toKey);

        //First/Last Entries
            //Map.Entry<K, V> firstEntry();
            //Map.Entry<K, V> lastEntry();
            //Map.Entry<K, V> pollFirstEntry();
            //Map.Entry<K, V> pollLastEntry();

        //Navigating by key
            //Map.Entry<K, V> lowerEntry(K key);
            //Map.Entry<K, V> higherEntry(K key);
            //K lowerKey(K key);
            //K higherKey(K key);
            //Map.Entry<K, V> floorEntry(K key);
            //Map.Entry<K, V> ceilingEntry(K key);
            //K floorKey(K key);
            //K ceilingKey(K key);

        //Reversing the order
            //NavigableMap<K, V> descendingMap()
            //NavigableSet<K> descendingKeySet()
            //NavigableSet<K> navigableKeySet()

            //NavigableMap<K, V> tailMap(E fromKey, boolean incl);
            //NavigableMap<K, V> headMap(E toKey, boolean incl);
            //NavigableMap<K, V> subMap(K fromKey, boolean frominclusive, K toKey, boolean toInclusive);
    }

    @Test
    public void java8Enhancements_test() throws Exception {
        final Product defaultProduct = new Product(-1, "Whatever the customer wants", 100);

        final Map<Integer, Product> idToProduct = new HashMap<>();

        idToProduct.put(1, door);
        idToProduct.put(2, floorPanel);
        idToProduct.put(3, window);

        System.out.println(idToProduct);

        /*
        Product result = idToProduct.getOrDefault(10, defaultProduct);
        System.out.println(result);
        System.out.println(idToProduct.get(10));

        Product result2 = idToProduct.replace(1, new Product(1, "Big Door", 50));
        System.out.println(result2);
        System.out.println(idToProduct.get(1));

        Product result2 = idToProduct.replace(4, new Product(1, "Big Door", 50));
        System.out.println(result2);
        System.out.println(idToProduct.get(4));
        */

        /*
        idToProduct.replaceAll((id, oldProduct) -> new Product(id, oldProduct.getName(), oldProduct.getWeight() + 10));
        System.out.println(idToProduct);
        */

        /*
        Product result = idToProduct.computeIfAbsent(10, (id) -> new Product(id, "Custom Product", 10));
        System.out.println(result);
        System.out.println(idToProduct);
        System.out.println(idToProduct.get(10));

        Product result = idToProduct.computeIfAbsent(2, (id) -> new Product(id, "Custom Product", 10));
        System.out.println(result);
        System.out.println(idToProduct);
        System.out.println(idToProduct.get(2));
        */

        idToProduct.forEach((key, value) -> {
            System.out.println(key + " -> " + value);
        });
    }


}
