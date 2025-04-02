package sub_dir_04

import (
	"fmt"
	"sync"
)

type Class04 struct {
	Name   string
	Age    int64
	Height int64
	Value  int64
}

func (c *Class04) TestMap() {
	map1 := make(map[string]string)
	var map2 map[string]int64
	map1["1"] = "1"
	map1["2"] = "2"
	delete(map1, "1")
	map2["1"] = 1

	value, ok := map1[""]
	if ok {
		fmt.Println(value)
	}

	for key, val := range map1 {
		fmt.Println(fmt.Sprintf("key is %s val is %s", key, val))
	}
}
func (c *Class04) TestArray() {
	intArr := [3]int64{}
	int64s := [...]int64{1, 2, 3, 4}

	intArr[2] = 1
	if len(intArr) > 0 {
		fmt.Println("intArr is not empty")
	}

	slice1 := int64s[1:3]
	i := make([]int64, 3, 6)
	i = append(i, 1)
	for index, item := range i {
		fmt.Println(fmt.Sprintf("index is %d item is %d", index, item))
	}
	i = append(i, slice1...)

}

func (c *Class04) TestForEach() {}
func (c *Class04) TestMultiAdd(mutex *sync.Mutex, waitGroup *sync.WaitGroup) {
	if mutex != nil {
		mutex.Lock()
		defer mutex.Unlock()
	}
	for i := 0; i < 10000; i++ {
		c.Value++
	}
	if waitGroup != nil {
		waitGroup.Done()
	}
}

func (c *Class04) TestChan() {
	i := make([]chan string, 10)
	for j := range i {
		i[j] = make(chan string, 1)
	}

	waitGroup := &sync.WaitGroup{}
	for index := 0; index < 5; index++ {
		waitGroup.Add(1)
		go func(idx int) {
			i[idx] <- fmt.Sprintf("%d", idx)
			close(i[idx])
			waitGroup.Done()
		}(index)
	}
	waitGroup.Wait()
	for _, item := range i[:5] {
		select {
		case chanVal, ok := <-item:
			if ok {
				fmt.Println(chanVal)
			}
		}
	}

}
func (c *Class04) TestRaceCondition() {}
func (c *Class04) TestDeadLock() {
	syncMap := &sync.Map{}
	syncMap.Store("", "")
}
