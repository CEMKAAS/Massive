import netscape.javascript.JSException;
import com.googlecode.json-simpleJSObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ClientLog {
    JSObject obj = new JSObject();

//    log(int productNum, int amount)
   public void exportAsCSV(File txtFile){

       try(FileWriter file = new FileWriter("txtFile")){
           file.write(obj.toString());
           file.flush();
           obj.
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
   }
}
