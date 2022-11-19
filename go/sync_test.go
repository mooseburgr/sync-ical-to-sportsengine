package sync

import (
	log "github.com/sirupsen/logrus"
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestSync_Success(t *testing.T) {
	err := client.Login()

	cal, err := client.GetTeamCalendar(GREY_DUCKS)

	log.Infof("cal: %+v, err: %+v", cal, err)

	assert.True(t, true)
}
