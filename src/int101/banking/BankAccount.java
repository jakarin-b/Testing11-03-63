package int101.banking;

import int101.base.Person;
import java.math.BigDecimal;

public class BankAccount {
    private static int nextAccountNo;
    private final int accountNo;
    private final String accountName;
    private final Person accountOwner;
    private AccountHistory history;
    private BigDecimal balance;

    public BankAccount(String accountName, Person accountOwner) {
        this.accountNo = nextAccountNo++;
        this.accountName = accountName;
        this.accountOwner = accountOwner;
        this.history = new AccountHistory(0);
        this.balance = new BigDecimal(0);
    }

    /* ToDo: 
       - call the above constructor to the the job.
       - use "firstname lastname" of accountOwner as the accountName;
    */
    public BankAccount(Person accountOwner) {
        this(accountOwner.getFirstname() + 
                " " + accountOwner.getLastname(), accountOwner);
    }
    
    public BankAccount deposit(double amount) {
        if (amount<=0) return null;
        BigDecimal changeTo = new BigDecimal(amount);
        balance = balance.add(new BigDecimal(amount));
        this.history.append(new AccountTransaction(TransactionType.DEPOSIT,changeTo));
        return this;
    }
    
    public BankAccount withdraw(double amount) {
        if (amount<=0) return null;
        if (balance.doubleValue()<amount) return null;
        BigDecimal changeTo = new BigDecimal(amount);
        balance = balance.subtract(new BigDecimal(amount));
       this.history.append(new AccountTransaction(TransactionType.WITHDRAW,changeTo));
        return this;
    }
    
    /* ToDo:
       - check if to Account is not null first.
       - try withdraw from this account first (call withdraw()); if fails, return null.
       - deposit to the other account (call deposit()); if fails, return null.
       - if everything is ok, return this (for method chaining).
    */
    public BankAccount transferTo(BankAccount to, double amount) {
        if (to==null) return null;
        if (withdraw(amount)==null) return null;
        BigDecimal changeTo = new BigDecimal(amount);
        to.deposit(amount);
        this.history.append(new AccountTransaction(TransactionType.TRANSFER_OUT, changeTo));
        to.history.append(new AccountTransaction(TransactionType.TRANSFER_IN, changeTo));
        return this;
    }

    public Person getAccountOwner() { return accountOwner; }

    @Override
    public String toString() {
        return "BankAccount[" + accountNo + ":" + accountName + "=" + balance + ']';
    }
    
}
