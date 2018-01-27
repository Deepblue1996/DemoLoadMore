package com.prohua.demoloadmore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.prohua.universal.DefaultAdapter;
import com.prohua.universal.DefaultViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class MainActivity extends AppCompatActivity
        implements DefaultAdapter.OnBindItemView, DefaultAdapter.OnBindHeaderView, DefaultAdapter.OnBindFooterView {

    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(String.valueOf(i + 1));
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        RefreshLayout refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        DefaultAdapter recyclerViewAdapter = new DefaultAdapter(
                getApplicationContext(), list, R.layout.item_list, R.layout.list_header, R.layout.list_footer);
        recyclerViewAdapter.setOnBindItemView(this);
        recyclerViewAdapter.setOnBindHeaderView(this);
        recyclerViewAdapter.setOnBindFooterView(this);

        recyclerView.setAdapter(recyclerViewAdapter);

        refreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        refreshLayout.setEnableLoadmore(true);//是否启用上拉加载功能

        // 监听下拉刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
            }
        });
        // 监听上拉加载
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(2000);
            }
        });

    }

    @Override
    public void onBindItemViewHolder(DefaultViewHolder holder, int position) {
        holder.setText(R.id.text_x, list.get(position));
    }

    @Override
    public void onBindHeaderViewHolder(DefaultViewHolder holder, int position) {
        holder.setImgRes(R.id.imgBg, R.mipmap.ic_launcher_round);
    }

    @Override
    public void onBindFooterViewHolder(DefaultViewHolder holder, int position) {
        holder.setImgRes(R.id.imgBg, R.mipmap.ic_launcher_round);
    }
}
