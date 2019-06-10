package com.rikkei.android_trainning;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Fragment1 extends Fragment {
    public static final String TAG = Fragment1.class.toString();
    private MediaPlayer mediaPlayer;
    private int currentPosition = 0;

    @Override
    public void onAttach(Context context) {
        Log.d(TAG,"onAttach 1");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onCreate 1");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView 1");
        View view = inflater.inflate(R.layout.fragment1,container,false);
        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onViewCreated 1");
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init(){
        mediaPlayer = MediaPlayer.create(getContext(),R.raw.testa);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d(TAG,"onSaveInstanceState 1");
        outState.putInt("position",mediaPlayer.getCurrentPosition());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState != null){
            currentPosition = savedInstanceState.getInt("position");
            mediaPlayer.seekTo(currentPosition);
        }
    }

    @Override
    public void onStart() {
        Log.d(TAG,"onStart 1");
        super.onStart();
        mediaPlayer.start();
    }

    @Override
    public void onResume() {
        Log.d(TAG,"onResume 1");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d(TAG,"onPause 1");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d(TAG,"onStop 1");
        super.onStop();
        mediaPlayer.stop();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG,"onDestroy 1---------------------");
        super.onDestroy();
    }

}
