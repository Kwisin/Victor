package sub_dir_03

import (
	"fmt"
	"testing"
)

func TestDeadLock(t *testing.T) {
	var item Class31
	item.TestMap()
	fmt.Println("finish")
}
