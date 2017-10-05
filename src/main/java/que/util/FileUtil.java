package que.util;

import java.io.File;
import java.io.IOException;

/**
 * File utilities.
 *
 * @author que
 */
public final class FileUtil {
    /**
     * constructor.
     */
    private FileUtil() {
    }

    /**
     * Delete directory and/or file.
     *
     * @param dirFilePath directory and/or file path.
     */
    public static void delete(final String dirFilePath) throws IOException {
        delete(new File(dirFilePath));
    }

    /**
     * Delete directory and/or file.
     *
     * @param dirFile directory and/or file.
     * @throws IOException If I/O error occur,
     */
    public static void delete(final File dirFile) throws IOException {
        if (dirFile == null || !dirFile.exists()) {
            throw new IOException("file " + dirFile + " not exist.");
        }

        deleteImpl(dirFile);
    }

    /**
     * Delete directory and/or file.
     *
     * @param dirFile directory and/or file.
     * @throws IOException If I/O error occur,
     */
    private static void deleteImpl(final File dirFile) throws IOException {
        if (dirFile.isDirectory()) {
            final File[] children = dirFile.listFiles();
            if (children != null) {
                for (final File child : children) {
                    if (!child.exists()) {
                        continue;
                    }

                    deleteImpl(child);
                }
            }
        }

        if (!dirFile.delete()) {
            throw new IOException("file " + dirFile.getCanonicalPath() + " delete fail.");
        }
    }
}
