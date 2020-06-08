package com.cl.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.cl.data.HomeListBean;
import com.cl.data.HomeMainBean;
import com.cl.the_projext.R;
import com.yiyatech.utils.glide_transformation.CornerTransform;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeMainAdapter extends RecyclerView.Adapter {
    final int BANNER = 0;
    final int SUBJECT = 1;
    final int MORE_LIVE = 2;
    final int LIST_ONE = 3;
    final int LIST_TWO = 4;
    Context context;
    List<HomeMainBean.Carousel> bannerData = new ArrayList<>();
    List<HomeMainBean.Live> liveData = new ArrayList<>();
    List<HomeListBean.ResultBean> listData = new ArrayList<>();
    private HomeMainChildAdapter homeMainChildAdapter;

    public HomeMainAdapter(Context context) {
        this.context = context;
    }

    public void setBannerData(List<HomeMainBean.Carousel> bannerData) {
        this.bannerData.addAll(bannerData);
        notifyDataSetChanged();
    }

    public void setLiveData(List<HomeMainBean.Live> liveData) {
        this.liveData.addAll(liveData);
        notifyDataSetChanged();
    }

    public void setListData(List<HomeListBean.ResultBean> listData) {
        this.listData.addAll(listData);
        notifyDataSetChanged();
    }

    public void setReBannerData(List<HomeMainBean.Carousel> bannerData) {
        this.bannerData.clear();
        this.bannerData.addAll(bannerData);
        notifyDataSetChanged();
    }

    public void setReLiveData(List<HomeMainBean.Live> liveData) {
        this.liveData.clear();
        if (liveData != null) {
            this.liveData.addAll(liveData);
        }
//        homeMainChildAdapter.refresh(this.liveData);
        notifyDataSetChanged();
    }

    public void setReListData(List<HomeListBean.ResultBean> listData) {
        this.listData.clear();
        this.listData.addAll(listData);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && bannerData.size() > 0) {
            return BANNER;
        }
        if (position == 1 || (bannerData.size() == 0 && position == 0)) {
            return SUBJECT;
        }
        if (position == 2 && liveData.size() > 0 || (bannerData.size() == 0 && liveData.size() > 0 && position == 1)) {
            return MORE_LIVE;
        }
//        if ()
        return LIST_ONE;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case BANNER:
                return new BannerHolder(LayoutInflater.from(context).inflate(R.layout.home_main_banner, parent, false));
            case SUBJECT:
                return new SubjectHolder(LayoutInflater.from(context).inflate(R.layout.home_main_subject, parent, false));
            case MORE_LIVE:
                return new LiveHolder(LayoutInflater.from(context).inflate(R.layout.home_main_live, parent, false));
//                return null;
            case LIST_ONE:
                return new ListOneHolder(LayoutInflater.from(context).inflate(R.layout.home_main_list_one, parent, false));
//            case LIST_TWO:
//                return null;
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
                        HomeMainBean.Carousel carouselBean = (HomeMainBean.Carousel) path;
                        Glide.with(context).load(carouselBean.getImg()).into(imageView);
                    }
                }).start();
                break;
            case MORE_LIVE:
                LiveHolder liveHolder = (LiveHolder) holder;
                homeMainChildAdapter = new HomeMainChildAdapter(context);
                LinearLayoutManager ms = new LinearLayoutManager(context);
                ms.setOrientation(LinearLayoutManager.HORIZONTAL);
                liveHolder.liveRecyclerView.setLayoutManager(ms);
                liveHolder.liveRecyclerView.setAdapter(homeMainChildAdapter);
                homeMainChildAdapter.setListData(liveData);
                break;
            case LIST_ONE:
                ListOneHolder listOneHolder = (ListOneHolder) holder;
//                HomeListBean.ResultBean resultBean = (HomeListBean.ResultBean) listData;
                if (bannerData.size() != 0 && liveData.size() != 0) {
                    position -= 2;
                }
                if ((bannerData.size() == 0 && liveData.size() != 0) || (bannerData.size() != 0 && liveData.size() == 0)) {
                    position -= 1;
                }

                HomeListBean.ResultBean resultBean = listData.get(position);
                listOneHolder.time.setText(resultBean.getDate());
                ImageView imageView = listOneHolder.imageView;
                RoundedCorners roundedCorners = new RoundedCorners(20);
                RequestOptions override = RequestOptions.bitmapTransform(roundedCorners).override(R.dimen.dp_130, R.dimen.dp_80);
                Glide
                        .with(context)
                        .load(resultBean.getPic())
                        .apply(override)
                        .into(imageView);
                String s = resultBean.getReply_num();
                if (!TextUtils.isEmpty(s)) {
                    listOneHolder.people.setText(s + "人跟帖");
                } else {
                    listOneHolder.people.setText(0 + "人跟帖");
                }
                String view_num = resultBean.getView_num();
                if (!TextUtils.isEmpty(view_num)) listOneHolder.brows.setText(view_num + "人浏览");
                else listOneHolder.brows.setText(0 + "人浏览");
                listOneHolder.title.setText(resultBean.getTitle());
                break;
//            case LIST_TWO:
//                ListTwoHolder listTwoHolder = (ListTwoHolder) holder;
//                break;
        }
    }

    @Override
    public int getItemCount() {

        if (bannerData.size() != 0 && liveData.size() != 0) {
            return listData.size() + 3;
        }
        if ((bannerData.size() == 0 && liveData.size() != 0) || (bannerData.size() != 0 && liveData.size() == 0)) {
            return listData.size() + 2;
        }
        return listData.size();
    }

    class BannerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.home_main_banner)
        Banner banner;

        public BannerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class SubjectHolder extends RecyclerView.ViewHolder {

        public SubjectHolder(@NonNull View itemView) {
            super(itemView);
//            ButterKnife.bind(this, itemView);
        }
    }

    class LiveHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.home_live_recycle)
        RecyclerView liveRecyclerView;
        @BindView(R.id.home_main_text_all)
        TextView textView;

        public LiveHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
//            liveRecyclerView = itemView.findViewById(R.id.home_live_recycle);
//            textView = itemView.findViewById(R.id.home_main_GoliveAll);

        }
    }

    class ListOneHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.home_main_title)
        TextView title;
        @BindView(R.id.homeList_one_image)
        ImageView imageView;
        @BindView(R.id.home_main_Browse)
        TextView brows;
        @BindView(R.id.home_main_people)
        TextView people;
        @BindView(R.id.home_main_time)
        TextView time;

        public ListOneHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
//            title = itemView.findViewById(R.id.home_main_title);
//            imageView = itemView.findViewById(R.id.homeList_one_image);
//            brows = itemView.findViewById(R.id.home_main_Browse);
//            people = itemView.findViewById(R.id.home_main_people);
//            time = itemView.findViewById(R.id.home_main_time);
        }
    }

    class ListTwoHolder extends RecyclerView.ViewHolder {
        public ListTwoHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
