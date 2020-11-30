package android.wawakidss.paydebt.accounts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.wawakidss.paydebt.R;
import android.wawakidss.paydebt.data.Debt;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

public class DebtFragment extends Fragment {

    private Debt debt;
    private TextInputEditText whatToRepay;
    private TextInputEditText agent;
    private TextInputEditText comment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        debt = new Debt();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_debt, container, false);


        return v;
    }
}
