package sync

import (
	ics "github.com/arran4/golang-ical"
	log "github.com/sirupsen/logrus"
	"net/http"
	"net/url"
	"os"
)

const (
	GREY_DUCKS = "11ed1861-cca4-306e-ba7c-da146b0cad6f"
)

var (
	client *SportsEngineClient
)

func init() {
	log.SetFormatter(&log.JSONFormatter{})
	client = NewSportsEngineClient("kylejohson.mn@gmail.com", GetPassword())
}

func Sync(w http.ResponseWriter, r *http.Request) {

	// validate URL param
	icalUrl := r.URL.Query().Get("icalUrl")
	if _, err := url.ParseRequestURI(icalUrl); err != nil {
		http.Error(w, "Invalid URL: "+err.Error(), http.StatusBadRequest)
		return
	}

	// TODO everything lol

	cal, err := ics.ParseCalendar(r.Body)
	if err != nil {
		http.Error(w, "Failed to parse calendar: "+err.Error(), http.StatusInternalServerError)
		return
	}

	err = cal.SerializeTo(w)
	if err != nil {
		http.Error(w, "Failed to serialize calendar: "+err.Error(), http.StatusInternalServerError)
		return
	}

}

func GetPassword() string {
	if token := os.Getenv("PASSWORD"); token != "" {
		return token
	} else {
		bytes, _ := os.ReadFile("se-password")
		return string(bytes)
	}
}
