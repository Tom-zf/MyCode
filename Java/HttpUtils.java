import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.util.List;

public class HttpUtils {
    public static void main(String[] args) throws IOException {
        String path = "https://timg01.bdimg.com/timg?searchbox_feed&size=f218_146&imgtype=1&quality=100&wh_rate=0&ref=http%3A%2F%2Fwww.baidu.com&sec=0&di=6c943098c7c6fe51e3008dbb4774bd15&src=http%3A%2F%2Ff11.baidu.com%2Fit%2Fu%3D1405059655%2C1327117340%26fm%3D173%26app%3D49%26f%3DJPEG%3Fw%3D218%26h%3D146%26s%3D55361ED116121662703DB509030070D1";

         doGetStream(path);
    }

    public static String doGetText(String path)  {
        HttpClient client = HttpClients.createDefault();
        HttpGet getMethod = new HttpGet(path);
        CloseableHttpResponse response = null;
        String textContent = null;
        try {
            response = ((CloseableHttpClient) client).execute(getMethod);
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                HttpEntity entity = response.getEntity();
                textContent = EntityUtils.toString(entity,"utf-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return textContent;
    }

    public static String doGetTextTryTimes(String path, int tryTimes) throws IOException {
        String ret = null;
        while(tryTimes > 0){
            ret = doGetText(path);
            if(ret != null){
                break;
            } else {
                tryTimes -= 1;
            }
        }
        return ret;
    }

    public static boolean doGetStream(String path)  {
        HttpClient client = HttpClients.createDefault();
        HttpGet getMethod = new HttpGet(path);
        CloseableHttpResponse response = null;
        InputStream is = null;
        boolean flag = false;
        try {
            response = ((CloseableHttpClient) client).execute(getMethod);
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                HttpEntity entity = response.getEntity();
                is = entity.getContent();
                String destPath = "C:\\Users\\Administrator\\Desktop\\img.jpg";
                OutputStream os = new FileOutputStream(destPath);
                FileUtils.writeStream(is,os);
            }
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    public static String doPostText(String path, List<NameValuePair> parmas){
        return null;
    }
}
