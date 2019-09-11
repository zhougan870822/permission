package com.zhoug.permission.module;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zhoug.android.permission.PermissionManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListener();
    }

    private void addListener(){

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1011);

                new PermissionManager(MainActivity.this)
                        .addPermissions(
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE
                        )
                        .setNotAsking("自行车自行车")
                        .setCallback(new PermissionManager.Callback() {
                            @Override
                            public void onRequestPermissionsResult(boolean success, ArrayList<String> grants) {
                                Log.d(TAG, "onRequestPermissionsResult:success="+success);
                                 Toast.makeText(MainActivity.this, success+"", Toast.LENGTH_SHORT).show();
                                 if(grants!=null){
                                     for (String g:grants){
                                         Log.d(TAG, "onRequestPermissionsResult:"+g);
                                     }
                                 }

                            }
                        }).request();


            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1011);

                new PermissionManager(MainActivity.this)
                        .addPermissions(
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE,

                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION,

                                Manifest.permission.CALL_PHONE,

                                Manifest.permission.BODY_SENSORS,

                                Manifest.permission.SEND_SMS,
                                Manifest.permission.READ_SMS,

                                Manifest.permission.READ_CALENDAR,
                                Manifest.permission.WRITE_CALENDAR,

                                Manifest.permission.READ_CONTACTS,
                                Manifest.permission.WRITE_CONTACTS,

                                Manifest.permission.RECORD_AUDIO,

                                Manifest.permission.CAMERA
                        )
                        .setNotAsking("自行车自行车")
                        .setCallback(new PermissionManager.Callback() {
                            @Override
                            public void onRequestPermissionsResult(boolean success, ArrayList<String> grants) {
                                Log.d(TAG, "onRequestPermissionsResult:success="+success);
                                Toast.makeText(MainActivity.this, success+"", Toast.LENGTH_SHORT).show();
                                if(grants!=null){
                                    for (String g:grants){
                                        Log.d(TAG, "onRequestPermissionsResult:"+g);
                                    }
                                }

                            }
                        }).request();
            }
        });

        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PermissionManager()
                        .setContext(MainActivity.this)
                        .setshowNotAskingDialog(false)
                        .addPermissions(Manifest.permission.CAMERA,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .setCallback(new PermissionManager.Callback() {
                            @Override
                            public void onRequestPermissionsResult(boolean success, ArrayList<String> denied) {
                                if(success){
                                    Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }else{
                                    String msg="拍照需要";
                                    if(denied!=null && denied.size()>0){
                                        if(denied.contains(Manifest.permission.CAMERA)){
                                            msg+="相机,";
                                        }
                                        if(denied.contains(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                                            msg+="储存";
                                        }
                                    }
                                    if(msg.endsWith(",")){
                                        msg=msg.substring(0,msg.length()-1);
                                    }
                                    msg+="权限";
                                     Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                                }

                            }
                        }).request();

            }
        });
    }


}
