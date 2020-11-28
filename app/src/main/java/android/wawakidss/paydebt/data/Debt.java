package android.wawakidss.paydebt.data;

import android.icu.util.Calendar;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Debt {

    private UUID id;
    private String debt;
    private Calendar takenAt;
    private String comment;
    private String agent;
    public enum Period {
        WEEK, MONTH, YEAR
    }
    private Period period;

    public Debt(Calendar takenAt, String debt, String comment, String agent) {
        id = UUID.randomUUID();
        this.takenAt = takenAt;
        this.comment = comment;
        this.agent = agent;
        this.debt = debt;
    }
}
