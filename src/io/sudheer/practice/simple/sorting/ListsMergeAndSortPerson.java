package io.sudheer.practice.simple.sorting;

import java.util.*;

import static java.util.stream.Collectors.toCollection;
import static java.util.Comparator.comparingInt;

public class ListsMergeAndSortPerson {

    public static void main(String[] args) {
        List<Person> list1 = new ArrayList(), list2 = new ArrayList();

        list1.add(new Person("Derp L1", 50));
        list1.add(new Person("Alec L1", 21));
        list1.add(new Person("Herp L1", 21));
        list1.add(new Person("Menard L1", 44));
        list1.add(new Person("Lili L1", 44));
        list1.add(new Person("Alice L1", 12));
        list1.add(new Person("Bob L1", 22));

        list2.add(new Person("Little L2", 5));
        list2.add(new Person("Lili L1", 44));
        list2.add(new Person("Alec L1", 21));
        list2.add(new Person("Herp L1", 21));
        list2.add(new Person("Menard L1", 44));
        list2.add(new Person("Giant L2", 100));
        list2.add(new Person("John L2", 50));
        List<Person> allListNew = interleaveSort(list1, list2, comparingInt(Person::getAge).reversed());

    }

    static<T> List<T> interleaveSort(List<T> list1, List<T> list2, Comparator<T> cmp) {
        // sort the lists and set them up as queues
        Deque<T> x = list1.stream().sorted(cmp).collect(toCollection(ArrayDeque::new));
        Deque<T> y = list2.stream().sorted(cmp).collect(toCollection(ArrayDeque::new));

        List<T> result = new ArrayList<>(x.size() + y.size());

        while (!x.isEmpty() && !y.isEmpty()) {
            if (cmp.compare(x.peek(), y.peek()) > 0) {
                result.add(y.poll());
            } else {
                // whenever we pick from X, swap queues so next time we will favor the other list
                result.add(x.poll());
                Deque<T> t = x;
                x = y;
                y = t;
            }
        }
        // since the while loop terminates when one of the queues is empty
        // we should add the left-over elements
        result.addAll(x);
        result.addAll(y);

        return result;
    }
}
