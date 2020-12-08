package android.wawakidss.paydebt.accounts;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.wawakidss.paydebt.R;
import android.wawakidss.paydebt.data.Debt;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class DebtFragment extends Fragment {

    private Debt debt;
    private TextInputEditText whatToRepay;
    private TextInputEditText agent;
    private TextInputEditText comment;
    private Button dateButton;
    private static final String TAG = "DebtFragment";
    private Calendar repayment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        debt = new Debt();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_debt, container, false);
        whatToRepay = (TextInputEditText)v.findViewById(R.id.debt);
        whatToRepay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        dateButton = (Button)v.findViewById(R.id.date_button);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repayment = Calendar.getInstance();
                int year, month, day;
                year = repayment.get(Calendar.YEAR);
                month = repayment.get(Calendar.MONTH);
                day = repayment.get(Calendar.DAY_OF_MONTH);
                final long today = System.currentTimeMillis() - 1000;

                final DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int month, int day) {
                                dateButton.setText(day + "-" + (month + 1) + "-" + year);
                                repayment.set(year, month, day);
                                String date = (new Integer(year).toString() + "-" + new Integer(month).toString() + "-" + new Integer(day).toString());
                                Log.d(TAG, "setted date: " + date);
                            }
                        }, year, month, day);
                datePickerDialog.getDatePicker().setMinDate(today);
                datePickerDialog.show();
            }
        });

        return v;
    }
}
