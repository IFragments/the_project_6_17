package com.cl.adapter;

import android.content.Context;
import android.util.Log;
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
import com.cl.data.HomeMainBean;
import com.cl.the_projext.R;
import com.yiyatech.utils.glide_transformation.CornerTransform;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeMainChildAdapter extends RecyclerView.Adapter<HomeMainChildAdapter.Holder> {
    Context context;
    List<HomeMainBean.Live> listData = new ArrayList<>();

    public HomeMainChildAdapter(Context context) {
        this.context = context;
    }

    public void setListData(List<HomeMainBean.Live> listData) {
        this.listData.addAll(listData);
        notifyDataSetChanged();
    }

    public void refresh(List<HomeMainBean.Live> listData) {
        this.listData.clear();
        this.listData.addAll(listData);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomeMainChildAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.home_main_child, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeMainChildAdapter.Holder holder, int position) {
        holder.textView.setText(listData.get(position).getActivityName());
        RequestOptions options = new RequestOptions().circleCrop();
        Glide
                .with(context)
                .load(listData.get(position).getCoverImgUrl())
                .apply(options)
                .into(holder.imageView);
        if (listData.get(position).is_liveing.equals("0")) {
            holder.living.setVisibility(View.VISIBLE);
            holder.linearLayout.setVisibility(View.GONE);
        } else {
            holder.linearLayout.setVisibility(View.VISIBLE);
            holder.living.setVisibility(View.GONE);
            holder.begin.setText(listData.get(position).getStartTime());
        }
    }

    private static final String TAG = "HomeMainChildAdapter";

    @Override
    public int getItemCount() {
        return listData.size();
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
