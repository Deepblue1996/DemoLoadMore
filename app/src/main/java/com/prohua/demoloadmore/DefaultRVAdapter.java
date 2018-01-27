package com.prohua.demoloadmore;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * 默认万能适配器中间层
 * Created by Deep on 2017/8/17 0017.
 */

public abstract class DefaultRVAdapter<T> extends RecyclerView.Adapter<DefaultViewHolder> {

    /**
     * TYPE_HEADER: 列表头
     * TYPE_NORMAL: 列表项
     * TYPE_FOOTER: 列表脚
     */
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    public static final int TYPE_FOOTER = 2;

    /**
     * 内容数据
     */
    protected List<T> list;
    /**
     * item的布局id
     */
    protected int layoutId;
    /**
     * 上下文
     */
    protected Context context;

    /**
     * 头部视图
     */
    protected int headerLayoutId;
    /**
     * 脚部视图
     */
    protected int footerLayoutId;

    /**
     * 初始化构造器
     *
     * @param context  上下文
     * @param list     数据
     * @param layoutId item布局
     */
    protected DefaultRVAdapter(Context context, List<T> list, @LayoutRes int layoutId,
                               @LayoutRes int headerLayoutId, @LayoutRes int footerLayoutId) {
        this.list = list;
        this.layoutId = layoutId;
        this.context = context;

        if (headerLayoutId != 0) {
            this.headerLayoutId = headerLayoutId;
            notifyItemInserted(0);
        }

        if (footerLayoutId != 0) {
            this.footerLayoutId = footerLayoutId;
            if (headerLayoutId != 0) {
                notifyItemInserted(list.size());
            } else {
                notifyItemInserted(list.size() + 1);
            }
        }
    }

    /**
     * Item创建视图 (封装)
     *
     * @param parent   向内传入的视图
     * @param viewType 类型
     * @return 当前view
     */
    @Override
    public DefaultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return new DefaultViewHolder(LayoutInflater.from(context).inflate(headerLayoutId, parent, false));
        } else if (viewType == TYPE_FOOTER) {
            return new DefaultViewHolder(LayoutInflater.from(context).inflate(footerLayoutId, parent, false));
        } else {
            return new DefaultViewHolder(LayoutInflater.from(context).inflate(layoutId, parent, false));
        }
    }

    /**
     * 将数据绑定到Item的视图
     *
     * @param holder   抽象向内传入的视图
     * @param position 当前的position
     */
    protected abstract void onBindItemViewHolder(DefaultViewHolder holder, int position);

    /**
     * 将数据绑定到header的视图
     *
     * @param holder   抽象向内传入的视图
     * @param position 当前的position
     */
    protected abstract void onBindHeaderViewHolder(DefaultViewHolder holder, int position);

    /**
     * 将数据绑定到Footer的视图
     *
     * @param holder   抽象向内传入的视图
     * @param position 当前的position
     */
    protected abstract void onBindFooterViewHolder(DefaultViewHolder holder, int position);

    @Override
    public void onBindViewHolder(DefaultViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) {
            try {
                onBindHeaderViewHolder(holder, position);
            } catch (NullPointerException e) {
                Log.e(DefaultRVAdapter.class.getName(), "You did not set this method --> onBindHeaderViewHolder!");
            }
        } else if (getItemViewType(position) == TYPE_FOOTER) {
            try {
                onBindFooterViewHolder(holder, position);
            } catch (NullPointerException e) {
                Log.e(DefaultRVAdapter.class.getName(), "You did not set this method --> onBindFooterViewHolder!");
            }
        } else {
            try {
                onBindItemViewHolder(holder, position);
            } catch (NullPointerException e) {
                Log.e(DefaultRVAdapter.class.getName(), "You did not set this method --> onBindItemViewHolder!");
            }
        }
    }

    /**
     * 获取item对应类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (headerLayoutId != 0 && position == 0) {
            return TYPE_HEADER;
        } else {
            if (footerLayoutId != 0 && headerLayoutId == 0 && position == list.size()) {
                return TYPE_FOOTER;
            } else if (footerLayoutId != 0 && headerLayoutId != 0 && position == list.size() + 1) {
                return TYPE_FOOTER;
            } else {
                return TYPE_NORMAL;
            }
        }
    }

    /**
     * 返回总数量
     *
     * @return 数量
     */
    @Override
    public int getItemCount() {
        if (headerLayoutId == 0 && footerLayoutId == 0) {
            return list.size();
        } else if (headerLayoutId != 0 && footerLayoutId != 0) {
            return list.size() + 2;
        } else {
            return list.size() + 1;
        }
    }
}