
package data;
import java.util.List;
import java.util.ArrayList;
import java.sql.Date;
import tools.MyTool;
import mng.LogIn;

public class DealerList extends ArrayList<Dealer> {
    LogIn loginObj=null;
    private static final String PHONEPATTERN = "\\d{9}|\\d{11}";
    private String dataFile="";
    boolean changed =false;

// COnstructor
    public DealerList(LogIn loginObj) {
        this.loginObj = loginObj;
    }
    
//*
    private void loadDealerFromFile() {
        List<String> lines = MyTool.readLinesFromFile(dataFile);
        for(String line:lines){ 
            Dealer d = new Dealer(line);
            this.add(d);
        }
    }
    
    public void initWithFile(){
        Config cR = new Config();
        dataFile = cR.getDealerFile();
        loadDealerFromFile();
    }
 
//*
    public DealerList getContinuingList(){        
        DealerList resultList = new DealerList(loginObj);
        for(Dealer d:this){
             if(d.isContinuing()==true) resultList.add(d);            
        }
        return resultList;
    }
    
//*
     public DealerList getUnContinuingList(){    
        DealerList resultList = new DealerList(loginObj);
        for(Dealer d:this){
             if(d.isContinuing()==false) resultList.add(d);
        }
        return resultList;
    }

//*
    public int searchDealer(String ID){
        ID = ID.toUpperCase();        
         for(int i=0;i<this.size();i++){
             if(ID.equals(this.get(i).getID())){
                 return i;
             }
         }
         return -1;
    }
    public void searchDealer(){
        MyTool.SC.nextLine();
        String inputID = MyTool.readNonBlank("Dealer's ID needs searching: ");
        int pos = searchDealer(inputID);
        if(pos<0) System.out.println("NOT FOUND!");
        else System.out.println("The positon of the ID in the list: " + pos);
    }
    public void addDealer(){
        String ID;
        String name;
        String addr;
        String phone;
        boolean continuing;
        int pos;
        do{
            MyTool.SC.nextLine();
            ID = MyTool.readPattern("ID of new dealer", Dealer.ID_FORMAT);
            ID = ID.toUpperCase();
            pos = searchDealer(ID);
            if(pos>=0) System.out.println("ID is duplicated");
        }while(pos>=0);
        name = MyTool.readNonBlank("Name of the new dealer: ").toUpperCase();
        addr = MyTool.readNonBlank("Address of new dealer: ");
        phone = MyTool.readPattern("Phone number: ", Dealer.PHONE_FORMAT);
        continuing = true;
        Dealer d = new Dealer(ID, name, addr, phone, continuing);
        this.add(d);
        System.out.println("New dealer has been added");
        changed = true;
    }
    public void removeDealer(){
        MyTool.SC.nextLine();
        String ID = MyTool.readNonBlank("Enter ID ");
        int pos=searchDealer(ID);
        if(pos<0) System.out.println("Not fOUND !");
        else {
            this.get(pos).setContinuing(false);            
            System.out.println("Removed");
            this.changed = true;
        }
    }
    public void updateDealer(){
        MyTool.SC.nextLine();
        System.out.print("Dealer's ID needs updating: ");
        String ID = MyTool.SC.nextLine();
        
        int pos = searchDealer(ID);
        if(pos<0) System.out.println("Dealer "+ID+" not found!");
        else{
            Dealer d = this.get(pos);
            String newName = "";
            System.out.print("new name, ENTER for omitting: ");
            newName = MyTool.SC.nextLine().trim().toUpperCase();
            if(!newName.isEmpty()){
                d.setName(newName);
                changed=true;
            }
            String newAddr = "";
            System.out.print("new addr, ENTER for omitting: ");
            newAddr = MyTool.SC.nextLine().trim().toUpperCase();
            if(!newAddr.isEmpty()){
                d.setAddr(newAddr);
                changed=true;
            }
            String newPhone = "";
            newPhone = MyTool.readPattern("Enter phone: ", PHONEPATTERN);
            if(!newPhone.isEmpty()){
                d.setPhone(newPhone);
                changed=true;
            }
        }
    }
    public void printAllDealers(){
        if(this.isEmpty()) System.out.println("Empty List!");
        else System.out.println(this);
    }
    public void printContinuingDealers(){
        this.getContinuingList().printAllDealers();
    }
        public void printUnContinuingDealers(){
        this.getUnContinuingList().printAllDealers();
    }
    
    public void writeDealerToFile(){
        if(changed) {
            MyTool.writeFile(dataFile, this);
            changed = false;
        }
    }
    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

}
