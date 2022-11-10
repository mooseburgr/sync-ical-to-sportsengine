package sync

import (
	"encoding/json"
	"fmt"
	log "github.com/sirupsen/logrus"
	"golang.org/x/net/publicsuffix"
	"io"
	"net/http"
	"net/http/cookiejar"
	"net/url"
)

type SportsEngineClient struct {
	username, password string
	client             http.Client
}

func NewSportsEngineClient(username, password string) *SportsEngineClient {
	jar, err := cookiejar.New(&cookiejar.Options{
		PublicSuffixList: publicsuffix.List,
	})
	if err != nil {
		panic(err)
	}
	return &SportsEngineClient{
		username: username,
		password: password,
		client: http.Client{
			Jar: jar,
		},
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
	log.Infof("cookies after login: %+v", c.client.Jar)
	log.WithField("username", c.username).Info("successful login")
	log.Debugf("login response: %v", resp)
	return nil
}

func (c *SportsEngineClient) GetTeamCalendar(teamId string) (GetTeamCalendarResponse, error) {
	resp, err := c.client.Get(fmt.Sprintf("https://api.sportngin.com/v3/calendar/team/%s", teamId))
	if err != nil {
		log.WithFields(log.Fields{"teamId": teamId, "error": err, "response": resp}).Errorf("failed to get calendar")
		return GetTeamCalendarResponse{}, err
	}

	defer resp.Body.Close()
	respBytes, err := io.ReadAll(resp.Body)
	if err != nil {
		log.WithFields(log.Fields{"error": err, "response": resp}).Error("failed to read response bytes")
		return GetTeamCalendarResponse{}, err
	}
	log.Info(string(respBytes))

	var decoded GetTeamCalendarResponse
	err = json.Unmarshal(respBytes, &decoded)
	if err != nil {
		log.WithFields(log.Fields{"error": err, "response": resp}).Error("failed to decode team calendar response")
		return GetTeamCalendarResponse{}, err
	}
	log.WithField("team_calendar", decoded).Debug("decoded team calendar response")
	return decoded, nil
}
