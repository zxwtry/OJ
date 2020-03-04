package comm

import (
	"bufio"
	"fmt"
	"io"
	"os"
	"strings"
	"time"
)

func JinGenerateDeep(index, len int, args []interface{}, minValue, maxValue int, f *os.File) {
	if index == len {
		formatStr := "xtabond2  dtfp l1.dtfp  er  er1   fee age  re2  ca1  cp1 de1  ab2    ,gmm(dtfp ,l(%d %d) collapse ) gmm( re2,l(%d %d) collapse )  gmm(ca1  ,l(%d %d) collapse )  gmm(cp1  ,l(%d %d) collapse )  gmm(de1   ,l(%d %d) collapse )  gmm(  ab2 ,l(%d %d) collapse )      iv(l(%d %d)age  )  iv(l(%d %d) fee   )  iv(l(%d %d) er1  ) iv(l(%d %d) er  )robust two\r\n"
		doStr := fmt.Sprintf(formatStr, args...)
		f.WriteString(doStr)
		return
	}
	for value := minValue; value <= maxValue; value++ {
		args[index] = value
		JinGenerateDeep(index+1, len, args, minValue, maxValue, f)
	}
}

func JinGenerateDoFile() {
	minValue := 1
	maxValue := 2
	argsLen := 20
	f, _ := os.OpenFile("C:/install/data/1_2_range.do", os.O_RDWR|os.O_APPEND|os.O_CREATE, 0666)
	args := make([]interface{}, 0, argsLen)
	for i := 0; i < argsLen; i++ {
		args = append(args, minValue)
	}
	JinGenerateDeep(0, argsLen, args, minValue, maxValue, f)

	f.Close()
}

func JinParseLog() {
	filePath := "C:/install/Stata13MP/Stata13MP/minixi_04.log"
	f, err := os.Open(filePath)
	if err != nil {
		panic(err)
	}
	defer f.Close()

	start := false
	cnt := 0

	lineLen := 101
	lines := make([]string, lineLen, lineLen)
	lineIndex := 0

	rd := bufio.NewReader(f)
	for {
		line, err := rd.ReadString('\n') //以'\n'为结束符读入一行

		if err != nil || io.EOF == err {
			break
		}
		// fmt.Println(line)
		if strings.Index(line, ". xtabond2  dtfp l1.dtfp") != -1 {
			start = true
		}
		if start && len(line) > 3 {

			lines[lineIndex%lineLen] = line
			lineIndex++
			if lineIndex%lineLen == 0 {
				fmt.Println(lines)
				time.Sleep(10 * time.Second)
			}
		}
	}

	fmt.Println(cnt)
}
