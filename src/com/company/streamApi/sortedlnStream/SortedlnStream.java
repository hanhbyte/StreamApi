package com.company.streamApi.sortedlnStream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortedlnStream {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5, 18, 10, 2, 4, 1, 3);
        List<String> words = Arrays.asList("swapnil", "vijay", "pintu", "nilu", "santosh");

//      Sắp xếp chữ số tăng dần
        numbers.stream().sorted().collect(Collectors.toList()).forEach(n -> System.out.println(n + "\t"));
//      Đảo ngược lại thứ tự đã sắp xếp
        numbers.stream().sorted(new IntComparator()).collect(Collectors.toList()).forEach(n -> System.out.println(n + "\t"));


//      Sắp xếp thứ tự theo bảng chữ cái tăng dần
        words.stream().sorted().collect(Collectors.toList()).forEach(n -> System.out.println(n + "\t"));
//      Đảo ngược lại thứ tự đã sắp xếp
        words.stream().sorted(new StringComparator()).collect(Collectors.toList()).forEach(n -> System.out.println(n + "\t"));
//      Sắp xếp theo thứ tự đã nhập
        words.stream().sorted(new StringComparator()).collect(Collectors.toList()).forEach(n -> System.out.println(n+"\t"));
    }
}

class StringComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        if (o1.length() < o2.length()) {
            return -1;
        }
        if (o1.length() > o2.length()) {
            return 1;
        } else
            return 0;

    }
}

class IntComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        if (o1 > o2)
            return -1;
        else if (o1 < o2)
            return 1;
        else
            return 0;
    }
}