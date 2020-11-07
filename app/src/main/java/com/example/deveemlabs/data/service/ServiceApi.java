package com.example.deveemlabs.data.service;

import android.accounts.NetworkErrorException;
import android.util.Log;

import com.example.deveemlabs.model.Comments;
import com.example.deveemlabs.model.Post;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class ServiceApi {
private OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
    private Retrofit retrofit = new Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClientBuilder.build())
        .baseUrl("http://jsonplaceholder.typicode.com/")
        .build();
    private IPlaceholderApi service = retrofit.create(IPlaceholderApi.class);

    public void getPostApi(PostCallback callback){
        Call<List<Post>> call = service.getPost();
                call.enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                        if (response.isSuccessful()){
                            if (response.body() != null){
                                callback.onSuccess(response.body());
                                Log.e("tag","onSuccess_getPostApi"+ response.body().toString());
                            }else {
                                callback.onFailure(new NetworkErrorException());
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Post>> call, Throwable t) {
                        callback.onFailure(new Exception());
                        Log.d("tag", t.getMessage().toString());
                    }
                });
    }

    public void getCommentApi(String postId,PostCallback callback){
        Call<List<Comments>> call = service.getComments("");
        call.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                if (response.isSuccessful() && response.body()!=null){
                    callback.onResponse(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {
                callback.onFailure(new NetworkErrorException());

            }
        });

    }


    public interface PostCallback{
        void onSuccess(List<Post> post);
        void onFailure(Exception e);
        void onResponse(List<Comments> body);
    }


    public interface IPlaceholderApi{
        @GET("posts")
        Call<List<Post>>getPost();
        @GET("comments")
        Call<List<Comments>>getComments(@Query("postId")String postId);


    }


}
