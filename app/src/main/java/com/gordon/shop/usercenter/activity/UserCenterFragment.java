package com.gordon.shop.usercenter.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gordon.shop.BaseFragment;
import com.gordon.shop.R;
import com.gordon.shop.view.CircleImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Gordon
 * @since 2016/3/21
 * do()
 */
public class UserCenterFragment extends BaseFragment{

//    @Bind(R.id.user_icon)
//    CircleImageView user_icon;
//    @Bind(R.id.user_nickname)
//    TextView username;
//    @Bind(R.id.user_des)
//    TextView user_des;
//    @Bind(R.id.user_item_1)
//    LinearLayout item_layout_1;
//    @Bind(R.id.user_item_2)
//    LinearLayout item_layout_2;
//    @Bind(R.id.user_item_3)
//    LinearLayout item_layout_3;

    private View contentView;

    public static UserCenterFragment newInstance() {
        UserCenterFragment fragment = new UserCenterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public UserCenterFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.user_center_layout, container, false);
        ButterKnife.bind(this, contentView);
//        addContentViewChild(contentView);
        return contentView;
    }
}
