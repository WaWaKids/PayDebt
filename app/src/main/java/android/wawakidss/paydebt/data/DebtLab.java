package android.wawakidss.paydebt.data;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DebtLab {

    private static DebtLab debtLab;
    private List<Debt> debts;

    public static DebtLab get(Context context) {
        if (debtLab == null)
            debtLab = new DebtLab(context);
        return debtLab;
    }

    private DebtLab(Context context) {
        debts = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Debt debt = new Debt();
            debt.setComment("Debt #" + i);
            debts.add(debt);
        }
    }

    public List<Debt> getDebts() {
        return debts;
    }

    public Debt getDebt(UUID id) {
        for (Debt debt: debts) {
            if (debt.getUuid().equals(id))
                return debt;
        }
        return null;
    }
}
