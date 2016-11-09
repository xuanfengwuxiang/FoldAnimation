package com.itheima.foldanimation;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private boolean isOpen = false;
    private TextView mTv_animation;
    private ImageView mIv_arrow;
    private String Tag = "MainActivity";
    private int mHeight = 0;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout rl_click = (RelativeLayout) findViewById(R.id.rl_click);
        ImageView iv_login = (ImageView) findViewById(R.id.iv_login);
        mIv_arrow = (ImageView) findViewById(R.id.iv_arrow);

        mTv_animation = (TextView) findViewById(R.id.tv_animation);
        Glide.with(getBaseContext()).load(R.mipmap.login).into(iv_login);
        mTv_animation.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                mHeight = mTv_animation.getMeasuredHeight();
                mTv_animation.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mTv_animation.setHeight(0);

            }
        });
        rl_click.setOnClickListener(new click());

    }



    private class click implements View.OnClickListener {

        public void onClick(View v) {

            int start ;
            int end ;
            if (isOpen){
                start = mHeight;
                end = 0;
                close(start,end);
                isOpen = false;
            }else{
                start = 0;
                end = mHeight;
                Log.i(Tag,end+"");
                open(start,end);
                isOpen = true;
            }

        }
    }

    private void open(int start,int end) {
        ValueAnimator valueAnimator =ValueAnimator.ofInt(start,end);
        valueAnimator.setDuration(1000);
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                int tempHeight = (int) animation.getAnimatedValue();
                mTv_animation.setHeight(tempHeight);
            }
        });
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mIv_arrow,"rotation",0,180);
        objectAnimator.setDuration(1000);
        objectAnimator.start();
    }

    private void close(int start,int end) {
        ValueAnimator valueAnimator =ValueAnimator.ofInt(start,end);
        valueAnimator.setDuration(1000);
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                int tempHeight = (int) animation.getAnimatedValue();

                mTv_animation.setHeight(tempHeight);
            }
        });
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mIv_arrow,"rotation",180,360);
        objectAnimator.setDuration(1000);
        objectAnimator.start();
    }
}
