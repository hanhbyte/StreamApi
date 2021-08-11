package com.company.streamApi.limitSkipPeakDemo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LimitSkipPeakDemo {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("abc","DEF","PQR","XYZ","dgfd");

//        words.stream().limit(2).collect(Collectors.toList()).forEach(n -> System.out.println(n));

//        words.stream().skip(2).forEach(n -> System.out.println(n));

//        words.stream().skip(2).limit(2).forEach(n -> System.out.println(n));

//        words.stream().map(n -> n.concat("zz")).collect(Collectors.toList()).forEach(n -> System.out.println(n));

        words.stream().peek(n -> System.out.println(n)).map(n -> n.concat("zz")).collect(Collectors.toList()).forEach(n -> System.out.println(n));
    }
}
