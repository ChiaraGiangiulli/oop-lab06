package it.unibo.collections;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {

    private UseListsAndMaps() {
    }
    private static final int HEAD = 0;
    private static final double ELEMS = 100000;
    private static final int MIN = 1000;
    private static final int MAX = 2000;
    /**
     * @param s
     *            unused
     */
    public static void main(final String... s) {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
            final List<Integer> list = new ArrayList<>();
            for ( int i = MIN ; i < MAX ; i++ ){
                list.add(i);
            }
        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
            final List<Integer> linkedList = new LinkedList<>();
            linkedList.addAll(list);
        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */
            final Integer temp = list.get(list.size()-1);
            list.set(list.size()-1, list.get(HEAD)); 
            list.set(HEAD, temp);
        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
            for (Integer i : list){
                System.out.println(i);
            }
        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */
            long time = System.nanoTime();
            for (int i = 1; i <= ELEMS; i++) {
                list.add(HEAD, i);
            }
            time = System.nanoTime() - time;
            var ms = TimeUnit.NANOSECONDS.toMillis(time);
            System.out.println(// NOPMD
                "Inserting "
                    + ELEMS
                    + " elements in the head of the list took "
                    + time
                    + "ns ("
                    + ms
                    + "ms)"
            );
            time = System.nanoTime();
            for (int i = 1; i <= ELEMS; i++) {
                linkedList.add(HEAD, i);
            }
            time = System.nanoTime() - time;
            ms = TimeUnit.NANOSECONDS.toMillis(time);
            System.out.println(// NOPMD
            "Inserting "
                + ELEMS
                + " elements in the head of the linked list took "
                + time
                + "ns ("
                + ms
                + "ms)"
            );
        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example PerfTest.java.
         */
            time = System.nanoTime();
            for (int i = HEAD ; i < MIN ; i++){
                list.get(list.size()/2);
            }
            time = System.nanoTime() - time;
            ms = TimeUnit.NANOSECONDS.toMillis(time);
            System.out.println(// NOPMD
            "Reading "
                + "  1000 times the element in the middle of the list took "
                + time
                + "ns ("
                + ms
                + "ms)"
            );
            
            for (int i = HEAD ; i < MIN ; i++){
                linkedList.get(linkedList.size()/2);
            }
            time = System.nanoTime() - time;
            ms = TimeUnit.NANOSECONDS.toMillis(time);
            System.out.println(// NOPMD
            "Reading "
                + "  1000 times the element in the middle of the linked list took "
                + time
                + "ns ("
                + ms
                + "ms)"
            );
            
        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */
            Map <String, Long> map = new HashMap<>();
            map.put("Africa", 1_110_635_000L);
            map.put("Americas", 972_005_000L);
            map.put("Antarctica", 0L);
            map.put("Asia", 4_298_723_000L);
            map.put("Europe", 742_452_000L);
            map.put("Oceania", 38_304_000L);

        /*
         * 8) Compute the population of the world
         */
            long worldPopulation = 0;
            for (final long population : map.values()) {
                worldPopulation += population;
            }
            System.out.println("World's population: " + worldPopulation);
    }
}
