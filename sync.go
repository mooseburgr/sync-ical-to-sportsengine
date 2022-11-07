package sync

import (
	"github.com/arran4/golang-ical"
	log "github.com/sirupsen/logrus"
	"net/http"
	"net/url"
	"os"
)

var (
	client *SportsEngineClient
)

func init() {
	log.SetFormatter(&log.JSONFormatter{})
	client = NewSportsEngineClient(os.Getenv("USERNAME"), os.Getenv("PASSWORD"))
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
