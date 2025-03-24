from abc import ABC, abstractmethod
import threading, queue


class Base(ABC):
    def __init__(self):
        pass

    @abstractmethod
    def intro(self):
        pass

    @abstractmethod
    def test_array(self):
        pass

    @abstractmethod
    def test_dict(self):
        pass

    @abstractmethod
    def test_tuple(self):
        pass

    @abstractmethod
    def test_set(self):
        pass

    @abstractmethod
    def test_thread(self):
        pass


class Class24(Base):
    attr = "testClass"

    def __init__(self, name, age):
        self.name = name
        self.age = age

    def __len__(self):
        return self.age

    def __add__(self, other):
        return self.age + other.age

    @property
    def name(self):
        return self._name

    @name.setter
    def name(self, new_name):
        self.name = new_name

    def intro(self):
        return f"Hi, I'm {self.name} and I'm {self.age} years old."

    @name.setter
    def name(self, value):
        self._name = value

    @staticmethod
    def static_method():
        print("this is a static method")

    @classmethod
    def class_method(cls):
        print("this is a class method")

    def test_array(self):
        arr = [1]
        arr1 = list(["test", "1"])
        arr1[1] = 1
        arr.extend(arr1)
        arr.remove(1)
        arr.reverse()
        i = len(arr)
        arr.append(2)
        arr_ = arr[1:2]
        for item in arr:
            print(item)
        for index, item in enumerate(arr):
            print(index)
            print(item)

        print(arr)
        print(arr1)

    def test_dict(self):
        dic = {"1": 2}
        dic.update({"1": 1})
        dic["3"] = 1
        dic1 = dict({"1": 1})
        fromkeys = dic.fromkeys([11, 2])
        items = dic.items()
        keys = dic.keys()
        values = dic.values()
        # for key, val in dic.items():
        #     print(key)
        #     print(val)
        # for index, item in enumerate(dic.items()):
        #     print(index)
        #     print(item)
        contains__ = dic.keys().__contains__(1)
        print(dic)
        print(dic1)

    def test_tuple(self):
        tup = (1, 3, "test")
        for item in tup:
            print(item)

    def test_set(self):
        s = {1, 2, 3}
        contains__ = s.__contains__(1)

    def test_increment(self, lock: threading.Lock):
        lock.acquire()
        for i in range(10000):
            self.age += 1
        lock.release()

    def test_decrement(self):
        for i in range(10000):
            self.age -= 1

    def test_thread(self):
        thread = threading.Thread(target=self.test_dict, name="test_thread")
        thread.start()
        thread.join()
        while True:
            continue


def test():
    print(1)


# 单行注释
'''多行注释'''
'''
类名 驼峰写法
变量名 new_attr
常量 MAX_VALUE
'''
