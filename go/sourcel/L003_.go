package sourcel

import (
	"encoding/json"
	"fmt"
)

func L003() {
	// rand.Seed(time.Now().Unix())
	// for i := 0; i < 100; i++ {
	// 	res := rand.Intn(100)
	// 	fmt.Println(res)
	// }
	// var data interface{}
	// data = nil
	// bs, bsErr := json.Marshal(data)
	// fmt.Println("bs:" + string(bs))
	// fmt.Printf("bsErr:%+v\n", bsErr)

	m := make(map[int]int)
	m[100] = 1
	bs, bsErr := json.Marshal(m)
	fmt.Printf("bs:%+v", string(bs))
	fmt.Printf("bsErr:%+v", bsErr)

}
