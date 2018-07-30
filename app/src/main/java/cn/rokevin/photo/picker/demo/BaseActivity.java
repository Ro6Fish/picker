package cn.rokevin.photo.picker.demo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by luokaiwen on 16/8/11.
 */
public class BaseActivity extends AppCompatActivity {

    protected Context mContext = BaseActivity.this;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }
}
