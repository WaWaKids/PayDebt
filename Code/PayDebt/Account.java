package PayDebt;
import java.sql.Date;

abstract public class Account {


    private int balance_cash;   //Денежный баланс
    private Date dateCreate;    //Дата создания
    private Date dateEnd;   //Дата погашения
    private int ID_debtor;    //ID должника
    private boolean type_account;
    private int periodicity_payment;    //Периодичность платежа
    private int ID_account; //ID Счёта
    private int Direction; //Направление

    static int counterAccount;
    //ЧТО ЗА ЗНАЧЕНИЕ ПЛАТЕЖЕЙ?????


    Account(){ }
    Account( int b_cash, Date d_Create , Date d_End, int ID_debtor, boolean type_acc, int period, int ID_acc, int Direct) {
        this.balance_cash = b_cash;
        this.dateCreate = d_Create;
        this.dateEnd = d_End;
        this.ID_debtor = ID_debtor;
        this.type_account = type_acc;
        this.periodicity_payment = period;
        this.ID_account = ID_acc;
        this.Direction = Direct;
        counterAccount++;
    }
    public void setBalance_cash(int balance_cash) { this.balance_cash = balance_cash; }
    public void setDateCreate(Date dateCreate) { this.dateCreate = dateCreate; }
    public void setDateEnd(Date dateEnd) { this.dateEnd = dateEnd; }
    public void setID_debtor(int ID_debtor) { this.ID_debtor = ID_debtor; }
    public void setType_account(boolean type_account) { this.type_account = type_account; }
    public void setPeriodicity_payment(int periodicity_payment) { this.periodicity_payment = periodicity_payment; }
    public void setID_account(int ID_account) { this.ID_account = ID_account; }
    public void setDirection(int direction) { Direction = direction; }

    public int getBalance_cash() { return balance_cash; }
    public Date getDateCreate() { return dateCreate; }
    public Date getDateEnd() { return dateEnd; }
    public int getID_debtor() { return ID_debtor; }
    public boolean isType_account() { return type_account; }
    public int getPeriodicity_payment() { return periodicity_payment; }
    public int getID_account() { return ID_account; }
    public int getDirection() { return Direction; }

    abstract public void CreateAccount();                       // Создание_счета
    abstract public void EditAccount( int ID );                 // Редактирование_счёта
    abstract public void DeleteAccount (int ID);                // Удаление_счета
    abstract public void CheckAccount (int ID);                 // Просмотр_счёта
    abstract public void ChangeInCashBalance (int ID, int sum); // Изменение_баланса
}
