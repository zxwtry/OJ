package comm

import "encoding/base64"

func Decode(decodeType string, decodeSource string) (uint32, string, string) {
	switch decodeType {
	case "base64":
		bs, err := base64.StdEncoding.DecodeString(decodeSource)
		if err != nil {
			return 1, err.Error(), ""
		}
		return 0, "", string(bs)
	}
	return 0, "", ""
}
