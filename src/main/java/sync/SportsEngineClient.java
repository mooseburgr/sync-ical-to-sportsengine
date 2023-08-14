package sync;

import static java.net.http.HttpRequest.*;
import static java.net.http.HttpResponse.*;
import static sync.Types.*;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@RequiredArgsConstructor
@Slf4j
public class SportsEngineClient {
  private final String username;
  private final String password;

  private static final ObjectMapper om =
      new ObjectMapper()
          .findAndRegisterModules()
          .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
  private final HttpClient client =
      HttpClient.newBuilder()
          .followRedirects(HttpClient.Redirect.ALWAYS)
          .cookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ALL))
          .build();

  @SneakyThrows
  public SportsEngineClient() {
    this.username = "kylejohnson.mn@gmail.com";
    var pwToUse = System.getenv("SE_PASSWORD");
    if (StringUtils.isBlank(pwToUse)) {
      pwToUse = Files.readString(Path.of("./se-password"));
    }
    this.password = pwToUse;
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
