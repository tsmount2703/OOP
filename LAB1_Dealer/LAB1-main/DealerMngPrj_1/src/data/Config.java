
package data;

import java.util.List;
import tools.MyTool;


public class Config {
    private static final String CONFIG_FILE = "DealerData/config.txt";
    private String accountFile;
    private String dealerFile;
    private String deliveryFile;
    
    public Config() {
        readData();
    }

    private void readData() {
        List<String> lines = MyTool.readLinesFromFile(CONFIG_FILE);
        for (String line : lines) {
            String[] parts = line.split(":");
            if (line.contains("accounts")) {
                accountFile = "DealerData/" + parts[1].trim();
            } else if (line.contains("dealers")) {
                dealerFile = "DealerData/" + parts[1].trim();
            } else if (line.contains("delivery")) {
                deliveryFile = "DealerData/" + parts[1].trim();
            }
        }
    }

    //Getters
    public String getAccountFile() {
        return accountFile;
    }
    public String getDealerFile() {
        return dealerFile;
    }
    public String getDeliveryFile() {
        return deliveryFile;
    }
    
}
