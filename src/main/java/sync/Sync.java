package sync;

import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import java.io.BufferedWriter;

public class Sync implements HttpFunction {

  public static final String GREY_DUCKS_ID = "11ed1861-cca4-306e-ba7c-da146b0cad6f";

  @Override
  public void service(HttpRequest request, HttpResponse response) throws Exception {
    var client = new SportsEngineClient();
    client.login();
    client.getTeamCalendar(GREY_DUCKS_ID);

    BufferedWriter writer = response.getWriter();
    writer.write("Hello World!");
  }
}
