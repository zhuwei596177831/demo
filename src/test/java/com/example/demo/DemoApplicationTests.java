package com.example.demo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.redis.FastJsonRedisEntity;
import com.example.redis.FastJsonRedisTemplate;
import com.example.redis.JacksonRedisEntity;
import com.example.redis.JacksonRedisTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private RedisTemplate<Object, Object> objectRedisTemplate;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //    @Autowired
//    private RedisTemplate<String, Object> fastJsonRedisTemplate;
    @Autowired
    private FastJsonRedisTemplate fastJsonRedisTemplate;
    @Autowired
    private JacksonRedisTemplate jacksonRedisTemplate;

    @Test
    public void testJacksonRedisTemplate() throws JsonProcessingException {
        try {
            ValueOperations<String, Object> valueOperations = jacksonRedisTemplate.opsForValue();
            JacksonRedisEntity jacksonRedisEntity = new JacksonRedisEntity("朱伟伟", 26);
//            valueOperations.set("jacksonRedisEntity", jacksonRedisEntity);
//            Object o = valueOperations.get("jacksonRedisEntity");
            BoundValueOperations<String, Object> boundValueOps = jacksonRedisTemplate.boundValueOps("jacksonRedisEntity");
            Object o = boundValueOps.get();
            System.out.println(o.getClass());//class java.util.LinkedHashMap
            System.out.println(o);
            String s = JacksonRedisTemplate.objectMapper.writeValueAsString(o);
            JacksonRedisEntity result = JacksonRedisTemplate.objectMapper.readValue(s, JacksonRedisEntity.class);
            System.out.println(result);

//            valueOperations.set("jacksonRedisEntityArray", Arrays.asList(new JacksonRedisEntity("朱伟伟", 26), new JacksonRedisEntity("方明正", 23)));
//            Object o1 = valueOperations.get("jacksonRedisEntityArray");
//            System.out.println(o1.getClass());//class java.util.ArrayList
//            System.out.println(o1);
//            System.out.println();
//            List<JacksonRedisEntity> jacksonRedisEntities = JSON.parseArray(o1.toJSONString(), JacksonRedisEntity.class);
//            System.out.println(jacksonRedisEntities);
        } finally {
            RedisConnectionUtils.unbindConnection(jacksonRedisTemplate.getRequiredConnectionFactory());
        }
    }

    @Test
    public void testFastJsonRedisTemplate() {
        try {
            ValueOperations<String, Object> valueOperations = fastJsonRedisTemplate.opsForValue();
            FastJsonRedisEntity fastJsonFastJsonRedisEntity = new FastJsonRedisEntity("朱伟伟", 26);
            valueOperations.set("fastJsonRedis", fastJsonFastJsonRedisEntity);
            JSONObject jsonObject = (JSONObject) valueOperations.get("fastJsonRedis");
            System.out.println(jsonObject.getClass());
            System.out.println(jsonObject);
            System.out.println();
            System.out.println(JSON.parseObject(jsonObject.toString(), FastJsonRedisEntity.class));
            System.out.println();

            valueOperations.set("jsonArray", Arrays.asList(new FastJsonRedisEntity("朱伟伟", 26), new FastJsonRedisEntity("方明正", 23)));
            JSONArray jsonArray = (JSONArray) valueOperations.get("jsonArray");
            System.out.println(jsonArray.getClass());
            System.out.println(jsonArray);
            System.out.println();
            List<FastJsonRedisEntity> fastJsonRedisEntities = JSON.parseArray(jsonArray.toJSONString(), FastJsonRedisEntity.class);
            System.out.println(fastJsonRedisEntities);
            System.out.println();
        } finally {
            RedisConnectionUtils.unbindConnection(fastJsonRedisTemplate.getRequiredConnectionFactory());
        }
    }

    @Test
    public void testObject() {
        ValueOperations<Object, Object> valueOperations = objectRedisTemplate.opsForValue();
//        FastJsonRedisEntity redisEntity = new FastJsonRedisEntity("朱伟伟", 26);
//        valueOperations.set("objectRedis", redisEntity);
        System.out.println(valueOperations.get("objectRedis").getClass());
        System.out.println(valueOperations.get("objectRedis"));
    }

    @Test
    public void test() {
        System.out.println(objectRedisTemplate.getDefaultSerializer().getClass());
        System.out.println(objectRedisTemplate.getKeySerializer().getClass());
        System.out.println(objectRedisTemplate.getValueSerializer().getClass());
        System.out.println(objectRedisTemplate.getHashKeySerializer().getClass());
        System.out.println(objectRedisTemplate.getHashValueSerializer().getClass());
        System.out.println();
        System.out.println(redisTemplate.getDefaultSerializer().getClass());
        System.out.println(redisTemplate.getKeySerializer().getClass());
        System.out.println(redisTemplate.getValueSerializer().getClass());
        System.out.println(redisTemplate.getHashKeySerializer().getClass());
        System.out.println(redisTemplate.getHashValueSerializer().getClass());
        System.out.println();
        System.out.println(stringRedisTemplate.getDefaultSerializer().getClass());
        System.out.println(stringRedisTemplate.getKeySerializer().getClass());
        System.out.println(stringRedisTemplate.getValueSerializer().getClass());
        System.out.println(stringRedisTemplate.getHashKeySerializer().getClass());
        System.out.println(stringRedisTemplate.getHashValueSerializer().getClass());
        System.out.println();
        System.out.println(fastJsonRedisTemplate.getDefaultSerializer().getClass());
        System.out.println(fastJsonRedisTemplate.getKeySerializer().getClass());
        System.out.println(fastJsonRedisTemplate.getValueSerializer().getClass());
        System.out.println(fastJsonRedisTemplate.getHashKeySerializer().getClass());
        System.out.println(fastJsonRedisTemplate.getHashValueSerializer().getClass());
    }

    @Test
    void testOpsForValue() {
        try {
//            redisTemplate.opsForValue().set("set", "set");
//            redisTemplate.opsForValue().set("setTimeout", "setTimeout", 10, TimeUnit.SECONDS);
//            redisTemplate.opsForValue().setIfAbsent("set", "set1");
//            redisTemplate.opsForValue().setIfPresent("set", "set1");
//            redisTemplate.opsForValue().setIfAbsent("setIfAbsent", "setIfAbsent");
//            Map<String, String> map = new HashMap<>();
//            map.put("key1", "value1");
//            map.put("key2", "value2");
//            redisTemplate.opsForValue().multiSet(map);
//            System.out.println(redisTemplate.opsForValue().get("set"));
//            System.out.println(redisTemplate.opsForValue().getAndSet("setIfAbsent", "setIfAbsent1"));
//            System.out.println(redisTemplate.opsForValue().multiGet(new ArrayList<>()));
//            System.out.println(redisTemplate.opsForValue().multiGet(Arrays.asList("key1", "key2")));
//
//            redisTemplate.opsForValue().set("intKey", new Random().nextInt(5) + "");
//            System.out.println(redisTemplate.opsForValue().increment("intKey"));
//            System.out.println(redisTemplate.opsForValue().increment("intKey", 3));
//            System.out.println(redisTemplate.opsForValue().decrement("intKey", 3));
//            redisTemplate.opsForValue().set("appendKey", "Hello");
//            System.out.println(redisTemplate.opsForValue().append("appendKey", ",World"));//11
//            System.out.println(redisTemplate.opsForValue().get("appendKey", 1, 3));//ell
//            System.out.println(redisTemplate.opsForValue().size("appendKey"));//11
//            System.out.println(redisTemplate.opsForValue().size("intKey"));//1
        } finally {
            RedisConnectionUtils.unbindConnection(redisTemplate.getRequiredConnectionFactory());
        }
    }

    @Test
    public void testOpsForHash() {
        try {
            redisTemplate.opsForHash().put("testOpsForHash", "hashPut", "hashPut");
            Map<String, String> map = new HashMap<>();
            map.put("id", UUID.randomUUID().toString());
            map.put("age", new Random().nextInt(30) + "");
            redisTemplate.opsForHash().putAll("testOpsForHash", map);
            System.out.println(redisTemplate.opsForHash().get("testOpsForHash", "id"));
            System.out.println(redisTemplate.opsForHash().hasKey("testOpsForHash", "age"));
            System.out.println(redisTemplate.opsForHash().multiGet("testOpsForHash", new ArrayList<>()));
            System.out.println(redisTemplate.opsForHash().multiGet("testOpsForHash", Arrays.asList("hashPut", "id", "age")));
            System.out.println(redisTemplate.opsForHash().delete("testOpsForHash", "hashPut"));
            redisTemplate.opsForHash().put("testOpsForHash", "incrementKey", "1");
            System.out.println(redisTemplate.opsForHash().increment("testOpsForHash", "incrementKey", 1));
            System.out.println(redisTemplate.opsForHash().increment("testOpsForHash", "incrementKey", 25.55));
            System.out.println(redisTemplate.opsForHash().keys("testOpsForHash"));
            System.out.println(redisTemplate.opsForHash().lengthOfValue("testOpsForHash", "id"));
            System.out.println(redisTemplate.opsForHash().size("testOpsForHash"));
            System.out.println(redisTemplate.opsForHash().values("testOpsForHash"));
            System.out.println(redisTemplate.opsForHash().entries("testOpsForHash"));
        } finally {
            RedisConnectionUtils.unbindConnection(redisTemplate.getRequiredConnectionFactory());
        }
    }

    @Test
    public void testOpsForList() {
        try {
            ListOperations<String, String> listOperations = redisTemplate.opsForList();
            System.out.println(listOperations.leftPush("opsForList", new Random().nextInt(10) + ""));
            System.out.println(listOperations.rightPush("opsForList", "10"));
            listOperations.set("opsForList", 0, UUID.randomUUID().toString());
            listOperations.leftPushAll("opsForList", UUID.randomUUID().toString(), UUID.randomUUID().toString());
            System.out.println(listOperations.rightPushAll("opsForList", UUID.randomUUID().toString(), UUID.randomUUID().toString()));//list size
            System.out.println(listOperations.index("opsForList", 0));
            System.out.println(listOperations.index("opsForList", 20));
            System.out.println(listOperations.leftPop("opsForList"));
            System.out.println(listOperations.leftPop("opsForList", 20, TimeUnit.SECONDS));//20s 容忍超时

            listOperations.rightPushAll("opsForList", "zhuweiwei", "朱伟伟", "zhuweiwei");
            listOperations.remove("opsForList", 2, "zhuweiwei");


            listOperations.leftPushAll("destinationKey", "哈哈哈");
            listOperations.rightPopAndLeftPush("opsForList", "destinationKey");
        } finally {
            RedisConnectionUtils.unbindConnection(redisTemplate.getRequiredConnectionFactory());
        }
    }

    @Test
    public void testOpsForSet() {
        try {
            SetOperations<String, String> setOperations = redisTemplate.opsForSet();
            setOperations.add("setOperations", "aaa", "bbb", "aaa", "ggg", "ddd");
            System.out.println(setOperations.isMember("setOperations", "bbb"));
            System.out.println(setOperations.isMember("setOperations", "hhh"));
            System.out.println(setOperations.size("setOperations"));

            setOperations.add("setOperations1", "aaa", "ggg", "ttt");
//        相交数据
            System.out.println(setOperations.intersect("setOperations", "setOperations1"));
//        取交集并存储
            System.out.println(setOperations.intersectAndStore("setOperations", "setOperations1", "setOperations2"));
//        合并数据
            System.out.println(setOperations.union("setOperations", "setOperations1"));
//        合并并存储
            System.out.println(setOperations.unionAndStore("setOperations", "setOperations1", "setOperations3"));
//        setOperations中不在setOperations1的数据
            System.out.println(setOperations.difference("setOperations", "setOperations1"));
//        setOperations中不在setOperations1的数据存储在setOperations4
            System.out.println(setOperations.differenceAndStore("setOperations", "setOperations1", "setOperations4"));
//        setOperations1中不在setOperations的数据存储在setOperations5
            System.out.println(setOperations.differenceAndStore("setOperations1", "setOperations", "setOperations5"));
            System.out.println(setOperations.members("setOperations"));
            System.out.println(setOperations.randomMember("setOperations"));
            System.out.println(setOperations.randomMembers("setOperations", 2));
        } finally {
            RedisConnectionUtils.unbindConnection(redisTemplate.getRequiredConnectionFactory());
        }
    }

    @Test
    public void testZSetOperations() {
        try {
            ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
//            zSetOperations.add("zSetOperations", "aaa", 1);
//            zSetOperations.add("zSetOperations", "bbb", 2);
//            zSetOperations.add("zSetOperations", "ggg", 3);
//            zSetOperations.add("zSetOperations", "ddd", 4);
//            Set<ZSetOperations.TypedTuple<String>> typedTuples = new HashSet<>();
//            typedTuples.add(new DefaultTypedTuple<>("朱伟伟", (double) 1000));
//            zSetOperations.add("zSetOperations", typedTuples);
            System.out.println(zSetOperations.rank("zSetOperations", "朱伟伟"));
            System.out.println(zSetOperations.rank("zSetOperations", "aaa"));
            System.out.println(zSetOperations.range("zSetOperations", 0, 2));
//        默认按分值 升序输出
            Set<ZSetOperations.TypedTuple<String>> typedTupleSet = zSetOperations.rangeWithScores("zSetOperations", 0, 2);
            if (!CollectionUtils.isEmpty(typedTupleSet)) {
                typedTupleSet.forEach(t -> {
                    System.out.println("value:" + t.getValue() + ",score:" + t.getScore());
                });
            }
            zSetOperations.reverseRange("zSetOperations", 0, 3).forEach(System.out::println);
        } finally {
            RedisConnectionUtils.unbindConnection(redisTemplate.getRequiredConnectionFactory());
        }
    }


//    public static void main(String[] args) {
//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfiguration.class);
//    }


}
