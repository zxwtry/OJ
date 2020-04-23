package main

import "fmt"

func CommRemoveArrIndex(arr []string, index int) []string {
	if index < 0 || index >= len(arr) || len(arr) == 0 {
		return arr
	}
	return append(arr[0:index], arr[index+1:]...)
}

func CommInsertArrIndex(arr []string, index int, val string) []string {
	if index < 0 || index > len(arr) {
		return arr
	}
	if index == len(arr) {
		return append(arr, val)
	}
	cpy := make([]string, len(arr), len(arr))
	copy(cpy, arr)
	return append(append(arr[0:index], val), cpy[index:]...)
}

type SignInStatusReq struct {
	Appid        string `json:"appid"`        // appid 找开发
	Sign         string `json:"sign"`         // sign生成方式，找开发
	CardInfo     string `json:"cardinfo"`     // 卡片信息，找开发
	UserType     int32  `json:"usertype"`     // 用户类型 1使用QQ号  2使用OpenId
	Uid          string `json:"uid"`          // 用户id
	NeedCardInfo bool   `json:"needcardinfo"` // 是否需要卡片信息
}

type SignInCardInfo struct {
	CardIndex    int32 `json:"cardindex"`    // 卡片位置 从0开始
	SignInStatus int32 `json:"signinstatus"` // 打卡状态
}

type SignInStatusInfo struct {
	ErrCode     int32            `json:"errcode"`     // 错误码不为0时，请勿使用这个数据
	ErrInfo     string           `json:"errinfo"`     // 错误信息
	TotalCount  int32            `json:"totalcount"`  // 打卡卡片总数
	SignInCount int32            `json:"signincount"` // 已经打卡天数
	CardInfo    []SignInCardInfo `json:"cardinfo"`    // 打卡信息
}

type RecvCardInfoRsp struct {
	Result  SignInStatusInfo `json:"result"`  //
	Retcode int32            `json:"retcode"` // 框架错误
	Retmsg  string           `json:"retmsg"`  // 框架错误信息
}

func CommStringMpKvSet(mpKv map[string]string, keys []string, sets []*string) {
	for index := 0; index < len(keys); index++ {
		*sets[index] = mpKv[keys[index]]
	}
}

func main() {
	m := make(map[string]string)
	fmt.Println("1111" + m["aa"] + "2222" + m["bb"])
	fmt.Println(len("1111" + m["aa"] + "2222" + m["bb"]))
}
