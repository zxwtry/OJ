package main

import (
	"fmt"
	"time"
)

func main() {
	t := time.Now().UnixNano() / int64(time.Millisecond)
	fmt.Println(t)
}
