package com.cl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cl.data.VipListBean;
import com.cl.the_projext.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VipChildListAdapter extends RecyclerView.Adapter<VipChildListAdapter.Holder> {
    Context context;
    List<VipListBean.ResultBean.ListBean> data = new ArrayList<>();

    public VipChildListAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<VipListBean.ResultBean.ListBean> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void setReData(List<VipListBean.ResultBean.ListBean> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.vip_list_recycle, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.like.setText(data.get(position).getComment_rate());
        holder.study.setText(data.get(position).getStudentnum());
        holder.title.setText(data.get(position).getLesson_name());
        Glide.with(context).load(data.get(position).getVip_thumb()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.vip_list_image)
        ImageView imageView;
        @BindView(R.id.peopleStudy)
        TextView study;
        @BindView(R.id.vip_like_num)
        TextView like;
        @BindView(R.id.vip_list_title)
        TextView title;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
