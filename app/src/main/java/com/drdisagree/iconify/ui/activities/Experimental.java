package com.drdisagree.iconify.ui.activities;

import static com.drdisagree.iconify.common.Const.SWITCH_ANIMATION_DELAY;
import static com.drdisagree.iconify.common.Preferences.HEADER_IMAGE_OVERLAP;
import static com.drdisagree.iconify.common.Preferences.HIDE_DATA_DISABLED_ICON;
import static com.drdisagree.iconify.common.Preferences.UNZOOM_DEPTH_WALLPAPER;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.drdisagree.iconify.R;
import com.drdisagree.iconify.config.RPrefs;
import com.drdisagree.iconify.databinding.ActivityExperimentalBinding;
import com.drdisagree.iconify.ui.utils.ViewHelper;
import com.drdisagree.iconify.utils.SystemUtil;

public class Experimental extends BaseActivity {

    private ActivityExperimentalBinding binding;

    @SuppressLint({"SetTextI18n", "CommitPrefEdits"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExperimentalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Header
        ViewHelper.setHeader(this, binding.header.toolbar, R.string.activity_title_experimental);

        // Header image overlap
        binding.headerImageOverlap.setChecked(RPrefs.getBoolean(HEADER_IMAGE_OVERLAP, false));
        binding.headerImageOverlap.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            RPrefs.putBoolean(HEADER_IMAGE_OVERLAP, isChecked);
            new Handler(Looper.getMainLooper()).postDelayed(SystemUtil::restartSystemUI, SWITCH_ANIMATION_DELAY);
        });
        binding.headerImageOverlapContainer.setOnClickListener(v -> binding.headerImageOverlap.toggle());

        // Unzoom depth wallpaper
        binding.unzoomDepthWallpaper.setChecked(RPrefs.getBoolean(UNZOOM_DEPTH_WALLPAPER, false));
        binding.unzoomDepthWallpaper.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            RPrefs.putBoolean(UNZOOM_DEPTH_WALLPAPER, isChecked);
            new Handler(Looper.getMainLooper()).postDelayed(SystemUtil::restartSystemUI, SWITCH_ANIMATION_DELAY);
        });
        binding.unzoomDepthWallpaperContainer.setOnClickListener(v -> binding.unzoomDepthWallpaper.toggle());

        // Hide data disabled icon
        binding.hideDataDisabledIcon.setChecked(RPrefs.getBoolean(HIDE_DATA_DISABLED_ICON, false));
        binding.hideDataDisabledIcon.setOnCheckedChangeListener((buttonView, isChecked) -> {
            RPrefs.putBoolean(HIDE_DATA_DISABLED_ICON, isChecked);
        });
        binding.hideDataDisabledIconContainer.setOnClickListener(v -> binding.hideDataDisabledIcon.toggle());
    }
}