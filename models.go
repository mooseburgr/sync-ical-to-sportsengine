package sync

import "time"

type GetTeamCalendarResponse struct {
	Metadata struct {
		TraceId    string `json:"trace_id"`
		Pagination struct {
			Total         int    `json:"total"`
			TotalPages    int    `json:"total_pages"`
			FirstPage     bool   `json:"first_page"`
			FirstPageHref string `json:"first_page_href"`
			NextPageHref  string `json:"next_page_href"`
			LastPage      bool   `json:"last_page"`
			LastPageHref  string `json:"last_page_href"`
			CurrentPage   int    `json:"current_page"`
			Limit         int    `json:"limit"`
		} `json:"pagination"`
	} `json:"metadata"`
	Result []struct {
		Id                  string        `json:"id"`
		OriginatorSystem    string        `json:"originator_system"`
		OriginatorType      string        `json:"originator_type"`
		OriginatorId        string        `json:"originator_id"`
		Title               string        `json:"title"`
		OrgId               int           `json:"org_id"`
		Description         string        `json:"description"`
		LocationName        string        `json:"location_name"`
		LocationAddress     string        `json:"location_address"`
		LocationDescription string        `json:"location_description"`
		Location            string        `json:"location"`
		LocationPlaceId     string        `json:"location_place_id"`
		LocationUrl         string        `json:"location_url"`
		VenueId             string        `json:"venue_id"`
		SubvenueId          string        `json:"subvenue_id"`
		VenueUrl            string        `json:"venue_url"`
		Status              string        `json:"status"`
		SportKey            string        `json:"sport_key"`
		TbdTime             bool          `json:"tbd_time"`
		AllDayEvent         bool          `json:"all_day_event"`
		StartDateTime       time.Time     `json:"start_date_time"`
		EndDateTime         time.Time     `json:"end_date_time"`
		LocalTimezone       string        `json:"local_timezone"`
		EventType           string        `json:"event_type"`
		AllowRsvp           interface{}   `json:"allow_rsvp"`
		IsLocked            interface{}   `json:"is_locked"`
		CreatedAt           time.Time     `json:"created_at"`
		UpdatedAt           time.Time     `json:"updated_at"`
		Links               []interface{} `json:"_links"`
		Principals          []struct {
			Id                        string `json:"id"`
			Type                      string `json:"type"`
			System                    string `json:"system"`
			OriginatorId              string `json:"originator_id,omitempty"`
			OriginatorType            string `json:"originator_type,omitempty"`
			OriginatorSystem          string `json:"originator_system,omitempty"`
			PrincipalOriginatorId     string `json:"principal_originator_id,omitempty"`
			PrincipalOriginatorType   string `json:"principal_originator_type,omitempty"`
			PrincipalOriginatorSystem string `json:"principal_originator_system,omitempty"`
			ExtendedAttributes        struct {
				Uniform      interface{} `json:"uniform"`
				PrivateNotes *string     `json:"private_notes"`
				ArrivalTime  *int        `json:"arrival_time"`
				PrimaryColor string      `json:"primary_color"`
				Name         string      `json:"name"`
			} `json:"extended_attributes,omitempty"`
		} `json:"principals"`
		GameDetails struct {
			OriginatorSystem string `json:"originator_system"`
			Status           string `json:"status"`
			Team1            struct {
				Id               string `json:"id"`
				OriginatorId     string `json:"originator_id"`
				OriginatorSystem string `json:"originator_system"`
				OriginatorType   string `json:"originator_type"`
				Name             string `json:"name"`
				IsHomeTeam       bool   `json:"is_home_team"`
			} `json:"team_1"`
			Team2 struct {
				Id               string `json:"id"`
				OriginatorId     string `json:"originator_id"`
				OriginatorSystem string `json:"originator_system"`
				OriginatorType   string `json:"originator_type"`
				Name             string `json:"name"`
				IsHomeTeam       bool   `json:"is_home_team"`
			} `json:"team_2"`
			ScoreAllowed bool `json:"score_allowed"`
		} `json:"game_details"`
		EventAttendees []struct {
			Id              string `json:"id"`
			Response        string `json:"response"`
			EventId         string `json:"event_id"`
			PersonaId       string `json:"persona_id"`
			PrincipalTriad  string `json:"principal_triad"`
			PrincipalId     string `json:"principal_id"`
			PrincipalType   string `json:"principal_type"`
			PrincipalSystem string `json:"principal_system"`
		} `json:"event_attendees"`
		SharedEvent   interface{}   `json:"shared_event"`
		ReadOnly      interface{}   `json:"read_only"`
		EventSeriesId interface{}   `json:"event_series_id"`
		State         []interface{} `json:"state"`
	} `json:"result"`
}
