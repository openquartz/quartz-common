package com.openquartz.common.util.concurrent.lock;

/**
 * consumer function
 *
 * @author svnee
 */
@FunctionalInterface
public interface Consumer {

    /**
     * consume
     */
    void consume();
}
