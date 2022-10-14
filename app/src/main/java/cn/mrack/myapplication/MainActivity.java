package cn.mrack.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import static cn.mrack.myapplication.App.antiAccessibilityService;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void test(View view) {
        Toast.makeText(this, "抢到了红包", Toast.LENGTH_SHORT).show();
    }


    public void resume(View view) {
        antiAccessibilityService.resume();
    }
}