package com.gordon.shop;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gordon.shop.dialog.ProgressBarDialog;
import com.gordon.shop.utils.ToastUtil;

/**
 * @author Gordon
 * @since 2016/3/18
 * do()
 */
public class BaseActivity extends FragmentActivity {

    protected ProgressBarDialog progressBar;
    // 标题
    protected TextView titleTxt;

    protected LinearLayout contentView;

    public boolean isinit = true;

    protected LinearLayout rightView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.base_layout);
        initViews();
    }


    protected void showActionSheet() {
    }


    public void toast(String content, int iconType) {
        if (!TextUtils.isEmpty(content)) {
            ToastUtil.showTips(this, iconType, content);
        }
    }


    public void openActivity(Class clazz) {
        openActivity(null, clazz);
    }

    public void openActivity(Bundle pBundle, Class clazz) {
        Intent intent = new Intent(this, clazz);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }

    protected void initViews() {
        progressBar = new ProgressBarDialog(this);
        progressBar.setCanceledOnTouchOutside(false);
        if (!isinit)
            return;
        titleTxt = (TextView) findViewById(R.id.titleTxt);
        contentView = (LinearLayout) findViewById(R.id.contentView);
        rightView = (LinearLayout) findViewById(R.id.rightView);

        // 设置返回功能
        Button backBtn = (Button) findViewById(R.id.leftBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                back();
            }
        });
    }

    public void show() {
        if (progressBar != null && !progressBar.isShowing()) {
            progressBar.show();
        }
    }

    public void setCancelListener(ProgressBarDialog.LoadingCancelCallBack cancelListener) {
        progressBar.setLoadingCancel(cancelListener);
    }

    public void show(String loadingText) {
        if (progressBar != null && !progressBar.isShowing()) {
            progressBar.show();
            progressBar.setMessage(loadingText);
        }
    }

    public void dismiss() {
        if (progressBar != null && progressBar.isShowing()) {
            progressBar.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        progressBar = null;
    }

    /* (non-Javadoc)
     * @see android.app.Activity#onResume()
     */
    @Override
    protected void onResume() {
        super.onResume();
//            if(null == pageName || pageName.isEmpty()){
//                MobclickAgent.onPageStart(this.getClass().getName());
//            }else{
//                MobclickAgent.onPageStart(pageName);
//            }
//
//            MobclickAgent.onResume(this);
//            if (SystemContext.getInstance().isUnAuth()) {
//                // 清除全局配置
//                SystemContext.getInstance().cleanContext();
//                // 会话失效， 主动注销，token不存在/认证失败，都到登录页
//                Intent intent = new Intent(this, LoginActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putBoolean(SystemConfig.SHAREDPREFERENCES_NAME_GLOBAL_UNAUTHENTICATED, true);
//                intent.putExtras(bundle);
//                startActivity(intent);
//                return;
//            }
    }

    /* (non-Javadoc)
     * @see android.app.Activity#onPause()
     */
    @Override
    protected void onPause() {
        super.onPause();
//            if(null == pageName || pageName.isEmpty()){
//                MobclickAgent.onPageEnd(this.getClass().getName());
//            }else{
//                MobclickAgent.onPageEnd(pageName);
//            }
//            //MobclickAgent.onPageEnd(titleTxt.getText().toString());
//            MobclickAgent.onPause(this);
    }

    /**
     * 返回
     */
    protected void back() {
        BaseActivity.this.finish();
    }

    /**
     * 获取中间布局容器
     */
    protected LinearLayout getContentView() {
        return (LinearLayout) findViewById(R.id.contentView);
    }

    /**
     * 设置标题
     */
    protected void setTitleTxt(String txt) {
        titleTxt.setText(txt);
    }

    /**
     * 设置布局左边是否显示
     *
     * @param visible
     */
    protected void setLeftVisible(Boolean visible) {
        View view = findViewById(R.id.left);
        if (view != null) {
            if (visible) {
                // 显示左边菜单
                view.setVisibility(View.VISIBLE);
            } else {
                // 隐藏左边菜单
                view.setVisibility(View.INVISIBLE);
            }
        }
    }

    /**
     * 设置布局右边是否显示
     *
     * @param visible
     */
    protected void setRightVisible(Boolean visible) {
        View view = findViewById(R.id.right);
        if (view != null) {
            if (visible) {
                // 显示左边菜单
                view.setVisibility(View.VISIBLE);
            } else {
                // 隐藏左边菜单
                view.setVisibility(View.INVISIBLE);
            }
        }
    }

    /**
     * 设置内容UI
     *
     * @param v
     */
    protected void addContentViewChild(View v) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置内容UI
        contentView.addView(v, params);
    }

    /**
     * 添加右边菜单
     *
     * @param v
     */
    protected void addRightView(View v) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rightView.addView(v, params);
    }


}