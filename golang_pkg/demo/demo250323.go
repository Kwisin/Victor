package demo

import (
	"fmt"
	"sync"
)

type Demo250323 struct {
	Name   string
	Gender int
}

func (d *Demo250323) testMap() {
	testMap := new(map[string]string)
	(*testMap)[""] = ""
	testMap2 := make(map[string]string)
	testMap2[""] = ""
	s, exist := testMap2[""]
	if exist {
		fmt.Println(s)
	}
	delete(testMap2, "")
}

func (d *Demo250323) testArray() {
	testSlice := new([]int64)
	*testSlice = append(*testSlice, 0)
	(*testSlice)[1] = 1
	testSlice2 := make([]int64, 3, 6)
	testSlice2 = append(testSlice2, 0)
	testSlice2[1] = 1

	testArray := new([3]int64)
	(*testArray)[0] = 1

	//intArray := [3]int{1, 2, 3}
	//var intSlice []int64
	//i := &[]int64{}
	//i = testSlice
	//intSlice = *testSlice

	copy(*testSlice, testSlice2)

}

func (d *Demo250323) testForEach() {
	intSlice := make([]int64, 0)
	intSlice = append(intSlice, 9)
	for i := 0; i < len(intSlice); i++ {
		fmt.Println(intSlice[i])
	}
	for index, val := range intSlice {
		fmt.Println(fmt.Sprintf("index is %d, val is %d", index, val))
	}

	intStrMap := make(map[int]string)
	for key, val := range intStrMap {
		fmt.Println(fmt.Sprintf("key is %d, val is %s", key, val))
	}

}

func testThread(c chan int) {
	for i := 0; i < 10; i++ {
		c <- i
	}
	close(c)
}

var temp int64

func testMultiAdd(mutex *sync.Mutex, waitGroup *sync.WaitGroup) {
	mutex.Lock()
	for i := 0; i < 1000; i++ {
		temp++
	}
	waitGroup.Done()
	mutex.Unlock()
}

func (d *Demo250323) TestChan() {
	intChan := make(chan int)
	go testThread(intChan)
	//for c := range intChan {
	//	//fmt.Println(c)
	//}

	s := &sync.WaitGroup{}
	s.Add(5)
	for i := 0; i < 5; i++ {
		go testMultiAdd(&sync.Mutex{}, s)
	}
	s.Wait()
	fmt.Println(temp)
}

func (d *Demo250323) TestRaceCondition() {
	round := 5
	waitGroup := &sync.WaitGroup{}
	waitGroup.Add(round)
	mutex := &sync.Mutex{}
	for i := 0; i < round; i++ {
		go testMultiAdd(mutex, waitGroup)
	}
	waitGroup.Wait()
	fmt.Println(temp)
}

func (d *Demo250323) TestDeadLock() {
	var mutex1, mutex2 sync.Mutex
	fmt.Println("goroutine 1 acquired mu")
	go func() {
		mutex1.Lock()
		fmt.Println("goroutine 1 acquired mu1")
		mutex2.Lock()
		fmt.Println("goroutine 1 acquired mu2")
		mutex1.Unlock()
		mutex2.Unlock()
	}()
	//time.Sleep(2 * time.Second)

	fmt.Println("goroutine 1 acquired mu3")

	go func() {
		mutex2.Lock()
		fmt.Println("main goroutine acquired mu2")
		mutex1.Lock()
		fmt.Println("main goroutine acquired mu1")
		mutex1.Unlock()
		mutex2.Unlock()
	}()
	fmt.Println("goroutine 1 acquired mu444")
	select {}
}
