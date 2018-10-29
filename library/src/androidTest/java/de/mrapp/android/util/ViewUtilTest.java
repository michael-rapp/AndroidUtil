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

import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import android.test.AndroidTestCase;
import android.view.View;

import junit.framework.Assert;

/**
 * Tests the functionality of the class {@link ViewUtil}.
 *
 * @author Michael Rapp
 */
public class ViewUtilTest extends AndroidTestCase {

    /**
     * Tests the functionality of the method, which allows to set the background of a view.
     */
    public final void testSetBackground() {
        View view = new View(getContext());
        Drawable background = ContextCompat.getDrawable(getContext(), android.R.drawable.ic_delete);
        ViewUtil.setBackground(view, background);
        assertEquals(background, view.getBackground());
    }

    /**
     * Ensures, that a {@link NullPointerException} is thrown by the method, which allows to set the
     * background of a view, if the view is null.
     */
    public final void testSetBackgroundThrowsException() {
        try {
            ViewUtil.setBackground(null, null);
            Assert.fail();
        } catch (NullPointerException e) {

        }
    }

}