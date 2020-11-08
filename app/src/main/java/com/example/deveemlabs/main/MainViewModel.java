package com.example.deveemlabs.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.deveemlabs.model.Post;

import java.util.List;

public class MainViewModel extends ViewModel  {
    MutableLiveData<List<Post>> postMutableLiveData = new MutableLiveData<>();


}
