package comm

import "io/ioutil"

import "strings"

func RenameFile() {
	fileDir := "D:/file/ffout/"
	files, _ := ioutil.ReadDir(fileDir)
	for _, fileHandler := range files {
		fileName := fileHandler.Name()
		nameIndex := strings.Index(fileName, "后宫甄环传")
		if nameIndex == -1 {

		} else {

		}
	}
}
