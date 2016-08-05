package com.gordon.shop.news.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.projection.MediaProjectionManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gordon.shop.BaseFragment;
import com.gordon.shop.R;
import com.gordon.shop.ShopApplication;
import com.gordon.shop.service.CaptureService;
import com.gordon.shop.service.RecordService;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Gordon
 * @since 2016/7/12
 * do()
 */
public class NewsFragment extends BaseFragment {
    private View contentView;
    @Bind(R.id.btn_screen_short)
    Button mScreenShortBtn;
    @Bind(R.id.btn_screen_record)
    Button mScreenRecordBtn;
    private MediaProjectionManager mMpMngr;
    private static final int REQUEST_MEDIA_PROJECTION = 1;
    private Intent mResultIntent = null;
    private int mResultCode = 0;
    public static final String TAG = "MainAc";
    boolean isCapture;
    private Activity activity;

    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public NewsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
        activity = getActivity();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        activity.stopService(new Intent(activity.getApplicationContext(),  CaptureService.class ));
        activity.stopService(new Intent(activity.getApplicationContext(),  RecordService.class ));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.activity_recorder_layout, container, false);
        ButterKnife.bind(this, contentView);
        mMpMngr = (MediaProjectionManager) getActivity().getApplicationContext().getSystemService(Context.MEDIA_PROJECTION_SERVICE);
        if(ShopApplication.getInstance()!=null){
            mResultIntent = ShopApplication.getInstance().getResultIntent();
            mResultCode =  ShopApplication.getInstance().getResultCode();
        }
        return contentView;
    }

    @OnClick({R.id.btn_screen_record,R.id.btn_screen_short})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_screen_record:
                isCapture = false;
                startIntent();
                activity.stopService(new Intent(activity.getApplicationContext(),  CaptureService.class ));
                break;
            case R.id.btn_screen_short:
                isCapture=true;
                startIntent();
                activity.stopService(new Intent(activity.getApplicationContext(),  RecordService.class ));
                break;
        }
    }

    private void startIntent() {
        if (mResultIntent != null && mResultCode != 0) {
            activity.startService(new Intent(activity.getApplicationContext(), isCapture?CaptureService.class:RecordService.class));
        } else {
            startActivityForResult(mMpMngr.createScreenCaptureIntent(), REQUEST_MEDIA_PROJECTION);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_MEDIA_PROJECTION) {
            if (resultCode == activity.RESULT_OK) {
                Log.e(TAG,"get capture permission success!");
                mResultCode = resultCode;
                mResultIntent = data;
                ShopApplication.getInstance().setResultCode(resultCode);
                ShopApplication.getInstance().setResultIntent(data);
                ShopApplication.getInstance().setMpmngr(mMpMngr);
                activity.startService(new Intent(activity.getApplicationContext(),isCapture?CaptureService.class:RecordService.class));
            }
        }
    }
}
