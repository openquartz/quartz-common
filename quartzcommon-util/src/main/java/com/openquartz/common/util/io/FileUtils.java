package com.openquartz.common.util.io;

import com.openquartz.common.util.constants.Constants;
import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;

/**
 * @author svnee
 **/
public final class FileUtils {

    private FileUtils() {
    }

    /**
     * sizeOfByte
     *
     * @param file file
     * @return byte
     */
    public static long sizeOf(File file) {
        return file.isDirectory() ? sizeOfDirectory0(file) : file.length();
    }

    /**
     * sizeOfMB
     *
     * @param file file
     * @return MB byte
     */
    public static int sizeOfMb(File file) {
        long byteSize = sizeOf(file);
        return BigInteger.valueOf(byteSize).divide(Constants.ONE_MB).intValue();
    }

    /**
     * sizeOfMB
     *
     * @param file file
     * @return MB byte
     */
    public static int sizeOfKb(File file) {
        long byteSize = sizeOf(file);
        return BigInteger.valueOf(byteSize).divide(Constants.ONE_KB).intValue();
    }

    /**
     * sizeOfDirectory0
     *
     * @param directory dictionary
     * @return size
     */
    private static long sizeOfDirectory0(final File directory) {

        final File[] files = directory.listFiles();
        // null if security restricted
        if (files == null) {
            return 0L;
        }
        long size = 0;

        for (final File file : files) {
            if (!isSymlink(file)) {
                size += sizeOf0(file);
                if (size < 0) {
                    break;
                }
            }
        }

        return size;
    }

    public static boolean isSymlink(final File file) {
        return file != null && Files.isSymbolicLink(file.toPath());
    }

    private static long sizeOf0(final File file) {
        if (file.isDirectory()) {
            return sizeOfDirectory0(file);
        }
        // will be 0 if file does not exist
        return file.length();
    }


}
