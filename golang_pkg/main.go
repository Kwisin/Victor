package main

import (
	"fmt"
	"github.com/samber/lo"
)

func main() {
	strings := make([]string, 0)
	strings = append(strings, "", "1")
	fmt.Println(strings[1])
	lo.Contains(strings, "")
	fmt.Println("222")
}
