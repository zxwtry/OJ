package comm

import (
	"strings"
)

// 一个str，同时对多个split进行分隔
func CommStringSplit(str string, splits []string) []string {
	ss := []string{str}
	for i := 0; i < len(splits); i++ {
		tt := make([]string, 0)
		for j := 0; j < len(ss); j++ {
			sp := strings.Split(ss[j], splits[i])
			tt = append(tt, sp...)
		}
		ss = tt
	}
	return ss
}

func CommReomveSharp(tagName string) string {
	tagName = strings.TrimLeft(tagName, "#")
	tagName = strings.TrimRight(tagName, "#")
	tagName = strings.TrimLeft(tagName, "＃")
	tagName = strings.TrimRight(tagName, "＃")
	return tagName
}

func CommRemoveArrIndex(arr []string, index int) []string {
	if index < 0 || index >= len(arr) || len(arr) == 0 {
		return arr
	}
	return append(arr[0:index], arr[index+1:]...)
}

func CommInsertArrIndex(arr []string, index int, val string) []string {
	if index < 0 || index > len(arr) {
		return arr
	}
	if index == len(arr) {
		return append(arr, val)
	}
	cpy := make([]string, len(arr), len(arr))
	copy(cpy, arr)
	return append(append(arr[0:index], val), cpy[index:]...)
}

// 一个str，看finds中每一个是否存在于str中
func CommStringIndexFind(str string, finds []string) map[string]int {
	res := make(map[string]int)
	for i := 0; i < len(finds); i++ {
		index := strings.Index(str, finds[i])
		if index == -1 {
			continue
		}
		res[finds[i]] = index
	}
	return res
}

func CommTrimLeftRightBlank(str string) string {
	str = strings.Trim(str, " ")
	str = strings.Trim(str, "　")
	str = strings.Trim(str, "\t")
	str = strings.Trim(str, "\r")
	str = strings.Trim(str, "\n")
	return str
}

func CommTrimLeftRightBlankCut(str string, cuts []string) string {
	for i := 0; i < len(cuts); i++ {
		str = strings.Trim(str, cuts[i])
	}
	return str
}

func commAdjuestTagBaseInfoStringArr(in []string, cuts []string) []string {
	tagChildsNew := make([]string, 0)
	for _, tagName := range in {
		tagNameNew := CommTrimLeftRightBlankCut(tagName, cuts)
		if len(tagNameNew) == 0 {
			continue
		}
		tagChildsNew = append(tagChildsNew, tagNameNew)
	}
	return tagChildsNew
}

func CommAdjuestOnlyString(str string) string {
	cuts := []string{"\n", "\t", "\r", " ", "　", "#", "＃"}
	return CommTrimLeftRightBlankCut(str, cuts)
}
