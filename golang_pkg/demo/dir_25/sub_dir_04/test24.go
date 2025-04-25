package sub_dir_04

import (
	"context"
	"fmt"
	"math/rand"
	"sync"
	"time"
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
		stringChans[i] = make(chan string, 10)
	}

	for i := 0; i < 10; i++ {
		go func(index int) {
			fmt.Println(fmt.Sprintf("thread %d started", index))
			for j := 0; j < 10; j++ {
				stringChans[index] <- fmt.Sprintf("thread %d insert val %d", index, j)
				time.Sleep(time.Second)
			}
			fmt.Println(fmt.Sprintf("thread %d finished", index))
			close(stringChans[index])
		}(i)
	}

	for i := 0; i < 10; {
		select {
		case val, ok := <-stringChans[i]:
			if ok {
				println(fmt.Sprintf("val is %s", val))
			}
		default:
		}
		i = (i + 1) % 10
	}

}

func (c *Class24) TestProducerConsumer() {
	strings := make(chan string, 10)
	ctx, cancel := context.WithTimeout(context.Background(), 5*time.Second)
	defer cancel()
	go func() {
		for {
			strings <- fmt.Sprintf("produce %d", rand.Intn(10))
		}
	}()

	go func() {
		for {
			select {
			case <-ctx.Done():
				close(strings)
				println("end !")
				return
			case item, ok := <-strings:
				if item == "produce 1" {
					time.Sleep(6 * time.Second)
				}
				if ok {
					println(fmt.Sprintf("consume %s", item))
				}
			}

		}
	}()

	for {
	}

}

func (c *Class24) TestRestaurant() {
	menu := []string{"红烧肉", "清蒸鲈鱼", "避风塘炒蟹", "糖醋排骨", "清炒包菜"}
	orders := make(chan string, 3)
	plate := make(chan string, 10)
	ctx, cancelFunc := context.WithTimeout(context.Background(), 10*time.Second)
	defer cancelFunc()

	s := &sync.WaitGroup{}

	// guest
	s.Add(1)
	go func() {
		defer s.Done()
		for {
			select {
			case <-ctx.Done():
				println("guest timeout!")
				close(orders)
				close(plate)
			default:
				randInt := rand.Intn(100)
				if randInt < 5 {
					println(fmt.Sprintf("guest has order %s", menu[randInt]))
					orders <- menu[randInt]
					time.Sleep(3 * time.Second)
				}
			}
		}

	}()

	// waiter
	s.Add(1)
	go func() {
		defer s.Done()
		for item := range plate {
			println(fmt.Sprintf("waiter has send order %s", item))
		}
	}()

	// cooker
	s.Add(1)
	go func() {
		defer s.Done()
		for {
			select {
			case <-ctx.Done():
				println("cooker timeout!")
				close(orders)
				close(plate)
			case order, ok := <-orders:
				if ok {
					println(fmt.Sprintf("cooker is cooking %s", order))
					time.Sleep(1 * time.Second)
					plate <- order
				}

			}
		}
	}()

	s.Wait()
}

func (c *Class24) TestRaceCondition() {
	//s := &sync.Mutex{}
	waitGroup := &sync.WaitGroup{}
	waitGroup.Add(10)
	cond := &sync.Cond{}
	cond.Wait()

	//once := &sync.Once{}
	//for i := 0; i < 10; i++ {
	//	go func() {
	//		defer waitGroup.Done()
	//		once.Do(func() {
	//			println("test")
	//		})
	//	}()
	//}

	//s4 := &sync.Pool{}
}
func (c *Class24) TestDeadLock() {}
