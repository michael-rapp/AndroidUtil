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
package de.mrapp.android.util;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Tests the functionality of the class {@link ClassUtil}.
 */
public class ClassUtilTest extends TestCase {

    /**
     * Tests the functionality of the method, which allows to retrieve a truncated version of a
     * class' full qualified name.
     */
    public final void testGetTruncatedName() {
        assertEquals("j.l.Object", ClassUtil.getTruncatedName(Object.class));
    }

    /**
     * Ensures, that a {@link NullPointerException} is thrown by the method, which allows to
     * retrieve a truncated version of a class' full qualified name, if the given class is null.
     */
    public final void testGetTruncatedNameThrowsException() {
        try {
            ClassUtil.getTruncatedName(null);
            Assert.fail();
        } catch (NullPointerException e) {
            return;
        }
    }

}