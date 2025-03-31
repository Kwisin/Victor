import string
import threading

from class_24 import Base
from threading import Lock


class Class31(Base):

    def __init__(self, name: string, age: int):
        super().__init__()
        self.region = "shanghai"
        self.name = name
        self.age = age

    def intro(self):
        print(f" name is {self.name} age is {self.age} region is {self.region}")

    def __contains__(self, item):
        return self.age == item

    def test_array(self):
        l = list([1, 23, 4])
        l1 = [1, 2, 3]
        l.append(1)
        l.remove(1)
        l.insert(2, "11")
        pop = l.pop(3)
        print(pop)
        for item in l:
            print(item)

        for i in range(10):
            print(i)

        for index, item in enumerate(l):
            print(f"index is {index} item is {item}")

    def test_dict(self):
        d = dict({1: 2})
        test_dict = {1: 1, 2: "2"}
        for key, val in test_dict:
            print(f"key is {key} val is {val}")
        test_dict[3] = "3"
        test_dict.keys()
        test_dict.values()
        test_dict.items()
        for index, item in enumerate(test_dict):
            print(f"index is {index} item is {item}")

    def test_tuple(self):
        set([1,2,3])
        tuple_items = (1, 2, 3)
        pass

    def test_set(self):
        test_set = {1, 2, 3, 4}
        test_set.union()
        pass

    def test_thread(self):
        lock = threading.Lock
        lock.acquire()
        pass


if __name__ == "__main__":
    pass
