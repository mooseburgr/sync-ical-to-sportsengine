package sync;

import static java.net.http.HttpRequest.*;
import static java.net.http.HttpResponse.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class SportsEngineClient {
  private final String username;
  private final String password;

  private final ObjectMapper objectMapper = new ObjectMapper();
  private final HttpClient client =
      HttpClient.newBuilder()
          .followRedirects(HttpClient.Redirect.ALWAYS)
          .cookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ALL))
          .build();

  public void login() throws IOException, InterruptedException {
    var formBody = String.format("user[login]=%s&user[password]=%s", username, password);
    var resp =
        client.send(
            HttpRequest.newBuilder()
                .uri(URI.create("https://user.sportngin.com/users/sign_in"))
                .POST(
                    BodyPublishers.ofString(
                        String.format("user[login]=%s&user[password]=%s", username, password)))
                .build(),
            BodyHandlers.ofString());
    log.info("successful login: {}", resp.body());
  }
}
