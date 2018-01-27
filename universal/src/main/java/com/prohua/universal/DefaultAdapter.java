package com.prohua.universal;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;

import java.util.List;

/**
 * 继承万能适配器
 * Created by Deep on 2017/8/17 0017.
 */

public class DefaultAdapter extends DefaultRVAdapter {

    /**
     * 列表项视图
     */
    private OnBindItemView onBindItemView;
    /**
     * 列表头视图
     */
    private OnBindHeaderView onBindHeaderView;
    /**
     * 列表脚视图
     */
    private OnBindFooterView onBindFooterView;

    /**
     * 适配器
     *
     * @param context  上下文
     * @param list     列表数据
     * @param layoutId item布局id
     */
    public DefaultAdapter(Context context, List list, @LayoutRes int layoutId,
                             @LayoutRes int headerLayoutId, @LayoutRes int footerLayoutId) {
        super(context, list, layoutId, headerLayoutId, footerLayoutId);
    }

    /**
     * item的视图默认初始化(头部视图,脚部视图,不会处理)
     *
     * @param holder   抽象向内传入的视图
     * @param position 当前的position
     */
    @Override
    protected void onBindItemViewHolder(DefaultViewHolder holder, final int position) {

        int nowPosition = position;
        if (super.headerLayoutId != 0) {
            nowPosition -= 1;
        }

        onBindItemView.onBindItemViewHolder(holder, nowPosition);

        // 如果不存在,则不绑定
        if (onBindItemClick != null) {
            final int finalNowPosition = nowPosition;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBindItemClick.onBindItemClick(v, finalNowPosition);
                }
            });
        }

        // 如果不存在,则不绑定
        if (onBindItemLongClick != null) {
            final int finalNowPosition1 = nowPosition;
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onBindItemLongClick.onBindItemLongClick(v, finalNowPosition1);
                    return true;
                }
            });
        }
    }

    @Override
    protected void onBindHeaderViewHolder(DefaultViewHolder holder, int position) {
        onBindHeaderView.onBindHeaderViewHolder(holder, position);
    }

    @Override
    protected void onBindFooterViewHolder(DefaultViewHolder holder, int position) {
        onBindFooterView.onBindFooterViewHolder(holder, position);
    }

    /**
     * 绑定item视图
     *
     * @param onBindItemView 要绑定的接口
     */
    public void setOnBindItemView(OnBindItemView onBindItemView) {
        this.onBindItemView = onBindItemView;
    }

    /**
     * 列表项视图接口
     */
    public interface OnBindItemView {
        void onBindItemViewHolder(DefaultViewHolder holder, int position);
    }

    /**
     * 绑定列表头视图
     *
     * @param onBindHeaderView 要绑定的接口
     */
    public void setOnBindHeaderView(OnBindHeaderView onBindHeaderView) {
        this.onBindHeaderView = onBindHeaderView;
    }

    /**
     * 头部视图接口
     */
    public interface OnBindHeaderView {
        void onBindHeaderViewHolder(DefaultViewHolder holder, int position);
    }

    /**
     * 绑定列表脚视图
     *
     * @param onBindFooterView 要绑定的接口
     */
    public void setOnBindFooterView(OnBindFooterView onBindFooterView) {
        this.onBindFooterView = onBindFooterView;
    }

    /**
     * 脚部视图接口
     */
    public interface OnBindFooterView {
        void onBindFooterViewHolder(DefaultViewHolder holder, int position);
    }

    /**
     * item点击事件
     */
    private OnBindItemClick onBindItemClick;

    /**
     * 绑定item点击事件
     *
     * @param onBindItemClick 要绑定的接口
     */
    public void setOnBindItemClick(OnBindItemClick onBindItemClick) {
        this.onBindItemClick = onBindItemClick;
    }

    /**
     * item点击事件接口
     */
    public interface OnBindItemClick {
        void onBindItemClick(View view, int position);
    }

    /**
     * item长点击事件
     */
    private OnBindItemLongClick onBindItemLongClick;

    /**
     * 绑定item长点击事件
     *
     * @param onBindItemLongClick 要绑定的接口
     */
    public void setOnBindItemLongClick(OnBindItemLongClick onBindItemLongClick) {
        this.onBindItemLongClick = onBindItemLongClick;
    }

    /**
     * item长点击事件接口
     */
    public interface OnBindItemLongClick {
        void onBindItemLongClick(View view, int position);
    }
}