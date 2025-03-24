package sub_dir_03

import (
	"fmt"
	"sync"
)

type Class24 struct {
	Temp    int64
	m1      *sync.Mutex
	m2      *sync.Mutex
	c       chan string
	SyncMap *sync.Map
	w       *sync.WaitGroup
}

func (c *Class24) TestArray() {
	// array
	var a [3]int64
	a[0] = 1
	intArr := [3]int64{}
	i := new([3]int64)
	int64s := [...]int64{1, 2, 3}
	i = &intArr
	i = &int64s
	fmt.Println(i)
	for index, item := range *i {
		fmt.Println(fmt.Sprintf("index:%d,item: %d", index, item))
	}

	for item := range *i {
		fmt.Println(fmt.Sprintf("item: %d", item))

	}

	// slice
	i4 := int64s[:2]
	i5 := int64s[0:2]
	copy(i4, i5)
	intSlice := make([]int64, 3, 6)
	i2 := &[]int64{}
	i3 := new([]int64)
	i3 = i2
	i3 = &intSlice
	fmt.Println(i3)
	*i3 = append(*i3, 1)
	if len(*i3) > 0 {
		fmt.Println(i3)
	}

	for index, item := range *i3 {
		fmt.Println(fmt.Sprintf("index:%d,item: %d", index, item))
	}

	for item := range *i3 {
		fmt.Println(fmt.Sprintf("item: %d", item))
	}

}
func (c *Class24) TestMap() {
	map1 := make(map[int64]string)
	var map2 map[int64]string
	//mapsAdr := new(map[int64]string)
	map2 = map1
	//map2 = *mapsAdr
	map2[1] = "11"
	map2[2] = "11"
	delete(map2, 1)
	map2[2] = "22"

	for key, val := range map2 {
		fmt.Println(fmt.Sprintf("key:%d, val: %s", key, val))
	}

	for key := range map2 {
		fmt.Println(fmt.Sprintf("key:%d", key))
	}

}
func (c *Class24) TestForEach() {

}
func (c *Class24) TestMultiAdd(mutex *sync.Mutex, waitGroup *sync.WaitGroup) {
	if mutex != nil {
		mutex.Lock()
		defer mutex.Unlock()
	}
	if waitGroup != nil {
		defer waitGroup.Done()
	}
	for i := 0; i < 1000; i++ {
		c.Temp++
	}
}
func (c *Class24) TestChan() {
	c.c = make(chan string)
	c.m1 = &sync.Mutex{}
	go func(m *sync.Mutex) {
		for i := 0; i < 10; i++ {
			c.c <- fmt.Sprintf("%d", i)
		}
	}(c.m1)

	go func(m *sync.Mutex) {
		for i := 11; i < 20; i++ {
			c.c <- fmt.Sprintf("%d", i)
		}
		close(c.c)
	}(c.m1)
	for item := range c.c {
		fmt.Println(fmt.Sprintf("%s", item))
	}
	select {}
}
func (c *Class24) TestRaceCondition() {
	var com int64
	if c.w == nil {
		c.w = &sync.WaitGroup{}
	}
	if c.m1 == nil {
		c.m1 = &sync.Mutex{}
	}
	c.w.Add(2)
	go func(m *sync.Mutex, w *sync.WaitGroup) {
		m.Lock()
		for i := 0; i < 10000; i++ {
			com++
		}
		w.Done()
		m.Unlock()
	}(c.m1, c.w)
	go func(m *sync.Mutex, w *sync.WaitGroup) {
		m.Lock()
		for i := 0; i < 10000; i++ {
			com++
		}
		w.Done()
		m.Unlock()
	}(c.m1, c.w)
	c.w.Wait()

	fmt.Println(com)
}
func (c *Class24) TestDeadLock() {
	if c.m1 == nil {
		c.m1 = &sync.Mutex{}
	}
	if c.m2 == nil {
		c.m2 = &sync.Mutex{}
	}
	go func(m1 *sync.Mutex, m2 *sync.Mutex) {
		m1.Lock()
		fmt.Println("func 1 process!")
		m2.Lock()
		fmt.Println("func 1 process!")
		m2.Unlock()
		m1.Unlock()
		fmt.Println("func 1 finish!")
	}(c.m1, c.m2)
	go func(m1 *sync.Mutex, m2 *sync.Mutex) {
		m1.Lock()
		fmt.Println("func 2 process!")
		m2.Lock()
		fmt.Println("func 2 process!")
		m2.Unlock()
		m1.Unlock()
		fmt.Println("func 2 finish!")
	}(c.m2, c.m1)

	fmt.Println("finish!")

	for {
	}
}
