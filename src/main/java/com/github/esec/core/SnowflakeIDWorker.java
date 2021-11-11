package com.github.esec.core;

import com.github.annotation.IdPrefix;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import javax.persistence.Table;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.locks.StampedLock;

@Slf4j
public class SnowflakeIDWorker implements IdentifierGenerator  {

    /**
     * strategy reference
     */
    public final static String STRATEGY_REFERENCE = "com.github.esec.core.SnowflakeIDWorker";

    /**
     * 开始时间截 (2018-01-01)
     */
    private final static long START_TIMESTAMP = 1514736000000L;

    /**
     * 机器id所占的位数
     */
    private final static long WORKER_ID_BITS = 8L;

    /**
     * 数据标识id所占的位数
     */
    private final static long DATA_CENTER_ID_BITS = 8L;

    /**
     * 支持的最大机器id，结果是 pow(2,8) (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
     */
    private final static long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);

    /**
     * 支持的最大数据标识id，结果是 pow(2,8)
     */
    private final static long MAX_DATA_CENTER_ID = ~(-1L << DATA_CENTER_ID_BITS);

    /**
     * 序列在id中占的位数
     */
    private final static long SEQUENCE_BITS = 6L;

    /**
     * 机器ID向左移6位
     */
    private final static long WORKER_ID_SHIFT = SEQUENCE_BITS;

    /**
     * 数据标识id向左移14位(6+8)
     */
    private final static long DATA_CENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;

    /**
     * 时间截向左移22位(6+8+8)
     */
    private final static long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATA_CENTER_ID_BITS;

    /**
     * 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
     */
    private final static long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);

    /**
     *  当前worker id
     */
    private final static long WORKER_ID = 0L;

    /**
     *  当前dataCenter id
     */
    private final static long DATA_CENTER_ID = 0L;

    /**
     * 工作机器ID(0~256) 或 IP地址
     */
    private long workerID;

    /**
     * 数据中心ID(0~256) 或 IP地址
     */
    private long dataCenterID;

    /**
     * 毫秒内序列(0~64)
     */
    private long sequence = 0L;

    /**
     * 上次生成ID的时间截
     */
    private long lastTimestamp = -1L;


    private static StampedLock stampedLock = new StampedLock();

    public SnowflakeIDWorker() {
        this(WORKER_ID, DATA_CENTER_ID);
    }

    /**
     * description:
     * <p>构造函数</p>
     */
    public SnowflakeIDWorker(long workerID, long dataCenterID) {
        if (workerID > MAX_WORKER_ID || workerID < 0) {
            throw new IllegalArgumentException(String.format("worker ID can't be greater than %d or less than 0", MAX_WORKER_ID));
        }
        if (dataCenterID > MAX_DATA_CENTER_ID || dataCenterID < 0) {
            throw new IllegalArgumentException(String.format("dataCenter ID can't be greater than %d or less than 0", MAX_DATA_CENTER_ID));
        }
        this.workerID = workerID;
        this.dataCenterID = dataCenterID;
    }

    // ==============================Methods==========================================

    /**
     * description:
     * <p>获得下一个ID (该方法是线程安全的)<br></p>
     */
    public /*synchronized*/ long nextID() {
        long writeLock = stampedLock.writeLock();
        try {
            long timestamp = timeGen();

            // 如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
            if (timestamp < lastTimestamp) {
                throw new RuntimeException(
                        String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
            }

            // 如果是同一时间生成的，则进行毫秒内序列
            if (lastTimestamp == timestamp) {
                sequence = (sequence + 1) & SEQUENCE_MASK;
                // 毫秒内序列溢出
                if (sequence == 0) {
                    // 阻塞到下一个毫秒,获得新的时间戳
                    timestamp = tilNextMillis(lastTimestamp);
                }
            }
            // 时间戳改变，毫秒内序列重置
            else {
                sequence = 0L;
            }

            // 上次生成ID的时间截
            lastTimestamp = timestamp;

            // 移位并通过或运算拼到一起组成64位的ID
            return ((timestamp - START_TIMESTAMP) << TIMESTAMP_LEFT_SHIFT)
                    | (dataCenterID << DATA_CENTER_ID_SHIFT)
                    | (workerID << WORKER_ID_SHIFT)
                    | sequence;
        } finally {
            stampedLock.unlockWrite(writeLock);
        }

    }

    /**
     * description:
     * <p>阻塞到下一个毫秒，直到获得新的时间戳<br></p>
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * description:
     * <p>返回以毫秒为单位的当前时间<br></p>
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }


    public String getIdPrefix(Class<?> entityClazz) {
        Objects.requireNonNull(entityClazz, "entityClazz can not be null");
        IdPrefix prefixAnnotation = entityClazz.getAnnotation(IdPrefix.class);
        if (Objects.nonNull(prefixAnnotation)) {
            return prefixAnnotation.value();
        }
        Table entity = entityClazz.getAnnotation(Table.class);
        if (Objects.nonNull(entity)) {
            return entity.name();
        }
        return entityClazz.getSimpleName().toUpperCase();
    }


    /**
     * generateId
     *
     * @param clazz
     * @return id
     * @author PlayBoy
     */
    public String generateId(Class<?> clazz) {
        return String.format("%s.%s", getIdPrefix(clazz), nextID());
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object entity) throws HibernateException {
        return generateId(entity.getClass());
    }

    private static volatile SnowflakeIDWorker instance;
    private static final String WORKER_ID_ENV = "WORKER_ID";

    /**
     * Gets the singleton instance for SnowflakeIDWorker.
     */
    public static SnowflakeIDWorker getInstance() {
        long dataCenterID = 0L;
        long workID = 0L;
        if (null == instance) {
            synchronized (SnowflakeIDWorker.class) {
                if (null == instance) {
                    InetAddress localHost = null;
                    try {
                        localHost = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        workID = new Random().nextInt(~(-1 << WORKER_ID_BITS));
                        dataCenterID = 0L;
                    }
                    if (null != localHost) {
                        String hostAddress = localHost.getHostAddress();
                        log.trace("Service IP: {}", hostAddress);
                        String[] split = hostAddress.split("[.]");
                        dataCenterID = Long.parseLong(split[2]);
                        workID = Long.parseLong(split[3]);
                    }
                    String workIDEnv = System.getenv(WORKER_ID_ENV);
                    if (null != workIDEnv) {
                        log.trace("WORKER_ID_ENV: {}", workIDEnv);
                        workID = Long.parseLong(workIDEnv);
                        dataCenterID = 0L;
                    }
                    log.trace("WORKER_ID: {}", workID);
                    log.trace("DATA_CENTER: {}", dataCenterID);
                    instance = new SnowflakeIDWorker(workID, dataCenterID);
                }
            }
        }
        return instance;
    }
}
