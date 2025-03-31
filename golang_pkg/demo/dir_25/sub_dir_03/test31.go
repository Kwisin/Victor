package sub_dir_03

import (
	"fmt"
	"sync"
	"time"
)

type Class31 struct {
	ModelName string
	Scope     int64
}

func (c *Class31) TestMap() {
	m := make(map[string]int64)
	m["1"] = 1
	delete(m, "1")
	m["2"] = 2
	for key, val := range m {
		fmt.Println(fmt.Sprintf("key is %s, val is %d", key, val))
	}

	var tempMap = map[string]int64{
		"22": 2,
	}
	tempMap["11"] = 1
	for key, val := range tempMap {
		fmt.Println(fmt.Sprintf("key is %s, val is %d", key, val))
	}

	var tempMap1 map[string]int64
	fmt.Println(tempMap1 == nil)
}
func (c *Class31) TestArray() {
	int64s := [5]int64{}
	int64s[1] = 3

	int64s1 := [...]int64{12, 3, 4}
	int64s1[1] = 2

	var tempArr [4]int64
	fmt.Println(tempArr)

	slice1 := int64s[0:3]
	slice2 := make([]int64, 3, 6)
	slice3 := []int64{1, 2, 3}
	var slice4 []int64
	for index, item := range slice1 {
		fmt.Println(fmt.Sprintf("index is %d, item is %d", index, item))
	}
	fmt.Println(slice2)
	fmt.Println(slice3)
	fmt.Println(slice4)

}
func (c *Class31) TestForEach() {
	i := 0
Loop:
	fmt.Println(i)
	if i == 8 {
		return
	}
	i++
	goto Loop
	fmt.Println("finish")
}
func (c *Class31) TestMultiAdd(mutex *sync.Mutex, waitGroup *sync.WaitGroup) {
	if mutex != nil {
		mutex.Lock()
		defer mutex.Unlock()
	}

	for i := 0; i < 10000; i++ {
		c.Scope++
	}
	if waitGroup != nil {
		waitGroup.Done()
	}
}
func (c *Class31) TestChan() {
	int64C := make(chan int64, 10)
	for i := 0; i < 10; i++ {
		int64C <- int64(i)
	}
	close(int64C)
	t := <-int64C
	fmt.Println(t)
	for item := range int64C {
		fmt.Println(item)
	}
}
func (c *Class31) TestRaceCondition() {
	waitGroup := &sync.WaitGroup{}
	waitGroup.Add(2)
	go c.TestMultiAdd(nil, waitGroup)
	go c.TestMultiAdd(nil, waitGroup)
	waitGroup.Wait()
	fmt.Println(c.Scope)

	mutex := &sync.Mutex{}
	waitGroup1 := &sync.WaitGroup{}
	c.Scope = 0
	waitGroup1.Add(2)
	go c.TestMultiAdd(mutex, waitGroup1)
	go c.TestMultiAdd(mutex, waitGroup1)
	waitGroup1.Wait()
	fmt.Println(c.Scope)
}
func (c *Class31) TestDeadLock() {
	m1 := &sync.Mutex{}
	m2 := &sync.Mutex{}

	go func(m1 *sync.Mutex, m2 *sync.Mutex) {
		m1.Lock()
		time.Sleep(2 * time.Second)
		m2.Lock()
		m2.Unlock()
		m1.Unlock()
	}(m1, m2)

	m2.Lock()
	time.Sleep(2 * time.Second)
	m1.Lock()
	m1.Unlock()
	m2.Unlock()

	fmt.Println("finish")
}

func (c *Class31) TestFunc() {
	temp := &Class31{}
	f := func(c *Class31) error {
		fmt.Println(1)
		return nil
	}
	go func(fu func(c *Class31) error) {
		fu(temp)
	}(f)
	for {
	}
}
