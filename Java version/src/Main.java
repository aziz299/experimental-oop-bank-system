import useres.BankAccount;
import useres.ExecutiveAccount;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sca = new Scanner(System.in);

        ArrayList<BankAccount> accounts = new ArrayList<>();
        ArrayList<ExecutiveAccount> ex_users = new ArrayList<>();

        boolean app_running = true;

        while (app_running) {
            System.out.print("\n[To open a new account Press (1)], [To login press (2)], [Executive (ex)]: ");
            String w = sca.next();

            switch (w) {
                case "1":
                    while (true) {
                        System.out.print("Enter the Username: ");
                        String new_id = sca.next();
                        System.out.print("Enter the Password: ");
                        String password = sca.next();

                        boolean exists = accounts.stream().anyMatch(acc -> acc.getAccount_id().equals(new_id));

                        if (exists) {
                            System.out.println("The username is already taken. Try again.\n");
                        } else {
                            accounts.add(new BankAccount(new_id, password));
                            System.out.println("Account created successfully!");
                            break;
                        }
                    }
                    break;

                case "2":
                    System.out.print("Enter the Username: ");
                    String user_id = sca.next();
                    System.out.print("Enter the Password: ");
                    String password = sca.next();

                    BankAccount account = null;
                    for (BankAccount acc : accounts) {
                        if (acc.getAccount_id().equals(user_id)) {
                            account = acc;
                            break;
                        }
                    }

                    if (account != null && account.Pt(password)) {
                        System.out.println("Login successful");
                        System.out.print("Current Balance: ");
                        account.get_amount();
                        System.out.println(" $");

                        System.out.print("[To deposit press 1], [To withdraw press 2], [To logout press l]: ");
                        String w2 = sca.next();

                        switch (w2) {
                            case "1":
                                System.out.print("Enter cash amount: ");
                                double cash = sca.nextDouble();
                                account.deposit(cash);
                                System.out.print("New Balance: ");
                                account.get_amount();
                                System.out.println(" $");
                                break;

                            case "2":
                                System.out.print("Enter withdrawal amount: ");
                                double amount = sca.nextDouble();
                                if (account.withdraw(amount)) {
                                    System.out.println("Transaction complete. Take your cash.");
                                } else {
                                    System.out.println("Insufficient balance.");
                                }
                                break;

                            case "l":
                                break;

                            default:
                                System.out.println("Wrong input.");
                                break;
                        }
                    } else {
                        System.out.println("Invalid credentials.");
                    }
                    break;

                case "ex":
                    System.out.print("[To login press (1)], [To open a new Executive account press (2)]: ");
                    String w3 = sca.next();

                    switch (w3) {
                        case "1":
                            System.out.print("Enter ex_Username: ");
                            String ex_id = sca.next();
                            System.out.print("Enter ex_Password: ");
                            String ex_pass = sca.next();

                            ExecutiveAccount ex_account = null;
                            for (ExecutiveAccount ex : ex_users) {
                                if (ex.getAccount_id().equals(ex_id)) {
                                    ex_account = ex;
                                    break;
                                }
                            }

                            if (ex_account != null && ex_account.Pt(ex_pass)) {
                                System.out.println("Executive Login successful");
                                System.out.print("Action (type 'Close' to exit app): ");
                                String doAction = sca.next();
                                if (doAction.equalsIgnoreCase("Close")) {
                                    app_running = false;
                                }
                            } else {
                                System.out.println("Invalid executive credentials.");
                            }
                            break;

                        case "2":
                            double[] ex_key = new double[12];
                            System.out.println("Enter 12 numeric key values:");
                            for (int i = 0; i < 12; i++) {
                                System.out.print("Enter key value " + (i + 1) + ": ");
                                ex_key[i] = sca.nextDouble();
                            }

                            // تعريف الأوبجكت المؤقت لحل الخطأ
                            ExecutiveAccount tempEx = new ExecutiveAccount("", "");

                            if (tempEx.verify_key(ex_key)){
                                while (true) {
                                    System.out.print("Enter Executive Username: ");
                                    String new_ex_id = sca.next();
                                    System.out.print("Enter Executive Password: ");
                                    String new_ex_pass = sca.next();

                                    boolean exists = ex_users.stream().anyMatch(ex -> ex.getAccount_id().equals(new_ex_id));

                                    if (exists) {
                                        System.out.println("The username is already taken.\n");
                                    } else {
                                        ex_users.add(new ExecutiveAccount(new_ex_id, new_ex_pass));
                                        System.out.println("Executive Account created successfully!");
                                        break;
                                    }
                                }
                            } else {
                                System.out.println("Invalid Key sum authorization failed.");
                            }
                            break;

                        default:
                            System.out.println("Wrong input.");
                            break;
                    }
                    break;

                default:
                    System.out.println("Wrong input. Try again.");
                    break;
            }
        }
        sca.close();
    }
}