@[TOC](整体框架展示)


整体效果:
[video(video-eCLp69Fv-1731722794691)(type-bilibili)(url-https://player.bilibili.com/player.html?aid=113490165503734)(image-https://img-blog.csdnimg.cn/img_convert/9234154a16aabd53131787f65abe001b.jpeg)(title-安卓小作业)]
![在这里插入图片描述](https://i-blog.csdnimg.cn/direct/1a9e551f7e57476688e60ce191fa6393.png)
![在这里插入图片描述](https://i-blog.csdnimg.cn/direct/691a41a9ba064d5bb7dc38924e768445.png)
![在这里插入图片描述](https://i-blog.csdnimg.cn/direct/98cb0e109be341afb02f0c724d851c6a.png)
![在这里插入图片描述](https://i-blog.csdnimg.cn/direct/f066c6a31aa54ca387b9c8c69afef749.png)

@[TOC](页面配置)
整体框架有4个fragment页面,聊天,朋友,发现,设置.
配置如下:

```bash
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    tools:context=".Fragment1">

    <!-- TODO: Update blank fragment layout -->
    <TextView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:gravity="center"
        android:text="这是聊天界面"
        android:textSize="60sp" />

    <ImageView
        android:id="@+id/imageView5"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/b1" />

</LinearLayout>
```

```bash
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    tools:context=".Fragment1">

    <!-- TODO: Update blank fragment layout -->
    <TextView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:gravity="center"
        android:text="这是朋友界面"
        android:textSize="60sp" />

    <ImageView
        android:id="@+id/imageView6"
        android:layout_width="400dp"
        android:layout_height="wrap_content"
        android:src="@drawable/b2" />

</LinearLayout>
```

```bash
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".Fragment1">

    <!-- TODO: Update blank fragment layout -->
    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center"
        android:text="这是发现界面"
        android:textSize="60sp" />

    <ImageView
        android:id="@+id/imageView7"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:src="@drawable/b3" />

    <ImageView
        android:id="@+id/imageView8"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:src="@drawable/b3" />

</LinearLayout>片
```

```bash
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".Fragment1">

    <!-- TODO: Update blank fragment layout -->
    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center"
        android:text="这是设置界面"
        android:textSize="60sp" />

    <ImageView
        android:id="@+id/imageView9"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:src="@drawable/b4" />

</LinearLayout>
```
## 设计思路@[TOC](设计思路)
主要想的是能够实现一个能够聊天,看到朋友头像,信息,发现朋友这种的页面,
目前可以看到朋友的头像,名称和个性签名,每个页面后面都有背景图片,页面头和底部分别设计了一个横幅和下面的4个按钮
![在这里插入图片描述](https://i-blog.csdnimg.cn/direct/30f0855a640a420eafebbc6c9e839e8d.png)



fragment2是处理朋友的页面,我们用的是recycleview
RecyclerView 是用于创建可滚动列表的视图控件，它提供了高效灵活的列表展示方式,它可以方便的处理朋友的信息,可以用滚动来查看朋友的头像和里面的一些名称和个性签名

![在这里插入图片描述](https://i-blog.csdnimg.cn/direct/70b6e4c25cc04252aa31226db03cc0bd.png)
代码如下:
```bash
package com.example.myapplication1;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Fragment2 extends Fragment {

    MusicService.Mybinder mybinder;
    private Context context;
    List<Map<String,Object>> list1;
    RecyclerView recyclerView;
    //List list;
    adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    View view=inflater.inflate(R.layout.activity_main_adapte, container, false);
        // Inflate the layout for this fragment

    context=getContext();
    recyclerView=view.findViewById(R.id.RecyclerView2);

    int[] phonename={R.drawable.p1,R.drawable.p2,R.drawable.p3};
    String[] price={"听泉猫","鸡哥","顶针"};
    String[] config={"东西是老的,但没什么用","鸡你太美","一眼盯帧"};

    list1=new ArrayList<>();
        for(int i=0;i<phonename.length;i++){
        Map<String,Object> map=new HashMap<>();
        map.put("name",phonename[i]);
        map.put("price",price[i]);
        map.put("config",config[i]);
        list1.add(map);
    }

    adapter=new adapter(context,list1);
    recyclerView.setAdapter(adapter);
    LinearLayoutManager manager=new LinearLayoutManager(context);
    manager.setOrientation(LinearLayoutManager.HORIZONTAL);
    recyclerView.setLayoutManager(manager);


    ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mybinder=(MusicService.Mybinder) iBinder;
            mybinder.todo();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mybinder=null;
        }
    };


        Intent intent =new Intent(context, MusicService.class);
        //context.startService(intent);
        context.bindService(intent,serviceConnection,Context.BIND_AUTO_CREATE);

        //context.unbindService(serviceConnection);

        return view;
    }
}
```


其中另外的3个fragment页面用于显示视图,代码为

```bash
package com.example.myapplication1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


public class Fragment3 extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_layout3, container, false);
    }
}
```

fragmentmainactivity主要是控制fragment的
通过点击下面的4个按钮来切换4个页面
代码为

```bash
package com.example.myapplication1;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class fragmentMainActivity extends AppCompatActivity implements View.OnClickListener{
    Fragment fragment1,fragment2,fragment3,fragment4;

    LinearLayout layout1,layout2,layout3,layout4;

    FragmentManager manager;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fragment_main);

        layout1=findViewById(R.id.button_LinearLayout1);
        layout2=findViewById(R.id.button_LinearLayout2);
        layout3=findViewById(R.id.button_LinearLayout3);
        layout4=findViewById(R.id.button_LinearLayout4);

        fragment1=new Fragment1();
        fragment2=new Fragment2();
        fragment3=new Fragment3();
        fragment4=new Fragment4();

        manager=getSupportFragmentManager();
        transaction=manager.beginTransaction();

        intial();

        fragmentHide();

        transaction.show(fragment1);


        transaction.commit();


        layout1.setOnClickListener(this::onClick);
        layout2.setOnClickListener(this::onClick);
        layout3.setOnClickListener(this::onClick);
        layout4.setOnClickListener(this::onClick);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void intial() {
        transaction.add(R.id.framelayout1,fragment1);
        transaction.add(R.id.framelayout1,fragment2);
        transaction.add(R.id.framelayout1,fragment3);
        transaction.add(R.id.framelayout1,fragment4);
    }

    void fragmentHide(){
        transaction.hide(fragment1);
        transaction.hide(fragment2);
        transaction.hide(fragment3);
        transaction.hide(fragment4);
//        transaction.commit();
    }

    @Override
    public void onClick(View a) {
        if(a.getId()==R.id.button_LinearLayout1)
            {fragmentHide();showfragment(fragment1);}
        if(a.getId()==R.id.button_LinearLayout2)
            {fragmentHide();showfragment(fragment2);}
        if(a.getId()==R.id.button_LinearLayout3)
            {fragmentHide();showfragment(fragment3);}
        if(a.getId()==R.id.button_LinearLayout4)
            {fragmentHide();showfragment(fragment4);}
        }

    private void showfragment(Fragment fragment_1) {
        transaction=manager.beginTransaction();
        fragmentHide();
        transaction.show(fragment_1);
        transaction.commit();
    }
}
```

其中initial函数这个方法的作用是将四个Fragment都添加到指定的布局容器（R.id.framelayout1）中，以便后续可以在该容器内进行显示、隐藏等操作

```bash
private void intial() {
    transaction.add(R.id.framelayout1, fragment1);
    transaction.add(R.id.framelayout1, fragment2);
    transaction.add(R.id.framelayout1, fragment3);
    transaction.add(R.id.framelayout1, fragment4);
}
```


下面这是实现View.OnClickListener接口的方法，用于处理四个LinearLayout布局的点击事件。当点击某个LinearLayout布局时，首先调用fragmentHide方法隐藏所有的Fragment，然后通过showfragment方法显示与该点击布局对应的Fragment
```bash
@Override
public void onClick(View a) {
    if (a.getId() == R.id.button_LinearLayout1)
        {fragmentHide(); showfragment(fragment1);}
    if (a.getId() == R.id.button_LinearLayout2)
        {fragmentHide(); showfragment(fragment2);}
    if (a.getId() == R.id.button_LinearLayout3)
        {fragmentHide(); showfragment(fragment3);}
    if (a.getId() == R.id.button_LinearLayout4)
        {fragmentHide(); showfragment(fragment4);}
}
```

这个方法用于显示指定的Fragment实例。首先重新创建一个FragmentTransaction实例，然后再次调用fragmentHide方法隐藏所有的Fragment（这可能是为了确保每次显示一个新的Fragment时，其他Fragment都处于隐藏状态），接着通过transaction.show显示指定的Fragment，最后通过transaction.commit提交操作，使显示操作生效。
总体来说，这段代码实现了一个Activity，其中包含四个Fragment，通过点击四个不同的LinearLayout布局区域可以切换显示不同的Fragment，并且在创建Activity时对Fragment进行了添加、隐藏等初始操作，同时还处理了窗口内边距相关的问题以适应屏幕布局
```bash
private void showfragment(Fragment fragment_1) {
    transaction = manager.beginTransaction();
    fragmentHide();
    transaction.show(fragment_1);
    transaction.commit();
}
```

代码地址: