package com.company.streamApi.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamDemo {
    public static void main(String[] args) {
        List<String> word = Arrays.asList("Roy", "Tiwari", "Aaron", "Marco", "Antonio");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        Optional<String> textString = word.stream().reduce((element1, element2) ->element1.concat(element2));

        System.out.println(textString.get());

        String text1 = word.stream().reduce("concatinating string is :",(element1, element2) ->element1.concat(element2));
        System.out.println(text1);

        Optional<String> longest = word.stream().reduce((element1, element2)-> element1.length() > element2.length() ? element1 : element2);
        System.out.println("longest string : " +longest.get());

        Optional<Integer> sum = numbers.stream().reduce((i,j) -> i+j);
        System.out.println("sum : "+sum.get());

        int sum1 = numbers.stream().reduce(10,(i,j) -> i+j);
        System.out.println("sum : " +sum1);

        Optional<Integer> mult = numbers.stream().reduce((i,j) -> i+j);
        System.out.println("mult : "+mult.get());

        int mult1 = numbers.stream().reduce(2,(i,j) -> i+j);
        System.out.println("mult : "+mult.get());

        Optional<Integer> min = numbers.stream().reduce(Integer :: max);
        System.out.println(min.get());
    }
}
