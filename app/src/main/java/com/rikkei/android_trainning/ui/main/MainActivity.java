package com.rikkei.android_trainning.ui.main;

import android.Manifest;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.rikkei.camerapreview.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    private Camera mcamera;
    private SurfaceView msurfaceView;
    private SurfaceHolder msurfaceHolder;
    private Camera.PictureCallback captureImageCallback;
    private Camera.PictureCallback rawCallback;
    private Camera.ShutterCallback shutterCallback;
    private MediaRecorder recorder;
    private boolean frontCam;
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private ImageView ivPhoto;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void init() {
        ivPhoto = findViewById(R.id.ivPhoto);
        msurfaceView = findViewById(R.id.surfaceView);
        msurfaceHolder = msurfaceView.getHolder();

        if (checkSelfPermission(Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA},
                    MY_CAMERA_REQUEST_CODE);
        } else {
            msurfaceHolder.addCallback(this);
            msurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
        ImageView button = findViewById(R.id.ivRotateCamera);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roTateCamera();
            }
        });
        ImageView btnchup = findViewById(R.id.ivCamera);
        btnchup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureImage();
            }
        });
        frontCam = false;
    }

    public void refreshCamera() {
        if (msurfaceHolder.getSurface() == null) return;

        try {
            mcamera.stopPreview();
        } catch (Exception e) {
        }

        try {
            mcamera.setPreviewDisplay(msurfaceHolder);
            mcamera.startPreview();
        } catch (Exception e) {
        }
    }

    private void roTateCamera() {
        if (frontCam) {
            int cameraId = isBackCameraExisted();
            if (cameraId >= 0) {
                try {
                    mcamera.stopPreview();
                    mcamera.release();

                    mcamera = Camera.open(cameraId);
                    mcamera.setPreviewDisplay(msurfaceHolder);
                    mcamera.startPreview();

                    frontCam = false;

                    changeOrientation();
                } catch (RuntimeException e) {
                } catch (Exception e) {
                }

                Camera.Parameters param;
                param = mcamera.getParameters();

                param.setPreviewSize(msurfaceView.getWidth(), msurfaceView.getHeight());
                param.setPreviewFrameRate(50);
                param.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
            }
        } else {
            int cameraId = isFrontCameraExisted();
            if (cameraId >= 0) {
                try {
                    mcamera.stopPreview();
                    mcamera.release();

                    mcamera = Camera.open(cameraId);
                    mcamera.setPreviewDisplay(msurfaceHolder);
                    mcamera.startPreview();

                    frontCam = true;

                    changeOrientation();
                } catch (RuntimeException e) {
                } catch (Exception e) {
                }

                Camera.Parameters param;
                param = mcamera.getParameters();

                param.setPreviewSize(msurfaceView.getWidth(), msurfaceView.getHeight());
                param.setPreviewFrameRate(30);
                param.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
            }
        }
    }

    public void captureImage() {
        rawCallback = new Camera.PictureCallback() {
            public void onPictureTaken(byte[] data, Camera camera) {
                Log.d("Log", "onPictureTaken - raw");
            }
        };

        /** Handles data for jpeg picture */
        shutterCallback = new Camera.ShutterCallback() {
            public void onShutter() {
                Log.i("Log", "onShutter'd");
            }
        };
        captureImageCallback = new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                File pictureFile = getOutputMediaFile();
                if (pictureFile == null) {
                    return;
                }
                try {
                    FileOutputStream fos = new FileOutputStream(pictureFile);
                    fos.write(data);
                    fos.close();
                } catch (FileNotFoundException e) {

                } catch (IOException e) {
                }
            }
        };
        mcamera.takePicture(shutterCallback, rawCallback, captureImageCallback);
        refreshCamera();

    }

    private static File getOutputMediaFile() {
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "MyCameraApp");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator
                + "IMG_" + timeStamp + ".jpg");

        return mediaFile;
    }


    public int isBackCameraExisted() {
        int cameraId = -1;

        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                cameraId = i;
                break;
            }
        }
        return cameraId;
    }

    public void changeOrientation() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            mcamera.setDisplayOrientation(0);
        else
            mcamera.setDisplayOrientation(90);
    }

    public int isFrontCameraExisted() {
        int cameraId = -1;

        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                cameraId = i;
                break;
            }
        }
        return cameraId;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mcamera = Camera.open();
        Camera.Parameters parameters;
        parameters = mcamera.getParameters();
        mcamera.setDisplayOrientation(90);
        parameters.setPreviewFrameRate(30);
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        try {
            mcamera.setPreviewDisplay(holder);

        } catch (IOException e) {
            e.printStackTrace();
        }
        mcamera.startPreview();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        try {
            mcamera.setPreviewDisplay(msurfaceHolder);
            mcamera.startPreview();
        } catch (Exception e) {
            // intentionally left blank for a test
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        mcamera.stopPreview();
        mcamera.release();
        mcamera = null;
    }

    @Override
    public void onConfigurationChanged(Configuration config) {
        super.onConfigurationChanged(config);
        changeOrientation();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_CAMERA_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    msurfaceHolder.addCallback(this);
                    msurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
                } else {
                    Toast.makeText(this, "Please provide the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }


}
