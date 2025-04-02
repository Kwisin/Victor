package dir_25

import "sync"

type ClassInterface interface {
	TestMap()
	TestArray()
	TestForEach()
	TestMultiAdd(mutex *sync.Mutex, waitGroup *sync.WaitGroup)
	TestChan()
	TestRaceCondition()
	TestDeadLock()
}
