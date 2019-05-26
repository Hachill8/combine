package com.example.hy;
import android.app.AuthenticationRequiredException;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener, View.OnClickListener
{
    ImageButton fab,fab_ft01,fab_ft02,fab_ft03,fab_ft04;

    Button fab_ft05,SignOut;
    SignInButton SignIn;
    private GoogleApiClient googleApiClient;
    private static final int GoogleSignInRequestCode=9001;
    private TextView Name,Email;
    private RelativeLayout Prof_Section;
    private FirebaseAnalytics mFirebaseAnalytics;//try
    private FirebaseAuth mAuth;//try

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //
        Prof_Section=(RelativeLayout)findViewById(R.id.prof_section);
        Name=(TextView)findViewById(R.id.name);
        Email=(TextView)findViewById(R.id.email);
        SignIn=(SignInButton)findViewById(R.id.bn_login);
        SignOut=(Button)findViewById(R.id.bn_logout);
        SignIn.setOnClickListener(this);
        SignOut.setOnClickListener(this);
        Prof_Section.setVisibility(View.GONE);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);//try
        // 設定 FirebaseAuth 介面
        mAuth = FirebaseAuth.getInstance();//try
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().requestId().requestProfile().build();
        googleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(MainActivity.this,new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(MainActivity.this,"Google",Toast.LENGTH_LONG).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();
        //
        fab=(ImageButton)findViewById(R.id.fab);
        fab_ft01=(ImageButton)findViewById(R.id.fab_紀);
        fab_ft02=(ImageButton)findViewById(R.id.fab_討);
        fab_ft03=(ImageButton)findViewById(R.id.fab_商);
        fab_ft04=(ImageButton)findViewById(R.id.fab_曆);
        fab_ft05=(Button)findViewById(R.id.fab_搜);

        fab_ft05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,search.class);
                startActivity(intent);
            }
        });

        fab_ft04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,calendar.class);
                startActivity(intent);
            }
        });

        fab_ft01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,record.class);
                startActivity(intent);
            }
        });
        fab_ft02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,forum.class);
                startActivity(intent);
            }
        });

        fab_ft03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,market.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.bn_login:
                signIn();break;
            case R.id.bn_logout:
                signOut();break;
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {  }
    @Override
    public void onConnectionSuspended(int i) {}
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) { }

    public void signIn()
    {   //signInButton 按下時，啟動登入頁面
        Intent googleSignInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(googleSignInIntent,GoogleSignInRequestCode);
    }

    public void signOut()
    {
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>()
        {
            @Override
            public void onResult(@NonNull Status status)
            { updateUI(false); }

        });
    }

    private  void handleResult(GoogleSignInResult result)
    {
        if (result.isSuccess())
            {
                GoogleSignInAccount account = result.getSignInAccount();
                //取得使用者並試登入
//                firebaseAuthWithGoogle(account);//try
                String name = account.getDisplayName();
                String email = account.getEmail();
                Name.setText(name);
                Email.setText(email);
                updateUI(true);
            }
            else{updateUI(false); }
    }

//    //登入 Firebase
//    private  void firebaseAuthWithGoogle(final GoogleSignInAccount account){
//        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (!task.isSuccessful()) {
//                            Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_LONG).show();
//                        }else {
//                            Toast.makeText(MainActivity.this, "SingIn name:"+account.getDisplayName(), Toast.LENGTH_LONG).show();
//                        }
//                    }
//
//                });
//    }
//
//    public void firebaseSingOut(View view)
//    {
//        // Firebase 登出
//        mAuth.signOut();
//
//        // Google 登出
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//        GoogleSignIn.getClient(this, gso).signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                Toast.makeText(MainActivity.this, "SingOut", Toast.LENGTH_LONG).show();
//            }
//        });
//    }


        private  void updateUI(boolean isLogin)
    {
        if(isLogin)
        {
            Prof_Section.setVisibility(View.VISIBLE);
            SignIn.setVisibility(View.GONE);
        }
        else
        {
            Prof_Section.setVisibility(View.GONE);
            SignIn.setVisibility(View.VISIBLE);
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
        }
    }
}

