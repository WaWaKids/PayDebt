package android.wawakidss.paydebt.accounts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.wawakidss.paydebt.R;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AccountsActivity extends AppCompatActivity {

    private static final String TAG = "AccountsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "lauching this activity");
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "going to AddDebtActivity");
                startActivity(new Intent(AccountsActivity.this, AddDebtActivity.class));
            }
        });
    }

    public Context getActivity() {
        return AccountsActivity.this;
    }
}