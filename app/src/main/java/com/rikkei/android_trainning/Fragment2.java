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


public class Fragment2 extends Fragment {
    public static final String TAG = Fragment2.class.toString();
    private MediaPlayer mediaPlayer;

    @Override
    public void onAttach(Context context) {
        Log.d(TAG,"onAttach 2");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onCreate 2");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView 2");
        View view = inflater.inflate(R.layout.fragment2,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onViewCreated 2");
        super.onViewCreated(view, savedInstanceState);
        init();
    }
    private void init() {
        mediaPlayer = MediaPlayer.create(getContext(),R.raw.testb);
    }

    @Override
    public void onStart() {
        Log.d(TAG,"onStart 2");
        super.onStart();
        mediaPlayer.start();
    }

    @Override
    public void onResume() {
        Log.d(TAG,"onResume 2");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d(TAG,"onPause 2");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d(TAG,"onStop 2");
        super.onStop();
        mediaPlayer.stop();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG,"onDestroy 2--------------------");
        super.onDestroy();
    }
}
