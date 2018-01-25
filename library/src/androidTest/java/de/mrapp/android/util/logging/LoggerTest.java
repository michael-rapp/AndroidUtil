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

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Tests the functionality of the class {@link Logger}.
 *
 * @author Michael Rapp
 */
public class LoggerTest extends TestCase {

    /**
     * Tests, if all properties are set correctly by the constructor.
     */
    public final void testConstructor() {
        LogLevel logLevel = LogLevel.ERROR;
        Logger logger = new Logger(logLevel);
        assertEquals(logLevel, logger.getLogLevel());
    }

    /**
     * Ensures, that a {@link NullPointerException} is thrown, if the log level, which passed to the
     * constructor, is null.
     */
    public final void testConstructorThrowsException() {
        try {
            new Logger(null);
            Assert.fail();
        } catch (NullPointerException e) {

        }
    }

    /**
     * Tests the functionality of the method, which allows to log a message on the log level
     * VERBOSE.
     */
    public final void testLogMessageOnLevelVerbose() {
        new Logger(LogLevel.VERBOSE).logVerbose(getClass(), "message");
    }

    /**
     * Tests the functionality of the method, which allows to log a message and an exception on the
     * log level VERBOSE.
     */
    public final void testLogMessageAndExceptionOnLevelVerbose() {
        new Logger(LogLevel.VERBOSE).logVerbose(getClass(), "message", new Throwable());
    }

    /**
     * Ensures, that a {@link NullPointerException} is thrown by the method, which allows to log a
     * message on the log level VERBOSE, if the tag is null.
     */
    public final void testLogMessageOnLevelVerboseThrowsExceptionIfTagIsNull() {
        try {
            new Logger(LogLevel.VERBOSE).logVerbose(null, "message");
            Assert.fail();
        } catch (NullPointerException e) {

        }
    }

    /**
     * Ensures, that a {@link NullPointerException} is thrown by the method, which allows to log a
     * message on the log level VERBOSE, if the message is null.
     */
    public final void testLogMessageOnLevelVerboseThrowsExceptionIfMessageIsNull() {
        try {
            new Logger(LogLevel.VERBOSE).logVerbose(getClass(), null);
            Assert.fail();
        } catch (NullPointerException e) {

        }
    }

    /**
     * Ensures, that an {@link IllegalArgumentException} is thrown by the method, which allows to
     * log a message on the log level VERBOSE, if the message is empty.
     */
    public final void testLogMessageOnLevelVerboseThrowsExceptionIfMessageIsEmpty() {
        try {
            new Logger(LogLevel.VERBOSE).logVerbose(getClass(), "");
            Assert.fail();
        } catch (IllegalArgumentException e) {

        }
    }

    /**
     * Ensures, that a {@link NullPointerException} is thrown by the method, which allows to log a
     * message and an exception on the log level VERBOSE, if the tag is null.
     */
    public final void testLogMessageAndExceptionOnLevelVerboseThrowsExceptionIfTagIsNull() {
        try {
            new Logger(LogLevel.VERBOSE).logVerbose(null, "message", new Throwable());
            Assert.fail();
        } catch (NullPointerException e) {

        }
    }

    /**
     * Ensures, that a {@link NullPointerException} is thrown by the method, which allows to log a
     * message and an exception on the log level VERBOSE, if the message is null.
     */
    public final void testLogMessageAndExceptionOnLevelVerboseThrowsExceptionIfMessageIsNull() {
        try {
            new Logger(LogLevel.VERBOSE).logVerbose(getClass(), null, new Throwable());
            Assert.fail();
        } catch (NullPointerException e) {

        }
    }

    /**
     * Ensures, that an {@link IllegalArgumentException} is thrown by the method, which allows to
     * log a message and an exception on the log level VERBOSE, if the message is empty.
     */
    public final void testLogMessageAndExceptionOnLevelVerboseThrowsExceptionIfMessageIsEmpty() {
        try {
            new Logger(LogLevel.VERBOSE).logVerbose(getClass(), "", new Throwable());
            Assert.fail();
        } catch (IllegalArgumentException e) {

        }
    }

    /**
     * Ensures, that a {@link NullPointerException} is thrown by the method, which allows to log a
     * message and an exception on the log level VERBOSE, if the cause is null.
     */
    public final void testLogMessageAndExceptionOnLevelVerboseThrowsExceptionIfCaiseIsNull() {
        try {
            new Logger(LogLevel.VERBOSE).logVerbose(getClass(), "message", null);
            Assert.fail();
        } catch (NullPointerException e) {

        }
    }

    /**
     * Tests the functionality of the method, which allows to log a message on the log level DEBUG.
     */
    public final void testLogMessageOnLevelDebug() {
        new Logger(LogLevel.DEBUG).logDebug(getClass(), "message");
    }

    /**
     * Tests the functionality of the method, which allows to log a message and an exception on the
     * log level DEBUG.
     */
    public final void testLogMessageAndExceptionOnLevelDebug() {
        new Logger(LogLevel.DEBUG).logDebug(getClass(), "message", new Throwable());
    }

    /**
     * Ensures, that a {@link NullPointerException} is thrown by the method, which allows to log a
     * message on the log level DEBUG, if the tag is null.
     */
    public final void testLogMessageOnLevelDebugThrowsExceptionIfTagIsNull() {
        try {
            new Logger(LogLevel.DEBUG).logDebug(null, "message");
            Assert.fail();
        } catch (NullPointerException e) {

        }
    }

    /**
     * Ensures, that a {@link NullPointerException} is thrown by the method, which allows to log a
     * message on the log level DEBUG, if the message is null.
     */
    public final void testLogMessageOnLevelDebugThrowsExceptionIfMessageIsNull() {
        try {
            new Logger(LogLevel.DEBUG).logDebug(getClass(), null);
            Assert.fail();
        } catch (NullPointerException e) {

        }
    }

    /**
     * Ensures, that an {@link IllegalArgumentException} is thrown by the method, which allows to
     * log a message on the log level DEBUG, if the message is empty.
     */
    public final void testLogMessageOnLevelDebugThrowsExceptionIfMessageIsEmpty() {
        try {
            new Logger(LogLevel.DEBUG).logDebug(getClass(), "");
            Assert.fail();
        } catch (IllegalArgumentException e) {

        }
    }

    /**
     * Ensures, that a {@link NullPointerException} is thrown by the method, which allows to log a
     * message and an exception on the log level DEBUG, if the tag is null.
     */
    public final void testLogMessageAndExceptionOnLevelDebugThrowsExceptionIfTagIsNull() {
        try {
            new Logger(LogLevel.DEBUG).logDebug(null, "message", new Throwable());
            Assert.fail();
        } catch (NullPointerException e) {

        }
    }

    /**
     * Ensures, that a {@link NullPointerException} is thrown by the method, which allows to log a
     * message and an exception on the log level DEBUG, if the message is null.
     */
    public final void testLogMessageAndExceptionOnLevelDebugThrowsExceptionIfMessageIsNull() {
        try {
            new Logger(LogLevel.DEBUG).logDebug(getClass(), null, new Throwable());
            Assert.fail();
        } catch (NullPointerException e) {

        }
    }

    /**
     * Ensures, that an {@link IllegalArgumentException} is thrown by the method, which allows to
     * log a message and an exception on the log level DEBUG, if the message is empty.
     */
    public final void testLogMessageAndExceptionOnLevelDebugThrowsExceptionIfMessageIsEmpty() {
        try {
            new Logger(LogLevel.DEBUG).logDebug(getClass(), "", new Throwable());
            Assert.fail();
        } catch (IllegalArgumentException e) {

        }
    }

    /**
     * Ensures, that a {@link NullPointerException} is thrown by the method, which allows to log a
     * message and an exception on the log level DEBUG, if the cause is null.
     */
    public final void testLogMessageAndExceptionOnLevelDebugThrowsExceptionIfCaiseIsNull() {
        try {
            new Logger(LogLevel.DEBUG).logDebug(getClass(), "message", null);
            Assert.fail();
        } catch (NullPointerException e) {

        }
    }

    /**
     * Tests the functionality of the method, which allows to log a message on the log level INFO.
     */
    public final void testLogMessageOnLevelInfo() {
        new Logger(LogLevel.INFO).logInfo(getClass(), "message");
    }

    /**
     * Tests the functionality of the method, which allows to log a message and an exception on the
     * log level INFO.
     */
    public final void testLogMessageAndExceptionOnLevelInfo() {
        new Logger(LogLevel.INFO).logInfo(getClass(), "message", new Throwable());
    }

    /**
     * Ensures, that a {@link NullPointerException} is thrown by the method, which allows to log a
     * message on the log level INFO, if the tag is null.
     */
    public final void testLogMessageOnLevelInfoThrowsExceptionIfTagIsNull() {
        try {
            new Logger(LogLevel.INFO).logInfo(null, "message");
            Assert.fail();
        } catch (NullPointerException e) {

        }
    }

    /**
     * Ensures, that a {@link NullPointerException} is thrown by the method, which allows to log a
     * message on the log level INFO, if the message is null.
     */
    public final void testLogMessageOnLevelInfoThrowsExceptionIfMessageIsNull() {
        try {
            new Logger(LogLevel.INFO).logInfo(getClass(), null);
            Assert.fail();
        } catch (NullPointerException e) {

        }
    }

    /**
     * Ensures, that an {@link IllegalArgumentException} is thrown by the method, which allows to
     * log a message on the log level INFO, if the message is empty.
     */
    public final void testLogMessageOnLevelInfoThrowsExceptionIfMessageIsEmpty() {
        try {
            new Logger(LogLevel.INFO).logInfo(getClass(), "");
            Assert.fail();
        } catch (IllegalArgumentException e) {

        }
    }

    /**
     * Ensures, that a {@link NullPointerException} is thrown by the method, which allows to log a
     * message and an exception on the log level INFO, if the tag is null.
     */
    public final void testLogMessageAndExceptionOnLevelInfoThrowsExceptionIfTagIsNull() {
        try {
            new Logger(LogLevel.INFO).logInfo(null, "message", new Throwable());
            Assert.fail();
        } catch (NullPointerException e) {

        }
    }

    /**
     * Ensures, that a {@link NullPointerException} is thrown by the method, which allows to log a
     * message and an exception on the log level INFO, if the message is null.
     */
    public final void testLogMessageAndExceptionOnLevelInfoThrowsExceptionIfMessageIsNull() {
        try {
            new Logger(LogLevel.INFO).logInfo(getClass(), null, new Throwable());
            Assert.fail();
        } catch (NullPointerException e) {

        }
    }

    /**
     * Ensures, that an {@link IllegalArgumentException} is thrown by the method, which allows to
     * log a message and an exception on the log level INFO, if the message is empty.
     */
    public final void testLogMessageAndExceptionOnLevelInfoThrowsExceptionIfMessageIsEmpty() {
        try {
            new Logger(LogLevel.INFO).logInfo(getClass(), "", new Throwable());
            Assert.fail();
        } catch (IllegalArgumentException e) {

        }
    }

    /**
     * Ensures, that a {@link NullPointerException} is thrown by the method, which allows to log a
     * message and an exception on the log level INFO, if the cause is null.
     */
    public final void testLogMessageAndExceptionOnLevelInfoThrowsExceptionIfCaiseIsNull() {
        try {
            new Logger(LogLevel.INFO).logInfo(getClass(), "message", null);
            Assert.fail();
        } catch (NullPointerException e) {

        }
    }

    /**
     * Tests the functionality of the method, which allows to log a message on the log level WARN.
     */
    public final void testLogMessageOnLevelWarn() {
        new Logger(LogLevel.WARN).logWarn(getClass(), "message");
    }

    /**
     * Tests the functionality of the method, which allows to log a message and an exception on the
     * log level WARN.
     */
    public final void testLogMessageAndExceptionOnLevelWarn() {
        new Logger(LogLevel.WARN).logWarn(getClass(), "message", new Throwable());
    }

    /**
     * Ensures, that a {@link NullPointerException} is thrown by the method, which allows to log a
     * message on the log level WARN, if the tag is null.
     */
    public final void testLogMessageOnLevelWarnThrowsExceptionIfTagIsNull() {
        try {
            new Logger(LogLevel.WARN).logWarn(null, "message");
            Assert.fail();
        } catch (NullPointerException e) {

        }
    }

    /**
     * Ensures, that a {@link NullPointerException} is thrown by the method, which allows to log a
     * message on the log level WARN, if the message is null.
     */
    public final void testLogMessageOnLevelWarnThrowsExceptionIfMessageIsNull() {
        try {
            new Logger(LogLevel.WARN).logWarn(getClass(), null);
            Assert.fail();
        } catch (NullPointerException e) {

        }
    }

    /**
     * Ensures, that an {@link IllegalArgumentException} is thrown by the method, which allows to
     * log a message on the log level WARN, if the message is empty.
     */
    public final void testLogMessageOnLevelWarnThrowsExceptionIfMessageIsEmpty() {
        try {
            new Logger(LogLevel.WARN).logWarn(getClass(), "");
            Assert.fail();
        } catch (IllegalArgumentException e) {

        }
    }

    /**
     * Ensures, that a {@link NullPointerException} is thrown by the method, which allows to log a
     * message and an exception on the log level WARN, if the tag is null.
     */
    public final void testLogMessageAndExceptionOnLevelWarnThrowsExceptionIfTagIsNull() {
        try {
            new Logger(LogLevel.WARN).logWarn(null, "message", new Throwable());
            Assert.fail();
        } catch (NullPointerException e) {

        }
    }

    /**
     * Ensures, that a {@link NullPointerException} is thrown by the method, which allows to log a
     * message and an exception on the log level WARN, if the message is null.
     */
    public final void testLogMessageAndExceptionOnLevelWarnThrowsExceptionIfMessageIsNull() {
        try {
            new Logger(LogLevel.WARN).logWarn(getClass(), null, new Throwable());
            Assert.fail();
        } catch (NullPointerException e) {

        }
    }

    /**
     * Ensures, that an {@link IllegalArgumentException} is thrown by the method, which allows to
     * log a message and an exception on the log level WARN, if the message is empty.
     */
    public final void testLogMessageAndExceptionOnLevelWarnThrowsExceptionIfMessageIsEmpty() {
        try {
            new Logger(LogLevel.WARN).logWarn(getClass(), "", new Throwable());
            Assert.fail();
        } catch (IllegalArgumentException e) {

        }
    }

    /**
     * Ensures, that a {@link NullPointerException} is thrown by the method, which allows to log a
     * message and an exception on the log level WARN, if the cause is null.
     */
    public final void testLogMessageAndExceptionOnLevelWarnThrowsExceptionIfCaiseIsNull() {
        try {
            new Logger(LogLevel.WARN).logWarn(getClass(), "message", null);
            Assert.fail();
        } catch (NullPointerException e) {

        }
    }

    /**
     * Tests the functionality of the method, which allows to log a message on the log level ERROR.
     */
    public final void testLogMessageOnLevelError() {
        new Logger(LogLevel.ERROR).logError(getClass(), "message");
    }

    /**
     * Tests the functionality of the method, which allows to log a message and an exception on the
     * log level ERROR.
     */
    public final void testLogMessageAndExceptionOnLevelError() {
        new Logger(LogLevel.ERROR).logError(getClass(), "message", new Throwable());
    }

    /**
     * Ensures, that a {@link NullPointerException} is thrown by the method, which allows to log a
     * message on the log level ERROR, if the tag is null.
     */
    public final void testLogMessageOnLevelErrorThrowsExceptionIfTagIsNull() {
        try {
            new Logger(LogLevel.ERROR).logError(null, "message");
            Assert.fail();
        } catch (NullPointerException e) {

        }
    }

    /**
     * Ensures, that a {@link NullPointerException} is thrown by the method, which allows to log a
     * message on the log level ERROR, if the message is null.
     */
    public final void testLogMessageOnLevelErrorThrowsExceptionIfMessageIsNull() {
        try {
            new Logger(LogLevel.ERROR).logError(getClass(), null);
            Assert.fail();
        } catch (NullPointerException e) {

        }
    }

    /**
     * Ensures, that an {@link IllegalArgumentException} is thrown by the method, which allows to
     * log a message on the log level ERROR, if the message is empty.
     */
    public final void testLogMessageOnLevelErrorThrowsExceptionIfMessageIsEmpty() {
        try {
            new Logger(LogLevel.ERROR).logError(getClass(), "");
            Assert.fail();
        } catch (IllegalArgumentException e) {

        }
    }

    /**
     * Ensures, that a {@link NullPointerException} is thrown by the method, which allows to log a
     * message and an exception on the log level ERROR, if the tag is null.
     */
    public final void testLogMessageAndExceptionOnLevelErrorThrowsExceptionIfTagIsNull() {
        try {
            new Logger(LogLevel.ERROR).logError(null, "message", new Throwable());
            Assert.fail();
        } catch (NullPointerException e) {

        }
    }

    /**
     * Ensures, that a {@link NullPointerException} is thrown by the method, which allows to log a
     * message and an exception on the log level ERROR, if the message is null.
     */
    public final void testLogMessageAndExceptionOnLevelErrorThrowsExceptionIfMessageIsNull() {
        try {
            new Logger(LogLevel.ERROR).logError(getClass(), null, new Throwable());
            Assert.fail();
        } catch (NullPointerException e) {

        }
    }

    /**
     * Ensures, that an {@link IllegalArgumentException} is thrown by the method, which allows to
     * log a message and an exception on the log level ERROR, if the message is empty.
     */
    public final void testLogMessageAndExceptionOnLevelErrorThrowsExceptionIfMessageIsEmpty() {
        try {
            new Logger(LogLevel.ERROR).logError(getClass(), "", new Throwable());
            Assert.fail();
        } catch (IllegalArgumentException e) {

        }
    }

    /**
     * Ensures, that a {@link NullPointerException} is thrown by the method, which allows to log a
     * message and an exception on the log level ERROR, if the cause is null.
     */
    public final void testLogMessageAndExceptionOnLevelErrorThrowsExceptionIfCaiseIsNull() {
        try {
            new Logger(LogLevel.ERROR).logError(getClass(), "message", null);
            Assert.fail();
        } catch (NullPointerException e) {

        }
    }

}