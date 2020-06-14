package com.cl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cl.data.SearchItemEntity;
import com.cl.the_projext.R;
import com.yiyatech.utils.newAdd.GlideUtil;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurriculumChildAdapter extends RecyclerView.Adapter<CurriculumChildAdapter.ViewHodler> {

    private List<SearchItemEntity> mList = new ArrayList<>();
    private Context mContext;

    public CurriculumChildAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setList(List<SearchItemEntity> mList) {
        this.mList.addAll(mList);
        notifyDataSetChanged();
    }

    public void setReList(List<SearchItemEntity> mList) {
        this.mList.clear();
        this.mList.addAll(mList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHodler(LayoutInflater.from(mContext).inflate(R.layout.home_curriculum_page, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodler holder, int position) {
        if (mList == null || mList.size() == 0) return;
        SearchItemEntity entity = mList.get(position);
        holder.tvTitle.setText(entity.getLesson_name());
        GlideUtil.loadImage(holder.ivPhoto, entity.getThumb());
        holder.tvLearnNum.setText(entity.getStudentnum() + "人学习");
        holder.tvLikeNum.setText("好评率" + entity.getRate());
        holder.tvPrice.setText("￥" + entity.getPrice());
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public class ViewHodler extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_photo)
        ImageView ivPhoto;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_learn_num)
        TextView tvLearnNum;
        @BindView(R.id.tv_like_num)
        TextView tvLikeNum;
        @BindView(R.id.tv_price)
        TextView tvPrice;

        public ViewHodler(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
