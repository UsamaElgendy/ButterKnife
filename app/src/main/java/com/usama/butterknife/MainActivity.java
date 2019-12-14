package com.usama.butterknife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    @BindView(R.id.log)
    TextView mLog;

    @OnClick(R.id.run_button)
    public void runCode() {
        if (mLog.getText().toString().equals(getString(R.string.intro_text))) {
            mLog.setText("");
        }
        log("Running code");

        Snackbar.make(findViewById(android.R.id.content), "This is a snackbar message", Snackbar.LENGTH_INDEFINITE)
                .setAction("Display Toast", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "This is a Toast message", Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    @OnClick(R.id.clear_button)
    public void clearLog() {
        mLog.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    private void log(String message) {
        Log.i(TAG, message);
        mLog.append(message + "\n");
        adjustScroll();
    }

    private void adjustScroll() {
        final int scrollAmount = mLog.getLayout()
                .getLineTop(mLog.getLineCount()) - mLog.getHeight();
        if (scrollAmount > 0)
            mLog.scrollTo(0, scrollAmount);
        else
            mLog.scrollTo(0, 0);
    }
}
