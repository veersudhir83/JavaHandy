package io.sudheer.practice.simple.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SortTest {
    public static void main(String[] args) {
        Stream<Integer> list = Stream.of(1, null, 2, 3, null);

        //Convert a Stream to List
        List<Integer> result = list.filter(Objects::nonNull).collect(Collectors.toList());

        // to print size of the list
        System.out.println(result.size());

        // to print items in the list
        result.forEach(System.out::println);

        List<Integer> list2 = new ArrayList<Integer>(Arrays.asList(null, 1, 2, null, 3, null));
        List<Integer> listWithoutNulls2 = list2.parallelStream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        System.out.println(listWithoutNulls2.size());
    }

}
