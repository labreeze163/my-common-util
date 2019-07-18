package com.jdk8;


import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by hzzhaolong on 19/7/18.
 *
 * Intermediate：
 *      map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 limit、 skip、 parallel、 sequential、 unordered
 *
 * Terminal：
 *      forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator
 *
 * Short-circuiting：
 *      anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit
 *
 */
public class StreamApiTest {


    public static void main(String[] args) {

        List<Dish> dishs = Lists.newArrayList(
                new Dish("meat", 1000), new Dish("vigetable", 50),
                new Dish("beef", 500), new Dish("salad", 50));

        List<String> lowCaloriesNames = getLowCaloriesNames(dishs);
        System.out.println(" lowCaloriesNames = " + JSON.toJSONString(lowCaloriesNames));

        // 1. 生成stream
        // 1.1 Collection.stream()
        dishs.stream();

        // 1.2 Collection.parallelStream()
        dishs.parallelStream();

        // 1.3 Arrays.stream(T array) or Stream.of()
        Stream.of(new Dish("meat", 1000), new Dish("vigetable", 50));
        Arrays.stream(new Dish[] {new Dish("meat", 1000), new Dish("vigetable", 50)});

        // 2. 转换

        // filter
        List<Dish> filteredDishs = dishs.stream().filter((Dish dish) -> dish.getCalories() < 100).collect(Collectors.toList());
        System.out.println(" filteredDishs = " + JSON.toJSONString(filteredDishs));

        // map
        List<String> mapFishs = dishs.stream().map((Dish dish) -> dish.getName()).collect(Collectors.toList());
        System.out.println(" mapFishs = " + JSON.toJSONString(mapFishs));

        // find
        Dish firstDish = dishs.stream().findFirst().get();
        System.out.println(" firstDish = " + JSON.toJSONString(firstDish));

        // forEach
        System.out.print(" forEach = " );
        dishs.stream().forEach((Dish dish) -> System.out.print(dish.getName() + " >> "));
        System.out.println();

        // groupingBy
        Map<Integer, List<Dish>> groupByDishs = dishs.stream().collect(Collectors.groupingBy((Dish dish) -> dish.getCalories()));
        System.out.println(" groupByDishs = " + JSON.toJSONString(groupByDishs));

        Map<Integer, List<Dish>> groupByDishs2 = dishs.stream().collect(Collectors.groupingBy((Dish dish) -> {
            if(dish.getCalories() < 100)
                return 1;
            else if(dish.getCalories() <= 500)
                return 2;
            else
                return 3;
        }));
        System.out.println(" groupByDishs2 = " + JSON.toJSONString(groupByDishs2));

        // partitioningBy
        Map<Boolean, List<Dish>> partitionByDishs = dishs.stream().collect(Collectors.partitioningBy((Dish dish) -> dish.getCalories() < 100));
        System.out.println(" partitionByDishs = " + JSON.toJSONString(partitionByDishs));


        // reduce
        // Optional<T> reduce(BinaryOperator<T> accumulator)
        Optional sumResult = Stream.of(1, 2, 3, 4)
                .reduce((sum, item) -> {
                    sum += item;
                    return sum;
                });
        System.out.println(" sumResult = " + sumResult.get());

        // T reduce(T identity, BinaryOperator<T> accumulator)
        Integer sumResult2 = Stream.of(1, 2, 3, 4)
                .reduce(100, (sum, item) -> {
                    sum += item;
                    return sum;
                });
        System.out.println(" sumResult2 = " + sumResult2);

        // <U> U reduce(U identity, BiFunction<U,? super T,U> accumulator, BinaryOperator<U> combiner)
        Integer sumResult3 = Stream.of(1, 2, 3, 4)
                .reduce(10, (Integer data1, Integer data2) -> {
                    return data1 + data2;
                }, (Integer reduce1, Integer reduce2) -> {
                    return reduce1 + reduce2;
                });
        System.out.println(" sumResult3 = " + sumResult3);

    }


    private static List<String> getLowCaloriesNames(List<Dish> dishs) {
         return dishs.stream()
                .filter((Dish dish) -> dish.getCalories() < 100)
                .sorted((Dish dish1, Dish dish2) -> Integer.compare(dish1.getCalories(), dish2.getCalories()))
                .map((Dish dish) -> dish.getName())
                .collect(Collectors.toList());
    }


    public static class Dish implements Serializable{

        private static final long serialVersionUID = -1167210864509960799L;

        private Integer calories;

        private String name;

        public Dish() {

        }

        public Dish(String name, Integer calories) {
            this.calories = calories;
            this.name = name;
        }

        public Integer getCalories() {
            return calories;
        }

        public void setCalories(Integer calories) {
            this.calories = calories;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}


