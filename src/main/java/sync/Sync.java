package sync;

import com.google.cloud.functions.HttpFunction;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Sync implements HttpFunction {

  private final HttpClient client =
      HttpClient.newBuilder()
          .followRedirects(HttpClient.Redirect.ALWAYS)
          .cookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ALL))
          .build();

  // Simple function to return "Hello World"
  @Override
  public void service(
      com.google.cloud.functions.HttpRequest request,
      com.google.cloud.functions.HttpResponse response)
      throws IOException, InterruptedException {

    var loginResp =
        client.send(HttpRequest.newBuilder().build(), HttpResponse.BodyHandlers.ofString());

    BufferedWriter writer = response.getWriter();
    writer.write("Hello World!");
  }
}
