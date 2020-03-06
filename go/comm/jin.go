package comm

import (
	"bufio"
	"fmt"
	"io"
	"os"
	"sort"
	"strconv"
	"strings"
)

var JinWrite *os.File = nil

func JinGenerateWrite(lineCount uint64, writeStr string, filePrefix string, fileLimitInt uint64) {
	if lineCount%fileLimitInt == 1 || JinWrite == nil {
		// 替换成新JinWrite
		if JinWrite != nil {
			JinWrite.Close()
		}
		var err error
		JinWrite, err = os.OpenFile(fmt.Sprintf(filePrefix+"_%05d.do", lineCount/fileLimitInt), os.O_RDWR|os.O_APPEND|os.O_CREATE, 0666)
		if err != nil {
			JinWrite = nil
			return
		}

		JinWrite.WriteString(fmt.Sprintf("log using "+filePrefix+"_%05d.log,replace\r\n", lineCount/fileLimitInt))
		JinWrite.WriteString("set more off\r\n")
	}
	if JinWrite != nil {
		JinWrite.WriteString(writeStr + "\r\n")
	}
}

func JinGenerateDeep(index, len int, args []interface{}, minValue, maxValue int, lineCount *uint64, formatStr string, filePrefix string, fileLimitInt uint64) {
	if index == len {
		for argIndex := 0; 2*argIndex < len; argIndex++ {
			i1 := 2 * argIndex
			i2 := i1 + 1
			v1 := args[i1].(int)
			v2 := args[i2].(int)
			if v1 > v2 {
				return
			}
		}
		// formatStr := "xtabond2 dtfp l1.dtfp er er1  fee age re2 ca1 cp1 de1 ab2  ,gmm(dtfp ,l(%d %d) collapse ) gmm( re2,l(%d %d) collapse ) gmm(ca1 ,l(%d %d) collapse ) gmm(cp1 ,l(%d %d) collapse ) gmm(de1  ,l(%d %d) collapse ) gmm( ab2 ,l(%d %d) collapse )   iv(l(%d %d)age fee er ) robust two\r\n"
		writeStr := fmt.Sprintf(formatStr, args...)
		(*lineCount)++
		// f.WriteString(doStr)
		JinGenerateWrite(*lineCount, writeStr, filePrefix, fileLimitInt)
		return
	}
	for value := minValue; value <= maxValue; value++ {
		args[index] = value
		JinGenerateDeep(index+1, len, args, minValue, maxValue, lineCount, formatStr, filePrefix, fileLimitInt)
	}
}

type JinDoFileParse struct {
	ExecSQL string
	ArrAllP []float64
	Ar1     float64
	Ar2     float64
	Sargan  float64
	Hansen  float64
	Er      float64
	Er1     float64
}

func JinGenerateDoFile(filePrefix string, fileLimitInt uint64, startValue uint64, endValue uint64, fileExec string) {
	minValue := int(startValue)
	maxValue := int(endValue)
	if minValue < 0 || minValue > 10 || maxValue < minValue || maxValue < 0 || maxValue > 10 {
		return
	}
	fileExecArr := strings.Split(fileExec, "l(1 1)")
	fileExec = strings.Join(fileExecArr, "l(%d %d)")
	argsLen := (len(fileExecArr) - 1) * 2
	if argsLen < 1 {
		return
	}
	var lineCount uint64 = 0
	//f, _ := os.OpenFile("C:/install/data/1_3_14_jin_all.do", os.O_RDWR|os.O_APPEND|os.O_CREATE, 0666)
	args := make([]interface{}, 0, argsLen)
	for i := 0; i < argsLen; i++ {
		args = append(args, minValue)
	}
	JinGenerateDeep(0, argsLen, args, minValue, maxValue, &lineCount, fileExec, filePrefix, fileLimitInt)

	//f.Close()
	if JinWrite != nil {
		JinWrite.Close()
	}
}

type JinDoFileParseList []JinDoFileParse

func (l JinDoFileParseList) Len() int {
	return len(l)
}

const JIN_ER_FACTOR = 3
const JIN_ER1_FACTOR = 3
const JIN_AR1_FACTOR = 3
const JIN_AR2_FACTOR = 3
const JIN_H_FACTOR = 3
const JIN_S_FACTOR = 3

func (l JinDoFileParseList) GetSumAllP(i int) float64 {
	v := l[i]
	sum := float64(0)
	for _, vv := range v.ArrAllP {
		if vv > 0.1 {
			sum += vv
		}
	}

	// 如果Er < 0.1 是符合预期的
	if v.Er < 0.1 {
		sum += 0
	} else {
		sum += JIN_ER_FACTOR * v.Er
	}

	// 如果Er1 < 0.1 是符合预期的
	if v.Er1 < 0.1 {
		sum += 0
	} else {
		sum += JIN_ER1_FACTOR * v.Er1
	}

	// 如果AR1 < 0.1 是符合预期的
	if v.Ar1 < 0.1 {
		sum += 0
	} else {
		sum += JIN_AR1_FACTOR * v.Ar1
	}

	// 如果AR2 > 0.1 是符合预期的
	if v.Ar2 > 0.1 {
		sum += 0
	} else {
		sum += JIN_AR2_FACTOR * v.Ar2 * 5
	}

	// 如果H > 0.1 是符合预期的
	if v.Hansen > 0.1 {
		sum += 0
	} else {
		sum += JIN_H_FACTOR * v.Hansen * 5
	}

	// 如果S > 0.1 是符合预期的
	if v.Sargan > 0.1 {
		sum += 0
	} else {
		sum += JIN_S_FACTOR * v.Sargan * 5
	}

	return sum
}

func (l JinDoFileParseList) Less(i, j int) bool {
	iSumAllP := l.GetSumAllP(i)
	jSumAllP := l.GetSumAllP(j)
	return iSumAllP < jSumAllP
}

func (l JinDoFileParseList) Swap(i, j int) {
	l[i], l[j] = l[j], l[i]
}

func JinParseLog(allFile []string, allOutFile string) {
	jinDoFileParseList := make([]JinDoFileParse, 0, 10)
	// JinParseLogToSrtList("C:/install/Stata13MP/Stata13MP/1_3_14_A.log", &jinDoFileParseList)
	// JinParseLogToSrtList("C:/install/Stata13MP/Stata13MP/1_3_14_2_A.log", &jinDoFileParseList)
	for _, allFileOne := range allFile {
		fmt.Println("allFileOne: " + allFileOne)
		JinParseLogToSrtList(allFileOne, &jinDoFileParseList)
	}
	os.Remove(allOutFile)

	sort.Sort(JinDoFileParseList(jinDoFileParseList))

	JinWriteLog, err := os.OpenFile(allOutFile, os.O_RDWR|os.O_APPEND|os.O_CREATE, 0666)
	if err != nil {
		fmt.Println(err.Error())
		return
	}
	fmt.Printf("jinDoFileParseListLen:%d\r\n", len(jinDoFileParseList))
	defer JinWriteLog.Close()
	for i := 0; i < 100 && i < len(jinDoFileParseList); i++ {
		fmt.Println(jinDoFileParseList[i].ExecSQL)
		JinWriteLog.WriteString(jinDoFileParseList[i].ExecSQL + "\r\n\r\n")
	}
}

func JinParseLogToSrtList(filePath string, jinDoFileParseList *[]JinDoFileParse) {
	//filePath := "C:/install/Stata13MP/Stata13MP/1_3_14_A.log"
	f, err := os.Open(filePath)
	if err != nil {
		panic(err)
	}
	defer f.Close()

	start := false
	cnt := 0
	cnt2 := 0

	lineLen := 10000
	lines := make([]string, lineLen, lineLen)
	lineIndex := 0

	//jinDoFileParseList := make([]JinDoFileParse, 0, 10)

	rd := bufio.NewReader(f)
	for {
		line, err := rd.ReadString('\n') //以'\n'为结束符读入一行

		if err != nil || io.EOF == err {
			break
		}
		// fmt.Println(line)
		if strings.Index(line, "xtabond2") != -1 {
			cnt2++
			lineIndex = 0
			start = true
		}
		if start && len(line) > 3 {
			cnt++

			lines[lineIndex%lineLen] = line
			lineIndex++
			if strings.Index(line, "xtabond2") != -1 && cnt2 > 1 {
				// 	fmt.Println(lines)
				// }
				jinDoFileParse := JinParseLogToSrt(lines)
				// jinDoFileParseJson, _ := json.Marshal(jinDoFileParse)
				// fmt.Println(string(jinDoFileParseJson))
				// time.Sleep(time.Second)
				*jinDoFileParseList = append(*jinDoFileParseList, jinDoFileParse)
			}
		}
	}
}

func JinParseLogToSrt(lines []string) JinDoFileParse {

	arrAllP := make([]string, 0, 10)
	ar1 := ""
	ar2 := ""
	Sargan := ""
	Hansen := ""

	// 第一个指标，全部p值
	for line2Index := 18; line2Index <= 29; line2Index++ {
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

	arrValues := strings.Split(lines[53], " ")
	// fmt.Println(arrValues)
	ar1 = strings.Trim(arrValues[len(arrValues)-1], "\n")
	ar1 = strings.Trim(ar1, "\r")
	arrValues = strings.Split(lines[54], " ")
	ar2 = strings.Trim(arrValues[len(arrValues)-1], "\n")
	ar2 = strings.Trim(ar2, "\r")

	arrValues = strings.Split(lines[56], " ")
	Sargan = strings.Trim(arrValues[len(arrValues)-1], "\n")
	Sargan = strings.Trim(Sargan, "\r")
	arrValues = strings.Split(lines[58], " ")
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

	jinDoFileParse.ExecSQL = strings.Replace(lines[0]+strings.Replace(lines[1], "> ", "", 1), "\r\n", "", -1)
	jinDoFileParse.Er = arrAllPF[1]
	jinDoFileParse.Er1 = arrAllPF[2]
	return jinDoFileParse
}
