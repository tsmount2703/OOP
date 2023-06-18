
package mng;
import data.Account;
import data.AccountChecker;
import data.DealerList;
import tools.MyTool;

public class LogIn {
    private Account acc=null;

// Constructor
    public LogIn( Account acc) {
        this.acc = acc;
    }
 
//*
    public static Account inputAccount() {
        System.out.println("Please Login to System.");
        System.out.print("Your account name: ");
        String name = MyTool.SC.nextLine();
        System.out.print("Your password: ");
        String pwd = MyTool.SC.nextLine();
        System.out.print("Your role: ");
        String role = MyTool.SC.nextLine();
        Account acc = new Account(name, pwd, role);
        return acc;
    }

// Getter
    public Account getAcc() {
        return acc;
    }
    
    public static void main(String[] args) {
        Account acc = null;
        boolean cont;
        boolean valid = false;
        do {            
            AccountChecker accChk = new AccountChecker();
            acc = inputAccount();
            valid = accChk.check(acc);
            cont = false;
            if (!valid)
                cont = MyTool.readBool("Invalid account- Try again?");
            if (!valid && !cont)
                System.exit(0);
        } while (cont);
        LogIn loginObj = new LogIn(acc);    
        if (acc.getRole().equalsIgnoreCase("ACC-1")) {
            String[] options = {"Add new dealer", "Search a dealer",
                                "Remove a dealer", "Update a dealer", 
                                "Print all dealers","Print continuing dealers", 
                                "Print UN-continuing dealers", "Write to file"};
        Menu mnu = new Menu(options);       
        DealerList dList = new DealerList(loginObj);
        dList.initWithFile();
        int choice=0;
        do {            
            choice = mnu.getChoice("Managing dealers");
            switch(choice) {
                case 1: 
                    dList.addDealer(); break;
                case 2: 
                    dList.searchDealer(); break;
                case 3: 
                    dList.removeDealer(); break;
                case 4: 
                    dList.updateDealer(); break;
                case 5: 
                    dList.printAllDealers(); break;
                case 6: 
                    dList.printContinuingDealers(); break;
                case 7: 
                    dList.printUnContinuingDealers(); break;
                case 8: 
                    dList.writeDealerToFile(); break;
                default:
                    if (dList.isChanged()) {
                        boolean res= MyTool.readBool("Data changed. Write to file?");
                        if (res==true) dList.writeDealerToFile();
                    }
            }
        } while (choice > 0 && choice <= mnu.size());
        System.out.println("Bye.");
        }
    }
}
