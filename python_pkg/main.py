if __name__ == '__main__':
    i = 1
    j = 1.08
    list1 = [1, 2, 3, "", 1.09]
    tuple1 = (1, 2, "5", 1.09)
    dic = {"": "1"}

    set1 = {1, 2, 3, 5, 3}
    set1.__contains__(1)

    print(list1[1])
    print(tuple1[2])
    print(dic[""])
    print(set1[1])

    list2 = list([1, 1, 3])
    list2.append(1)
    del list2[0]
    list2.remove(1)

    tuple2 = tuple([1, 23, ""])
    d = dict({1: 1})
    s = set({1, 2})
