package main

import (
	"encoding/base64"
	"encoding/hex"
	"fmt"
	"strings"
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

	p, err := base64.StdEncoding.DecodeString("ChXlj6/og73mhJ/lhbTotqPnmoTkuroSSgoKMjAyODA0NjMxNBIM8J2Zj/CdmLzwnZmJGg/lnKjlhbPms6jkvaDlk6YqDAoG5YWz5rOoEAEgADoPCgpJc1ZlcmlmaWVkEgEwElEKCjIyMzQ3OTMxMzYSE+mGi+a6nOW8oOmlseWEv/CfkL0aD1RB5Lmf5YWz5rOof39/fyoMCgblhbPms6gQASAAOg8KCklzVmVyaWZpZWQSATASXAoKMjY2MjQ5NzQwMBIe5rW36LS877yM546L6Lev6aOe4pq+77iP4pq+77iPGg9UQeS5n+WFs+azqH9/f38qDAoG5YWz5rOoEAEgADoPCgpJc1ZlcmlmaWVkEgEwEkgKCjMxNjUyNzA3NTYSCkZJU0jlsI/psbwaD+eBtemtguaRhOW9seW4iCoMCgblhbPms6gQASAAOg8KCklzVmVyaWZpZWQSATASTQoKMjc5MDQ3MjcyNBIS5aau5aau55qE6a2U5LuZ5aChGgzprZTlubvmrYzmiYsqDAoG5YWz5rOoEAEgADoPCgpJc1ZlcmlmaWVkEgEwElYKCTYxMDk3NzM0MxIYbG9yZW56Z2xp77yI5p2O5rO95Zu977yJGhAx5L2N5YWx5ZCM5aW95Y+LKgwKBuWFs+azqBABIAA6DwoKSXNWZXJpZmllZBIBMBJKCgoyMTQ4NTIyNjU5Egtbc11wcmV2Wy9zXRoQMeS9jeWFseWQjOWlveWPiyoMCgblhbPms6gQASAAOg8KCklzVmVyaWZpZWQSATASOQoJNDc5NjcxMjY0EgN6eHcaCFFR5aW95Y+LKgwKBuWFs+azqBABIAA6DwoKSXNWZXJpZmllZBIBMBJGCgoyNDUyOTg5NDQ5Eg/ng5/pm6jlhaXmsZ/ljZcaCFFR5aW95Y+LKgwKBuWFs+azqBABIAA6DwoKSXNWZXJpZmllZBIBMBJGCgoxOTU2OTgyMDYzEghTYWJpbmV3dRoPVEHkuZ/lhbPms6h/f39/KgwKBuWFs+azqBABIAA6DwoKSXNWZXJpZmllZBIBMBJKCgoyNjUyMzY1ODAwEgzomY7niZnpmL/mnbAaD1RB5Lmf5YWz5rOof39/fyoMCgblhbPms6gQASAAOg8KCklzVmVyaWZpZWQSATASQQoJMjQwODcwNTM4EgRaZWduGg/kurrpl7TmsLTonJzmoYMqDAoG5YWz5rOoEAEgADoPCgpJc1ZlcmlmaWVkEgEwEkQKCjI3MDM5Mjg0OTMSBumXuemXuRoP5r2u5pCt576O5bCR5aWzKgwKBuWFs+azqBABIAA6DwoKSXNWZXJpZmllZBIBMBJOCgoxMTk0ODM5MDE0Eg/nnLznnZvnnYHkuI3lvIAaEDLkvY3lhbHlkIzlpb3lj4sqDAoG5YWz5rOoEAEgADoPCgpJc1ZlcmlmaWVkEgEwEkUKBzc5MTk0MjkSCWhhbmtlemhvdRoQMeS9jeWFseWQjOWlveWPiyoMCgblhbPms6gQASAAOg8KCklzVmVyaWZpZWQSATASQQoKMzI4Mjk0MTA1OBID4oGiGg9UQeS5n+WFs+azqH9/f38qDAoG5YWz5rOoEAEgADoPCgpJc1ZlcmlmaWVkEgEwEkUKCTUxNjc0ODEwMRIIVGFtbXlqaW4aD1RB5Lmf5YWz5rOof39/fyoMCgblhbPms6gQASAAOg8KCklzVmVyaWZpZWQSATASRAoKMzA4MzY2OTkxOBIJ6L6w5bCP5bymGgzkuozog6HllLHop4EqDAoG5YWz5rOoEAEgADoPCgpJc1ZlcmlmaWVkEgEwEkUKCTk4NTE5Njc2ORIFQ2hveWkaEuWFg+awlOaOouW6l+e+juWlsyoMCgblhbPms6gQASAAOg8KCklzVmVyaWZpZWQSATASSgoKMTkxNDQwODM4MhILQ2hhcmxpZSBZYW4aEDLkvY3lhbHlkIzlpb3lj4sqDAoG5YWz5rOoEAEgADoPCgpJc1ZlcmlmaWVkEgEwGlAKEnBlcnNvblJlY29tTGlzdFVybBI6aHR0cHM6Ly9oNS5xem9uZS5xcS5jb20vdjIvd2V6b25lL3JlY29tbWVuZD9fd3Y9MyZfcHJveHk9MSgF")
	if err != nil {
		// handle error
	}
	h := hex.EncodeToString(p)
	fmt.Println(h) //

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
