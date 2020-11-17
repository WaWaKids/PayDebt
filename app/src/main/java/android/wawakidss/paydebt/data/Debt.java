package android.wawakidss.paydebt.data;

import android.icu.util.Calendar;

import java.util.UUID;

public abstract class Debt {

    private UUID id;
    private int sum;
    private Calendar takenAt;
    private Calendar repayTill;
    private String comment;

    public Debt() {
        id = UUID.randomUUID();
        takenAt = Calendar.getInstance();
    }
}
