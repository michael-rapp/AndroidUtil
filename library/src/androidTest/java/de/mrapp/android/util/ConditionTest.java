/*
 * Copyright 2015 - 2016 Michael Rapp
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

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.io.File;
import java.io.IOException;

/**
 * Tests the functionality of the class {@link Condition}.
 *
 * @author Michael Rapp
 */
public class ConditionTest extends AndroidTestCase {

    /**
     * Tests the functionality of the method, which allows to ensure that an object is not null, if
     * the object is null.
     */
    public final void testEnsureNotNullThrowsException() {
        String message = "message";

        try {
            Condition.ensureNotNull(null, message);
            Assert.fail();
        } catch (NullPointerException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an object is not null, if
     * the object is not null.
     */
    public final void testEnsureNotNullThrowsNoException() {
        Condition.ensureNotNull(new Object(), "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an object is not null and
     * expects a class as a parameter, if the object is null.
     */
    public final void testEnsureNotNullWithClassParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureNotNull(null, message, IllegalArgumentException.class);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an object is not null and
     * expects a class as a parameter, if the object is not null.
     */
    public final void testEnsureNotNullWithClassParameterThrowsNoException() {
        Condition.ensureNotNull(new Object(), "message", IllegalArgumentException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a text is not empty, if
     * the text is empty.
     */
    public final void testEnsureNotEmptyThrowsException() {
        String message = "message";

        try {
            Condition.ensureNotEmpty("", message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a text is not empty, if
     * the text is not empty.
     */
    public final void testEnsureNotEmptyThrowsNoException() {
        Condition.ensureNotEmpty("text", "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a text is not empty and
     * expects a class as a parameter, if the text is empty.
     */
    public final void testEnsureNotEmptyWithClassParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureNotEmpty("", message, NullPointerException.class);
            Assert.fail();
        } catch (NullPointerException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a text is not empty and
     * expects a class as a parameter, if the text is not empty.
     */
    public final void testEnsureNotEmptyWithClassParameterThrowsNoException() {
        Condition.ensureNotEmpty("text", "message", NullPointerException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Short} value is
     * at least as great as a reference value, if the value is smaller as the reference value.
     */
    public final void testEnsureAtLeastWithShortParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtLeast((short) 0, (short) 1, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Short} value is
     * at least as great as a reference value, if the value is equal or greater as the reference
     * value.
     */
    public final void testEnsureAtLeastWithShortParameterThrowsNoException() {
        Condition.ensureAtLeast((short) 1, (short) 1, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Short} value is
     * at least as great as a reference value and expects a class as a parameter, if the value is
     * smaller as the reference value.
     */
    public final void testEnsureAtLeastWithShortAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtLeast((short) 0, (short) 1, message, IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Short} value is
     * at least as great as a reference value and expects a class as a parameter, if the value is
     * equal or greater as the reference value.
     */
    public final void testEnsureAtLeastWithShortAndClassParametersThrowsNoException() {
        Condition.ensureAtLeast((short) 1, (short) 1, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an {@link Integer} value
     * is at least as great as a reference value, if the value is smaller as the reference value.
     */
    public final void testEnsureAtLeastWithIntegerParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtLeast(0, 1, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an {@link Integer} value
     * is at least as great as a reference value, if the value is equal or greater as the reference
     * value.
     */
    public final void testEnsureAtLeastWithIntegerParameterThrowsNoException() {
        Condition.ensureAtLeast(1, 1, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an {@link Integer} value
     * is at least as great as a reference value and expects a class as a parameter, if the value is
     * smaller as the reference value.
     */
    public final void testEnsureAtLeastWithIntegerAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtLeast(0, 1, message, IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an {@link Integer} value
     * is at least as great as a reference value and expects a class as a parameter, if the value is
     * equal or greater as the reference value.
     */
    public final void testEnsureAtLeastWithIntegerAndClassParametersThrowsNoException() {
        Condition.ensureAtLeast(1, 1, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Long} value is as
     * least as great as a reference value, if the value is smaller as the reference value.
     */
    public final void testEnsureAtLeastWithLongParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtLeast(0L, 1L, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Long} value is as
     * least as great as a reference value, if the value is equal or greater as the reference
     * value.
     */
    public final void testEnsureAtLeastWithLongParameterThrowsNoException() {
        Condition.ensureAtLeast(1L, 1L, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Long} value is as
     * least as great as a reference value and expects a class as a parameter, if the value is
     * smaller as the reference value.
     */
    public final void testEnsureAtLeastWithLongAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtLeast(0L, 1L, message, IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Long} value is as
     * least as great as a reference value and expects a class as a parameter, if the value is equal
     * or greater as the reference value.
     */
    public final void testEnsureAtLeastWithLongAndClassParametersThrowsNoException() {
        Condition.ensureAtLeast(1L, 1L, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Float} value is
     * at least as great as a reference value, if the value is smaller as the reference value.
     */
    public final void testEnsureAtLeastWithFloatParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtLeast(0f, 1f, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Float} value is
     * at least as great as a reference value, if the value is equal or greater as the reference
     * value.
     */
    public final void testEnsureAtLeastWithFloatParameterThrowsNoException() {
        Condition.ensureAtLeast(1f, 1f, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Float} value is
     * at least as great as a reference value and expects a class as a parameter, if the value is
     * smaller as the reference value.
     */
    public final void testEnsureAtLeastWithFloatAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtLeast(0f, 1f, message, IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Float} value is
     * at least as great as a reference value and expects a class as a parameter, if the value is
     * equal or greater as the reference value.
     */
    public final void testEnsureAtLeastWithFloatAndClassParametersThrowsNoException() {
        Condition.ensureAtLeast(1f, 1f, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Double} value is
     * at least as great as a reference value, if the value is smaller as the reference value.
     */
    public final void testEnsureAtLeastWithDoubleParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtLeast(0d, 1d, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Double} value is
     * at least as great as a reference value, if the value is equal or greater as the reference
     * value.
     */
    public final void testEnsureAtLeastWithDoubleParameterThrowsNoException() {
        Condition.ensureAtLeast(1d, 1d, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Double} value is
     * at least as great as a reference value and expects a class as a parameter, if the value is
     * smaller as the reference value.
     */
    public final void testEnsureAtLeastWithDoubleAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtLeast(0d, 1d, message, IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Double} value is
     * at least as great as a reference value and expects a class as a parameter, if the value is
     * equal or greater as the reference value.
     */
    public final void testEnsureAtLeastWithDoubleAndClassParametersThrowsNoException() {
        Condition.ensureAtLeast(1d, 1d, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Short} value is
     * at maximum as great as a reference value, if the value is greater as the reference value.
     */
    public final void testEnsureAtMaximumWithShortParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtMaximum((short) 2, (short) 1, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Short} value is
     * at maximum as great as a reference value, if the value is equal or smaller as the reference
     * value.
     */
    public final void testEnsureAtMaximumWithShortParameterThrowsNoException() {
        Condition.ensureAtMaximum((short) 1, (short) 1, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Short} value is
     * at maximum as great as a reference value and expects a class as a parameter, if the value is
     * greater as the reference value.
     */
    public final void testEnsureAtMaximumWithShortAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtMaximum((short) 2, (short) 1, message,
                    IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Short} value is
     * at maximum as great as a reference value and expects a class as a parameter, if the value is
     * equal or smaller as the reference value.
     */
    public final void testEnsureAtMaximumWithShortAndClassParametersThrowsNoException() {
        Condition.ensureAtMaximum((short) 1, (short) 1, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an {@link Integer} value
     * is at maximum as great as a reference value, if the value is greater as the reference value.
     */
    public final void testEnsureAtMaximumWithIntegerParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtMaximum(2, 1, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an {@link Integer} value
     * is at maximum as great as a reference value, if the value is equal or smaller as the
     * reference value.
     */
    public final void testEnsureAtMaximumWithIntegerParameterThrowsNoException() {
        Condition.ensureAtMaximum(1, 1, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an {@link Integer} value
     * is at maximum as great as a reference value and expects a class as a parameter, if the value
     * is greater as the reference value.
     */
    public final void testEnsureAtMaximumWithIntegerAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtMaximum(2, 1, message, IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an {@link Integer} value
     * is at maximum as great as a reference value and expects a class as a parameter, if the value
     * is equal or smaller as the reference value.
     */
    public final void testEnsureAtMaximumWithIntegerAndClassParametersThrowsNoException() {
        Condition.ensureAtMaximum(1, 1, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Long} value is at
     * maximum as great as a reference value, if the value is greater as the reference value.
     */
    public final void testEnsureAtMaximumWithLongParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtMaximum(2L, 1L, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Long} value is at
     * maximum as great as a reference value, if the value is equal or smaller as the reference
     * value.
     */
    public final void testEnsureAtMaximumWithLongParameterThrowsNoException() {
        Condition.ensureAtMaximum(1L, 1L, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Long} value is at
     * maximum as great as a reference value and expects a class as a parameter, if the value is
     * greater as the reference value.
     */
    public final void testEnsureAtMaximumWithLongAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtMaximum(2L, 1L, message, IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Long} value is at
     * maximum as great as a reference value and expects a class as a parameter, if the value is
     * equal or smaller as the reference value.
     */
    public final void testEnsureAtMaximumWithLongAndClassParametersThrowsNoException() {
        Condition.ensureAtMaximum(1L, 1L, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Float} value is
     * at maximum as great as a reference value, if the value is greater as the reference value.
     */
    public final void testEnsureAtMaximumWithFloatParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtMaximum(2f, 1f, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Float} value is
     * at maximum as great as a reference value, if the value is equal or smaller as the reference
     * value.
     */
    public final void testEnsureAtMaximumWithFloatParameterThrowsNoException() {
        Condition.ensureAtMaximum(1f, 1f, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Float} value is
     * at maximum as great as a reference value and expects a class as a parameter, if the value is
     * greater as the reference value.
     */
    public final void testEnsureAtMaximumWithFloatAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtMaximum(2f, 1f, message, IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Float} value is
     * at maximum as great as a reference value and expects a class as a parameter, if the value is
     * equal or smaller as the reference value.
     */
    public final void testEnsureAtMaximumWithFloatAndClassParametersThrowsNoException() {
        Condition.ensureAtMaximum(1f, 1f, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Double} value is
     * at maximum as great as a reference value, if the value is greater as the reference value.
     */
    public final void testEnsureAtMaximumWithDoubleParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtMaximum(2d, 1d, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Double} value is
     * at maximum as great as a reference value, if the value is equal or smaller as the reference
     * value.
     */
    public final void testEnsureAtMaximumWithDoubleParameterThrowsNoException() {
        Condition.ensureAtMaximum(1d, 1d, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Double} value is
     * at maximum as great as a reference value and expects a class as a parameter, if the value is
     * greater as the reference value.
     */
    public final void testEnsureAtMaximumWithDoubleAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtMaximum(2d, 1d, message, IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Double} value is
     * at maximum as great as a reference value and expects a class as a parameter, if the value is
     * equal or smaller as the reference value.
     */
    public final void testEnsureAtMaximumWithDoubleAndClassParametersThrowsNoException() {
        Condition.ensureAtMaximum(1d, 1d, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Short} value is
     * greater as a reference value, if the value is equal or smaller as the reference value.
     */
    public final void testEnsureGreaterWithShortParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureGreater((short) 1, (short) 1, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Short} value is
     * greater as a reference value, if the value is greater as the reference value.
     */
    public final void testEnsureGreaterWithShortParameterThrowsNoException() {
        Condition.ensureGreater((short) 2, (short) 1, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Short} value is
     * greater as a reference value and expects a class as a parameter, if the value is equal or
     * greater as the reference value.
     */
    public final void testEnsureGreaterWithShortAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureGreater((short) 1, (short) 1, message, IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Short} value is
     * greater as a reference value and expects a class as a parameter, if the value is greater as
     * the reference value.
     */
    public final void testEnsureGreaterWithShortAndClassParametersThrowsNoException() {
        Condition.ensureGreater((short) 2, (short) 1, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an {@link Integer} value
     * is greater as a reference value, if the value is equal or smaller as the reference value.
     */
    public final void testEnsureGreaterWithIntegerParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureGreater(1, 1, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an {@link Integer} value
     * is greater as a reference value, if the value is greater as the reference value.
     */
    public final void testEnsureGreaterWithIntegerParameterThrowsNoException() {
        Condition.ensureGreater(2, 1, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an {@link Integer} value
     * is greater as a reference value and expects a class as a parameter, if the value is equal or
     * greater as the reference value.
     */
    public final void testEnsureGreaterWithIntegerAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureGreater(1, 1, message, IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an {@link Integer} value
     * is greater as a reference value and expects a class as a parameter, if the value is greater
     * as the reference value.
     */
    public final void testEnsureGreaterWithIntegerAndClassParametersThrowsNoException() {
        Condition.ensureGreater(2, 1, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Long} value is
     * greater as a reference value, if the value is equal or smaller as the reference value.
     */
    public final void testEnsureGreaterWithLongParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureGreater(1L, 1L, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Long} value is
     * greater as a reference value, if the value is greater as the reference value.
     */
    public final void testEnsureGreaterWithLongParameterThrowsNoException() {
        Condition.ensureGreater(2L, 1L, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Long} value is
     * greater as a reference value and expects a class as a parameter, if the value is equal or
     * greater as the reference value.
     */
    public final void testEnsureGreaterWithLongAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureGreater(1L, 1L, message, IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Long} value is
     * greater as a reference value and expects a class as a parameter, if the value is greater as
     * the reference value.
     */
    public final void testEnsureGreaterWithLongAndClassParametersThrowsNoException() {
        Condition.ensureGreater(2L, 1L, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Float} value is
     * greater as a reference value, if the value is equal or smaller as the reference value.
     */
    public final void testEnsureGreaterWithFloatParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureGreater(1f, 1f, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Float} value is
     * greater as a reference value, if the value is greater as the reference value.
     */
    public final void testEnsureGreaterWithFloatParameterThrowsNoException() {
        Condition.ensureGreater(2f, 1f, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Float} value is
     * greater as a reference value and expects a class as a parameter, if the value is equal or
     * greater as the reference value.
     */
    public final void testEnsureGreaterWithFloatAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureGreater(1f, 1f, message, IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Float} value is
     * greater as a reference value and expects a class as a parameter, if the value is greater as
     * the reference value.
     */
    public final void testEnsureGreaterWithFloatAndClassParametersThrowsNoException() {
        Condition.ensureGreater(2f, 1f, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Double} value is
     * greater as a reference value, if the value is equal or smaller as the reference value.
     */
    public final void testEnsureGreaterWithDoubleParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureGreater(1d, 1d, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Double} value is
     * greater as a reference value, if the value is greater as the reference value.
     */
    public final void testEnsureGreaterWithDoubleParameterThrowsNoException() {
        Condition.ensureGreater(2d, 1d, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Double} value is
     * greater as a reference value and expects a class as a parameter, if the value is equal or
     * greater as the reference value.
     */
    public final void testEnsureGreaterWithDoubleAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureGreater(1d, 1d, message, IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Double} value is
     * greater as a reference value and expects a class as a parameter, if the value is greater as
     * the reference value.
     */
    public final void testEnsureGreaterWithDoubleAndClassParametersThrowsNoException() {
        Condition.ensureGreater(2d, 1d, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Short} value is
     * smaller as a reference value, if the value is equal or greater as the reference value.
     */
    public final void testEnsureSmallerWithShortParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureSmaller((short) 1, (short) 1, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Short} value is
     * smaller as a reference value, if the value is smaller as the reference value.
     */
    public final void testEnsureSmallerWithShortParameterThrowsNoException() {
        Condition.ensureSmaller((short) 0, (short) 1, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Short} value is
     * smaller as a reference value and expects a class as a parameter, if the value is equal or
     * smaller as the reference value.
     */
    public final void testEnsureSmallerWithShortAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureSmaller((short) 1, (short) 1, message, IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Short} value is
     * smaller as a reference value and expects a class as a parameter, if the value is smaller as
     * the reference value.
     */
    public final void testEnsureSmallerWithShortAndClassParametersThrowsNoException() {
        Condition.ensureSmaller((short) 0, (short) 1, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an {@link Integer} value
     * is smaller as a reference value, if the value is equal or greater as the reference value.
     */
    public final void testEnsureSmallerWithIntegerParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureSmaller(1, 1, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an {@link Integer} value
     * is smaller as a reference value, if the value is smaller as the reference value.
     */
    public final void testEnsureSmallerWithIntegerParameterThrowsNoException() {
        Condition.ensureSmaller(0, 1, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an {@link Integer} value
     * is smaller as a reference value and expects a class as a parameter, if the value is equal or
     * smaller as the reference value.
     */
    public final void testEnsureSmallerWithIntegerAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureSmaller(1, 1, message, IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an {@link Integer} value
     * is smaller as a reference value and expects a class as a parameter, if the value is smaller
     * as the reference value.
     */
    public final void testEnsureSmallerWithIntegerAndClassParametersThrowsNoException() {
        Condition.ensureSmaller(0, 1, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Long} value is
     * smaller as a reference value, if the value is equal or greater as the reference value.
     */
    public final void testEnsureSmallerWithLongParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureSmaller(1L, 1L, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Long} value is
     * smaller as a reference value, if the value is smaller as the reference value.
     */
    public final void testEnsureSmallerWithLongParameterThrowsNoException() {
        Condition.ensureSmaller(0L, 1L, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Long} value is
     * smaller as a reference value and expects a class as a parameter, if the value is equal or
     * smaller as the reference value.
     */
    public final void testEnsureSmallerWithLongAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureSmaller(1L, 1L, message, IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Long} value is
     * smaller as a reference value and expects a class as a parameter, if the value is smaller as
     * the reference value.
     */
    public final void testEnsureSmallerWithLongAndClassParametersThrowsNoException() {
        Condition.ensureSmaller(0L, 1L, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Float} value is
     * smaller as a reference value, if the value is equal or greater as the reference value.
     */
    public final void testEnsureSmallerWithFloatParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureSmaller(1f, 1f, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Float} value is
     * smaller as a reference value, if the value is smaller as the reference value.
     */
    public final void testEnsureSmallerWithFloatParameterThrowsNoException() {
        Condition.ensureSmaller(0f, 1f, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Float} value is
     * smaller as a reference value and expects a class as a parameter, if the value is equal or
     * smaller as the reference value.
     */
    public final void testEnsureSmallerWithFloatAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureSmaller(1f, 1f, message, IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Float} value is
     * smaller as a reference value and expects a class as a parameter, if the value is smaller as
     * the reference value.
     */
    public final void testEnsureSmallerWithFloatAndClassParametersThrowsNoException() {
        Condition.ensureSmaller(0f, 1f, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Double} value is
     * smaller as a reference value, if the value is equal or greater as the reference value.
     */
    public final void testEnsureSmallerWithDoubleParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureSmaller(1d, 1d, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Double} value is
     * smaller as a reference value, if the value is smaller as the reference value.
     */
    public final void testEnsureSmallerWithDoubleParameterThrowsNoException() {
        Condition.ensureSmaller(0d, 1d, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Double} value is
     * smaller as a reference value and expects a class as a parameter, if the value is equal or
     * smaller as the reference value.
     */
    public final void testEnsureSmallerWithDoubleAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureSmaller(1d, 1d, message, IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Double} value is
     * smaller as a reference value and expects a class as a parameter, if the value is smaller as
     * the reference value.
     */
    public final void testEnsureSmallerWithDoubleAndClassParametersThrowsNoException() {
        Condition.ensureSmaller(0d, 1d, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link File} exists, if
     * the file does not exist.
     */
    public final void testEnsureFileExistsThrowsException() {
        String message = "message";
        File file = new File(getContext().getFilesDir(), "test");

        try {
            Condition.ensureFileExists(file, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link File} exists, if
     * the file does exist.
     *
     * @throws IOException
     *         The exception, which is thrown, if an error occurs while creating the file
     */
    public final void testEnsureFileExistsThrowsNoException() throws IOException {
        String message = "message";
        File file = new File(getContext().getFilesDir(), "test");

        try {
            boolean created = file.createNewFile();
            assertTrue(created);
            Condition.ensureFileExists(file, message);
        } finally {
            file.delete();
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link File} exists and
     * expects a class as a parameter, if the file does not exist.
     */
    public final void testEnsureFileExistsWithClassParameterThrowsException() {
        String message = "message";
        File file = new File(getContext().getFilesDir(), "test");

        try {
            Condition.ensureFileExists(file, message, IllegalStateException.class);
            Assert.fail();
        } catch (IllegalStateException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link File} exists and
     * expects a class as a paramter, if the file does exist.
     *
     * @throws IOException
     *         The exception, which is thrown, if an error occurs while creating the file
     */
    public final void testEnsureFileExistsWithClassParameterThrowsNoException() throws IOException {
        String message = "message";
        File file = new File(getContext().getFilesDir(), "test");

        try {
            boolean created = file.createNewFile();
            assertTrue(created);
            Condition.ensureFileExists(file, message, IllegalStateException.class);
        } finally {
            file.delete();
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link File} is a
     * directory, if the file is not a directory.
     *
     * @throws IOException
     *         The exception, which is thrown, if an error occurs while creating the file
     */
    public final void testEnsureFileIsDirectoryThrowsException() throws IOException {
        String message = "message";
        File file = new File(getContext().getFilesDir(), "test");

        try {
            boolean created = file.createNewFile();
            assertTrue(created);
            Condition.ensureFileIsDirectory(file, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        } finally {
            file.delete();
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link File} is a
     * directory, if the file is a directory.
     *
     * @throws IOException
     *         The exception, which is thrown, if an error occurs while creating the file
     */
    public final void testEnsureFileIsDirectoryThrowsNoException() throws IOException {
        String message = "message";
        File file = new File(getContext().getFilesDir(), "test");

        try {
            boolean created = file.mkdirs();
            assertTrue(created);
            Condition.ensureFileIsDirectory(file, message);
        } finally {
            file.delete();
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link File} is a
     * directory and expects a class as a parameter, if the file is not a directory.
     *
     * @throws IOException
     *         The exception, which is thrown, if an error occurs while creating the file
     */
    public final void testEnsureFileIsDirectoryWithClassParameterThrowsException()
            throws IOException {
        String message = "message";
        File file = new File(getContext().getFilesDir(), "test");

        try {
            boolean created = file.createNewFile();
            assertTrue(created);
            Condition.ensureFileIsDirectory(file, message, IllegalStateException.class);
            Assert.fail();
        } catch (IllegalStateException e) {
            assertEquals(message, e.getMessage());
        } finally {
            file.delete();
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link File} is a
     * directory and expects a class as a parameter, if the file is a directory.
     *
     * @throws IOException
     *         The exception, which is thrown, if an error occurs while creating the file
     */
    public final void testEnsureFileIsDirectoryWithClassParameterThrowsNoException()
            throws IOException {
        String message = "message";
        File file = new File(getContext().getFilesDir(), "test");

        try {
            boolean created = file.mkdirs();
            assertTrue(created);
            Condition.ensureFileIsDirectory(file, message, IllegalStateException.class);
        } finally {
            file.delete();
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link File} is not a
     * directory, if the file is a directory.
     *
     * @throws IOException
     *         The exception, which is thrown, if an error occurs while creating the file
     */
    public final void testEnsureFileIsNoDirectoryThrowsException() throws IOException {
        String message = "message";
        File file = new File(getContext().getFilesDir(), "test");

        try {
            boolean created = file.mkdirs();
            assertTrue(created);
            Condition.ensureFileIsNoDirectory(file, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        } finally {
            file.delete();
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link File} is not a
     * directory, if the file is not a directory.
     *
     * @throws IOException
     *         The exception, which is thrown, if an error occurs while creating the file
     */
    public final void testEnsureFileIsNoDirectoryThrowsNoException() throws IOException {
        String message = "message";
        File file = new File(getContext().getFilesDir(), "test");

        try {
            boolean created = file.createNewFile();
            assertTrue(created);
            Condition.ensureFileIsNoDirectory(file, message);
        } finally {
            file.delete();
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link File} is not a
     * directory and expects a class as a parameter, if the file is a directory.
     *
     * @throws IOException
     *         The exception, which is thrown, if an error occurs while creating the file
     */
    public final void testEnsureFileIsNoDirectoryWithClassParameterThrowsException()
            throws IOException {
        String message = "message";
        File file = new File(getContext().getFilesDir(), "test");

        try {
            boolean created = file.mkdirs();
            assertTrue(created);
            Condition.ensureFileIsNoDirectory(file, message, IllegalStateException.class);
            Assert.fail();
        } catch (IllegalStateException e) {
            assertEquals(message, e.getMessage());
        } finally {
            file.delete();
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link File} is not a
     * directory and expects a class as a parameter, if the file is not a directory.
     *
     * @throws IOException
     *         The exception, which is thrown, if an error occurs while creating the file
     */
    public final void testEnsureFileIsNoDirectoryWithClassParameterThrowsNoException()
            throws IOException {
        String message = "message";
        File file = new File(getContext().getFilesDir(), "test");

        try {
            boolean created = file.createNewFile();
            assertTrue(created);
            Condition.ensureFileIsNoDirectory(file, message, IllegalStateException.class);
        } finally {
            file.delete();
        }
    }

}