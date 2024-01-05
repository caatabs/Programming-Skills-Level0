package Banking;

import java.util.Scanner;

/** Create an online banking system with the following features:

 *  - Users must be able to log in with a username and password.
 *  - If the user enters the wrong credentials three times, the system must lock them out.
 *  - The initial balance in the bank account is $2000.
 *  - The system must allow users to deposit, withdraw, view, and transfer money.
 *  - The system must display a menu for users to perform transactions.
 *  -    
* */
public class Banking {
    private static final String USERNAME = "admi";
    private static final String PASSWORD = "princessCat";
    private static final Double INITIAL_BALANCE = 2000.0;
    private static final Integer MAX_LOGIN_ATTEMPTS = 3;

    private static String currentUser;
    private static Double currentBalance;
    private static Integer loginAttempts = 0;

    /** Method that verifies correct login
     */
    private static boolean login() {
        Scanner scanner = new Scanner(System.in);

        while (loginAttempts < MAX_LOGIN_ATTEMPTS) {
            System.out.println("Enter username: ");
            String username = scanner.nextLine();

            System.out.println("Enter password: ");
            String password = scanner.nextLine();

            if (username.equals(USERNAME) && password.equals(PASSWORD)) {
                currentUser = username;
                currentBalance = INITIAL_BALANCE;
                loginAttempts = 0;
                return true;
            }
            else {
                loginAttempts++;
                System.out.println("Incorrect credentials. Attempts left: " + (MAX_LOGIN_ATTEMPTS - loginAttempts));
            }
        }
        System.out.println("Incorrect credentials. No more attempts available");
        return false;
    }

    /** Method that displays the different actions that can be performed by the user
     * */
    private static void displayMenu() {
        System.out.println("Choose an option: ");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. View Balance");
        System.out.println("4. Transfer");
        System.out.println("5. Exit");
    }

    /** Method that credits the deposit made to the account
     * */
    private static void deposit() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the deposit amount: $");
        Double deposit = Double.parseDouble(scanner.nextLine());

        currentBalance += deposit;

        System.out.println("Deposit successful. Updated balance: $" + currentBalance);
    }

    /** Method of withdrawing money from the account
     * */
    private static void withdraw() {
        Scanner scanner = new Scanner(System.in);

        Double withdraw = Double.parseDouble(scanner.nextLine());

        if (withdraw <= currentBalance) {
            currentBalance -= withdraw;
            System.out.println("Withdrawal successful. Updated balance: $" + currentBalance);
        }
        else {
            System.out.println("Insufficient funds. Withdrawal failed.");
        }
    }

    /** Method to view the current account balance
     * */
    private static void viewBalance() {
        System.out.println("Current balance: $" + currentBalance);
    }

    private static void transfer() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the transfer amount: $");
        Double transfer = Double.parseDouble(scanner.nextLine());

        if (transfer <= currentBalance) {
            System.out.print("Enter the recipient's username: ");
            String recipient = scanner.next();

            // Perform transfer logic here ...

            System.out.println("Transfer successful to " + recipient + ". Updated balance: $" + currentBalance);
        }
        else {
            System.out.println("Insufficient funds. Transfer failed.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Online Banking System. Please enter your credentials");

        while(true) {
            if (login()) {
                displayMenu();

                Integer choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        deposit();
                        break;
                    case 2:
                        withdraw();
                        break;
                    case 3:
                        viewBalance();
                        break;
                    case 4:
                        transfer();
                        break;
                    case 5:
                        System.out.println("Thank you for using Online Banking System. Goodbye!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
            else {
                System.out.println("Login failed. Account locked after " + MAX_LOGIN_ATTEMPTS + " attempts.");
                System.exit(0);
            }
        }
    }
}
