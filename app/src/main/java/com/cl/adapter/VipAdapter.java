package com.cl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cl.data.VipBannerBean;
import com.cl.data.VipListBean;
import com.cl.the_projext.R;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VipAdapter extends RecyclerView.Adapter {
    Context context;
    List<VipBannerBean.ResultBean.LunbotuBean> bannerData = new ArrayList<>();
    List<VipBannerBean.ResultBean.LiveBeanX.LiveBean> liveData = new ArrayList<>();
    List<VipListBean.ResultBean.ListBean> listData = new ArrayList<>();
    public int isRefresh;

    public void setIsRefresh(int isRefresh) {
        this.isRefresh = isRefresh;
    }

    public VipAdapter(Context context) {
        this.context = context;
    }

    public void setBannerData(List<VipBannerBean.ResultBean.LunbotuBean> bannerData) {
        this.bannerData.clear();
        this.bannerData.addAll(bannerData);
        notifyDataSetChanged();
    }

    public void setLiveData(List<VipBannerBean.ResultBean.LiveBeanX.LiveBean> liveData) {
        this.liveData.clear();
        this.liveData.addAll(liveData);
        notifyDataSetChanged();
    }

    public void setListData(List<VipListBean.ResultBean.ListBean> listData) {
        this.listData.addAll(listData);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case BANNER:
                return new BannerHolder(LayoutInflater.from(context).inflate(R.layout.vip_banner, parent, false));
            case LIVE:
                return new LiveHolder(LayoutInflater.from(context).inflate(R.layout.home_main_live, parent, false));
            case LIST:
                return new ListHolder(LayoutInflater.from(context).inflate(R.layout.vip_list, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case BANNER:
                BannerHolder bannerHolder = (BannerHolder) holder;
                bannerHolder.banner.setImages(bannerData).setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        VipBannerBean.ResultBean.LunbotuBean vipBannerBean = (VipBannerBean.ResultBean.LunbotuBean) path;
                        Glide.with(context).load(vipBannerBean.getImg()).into(imageView);
                    }
                }).start();
                break;
            case LIVE:
                LiveHolder liveHolder = (LiveHolder) holder;
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
                liveHolder.recyclerView.setLayoutManager(linearLayoutManager);
                VipChildAdapter vipChildAdapter = new VipChildAdapter(context);
                liveHolder.recyclerView.setAdapter(vipChildAdapter);
                liveHolder.textView.setText("VIP直播");
                vipChildAdapter.setData(liveData);
                vipChildAdapter.notifyDataSetChanged();
                break;
            case LIST:
                ListHolder listHolder = (ListHolder) holder;
                listHolder.recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
                VipChildListAdapter vipChildListAdapter = new VipChildListAdapter(context);
                listHolder.recyclerView.setAdapter(vipChildListAdapter);
                if (isRefresh == 1){
                    vipChildListAdapter.setReData(listData);
                }else {
                    vipChildListAdapter.setData(listData);
                }
                break;
        }
    }

    final int BANNER = 0;
    final int LIVE = 1;
    final int LIST = 2;

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && bannerData.size() != 0) {
            return BANNER;
        }
        if ((position == 1 && liveData.size() != 0) || (position == 0 && bannerData.size() == 0)) {
            return LIVE;
        }
        return LIST;
    }

    @Override
    public int getItemCount() {
        boolean b = bannerData.size() != 0;
        boolean l = liveData.size() != 0;
        int count = listData.size();
        if (b && l) {
            return count += 2;
        }
        if ((b && !l) || (!b && l)) {
            return count + 1;
        }
        return count;
    }

    class BannerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.banner_vip)
        Banner banner;

        public BannerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class LiveHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.home_live_recycle)
        RecyclerView recyclerView;
        @BindView(R.id.home_main_text_all)
        TextView textView;

        public LiveHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ListHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.recycle_vip_all)
        RecyclerView recyclerView;

        public ListHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
