package com.example.mianshi.concurrent;

import com.alibaba.fastjson.JSON;

import java.security.Key;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author 朱伟伟
 * @date 2021-04-20 10:50:51
 * @description
 */
public class MapTest {
    private static final HashMap<String, String> map = new HashMap<>();

    private static final ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<String, AtomicInteger> atomicIntegerConcurrentHashMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
//        Runnable runnable = () -> {
//            while (true) {
//                System.out.println(map.get("token"));
//            }
//        };
//        new Thread(runnable).start();
//        TimeUnit.SECONDS.sleep(5);
//        map.put("token", UUID.randomUUID().toString());
//        System.out.println(map);
//        map.clear();
//        System.out.println(map);


//        concurrentHashMap.put("key", 1);
//        Runnable runnable = () -> {
//            int key = concurrentHashMap.get("key") + 1;
//            concurrentHashMap.put("key", key);
//        };
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        for (int i = 0; i < 1000; i++) {
//            executorService.execute(runnable);
//        }
//        Thread.sleep(3000);
//        System.out.println(concurrentHashMap.get("key"));
//        executorService.shutdown();

//        atomicIntegerConcurrentHashMap.put("key", new AtomicInteger(1));
//        Runnable runnable = () -> atomicIntegerConcurrentHashMap.get("key").getAndIncrement();
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        for (int i = 0; i < 1000; i++) {
//            executorService.execute(runnable);
//        }
//        Thread.sleep(2000);
//        System.out.println(atomicIntegerConcurrentHashMap.get("key"));
//        executorService.shutdown();

//        Map<String, String> putMap = new HashMap<>();
//        String put = putMap.put("a", "b");
//        System.out.println(put);
//        put = putMap.put("c", "d");
//        System.out.println(put);
//        put = putMap.put("c", "e");
//        System.out.println(put);
//
//        System.out.println("=========");
//
//        Map<String, String> computeMap = new HashMap<>();
//        String compute = computeMap.compute("a", (k, v) -> "b");
//        System.out.println(compute);
//        compute = computeMap.compute("a", (k, v) -> "c");
//        System.out.println(compute);
//
//        System.out.println("==========");
//
//        Map<String, String> putIfAbsentMap = new HashMap<>();
//        String putIfAbsent = putIfAbsentMap.putIfAbsent("a", "b");
//        System.out.println(putIfAbsent);
//        putIfAbsent = putIfAbsentMap.putIfAbsent("a", "b");
//        System.out.println(putIfAbsent);
//
//        System.out.println("=========");
//
//        Map<String, String> computeIfAbsentMap = new HashMap<>();
//        String computeIfAbsent = computeIfAbsentMap.computeIfAbsent("a", a -> "b");
//        System.out.println(computeIfAbsent);
//        computeIfAbsent = computeIfAbsentMap.computeIfAbsent("a", a -> "c");
//        System.out.println(computeIfAbsent);


        Map<String, Integer> unsortMap = new HashMap<>();
        unsortMap.put("z", 10);
        unsortMap.put("b", 5);
        unsortMap.put("a", 6);
        unsortMap.put("c", 20);
        unsortMap.put("d", 1);
        unsortMap.put("e", 7);
        unsortMap.put("y", 8);
        unsortMap.put("n", 99);
        unsortMap.put("g", 50);
        unsortMap.put("m", 2);
        unsortMap.put("f", 9);
        System.out.println(unsortMap);
        Map<String, Integer> sortedMap = unsortMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(String::compareTo))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldV, newV) -> oldV, LinkedHashMap::new));
        System.out.println(sortedMap);

        sortedMap = unsortMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue((o1, o2) -> o2 - o1))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (integer, integer2) -> integer, LinkedHashMap::new));
        System.out.println(sortedMap);

        sortedMap.forEach((Key, value) -> System.out.println("key:" + Key + ",value" + value));


        List<Student> students = Arrays.asList(new Student("张三", "语文", 80), new Student("张三", "数学", 70), new Student("张三", "英语", 55),
                new Student("李四", "语文", 65), new Student("李四", "数学", 78), new Student("李四", "英语", 87),
                new Student("王五", "语文", 80), new Student("王五", "数学", 70), new Student("王五", "英语", 55));
        Map<String, Integer> collect = students.stream().collect(Collectors.groupingBy(Student::getName, Collectors.summingInt(Student::getScore)));
        System.out.println(JSON.toJSONString(collect));
        collect = students.stream().collect(Collectors.groupingBy(Student::getSubject, Collectors.summingInt(Student::getScore)));
        System.out.println(JSON.toJSONString(collect));
        System.out.println("==========");
        Map<String, Integer> map = new HashMap<>();
        students.forEach(s -> map.merge(s.getName(), s.getScore(), Integer::sum));
        System.out.println(JSON.toJSONString(map));
        Map<String, Integer> map1 = new HashMap<>();
        students.forEach(s -> map1.merge(s.getSubject(), s.getScore(), Integer::sum));
        System.out.println(JSON.toJSONString(map1));
    }

    private static class Student {
        private String name;
        private String subject;
        private Integer score;

        public Student(String name, String subject, Integer score) {
            this.name = name;
            this.subject = subject;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public Integer getScore() {
            return score;
        }

        public void setScore(Integer score) {
            this.score = score;
        }
    }

}
