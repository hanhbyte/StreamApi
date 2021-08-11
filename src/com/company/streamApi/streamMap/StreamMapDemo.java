package com.company.streamApi.streamMap;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamMapDemo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Stream<Integer> numberStream = numbers.stream();
        numberStream.map(n -> n*n).collect(Collectors.toList()).forEach(n -> System.out.println(n));
    }
}
