package android.wawakidss.paydebt.data;

public class DebtDbSchema {

    public static final class DebtTable {
        public static final String NAME = "Debts";
        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String ATTACHMENT = "attachment";
            public static final String DEBT = "debt";
            public static final String AGENT = "agent";
            public static final String DATE = "date";
            public static final String REPAYMENT = "repayment";
            public static final String PERIOD = "period";
        }
    }
}