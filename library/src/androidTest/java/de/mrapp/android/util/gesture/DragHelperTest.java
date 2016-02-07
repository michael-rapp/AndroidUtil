/*
 * AndroidUtil Copyright 2015 - 2016 Michael Rapp
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
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
        assertTrue(dragHelper.isReseted());
        assertFalse(dragHelper.hasThresholdBeenReached());
        assertEquals(-1.0f, dragHelper.getDragSpeed());
        assertEquals(-1, dragHelper.getStartPosition());
        assertEquals(0, dragHelper.getDistance());
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
        dragHelper.update(0);
        assertFalse(dragHelper.hasThresholdBeenReached());
        assertEquals(-1.0f, dragHelper.getDragSpeed());
        assertEquals(0, dragHelper.getStartPosition());
        assertEquals(0, dragHelper.getDistance());
        dragHelper.update(1);
        assertTrue(dragHelper.hasThresholdBeenReached());
        assertNotSame(-1.0f, dragHelper.getDragSpeed());
        assertEquals(0, dragHelper.getStartPosition());
        assertEquals(0, dragHelper.getDistance());
        dragHelper.update(2);
        assertTrue(dragHelper.hasThresholdBeenReached());
        assertNotSame(-1.0f, dragHelper.getDragSpeed());
        assertEquals(0, dragHelper.getStartPosition());
        assertEquals(1, dragHelper.getDistance());
    }

    /**
     * Tests the functionality of the update-method.
     */
    public final void testReset() {
        DragHelper dragHelper = new DragHelper(1);
        assertTrue(dragHelper.isReseted());
        dragHelper.update(0);
        assertFalse(dragHelper.isReseted());
        dragHelper.update(1);
        dragHelper.update(2);
        assertTrue(dragHelper.hasThresholdBeenReached());
        assertNotSame(-1.0f, dragHelper.getDragSpeed());
        assertEquals(1, dragHelper.getDistance());
        assertEquals(0, dragHelper.getStartPosition());
        assertFalse(dragHelper.isReseted());
        dragHelper.reset();
        assertTrue(dragHelper.hasThresholdBeenReached());
        assertNotSame(-1.0f, dragHelper.getDragSpeed());
        assertEquals(1, dragHelper.getDistance());
        assertEquals(0, dragHelper.getStartPosition());
        assertTrue(dragHelper.isReseted());
        dragHelper.update(1);
        assertFalse(dragHelper.hasThresholdBeenReached());
        assertEquals(-1.0f, dragHelper.getDragSpeed());
        assertEquals(0, dragHelper.getDistance());
        assertEquals(1, dragHelper.getStartPosition());
        assertFalse(dragHelper.isReseted());
    }

}