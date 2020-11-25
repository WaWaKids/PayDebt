package android.wawakidss.paydebt.accounts;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.wawakidss.paydebt.R;
import android.wawakidss.paydebt.accounts.tabs.PagerAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class AccountsActivity extends AppCompatActivity {

    private static final String TAG = "AccountsActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "lauching this activity");
        FloatingActionButton fab = findViewById(R.id.fab);
        TabLayout tabs = (TabLayout)findViewById(R.id.tabs);
        TabItem i_owe_to = (TabItem)findViewById(R.id.i_owe);
        TabItem owe_to_me = (TabItem)findViewById(R.id.owe_to_me);
        ViewPager viewPager = (ViewPager)findViewById(R.id.viewPager);

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabs.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Going to AddDebtActivity");
                startActivity(new Intent(AccountsActivity.this, AddDebtActivity.class));
            }
        });
    }
}