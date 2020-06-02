package com.cl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cl.data.InfoBean;
import com.cl.the_projext.R;

import java.util.ArrayList;
import java.util.List;

//import butterknife.BindView;
//import butterknife.ButterKnife;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.Holder> {
    List<InfoBean.DatasBean> data = new ArrayList<>();
    Context context;

    public void clears() {
        this.data.clear();
        notifyDataSetChanged();
    }

    public HomeAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<InfoBean.DatasBean> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void refresh() {
        this.data.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.home_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        RequestOptions requestOptions = new RequestOptions().circleCrop();
        Glide.with(context).load(data.get(position).getThumbnail()).apply(requestOptions).into(holder.imageView);
        holder.title.setText(data.get(position).getTitle());
        holder.desc.setText(data.get(position).getAuthor());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        //        @BindView(R.id.home_image)
        ImageView imageView;
        //        @BindView(R.id.item_title)
        TextView title;
        //        @BindView(R.id.item_desc)
        TextView desc;
        //        @BindView(R.id.sure)
        Button button;

        public Holder(@NonNull View itemView) {
            super(itemView);
//            ButterKnife.bind(this, itemView);
            imageView = itemView.findViewById(R.id.home_image);
            title = itemView.findViewById(R.id.item_title);
            desc = itemView.findViewById(R.id.item_desc);
            button = itemView.findViewById(R.id.sure);
        }
    }
}
