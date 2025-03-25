package sub_dir_03

import (
	"fmt"
	"sync"
	"time"
)

type Class25 struct {
	comVal int64
}

func (c *Class25) TestMap() {
	testMap := make(map[string]int64)
	testMap["1"] = 111
	testMap["2"] = 222
	delete(testMap, "1")
	for k, v := range testMap {
		fmt.Println(fmt.Sprintf("key is %s,val is %d", k, v))
	}
}
func (c *Class25) TestArray() {
	testArr := [4]int64{}
	testArr1 := [...]int{1, 2, 3}
	arrPtr := new([7]int64)
	testArr[1] = 1
	testArr1[2] = 3
	arrPtr[2] = 5
	for index, item := range arrPtr {
		fmt.Println(fmt.Sprintf("index is %d,item is %d", index, item))
	}

	testSlice := arrPtr[1:5]
	testSlice2 := make([]int64, 4, 6)
	testSlice = append(testSlice, 1)
	testSlice2[3] = 6

}
func (c *Class25) TestForEach() {}
func (c *Class25) TestMultiAdd(mutex *sync.Mutex, waitGroup *sync.WaitGroup) {
	if mutex != nil {
		mutex.Lock()
		defer mutex.Unlock()
	}
	for i := 0; i < 100000; i++ {
		c.comVal++
	}
	if waitGroup != nil {
		waitGroup.Done()
	}
}

func (c *Class25) TestChan() {
	var testChan1 chan int
	var testChan2 chan int
	var testChan3 chan int
	testChan1 = make(chan int)
	testChan2 = make(chan int)
	testChan3 = make(chan int)
	go func() {
		for i := 0; i < 10; i++ {
			testChan1 <- i
			time.Sleep(1 * time.Second)
		}
		close(testChan1)
	}()
	go func() {
		for i := 11; i < 20; i++ {
			testChan2 <- i
			time.Sleep(1 * time.Second)
		}
		close(testChan2)
	}()
	go func() {
		for i := 21; i < 30; i++ {
			testChan3 <- i
		}
		close(testChan3)
	}()
	for c := range testChan3 {
		fmt.Println(fmt.Sprintf("testChan3 val:%d", c))
	}

	now := int64(0)
	for {
		select {
		case val, ok := <-testChan1:
			if ok && val != 0 {
				now = time.Now().Unix()
				fmt.Println(val)
			}
		case val, ok := <-testChan2:
			if ok && val != 0 {
				now = time.Now().Unix()
				fmt.Println(val)
			}
		default:
			if time.Now().Unix()-now > 10 {
				fmt.Println("noting")
				return
			}
		}
	}
}

func (c *Class25) TestRaceCondition() {
	waitGroup := &sync.WaitGroup{}
	waitGroup.Add(2)
	go c.TestMultiAdd(nil, waitGroup)
	go c.TestMultiAdd(nil, waitGroup)
	waitGroup.Wait()
	fmt.Println(fmt.Sprintf("TestRaceCondition first:%d", c.comVal))

	c.comVal = 0
	mutex := &sync.Mutex{}
	waitGroup.Add(2)
	go c.TestMultiAdd(mutex, waitGroup)
	go c.TestMultiAdd(mutex, waitGroup)
	waitGroup.Wait()
	fmt.Println(fmt.Sprintf("TestRaceCondition second:%d", c.comVal))

}
func (c *Class25) TestDeadLock() {
	mutex1 := &sync.Mutex{}
	mutex2 := &sync.Mutex{}
	go func(m1 *sync.Mutex, m2 *sync.Mutex) {
		m1.Lock()
		time.Sleep(1 * time.Second)
		fmt.Println(fmt.Sprintf("func1 m1 locked"))
		m2.Lock()
		m2.Unlock()
		m1.Unlock()
	}(mutex1, mutex2)

	mutex2.Lock()
	time.Sleep(1 * time.Second)
	fmt.Println(fmt.Sprintf("func2 m1 locked"))
	mutex1.Lock()
	mutex1.Unlock()
	mutex2.Unlock()

	fmt.Println(fmt.Sprintf("finished"))
}
