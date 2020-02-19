package comm

import (
	"fmt"
	"strings"
	"time"
)

type A struct {
	B bool
}

func DataFormatToString() {
	str := time.Unix(1578578433, 0).Format("2006-01-02 15:04:05")
	fmt.Println(str)
	a := A{}
	fmt.Printf("%+v", a)

	s1 := "201910201920"
	s2 := "1020234"

	fmt.Println(strings.Index(s1, s2))

	wpcTime, _ := time.Parse("20060102", "20200801")
	day := (wpcTime.Unix() - time.Now().Unix()) / (24 * 60 * 60)
	fmt.Printf("day:%d\n", day)
}

// dayToWpc 实验
func dayToWpc() {
	wpcTime, _ := time.Parse("20060102", "20200801")
	dayToWpc := (wpcTime.Unix() - time.Now().Unix()) / (24 * 60 * 60)
	fmt.Printf("xinweizhu 还有 %d 天就开始WPC比赛了。\n", dayToWpc)
}

// Notify 提醒消息
func Notify() {
	dayToWpc()
}
