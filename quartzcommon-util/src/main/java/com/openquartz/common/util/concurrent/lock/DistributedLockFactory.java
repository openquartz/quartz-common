package com.openquartz.common.util.concurrent.lock;

import com.openquartz.common.util.lang.Pair;
import java.util.concurrent.locks.Lock;

/**
 * Distributed EventLock
 *
 * @author svnee
 */
public interface DistributedLockFactory {

    /**
     * Get Lock
     *
     * @param lockKey lockKey
     * @return lock must not be null
     */
    Lock getLock(Pair<String, LockBizType> lockKey);

}
