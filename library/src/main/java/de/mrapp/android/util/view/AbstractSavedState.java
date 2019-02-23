/*
 * Copyright 2015 - 2019 Michael Rapp
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
package de.mrapp.android.util.view;

import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.AbsSavedState;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import de.mrapp.util.Condition;

/**
 * An abstract base class for all data structures, which allow to store the internal state of a
 * view. This class identical to the class {@link AbsSavedState} of the Android SDK, except for the
 * fact, that it does use a custom class loader instead of the system's one. The reason for this is,
 * that using the class {@link AbsSavedState} in a library project, may cause a {@link
 * BadParcelableException} to be thrown when unmarshalling.
 *
 * @author Michael Rapp
 * @since 1.8.0
 */
public abstract class AbstractSavedState implements Parcelable {

    /**
     * The state of the superclass of the view, this saved state corresponds to.
     */
    private final Parcelable superState;

    /**
     * Creates a new data structure, which allows to store the internal state of a view. This
     * constructor is used when reading from a parcel. It reads the state of the superclass.
     *
     * @param source
     *         The parcel to read read from as a instance of the class {@link Parcel}. The parcel
     *         may not be null
     */
    protected AbstractSavedState(@NonNull final Parcel source) {
        Condition.INSTANCE.ensureNotNull(source, "The parcel may not be null");
        superState = source.readParcelable(getClass().getClassLoader());
    }

    /**
     * Creates a new data structure, which allows to store the internal state of a view. This
     * constructor is called by derived classes when saving their states.
     *
     * @param superState
     *         The state of the superclass of the view, this saved state corresponds to, as an
     *         instance of the type {@link Parcelable} or null if no state is available
     */
    protected AbstractSavedState(@Nullable final Parcelable superState) {
        this.superState = superState;
    }

    /**
     * Returns the state of the superclass of the view, this saved state corresponds to.
     *
     * @return The state of the superclass of the view, this saved state corresponds to, as an
     * instance of the type {@link Parcelable} or null, if no state is available
     */
    public final Parcelable getSuperState() {
        return superState;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @CallSuper
    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeParcelable(superState, flags);
    }

}