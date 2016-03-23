package com.gordon.shop;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gordon.shop.dialog.ProgressBarDialog;
import com.gordon.shop.utils.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Gordon
 * @since 2016/3/22
 * do()
 */
public class BaseFragment extends Fragment {
    protected ProgressBarDialog progressBar;
    public boolean isinit = true;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ;
    }


    protected void showActionSheet() {
    }


    public void toast(String content, int iconType) {
        if (!TextUtils.isEmpty(content)) {
            ToastUtil.showTips(getActivity(), iconType, content);
        }
    }


    public void openActivity(Class clazz) {
        openActivity(null, clazz);
    }

    public void openActivity(Bundle pBundle, Class clazz) {
        Intent intent = new Intent(getActivity(), clazz);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
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
    public void onDestroy() {
        super.onDestroy();
        progressBar = null;
    }

    /* (non-Javadoc)
     * @see android.app.Activity#onResume()
     */
    @Override
    public void onResume() {
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
    public void onPause() {
        super.onPause();
//            if(null == pageName || pageName.isEmpty()){
//                MobclickAgent.onPageEnd(this.getClass().getName());
//            }else{
//                MobclickAgent.onPageEnd(pageName);
//            }
//            //MobclickAgent.onPageEnd(titleTxt.getText().toString());
//            MobclickAgent.onPause(this);
    }







}
