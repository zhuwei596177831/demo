package com.jdk.BlockingQueue;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author 朱伟伟
 * @date 2021-01-28 15:36:02
 * @description 有两个线程A, B, A线程每200ms就生成一个[0,100]之间的随机数， B线程每2S中打印出A线程所产生的增量随机数。
 *
 * 基于数组的阻塞队列实现 ArrayBlockingQueue在生产者放入数据和消费者获取数据，都是共用同一个锁对象，由此也意味着两者无法真正并行运行
 * {@link ArrayBlockingQueue}
 *
 * 基于链表的阻塞队列 在高并发的情况下生产者和消费者可以并行地操作队列中的数据，以此来提高整个队列的并发性能
 * 如果构造一个LinkedBlockingQueue对象，而没有指定其容量大小，LinkedBlockingQueue会默认一个类似无限大小的容量（Integer.MAX_VALUE），
 * 这样的话，如果生产者的速度一旦大于消费者的速度，也许还没有等到队列满阻塞产生，系统内存就有可能已被消耗殆尽了
 * {@link LinkedBlockingQueue}
 *
 * DelayQueue中的元素只有当其指定的延迟时间到了，才能够从队列中获取到该元素。
 * DelayQueue是一个没有大小限制的队列，因此往队列中插入数据的操作（生产者）永远不会被阻塞，而只有获取数据的操作（消费者）才会被阻塞，
 * 所以一定要注意内存的使用。
 * {@link DelayQueue}
 *
 * 基于优先级的阻塞队列 不会阻塞数据生产者，而只会在没有可消费的数据时，阻塞数据的消费者。因此使用的时候要特别注意，
 * 生产者生产数据的速度绝对不能快于消费者消费数据的速度，否则时间一长，会最终耗尽所有的可用堆内存空间
 * {@link PriorityBlockingQueue}
 *
 */
public class BlockingQueueTest {
    private static BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>(10);

    public static void main(String[] args) {
        ScheduledExecutorService productExecutor = Executors.newScheduledThreadPool(1);
        ThreadLocalRandom random = ThreadLocalRandom.current();
        productExecutor.scheduleAtFixedRate(() -> {
            int value = random.nextInt(101);
            try {
                //add方法在添加元素的时候，若超出了度列的长度会直接抛出异常：
                //offer方法在添加元素时，如果发现队列已满无法添加的话，会直接返回false。
                //对于put方法，若向队尾添加元素的时候发现队列已经满了会发生阻塞一直等待空间，以加入元素
                boolean offer = blockingQueue.offer(value);//把生成的随机数放进去
                System.out.println(offer);

//                blockingQueue.add(value);  //把生成的随机数放进去

//                blockingQueue.put(value);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }, 0, 200, TimeUnit.MILLISECONDS);  //每200毫秒执行线程

        //用一个线程去循环消费  这里用sleep去实现
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    System.out.println("=============开始取值=============");
                    //poll: 若队列为空，返回null。
                    //remove:若队列为空，抛出NoSuchElementException异常。
                    //take:若队列为空，发生阻塞，等待有元素。

//                    List<Integer> list = new LinkedList<>();
//                    blockingQueue.drainTo(list);  //drainTo()将队列中的值全部从队列中移除，并赋值给对应集合
//                    list.forEach(System.out::println);

                    System.out.println(blockingQueue.take());
//                    System.out.println(blockingQueue.poll());
//                    System.out.println(blockingQueue.poll(1000, TimeUnit.MILLISECONDS));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
