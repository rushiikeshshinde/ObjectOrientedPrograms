import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Stock {
    private final String name;
    private final int numShares;
    private final double price;

    public Stock(String name, int numShares, double price) {
        this.name = name;
        this.numShares = numShares;
        this.price = price;
    }

    public double calculateValue() {
        return numShares * price;
    }

    public String getName() {
        return name;
    }

    public int getNumShares() {
        return numShares;
    }

    public double getPrice() {
        return price;
    }
}

class StockPortfolio {
    private final List<Stock> stocks;

    public StockPortfolio() {
        stocks = new ArrayList<>();
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public double calculateTotalValue() {
        return stocks.stream().mapToDouble(Stock::calculateValue).sum();
    }

    public void printReport() {
        System.out.println("\nStock Report");
        System.out.println("-".repeat(65));
        System.out.printf("%-15s %-20s %-15s %-15s%n", "Stock Name", "Number of Shares", "Share Price", "Total Value");
        System.out.println("-".repeat(65));
        for (Stock stock : stocks) {
            System.out.printf("%-15s %-20d $%-14.2f $%-14.2f%n",
                    stock.getName(), stock.getNumShares(), stock.getPrice(), stock.calculateValue());
        }
        System.out.println("-".repeat(65));
        System.out.printf("%-15s %-35s $%-15.2f%n","Total","", calculateTotalValue());
        System.out.println("-".repeat(65));
        System.out.printf("Total Portfolio Value: $%.2f%n", calculateTotalValue());
    }
}

public class StockAccountManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StockPortfolio portfolio = new StockPortfolio();

        System.out.print("Enter the number of stocks: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Stock #" + (i + 1) + ":");
            System.out.print("Stock Name: ");
            String name = scanner.nextLine();
            System.out.print("Number of Shares: ");
            int numShares = scanner.nextInt();
            System.out.print("Share Price: $");
            double price = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            Stock stock = new Stock(name, numShares, price);
            portfolio.addStock(stock);
        }

        portfolio.printReport();
        scanner.close();
    }
}