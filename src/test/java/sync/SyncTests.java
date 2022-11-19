package sync;

import static sync.Sync.GREY_DUCKS_ID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SyncTests {

  SportsEngineClient client;

  @BeforeEach
  void setup() throws Exception {
    client = new SportsEngineClient();
  }

  @Test
  void testClient() throws Exception {
    client.login();
    var calendar = client.getTeamCalendar(GREY_DUCKS_ID);
  }
}
