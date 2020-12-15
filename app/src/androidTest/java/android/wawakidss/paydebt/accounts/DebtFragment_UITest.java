package android.wawakidss.paydebt.accounts;

import android.wawakidss.paydebt.R;

import androidx.test.espresso.ViewAssertion;

import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class DebtFragment_UITest {

    @Test
    public void DebtFragment_isCorrect() {
        onView(withId(R.id.button_delete)).check((ViewAssertion) isClickable());
    }
}
