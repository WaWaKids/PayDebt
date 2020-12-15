package android.wawakidss.paydebt.accounts;

import android.wawakidss.paydebt.R;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class AddDebt_UITest {

    @Rule
    public ActivityTestRule<AddDebtActivity> mAccountsActivityActivityTestRule = new ActivityTestRule<>(AddDebtActivity.class);

    @Test
    public void radioGroup1_isCorrect() {

        onView(withId(R.id.i_owe)).check(matches(withText(R.string.i_owe)));
        onView(withId(R.id.i_owe)).check(matches(isClickable()));
        onView(withId(R.id.owe_to_me)).check(matches(withText(R.string.owe_to_me)));
        onView(withId(R.id.owe_to_me)).check(matches(isClickable()));
    }

    @Test
    public void textInputEditTextDebt_isCorrect() {
        onView(withId(R.id.debt)).check(matches(isClickable()));
        onView(withId(R.id.debt)).perform(typeText("Something"));
        onView(withId(R.id.debt)).check(matches(withText("Something")));
    }

    @Test
    public void textInputEditTextAgent_isCorrect() {
        onView(withId(R.id.agent)).check(matches(isClickable()));
        onView(withId(R.id.agent)).perform(typeText("Something"));
        onView(withId(R.id.agent)).check(matches(withText("Something")));
    }

    @Test
    public void textInputEditTextComment_isCorrect() {
        onView(withId(R.id.comment)).check(matches(isClickable()));
        onView(withId(R.id.comment)).perform(typeText("Something"));
        onView(withId(R.id.comment)).check(matches(withText("Something")));
    }

    @Test
    public void textIMustRepay_isCorrect() {
        onView(withId(R.id.i_must_repay)).check(matches(withText(R.string.i_must_repay)));
    }

    @Test
    public void date_button_isCorrect() {
        onView(withId(R.id.set_date)).check(matches(withText(R.string.set_date)));
        onView(withId(R.id.set_date)).check(matches(isClickable()));
    }

    @Test
    public void textPaymentPeriod_isCorrect() {
        onView(withId(R.id.text_payment_period)).check(matches(withText(R.string.text_payment_period)));
    }

    @Test
    public void buttonConfirm_isCorrect() {
        onView(withId(R.id.button_confirm)).check(matches(isClickable()));
    }
}
