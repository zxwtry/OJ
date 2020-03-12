package main

import "fmt"

func solve(m map[string]string) {
	m["aaaa"] = "AAAA"
}

type A struct {
	Test string
}

func main() {
	// comm.ChiSolveExcel()
	m := make(map[string]*A)
	a := A{
		Test: "cccc",
	}
	m["test"] = &a
	for k, v := range m {
		fmt.Printf("k:%s  v:%+v\n", k, v)
	}

	c := m["test"]
	c.Test = "bbbb"

	for k, v := range m {
		fmt.Printf("k:%s  v:%+v\n", k, v)
	}
}
