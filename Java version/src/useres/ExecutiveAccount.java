package useres;

public class ExecutiveAccount extends user{
    public ExecutiveAccount (String id , String pasward){
        this.account_id= id;
        this.passwordd=pasward;
        }
    public boolean verify_key(double[] key) {
        int sum = 0;
        for (int i = 0; i < key.length; i++) {
            sum += key[i];
        }
        return Math.abs(sum - 12) < 1e-9;;
     }

    }

