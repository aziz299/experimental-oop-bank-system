package useres;
public class BankAccount extends user {
    private double amount =0;
    public BankAccount (String id , String pasward){
        this.account_id= id;
        this.passwordd=pasward;

    }
    public void get_amount(){
        System.out.print(amount);

    }
    public void deposit(double c){
        amount =amount+ c;
    }
    public boolean withdraw(double c) {
        if (c<=amount) {
            amount =amount - c;
            return true ;
        }
        return false;


    }



}
