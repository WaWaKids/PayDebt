package android.wawakidss.paydebt.data;

import android.icu.util.Calendar;

public abstract class Debt {

    private int sum;
    private Calendar takenAt;
    private Calendar repayTill;
    private String comment;
}
