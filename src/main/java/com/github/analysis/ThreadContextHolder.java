package com.github.analysis;

import com.github.common.cons.TransactionIDEnum;

import java.sql.Timestamp;

/**
 * @author 康盼Java开发工程师
 */
public class ThreadContextHolder {

    private static final ThreadLocal<String> TRANSACTION_ID = new ThreadLocal<>();

    private static final ThreadLocal<Timestamp> REQUEST_TIME = new ThreadLocal<>();

    public static String getTransactionId() {
        String transactionId = TRANSACTION_ID.get();
        if (transactionId == null || "".equals(transactionId)) {
            return "UnKnown";
        }
        return transactionId;
    }

    public static void setTransactionId(String tId) {
        TRANSACTION_ID.set(tId);
        REQUEST_TIME.set(new Timestamp(System.currentTimeMillis()));
    }

    public static Timestamp getRequestTime() {
        return REQUEST_TIME.get();
    }

    public static void setTransactionId(TransactionIDEnum transactionIDEnum) {
        setTransactionId(transactionIDEnum.getValue());
    }

    public static void clear() {
        TRANSACTION_ID.remove();
        REQUEST_TIME.remove();
    }
}