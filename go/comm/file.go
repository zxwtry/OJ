package comm

import (
	"fmt"
	"io/ioutil"
	"os"
	"strconv"
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
