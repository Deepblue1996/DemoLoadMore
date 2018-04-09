# UniversalAdapter
UniversalAdapter is a fast recyclerview adapter, which enables quick insertion of header layout and bottom layout.

### RecyclerView 快捷使用适配器 插入头布局，脚布局
RecyclerView fast use adapter insert header layout, foot layout

![Image text](https://raw.githubusercontent.com/Deepblue1996/UniversalAdapter/master/20180203163000.jpg)

部署:
 
下拉刷新工程budle.gradle添加
<pre><code>dependencies {
    compile 'com.scwang.smartrefresh:SmartRefreshLayout:1.0.3'
}
</code></pre>

## How to

To get a Git project into your build:

Step 1. Add the JitPack repository to your build file

gradle
maven
sbt
leiningen
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}Copy
Step 2. Add the dependency

	dependencies {
	        compile 'com.github.Deepblue1996:UniversalAdapter:1.0'
	}

