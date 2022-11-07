package sync

import (
	"encoding/json"
	"fmt"
	log "github.com/sirupsen/logrus"
	"net/http"
	"net/url"
)

type SportsEngineClient struct {
	username, password string
	client             *http.Client
}

func NewSportsEngineClient(username, password string) *SportsEngineClient {
	return &SportsEngineClient{
		username: username,
		password: password,
		client:   http.DefaultClient,
	}
}

func (c *SportsEngineClient) Login() error {
	resp, err := c.client.PostForm("https://user.sportngin.com/users/sign_in", url.Values{
		"user[login]":    {c.username},
		"user[password]": {c.password},
	})

	if err != nil {
		log.WithFields(log.Fields{"username": c.username, "error": err}).Error("login failed")
		return err
	}
	log.WithField("username", c.username).Info("successful login")
	log.Debugf("login response: %v", resp)
	return nil
}

func (c *SportsEngineClient) GetTeamCalendar(teamId string) (*GetTeamCalendarResponse, error) {
	resp, err := c.client.Get(fmt.Sprintf("https://api.sportngin.com/v3/calendar/team/%s", teamId))
	if err != nil {
		log.WithFields(log.Fields{"teamId": teamId, "error": err, "response": resp}).Errorf("failed to get calendar")
		return nil, err
	}
	var decoded *GetTeamCalendarResponse
	err = json.NewDecoder(resp.Body).Decode(decoded)
	if err != nil {
		log.WithField("error", err).Error("failed to decode team calendar response")
		return nil, err
	}
	log.WithField("team_calendar", decoded).Debug("decoded team calendar response")
	return decoded, nil
}
