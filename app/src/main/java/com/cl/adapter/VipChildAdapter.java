package com.cl.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cl.data.VipBannerBean;
import com.cl.data.VipListBean;
import com.cl.the_projext.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VipChildAdapter extends RecyclerView.Adapter<VipChildAdapter.Holder> {
    Context context;
    List<VipBannerBean.ResultBean.LiveBeanX.LiveBean> data = new ArrayList();

    public VipChildAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<VipBannerBean.ResultBean.LiveBeanX.LiveBean> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.home_main_child, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.textView.setText(data.get(position).getActivityName());
        RequestOptions options = new RequestOptions().circleCrop();
        Glide
                .with(context)
                .load(data.get(position).getCoverImgUrl())
                .apply(options)
                .into(holder.imageView);
        if (data.get(position).getIs_liveing() == 0) {
            holder.living.setVisibility(View.VISIBLE);
            holder.linearLayout.setVisibility(View.GONE);
        } else {
            holder.linearLayout.setVisibility(View.VISIBLE);
            holder.living.setVisibility(View.GONE);
            holder.begin.setText(data.get(position).getStartTime());
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.home_main_child_image)
        ImageView imageView;
        @BindView(R.id.home_main_live_title)
        TextView textView;
        @BindView(R.id.living)
        TextView living;
        @BindView(R.id.live_lin)
        LinearLayout linearLayout;
        @BindView(R.id.live_begin_time)
        TextView begin;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
