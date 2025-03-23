package pkg

import (
	e "errors"
)

/*

 */

type Base struct {
	Name string
	Age  int64
}

// func +  所属类 +方法名
func (b *Base) testBase() error {
	return e.New("")
}

func (b *Base) GetName() string {
	return b.Name
}

func (b *Base) GetAge() int64 {
	return b.Age
}
