package com.gordon.shop;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.RadioGroup;

import com.gordon.shop.pagemain.activity.PageMainFragment;
import com.gordon.shop.usercenter.activity.UserCenterFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Gordon
 * @since 2016/3/18
 * do()
 */
public class MainActivity extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener{
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTrasaction;
    private String mCurrentFragmentTag = PageMainFragment.class.getSimpleName();
    private SampleFragment fragment;
    private UserCenterFragment userCenterFragment;
    private PageMainFragment pageMainFragment;
    @Bind(R.id.main_tab_rg)
    RadioGroup bottom_tabs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragments();
        initView();
    }

    private void initView() {
        bottom_tabs.setOnCheckedChangeListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Necessary to restore the BottomBar's state, otherwise we would
        // lose the current tab on orientation change.
    }

    /**
     * 初始化Fragment
     */
    private void initFragments() {
        mFragmentManager = getSupportFragmentManager();
        pageMainFragment = PageMainFragment.newInstance();
        addFragment(pageMainFragment, PageMainFragment.class.getSimpleName());
        fragment = SampleFragment.newInstance();
        addFragment(fragment, SampleFragment.class.getSimpleName());
        fragment = SampleFragment.newInstance();
        addFragment(fragment, SampleFragment.class.getSimpleName());
        fragment = SampleFragment.newInstance();
        addFragment(fragment, SampleFragment.class.getSimpleName());
        userCenterFragment = UserCenterFragment.newInstance();
        addFragment(userCenterFragment, UserCenterFragment.class.getSimpleName());
        changeTabContent(pageMainFragment,PageMainFragment.class.getSimpleName());
    }
    /**
     * 切换Tab
     *
     * @param fragment Fragment的实例
     * @param tag      要绑定的Tag
     */
    private void changeTabContent(Fragment fragment, String tag) {

        mFragmentTrasaction = mFragmentManager.beginTransaction();

        if (getCurrentFragment() != null) {
            getCurrentFragment().onPause();
            mFragmentTrasaction.hide(getCurrentFragment());
        }

        fragment.onResume();
        mFragmentTrasaction.show(fragment);
        mFragmentTrasaction.commitAllowingStateLoss();
        mCurrentFragmentTag = tag;
    }

    /**
     * 向FragmentManager中添加Fragment
     *
     * @param fragment 要添加的Fragment
     * @param tag      给Fragment绑定的Tag
     */
    private void addFragment(Fragment fragment, String tag) {
        if (fragment.isAdded()) {
            return;
        }
        mFragmentTrasaction = mFragmentManager.beginTransaction();
        mFragmentTrasaction.add(R.id.main_container, fragment, tag);
        mFragmentTrasaction.hide(fragment);
        mFragmentTrasaction.commit();
    }

    /**
     * 获取当前正在显示的Fragment
     *
     * @return Fragment 当前正在显示的Fragment
     */
    private Fragment getCurrentFragment() {
        return mFragmentManager.findFragmentByTag(mCurrentFragmentTag);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
             switch(checkedId){
                 case R.id.main_tab_1:
                     changeTabContent(pageMainFragment, PageMainFragment.class.getSimpleName());
                     break;
                 case R.id.main_tab_2:
                     changeTabContent(fragment, SampleFragment.class.getSimpleName());
                     break;
                 case R.id.main_tab_3:
                     changeTabContent(fragment, SampleFragment.class.getSimpleName());
                     break;
                 case R.id.main_tab_4:
                     changeTabContent(fragment, SampleFragment.class.getSimpleName());
                     break;
                 case R.id.main_tab_5:
                     changeTabContent(userCenterFragment, UserCenterFragment.class.getSimpleName());
                     break;
                 default:
                     break;
             }
    }

    @Override
    public void onClick(View v) {

    }
}
