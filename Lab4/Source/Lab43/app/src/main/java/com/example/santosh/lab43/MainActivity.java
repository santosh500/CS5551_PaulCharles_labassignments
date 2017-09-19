package com.example.santosh.lab43;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

//Code was derived and referenced from Google Developers: https://developers.google.com/identity/sign-in/android/sign-in
//Code was derived and referenced from Google Developers: (Github Link)https://github.com/googlesamples/google-services/blob/master/android/signin/app/src/main/java/com/google/samples/quickstart/signin/SignInActivity.java#L51-L55

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener
{

    //Code was derived and referenced from Google Developers: https://developers.google.com/identity/sign-in/android/sign-in
    //Code was derived and referenced from Google Developers: (Github Link)https://github.com/googlesamples/google-services/blob/master/android/signin/app/src/main/java/com/google/samples/quickstart/signin/SignInActivity.java#L51-L55
    private static final int SignInParam = 9001;

    Button locSearch;

    //Code was derived and referenced from Google Developers: https://developers.google.com/identity/sign-in/android/sign-in
    //Code was derived and referenced from Google Developers: (Github Link)https://github.com/googlesamples/google-services/blob/master/android/signin/app/src/main/java/com/google/samples/quickstart/signin/SignInActivity.java#L51-L55
    private GoogleApiClient mGoogleApiClient;
    private TextView mStatusTextView;
    private ProgressDialog mProgressDialog;

    String username, password;

    EditText un, pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Code was derived and referenced from Google Developers: https://developers.google.com/identity/sign-in/android/sign-in
        //Code was derived and referenced from Google Developers: (Github Link)https://github.com/googlesamples/google-services/blob/master/android/signin/app/src/main/java/com/google/samples/quickstart/signin/SignInActivity.java#L51-L55
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        //Code was derived and referenced from Google Developers: https://developers.google.com/identity/sign-in/android/sign-in
        //Code was derived and referenced from Google Developers: (Github Link)https://github.com/googlesamples/google-services/blob/master/android/signin/app/src/main/java/com/google/samples/quickstart/signin/SignInActivity.java#L51-L55
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        locSearch = (Button) findViewById(R.id.button4);

        //Code was derived and referenced from Google Developers: https://developers.google.com/identity/sign-in/android/sign-in
        //Code was derived and referenced from Google Developers: (Github Link)https://github.com/googlesamples/google-services/blob/master/android/signin/app/src/main/java/com/google/samples/quickstart/signin/SignInActivity.java#L51-L55
        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_WIDE);

        findViewById(R.id.sign_in_button).setOnClickListener(this);
        findViewById(R.id.button).setOnClickListener(this);

        username = getIntent().getStringExtra("username");
        password = getIntent().getStringExtra("password");




    }


    public void login(View v)
    {

        un= (EditText) findViewById(R.id.editText);
        pw = (EditText) findViewById(R.id.editText3);
        if(un.getText().toString().equals(username) && pw.getText().toString().equals(password))
        {
            startActivity(new Intent(MainActivity.this,PlacesApp.class));
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Login Failed",
                    Toast.LENGTH_LONG).show();
        }

    }


    //Code was derived and referenced from Google Developers: https://developers.google.com/identity/sign-in/android/sign-in
    //Code was derived and referenced from Google Developers: (Github Link)https://github.com/googlesamples/google-services/blob/master/android/signin/app/src/main/java/com/google/samples/quickstart/signin/SignInActivity.java#L51-L55
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, SignInParam);

    }



    //Code was derived and referenced from Google Developers: https://developers.google.com/identity/sign-in/android/sign-in
    //Code was derived and referenced from Google Developers: (Github Link)https://github.com/googlesamples/google-services/blob/master/android/signin/app/src/main/java/com/google/samples/quickstart/signin/SignInActivity.java#L51-L55
    // [START signOut]
    public void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {

                    }
                });
    }




    //Code was derived and referenced from Google Developers: https://developers.google.com/identity/sign-in/android/sign-in
    //Code was derived and referenced from Google Developers: (Github Link)https://github.com/googlesamples/google-services/blob/master/android/signin/app/src/main/java/com/google/samples/quickstart/signin/SignInActivity.java#L51-L55
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            case R.id.button:
                signOut();
                break;

        }
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
