package com.openquartz.common.util.concurrent.lock.impl;

import com.openquartz.common.util.concurrent.lock.Consumer;
import com.openquartz.common.util.concurrent.lock.DistributedLockFactory;
import com.openquartz.common.util.concurrent.lock.LockBizType;
import com.openquartz.common.util.concurrent.lock.LockSupport;
import com.openquartz.common.util.lang.Pair;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LockSupportImpl
 *
 * @author svnee
 **/
public class LockSupportImpl implements LockSupport {

    private final DistributedLockFactory distributedLockFactory;
    private final Map<String, Lock> localLockMap = new ConcurrentHashMap<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(LockSupport.class);

    public LockSupportImpl(DistributedLockFactory distributedLockFactory) {
        this.distributedLockFactory = distributedLockFactory;
    }

    public static final String LOCK_KEY_FORMATTER = "%s:%s";

    /**
     * consume if tryLock
     * first try lock local lock,second try lock distributed lock
     *
     * @param lockKey lockKey
     * @param consumer consumer function
     * @return if consume function
     */
    @Override
    public boolean consumeIfTryLock(Pair<String, LockBizType> lockKey, Consumer consumer) {

        String identifyLockKey = String.format(LOCK_KEY_FORMATTER, lockKey.getValue().getCode(), lockKey.getKey());

        Lock lock = localLockMap.computeIfAbsent(identifyLockKey, k -> new ReentrantLock());
        if (lock.tryLock()) {
            try {
                if (Objects.isNull(distributedLockFactory)) {
                    consumer.consume();
                    return true;
                } else {
                    Lock dLock = distributedLockFactory.getLock(lockKey);
                    if (Objects.isNull(dLock)){
                        throw new IllegalArgumentException("distribute lock is null!lock-key:"+lockKey);
                    }
                    if (dLock.tryLock()) {
                        try {
                            consumer.consume();
                            return true;
                        } finally {
                            dLock.unlock();
                        }
                    }
                }
            } finally {
                try {
                    lock.unlock();
                } catch (Exception ex) {
                    LOGGER.error("[LockSupport#consumeIfTryLock] release lock error!lockKey:{}", lockKey, ex);
                }
                localLockMap.remove(identifyLockKey, lock);
            }
        }
        return false;
    }
}
