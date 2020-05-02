package leetcode

func L001_01_twoSum(nums []int, target int) []int {
	m := make(map[int]int)
	for i, num := range nums {
		j, ok := m[target-num]
		if ok {
			return []int{j, i}
		}
		m[num] = i
	}
	return []int{-1, -1}
}
