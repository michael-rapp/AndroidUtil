/*
 * Copyright 2015 - 2017 Michael Rapp
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
package de.mrapp.android.util.gesture;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Tests the functionality of the class {@link DragHelper}.
 *
 * @author Michael Rapp
 */
public class DragHelperTest extends TestCase {

    /**
     * Tests, if all properties are set properly by the constructor.
     */
    public final void testConstructor() {
        int threshold = 1;
        DragHelper dragHelper = new DragHelper(threshold);
        assertTrue(dragHelper.isReset());
        assertFalse(dragHelper.hasThresholdBeenReached());
        assertEquals(-1f, dragHelper.getDragSpeed());
        assertEquals(-1f, dragHelper.getDragStartPosition());
        assertEquals(0f, dragHelper.getDragDistance());
    }

    /**
     * Ensures, that an {@link IllegalArgumentException} is thrown by the constructor, if the
     * threshold is less than 0.
     */
    public final void testConstructorThrowsException() {
        try {
            new DragHelper(-1);
            Assert.fail();
        } catch (IllegalArgumentException e) {

        }
    }

    /**
     * Tests the functionality of the update-method.
     */
    public final void testUpdate() {
        DragHelper dragHelper = new DragHelper(1);
        dragHelper.update(0f);
        assertFalse(dragHelper.hasThresholdBeenReached());
        assertEquals(-1f, dragHelper.getDragSpeed());
        assertEquals(0f, dragHelper.getDragStartPosition());
        assertEquals(0f, dragHelper.getDragDistance());
        dragHelper.update(1f);
        assertTrue(dragHelper.hasThresholdBeenReached());
        assertNotSame(-1f, dragHelper.getDragSpeed());
        assertEquals(0f, dragHelper.getDragStartPosition());
        assertEquals(0f, dragHelper.getDragDistance());
        dragHelper.update(2f);
        assertTrue(dragHelper.hasThresholdBeenReached());
        assertNotSame(-1f, dragHelper.getDragSpeed());
        assertEquals(0f, dragHelper.getDragStartPosition());
        assertEquals(1f, dragHelper.getDragDistance());
    }

    /**
     * Tests the functionality of the update-method.
     */
    public final void testReset() {
        DragHelper dragHelper = new DragHelper(1);
        assertTrue(dragHelper.isReset());
        dragHelper.update(0f);
        assertFalse(dragHelper.isReset());
        dragHelper.update(1f);
        dragHelper.update(2f);
        assertTrue(dragHelper.hasThresholdBeenReached());
        assertNotSame(-1f, dragHelper.getDragSpeed());
        assertEquals(1f, dragHelper.getDragDistance());
        assertEquals(0f, dragHelper.getDragStartPosition());
        assertFalse(dragHelper.isReset());
        dragHelper.reset();
        assertTrue(dragHelper.hasThresholdBeenReached());
        assertNotSame(-1f, dragHelper.getDragSpeed());
        assertEquals(1f, dragHelper.getDragDistance());
        assertEquals(0f, dragHelper.getDragStartPosition());
        assertTrue(dragHelper.isReset());
        dragHelper.update(1f);
        assertFalse(dragHelper.hasThresholdBeenReached());
        assertEquals(-1f, dragHelper.getDragSpeed());
        assertEquals(0f, dragHelper.getDragDistance());
        assertEquals(1f, dragHelper.getDragStartPosition());
        assertFalse(dragHelper.isReset());
    }

}