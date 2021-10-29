import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * @author YeYaqiao
 * ES 连接工具类
 */
public class EsConnection {
    RestHighLevelClient client = null;

    public RestHighLevelClient getClient() {
        client = new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1", 9200)));
        return client;
    }

    public void close() {
        if (client != null) {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        EsConnection client = new EsConnection();
        RestHighLevelClient client1 = client.getClient();
        System.out.println(client1);
        System.out.println(client);

        client.close();

    }
}
