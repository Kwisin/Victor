import string
import threading

from demo.dir_25.sub_dir_03.class_24 import Base


class Class25(Base):
    temp = 1
    mutex = threading.Lock()

    def __init__(self, name: string, age: int):
        self.name = name
        self.age = age
        print("init finish")

    def __len__(self):
        return self.age

    def __add__(self, other):
        return self.age + other.age

    def __contains__(self, target):
        return self.age == target

    def intro(self):
        return f"this is {self.name} and his age is {self.age}"

    def test_array(self):
        arr = [1, 2, 3]
        arr1 = list([1])
        arr = arr1
        arr_ = arr[1:2:-1]
        for item in arr:
            print(item)

        for index, item in enumerate(arr):
            print(f"index is {index}, item is {item}")
        arr.append(1)
        pass

    def test_dict(self):
        test_dic = {1: 1, 2: 4, 3: 8}
        test_dic.update({1: 11, 2: 22, "3": "123"})

        for key, val in test_dic:
            print(f"key is {key}, value is {val}")
        for key in test_dic.keys():
            print(f"key is {key}, value is {test_dic[key]}")
        del test_dic[1]
        test_dic.popitem()
        test_dic.pop(2)

    def test_tuple(self):
        tup = (1, 2, 3, 4)
        for item in tup:
            print(item)

        try:
            i = 1 + "1"
        except TypeError as e:
            print(e)

        pass

    def test_set(self):
        pass

    def test_add(self):
        if self.mutex is not None:
            self.mutex.acquire()
        for i in range(10000):
            self.temp += 1
        if self.mutex is not None:
            self.mutex.release()

    def test_thread(self):
        self.mutex = None
        thread = threading.Thread(target=self.test_add, name="test_thread")
        thread.start()
        thread1 = threading.Thread(target=self.test_add, name="test_thread1")
        thread1.start()
        thread.join()
        thread1.join()

        self.mutex = threading.Lock
        thread = threading.Thread(target=self.test_add, name="test_thread")
        thread.start()
        thread1 = threading.Thread(target=self.test_add, name="test_thread1")
        thread1.start()
        thread.join()
        thread1.join()


if __name__ == "__main__":
    pass
