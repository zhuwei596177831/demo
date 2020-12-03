package com.example.getbean.getEarlyBeanReference.transaction;

import java.sql.Connection;

/**
 * @author 朱伟伟
 * @date 2020-12-03 13:56:12
 * @description
 */
public interface PropagationAndIsolation {
    // 7种类型的事务传播行为
    int PROPAGATION_REQUIRED = 0; // 如果当前没有事务，就新建一个事务，如果已经存在一个事务中，加入到这个事务中。这是最常见的选择。
    int PROPAGATION_SUPPORTS = 1; //支持当前事务，如果当前没有事务，就以**非事务**方式执行
    int PROPAGATION_MANDATORY = 2; //使用当前的事务，如果当前没有事务，就抛出异常
    int PROPAGATION_REQUIRES_NEW = 3; //新建事务，如果当前存在事务，把当前事务挂起  ===== 这个也是使用较多的  相当于另起炉灶
    int PROPAGATION_NOT_SUPPORTED = 4; //以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。
    int PROPAGATION_NEVER = 5; //以非事务方式执行，如果当前存在事务，则抛出异常
    int PROPAGATION_NESTED = 6; //如果当前存在事务，则在嵌套事务(它是一个子事务，但他仍还是外部事务的一部分，外部事务提交了它才提交。。。注意和REQUIRES_NEW的区别~~~)内执行。
    //如果当前没有事务，则执行与PROPAGATION_REQUIRED类似的操作

    // 4种：隔离级别
    // PlatformTransactionManager的默认隔离级别（对大多数数据库来说就是ISOLATION_ READ_COMMITTED
    // MySQL默认采用ISOLATION_REPEATABLE_READ，Oracle采用READ__COMMITTED级别
    int ISOLATION_DEFAULT = -1;
    //读未提交 最低的隔离级别。(事实上我们不应该称其为隔离级别)  可能导致脏，幻，不可重复读
    int ISOLATION_READ_UNCOMMITTED = Connection.TRANSACTION_READ_UNCOMMITTED;
    // 读已提交，大多数数据库的默认级别。可防止脏读，但幻读和不可重复读仍可以发生。
    int ISOLATION_READ_COMMITTED = Connection.TRANSACTION_READ_COMMITTED;
    // 可重复读。该隔离级别确保如果在事务中查询了某个数据集，你至少还能再次查询到相同的数据集，即使其他事务修改了所查询的数据。
    // 可防止脏读，不可重复读，但幻读仍可能发生。
    int ISOLATION_REPEATABLE_READ = Connection.TRANSACTION_REPEATABLE_READ;
    // 序列化。代价最大、可靠性最高的隔离级别   所有的事务都是按顺序一个接一个地执行。避免所有不安全读取。
    int ISOLATION_SERIALIZABLE = Connection.TRANSACTION_SERIALIZABLE;

    // 默认的超时时间 -1表示不超时（单位是秒）
    // 如果超过该时间限制但事务还没有完成，则自动回滚事务
    int TIMEOUT_DEFAULT = -1;

    int getPropagationBehavior(); //返回事务的传播行为（一共7种）
    int getIsolationLevel(); // 返回事务的隔离级别。 事务管理器根据它来控制另外一个事务可以看到本事务内的哪些数据
    int getTimeout(); // 返回超时时间(事务必须在多少秒内完成)
    boolean isReadOnly(); // 事务是否只读(事务管理器能够根据这个返回值进行优化，确保事务是只读的)
    String getName(); // 事务的名字 可以为null

}
