package com.gordon.shop;

/**
 * @author Gordon
 * @since 2016/3/17
 * do()
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Iiro Krankka (http://github.com/roughike)
 */
public class SampleFragment extends Fragment {
    private static final String ARG_TEXT = "ARG_TEXT";

    public SampleFragment() {
    }

    public static SampleFragment newInstance() {
        SampleFragment fragment = new SampleFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView textView = new TextView(container.getContext());
        textView.setText(getArguments().getString(ARG_TEXT));

        return textView;
    }
}
