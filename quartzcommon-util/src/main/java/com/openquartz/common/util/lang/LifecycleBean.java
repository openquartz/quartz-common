package com.openquartz.common.util.lang;

/**
 * LifecycleBean
 *
 * @author svnee
 */
public interface LifecycleBean {

    /**
     * init
     */
    default void init(){
    }

    /**
     * destroy method
     */
    default void destroy(){

    }
}
