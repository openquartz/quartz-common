package com.openquartz.common.util.lang;

/**
 * EnumInterface
 *
 * @author svnee
 */
public interface EnumInterface<K> {

    /**
     * code
     *
     * @return code
     */
    K getCode();

    /**
     * desc
     *
     * @return desc
     */
    String getDesc();
}
