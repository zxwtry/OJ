package p01

import (
	"bufio"
	"fmt"
	"os"
	"strings"

	"github.com/zxwtry/OJ/go/comm"
)

func P01_01_Read_file(fileName string) {
	f, err := os.Open(fileName)
	if err != nil {
		fmt.Printf("read file fileName[%s] err[%+v]", fileName, err)
	}

	line := 0
	input := bufio.NewScanner(f)

	m := make(map[string]int)
	for input.Scan() {
		line++
		oneLine := input.Text()
		oneLine = comm.CommTrimLeftRightBlank(oneLine)
		oneLineArr := strings.Split(oneLine, ",")
		//		fmt.Printf("line[%d]  content[%s]\n", line, comm.CommLog("%s", oneLineArr))
		if len(oneLineArr) > 19 {
			pageId := oneLineArr[19]

			pageIdCnt, _ := m[pageId]
			m[pageId] = pageIdCnt + 1
		}

	}

	fmt.Println(comm.CommLog("%s", m))
}
