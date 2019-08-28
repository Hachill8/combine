package com.example.hy.login;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.hy.R;
import com.example.hy.select_model;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener, View.OnClickListener
{

    TextView loginsub;
    Animation smalltobig, stb2;
    String name,email;

    //
    SignInButton SignIn;
    Button SignOut;
    public GoogleApiClient googleApiClient;
    public static final int GoogleSignInRequestCode=9001;
    public TextView Name,Email;
    private RelativeLayout Prof_Section;
    public FirebaseAnalytics mFirebaseAnalytics;//try
    public FirebaseAuth mAuth;//try
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        Log.v("test", "start");

        smalltobig = AnimationUtils.loadAnimation(this, R.anim.smalltobig);
        stb2 = AnimationUtils.loadAnimation(this, R.anim.stb2);

        loginsub = (TextView) findViewById(R.id.loginsub);

        //
//        Prof_Section=(RelativeLayout)findViewById(R.id.prof_section);
//        Email=(TextView)findViewById(R.id.email);
//        SignOut=(Button)findViewById(R.id.bn_logout);
//        Name=(TextView)findViewById(R.id.name);
        SignIn=(SignInButton)findViewById(R.id.bn_login);


        SignIn.setOnClickListener(this);
        //SignOut.setOnClickListener(this);
        //Prof_Section.setVisibility(View.GONE);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);//try
        // 設定 FirebaseAuth 介面
        mAuth = FirebaseAuth.getInstance();//try
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().requestId().requestProfile().build();


        googleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();

        loginsub.setTranslationY(400);
        SignIn.setTranslationY(400);

        SignIn.setAlpha(0);
        loginsub.setAlpha(0);

        SignIn.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        loginsub.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(700).start();

//        SignIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent b = new Intent(login.this,login2.class);
//                startActivity(b);
//            }   });
    }
    //
    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {

            case R.id.bn_login:
                signIn();break;
//            case R.id.bn_logout:
//                signOut();break;
        }
    }
    @Override
    public void onConnected(@Nullable Bundle bundle) {  }
    @Override
    public void onConnectionSuspended(int i) {}
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) { }

//    public void test()
//    {
//        Intent b = new Intent(login.this, login2.class);
//        startActivity(b);
//    }

    public void signIn()
    {
        //signInButton 按下時，啟動登入頁面
        Intent googleSignInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(googleSignInIntent,GoogleSignInRequestCode);
    }

//    public void signOut()
//    {   Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>()
//    {
//        @Override
//        public void onResult(@NonNull Status status)
//        { updateUI(false); }
//
//    }); }

    private  void handleResult(GoogleSignInResult result)
    {
        if (result.isSuccess())
        {
            GoogleSignInAccount account = result.getSignInAccount();
            //取得使用者並試登入
//                firebaseAuthWithGoogle(account);//try
//            name = account.getDisplayName();
//            email = account.getEmail();
//            Name.setText(name);
//            Email.setText(email);
            updateUI(true);
            Log.v("test", "true");
        }
        else{
            updateUI(false);
            Log.v("test", "false");
        }
    }

    private  void updateUI(boolean isLogin)
    {
        Log.v("test", "enter updateui");
        if(isLogin)
        {
//          Prof_Section.setVisibility(View.VISIBLE);顯示Gmail帳號及暱稱
//          SignIn.setVisibility(View.GONE);

            //以下為將資料/數據值傳遞到下一個java檔
            //new一個intent物件，並指定Activity切換的class

//            Intent intent = new Intent();
//            intent.setClass(login.this, login2.class);
//
//            //new一個Bundle物件，並將要傳遞的資料傳入
//            Bundle bundle = new Bundle();
//            bundle.putString("name", name);
//            //將Bundle物件assign給intent
//            intent.putExtras(bundle);
//
//            //切換Activity
//            startActivity(intent);

            Toast.makeText(login.this, "帳號連結成功", Toast.LENGTH_SHORT).show();

            Intent a = new Intent( login.this,login2.class);
            startActivity(a);
            Log.v("test", "成功");
        }
        else
        {
//            Prof_Section.setVisibility(View.GONE);
            SignIn.setVisibility(View.VISIBLE);
            Log.v("test","失敗");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GoogleSignInRequestCode)
        {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
            Log.v("test","result: "+result.toString());
        }
    }
    //API end
}

