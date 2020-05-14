package comm

import (
	"encoding/json"
	"fmt"
)

func CommLog(objs ...interface{}) string {
	s := objs[0].(string)
	avg := make([]interface{}, 0, len(objs)-1)
	for index := 1; index < len(objs); index++ {
		bs, _ := json.Marshal(objs[index])
		avg = append(avg, string(bs))
	}
	return fmt.Sprintf(s, avg...)
}
