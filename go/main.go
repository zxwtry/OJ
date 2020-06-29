package main

import (
	"fmt"
	"strings"

	"github.com/zxwtry/OJ/go/comm"
)

// hash
func HashFuncRobertJenkins32Bit(value uint32) uint32 {
	value = (value + 0x7ed55d16) + (value << 12)
	value = (value ^ 0xc761c23c) ^ (value >> 19)
	value = (value + 0x165667b1) + (value << 5)
	value = (value + 0xd3a2646c) ^ (value << 9)
	value = (value + 0xfd7046c5) + (value << 3)
	value = (value ^ 0xb55a4f09) ^ (value >> 16)

	return value
}

func main() {

	// st := StCategoryFilter{
	// 	TagCategory: "aaa",
	// 	PageSize:    10,
	// 	PageCurrent: 1,
	// 	Sort:        1,
	// 	SortType:    1,
	// }
	// arr := []StCategoryFilter{st, st}

	// ret := comm.CommLog("%s", arr)
	// fmt.Println(ret)

	// uin := uint32(115964137)
	// fmt.Println(HashFuncRobertJenkins32Bit(uin))
	//p01.P01_01_Read_file("C:\\Users\\xinweizhu\\Downloads\\曝光-部分.csv")
	// p01.P01_02_Read_File_GBK("C:\\Users\\xinweizhu\\Downloads\\P图标签第二版.csv")

	fmt.Println(comm.CommComplexHashCRC("479671264"))

	fmt.Println(CommTransCamel(" "))

}

func CommTransCamel(s string) string {
	arr := make([]string, 0)
	pre := 0
	cur := 0
	for ; cur < len(s); cur++ {
		if s[cur] >= 'A' && s[cur] <= 'Z' {
			arr = append(arr, s[pre:cur])
			pre = cur
		}
	}
	arr = append(arr, s[pre:cur])
	save := make([]string, 0)
	for i := 0; i < len(arr); i++ {
		if len(arr[i]) > 0 {
			save = append(save, strings.ToUpper(arr[i]))
		}
	}
	return strings.Join(save, "_")
}

// 标签排序页，请求筛选项
type StCategoryFilter struct {
	TagCategory          string   `protobuf:"bytes,1,opt,name=tagCategory" json:"tagCategory,omitempty"`
	PageSize             int32    `protobuf:"varint,2,opt,name=pageSize" json:"pageSize,omitempty"`
	PageCurrent          int32    `protobuf:"varint,3,opt,name=pageCurrent" json:"pageCurrent,omitempty"`
	Sort                 int32    `protobuf:"varint,4,opt,name=sort,enum=trpc.feedcloud.tagbasesvr .EnumListSort" json:"sort,omitempty"`
	SortType             int32    `protobuf:"varint,5,opt,name=sortType,enum=trpc.feedcloud.tagbasesvr.EnumListSortType" json:"sortType,omitempty"`
	XXX_NoUnkeyedLiteral struct{} `json:"-"`
	XXX_unrecognized     []byte   `json:"-"`
	XXX_sizecache        int32    `json:"-"`
}

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
