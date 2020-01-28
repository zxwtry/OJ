package sourcel

import "sort"

/**
 * User:  zxwtry
 * Date:  2018/4/2
 * Time:  8:35
 */

func L001A_twoSum(nums []int, target int) []int {
	len := len(nums)
	for i := 0; i < len; i++ {
		for j := i + 1; j < len; j++ {
			if (nums[i] + nums[j]) == target {
				return []int{i, j}
			}
		}
	}
	return []int{0, 0}
}

func L001B_twoSum(nums []int, target int) []int {
	i := 0
	j := len(nums) - 1
	numsSave := make([]int, len(nums))
	copy(numsSave, nums)
	sort.Ints(nums)
	for i < j {
		sum := nums[i] + nums[j]
		if sum == target {
			return []int{getIndexOfArray(numsSave, nums[i], true),
				getIndexOfArray(numsSave, nums[j], false)}
		} else if sum > target {
			j--
		} else {
			i++
		}
	}
	return []int{0, 0}
}

func getIndexOfArray(array []int, value int, ascend bool) int {
	index := 0
	arrayLen := len(array)
	if !ascend {
		index = arrayLen - 1
	}
	for {
		if array[index] == value {
			return index
		}
		if ascend {
			index++
			if index >= arrayLen {
				break
			}
		} else {
			index--
			if index < 0 {
				break
			}
		}
	}
	return 0
}

type IndexValue struct {
	index int
	value int
}

type IndexValues []IndexValue

func (s IndexValues) Len() int {
	return len(s)
}

func (s IndexValues) Less(i, j int) bool {
	return s[i].value < s[j].value
}

func (s IndexValues) Swap(i, j int) {
	s[i], s[j] = s[j], s[i]
}

func L001C_twoSum(nums []int, target int) []int {
	s := make(IndexValues, len(nums))
	for i, v := range nums {
		s[i] = IndexValue{i, v}
	}
	sort.Sort(s)
	i, j := 0, (len(nums) - 1)
	for i < j {
		sum := s[i].value + s[j].value
		if sum == target {
			return []int{s[i].index, s[j].index}
		} else if sum < target {
			i++
		} else {
			j--
		}
	}
	return []int{0, 0}
}
