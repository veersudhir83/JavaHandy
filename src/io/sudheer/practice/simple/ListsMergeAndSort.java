package io.sudheer.practice.simple;

import java.util.*;
import java.util.stream.Collectors;
import org.apache.commons.collections.ListUtils;

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
        List<OptedValueDTO> implicitWith0Ranks = new ArrayList<>(implicit.stream()
                .peek(f -> f.setRank(0))
                .collect(Collectors.toList()));

        List<OptedValueDTO> result = new ArrayList<>(explict.size() + implicit.size());
        List<OptedValueDTO> intersection = ListUtils.intersection(explict, implicitWith0Ranks);

        explict.removeAll(implicitWith0Ranks);
        implicitWith0Ranks.removeAll(intersection);
        // we should add the left-over elements
        result.addAll(intersection);
        result.addAll(explict);
        result.addAll(implicitWith0Ranks);

        return result;
    }
}
