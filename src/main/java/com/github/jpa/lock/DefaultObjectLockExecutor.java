package com.github.jpa.lock;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

@Component
public class DefaultObjectLockExecutor implements ObjectLockExecutor {

    private final LogicLockExecutor logicLockExecutor;
    private final Properties properties;


    @Autowired
    public DefaultObjectLockExecutor(LogicLockExecutor logicLockExecutor, Properties properties) {
        this.logicLockExecutor = logicLockExecutor;
        this.properties = properties;
    }

    private static final ThreadLocal<Map<String, LockNode>> LOCAL_NODES = new ThreadLocal();
    private static final ThreadLocal<LinkedList<String>> LOCAL_KEYS = new ThreadLocal();


    private abstract static class Action {
        protected final LockNode node;
        protected final String attKey;

        private Action(LockNode node, String attKey) {
            this.node = node;
            this.attKey = attKey;
        }

        public abstract void proceed(BiConsumer<String, String> consumer);
    }

    private static class LockAction extends Action {
        private BiPredicate<String, String> condition;
        private final int retry;

        private LockAction(LockNode node, String attKey, int retry) {
            super(node, attKey);
            this.retry = retry;
        }

        public LockAction onCondition(BiPredicate<String, String> condition) {
            this.condition = condition;
            return this;
        }

        @Override
        public void proceed(BiConsumer<String, String> consumer) {
            if (condition != null && condition.test(node.objKey, attKey)) {
                try {
                    consumer.accept(node.objKey, attKey);
                } catch (Exception exception) {
                    for (int i = 0; i < retry; ++i) {
                        try {
                            consumer.accept(node.objKey, attKey);
                            i = retry;
                        } catch (Exception exc) {
                            if (this.retry - i == 1) {
                                throw new RuntimeException(exc);
                            }
                        }
                    }
                }
            }
        }
    }

    private static class LockNode {
        private final String objKey;
        private final Set<String> attKeys;

        private LockNode(String objKey) {
            this.objKey = objKey;
            this.attKeys = new HashSet();
        }

        /**
         * ThreadLocal<Map<String, LockNode>> get put
         *
         * @param objKey
         * @return
         */
        public static LockNode of(String objKey) {
            Map<String, LockNode> nodes =
                    Optional.ofNullable(LOCAL_NODES.get())
                            .orElseGet(() -> {
                                LOCAL_NODES.set(new HashMap());
                                LOCAL_KEYS.set(new LinkedList());
                                return new HashMap();
                            });

            return Optional.ofNullable(nodes.get(objKey)).orElseGet(() -> {
                LockNode tmp = new LockNode(objKey);
                LOCAL_NODES.get().put(objKey, tmp);
                return tmp;
            });
        }

        public LockAction lock(String attKey, int retry) {
            return new LockAction(this, attKey, retry);
        }
    }


    @Override
    public void executeLock(String objKey) {
        LockNode.of(objKey)
                .lock("ALL", properties.getRetry())
                .onCondition((obj, att) -> LOCAL_NODES.get().get(obj) != null)
                .proceed((obj, att) -> logicLockExecutor.obtainLockForEntity(obj));
    }

    @Override
    public void executeUnlock(String objKey) {

    }

    @Override
    public void executeLock(String objKey, String attKey) {

    }

    @Override
    public void executeUnlock(String objKey, String attKey) {

    }

    @Override
    public void forceUnlockAll() {

    }
}
