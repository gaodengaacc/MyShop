package com.gordon.shop.pagemain.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.gordon.shop.BaseFragment;
import com.gordon.shop.R;
import com.gordon.shop.view.refreshlayout.PtrFrameLayout;
import com.gordon.shop.view.refreshlayout.PtrHandler;
import com.gordon.shop.view.refreshlayout.PtrUIHandler;
import com.gordon.shop.view.refreshlayout.header.StoreHouseHeader;
import com.gordon.shop.view.refreshlayout.indicator.PtrIndicator;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Gordon
 * @since 2016/3/22
 * do()
 */
public class PageMainFragment extends BaseFragment {

    final String[] mStringList = {"Alibaba", "TMALL 11-11", "GORDON"};
    private String mTitlePre;
    @Bind(R.id.store_house_ptr_frame)
    PtrFrameLayout frame;
    @Bind(R.id.page_main_content)
    LinearLayout content;
    @Bind(R.id.titleTxt)
    TextView titleText;
    @Bind(R.id.main_listView)
    ListView main_listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page_main_layout, null);
        ButterKnife.bind(this, view);
        final StoreHouseHeader header = new StoreHouseHeader(getContext());
        header.initWithString(mStringList[0]);
        setTitleText(mTitlePre + mStringList[0]);
        frame.addPtrUIHandler(new PtrUIHandler() {

            private int mLoadTime = 0;

            @Override
            public void onUIReset(PtrFrameLayout frame) {
                mLoadTime++;
                String string = mStringList[mLoadTime % mStringList.length];
                header.initWithString(string);
            }

            @Override
            public void onUIRefreshPrepare(PtrFrameLayout frame) {
                String string = mStringList[mLoadTime % mStringList.length];
                setTitleText(mTitlePre + string);
            }

            @Override
            public void onUIRefreshBegin(PtrFrameLayout frame) {

            }

            @Override
            public void onUIRefreshComplete(PtrFrameLayout frame) {

            }

            @Override
            public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {

            }
        });

        frame.setDurationToCloseHeader(3000);
        frame.setHeaderView(header);
        frame.addPtrUIHandler(header);
        frame.postDelayed(new Runnable() {
            @Override
            public void run() {
                frame.autoRefresh(false);
            }
        }, 100);

        frame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return true;
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        frame.refreshComplete();
                    }
                }, 2000);
            }
        });
        return view;
    }

    private void setTitleText(String text) {
        if (titleText != null) {
            titleText.setText(text);
        }
    }

    public static PageMainFragment newInstance() {
        PageMainFragment fragment = new PageMainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public PageMainFragment() {
    }


}
