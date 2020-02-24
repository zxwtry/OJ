package comm

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"os"
	"strconv"
	"strings"
)

func RenameFile() {
	fileDirFormat := "D:/file/video/13017548/%d/lua.flv.bili2api.80/"
	for i := 10; i <= 28; i++ {
		fileDir := fmt.Sprintf(fileDirFormat, i)
		fileList, _ := ioutil.ReadDir(fileDir)
		for _, filePath := range fileList {
			fileName := filePath.Name()
			if len(fileName) > 4 && fileName[len(fileName)-4:] == ".blv" {
				blvIndexStr := fileName[:len(fileName)-4]
				blvIndex, _ := strconv.Atoi(blvIndexStr)
				fileNewName := fmt.Sprintf("D:/file/video/连城诀%d-%02d.blv", i, blvIndex)
				fmt.Println(fileDir + fileName)
				fmt.Println(fileNewName)
				os.Rename(fileDir+fileName, fileNewName)
			}
		}
	}
}

func MergeFile() {
	fileDir := "D:/file/ffout/"
	fileList, _ := ioutil.ReadDir(fileDir)
	for _, fileInfo := range fileList {
		fileName := fileInfo.Name()
		fileNamePart := strings.Split(fileName, "-")
		if len(fileNamePart) == 2 {
			fileHandler, _ := os.Open(fileDir + fileName)
			fileAllBytes, _ := ioutil.ReadAll(fileHandler)
			fileNewName := fileDir + fileNamePart[0] + ".mp3"
			ioutil.WriteFile(fileNewName, fileAllBytes, os.ModeAppend)
			fileHandler.Close()
		}
	}
}

func DelFile() {
	fileDir := "D:/file/ffout/"
	fileList, _ := ioutil.ReadDir(fileDir)
	for _, filePath := range fileList {
		fileIndex := strings.Index(filePath.Name(), "-")
		if fileIndex != -1 {
			os.Remove(fileDir + filePath.Name())
			fmt.Println(fileDir + filePath.Name())
		}
	}
}

type BZFileInfoEP struct {
	Index      string `json:"index"`
	IndexTitle string `json:"index_title"`
}
type BZFileInfo struct {
	Ep BZFileInfoEP `json:"ep"`
}

func ReadBZFileInfo(path string) *BZFileInfo {
	filePath, _ := os.Open(path)
	defer filePath.Close()
	fileBytes, _ := ioutil.ReadAll(filePath)
	var bzFileInfo BZFileInfo
	json.Unmarshal(fileBytes, &bzFileInfo)
	return &bzFileInfo
}

func SolveBzBlv() {
	fileDir := "D:/file/video/s_28898/"
	fileList, _ := ioutil.ReadDir(fileDir)
	for _, fileHandler := range fileList {
		fileName := fileHandler.Name()
		entryName := fmt.Sprintf("D:/file/video/s_28898/%s/entry.json", fileName)
		bzFileInfo := ReadBZFileInfo(entryName)
		fmt.Printf("%+v\n", bzFileInfo)

	}
}
