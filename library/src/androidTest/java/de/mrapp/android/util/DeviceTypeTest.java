package de.mrapp.android.util;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Tests the functionality of the class {@link DeviceType}.
 *
 * @author Michael Rapp
 */
public class DeviceTypeTest extends TestCase {

    /**
     * Tests the functionality of the getValue-method.
     */
    public final void testGetValue() {
        assertEquals("phone", DeviceType.PHONE.getValue());
        assertEquals("phablet", DeviceType.PHABLET.getValue());
        assertEquals("tablet", DeviceType.TABLET.getValue());
    }

    /**
     * Tests the functionality of the fromValue-method.
     */
    public final void testFromValue() {
        assertEquals(DeviceType.PHONE, DeviceType.fromValue("phone"));
        assertEquals(DeviceType.PHABLET, DeviceType.fromValue("phablet"));
        assertEquals(DeviceType.TABLET, DeviceType.fromValue("tablet"));
    }

    /**
     * Ensures, that an {@link IllegalArgumentException} is thrown by the fromValue-method, if the
     * given value is invalid.
     */
    public final void testFromValueThrowsException() {
        try {
            DeviceType.fromValue("foo");
            Assert.fail();
        } catch (IllegalArgumentException e) {

        }
    }

}