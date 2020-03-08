package comm

import "math/rand"

import "fmt"

import "strings"

import "strconv"

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

type ChiSolveExcelStruct struct {
	CityName   string
	OriginData []float64
	ToOneData  []float64
	Value      float64
	PositionX  float64
	PositionY  float64
}

func ChiSolveExcel() {
	val := `广州	深圳	珠海	佛山	惠州	东莞	中山	江门	肇庆	澳门	香港	指标权重
113.27	114.05	113.57	113.12	114.42	113.75	113.38	113.08	112.47	113.33	114.08	0
23.13	22.55	22.27	23.02	23.12	23.05	22.52	22.58	23.05	22.13	22.2	0
134214.24	298410.79	27053.27	69887.52	40172.24	90457.03	25331.9	22859.16	9166.12	0	29846	0.05614
600.17	1161.93	92.15	254.77	94.19	236.32	61.12	62.63	22.72	0	237.32	0.058
2.63	4.8	3.16	2.56	2.3	2.85	1.68	2.16	1.03	0	0.8	0.05647
11746	14416	2041	3949	1105	5791	2380	1241	410	0	0	0.05521
82	13	10	13	5	9	5	4	6	12	12	0.05514
237	25	9	9	4	13	5	7	8	0	0	0.05659
163.67	554.98	45.52	54.68	22.17	39.34	41.05	13.28	6.99	0	17.45	0.05314
6.53	12.96	7.95	6.78	4.07	5.14	9.37	3.51	2.21	0	0.6	0.05365
1000	3369.54	245	567.76	263	372.69	250	230	48.69	0	0	0.05332
877.9	2769.27	198.58	686.93	381.31	365.01	397.51	264.31	169.07	85.12	0	0.05445
9810	9751	386	342	33	272	242	374	11	0	0	0.05564
719.38	576.93	40.9	7.47	3.19	18.32	3.4	9.65	0.21	0	0	0.0573
10797	21310	3452	5058	1445	6716	1875	712	294	0	7253	0.05397
50169	69970	13139	29709	5222	24674	8165	4089	2146	0	13992	0.05489
2793.05	1523.5	1247.04	1242.01	1542.57	3924.44	349.65	425.86	129.16	0	0	0.05454
8986.75	23871.71	2712.01	9634.22	4178.55	11957.86	2809.77	2130.21	942.39	0	0	0.05538
2648	886	334	598	168	426	265	293	91	0	0	0.05435
8.05	3.56	0.59	0.78	0.37	0.99	0.41	0.6	0.15	0	0	0.06182`
	arrVal := strings.Split(val, "\n")
	arrValF := make([][]float64, 0, 10)
	arrToOneF := make([][]float64, 0, 10)
	for _, strVal := range arrVal {
		arrStrVal := strings.Split(strVal, "\t")
		arrValFOne := make([]float64, 0, len(arrStrVal))
		arrToOneFOne := make([]float64, 0, len(arrStrVal))
		sum := 0.0
		for index, strStrVal := range arrStrVal {
			strStrValF, _ := strconv.ParseFloat(strStrVal, 64)
			arrValFOne = append(arrValFOne, strStrValF)
			if index != len(arrStrVal)-1 {
				sum += strStrValF
			}
		}
		for _, vv := range arrValFOne {
			arrToOneFOne = append(arrToOneFOne, vv/sum)
		}

		arrValF = append(arrValF, arrValFOne)
		arrToOneF = append(arrToOneF, arrToOneFOne)
	}
	cityArr := strings.Split(arrVal[0], "\t")
	cityList := make([]ChiSolveExcelStruct, len(arrValF[0])-1, len(arrValF[0])-1)

	// 经度max
	xMax := arrValF[1][0]
	xMin := arrValF[1][0]
	for cityIndex := 0; cityIndex < len(cityList); cityIndex++ {
		if xMax < arrValF[1][cityIndex] {
			xMax = arrValF[1][cityIndex]
		}
		if xMin > arrValF[1][cityIndex] {
			xMin = arrValF[1][cityIndex]
		}
	}
	// 纬度min
	yMax := arrValF[2][0]
	yMin := arrValF[2][0]
	for cityIndex := 0; cityIndex < len(cityList); cityIndex++ {
		if yMax < arrValF[2][cityIndex] {
			yMax = arrValF[2][cityIndex]
		}
		if yMin > arrValF[2][cityIndex] {
			yMin = arrValF[2][cityIndex]
		}
	}

	for cityIndex := 0; cityIndex < len(cityList); cityIndex++ {
		originData := make([]float64, len(arrVal)-3, len(arrVal)-3)
		toOneData := make([]float64, len(arrVal)-3, len(arrVal)-3)
		value := 0.0
		for rowIndex := 3; rowIndex < len(arrVal); rowIndex++ {
			originData[rowIndex-3] = arrValF[rowIndex][cityIndex]
			toOneData[rowIndex-3] = arrToOneF[rowIndex][cityIndex]
			value += arrToOneF[rowIndex][cityIndex] + arrValF[rowIndex][len(arrValF[0])-1]
		}
		cityStruct := ChiSolveExcelStruct{
			CityName:   cityArr[cityIndex],
			OriginData: originData,
			ToOneData:  toOneData,
			Value:      value,
			PositionX:  0.5 + (arrValF[1][cityIndex]-xMin)/(xMax-xMin)*4,
			PositionY:  0.5 + (arrValF[2][cityIndex]-yMin)/(yMax-yMin)*4,
		}
		// fmt.Println(cityStruct)
		fmt.Printf("%s\t\t(%.1f,%.1f)\t\t%.5f\n", cityStruct.CityName, cityStruct.PositionX, cityStruct.PositionY, cityStruct.Value)
	}
	// 计算每个栏目归一值
}
