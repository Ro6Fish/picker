package cn.rokevin.photo.picker.widget;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import cn.rokevin.photo.picker.R;
import cn.rokevin.photo.picker.util.Util;

/**
 * Created by luokaiwen on 15/5/28.
 * <p/>
 * 图片选择器弹框
 */
public class PhotoPickerDialog extends BaseDialog {

    private OnPhotoPickerListener mListener;

    public PhotoPickerDialog(Context context) {
        super(context);
    }

    public void setListener(OnPhotoPickerListener mListener) {
        this.mListener = mListener;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_photo_picker;
    }

    @Override
    protected void showView(View view) {

        RelativeLayout rlMain = view.findViewById(R.id.rl_main);
        Button btnCamera = view.findViewById(R.id.btn_camera);
        Button btnGallry = view.findViewById(R.id.btn_gallery);
        Button btnCancel = view.findViewById(R.id.btn_cancel);

        rlMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cancel();
            }
        });

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (null != mListener) {
                    mListener.onCameraPick();
                }
                cancel();
            }
        });

        btnGallry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (null != mListener) {
                    mListener.onGalleryPick();
                }
                cancel();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cancel();
            }
        });
    }

    @Override
    protected int getAnimStyle() {
        return R.style.AnimPhotoPicker;
    }

    @Override
    protected int getWidth() {

        return Util.getScreenWidth(mContext);
    }

    public interface OnPhotoPickerListener {

        void onCameraPick();

        void onGalleryPick();
    }
}
