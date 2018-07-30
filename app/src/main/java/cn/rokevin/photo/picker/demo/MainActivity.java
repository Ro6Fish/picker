package cn.rokevin.photo.picker.demo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;

import cn.rokevin.photo.picker.util.PhotoPickUtil;
import cn.rokevin.photo.picker.util.SDUtil;
import cn.rokevin.photo.picker.util.Util;
import cn.rokevin.photo.picker.widget.PhotoPickerDialog;

public class MainActivity extends BaseActivity {

    SimpleDraweeView sdvPhoto;

    private PhotoPickerDialog mDialog;

    private PhotoPickUtil mPhotoPickUtil = new PhotoPickUtil(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDialog();

        sdvPhoto = findViewById(R.id.sdv_photo);

        mPhotoPickUtil.setImageDir(SDUtil.getSDPath() + "/AAA/");
        mPhotoPickUtil.setOnPhotoCropListener(new PhotoPickUtil.OnPhotoCropListener() {
            @Override
            public void onFinish(Uri uri) {

                if (uri == null) {
                    return;
                }

                // 修改过图片先走上传图片接口,在执行更新用户信息接口
                String imageUri = uri.getPath();

                Log.e("Main", "mImageUri:" + imageUri);

                sdvPhoto.setImageURI(uri);
            }
        });

        int width = Util.getScreenWidth(mContext);
        int height = (int) (width / sdvPhoto.getAspectRatio());
//        mPhotoPickUtil.setCropRect(width, height);

        mPhotoPickUtil.disableCrop();

        findViewById(R.id.sdv_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDialog.showDialog();
            }
        });

        findViewById(R.id.btn_clear).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                SDUtil.clear(SDUtil.getSDPath() + "/AAA/");

                sdvPhoto.setImageURI(Uri.parse(""));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPhotoPickUtil.onActivityResult(requestCode, resultCode, data);
    }

    public void initDialog() {

        mDialog = new PhotoPickerDialog(mContext);
        mDialog.setListener(new PhotoPickerDialog.OnPhotoPickerListener() {
            @Override
            public void onCameraPick() {
                mPhotoPickUtil.callCamera();
            }

            @Override
            public void onGalleryPick() {
                mPhotoPickUtil.callPhoto();
            }
        });
    }
}
