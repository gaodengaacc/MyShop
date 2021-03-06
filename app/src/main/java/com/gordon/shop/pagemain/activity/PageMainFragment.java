package com.gordon.shop.pagemain.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.gordon.shop.BaseFragment;
import com.gordon.shop.R;
import com.gordon.shop.pagemain.adapter.PageMainCenterAdapter;
import com.gordon.shop.utils.ImageUtils;
import com.gordon.shop.view.refreshlayout.PtrFrameLayout;
import com.gordon.shop.view.refreshlayout.PtrHandler;
import com.gordon.shop.view.refreshlayout.PtrUIHandler;
import com.gordon.shop.view.refreshlayout.header.StoreHouseHeader;
import com.gordon.shop.view.refreshlayout.indicator.PtrIndicator;

import java.util.ArrayList;
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
    //    @Bind(R.id.page_main_content)
//    LinearLayout content;
    @Bind(R.id.titleTxt)
    TextView titleText;
    //    @Bind(R.id.page_main_gif_imageView)
//    ImageView gif;
    @Bind(R.id.main_listView)
    RecyclerView main_listView;
    private PageMainCenterAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page_main_layout, null);
        ButterKnife.bind(this, view);
//        NewsBannerView newsBannerView = new NewsBannerView(getActivity());
//        content.addView(newsBannerView);
//        ImageUtils.showGifImage(getActivity(), gif, R.mipmap.splash_gif);
//        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 4);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        main_listView.setLayoutManager(new RecyclerView.LayoutManager() {
//            @Override
//            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
//                return null;
//            }
//        });
        main_listView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        List<Integer> list = new ArrayList<>();
        list.add(R.mipmap.bottom_select2);
        list.add(R.mipmap.bottom_select3);
        list.add(R.mipmap.bottom_select4);
        list.add(R.mipmap.bottom_select5);
        list.add(R.mipmap.bottom_select1);
        list.add(R.mipmap.bottom_select2);
        list.add(R.mipmap.bottom_select3);
        list.add(R.mipmap.bottom_select4);
        list.add(R.mipmap.bottom_select5);
        list.add(R.mipmap.bottom_select1);
        adapter = new PageMainCenterAdapter(getActivity(), list);
        main_listView.setAdapter(adapter);
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
                LinearLayoutManager layoutManager = (LinearLayoutManager) main_listView.getLayoutManager();
                if (layoutManager.findFirstVisibleItemPosition() == 0 && layoutManager.findFirstCompletelyVisibleItemPosition()==0)
                    return true;
                else
                    return false;
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
