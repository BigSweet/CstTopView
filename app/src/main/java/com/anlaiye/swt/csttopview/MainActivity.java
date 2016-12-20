package com.anlaiye.swt.csttopview;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
    private CstTopView cstTopView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        cstTopView= (CstTopView) findViewById(R.id.csttopview);

        //获得控件调用这个点击方法，实现点击事件
        cstTopView.SetTopListener(new CstTopView.ToolListener() {
            @Override
            public void LeftClick() {
                Toast.makeText(MainActivity.this,"左边被点击啦",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void RightClick() {
                Toast.makeText(MainActivity.this,"右边被点击啦",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
