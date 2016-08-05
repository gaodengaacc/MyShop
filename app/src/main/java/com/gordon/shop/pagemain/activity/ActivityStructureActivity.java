package com.gordon.shop.pagemain.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.gordon.shop.R;

/**
 * @author Gordon
 * @since 2016/8/3
 * do()
 */
public class ActivityStructureActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_PROGRESS);
//        getWindow().getDecorView().setBackgroundColor(Color.BLUE);
        setContentView(R.layout.structure_activity);
        getWindow().getDecorView().setBackgroundColor(Color.BLUE);
        View root_view = findViewById(R.id.structure_button);
        forView(root_view);

    }

    private void forView(View root_view) {
        while (root_view != null) {
            Log.i("ActivityStructureActivity", root_view.getClass().toString());
            if (root_view instanceof ViewGroup) {
                int child_count = ((ViewGroup) root_view).getChildCount();
                for (int i = 0; i < child_count; i++) {
                    View v = ((ViewGroup) root_view).getChildAt(i);
                    Log.i("ActivityStructureActivity", "\t child:" + v.getClass().toString());
                }
                Log.i("ActivityStructureActivity", "\n");
            }
            root_view = (View) root_view.getParent();
        }
    }
}
