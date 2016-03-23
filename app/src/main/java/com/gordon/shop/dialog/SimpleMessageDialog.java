package com.gordon.shop.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.gordon.shop.R;


/**
 * @author Gordon
 * @since 2016/3/18
 * do()
 */
public class SimpleMessageDialog extends Dialog implements OnClickListener {

    private Context context;
    private String info = "";
    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    private OnItemClickListener onItemClickListener;

    private TextView mTvInfo;

    private Button mBtnYes;
    private int mBtnYesVisibility = View.VISIBLE;
    private String yes = "确定";
    private Button mBtnCancel;
    private int mBtnCancelVisibility = View.VISIBLE;
    private String cancel = "取消";

    public SimpleMessageDialog(Context context) {
        super(context, R.style.dialog);
        this.context = context;
    }

    public SimpleMessageDialog(Context context, String info) {
        super(context, R.style.dialog);
        this.context = context;
        this.info = info;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_simple_message);

        mTvInfo = (TextView) findViewById(R.id.simple_message_tv_info);
        mTvInfo.setText(info);
        mBtnYes = (Button) findViewById(R.id.simple_message_btn_yes);
        mBtnYes.setVisibility(mBtnYesVisibility);
        mBtnYes.setText(yes);
        mBtnCancel = (Button) findViewById(R.id.simple_message_btn_cancel);
        mBtnCancel.setVisibility(mBtnCancelVisibility);
        mBtnCancel.setText(cancel);
        mBtnYes.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
    }

    @Override
    public void show() {
        if (!isShowing())
            super.show();
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setYesBtnText(String yes) {
        this.yes = yes;
    }

    public void setCancelBtnText(String cancel) {
        this.cancel = cancel;
    }

    public void setBtnYesVisibility(int visibility) {
        mBtnYesVisibility = visibility;
    }

    public void setBtnCancelVisibility(int visibility) {
        mBtnCancelVisibility = visibility;
    }

    public interface OnItemClickListener {
        public void OnYesClick(View view);

        public void OnCancelClick(View view);
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public SimpleMessageDialog setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    @Override
    public void onClick(View v) {
        if (null != onItemClickListener) {
            if (R.id.simple_message_btn_yes == v.getId()) {
                onItemClickListener.OnYesClick(v);
            } else {
                onItemClickListener.OnCancelClick(v);
            }
        }
        dismiss();
    }
}
