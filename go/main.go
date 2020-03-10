package main

import "fmt"

func main() {
	//comm.JinMain()

	m := make(map[string]bool)
	m["aaa"] = true
	m["bbb"] = false
	fmt.Println(len(m))
}
