package com.gordon.shop.pagemain.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gordon.shop.R;
import com.gordon.shop.pagemain.activity.ActivityStructureActivity;
import com.gordon.shop.pagemain.activity.NewsBannerView;
import com.gordon.shop.pagemain.activity.OnClickEventActivity;
import com.gordon.shop.pagemain.activity.WindowManagerActivity;
import com.gordon.shop.utils.ImageUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PageMainCenterAdapter extends RecyclerView.Adapter {
    private Context context;
    private static final String TAG = PageMainCenterAdapter.class.getSimpleName();
    private List list;

    public PageMainCenterAdapter(Context context, List list) {
        this.context = context;
        this.list = list;
    }

    public static enum ITEM_TYPE {
        ITEM_TYPE_TOP, ITEM_TYPE_COMMON
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        if (viewType == ITEM_TYPE.ITEM_TYPE_TOP.ordinal()) {
            LinearLayout newsBannerView = new LinearLayout(context);
            viewHolder = new TopViewHolder(newsBannerView);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_page_main_top_grid, null);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            view.setLayoutParams(lp);
            viewHolder = new PersonViewHolder(view);
        }
//        switch (i){
//            case 0:
//
//                break;
//          default:
//
//                break;
////            case 2:
////                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_page_main_top_grid, null);
////                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
////                view.setLayoutParams(lp);
////                viewHolder =new PersonViewHolder(view);
////                break;
//        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof TopViewHolder) {
            TopViewHolder holder = (TopViewHolder) viewHolder;
            NewsBannerView newsBannerView = new NewsBannerView(context);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            holder.layout.addView(newsBannerView, lp);
            newsBannerView.setOnItemClickListener(new NewsBannerView.onItemClick() {
                @Override
                public void onClick(List list, int position) {
                    context.startActivity(new Intent(context, WindowManagerActivity.class));
                }
            });
        } else if (viewHolder instanceof PersonViewHolder) {
            PersonViewHolder holder = (PersonViewHolder) viewHolder;
            ImageUtils.showImage(context, holder.rootView, (Integer) list.get(i));
        }
//        switch (i){
//            case 0:
//                break;
//           default:
//
//               break;
//        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_TYPE.ITEM_TYPE_TOP.ordinal();
        } else {
            return ITEM_TYPE.ITEM_TYPE_COMMON.ordinal();
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class PersonViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_main_imageView)
        ImageView rootView;

        public PersonViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    class TopViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;

        public TopViewHolder(View view) {
            super(view);
            this.layout = (LinearLayout) view;
            ButterKnife.bind(this, view);
        }

    }
}
