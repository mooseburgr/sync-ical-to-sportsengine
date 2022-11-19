package sync;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.Data;

public class Types {
  @Data
  public static class EventAttendee {
    private String id;
    private String response;

    @JsonProperty("event_id")
    private String eventId;

    @JsonProperty("persona_id")
    private String personaId;

    @JsonProperty("principal_triad")
    private String principalTriad;

    @JsonProperty("principal_id")
    private String principalId;

    @JsonProperty("principal_type")
    private String principalType;

    @JsonProperty("principal_system")
    private String principalSystem;
  }

  @Data
  public static class ExtendedAttributes {
    private String uniform;

    @JsonProperty("private_notes")
    private String privateNotes;

    @JsonProperty("arrival_time")
    private int arrivalTime;

    @JsonProperty("primary_color")
    private String primaryColor;

    private String name;
  }

  @Data
  public static class GameDetails {
    private String originator_system;
    private String status;
    private Team team_1;
    private Team team_2;
    private boolean score_allowed;
    private String originator_id;
    private String originator_type;
    private ZonedDateTime originator_updated_at;
  }

  @Data
  public static class Metadata {
    private String trace_id;
    private Pagination pagination;
  }

  @Data
  public static class Pagination {
    private int total;
    private int total_pages;
    private boolean first_page;
    private String first_page_href;
    private String next_page_href;
    private boolean last_page;
    private String last_page_href;
    private int current_page;
    private int limit;
  }

  @Data
  public static class Principal {
    private String id;
    private String type;
    private String system;
    private String originator_id;
    private String originator_type;
    private String originator_system;
    private String principal_originator_id;
    private String principal_originator_type;
    private String principal_originator_system;
    private ExtendedAttributes extended_attributes;
  }

  @Data
  public static class Result {
    private String id;
    private String originator_system;
    private String originator_type;
    private String originator_id;
    private String title;
    private int org_id;
    private String description;
    private String location_name;
    private String location_address;
    private String location_description;
    private String location;
    private String location_place_id;
    private String location_url;
    private String venue_id;
    private String subvenue_id;
    private String venue_url;
    private String status;
    private String sport_key;
    private boolean tbd_time;
    private boolean all_day_event;
    private ZonedDateTime start_date_time;
    private ZonedDateTime end_date_time;
    private String local_timezone;
    private String event_type;
    private Object allow_rsvp;
    private boolean is_locked;
    private ZonedDateTime created_at;
    private ZonedDateTime updated_at;
    private List<Object> _links;
    private List<Principal> principals;
    private GameDetails game_details;
    private List<EventAttendee> event_attendees;
    private Object shared_event;
    private Object read_only;
    private Object event_series_id;
    private List<Object> state;
  }

  @Data
  public static class TeamCalendarResponse {
    private Metadata metadata;
    private List<Result> result;
  }

  @Data
  public static class Team {
    private String id;
    private String originator_id;
    private String originator_system;
    private String originator_type;
    private String name;
    private boolean is_home_team;
    private String primary_color;
    private String score;
  }
}
