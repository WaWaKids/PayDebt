package PayDebt;

abstract public class Agent {

    private int ID_deptor;
    private String name;
    private int counterDept;
    private int counterUnpaidDebt;
    private int counterPaidDebt;
    private int phoneNumber;

    public int getID_deptor() { return ID_deptor; }
    public String getName() { return name;}
    public int getCounterDept() { return counterDept; }
    public int getCounterUnpaidDebt() { return counterUnpaidDebt; }
    public int getCounterPaidDebt() { return counterPaidDebt; }
    public int getPhoneNumber() { return phoneNumber; }

    public void setID_deptor(int ID_deptor) { this.ID_deptor = ID_deptor; }
    public void setName(String name) { this.name = name; }
    public void setCounterDept(int counterDept) { this.counterDept = counterDept; }
    public void setCounterUnpaidDebt(int counterUnpaidDebt) { this.counterUnpaidDebt = counterUnpaidDebt; }
    public void setCounterPaidDebt(int counterPaidDebt) { this.counterPaidDebt = counterPaidDebt; }
    public void setPhoneNumber(int phoneNumber) { this.phoneNumber = phoneNumber; }
}
