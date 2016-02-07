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

import static de.mrapp.android.util.Condition.ensureAtLeast;

/**
 * A helper class, which may be used to recognize drag gestures.
 *
 * @author Michael Rapp
 * @since 1.4.0
 */
public class DragHelper {

    /**
     * The distance in pixels, the gesture must last until it is recognized.
     */
    private final int threshold;

    /**
     * The distance, which has been passed while dragging, in pixels or 0, if the threshold has not
     * been reached yet.
     */
    private int distance;

    /**
     * The position, where the threshold was reached or -1, if the threshold has not been reached
     * yet.
     */
    private int thresholdReachedPosition;

    /**
     * The position, where the gesture has been started at or -1, if no gesture has been started
     * yet.
     */
    private int dragStartPosition;

    /**
     * The time, when the gesture has been started or -1, if no gesture has been started yet.
     */
    private long dragStartTime;

    /**
     * True, if the method <code>reset():void</code> has been called since the last value has been
     * added, false otherwise.
     */
    private boolean reseted;

    /**
     * True, if the threshold has already been reached, false otherwise.
     */
    private boolean reachedThreshold;

    /**
     * Returns, whether the threshold is reached by a specific distance.
     *
     * @param distance
     *         The distance, which should be checked, as an {@link Integer} value
     * @return True, if the threshold is reached by the given distance, false otherwise
     */
    private boolean reachedThreshold(final int distance) {
        return Math.abs(distance) >= threshold;
    }

    /**
     * Creates a new helper class, which may be used to recognize drag gestures.
     *
     * @param threshold
     *         The distance in pixels, the gesture must last until it is recognized, as an {@link
     *         Integer} value. The value must be at least 0
     */
    public DragHelper(final int threshold) {
        ensureAtLeast(threshold, 0, "The threshold must be at least 0");
        this.threshold = threshold;
        this.distance = 0;
        this.thresholdReachedPosition = -1;
        this.dragStartPosition = -1;
        this.dragStartTime = -1;
        this.reachedThreshold = false;
        reset();
    }

    /**
     * Marks the instance to be reseted. This will cause all properties to be reseted to default
     * values, when a value is added by calling the method <code>update(float):void</code> the next
     * time. Therefore this method may be used to start recognizing a new drag gesture, whenever a
     * value is added the next time, while the values of the previous recognition can be still
     * retrieved until recognizing the new gesture begins.
     */
    public final void reset() {
        reseted = true;
    }

    /**
     * Returns, whether the instance has been marked to be reseted, since the method
     * <code>update(float):void</code> has been called the last time. See method
     * <code>reset():void</code> for further information.
     *
     * @return True, if the instance has been marked to be reseted, false otherwise
     */
    public final boolean isReseted() {
        return reseted;
    }

    /**
     * Updates the instance by adding a new position. This will cause all properties to be
     * re-calculated, depending on the new position.
     *
     * @param position
     *         The position, which should be added, as a {@link Float} value
     */
    public final void update(final float position) {
        int roundedPosition = Math.round(position);

        if (reseted) {
            reseted = false;
            distance = 0;
            thresholdReachedPosition = -1;
            dragStartTime = -1;
            dragStartPosition = roundedPosition;
            reachedThreshold = false;
        } else {
            if (!reachedThreshold) {
                if (reachedThreshold(roundedPosition - dragStartPosition)) {
                    dragStartTime = System.currentTimeMillis();
                    reachedThreshold = true;
                    thresholdReachedPosition = roundedPosition;
                }
            } else {
                distance = roundedPosition - thresholdReachedPosition;
            }
        }
    }

    /**
     * Returns, whether the threshold has already been reached, or not.
     *
     * @return True, if the threshold has been already reached, false otherwise
     */
    public final boolean hasThresholdBeenReached() {
        return reachedThreshold;
    }

    /**
     * Returns the distance, which has been passed while dragging, in pixels.
     *
     * @return The distance, which has been passed while dragging, as an {@link Integer} value or 0,
     * if the threshold has not been reached yet
     */
    public final int getDistance() {
        return distance;
    }

    /**
     * Returns the position, where the gesture has been started at.
     *
     * @return The position, where the gesture has been started at, as an {@link Integer} value or
     * -1, if no gesture has been started yet
     */
    public final int getStartPosition() {
        return dragStartPosition;
    }

    /**
     * Returns the speed of the drag gesture in pixels per millisecond.
     *
     * @return The speed of the drag gesture as a {@link Float} value or -1, if the threshold has
     * not been reached yet
     */
    public final float getDragSpeed() {
        if (hasThresholdBeenReached()) {
            long interval = System.currentTimeMillis() - dragStartTime;
            return (float) Math.abs(getDistance()) / (float) interval;
        } else {
            return -1;
        }
    }

}