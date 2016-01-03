/*
 * AndroidUtil Copyright 2015 Michael Rapp
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

/**
 * Contains all possible types of devices, depending on their display size.
 *
 * @author Michael Rapp
 * @since 1.3.0
 */
public enum DeviceType {

    /**
     * When the device is a phone, e.g. 4-, 5- or 6-inch devices like Google's Nexus 4, 5 and 6.
     */
    PHONE("phone"),

    /**
     * When the device is a small tablet (phablet), e.g. a 7-inch devices like Google's Nexus 7.
     */
    PHABLET("phablet"),

    /**
     * When the device is a tablet, e.g. a 10-inch devices like Google's Nexus 10.
     */
    TABLET("tablet");

    /**
     * The value of the device type.
     */
    private String value;

    /**
     * Creates a new device type.
     *
     * @param value
     *         The value of the device type as a {@link String}
     */
    DeviceType(final String value) {
        this.value = value;
    }

    /**
     * Returns the value of the device type.
     *
     * @return The value of the device type as a {@link String}
     */
    public final String getValue() {
        return value;
    }

    /**
     * Returns the device type, which corresponds to a specific value.
     *
     * @param value
     *         The value of the device type, which should be returned, as a {@link String}
     * @return The device type, which corresponds to the given value, as a value of the enum {@link
     * DeviceType}
     */
    public static DeviceType fromValue(final String value) {
        for (DeviceType deviceType : values()) {
            if (deviceType.getValue().equals(value)) {
                return deviceType;
            }
        }

        throw new IllegalArgumentException("Invalid enum value: " + value);
    }

}