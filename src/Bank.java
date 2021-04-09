import java.util.ArrayList;
import java.util.regex.Pattern;

public class Bank {

    private String bankName;
    private String branchLocation;
    private ArrayList<Account> accountArrayList = new ArrayList<>();;

    public static enum BranchLocations {
        TORONTO,BARRIE,COLLINGWOOD,BRAMPTON,ORANGEVILLE
    };

    public Bank() {
        this.bankName = "Scotia Bank";
        this.branchLocation = "";
        this.accountArrayList = new ArrayList<>();
    }

    public Bank(String bankName, String branchLocation) {
        if (BankNameChecker(bankName)) {
            this.bankName = bankName;
        } else {
            this.bankName = "Scotia Bank";
        }
        if (checkingBankLocationString(branchLocation)) {
            this.branchLocation = branchLocation;
        } else {
            this.branchLocation = String.valueOf(BranchLocations.COLLINGWOOD);
        }
    }

    public Bank(String bankName, BranchLocations branchLocation) {
        if (BankNameChecker(bankName)) {
            this.bankName = bankName;
        } else {
            this.bankName = "ROYAL BANK OF CANADA";
        }
        if (checkBankLocationENUM(branchLocation)) {
            this.branchLocation = String.valueOf(branchLocation);
        } else {
            this.branchLocation = String.valueOf(BranchLocations.TORONTO);
        }
    }

    public String getBankName() {
        return this.bankName;
    }

    public boolean setBankName(String bankName) {
        if (BankNameChecker(bankName)) {
            this.bankName = bankName;
            return true;
        }
        return false;
    }

    public boolean setBranchLocation(String branchLocation) {
        if (checkingBankLocationString(branchLocation)) {
            this.branchLocation = branchLocation;
            return true;
        }
        return false;
    }

    public String getBranchLocation() {
        return branchLocation;
    }

    public boolean setBranchLocation(BranchLocations branchLocation) {
        if (checkBankLocationENUM(branchLocation)) {
            this.branchLocation = String.valueOf(branchLocation);
            return true;
        }
        return false;
    }

    public Account getAccountByNumber(int accountNumber) {
        for (int i = 0; i < this.accountArrayList.size(); i++) {
            if (this.accountArrayList.get(i).getAccountNumber() == accountNumber) {
                return this.accountArrayList.get(i);
            }
        }
        return new Account();
    }

    public boolean addAccount(Account account) {
        boolean temp = false;
        for (int i = 0; i < this.accountArrayList.size(); i++) {
            if (this.accountArrayList.get(i).getAccountNumber() == account.getAccountNumber()) {
                temp = true;
            }
        }
        if (!temp) {
            this.accountArrayList.add(account);
            return true;
        } else {
            return false;
        }
    }

    public boolean addAccount(String accountName, int accountNumber, double accountBalance) {
        boolean found = false;
        for (int i = 0; i < this.accountArrayList.size(); i++) {
            if (this.accountArrayList.get(i).getAccountNumber() == accountNumber) {
                found = true;
            }
        }
        if (!found) {
            this.accountArrayList.add(new Account(accountName, accountNumber, accountBalance));
            return true;
        } else {
            return false;
        }
    }

    public Account viewAccount(int accountNumber) {
        for (int i = 0; i < this.accountArrayList.size(); i++) {
            if (this.accountArrayList.get(i).getAccountNumber() == accountNumber) {
                return this.accountArrayList.get(i);
            }
        }
        return new Account();
    }

    public Account viewAccount(byte index) {
        try {
            Account a = accountArrayList.get(index);
            return a;
        } catch (Exception ex) {
            return new Account();
        }
    }

    public boolean modifyAccount(int accountNumber, String accountName) {
        for (int i = 0; i < this.accountArrayList.size(); i++) {
            if (this.accountArrayList.get(i).getAccountNumber() == accountNumber) {
                return this.accountArrayList.get(i).setAccountName(accountName);
            }
        }
        return false;
    }

    public boolean modifyAccount(int accountNumber, double accountBalance) {
        for (int i = 0; i < this.accountArrayList.size(); i++) {
            if (this.accountArrayList.get(i).getAccountNumber() == accountNumber) {
                return this.accountArrayList.get(i).setAccountBalance(accountBalance);
            }
        }
        return false;
    }

    public boolean modifyAccount(int accountNumber, String accountName, double accountBalance) {
        for (int i = 0; i < this.accountArrayList.size(); i++) {
            if (this.accountArrayList.get(i).getAccountNumber() == accountNumber) {
                if (this.accountArrayList.get(i).setAccountName(accountName)
                        && this.accountArrayList.get(i).setAccountBalance(accountBalance)) {
                    this.accountArrayList.get(i).setAccountName(accountName);
                    this.accountArrayList.get(i).setAccountBalance(accountBalance);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean modifyAccount(byte index, String accountName) {
        try {
            return this.accountArrayList.get(index).setAccountName(accountName);
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean modifyAccount(byte index, double accountBalance) {
        try {
            return this.accountArrayList.get(index).setAccountBalance(accountBalance);
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean modifyAccount(byte index, String accountName, double accountBalance) {
        try {
            if (this.accountArrayList.get(index).setAccountName(accountName)
                    && this.accountArrayList.get(index).setAccountBalance(accountBalance)) {
                this.accountArrayList.get(index).setAccountName(accountName);
                this.accountArrayList.get(index).setAccountBalance(accountBalance);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteAccount(int accountNumber) {
        boolean found = false;
        int index = 0;
        for (int i = 0; i < this.accountArrayList.size(); i++) {
            if (this.accountArrayList.get(i).getAccountNumber() == accountNumber) {
                index = i;
                found = true;
            }
        }
        if (found) {
            this.accountArrayList.remove(index);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteAccount(byte index) {
        try {
            this.accountArrayList.remove(index);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private boolean BankNameChecker(String bankName) {
        boolean b1 = Pattern.matches("[A-Za-z0-9\\&\\s]*", bankName);
        boolean b2 = bankName.length() >= 8;
        return b1 && b2;
    }

    private boolean checkingBankLocationString(String bankName) {
        for (BranchLocations bn : BranchLocations.values()) {
            if (String.valueOf(bn).equals(bankName)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkBankLocationENUM(BranchLocations bankName) {
        for (BranchLocations bn : BranchLocations.values()) {
            if (bn == bankName) {
                return true;
            }
        }
        return false;
    }

}
