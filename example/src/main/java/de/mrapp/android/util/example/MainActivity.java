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
package de.mrapp.android.util.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import de.mrapp.android.util.view.ElevationShadowView;

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
    private ElevationShadowView elevationLeft;

    /**
     * The image view, which is used to display the shadow, which is located at the top left corner
     * of the elevated view.
     */
    private ElevationShadowView elevationTopLeft;

    /**
     * The image view, which is used to display the shadow, which is located above the top edge of
     * the elevated view.
     */
    private ElevationShadowView elevationTop;

    /**
     * The image view, which is used to display the shadow, which is located at the top right corner
     * of the elevated view.
     */
    private ElevationShadowView elevationTopRight;

    /**
     * The image view, which is used to display the shadow, which is located besides the right edge
     * of the elevated view.
     */
    private ElevationShadowView elevationRight;

    /**
     * The image view, which is used to display the shadow, which is located at the bottom right
     * corner of the elevated view.
     */
    private ElevationShadowView elevationBottomRight;

    /**
     * The image view, which is used to display the shadow, which is located below the bottom edge
     * of the elevated view.
     */
    private ElevationShadowView elevationBottom;

    /**
     * The image view, which is used to display the shadow, which is located at the bottom left
     * corner of the elevated view.
     */
    private ElevationShadowView elevationBottomLeft;

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
        elevationLeft.setShadowElevation(elevation);
        elevationLeft.emulateParallelLight(parallelLight);
        elevationTopLeft.setShadowElevation(elevation);
        elevationTopLeft.emulateParallelLight(parallelLight);
        elevationTop.setShadowElevation(elevation);
        elevationTop.emulateParallelLight(parallelLight);
        elevationTopRight.setShadowElevation(elevation);
        elevationTopRight.emulateParallelLight(parallelLight);
        elevationRight.setShadowElevation(elevation);
        elevationRight.emulateParallelLight(parallelLight);
        elevationBottomRight.setShadowElevation(elevation);
        elevationBottomRight.emulateParallelLight(parallelLight);
        elevationBottom.setShadowElevation(elevation);
        elevationBottom.emulateParallelLight(parallelLight);
        elevationBottomLeft.setShadowElevation(elevation);
        elevationBottomLeft.emulateParallelLight(parallelLight);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        elevationSeekBar = (SeekBar) findViewById(R.id.elevation_seek_bar);
        elevationSeekBar.setProgress(getDefaultElevation());
        elevationSeekBar.setOnSeekBarChangeListener(createSeekBarListener());
        elevationTextView = (TextView) findViewById(R.id.elevation_text_view);
        parallelLightCheckBox = (CheckBox) findViewById(R.id.parallel_light_check_box);
        parallelLightCheckBox.setChecked(isParallelLightUsedByDefault());
        parallelLightCheckBox.setOnCheckedChangeListener(createCheckBoxListener());
        elevationLeft = (ElevationShadowView) findViewById(R.id.elevation_left);
        elevationTopLeft = (ElevationShadowView) findViewById(R.id.elevation_top_left);
        elevationTop = (ElevationShadowView) findViewById(R.id.elevation_top);
        elevationTopRight = (ElevationShadowView) findViewById(R.id.elevation_top_right);
        elevationRight = (ElevationShadowView) findViewById(R.id.elevation_right);
        elevationBottomRight = (ElevationShadowView) findViewById(R.id.elevation_bottom_right);
        elevationBottom = (ElevationShadowView) findViewById(R.id.elevation_bottom);
        elevationBottomLeft = (ElevationShadowView) findViewById(R.id.elevation_bottom_left);
        adaptElevation(getDefaultElevation(), isParallelLightUsedByDefault());
    }

}