class AccountBad {
    protected double balance;
    protected double interestRate;
    
    public AccountBad(double initialBalance, double interestRate) {
        this.balance = initialBalance;
        this.interestRate = interestRate;
    }
    
    public void deposit(double amount) {
        balance += amount;
    }
    
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        }
    }
    
    public double getBalance() {
        return balance;
    }
}

class FixedDepositAccountBad extends AccountBad {
    private long maturityDate;
    private long creationDate;
    
    public FixedDepositAccountBad(double initialBalance, double interestRate, long maturityDate) {
        super(initialBalance, interestRate);
        this.creationDate = System.currentTimeMillis();
        this.maturityDate = maturityDate;
    }
    
    @Override
    public void withdraw(double amount) {
        if (System.currentTimeMillis() < maturityDate) {
            throw new IllegalStateException("Cannot withdraw before maturity date!");
        }
        super.withdraw(amount);
    }
}

abstract class Account {
    protected double balance;
    protected double interestRate;
    
    public Account(double initialBalance, double interestRate) {
        this.balance = initialBalance;
        this.interestRate = interestRate;
    }
    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: ₹" + amount + ", New balance: ₹" + balance);
        }
    }
    
    public abstract boolean canWithdraw(double amount);
    
    public abstract void withdraw(double amount);
    
    public double getBalance() {
        return balance;
    }
    
    public void applyInterest() {
        balance += balance * interestRate;
    }
}

class SavingsAccount extends Account {
    private static final double MINIMUM_BALANCE = 1000;
    
    public SavingsAccount(double initialBalance, double interestRate) {
        super(initialBalance, interestRate);
    }
    
    @Override
    public boolean canWithdraw(double amount) {
        return (balance - amount) >= MINIMUM_BALANCE;
    }
    
    @Override
    public void withdraw(double amount) {
        if (canWithdraw(amount)) {
            balance -= amount;
            System.out.println("Withdrawn: ₹" + amount + ", New balance: ₹" + balance);
        } else {
            System.out.println("Withdrawal denied. Minimum balance of ₹" + MINIMUM_BALANCE + " must be maintained.");
        }
    }
}

class CheckingAccount extends Account {
    private double overdraftLimit;
    
    public CheckingAccount(double initialBalance, double interestRate, double overdraftLimit) {
        super(initialBalance, interestRate);
        this.overdraftLimit = overdraftLimit;
    }
    
    @Override
    public boolean canWithdraw(double amount) {
        return (balance - amount) >= -overdraftLimit;
    }
    
    @Override
    public void withdraw(double amount) {
        if (canWithdraw(amount)) {
            balance -= amount;
            System.out.println("Withdrawn: ₹" + amount + ", New balance: ₹" + balance);
        } else {
            System.out.println("Withdrawal denied. Exceeds overdraft limit of ₹" + overdraftLimit);
        }
    }
}

class FixedDepositAccount {
    private double balance;
    private double interestRate;
    private long maturityDate;
    private long creationDate;
    
    public FixedDepositAccount(double initialBalance, double interestRate, long maturityDays) {
        this.balance = initialBalance;
        this.interestRate = interestRate;
        this.creationDate = System.currentTimeMillis();
        this.maturityDate = creationDate + (maturityDays * 24 * 60 * 60 * 1000);
    }
    
    public void deposit(double amount) {
        System.out.println("Cannot add more funds to fixed deposit account.");
    }
    
    public boolean canWithdraw() {
        return System.currentTimeMillis() >= maturityDate;
    }
    
    public void withdrawAtMaturity() {
        if (canWithdraw()) {
            System.out.println("Maturity reached. Balance with interest: ₹" + (balance * (1 + interestRate)));
        } else {
            long daysRemaining = (maturityDate - System.currentTimeMillis()) / (24 * 60 * 60 * 1000);
            System.out.println("Fixed deposit still locked. Days remaining: " + daysRemaining);
        }
    }
    
    public double getBalance() {
        return balance;
    }
}

public class LSP_BankingSystem {
    public static void main(String[] args) {
        System.out.println("===== Liskov Substitution Principle (LSP) =====\n");
        
        Account savingsAccount = new SavingsAccount(5000, 0.02);
        Account checkingAccount = new CheckingAccount(2000, 0.01, 500);
        
        System.out.println("--- Savings Account ---");
        savingsAccount.deposit(1000);
        savingsAccount.withdraw(500);
        savingsAccount.withdraw(5000);
        
        System.out.println("\n--- Checking Account ---");
        checkingAccount.deposit(500);
        checkingAccount.withdraw(2000);
        checkingAccount.withdraw(1000);
        
        System.out.println("\n--- Fixed Deposit Account (Separate Abstraction) ---");
        FixedDepositAccount fdAccount = new FixedDepositAccount(10000, 0.05, 365);
        fdAccount.deposit(1000);
        fdAccount.withdrawAtMaturity();
    }
}
