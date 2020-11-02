package android.wawakidss.paydebt.ui.accounts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.wawakidss.paydebt.R;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class AccountsFragment extends Fragment {

    private AccountsViewModel mAccountsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mAccountsViewModel =
                ViewModelProviders.of(this).get(AccountsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_accounts, container, false);
        final TextView textView = root.findViewById(R.id.text_accounts);
        mAccountsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}