import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HttpUrlConnectUtils {

    public static List<String> doGet(String path){
        return doGet0(path,"utf-8");
    }

    public static List<String> doGet(String path, String encoding){
        return doGet0(path,encoding);
    }

    private static List<String> doGet0(String path,String encoding){
        List<String> content = null;
        BufferedReader br = null;
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            int statusCode = conn.getResponseCode();
            if(statusCode == 200){
                br = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding));
                content = new ArrayList<String>();
                String line = null;
                while((line = br.readLine()) != null){
                    content.add(line);
                }
            } else {
                System.out.print("Connect Error:error code("+statusCode+")");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return content;
        }
    }

}
