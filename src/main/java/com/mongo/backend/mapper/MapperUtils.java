package com.mongo.backend.mapper;

import com.mongo.backend.error.StringSearch;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapperUtils {
    public static <T, R> R mapIfNotNull(Function<T, R> mapperFn, T arg) {
        if (arg == null) {
            return null;
        } else {
            return mapperFn.apply(arg);
        }
    }
    public static <T> T checkNull(T arg) {
        if (arg == null) {
            return null;
        } else {
            return arg;
        }
    }

    public static <T, R> List<R> mapListIfNotNull(Function<T, R> mapper, List<T> list) {
        if (list == null) {
            return null;
        }
        return list.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }

    public Integer addOne(Integer x) {
        return x + 1;
    }
    void ans(){
        Integer result1 = mapIfNotNull(this::addOne, null);  // result1 will be 6
        Integer result2 = mapIfNotNull(this::addOne, null);  // result2 will be null
        System.out.println(result1);
        System.out.println(result2);
    }
    public static void main(String[] args) {
        StringSearch ss = new StringSearch();
        ss.ans();
    }
}
