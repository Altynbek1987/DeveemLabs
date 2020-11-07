package com.example.deveemlabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.example.deveemlabs.adapter.AdapterMainActivity;
import com.example.deveemlabs.data.service.ServiceApi;
import com.example.deveemlabs.interfaces.OnItemClickListener;
import com.example.deveemlabs.model.Comments;
import com.example.deveemlabs.model.Post;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ServiceApi.PostCallback {
    RecyclerView recyclerView;
    AdapterMainActivity adapterMainActivity;
    private List<Post> postList;
    private String post;
    private static int MAIN_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.serviceApi.getPostApi(this);
    }
    private void createRecyclerView(){
        recyclerView = findViewById(R.id.recycler_View);
        adapterMainActivity = new AdapterMainActivity(postList);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        recyclerView.setAdapter(adapterMainActivity);
        adapterMainActivity.notifyDataSetChanged();
        adapterMainActivity.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void itemClick(int position) {
                Intent intent = new Intent(MainActivity.this,CommentActivity.class);
                intent.putExtra(CommentActivity.KEY, postList.get(position).getId());
                startActivityForResult(intent,MAIN_CODE);
            }
        });
    }



    @Override
    public void onSuccess(List<Post> post) {
        Log.e("tag","onSuccess_MainActivity");
        postList = post;
        createRecyclerView();
    }

    @Override
    public void onFailure(Exception e) {

    }

    @Override
    public void onResponse(List<Comments> body) {

    }
}