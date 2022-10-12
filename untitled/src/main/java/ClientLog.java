import netscape.javascript.JSException;
import netscape.javascript.JSObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ClientLog {
    public static void writeJsonSimpleDemo(String filename) throws Exception {
        JSONObject sampleObject = new JSONObject();
        sampleObject.put("name", "Stackabuser");
        sampleObject.put("age", 35);
    }


////    log(int productNum, int amount)
//   public void exportAsCSV(File txtFile)
//   {
//
//       try(FileWriter file = new FileWriter("txtFile")){
//           JSObject obj = new JSObject();
//           file.write(obj.toString());
//           obj.
//           file.flush();
//       } catch (IOException e) {
//           throw new RuntimeException(e);
//       }
//   }
//}
