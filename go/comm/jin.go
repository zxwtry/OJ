package comm

import (
	"bufio"
	"fmt"
	"io"
	"os"
	"strconv"
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

type JinDoFileParse struct {
	ArrAllP []float64
	Ar1     float64
	Ar2     float64
	Sargan  float64
	Hansen  float64
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
	filePath := "C:/install/Stata13MP/Stata13MP/1_2_range_copy.log"
	f, err := os.Open(filePath)
	if err != nil {
		panic(err)
	}
	defer f.Close()

	start := false
	cnt := 0
	cnt2 := 0

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
			cnt2++
			start = true
		}
		if start && len(line) > 3 {
			cnt++

			lines[lineIndex%lineLen] = line
			lineIndex++
			if lineIndex%lineLen == 0 {
				// 	fmt.Println(lines)
				// }
				arrAllP := make([]string, 0, 10)
				ar1 := ""
				ar2 := ""
				Sargan := ""
				Hansen := ""

				// 第一个指标，全部p值
				for line2Index := 19; line2Index <= 30; line2Index++ {
					arrValues := strings.Split(lines[line2Index], " ")
					count := 0
					for _, value := range arrValues {
						if len(value) != 0 {
							count++
							if count == 6 {
								arrAllP = append(arrAllP, value)
							}
						}
					}
				}

				arrValues := strings.Split(lines[60], " ")
				// fmt.Println(arrValues)
				ar1 = strings.Trim(arrValues[len(arrValues)-1], "\n")
				ar1 = strings.Trim(ar1, "\r")
				arrValues = strings.Split(lines[61], " ")
				ar2 = strings.Trim(arrValues[len(arrValues)-1], "\n")
				ar2 = strings.Trim(ar2, "\r")

				arrValues = strings.Split(lines[63], " ")
				Sargan = strings.Trim(arrValues[len(arrValues)-1], "\n")
				Sargan = strings.Trim(Sargan, "\r")
				arrValues = strings.Split(lines[65], " ")
				Hansen = strings.Trim(arrValues[len(arrValues)-1], "\n")
				Hansen = strings.Trim(Hansen, "\r")

				// fmt.Println(arrAllP)
				// fmt.Println(ar1)
				// fmt.Println(ar2)
				// fmt.Println(Sargan)
				// fmt.Println(Hansen)

				jinDoFileParse := JinDoFileParse{}
				arrAllPF := make([]float64, 0, len(arrAllP))
				for _, allP := range arrAllP {
					allPF, _ := strconv.ParseFloat(allP, 64)
					arrAllPF = append(arrAllPF, allPF)
				}
				jinDoFileParse.ArrAllP = arrAllPF
				ar1F, _ := strconv.ParseFloat(ar1, 64)
				ar2F, _ := strconv.ParseFloat(ar2, 64)
				SarganF, _ := strconv.ParseFloat(Sargan, 64)
				HansenF, _ := strconv.ParseFloat(Hansen, 64)
				jinDoFileParse.Ar1 = ar1F
				jinDoFileParse.Ar2 = ar2F
				jinDoFileParse.Sargan = SarganF
				jinDoFileParse.Hansen = HansenF

				fmt.Println(jinDoFileParse)
				time.Sleep(time.Millisecond * 100)
			}
		}
	}

	fmt.Println(cnt)
	fmt.Println(cnt2)
}
