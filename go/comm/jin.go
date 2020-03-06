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
	ExecSQL  string
	ArrAllP  []float64
	ArrCoef  []float64
	Ar1      float64
	Ar2      float64
	Sargan   float64
	Hansen   float64
	Er       float64
	Er1      float64
	Er2      float64
	Er22     float64
	ErCoef   float64
	Er1Coef  float64
	Er2Coef  float64
	Er22Coef float64
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

var JIN_ER_FACTOR float64 = 3
var JIN_ER1_FACTOR float64 = 3
var JIN_AR1_FACTOR float64 = 3
var JIN_AR2_FACTOR float64 = 3
var JIN_H_FACTOR float64 = 3
var JIN_S_FACTOR float64 = 3

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

	if v.Ar1 < 0.1 &&
		v.Ar2 > 0.1 &&
		v.Sargan > 0.1 &&
		v.Hansen > 0.1 &&
		v.Er < 0.1 &&
		v.Er1 < 0.1 &&
		v.ErCoef*v.Er1Coef < 0 {
		sum -= 1
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
	for _, allFileOne := range allFile {
		fmt.Println("solve: " + allFileOne)
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
	for i := 0; i < 50 && i < len(jinDoFileParseList); i++ {
		JinWriteLog.WriteString(jinDoFileParseList[i].ExecSQL + "\r\n")
		JinWriteLog.WriteString(fmt.Sprintf("Er:\t\t%5.3f\r\n", jinDoFileParseList[i].Er))
		JinWriteLog.WriteString(fmt.Sprintf("Er1:\t\t%f\r\n", jinDoFileParseList[i].Er1))
		JinWriteLog.WriteString(fmt.Sprintf("Er2:\t\t%f\r\n", jinDoFileParseList[i].Er2))
		JinWriteLog.WriteString(fmt.Sprintf("Er22:\t\t%f\r\n", jinDoFileParseList[i].Er22))
		JinWriteLog.WriteString(fmt.Sprintf("Ar1:\t\t%f\r\n", jinDoFileParseList[i].Ar1))
		JinWriteLog.WriteString(fmt.Sprintf("Ar2:\t\t%f\r\n", jinDoFileParseList[i].Ar2))
		JinWriteLog.WriteString(fmt.Sprintf("Sargan:\t\t%f\r\n", jinDoFileParseList[i].Sargan))
		JinWriteLog.WriteString(fmt.Sprintf("Hansen:\t\t%f\r\n", jinDoFileParseList[i].Hansen))
		JinWriteLog.WriteString(fmt.Sprintf("AllP:%+v\r\n", jinDoFileParseList[i].ArrAllP))
		JinWriteLog.WriteString(fmt.Sprintf("ErCoef:\t\t%+v\r\n", jinDoFileParseList[i].ErCoef))
		JinWriteLog.WriteString(fmt.Sprintf("Er1Coef:\t\t%+v\r\n", jinDoFileParseList[i].Er1Coef))
		JinWriteLog.WriteString(fmt.Sprintf("Er2Coef:\t\t%+v\r\n", jinDoFileParseList[i].Er2Coef))
		JinWriteLog.WriteString(fmt.Sprintf("Er22Coef:\t\t%+v\r\n", jinDoFileParseList[i].Er22Coef))
		JinWriteLog.WriteString(fmt.Sprintf("ArrCoef:%+v\r\n\r\n\r\n", jinDoFileParseList[i].ArrCoef))
	}
}

func JinParseLogToSrtList(filePath string, jinDoFileParseList *[]JinDoFileParse) {
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

			if cnt2 > 0 {
				// fmt.Println(lineIndex)
				jinDoFileParse := JinParseLogToSrt(lines, lineIndex)
				*jinDoFileParseList = append(*jinDoFileParseList, jinDoFileParse)
			}
			cnt2++
			lineIndex = 0
			start = true
		}
		if start && len(line) > 3 {
			cnt++
			lines[lineIndex%lineLen] = strings.ReplaceAll(strings.ReplaceAll(line, "\n", ""), "\r", "")
			lineIndex++
		}
	}
}

func JinParseLogToSrtAllP(lines []string, linesLen int, parse *JinDoFileParse) {
	parse.ArrAllP = make([]float64, 0, 10)
	parse.ArrCoef = make([]float64, 0, 10)
	for i := 0; i < linesLen; i++ {
		line := lines[i]
		if strings.Index(line, "|") != -1 {
			arrValues := strings.Split(line, " ")
			count := 0
			for _, value := range arrValues {
				if len(value) != 0 {
					count++
					if count == 3 {
						valueFloat, valueFloatErr := strconv.ParseFloat(value, 64)
						if valueFloatErr == nil {
							parse.ArrCoef = append(parse.ArrCoef, valueFloat)
							if strings.Index(line, "er |") != -1 {
								parse.ErCoef = valueFloat
							}
							if strings.Index(line, "er1 |") != -1 {
								parse.Er1Coef = valueFloat
							}
							if strings.Index(line, "er2 |") != -1 {
								parse.Er2Coef = valueFloat
							}
							if strings.Index(line, "er22 |") != -1 {
								parse.Er22Coef = valueFloat
							}
						}
					}
					if count == 6 {
						valueFloat, valueFloatErr := strconv.ParseFloat(value, 64)
						if valueFloatErr == nil {
							parse.ArrAllP = append(parse.ArrAllP, valueFloat)
							if strings.Index(line, "er |") != -1 {
								parse.Er = valueFloat
							}
							if strings.Index(line, "er1 |") != -1 {
								parse.Er1 = valueFloat
							}
							if strings.Index(line, "er2 |") != -1 {
								parse.Er2 = valueFloat
							}
							if strings.Index(line, "er22 |") != -1 {
								parse.Er22 = valueFloat
							}
						}
					}
				}
			}
		}
	}
	// if erCof*er1Cof  {
	// 	// return arrAllP, 1000, 1000
	// }
	return
}

func JinParseLogToSrtFilter(lines []string, linesLen int, filter string) float64 {
	for i := 0; i < linesLen; i++ {
		line := lines[i]
		if strings.Index(line, filter) != -1 {
			arrValues := strings.Split(line, " ")
			ar1 := arrValues[len(arrValues)-1]
			valueFloat, valueFloatErr := strconv.ParseFloat(ar1, 64)
			if valueFloatErr != nil {
				return 1000
			}
			return valueFloat
		}
	}
	return 1000
}

func JinParseLogToSrtSQL(lines []string, linesLen int) string {
	sql := ""
	for i := 0; i < linesLen; i++ {
		line := lines[i]
		if len(line) > 2 && (line[0] == '.' || line[0] == '>') {
			sql += line[2:]
		} else {
			return sql
		}
	}
	return sql
}

func JinParseLogToSrt(lines []string, linesLen int) JinDoFileParse {
	jinDoFileParse := JinDoFileParse{}
	// jinDoFileParse.ArrAllP, jinDoFileParse.Er, jinDoFileParse.Er1, jinDoFileParse.CofM, jinDoFileParse.Er2, jinDoFileParse.Er22 =
	JinParseLogToSrtAllP(lines, linesLen, &jinDoFileParse)
	jinDoFileParse.Ar1 = JinParseLogToSrtFilter(lines, linesLen, "Arellano-Bond test for AR(1)")
	jinDoFileParse.Ar2 = JinParseLogToSrtFilter(lines, linesLen, "Arellano-Bond test for AR(2)")
	jinDoFileParse.Sargan = JinParseLogToSrtFilter(lines, linesLen, "Sargan test of overid.")
	jinDoFileParse.Hansen = JinParseLogToSrtFilter(lines, linesLen, "Hansen test of overid.")
	jinDoFileParse.ExecSQL = JinParseLogToSrtSQL(lines, linesLen)

	return jinDoFileParse
}
