package android.wawakidss.paydebt.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DebtBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "debtBase.db";

    public DebtBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DebtDbSchema.DebtTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                DebtDbSchema.DebtTable.Cols.UUID + ", " +
                DebtDbSchema.DebtTable.Cols.ATTACHMENT + ", " +
                DebtDbSchema.DebtTable.Cols.DEBT + ", " +
                DebtDbSchema.DebtTable.Cols.AGENT + ", " +
                DebtDbSchema.DebtTable.Cols.DATE + ", " +
                DebtDbSchema.DebtTable.Cols.REPAYMENT + ", " +
                DebtDbSchema.DebtTable.Cols.PERIOD +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
