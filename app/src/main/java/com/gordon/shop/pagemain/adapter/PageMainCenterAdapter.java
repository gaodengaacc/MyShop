package com.gordon.shop.pagemain.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gordon.shop.R;
import com.gordon.shop.pagemain.activity.NewsBannerView;
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

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (i){
            case 0:
                NewsBannerView newsBannerView = new NewsBannerView(context);
                viewHolder =new TopViewHolder(newsBannerView);
                break;
          default:
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_page_main_top_grid, null);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                view.setLayoutParams(lp);
                viewHolder =new PersonViewHolder(view);
                break;
//            case 2:
//                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_page_main_top_grid, null);
//                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                view.setLayoutParams(lp);
//                viewHolder =new PersonViewHolder(view);
//                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        switch (i){
            case 0:
                break;
           default:
               PersonViewHolder holder = (PersonViewHolder) viewHolder;
               ImageUtils.showImage(context, holder.rootView, (Integer) list.get(i));
               break;
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
            ButterKnife.bind(this,itemView);
        }

    }
    class TopViewHolder extends RecyclerView.ViewHolder {

        public TopViewHolder(View view) {
            super(view);
        }

    }
}
