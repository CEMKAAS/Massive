import au.com.bytecode.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ClientLog {

    private int productNum;
    private int amount;
    private String empy;

    public ClientLog() {
        this.productNum = productNum;
        this.amount = amount;
        this.empy = empy;
    }

    public void exportAsCSV(File txtFile) {
        String[] empy1 = empy.split(",");
        try (CSVWriter writer = new CSVWriter(new FileWriter(txtFile, true))) {
            writer.writeNext(empy1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void log(int productNum, int amount) {
        StringBuilder print = new StringBuilder();
        print.append(productNum);
        print.append(",");
        print.append(amount);
        empy = print.toString();

    }
}