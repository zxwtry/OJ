package main

import (
	"fmt"
	"os"
	"strconv"
	"strings"

	"github.com/zxwtry/OJ/go/comm"
)

func main() {
	args := os.Args
	if len(args) < 2 {
		return
	}
	switch args[1] {
	case "gen":
		filePrefix := args[2]
		fileLimit := args[3]
		fileLimitInt, err := strconv.ParseUint(fileLimit, 10, 64)
		if fileLimitInt == 0 || err != nil {
			fmt.Println("fileLimitInt " + fileLimit + " " + err.Error())
			return
		}
		startValue := args[4]
		startValueInt, err := strconv.ParseUint(startValue, 10, 64)
		if startValueInt == 0 || err != nil {
			fmt.Println("startValueInt" + startValue)
			return
		}
		endValue := args[5]
		endValueInt, err := strconv.ParseUint(endValue, 10, 64)
		if endValueInt == 0 || err != nil {
			fmt.Println("endValueInt" + endValue)
			return
		}

		fileExecArr := args[6:]
		fileExec := strings.Join(fileExecArr, " ")
		comm.JinGenerateDoFile(filePrefix, fileLimitInt, startValueInt, endValueInt, fileExec)
	case "log":
		filePath := args[2]
		filePath = strings.Replace(filePath, "\\", "/", -1)
		files := args[3:]
		allFile := make([]string, 0, 10)
		outFile := ""
		for _, fileName := range files {
			if strings.Index(fileName, ".log") != -1 {
				fileName = fileName[0 : len(fileName)-4]
			}
			if len(outFile) == 0 {
				outFile = filePath + "/" + fileName + "_res.txt"
			}
			allFile = append(allFile, filePath+"/"+fileName+".log")
		}
		comm.JinParseLog(allFile, outFile)
		fmt.Println("outFile:" + outFile)
	}
}
