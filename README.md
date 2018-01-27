# DemoLoadMore
RecyclerView LoadMore QUIT

这个示范模板,非常简单,用法:
 
准备工作:
 
工程budle.gradle添加
<pre><code>dependencies {
    compile 'com.scwang.smartrefresh:SmartRefreshLayout:1.0.3'
}
</code></pre>

复制模板三个基类
<pre><code>DefaultAdapter.class
DefaultRVAdapter.class
DefaultViewHolder.class
</code></pre>

使用步骤:

自定义Application类:

添加相关初始化代码
<pre><code>//static 代码段可以防止内存泄露
static {
   //设置全局的Header构建器
   SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
        @Override
        public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
        layout.setPrimaryColorsId(android.R.color.white, android.R.color.darker_gray);//全局设置主题颜色
            return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);//指定为经典Header
        }
   });
   //设置全局的Footer构建器
   SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
        @Override
        public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
            //指定为经典Footer，默认是 BallPulseFooter
            layout.setPrimaryColorsId(android.R.color.white, android.R.color.darker_gray);//全局设置主题颜色
            return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
        }
   });
}
</code></pre>
RecyclerView顶部底部监听
<pre><code>// 监听下拉刷新
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
refreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
refreshLayout.setEnableLoadmore(true);//是否启用上拉加载功能
</code></pre>
适配器
<pre><code>DefaultAdapter recyclerViewAdapter = new DefaultAdapter(
     getApplicationContext(), list, R.layout.item_list);
recyclerViewAdapter.setOnBindItemView(this);
</code></pre>
使用ItemView接口,重写
<pre><code>@Override
public void onBindItemViewHolder(DefaultViewHolder holder, int position) {
     holder.text(R.id.text_x, list.get(position));
}
</code></pre>
