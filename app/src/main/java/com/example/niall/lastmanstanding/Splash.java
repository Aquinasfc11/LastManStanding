package com.example.niall.lastmanstanding;

/**
 * Created by Niall on 06/12/2017.
 */

import android.app.Activity;
        import android.content.Intent;
        import android.graphics.PixelFormat;
        import android.os.Bundle;
        import android.view.Window;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.widget.LinearLayout;
        import android.widget.ProgressBar;

public class Splash extends Activity {

    //variables declared
    private ProgressBar mProgress;

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    //Called when the activity is first created
    Thread splashTread;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //xml layour for splash screen
        setContentView(R.layout.splash);
        //start the animation of the splash screen
        StartAnimations();
    }

    //method of starting the animation for the splash screen
    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout l = (LinearLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        // starts the animation of the splash screen
        l.startAnimation(anim);
        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();

        splashTread = new Thread() {
            @Override
            public void run() {

                try {
                    int waited = 0;

                    // splash screen pause time
                    while (waited < 3500) {
                        sleep(100);

                        waited += 100;
                    }
                    //goes to login page once splash screen as ended
                    Intent intent = new Intent(Splash.this,
                            Login.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    Splash.this.finish();
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    //on completion of the splash screen animation
                    Splash.this.finish();
                }
            }
        };
        splashTread.start();
    }
}


