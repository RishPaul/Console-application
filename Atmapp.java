import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Atmapp {
    public static HashMap<Integer,String> map = new HashMap<>();

    public static void main(String[] args) {
        ArrayList<UserDetails> list = new ArrayList<>();
        list.add(new UserDetails("A100", "Rish", 20, 1000));
        list.add(new UserDetails("A101", "Riya", 35, 5000));
        list.add(new UserDetails("A102", "Sreeja",20, 1000));
        
        Scanner sc =  new Scanner(System.in);
        int pin=0;
        map.put(1234, "A100");
        map.put(2345, "A101");
        map.put(3456, "A102");
        map.put(4567, "A103");
        System.out.println("---Welcome to Bank of Java--");
        boolean flag=true;
        do {
            System.out.println("Enter pin:");
            pin=sc.nextInt();
            if(!map.containsKey(pin)) {
                System.out.println("Enter valid pin!");
                flag=true;
            } else {
                flag=false;
            }
        } while(flag);
        
        char c='y';
        do {
            System.out.println("1. Withdrawal");
            System.out.println("2. Deposit");
            System.out.println("3. Balance check");
            System.out.println("4. Admin access");
            System.out.println("Enter your choice:");
            int ch=sc.nextInt();

            switch(ch) {
                case 1:
                   
                    withdrawal(pin, list);
                    break;

                case 2:
                    deposit(pin, list, sc);
                    break;

                case 3:
                    balanceCheck(pin, list);
                    break;

                case 4:
                    adminAccess(list);
                    break;

                default:
                    System.out.println("Enter Valid choice:");
                    continue; 
            }
            System.out.println("Do you want to continue:y/n");
            c=sc.next().charAt(0);
        } while(c!='n');
    }

    public static void withdrawal(int pin, ArrayList<UserDetails> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter amount to be withdrawn:");
        int amount = sc.nextInt();
        for(UserDetails user : list) {
            if(user.act_no.equals(map.get(pin))) {
                if(amount > user.balance) {
                    System.out.println("Insufficient balance!");
                    return;
                }
                user.balance -= amount;
                System.out.println("Amount withdrawn successfully. Updated balance: " + user.balance);
                return;
            }
        }
        System.out.println("User not found!");
    }

    public static void deposit(int pin, ArrayList<UserDetails> list, Scanner sc) {
        System.out.println("Enter amount to be deposited:");
        int amount = sc.nextInt();
        for(UserDetails user:list) {
            if(user.act_no.equals(map.get(pin))) {
                user.balance += amount;
                System.out.println("Amount deposited successfully. Updated balance: " + user.balance);
            }
        }
       
    }

    
    public static void balanceCheck(int pin, ArrayList<UserDetails> list) {
        for(UserDetails user:list) {
            if(user.act_no.equals(map.get(pin))) {
                System.out.println("Balance available: " + user.balance);
            }
        }
       
    }

    public static void adminAccess(ArrayList<UserDetails> list) {
        System.out.println("--- Admin Access ---");
        for (UserDetails user : list) {
            System.out.println("Account Number: " + user.act_no);
            System.out.println("Name: " + user.name);
            System.out.println("Age: " + user.age);
            System.out.println("Balance: " + user.balance);
            System.out.println("-------------------");
        }
        
        int totalbal = 0;
        for (UserDetails user : list) {
            totalbal += user.balance;
        }
        System.out.println("Total Balance in Bank: " + totalbal);
    }
}

class UserDetails {
    String act_no;
    String name;
    int age;
    int balance;

    UserDetails(String act_no,String name,int age,int balance){
        this.act_no=act_no;
        this.name=name;
        this.age=age;
        this.balance=balance;
    }
}
