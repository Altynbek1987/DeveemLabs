package com.example.deveemlabs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

import com.example.deveemlabs.adapter.CommentAdapter;
import com.example.deveemlabs.data.service.ServiceApi;
import com.example.deveemlabs.model.Comments;
import com.example.deveemlabs.model.Post;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends AppCompatActivity implements ServiceApi.PostCallback, ExampleDialog.ExampleDialogListener {
    public static final String KEY = "key";
    private RecyclerView commentRecycler;
    private CommentAdapter commentAdapter;
    private List<Comments> commentsList = new ArrayList<>();
    private String comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        createRecyclerView();
        if (getIntent() != null){
            String postId = getIntent().getStringExtra(KEY);
            App.serviceApi.getCommentApi(postId ,this);
        }
    }
    private void createRecyclerView(){
        commentRecycler = findViewById(R.id.recyclerViewComment);
        commentAdapter = new CommentAdapter(commentsList);
        commentRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        commentRecycler.setAdapter(commentAdapter);
        commentAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                addComment();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void addComment(){
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(),"example dialog");
    }

    @Override
    public void onSuccess(List<Post> post) {

    }

    @Override
    public void onFailure(Exception e) {

    }

    @Override
    public void onResponse(List<Comments> body) {
        commentsList.addAll(body);
        commentAdapter.notifyDataSetChanged();
    }

    @Override
    public void applyTexts(Comments comments) {
        commentsList.add(comments);
        commentAdapter.notifyDataSetChanged();
        commentRecycler.smoothScrollToPosition(commentsList.size() - 1);
    }
}