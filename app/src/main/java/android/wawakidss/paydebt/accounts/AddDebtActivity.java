package android.wawakidss.paydebt.accounts;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.wawakidss.paydebt.R;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddDebtActivity extends AppCompatActivity {

    private static final String TAG = "AddDebtActivity";
    Button dateButton;
    RadioGroup period;
    private int year, month, day;
    TextView textFirstRepayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_debt);
        Log.d(TAG, "launching the activity");
        ImageView confirmButton = (ImageView)findViewById(R.id.button_confirm);
        textFirstRepayment = (TextView)findViewById(R.id.text_first_repayment);
        period = (RadioGroup)findViewById(R.id.period);
        textFirstRepayment.setText(R.string.text_when_to_repay);
        period.setVisibility(View.GONE);
        dateButton = (Button)findViewById(R.id.dateButton);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddDebtActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                dateButton.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        RadioGroup duration = (RadioGroup)findViewById(R.id.radioGroup1);
        duration.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.once_only:
                        textFirstRepayment.setText(R.string.text_when_to_repay);
                        period.setVisibility(View.GONE);
                        break;

                    case R.id.continuous:
                        textFirstRepayment.setText(R.string.first_repayment_date);
                        period.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "saving the info, going back to AccountsActivity");
                startActivity(new Intent(AddDebtActivity.this, AccountsActivity.class));
            }
        });
    }
}