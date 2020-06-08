package com.cl.base;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cl.frame.LoadTypeConfig;
import com.cl.interfaces.DataListener;
import com.cl.utils.Application1907;
import com.cl.utils.DataType;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    public Application1907 mApplication;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = (Application1907) getApplication();
    }

    /**
     * recyclerview在整个项目中使用比较频繁，将公共代码进行抽取
     *
     * @param pRecyclerView  要操作的recyclerview
     * @param pRefreshLayout 如果有刷新和加载更多的问题，所使用的smartRefreshLayout
     * @param pDataListener  刷新和加载的监听，如果实际使用中部涉及到刷新和加载更多，直接传null
     */
    public void initRecyclerView(RecyclerView pRecyclerView, SmartRefreshLayout pRefreshLayout, DataListener pDataListener) {
        if (pRecyclerView != null) pRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (pRefreshLayout != null && pDataListener != null) {
            pRefreshLayout.setOnRefreshListener(refreshLayout -> pDataListener.dataType(LoadTypeConfig.REFRESH));
            pRefreshLayout.setOnLoadMoreListener(refreshLayout -> pDataListener.dataType(LoadTypeConfig.MORE));
        }
    }

    public void showLog(Object content) {
        Log.e("this error", content.toString());
    }

    public void showToast(Object content) {
        if(content !=null){

        Toast.makeText(getApplicationContext(), content.toString(), Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(mApplication, "空", Toast.LENGTH_SHORT).show();
        }
    }
}
