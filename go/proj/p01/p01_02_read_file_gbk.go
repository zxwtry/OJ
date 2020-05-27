package p01

import (
	"fmt"
	"io/ioutil"
	"os"
	"strings"

	"github.com/axgle/mahonia"
)

func P01_02_Read_File_GBK(fileName string) {
	f, err := os.Open(fileName)
	if err != nil {
		fmt.Printf("read file fileName[%s] err[%+v]", fileName, err)
	}

	bs, bsErr := ioutil.ReadAll(f)
	if bsErr != nil {
		fmt.Println(bsErr)
		return
	}

	picTagPre := "  - PicTag: "
	recomTagsPre := "    RecomTags: "

	newTagMap := make(map[string]bool)

	t := ""
	// gbk转utf8
	dec := mahonia.NewDecoder("gbk")
	str := dec.ConvertString(string(bs))
	arr := strings.Split(str, "\n")
	s := ""
	for _, arrOne := range arr {
		splitOne := strings.Split(arrOne, ",")
		if len(splitOne) < 10 {
			continue
		}
		recomTagMid := splitOne[2]
		recomTagMid = strings.ReplaceAll(recomTagMid, "/", ",")
		if recomTagMid == "" {
			continue
		}

		if splitOne[3] == "" {
			continue
		}

		picTagMid := splitOne[0]
		s += picTagPre + picTagMid + "\n"

		recomTags := strings.Split(recomTagMid, ",")
		for _, recomTag := range recomTags {
			if recomTag == "" {
				continue
			}

			_, newTagMapOk := newTagMap[recomTag]
			if !newTagMapOk {
				t += splitOne[3] + "#######" + recomTag + "\n"
			}
			newTagMap[recomTag] = true
		}

		s += recomTagsPre + recomTagMid + "\n\n"
	}

	ioutil.WriteFile("C:\\Users\\xinweizhu\\Downloads\\P图标签第二版_res.log", []byte(s), 0666)
	ioutil.WriteFile("C:\\Users\\xinweizhu\\Downloads\\P图标签第二版_res_2.log", []byte(t), 0666)
}
