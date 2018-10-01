package io.sudheer.practice.javastreams;

import org.apache.log4j.Logger;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Streams {

    static final Logger LOGGER = Logger.getLogger(Streams.class);

    private static Employee[] arrayOfEmployees = {
            null,
            new Employee(1, "Jeff Bezos", 25000, true),
            new Employee(2, "Bill Gates", 20000, true),
            new Employee(3, "Mark Zuckerberg3", 30000, true),
            new Employee(5, "Mark Zuckerberg5", 50000, true),
            new Employee(6, "Mark Zuckerberg6", 60000, true),
            new Employee(4, "Mark Zuckerberg4", 40000, true)
    };

    public static void main(String[] args) {
        Stream<Employee> empStream = Stream.of(arrayOfEmployees);
        printStream(empStream);
        //otherWaysToCreateStream();
    }

    /**
     * To print the stream
     * @param stream
     */
    private static void printStream(Stream<Employee> stream) {
        AtomicInteger ordinal = new AtomicInteger(0);
        stream.filter(Objects::nonNull).filter(x -> x.getSalary() > 10000).limit(4).forEach(x ->
            {
                LOGGER.info("Name = " + x.getName() + "; Salary = " + x.getSalary());
                ordinal.getAndIncrement();
            });
        LOGGER.info("Count = " + ordinal.get());

        /**
         * To print matched items; If none matches, print a message
         */
        //Employee e = stream.filter(Objects::nonNull).filter(x -> x.getSalary() < 10000).limit(4).findAny().orElse(null);
        //LOGGER.info(e != null ? e.toString() : "None Matched");
    }

    /**
     * Other ways to create stream of objects
     */
    private static void otherWaysToCreateStream() {
        Stream<Employee> empStream;
        empStream = Stream.of(arrayOfEmployees[0], arrayOfEmployees[1], arrayOfEmployees[2], arrayOfEmployees[3]);
        printStream(empStream);

        Stream.Builder<Employee> empStreamBuilder = Stream.builder();
        empStreamBuilder.accept(arrayOfEmployees[0]);
        empStreamBuilder.accept(arrayOfEmployees[1]);
        empStreamBuilder.accept(arrayOfEmployees[2]);
        empStreamBuilder.accept(arrayOfEmployees[3]);
        empStream = empStreamBuilder.build();
        printStream(empStream);
    }
}
