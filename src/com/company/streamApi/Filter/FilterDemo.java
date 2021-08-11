package com.company.streamApi.Filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilterDemo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Stream<Integer> numberStream = numbers.stream();
        numberStream.filter(n -> n%2 ==0).collect(Collectors.toList()).forEach(n -> System.out.println(n+"\t"));
    }

}
