package com.cl.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cl.frame.LoadTypeConfig;
import com.cl.utils.DataType;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    protected Context context;
    private Unbinder bind;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(getLayout(), null);
        bind = ButterKnife.bind(this, root);
        initView();
        initData();
        return root;
    }

    protected abstract void initData();

    protected abstract void initView();

    public void initRecycleData(RecyclerView recyclerView, SmartRefreshLayout pMoreRefresh, DataType dataType) {
        if (recyclerView!=null)recyclerView.setLayoutManager(new LinearLayoutManager(context));
        if (pMoreRefresh != null && dataType != null) {
            pMoreRefresh.setOnLoadMoreListener(refreshLayout -> dataType.getType(LoadTypeConfig.MORE));
            pMoreRefresh.setOnRefreshListener(refreshLayout -> dataType.getType(LoadTypeConfig.REFRESH));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }

    protected abstract int getLayout();
}
