import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

/*
<dependencies>
	<dependency>
		<groupId>org.apache.httpcomponents</groupId>
		<artifactId>httpclient</artifactId>
		<version>4.5.5</version>
	</dependency>
</dependencies>
*/
public class HttpUtils {
    public static void main(String[] args){
        String path = "http://www.baidu.com";
        try {
            System.out.println(doGet(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String doGet(String path) throws IOException {
        HttpClient client = HttpClients.createDefault();
        HttpGet getMethod = new HttpGet(path);
        CloseableHttpResponse response = ((CloseableHttpClient) client).execute(getMethod);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity,"utf-8");
        }
        if(response != null){
            response.close();
        }
        return null;
    }
}
