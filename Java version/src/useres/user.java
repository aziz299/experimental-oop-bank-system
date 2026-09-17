package useres;
public abstract class user {
    String account_id;

    protected String passwordd;

    public boolean Pt(String p) {
        return (p.equals(passwordd));
    }

    public String getAccount_id() {
        return account_id;
    }
}
