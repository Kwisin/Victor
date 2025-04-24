package sub_dir_04

import (
	"fmt"
	"sync"
)

type Class24 struct {
	Name   string
	Age    int64
	Gender bool
}

func (c *Class24) TestMap() {
	var testMap = map[int64]int64{1: 1}
	testMap[2] = 2
	//var testMap2 map[string]int64
	//testMap2["1"] = 1
	testMap1 := make(map[string]int64)
	testMap1["1"] = 1
	delete(testMap1, "1")
	testMap1["2"] = 2
	for key, val := range testMap1 {
		fmt.Println(fmt.Sprintf("key is %s, val is %d", key, val))
	}
}
func (c *Class24) TestArray() {
	int64s := [3]int64{}
	int64s[0] = 1
	intArr := [...]int64{1, 2, 3, 4, 5}
	intArr[1] = 1

	slice1 := int64s[:2]
	slice1 = append(slice1, 1)

	ints := []int64{1, 2, 3}
	ints = append(ints, 1)
	ints = append(ints, slice1...)

	var arr [3]int64
	arr[1] = 1

	for index, val := range arr {
		fmt.Println(fmt.Sprintf("index is %d, val is %d", index, val))
	}
}
func (c *Class24) TestForEach() {

}
func (c *Class24) TestMultiAdd(mutex *sync.Mutex, waitGroup *sync.WaitGroup) {}
func (c *Class24) TestChan() {
	stringChans := make([]chan string, 10)
	for i := 0; i < 10; i++ {
		go func(index int) {
			fmt.Println(fmt.Sprintf("thread %d started", index))
			stringChans[index] <- ""
			fmt.Println(fmt.Sprintf("thread %d finished", index))
		}(i)
	}

	select {
	case <-stringChans[0]:

	}
}
func (c *Class24) TestRaceCondition() {}
func (c *Class24) TestDeadLock()      {}
