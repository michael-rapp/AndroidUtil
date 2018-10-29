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
package de.mrapp.android.util.logging;

import androidx.annotation.NonNull;
import android.util.Log;

import static de.mrapp.android.util.ClassUtil.getTruncatedName;
import static de.mrapp.android.util.Condition.ensureNotEmpty;
import static de.mrapp.android.util.Condition.ensureNotNull;

/**
 * A logger, which allows to write log messages to Logcat. It allows to specify a log level in order
 * to filter log messages depending on their priority.
 *
 * @author Michael Rapp
 * @since 1.13.0
 */
public class Logger {

    /**
     * The current log level.
     */
    private LogLevel logLevel;

    /**
     * Creates a new logger, which uses a specific log level.
     *
     * @param logLevel
     *         The log level, which should be used by the logger, as a value of the enum {@link
     *         LogLevel}. The log level may not be null
     */
    public Logger(@NonNull final LogLevel logLevel) {
        setLogLevel(logLevel);
    }

    /**
     * Returns the current log level. Only log messages with a rank greater or equal than the rank
     * of the currently applied log level, are written to the output.
     *
     * @return The current log level as a value of the enum {@link LogLevel}. The log level may
     * either be <code>ALL</code>, <code>DEBUG</code>, <code>INFO</code>, <code>WARN</code>,
     * <code>ERROR</code> or <code>OFF</code>
     */
    public final LogLevel getLogLevel() {
        return logLevel;
    }

    /**
     * Sets the log level. Only log messages with a rank greater or equal than the rank of the
     * currently applied log level, are written to the output.
     *
     * @param logLevel
     *         The log level, which should be set, as a value of the enum {@link LogLevel}. The log
     *         level may not be null
     */
    public final void setLogLevel(@NonNull final LogLevel logLevel) {
        ensureNotNull(logLevel, "The log level may not be null");
        this.logLevel = logLevel;
    }

    /**
     * Logs a specific message on the log level VERBOSE.
     *
     * @param tag
     *         The tag, which identifies the source of the log message, as an instance of the class
     *         {@link Class}. The tag may not be null
     * @param message
     *         The message, which should be logged, as a {@link String}. The message may neither be
     *         null, nor empty
     */
    public final void logVerbose(@NonNull final Class<?> tag, @NonNull final String message) {
        ensureNotNull(tag, "The tag may not be null");
        ensureNotNull(message, "The message may not be null");
        ensureNotEmpty(message, "The message may not be empty");

        if (LogLevel.VERBOSE.getRank() >= getLogLevel().getRank()) {
            Log.v(getTruncatedName(tag), message);
        }
    }

    /**
     * Logs a specific message and exception on the log level VERBOSE.
     *
     * @param tag
     *         The tag, which identifies the source of the log message, as an instance of the class
     *         {@link Class}. The tag may not be null
     * @param message
     *         The message, which should be logged, as a {@link String}. The message may neither be
     *         null, nor empty
     * @param cause
     *         The exception, which caused the log message, as an instance of the class {@link
     *         Throwable}. The cause may not be null
     */
    public final void logVerbose(@NonNull final Class<?> tag, @NonNull final String message,
                                 @NonNull final Throwable cause) {
        ensureNotNull(tag, "The tag may not be null");
        ensureNotNull(message, "The message may not be null");
        ensureNotEmpty(message, "The message may not be empty");
        ensureNotNull(cause, "The cause may not be null");

        if (LogLevel.VERBOSE.getRank() >= getLogLevel().getRank()) {
            Log.v(getTruncatedName(tag), message, cause);
        }
    }

    /**
     * Logs a specific message on the log level DEBUG.
     *
     * @param tag
     *         The tag, which identifies the source of the log message, as an instance of the class
     *         {@link Class}. The tag may not be null
     * @param message
     *         The message, which should be logged, as a {@link String}. The message may neither be
     *         null, nor empty
     */
    public final void logDebug(@NonNull final Class<?> tag, @NonNull final String message) {
        ensureNotNull(tag, "The tag may not be null");
        ensureNotNull(message, "The message may not be null");
        ensureNotEmpty(message, "The message may not be empty");

        if (LogLevel.DEBUG.getRank() >= getLogLevel().getRank()) {
            Log.d(getTruncatedName(tag), message);
        }
    }

    /**
     * Logs a specific message and exception on the log level DEBUG.
     *
     * @param tag
     *         The tag, which identifies the source of the log message, as an instance of the class
     *         {@link Class}. The tag may not be null
     * @param message
     *         The message, which should be logged, as a {@link String}. The message may neither be
     *         null, nor empty
     * @param cause
     *         The exception, which caused the log message, as an instance of the class {@link
     *         Throwable}. The cause may not be null
     */
    public final void logDebug(@NonNull final Class<?> tag, @NonNull final String message,
                               @NonNull final Throwable cause) {
        ensureNotNull(tag, "The tag may not be null");
        ensureNotNull(message, "The message may not be null");
        ensureNotEmpty(message, "The message may not be empty");
        ensureNotNull(cause, "The cause may not be null");

        if (LogLevel.DEBUG.getRank() >= getLogLevel().getRank()) {
            Log.d(getTruncatedName(tag), message, cause);
        }
    }

    /**
     * Logs a specific message on the log level INFO.
     *
     * @param tag
     *         The tag, which identifies the source of the log message, as an instance of the class
     *         {@link Class}. The tag may not be null
     * @param message
     *         The message, which should be logged, as a {@link String}. The message may neither be
     *         null, nor empty
     */
    public final void logInfo(@NonNull final Class<?> tag, @NonNull final String message) {
        ensureNotNull(tag, "The tag may not be null");
        ensureNotNull(message, "The message may not be null");
        ensureNotEmpty(message, "The message may not be empty");

        if (LogLevel.INFO.getRank() >= getLogLevel().getRank()) {
            Log.i(getTruncatedName(tag), message);
        }
    }

    /**
     * Logs a specific message and exception on the log level INFO.
     *
     * @param tag
     *         The tag, which identifies the source of the log message, as an instance of the class
     *         {@link Class}. The tag may not be null
     * @param message
     *         The message, which should be logged, as a {@link String}. The message may neither be
     *         null, nor empty
     * @param cause
     *         The exception, which caused the log message, as an instance of the class {@link
     *         Throwable}. The cause may not be null
     */
    public final void logInfo(@NonNull final Class<?> tag, @NonNull final String message,
                              @NonNull final Throwable cause) {
        ensureNotNull(tag, "The tag may not be null");
        ensureNotNull(message, "The message may not be null");
        ensureNotEmpty(message, "The message may not be empty");
        ensureNotNull(cause, "The cause may not be null");

        if (LogLevel.INFO.getRank() >= getLogLevel().getRank()) {
            Log.i(getTruncatedName(tag), message, cause);
        }
    }

    /**
     * Logs a specific message on the log level WARN.
     *
     * @param tag
     *         The tag, which identifies the source of the log message, as an instance of the class
     *         {@link Class}. The tag may not be null
     * @param message
     *         The message, which should be logged, as a {@link String}. The message may neither be
     *         null, nor empty
     */
    public final void logWarn(@NonNull final Class<?> tag, @NonNull final String message) {
        ensureNotNull(tag, "The tag may not be null");
        ensureNotNull(message, "The message may not be null");
        ensureNotEmpty(message, "The message may not be empty");

        if (LogLevel.WARN.getRank() >= getLogLevel().getRank()) {
            Log.w(getTruncatedName(tag), message);
        }
    }

    /**
     * Logs a specific message and exception on the log level WARN.
     *
     * @param tag
     *         The tag, which identifies the source of the log message, as an instance of the class
     *         {@link Class}. The tag may not be null
     * @param message
     *         The message, which should be logged, as a {@link String}. The message may neither be
     *         null, nor empty
     * @param cause
     *         The exception, which caused the log message, as an instance of the class {@link
     *         Throwable}. The cause may not be null
     */
    public final void logWarn(@NonNull final Class<?> tag, @NonNull final String message,
                              @NonNull final Throwable cause) {
        ensureNotNull(tag, "The tag may not be null");
        ensureNotNull(message, "The message may not be null");
        ensureNotEmpty(message, "The message may not be empty");
        ensureNotNull(cause, "The cause may not be null");

        if (LogLevel.WARN.getRank() >= getLogLevel().getRank()) {
            Log.w(getTruncatedName(tag), message, cause);
        }
    }

    /**
     * Logs a specific message on the log level ERROR.
     *
     * @param tag
     *         The tag, which identifies the source of the log message, as an instance of the class
     *         {@link Class}. The tag may not be null
     * @param message
     *         The message, which should be logged, as a {@link String}. The message may neither be
     *         null, nor empty
     */
    public final void logError(@NonNull final Class<?> tag, @NonNull final String message) {
        ensureNotNull(tag, "The tag may not be null");
        ensureNotNull(message, "The message may not be null");
        ensureNotEmpty(message, "The message may not be empty");

        if (LogLevel.ERROR.getRank() >= getLogLevel().getRank()) {
            Log.e(getTruncatedName(tag), message);
        }
    }

    /**
     * Logs a specific message and exception on the log level ERROR.
     *
     * @param tag
     *         The tag, which identifies the source of the log message, as an instance of the class
     *         {@link Class}. The tag may not be null
     * @param message
     *         The message, which should be logged, as a {@link String}. The message may neither be
     *         null, nor empty
     * @param cause
     *         The exception, which caused the log message, as an instance of the class {@link
     *         Throwable}. The cause may not be null
     */
    public final void logError(@NonNull final Class<?> tag, @NonNull final String message,
                               @NonNull final Throwable cause) {
        ensureNotNull(tag, "The tag may not be null");
        ensureNotNull(message, "The message may not be null");
        ensureNotEmpty(message, "The message may not be empty");
        ensureNotNull(cause, "The cause may not be null");

        if (LogLevel.ERROR.getRank() >= getLogLevel().getRank()) {
            Log.e(getTruncatedName(tag), message, cause);
        }
    }

}