package comm

import "math/rand"

import "fmt"

func ChiGetRatio(n int) {
	if n < 1 {
		return
	}
	avg := float64(1) / float64(n)
	sum := float64(0)
	l := make([]float64, 0, n)
	for i := 0; i < n-1; i++ {
		vv := ((rand.Float64() - 0.5) * 0.1) * avg
		sum += avg + vv
		l = append(l, avg+vv)
	}
	l = append(l, float64(1)-sum)
	for _, v := range l {
		fmt.Printf("%.5f\n", v)
	}

}
