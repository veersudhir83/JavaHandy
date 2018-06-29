package io.sudheer.practice.simple;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;

public class ListsMergeAndSort {

    public static void main(String[] args) {
        List<OptedValueDTO> explicit = new ArrayList(), implicit = new ArrayList();

        explicit.add(new OptedValueDTO(0, 55611, "Men's Shoes"));
        explicit.add(new OptedValueDTO(0, 55971, "Home Decor"));
        explicit.add(new OptedValueDTO(0, 29391, "Furniture"));
        explicit.add(new OptedValueDTO(0, 22050, "Men's Clothing"));
        explicit.add(new OptedValueDTO(0, 57386, "Men's Watches"));

        implicit.add(new OptedValueDTO(2, 60451, "Guys"));
        implicit.add(new OptedValueDTO(1, 22050, "Men's Clothing"));
        implicit.add(new OptedValueDTO(3, 60451, "Men's Watches"));

        List<OptedValueDTO> allList=interleaveSortModified(explicit, implicit);
    }

    private static<T> List<OptedValueDTO> interleaveSortModified(List<OptedValueDTO> list1, List<OptedValueDTO> list2) {
        // sort the individual lists based on ranking
        List<OptedValueDTO> explict = (ArrayList) list1.stream().sorted(Comparator.comparing(OptedValueDTO::getRank)).collect(Collectors.toList());
        List<OptedValueDTO> implicit = (ArrayList) list2.stream().sorted(Comparator.comparing(OptedValueDTO::getRank)).collect(Collectors.toList());

        List<OptedValueDTO> result = new ArrayList<>(explict.size() + implicit.size());

        // we should add the left-over elements
        result.addAll(explict);
        result.addAll(implicit);

        return result;
    }
}
