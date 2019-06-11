package com.example.hy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xw.repo.BubbleSeekBar;

public class pot_size extends AppCompatActivity
{
    TextView pagetitle,pagesubtitle,pagevegedecs;
    ImageView packageplace;
    BubbleSeekBar bubbleseekbar;
    Animation packageimg,packagetitle;
    Button btntake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView( R.layout.activity_pot_size );

        pagetitle=(TextView)findViewById( R.id.pagetitle );
        pagesubtitle=(TextView)findViewById( R.id.pagesubtitle );
        pagevegedecs=(TextView)findViewById( R.id.pagevegedecs );
        packageplace=(ImageView)findViewById( R.id.packageplace );
        bubbleseekbar=(BubbleSeekBar) findViewById( R.id.bubbleseekbar );
        btntake=(Button) findViewById( R.id.btntake );

        packagetitle= AnimationUtils.loadAnimation( this,R.anim.packagetitle );
        packageimg= AnimationUtils.loadAnimation( this,R.anim.packageimg );

        bubbleseekbar.setOnProgressChangedListener( new BubbleSeekBar.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(int progress, float progressFloat)
            {
                if((progress>20)&&(progress<25))
                {
                    pagetitle.setText( "深度20~25公分" );
                    pagesubtitle.setText( "適合葉菜類根系較短，生長快速" );
                    pagevegedecs.setText( "#小紅蘿蔔 #大蒜" );
                    packageplace.setImageResource( R.drawable.ssize );
                    btntake.setBottom(  R.id.btntake );

                    packageplace.startAnimation( packageimg );
                    pagetitle.startAnimation( packagetitle );
                    pagesubtitle.startAnimation( packagetitle );
                    pagevegedecs.startAnimation( packagetitle );
                    btntake.setOnClickListener( new View.OnClickListener() {
                        @Override
                        public void onClick(View v)
                        {
                            Toast toast=Toast.makeText( pot_size.this,"已新增盆栽",Toast.LENGTH_SHORT );
                            toast.setGravity( Gravity.CENTER, 0, 0);
                            toast.show();


                            Intent a = new Intent(pot_size.this,choose_vege.class);
                            startActivity(a);
                        }
                    } );
                }

                else if((progress>25)&&(progress<35))
                {
                    pagetitle.setText( "深度25~35公分" );
                    pagesubtitle.setText( "適合架枝架的豆類，需要稍微穩重" );
                    pagevegedecs.setText( "#小紅蘿蔔 #大蒜" );
                    packageplace.setImageResource( R.drawable.msize );
                    btntake.setBottom(  R.id.btntake );

                    packageplace.startAnimation( packageimg );
                    pagetitle.startAnimation( packagetitle );
                    pagesubtitle.startAnimation( packagetitle );
                    pagevegedecs.startAnimation( packagetitle );
                    btntake.setOnClickListener( new View.OnClickListener() {
                        @Override
                        public void onClick(View v)
                        {
                            Toast toast=Toast.makeText( pot_size.this,"已新增盆栽",Toast.LENGTH_SHORT );
                            toast.setGravity( Gravity.CENTER, 0, 0);
                            toast.show();

                            Intent b = new Intent(pot_size.this,test_two.class);
                            startActivity(b);
                        }
                    } );

                }

                else if((progress>35)&&(progress<40))
                {
                    pagetitle.setText( "深度35~40公分" );
                    pagesubtitle.setText( "生長時間長、需肥性強的瓜果和根莖類" );
                    packageplace.setImageResource( R.drawable.lsixe );
                    pagevegedecs.setText( "#小紅蘿蔔 #大蒜" );

                    packageplace.startAnimation( packageimg );
                    pagetitle.startAnimation( packagetitle );
                    pagesubtitle.startAnimation( packagetitle );
                    pagevegedecs.startAnimation( packagetitle );

                    btntake.setOnClickListener( new View.OnClickListener() {
                        @Override
                        public void onClick(View v)
                        {
                            Toast toast=Toast.makeText( pot_size.this,"已新增盆栽",Toast.LENGTH_SHORT );
                            toast.setGravity( Gravity.CENTER, 0, 0);
                            toast.show();

                            Intent b = new Intent(pot_size.this,test_two.class);
                            startActivity(b);
                        }
                    } );
                }
            }

            @Override
            public void getProgressOnActionUp(int progress, float progressFloat)
            {

            }

            @Override
            public void getProgressOnFinally(int progress, float progressFloat)
            {

            }
        } );

    }
}
