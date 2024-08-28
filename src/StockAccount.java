import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class CompanyShares {
    private final String stockSymbol;
    private int numberOfShares;
    private Date transactionDateTime;

    public CompanyShares(String stockSymbol, int numberOfShares) {
        this.stockSymbol = stockSymbol;
        this.numberOfShares = numberOfShares;
        this.transactionDateTime = new Date();
    }

    // Getters and setters
    public String getStockSymbol() { return stockSymbol; }
    public int getNumberOfShares() { return numberOfShares; }
    public Date getTransactionDateTime() { return transactionDateTime; }

    public void setNumberOfShares(int numberOfShares) {
        this.numberOfShares = numberOfShares;
        this.transactionDateTime = new Date();
    }
}

public class StockAccount {
    private final String customerName;
    private double balance;
    private final List<CompanyShares> sharesList;

    public StockAccount(String customerName, double initialBalance) {
        this.customerName = customerName;
        this.balance = initialBalance;
        this.sharesList = new ArrayList<>();
    }

    public void buy(String symbol, int shares, double price) {
        double cost = shares * price;
        if (cost > balance) {
            System.out.println("Insufficient funds to complete the transaction.");
            return;
        }

        CompanyShares existingShares = findSharesBySymbol(symbol);
        if (existingShares != null) {
            existingShares.setNumberOfShares(existingShares.getNumberOfShares() + shares);
        } else {
            sharesList.add(new CompanyShares(symbol, shares));
        }

        balance -= cost;
        System.out.println("Bought " + shares + " shares of " + symbol + " for $" + cost);
    }

    public void sell(String symbol, int shares, double price) {
        CompanyShares existingShares = findSharesBySymbol(symbol);
        if (existingShares == null || existingShares.getNumberOfShares() < shares) {
            System.out.println("Insufficient shares to complete the transaction.");
            return;
        }

        double earnings = shares * price;
        existingShares.setNumberOfShares(existingShares.getNumberOfShares() - shares);

        if (existingShares.getNumberOfShares() == 0) {
            sharesList.remove(existingShares);
        }

        balance += earnings;
        System.out.println("Sold " + shares + " shares of " + symbol + " for $" + earnings);
    }

    public void printReport() {
        System.out.println("Stock Account Report for " + customerName);
        System.out.println("Current Balance: $" + balance);
        System.out.println("Shares owned:");
        for (CompanyShares shares : sharesList) {
            System.out.println(shares.getStockSymbol() + ": " + shares.getNumberOfShares() +
                    " (Last transaction: " + shares.getTransactionDateTime() + ")");
        }
    }

    private CompanyShares findSharesBySymbol(String symbol) {
        for (CompanyShares shares : sharesList) {
            if (shares.getStockSymbol().equals(symbol)) {
                return shares;
            }
        }
        return null;
    }

    // Main method for testing
    public static void main(String[] args) {
        StockAccount account = new StockAccount("John Doe", 10000);

        account.buy("Apple", 10, 150);
        account.buy("Google", 5, 2000);
        account.printReport();

        account.sell("Apple", 5, 160);
        account.buy("Apple", 2, 155);
        account.printReport();
    }
}