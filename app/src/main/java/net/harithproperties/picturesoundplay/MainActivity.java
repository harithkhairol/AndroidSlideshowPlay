package net.harithproperties.picturesoundplay;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView imgView;
    ImageButton imgNext,imgPrevious,play,pause;
    Timer timer;




    int currentImage=-1;
    int images[]={R.drawable.image0,R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4,R.drawable.image5,R.drawable.image6,
            R.drawable.image7,R.drawable.image8,R.drawable.image9};
    int sound[]={R.raw.sound0,R.raw.sound1,R.raw.sound2,R.raw.sound3,R.raw.sound4,R.raw.sound5,R.raw.sound6,
            R.raw.sound7,R.raw.sound8,R.raw.sound9};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgView=findViewById(R.id.imgView);
        imgNext=findViewById(R.id.imgNext);
        imgPrevious=findViewById(R.id.imgPrevious);
        play=findViewById(R.id.imgPlay);
        pause=findViewById(R.id.imgPause);




        imgPrevious.setOnClickListener(this);
        imgNext.setOnClickListener(this);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);


//        ring.start();
    }


    public void startSlides() {

        timer = new Timer();
        timer.schedule(new TimerTask() {

            public void run() {
                // avoid exception:
                // "Only the original thread that created a view hierarchy can touch its views"
                runOnUiThread(new Runnable() {
                    public void run() {

                        currentImage=currentImage+1;
                        if(currentImage>=images.length)
                            currentImage=0;
                        showCurrentImage();
                        System.out.print(currentImage);




                    }
                });
            }



        }, 0, 1000);

    }

    public void showNextImage(){

        currentImage=currentImage+1;
        if(currentImage>=images.length)
            currentImage=0;
        showCurrentImage();
        System.out.print(currentImage);


    }

    public void showPreviousImage(){

        currentImage=currentImage-1;
        if(currentImage<0)
            currentImage=images.length-1;
        showCurrentImage();
        System.out.print(currentImage);

    }

    public void showCurrentImage(){

        imgView.setImageResource((images[currentImage]));
        MediaPlayer ring = MediaPlayer.create(MainActivity.this,(sound[currentImage]));
        ring.start();


    }

    @Override
    public void onClick(View view) {


        if (view == imgNext)
            showNextImage();
        else if (view == imgPrevious)
            showPreviousImage();

        else if (view == play) {
            startSlides();


        }

        else if(view==pause){

            if (timer != null) {
                timer.cancel();

                //  timer = null;
            }


        }
    }

    }









