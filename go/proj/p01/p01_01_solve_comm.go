package p01

import "os"

import "fmt"

import "io/ioutil"

import "strings"

func P01_01() {
	filePath := "C:/Users/xinweizhu/Downloads/idex_1587712460854_6473.csv"
	filePathP, filePathPErr := os.Open(filePath)
	if filePathPErr != nil {
		fmt.Println(filePathPErr)
		return
	}
	bs, bsErr := ioutil.ReadAll(filePathP)
	if bsErr != nil {
		fmt.Println(bsErr)
		return
	}
	str := string(bs)
	arr := strings.Split(str, "\n")
	for _, line := range arr {
		line = strings.Trim(line, "\r")
		line = strings.Trim(line, "\n")
		lineArr := strings.Split(line, ",")
		for lineIndex, lineStr := range lineArr {
			fmt.Printf("index:%d  str:%s\n", lineIndex, lineStr)
		}
	}
}
