package test2;

//import static org.junit.Assert.assertTrue;
import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.http.apache.ApacheHttpTransport;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    //curl https://example.com 相当のことができる機能
    @Test
    public void testGoogleHttpJavaClientGet() {
        HttpTransport transport = new ApacheHttpTransport();
        HttpRequestFactory factory = transport.createRequestFactory();

        GenericUrl url = new GenericUrl("http://localhost:8080/get?param=value");

        try {
            HttpRequest request = factory.buildGetRequest(url);
            HttpResponse response = request.execute();

            try {
                assertThat(response.getStatusCode())
                    .isEqualTo(200);
                assertThat(response.parseAsString())
                    .contains("Accessed URL = /get?param=value",
                              "Accessed Method = GET");
            } finally {
                response.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                transport.shutdown();
            } catch (IOException e ) {
                e.printStackTrace();
            }
        }
    }


    //curl -o file https://example.com 相当のことができる機能
    @Test
    public void testGoogleHttpJavaClientGet_O() {
        HttpTransport transport = new ApacheHttpTransport();
        HttpRequestFactory factory = transport.createRequestFactory();

        GenericUrl url = new GenericUrl("http://localhost:8080/get?param=value");

        try {
            HttpRequest request = factory.buildGetRequest(url);
            HttpResponse response = request.execute();

            try {
                assertThat(response.getStatusCode())
                    .isEqualTo(200);
                assertThat(response.parseAsString())
                    .contains("Accessed URL = /get?param=value",
                              "Accessed Method = GET");
                //ファイルに書き込む処理
                FileWriter fw = new FileWriter("test.txt",true);
                fw.write("Accessed URL = /get?param=value");
                fw.write("Accessed Method = GET");
                fw.close();
            } finally {
                response.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                transport.shutdown();
            } catch (IOException e ) {
                e.printStackTrace();
            }
        }
    }


   //curl -v  https://example.com 相当のことができる機能
    @Test
    public void testGoogleHttpJavaClientGet_V() {
        HttpTransport transport = new ApacheHttpTransport();
        HttpRequestFactory factory = transport.createRequestFactory();

        GenericUrl url = new GenericUrl("http://localhost:8080/get?param=value");

        try {
            HttpRequest request = factory.buildGetRequest(url);
            HttpResponse response = request.execute();

            try {
                assertThat(response.getStatusCode())
                    .isEqualTo(200);
                assertThat(response.parseAsString())
                    .contains("Accessed URL = /get?param=value",
                              "Accessed Method = GET");
            } finally {
                response.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                transport.shutdown();
            } catch (IOException e ) {
                e.printStackTrace();
            }
        }
    }

    //curl -X POST https://example.com 相当のことができる機能
    @Test
    public void testGoogleHttpJavaClientPost() {
        HttpTransport transport = new ApacheHttpTransport();
        HttpRequestFactory factory = transport.createRequestFactory();

        GenericUrl url = new GenericUrl("http://localhost:8080/post");

        try {
            StringBuilder builder = new StringBuilder();
           

            HttpRequest request =
                factory.buildPostRequest(url,
                                         new InputStreamContent("text/plain",
                                                                new ByteArrayInputStream(builder.toString().getBytes(StandardCharsets.UTF_8))));
            HttpResponse response = request.execute();

            try {
                assertThat(response.getStatusCode())
                    .isEqualTo(200);
                assertThat(response.parseAsString())
                    .contains("Accessed URL = /post",
                              "Accessed Method = POST",
                              "Request Body<<",
                              
                              ">>");
            } finally {
                response.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                transport.shutdown();
            } catch (IOException e ) {
                e.printStackTrace();
            }
        }
      
    }
    
    //curl -X POST -d "key=value" https://example.com 相当のことができる機能
    @Test
    public void testGoogleHttpJavaClientPost_d() {
        HttpTransport transport = new ApacheHttpTransport();
        HttpRequestFactory factory = transport.createRequestFactory();

        GenericUrl url = new GenericUrl("http://localhost:8080/post");

        try {
            StringBuilder builder = new StringBuilder();
            builder.append("POST Body");
            builder.append("\r\n");
            builder.append("Hello Http Server!!");
            builder.append("\r\n");

            HttpRequest request =
                factory.buildPostRequest(url,
                                         new InputStreamContent("text/plain",
                                                                new ByteArrayInputStream(builder.toString().getBytes(StandardCharsets.UTF_8))));
            HttpResponse response = request.execute();

            try {
                assertThat(response.getStatusCode())
                    .isEqualTo(200);
                assertThat(response.parseAsString())
                    .contains("Accessed URL = /post",
                              "Accessed Method = POST",
                              "Request Body<<",
                              "POST Body",
                              "Hello Http Server!!",
                              ">>");
            } finally {
                response.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                transport.shutdown();
            } catch (IOException e ) {
                e.printStackTrace();
            }
        }
      
    }
}
