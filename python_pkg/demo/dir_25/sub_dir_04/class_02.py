import time

from demo.dir_25.base_class import Base
from typing import Optional
import string
import threading
import concurrent.futures


class Class02(Base):
    def __init__(self, name: string, age, height, identity: Optional[int] = None):
        self.name = name
        self.age = age
        self.height = height
        if identity is not None:
            self.identity = identity

    def intro(self):
        print(f"my name is {self.name},age is {self.age},height is {self.height} and identity is {self.identity}")
        print("intro finish")

    def test_array(self):
        arr = list([1, 2, 3])
        arr1 = [5, 6, 7]
        arr_ = [arr, arr1]
        for item in arr_:
            print(item)
        arr_.append(11)
        try:
            arr_.remove(5)
        except Exception as e:
            print(e)
        arr_.insert(4, 99)
        arr_.extend(arr)
        for index, item in enumerate(arr_):
            print(f"index is {index}, item is {item}")
        print("test_array finish")

    def test_dict(self):
        dict1 = dict({1: 2})
        dict2 = {2: "11", 4: "223"}
        try:
            for key, val in dict2:
                print(f"key is {key} value is {val}")
        except Exception as e:
            print(e)
        for item in dict2.items():
            print(f"item is {item}")
        for key in dict2.keys():
            print(f"key is {key} value is {dict2[key]}")
        print("test_dict finish")

    def test_tuple(self):
        pass

    def test_set(self):
        pass

    def test_thread(self):
        thread = threading.Thread(target=self.test_array)
        thread.start()
        thread.join()
        print("test_thread finish")


def test_class_02():
    class_ = Class02("jay", 8, 22, 10)
    class_.intro()
    class_.test_array()
    class_.test_dict()
    class_.test_tuple()
    class_.test_set()
    class_.test_thread()
