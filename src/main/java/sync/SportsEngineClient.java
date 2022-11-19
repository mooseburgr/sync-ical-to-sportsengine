package sync;

import static java.net.http.HttpRequest.*;
import static java.net.http.HttpResponse.*;
import static sync.Types.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class SportsEngineClient {
  private final String username;
  private final String password;

  private static final ObjectMapper om = new ObjectMapper().registerModule(new JavaTimeModule());
  private final HttpClient client =
      HttpClient.newBuilder()
          .followRedirects(HttpClient.Redirect.ALWAYS)
          .cookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ALL))
          .build();

  public SportsEngineClient() throws IOException {
    this.username = "kylejohnson.mn@gmail.com";
    this.password =
        Optional.ofNullable(System.getenv("SE_PASSWORD"))
            .orElse(Files.readString(Path.of("se-password")));
  }

  public void login() throws Exception {
    var resp =
        client.send(
            HttpRequest.newBuilder(URI.create("https://user.sportngin.com/users/sign_in"))
                .POST(
                    BodyPublishers.ofString(
                        String.format("user[login]=%s&user[password]=%s", username, password)))
                .build(),
            BodyHandlers.ofString());
    log.info("successful login: {}", resp.body());
  }

  public TeamCalendarResponse getTeamCalendar(String teamId) throws Exception {
    var resp =
        client.send(
            HttpRequest.newBuilder(
                    URI.create(
                        String.format("https://api.sportngin.com/v3/calendar/team/%s", teamId)))
                .GET()
                .build(),
            BodyHandlers.ofString());
    log.info("got team calendar: {}", resp.body());
    return om.readValue(resp.body(), TeamCalendarResponse.class);
  }
}
