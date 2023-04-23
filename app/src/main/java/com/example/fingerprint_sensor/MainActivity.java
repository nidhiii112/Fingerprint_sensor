package com.example.fingerprint_sensor;

import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ImageView imageView;
    private FingerprintManager fingerprintManager;
    private FingerprintManager.AuthenticationCallback authenticationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.fingeprint1);
        fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

        authenticationCallback = new FingerprintManager.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                textView.setText("ERROR");
                imageView.setImageResource(R.drawable.fingerprint2);
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                textView.setText("HELP");
                imageView.setImageResource(R.drawable.fingeprint1);
                super.onAuthenticationHelp(helpCode, helpString);
            }

            @Override
            public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                textView.setText("SUCCESS");
                imageView.setImageResource(R.drawable.fingerprint3);
                super.onAuthenticationSucceeded(result);

                Intent intent = new Intent(MainActivity.this, Dashboard.class);
                startActivity(intent);
            }

            @Override
            public void onAuthenticationFailed() {
                textView.setText("BACKSPACE");
                imageView.setImageResource(R.drawable.fingerprint2);
                super.onAuthenticationFailed();
            }
        };
    }

    public void scanButton(View view){
        fingerprintManager.authenticate(null, null, 0, authenticationCallback, null);
    }
    }
