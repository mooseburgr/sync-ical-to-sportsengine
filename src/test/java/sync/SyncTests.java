package sync;

import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SyncTests {

  SportsEngineClient client;

  @BeforeEach
  public void setup() throws Exception {
    client =
        new SportsEngineClient(
            "kylejohnson.mn@gmail.com", Files.readString(Path.of("se-password")));
  }

  @Test
  public void testLogin() throws Exception {
    setup();
    client.login();
  }
}
