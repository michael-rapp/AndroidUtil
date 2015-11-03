/*
 * AndroidUtil Copyright 2015 Michael Rapp
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 */
package de.mrapp.android.util.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import de.mrapp.android.util.ElevationUtil.Orientation;

import static de.mrapp.android.util.ElevationUtil.createElevationShadow;

/**
 * The example app's main activity.
 *
 * @author Michael Rapp
 */
public class MainActivity extends AppCompatActivity {

    /**
     * The seek bar, which allows to adjust the elevation.
     */
    private SeekBar elevationSeekBar;

    /**
     * The text view, which displays the current elevation.
     */
    private TextView elevationTextView;

    /**
     * The checkbox, which allows to specify, if parallel light should be emulated, or not.
     */
    private CheckBox parallelLightCheckBox;

    /**
     * The image view, which is used to display the shadow, which is located besides the left edge
     * of the elevated view.
     */
    private ImageView elevationLeft;

    /**
     * The image view, which is used to display the shadow, which is located at the top left corner
     * of the elevated view.
     */
    private ImageView elevationTopLeft;

    /**
     * The image view, which is used to display the shadow, which is located above the top edge of
     * the elevated view.
     */
    private ImageView elevationTop;

    /**
     * The image view, which is used to display the shadow, which is located at the top right corner
     * of the elevated view.
     */
    private ImageView elevationTopRight;

    /**
     * The image view, which is used to display the shadow, which is located besides the right edge
     * of the elevated view.
     */
    private ImageView elevationRight;

    /**
     * The image view, which is used to display the shadow, which is located at the bottom right
     * corner of the elevated view.
     */
    private ImageView elevationBottomRight;

    /**
     * The image view, which is used to display the shadow, which is located below the bottom edge
     * of the elevated view.
     */
    private ImageView elevationBottom;

    /**
     * The image view, which is used to display the shadow, which is located at the bottom left
     * corner of the elevated view.
     */
    private ImageView elevationBottomLeft;

    /**
     * Initializes the activity's toolbar.
     */
    private void initializeToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * Returns the default elevation.
     *
     * @return The default elevation in dp, as an {@link Integer} value
     */
    private int getDefaultElevation() {
        return getResources().getInteger(R.integer.default_elevation);
    }

    /**
     * Returns, whether parallel light should be emulated by default, or not.
     *
     * @return True, if parallel light should be emulated by default, false otherwise
     */
    private boolean isParallelLightUsedByDefault() {
        return getResources().getBoolean(R.bool.use_parallel_light_by_default);
    }

    /**
     * Creates and returns a listener, which allows to adjust the elevation, when the value of a
     * seek bar has been changed.
     *
     * @return The listener, which has been created, as an instance of the type {@link
     * OnSeekBarChangeListener}
     */
    private OnSeekBarChangeListener createSeekBarListener() {
        return new OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(final SeekBar seekBar, final int progress,
                                          final boolean fromUser) {
                adaptElevation(progress, parallelLightCheckBox.isChecked());
            }

            @Override
            public void onStartTrackingTouch(final SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(final SeekBar seekBar) {

            }

        };
    }

    private OnCheckedChangeListener createCheckBoxListener() {
        return new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                adaptElevation(elevationSeekBar.getProgress(), isChecked);
            }

        };
    }

    /**
     * Adapts the elevation.
     *
     * @param elevation
     *         The elevation, which should be set, in dp as an {@link Integer} value
     * @param parallelLight
     *         True, if parallel light should be emulated, false otherwise
     */
    private void adaptElevation(final int elevation, final boolean parallelLight) {
        elevationTextView.setText(String.format(getString(R.string.elevation), elevation));
        elevationLeft.setImageBitmap(
                createElevationShadow(this, elevation, Orientation.LEFT, parallelLight));
        elevationTopLeft.setImageBitmap(
                createElevationShadow(this, elevation, Orientation.TOP_LEFT, parallelLight));
        elevationTop.setImageBitmap(
                createElevationShadow(this, elevation, Orientation.TOP, parallelLight));
        elevationTopRight.setImageBitmap(
                createElevationShadow(this, elevation, Orientation.TOP_RIGHT, parallelLight));
        elevationRight.setImageBitmap(
                createElevationShadow(this, elevation, Orientation.RIGHT, parallelLight));
        elevationBottomRight.setImageBitmap(
                createElevationShadow(this, elevation, Orientation.BOTTOM_RIGHT, parallelLight));
        elevationBottom.setImageBitmap(
                createElevationShadow(this, elevation, Orientation.BOTTOM, parallelLight));
        elevationBottomLeft.setImageBitmap(
                createElevationShadow(this, elevation, Orientation.BOTTOM_LEFT, parallelLight));
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeToolbar();
        elevationSeekBar = (SeekBar) findViewById(R.id.elevation_seek_bar);
        elevationSeekBar.setProgress(getDefaultElevation());
        elevationSeekBar.setOnSeekBarChangeListener(createSeekBarListener());
        elevationTextView = (TextView) findViewById(R.id.elevation_text_view);
        parallelLightCheckBox = (CheckBox) findViewById(R.id.parallel_light_check_box);
        parallelLightCheckBox.setChecked(isParallelLightUsedByDefault());
        parallelLightCheckBox.setOnCheckedChangeListener(createCheckBoxListener());
        elevationLeft = (ImageView) findViewById(R.id.elevation_left);
        elevationTopLeft = (ImageView) findViewById(R.id.elevation_top_left);
        elevationTop = (ImageView) findViewById(R.id.elevation_top);
        elevationTopRight = (ImageView) findViewById(R.id.elevation_top_right);
        elevationRight = (ImageView) findViewById(R.id.elevation_right);
        elevationBottomRight = (ImageView) findViewById(R.id.elevation_bottom_right);
        elevationBottom = (ImageView) findViewById(R.id.elevation_bottom);
        elevationBottomLeft = (ImageView) findViewById(R.id.elevation_bottom_left);
        adaptElevation(getDefaultElevation(), isParallelLightUsedByDefault());
    }

}