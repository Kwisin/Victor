package sub_dir_03

import "sync"

type class interface {
	TestMap()
	TestArray()
	TestForEach()
	TestMultiAdd(mutex *sync.Mutex, waitGroup *sync.WaitGroup)
	TestChan()
	TestRaceCondition()
	TestDeadLock()
}
