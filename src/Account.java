import java.util.regex.Pattern;

public class Account {

    private String accountName;
    private int accountNumber;
    private double accountBalance;

    public Account() {
        this.accountName = "";
        this.accountNumber = 0;
        this.accountBalance = 0.0;
    }

    public Account(String accountName, int accountNumber, double accountBalance) {
        if (checkAccountName(accountName)) {
            this.accountName = accountName;
        } else {
            this.accountName = "";
        }
        if (checkAccountNumber(accountNumber)) {
            this.accountNumber = accountNumber;
        } else {
            this.accountNumber = 0;
        }
        if (checkAccountBalance(accountBalance)) {
            this.accountBalance = accountBalance;
        } else {
            this.accountBalance = 0.0;
        }
    }

    public String getAccountName() {
        return this.accountName;
    }

    public boolean setAccountName(String accountName) {
        if (checkAccountName(accountName)) {
            this.accountName = accountName;
            return true;
        }
        return false;
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    public boolean setAccountNumber(int accountNumber) {
        if (checkAccountNumber(accountNumber)) {
            this.accountNumber = accountNumber;
            return true;
        }
        return false;
    }

    public double getAccountBalance() {
        return this.accountBalance;
    }

    public boolean setAccountBalance(double value) {
        if (checkAccountBalance(value)) {
            this.accountBalance = value;
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Account) {
            Account account = (Account) obj;
            if (account.getAccountBalance() == this.accountBalance
                    && account.getAccountName().equals(this.accountName)
                    && account.getAccountNumber() == this.accountNumber) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Account Name: " + this.accountName + ", Account Number: " + this.accountNumber + ", Account Balance: " + this.accountBalance;
    }

    public boolean checkAccountNumber(int accountNumber) {
        return String.valueOf(accountNumber).length() >= 5
                && String.valueOf(accountNumber).length() <= 9;
    }

    public boolean checkAccountName(String accountName) {
        boolean b1 = Pattern.matches("[A-Za-z\\-\\'\\s]*", accountName);
        boolean b2 = accountName.length() >= 4;
        return b1 && b2;
    }

    public boolean checkAccountBalance(double accountBalance) {
        String[] accountBalanceStr = String.valueOf(accountBalance).split("\\.");
        if (accountBalanceStr.length == 1) {
            if (!".".equals(accountBalanceStr[0]) && Integer.parseInt(accountBalanceStr[0]) >= 0) {
                return true;
            }
        }
        if (accountBalanceStr.length == 2) {
            if (!".".equals(accountBalanceStr[0]) && accountBalanceStr[1].length() <= 2) {
                return true;
            }
        }
        if (accountBalanceStr.length == 3) {
            return accountBalanceStr[0].length() <= 2;
        }
        return false;
    }

}