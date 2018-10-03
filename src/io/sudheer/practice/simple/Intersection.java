package io.sudheer.practice.simple;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Intersection {
    public static void main(String[] args) {
        List<OptedValueDTO> explicitOptedDTOs = new ArrayList<OptedValueDTO>();
        explicitOptedDTOs.add(new OptedValueDTO(22050, "Men's Clothing1", 0, ""));
        explicitOptedDTOs.add(new OptedValueDTO(22051, "Men's Clothing2", 0, ""));
        explicitOptedDTOs.add(new OptedValueDTO(22052, "Men's Clothing3", 0, ""));
        explicitOptedDTOs.add(new OptedValueDTO(22053, "Men's Clothing4", 0, ""));

        List<OptedValueDTO> implicitOptedDTOs = new ArrayList<OptedValueDTO>();
        implicitOptedDTOs.add(new OptedValueDTO(22050, "Men's Clothing11", 1, ""));
        implicitOptedDTOs.add(new OptedValueDTO(22051, "Men's Clothing22", 2, ""));
        implicitOptedDTOs.add(new OptedValueDTO(22052, "Men's Clothing33", 3, ""));
        implicitOptedDTOs.add(new OptedValueDTO(22053, "Men's Clothing44", 4, ""));

        if (CollectionUtils.isEmpty(implicitOptedDTOs)) {
            // If implicitOptedDTOs is empty, then return only explicit ones without any filtering
            explicitOptedDTOs.stream().forEach(f -> f.setXapiPrefType("E"));
        } else if(CollectionUtils.isEmpty(explicitOptedDTOs)) {
            // If explicitOptedDTOs is empty, then return only implicit ones in the order of ranking
            Collections.sort(implicitOptedDTOs, new OptedValueComparator());
            implicitOptedDTOs.stream().forEach(f -> f.setXapiPrefType("I"));
        } else {
            // sort the individual lists based on ranking
            Collections.sort(implicitOptedDTOs, new OptedValueComparator());

            List<OptedValueDTO> implicitWith0Ranks = new ArrayList<>(implicitOptedDTOs.stream()
                    .peek(f -> f.setRank(0))
                    .collect(Collectors.toList()));

            List<OptedValueDTO> finalOrderedList = new ArrayList<>(explicitOptedDTOs.size() + implicitOptedDTOs.size());

            // identify the common objects in both the lists
            List<OptedValueDTO> intersection = ListUtils.intersection(implicitWith0Ranks, explicitOptedDTOs);

            // retain uncommon objects in explicit
            explicitOptedDTOs.removeAll(intersection);

            // retain uncommon objects in implicit
            implicitWith0Ranks.removeAll(intersection);

            //set xapiPrefType as B
            intersection.stream().forEach(f -> f.setXapiPrefType("B"));
            //set xapiPrefType as E
            explicitOptedDTOs.stream().forEach(f -> f.setXapiPrefType("E"));
            //set xapiPrefType as I
            implicitWith0Ranks.stream().forEach(f -> f.setXapiPrefType("I"));


            // we should add the intersection, explicit and implicit lists in an order
            finalOrderedList.addAll(intersection);
            finalOrderedList.addAll(explicitOptedDTOs);
            finalOrderedList.addAll(implicitWith0Ranks);
            System.out.println(finalOrderedList.size());
        }
    }
}
