class Account:
    def __init__(self, account_id, password):
        self.id = account_id
        self.__password = password

    def check_password(self, p):
        return p == self.__password


class BankAccount(Account):
    def __init__(self, account_id, password, amount=0.0):
        super().__init__(account_id, password)
        self.__amount = amount

    def get_balance(self):
        return self.__amount

    def deposit(self, cash):
        self.__amount += cash

    def withdraw(self, cash):
        if cash <= self.__amount:
            self.__amount -= cash
            return True
        return False


class ExecutiveAccount(Account):
    def __init__(self, account_id, password):
        super().__init__(account_id, password)


def verify_key(key):
    return sum(key) == 12


users = []
ex_users = []
app_running = True

while app_running:
    w = input("\n[To open a new account Press (1)], [To login press (2)], [Executive (ex)]: ")

    if w == "1":
        while True:
            new_id = input("Enter the Username: ")
            password = input("Enter the Password: ")
            
            if any(b.id == new_id for b in users):
                print("The username is already taken. Try again.\n")
            else:
                users.append(BankAccount(new_id, password, 0.0))
                print("Account created successfully!")
                break

    elif w == "2":
        user_id = input("Enter the Username: ")
        password = input("Enter the Password: ")
        
        account = next((n for n in users if n.id == user_id), None)
        
        if account and account.check_password(password):
            print("Login successful")
            print(f"Current Balance: {account.get_balance()} $")
            
            w2 = input("[To deposit press 1], [To withdraw press 2], [To logout press l]: ")
            if w2 == "1":
                cash = float(input("Enter cash amount: "))
                account.deposit(cash)
                print(f"New Balance: {account.get_balance()} $")
            elif w2 == "2":
                amount = float(input("Enter withdrawal amount: "))
                if account.withdraw(amount):
                    print("Transaction complete. Take your cash.")
                else:
                    print("Insufficient balance.")
            elif w2 == "l":
                continue
            else:
                print("Wrong input.")
        else:
            print("Invalid credentials.")

    elif w == "ex":
        w3 = input("[To login press (1)], [To open a new Executive account press (2)]: ")
        
        if w3 == "1":
            user_id = input("Enter ex_Username: ")
            password = input("Enter ex_Password: ")
            account = next((n for n in ex_users if n.id == user_id), None)
            
            if account and account.check_password(password):
                print("Executive Login successful")
                do = input("Action (type 'Close' to exit app): ")
                if do.strip().lower() == "close":
                    app_running = False
            else:
                print("Invalid executive credentials.")

        elif w3 == "2":
            ex_key = []
            print("Enter 12 numeric key values:")
            for i in range(12):
                val = float(input(f"Enter key value {i+1}: "))
                ex_key.append(val)
                
            if verify_key(ex_key):
                while True:
                    new_id = input("Enter Executive Username: ")
                    password = input("Enter Executive Password: ")
                    
                    if any(b.id == new_id for b in ex_users):
                        print("The username is already taken.\n")
                    else:
                        ex_users.append(ExecutiveAccount(new_id, password))
                        print("Executive Account created successfully!")
                        break
            else:
                print("Invalid Key sum authorization failed.")

    else:
        print("Wrong input. Try again.")
