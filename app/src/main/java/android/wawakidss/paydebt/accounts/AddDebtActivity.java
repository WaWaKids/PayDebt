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

import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDate;
import java.util.Calendar;

public class AddDebtActivity extends AppCompatActivity {

    private static final String TAG = "AddDebtActivity";
    Button dateButton;
    RadioGroup period;
    ImageView confirmButton;
    TextInputEditText debt;
    TextInputEditText agent;
    TextInputEditText comment;
    TextView textFirstRepayment;
    Calendar repayment;
    Calendar today;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_debt);
        Log.d(TAG, "launching the activity");
        confirmButton = (ImageView)findViewById(R.id.button_confirm);
        textFirstRepayment = (TextView)findViewById(R.id.text_first_repayment);
        period = (RadioGroup)findViewById(R.id.period);
        textFirstRepayment.setText(R.string.text_when_to_repay);
        debt = (TextInputEditText)findViewById(R.id.debt);
        agent = (TextInputEditText)findViewById(R.id.agent);
        comment = (TextInputEditText)findViewById(R.id.comment);
        period.setVisibility(View.GONE);
        dateButton = (Button)findViewById(R.id.dateButton);
        today = Calendar.getInstance();

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repayment = Calendar.getInstance();
                int year, month, day;
                year = repayment.get(Calendar.YEAR);
                month = repayment.get(Calendar.MONTH);
                day = repayment.get(Calendar.DAY_OF_MONTH);
                final long today = System.currentTimeMillis() - 1000;

                final DatePickerDialog datePickerDialog = new DatePickerDialog(AddDebtActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int month, int day) {
                                dateButton.setText(day + "-" + (month + 1) + "-" + year);
                                repayment.set(year, month, day);
                            }
                        }, year, month, day);
                datePickerDialog.getDatePicker().setMinDate(today);
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