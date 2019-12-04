package com.momen.momen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private Button buttonEnable;
    private Switch imageFlashlight;
    private static final int CAMERA_REQUEST = 50;
    private boolean flashLightStatus = false;

    private Button back;
    private Toolbar toolbar;

    RecyclerView recyclerView;
    Myadapter myadapter;
    String [] title, dese;
    int[] Images ={
            R.drawable.bangaldesh,R.drawable.canda,R.drawable.chin,
            R.drawable.india,R.drawable.japan,R.drawable.naijeria,
            R.drawable.oganda,R.drawable.pakistan,R.drawable.russia,
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //flash lighte
        imageFlashlight =  findViewById(R.id.imageFlashlight);
        buttonEnable =  findViewById(R.id.buttonEnable);
        final boolean hasCameraFlash = getPackageManager().
                hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        boolean isEnabled = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED;
        buttonEnable.setEnabled(!isEnabled);
        imageFlashlight.setEnabled(isEnabled);
        buttonEnable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(HomeActivity.this, new String[] {Manifest.permission.CAMERA}, CAMERA_REQUEST);
            }
        });
        imageFlashlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hasCameraFlash) {
                    if (flashLightStatus)
                        flashLightOff();
                    else
                        flashLightOn();
                } else {
                    Toast.makeText(HomeActivity.this, "No flash available on your device",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        //back button

        back=findViewById(R.id.back_btn_id);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeActivity.super.onBackPressed();
//                Intent intent=new Intent(getApplicationContext(),Main.class);
//                startActivity(intent);
            }
        });



        //add to toolbar back button
        toolbar=(Toolbar) findViewById(R.id.toolbar_id);
        setSupportActionBar(toolbar);



        //add Recyclerview
        recyclerView=findViewById(R.id.recyclerviewid);
        title=getResources().getStringArray(R.array.CountryNamme);
        dese=getResources().getStringArray(R.array.Countrydecspction);
        myadapter= new Myadapter(this,title,dese,Images);
        recyclerView.setAdapter(myadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myadapter.setOnItmClickListener(new Myadapter.Clicklistener() {
            @Override
            public void OnItmClick(int position, View v) {
                Toast.makeText(getApplicationContext(), "Click", Toast.LENGTH_LONG).show();
            }
            @Override
            public void OnItmlongClick(int position, View v) {
                Toast.makeText(getApplicationContext(), "LongClick", Toast.LENGTH_LONG).show();
            }
        });

    }

    //flashlight
    private void flashLightOn() {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, true);
            flashLightStatus = true;
            // imageFlashlight.setImageResource(R.drawable.ic_power_settings_new_black_24dp);
        } catch (CameraAccessException e) {
        }
    }
    private void flashLightOff() {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, false);
            flashLightStatus = false;
            // imageFlashlight.setImageResource(R.drawable.power_off);
        } catch (CameraAccessException e) {
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case CAMERA_REQUEST :
                if (grantResults.length > 0  &&  grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    buttonEnable.setEnabled(false);
                    buttonEnable.setText("");
                    imageFlashlight.setEnabled(true);
                } else {
                    Toast.makeText(HomeActivity.this, "Permission Denied for the Camera", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }




}
