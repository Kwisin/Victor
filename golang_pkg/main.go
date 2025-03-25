package main

import (
	"fmt"
	"golang_study/demo/dir_25/sub_dir_03"
	"golang_study/pkg"
)

func main() {
	var item sub_dir_03.Class25
	item.TestDeadLock()
}

func testNewObj() {
	var b pkg.Base // 直接用var的方式新建一个对象，默认字段全部初始化为空值或者0
	fmt.Println(fmt.Sprintf("name:%s", b.GetName()))
	fmt.Println(fmt.Sprintf("age:%d", b.GetAge()))
	fmt.Print()
	p := &b
	fmt.Print(p)

	baseItem := pkg.Base{Name: "test", Age: 9}
	fmt.Println(fmt.Sprintf("name:%s", baseItem.GetName()))
	fmt.Println(fmt.Sprintf("age:%d", baseItem.GetAge()))
	fmt.Print()

	baseItem = pkg.Base{
		Name: "",
		Age:  1,
	}
	fmt.Println(fmt.Sprintf("name:%s", baseItem.GetName()))
	fmt.Println(fmt.Sprintf("age:%d", baseItem.GetAge()))
	fmt.Print()

	p2 := new(pkg.Base)
	fmt.Println(p2)
	fmt.Print()

	/*
		总结
		var b pkg.Base
		直接用var声明并初始化一个结构体时，所有字段都被设置为0值或者空字符串

		pkg.Base{Name: "test", Age: 9}
		用结构体新建一个对象

		new(pkg.Base)
		用new方法新建一个对象

		只有new方法新建的对象是指针类型
	*/
}

func testArrAndSlice() {

	// 数组  固定长度的是数据，是值类型，存在栈中
	ints := [3]int{1, 2, 3}
	ints1 := [...]int{1, 2, 3, 4, 5}
	ints2 := [3]int{1: 1, 2: 0}
	fmt.Println(ints1)
	fmt.Println(ints2)

	// append函数不能直接用于数组
	//ints = append(ints, 1)

	//数组取切片，: 左闭右开
	sliceItem := ints[0:2]
	sliceItem = append(sliceItem, 1)

	// 不固定长度的是切片，长度可以动态变换
	arr := make([]int64, 3, 5)
	slice := []int{1, 2, 3}
	fmt.Println(arr[2])
	slice = append(slice, 1)
	fmt.Println(slice[2])
	fmt.Println()

	var sliceTest []int
	sliceTest = append(sliceTest, 0)

	var listTest [3]int
	listTest[0] = 1
	fmt.Println()

	i := new([]int)
	*i = append(*i, 0)

	fmt.Println(i)
	/*
		数组长度固定，在栈上分配内存
		切片长度不固定，在堆中分配内存，并指向栈内元素
	*/
}

func testMap() {
	intSMap := make(map[int]string)
	fmt.Println(intSMap)
	intSMap1 := map[int]string{
		1: "",
	}
	fmt.Println(intSMap1)
	fmt.Println()
	var m = map[string]string{
		"": "",
	}
	fmt.Println(m)
}
