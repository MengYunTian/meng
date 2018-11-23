package qmuiandroidstudy.qiao.com.myapplication;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.Toast;

public class MainActivity extends Activity {
    private LinearLayout root_view;
    private int screenHeight = 0;
    private int keyHeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        initOtherData();
    }

    private void initView() {
        root_view = (LinearLayout) findViewById(R.id.root_view);
    }

    private void initListener() {
        root_view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeight)) {
                    Toast.makeText(MainActivity.this,"键盘弹起",Toast.LENGTH_SHORT).show();
                    _MoveUp(root_view, 40);
                } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyHeight)) {
                    Toast.makeText(MainActivity.this,"键盘落下",Toast.LENGTH_SHORT).show();
                    _MoveDown(root_view, 40);
                }
            }
        });
    }

    private void initOtherData() {
        screenHeight = this.getWindowManager().getDefaultDisplay().getHeight();
        keyHeight = screenHeight / 3;
    }
    /**
     * 布局上移
     */
    void _MoveUp(View v, int h) {
        v.scrollBy(0, DensityUtil.dip2px(this, h));
    }


    /**
     * 布局下沉
     *
     * @param v
     * @param h
     */
    void _MoveDown(View v, int h) {
        v.scrollBy(0, -DensityUtil.dip2px(this, h));
    }


}
