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
 * Tests the functionality of the class {@link LogLevel}.
 *
 * @author Michael Rapp
 */
public class LogLevelTest extends TestCase {

    /**
     * Tests the functionality of the method, which allows to retrieve the log level's rank, if the
     * log level is ALL.
     */
    public final void testGetRankWhenLogLevelIsAll() {
        assertEquals(0, LogLevel.ALL.getRank());
    }

    /**
     * Tests the functionality of the method, which allows to retrieve the log level's rank, if the
     * log level is VERBOSE.
     */
    public final void testGetRankWhenLogLevelIsVerbose() {
        assertEquals(1, LogLevel.VERBOSE.getRank());
    }

    /**
     * Tests the functionality of the method, which allows to retrieve the log level's rank, if the
     * log level is DEBUG.
     */
    public final void testGetRankWhenLogLevelIsDebug() {
        assertEquals(2, LogLevel.DEBUG.getRank());
    }

    /**
     * Tests the functionality of the method, which allows to retrieve the log level's rank, if the
     * log level is INFO.
     */
    public final void testGetRankWhenLogLevelIsInfo() {
        assertEquals(3, LogLevel.INFO.getRank());
    }

    /**
     * Tests the functionality of the method, which allows to retrieve the log level's rank, if the
     * log level is WARN.
     */
    public final void testGetRankWhenLogLevelIsWarn() {
        assertEquals(4, LogLevel.WARN.getRank());
    }

    /**
     * Tests the functionality of the method, which allows to retrieve the log level's rank, if the
     * log level is ERROR.
     */
    public final void testGetRankWhenLogLevelIsError() {
        assertEquals(5, LogLevel.ERROR.getRank());
    }

    /**
     * Tests the functionality of the method, which allows to retrieve the log level's rank, if the
     * log level is OFF.
     */
    public final void testGetRankWhenLogLevelIsOff() {
        assertEquals(6, LogLevel.OFF.getRank());
    }

    /**
     * Tests the functionality of the method, which allows to retrieve the log level, which belongs
     * to a specific rank, if the log level is ALL.
     */
    public final void testFromRankWhenLogLevelIsAll() {
        assertEquals(LogLevel.ALL, LogLevel.fromRank(0));
    }

    /**
     * Tests the functionality of the method, which allows to retrieve the log level, which belongs
     * to a specific rank, if the log level is VERBOSE.
     */
    public final void testFromRankWhenLogLevelIsVerbose() {
        assertEquals(LogLevel.VERBOSE, LogLevel.fromRank(1));
    }

    /**
     * Tests the functionality of the method, which allows to retrieve the log level, which belongs
     * to a specific rank, if the log level is DEBUG.
     */
    public final void testFromRankWhenLogLevelIsDebug() {
        assertEquals(LogLevel.DEBUG, LogLevel.fromRank(2));
    }

    /**
     * Tests the functionality of the method, which allows to retrieve the log level, which belongs
     * to a specific rank, if the log level is INFO.
     */
    public final void testFromRankWhenLogLevelIsInfo() {
        assertEquals(LogLevel.INFO, LogLevel.fromRank(3));
    }

    /**
     * Tests the functionality of the method, which allows to retrieve the log level, which belongs
     * to a specific rank, if the log level is WARN.
     */
    public final void testFromRankWhenLogLevelIsWarn() {
        assertEquals(LogLevel.WARN, LogLevel.fromRank(4));
    }

    /**
     * Tests the functionality of the method, which allows to retrieve the log level, which belongs
     * to a specific rank, if the log level is ERROR.
     */
    public final void testFromRankWhenLogLevelIsError() {
        assertEquals(LogLevel.ERROR, LogLevel.fromRank(5));
    }

    /**
     * Tests the functionality of the method, which allows to retrieve the log level, which belongs
     * to a specific rank, if the log level is OFF.
     */
    public final void testFromRankWhenLogLevelIsOff() {
        assertEquals(LogLevel.OFF, LogLevel.fromRank(6));
    }

    /**
     * Ensures, that an {@link IllegalArgumentException} is thrown by the method, which allows to
     * retrieve the log level, which belongs to a specific rank, if the rank is invalid.
     */
    public final void testFromRankThrowsException() {
        try {
            LogLevel.fromRank(-1);
            Assert.fail();
        } catch (IllegalArgumentException e) {

        }
    }

}