/*
 * Copyright 2015 - 2018 Michael Rapp
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package de.mrapp.android.util;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.IOException;

import static de.mrapp.android.util.Condition.ensureNotNull;

/**
 * An utility class, which provides static methods, which allow to handle files.
 *
 * @author Michael Rapp
 * @since 1.9.0
 */
public final class FileUtil {

    /**
     * Creates a specific directory, if it does not already exist.
     *
     * @param directory
     *         The directory, which should be created, as an instance of the class {@link File}. The
     *         directory may not be null
     * @param createParents
     *         True, if the parent directories should also be created, if necessary, false
     *         otherwise
     * @throws IOException
     *         The exception, which is thrown, if an error occurred while creating the directory
     */
    private static void mkdir(@NonNull final File directory, final boolean createParents)
            throws IOException {
        ensureNotNull(directory, "The directory may not be null");
        boolean result = createParents ? directory.mkdirs() : directory.mkdir();

        if (!result && !directory.exists()) {
            throw new IOException("Failed to create directory \"" + directory + "\"");
        }
    }

    /**
     * Creates a new utility class, which provides static methods, which allow to handle files.
     */
    private FileUtil() {

    }

    /**
     * Creates a specific directory, if it does not already exist. It is assumed, that the parent
     * directories already exist. If the parent directories should be created as well, use the
     * <code>mkdirs</code>-method instead.
     *
     * @param directory
     *         The directory, which should be created, as an instance of the class {@link File}. The
     *         directory may not be null
     * @throws IOException
     *         The exception, which is thrown, if an error occurred while creating the directory
     */
    public static void mkdir(@NonNull final File directory) throws IOException {
        mkdir(directory, false);
    }

    /**
     * Creates a specific directory, if it does not already exist. If necessary the parent
     * directories are created as well. If no parent directories should be created, use the
     * <code>mkdir</code>-method instead.
     *
     * @param directory
     *         The directory, which should be created, as an instance of the class {@link File}. The
     *         directory may not be null
     * @throws IOException
     *         The exception, which is thrown, if an error occurred while creating the directory
     */
    public static void mkdirs(@NonNull final File directory) throws IOException {
        mkdir(directory, true);
    }

    /**
     * Deletes a specific file. If a directory and its contents should be deleted, use the
     * <code>deleteRecursively</code>-method instead.
     *
     * @param file
     *         The file, which should be deleted, as an instance of the class {@link File}. The file
     *         may not be null
     * @throws IOException
     *         The exception, which is thrown, if an error occurred while deleting the file
     */
    public static void delete(@NonNull final File file) throws IOException {
        ensureNotNull(file, "The file may not be null");
        boolean result = file.delete();

        if (!result && file.exists()) {
            if (file.isDirectory()) {
                throw new IOException(
                        "Directory \"" + file + "\" must be empty before being deleted");
            }

            throw new IOException("Failed to deleted file \"" + file + "\"");
        }
    }

    /**
     * Deletes a specific file or directory. If the file is a directory, all contained files and
     * subdirectories are deleted recursively.
     *
     * @param file
     *         The file or directory, which should be deleted, as an instance of the class {@link
     *         File}. The file or directory may not be null
     * @throws IOException
     *         The exception, which is thrown, if an error occurs while deleting the file or
     *         directory
     */
    public static void deleteRecursively(@NonNull final File file) throws IOException {
        ensureNotNull(file, "The file or directory may not be null");

        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                deleteRecursively(child);
            }
        }

        delete(file);
    }

    /**
     * Creates a new, empty file.
     *
     * @param file
     *         The file, which should be created, as an instance of the class {@link File}. The file
     *         may not be null. If the file is a directory, an {@link IllegalArgumentException} will
     *         be thrown
     * @throws IOException
     *         The exception, which is thrown, if an error occurs while creating the file
     */
    public static void createNewFile(@NonNull final File file) throws IOException {
        createNewFile(file, false);
    }

    /**
     * Creates a new, empty file.
     *
     * @param file
     *         The file, which should be created, as an instance of the class {@link File}. The file
     *         may not be null. If the file is a directory, an {@link IllegalArgumentException} will
     *         be thrown
     * @param overwrite
     *         True, if the file should be overwritten, if it does already exist, false otherwise
     * @throws IOException
     *         The exception, which is thrown, if an error occurs while creating the file
     */
    public static void createNewFile(@NonNull final File file, final boolean overwrite)
            throws IOException {
        ensureNotNull(file, "The file may not be null");
        boolean result = file.createNewFile();

        if (!result) {
            if (overwrite) {
                try {
                    delete(file);
                    createNewFile(file, false);
                } catch (IOException e) {
                    throw new IOException("Failed to overwrite file \"" + file + "\"");
                }
            } else if (file.exists()) {
                throw new IOException("File \"" + file + "\" does already exist");
            } else {
                throw new IllegalArgumentException("The file must not be a directory");
            }
        }
    }

}