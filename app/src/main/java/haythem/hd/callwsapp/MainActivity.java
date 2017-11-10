package haythem.hd.callwsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import haythem.hd.callwsapp.view.asynctask.CallWithAsyncActivity;
import haythem.hd.callwsapp.view.retrofit.CallWithRetrofitActivity;
import haythem.hd.callwsapp.view.thread.CallWithThreadActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button mBtnThreadCall;
    Button mBtnAsyncCall;
    Button mBtnRetroCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewsById();
        mBtnThreadCall.setOnClickListener(this);
        mBtnAsyncCall.setOnClickListener(this);
        mBtnRetroCall.setOnClickListener(this);
    }


    private void findViewsById() {
        mBtnThreadCall = findViewById(R.id.thread_btn);
        mBtnAsyncCall = findViewById(R.id.async_btn);
        mBtnRetroCall = findViewById(R.id.retro_btn);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.thread_btn:
                intent = new Intent(MainActivity.this, CallWithThreadActivity.class);
                startActivity(intent);
                break;
            case R.id.async_btn:
                intent = new Intent(MainActivity.this, CallWithAsyncActivity.class);
                startActivity(intent);
                break;
            case R.id.retro_btn:
                intent = new Intent(MainActivity.this, CallWithRetrofitActivity.class);
                startActivity(intent);
                break;
        }
    }
}
