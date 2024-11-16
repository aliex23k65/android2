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