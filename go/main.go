package main

import (
	"encoding/base64"
	"fmt"
)

type TagCategoryInfo struct {
	Name    string            `json:"name"`    // 标签/类目
	Sign    uint32            `json:"sign"`    // 类型标识
	Childs  []TagCategoryInfo `json:"childs"`  // 标签
	Relates []TagCategoryInfo `json:"relates"` // 关联标签
	Parents []TagCategoryInfo `json:"parents"` // 所属上级类目
}

func main() {
	qua := "VjFfQU5EX1NRXzguMy4zXzBfUkRNX0I="
	quaBs, _ := base64.StdEncoding.DecodeString(string(qua))
	fmt.Println(string(quaBs))

}

// rmNameFromArr
// []string 返回删除好的arr
// bool   true: arr中没有name，不需要移除。　 false:arr中有name，已经移除
func rmNameFromArr(arr []string, name string) ([]string, bool) {
	index := 0
	arrLen := len(arr)
	for ; index < len(arr); index++ {
		if arr[index] == name {
			break
		}
	}
	if index == arrLen {
		return arr, true
	} else {
		return append(arr[0:index], arr[index+1:len(arr)]...), false
	}
}
