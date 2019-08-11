package by.epam.ft.entity;

public class Hr extends MyEntity {
    private int idHr;
    private int idAccount;

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public int getIdHr() {
        return idHr;
    }

    public void setIdHr(int idHr) {
        this.idHr = idHr;
    }
}
