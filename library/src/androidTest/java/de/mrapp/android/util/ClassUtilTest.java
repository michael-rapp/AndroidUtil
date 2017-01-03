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