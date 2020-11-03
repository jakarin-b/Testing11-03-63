package int101.banking;

public class AccountHistory {
    private final AccountTransaction history[];
    private int count;

    public AccountHistory(int size) {
        history = new AccountTransaction[size>0 ? size :100];
    }
    
    public AccountHistory append(AccountTransaction trx) {
        if (count < history.length) {
            history[count++] = trx;
            return this;
        }
        return null;
    }

    @Override
    public String toString() {
        String s = "";
        for(int i = 0; i <= count; i++){
           s+= history[i].toString() + "\n ";
        }
        return s;
    }
}
