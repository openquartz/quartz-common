package com.openquartz.common.util.concurrent.lock;

import com.openquartz.common.util.lang.Pair;

/**
 * LockSupport
 *
 * @author svnee
 */
public interface LockSupport {

    /**
     * consume if try lock
     *
     * @param lockKey key: bizId, value: bizType
     * @param consumer consumer function
     * @return if consume function
     */
    boolean consumeIfTryLock(Pair<String, LockBizType> lockKey, Consumer consumer);
}
