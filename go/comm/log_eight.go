package comm

import "strings"

import "fmt"

import "strconv"

func SolveLogEight(input string) string {
	inputArr := strings.Split(input, "\\")
	fmt.Println(len(inputArr))
	output := ""

	for _, inputOne := range inputArr {
		inputInt := int64(0)
		inputBs := make([]byte, 0, 3)
		transOne := inputOne
		if len(inputOne) > 4 && inputOne[3] == '"' {
			transOne = inputOne[0:3]
		}
		if len(transOne) > 2 && len(transOne) < 4 {
			inputInt, _ = strconv.ParseInt(transOne, 8, 16)
			if inputInt != 0 {
				inputBs = append(inputBs, byte(inputInt))
			}
		}
		if len(inputBs) == 0 {
			output += inputOne
		} else {
			if len(inputOne) > 4 && inputOne[3] == '"' {
				output += string(inputBs)
				output += inputOne[3:]
			} else {
				output += string(inputBs)
			}
		}
	}

	return output
}
