import time

from demo.dir_25.base_class import Base
from typing import Optional
from threading import Condition, Semaphore, Event, Barrier, Lock, Thread


class Class06(Base):

    def __init__(self, name: str, age: int, co: str, address: Optional[str] = None):
        self.name = name
        self.age = age
        self.co = co
        self.address = address
        self.lock = Lock()
        self.semaphore = Semaphore(5)
        self.condition = Condition()
        self.event = Event()

        def barrier_func():
            print("barrier finished")

        self.barrier = Barrier(parties=10, action=barrier_func)
        self.resource = 0
        self.intro()

    def intro(self):

        print(
            f'my name is {self.name},age is {self.age},co is {self.co} and address is {self.address if self.address is not None else None}')

    def test_array(self):
        list1 = [1, 2, 3, "3", True]
        list2 = list([12, 45, 73])
        list1.append(22)
        test_for_each(list1)
        list1.extend(list2)
        test_for_each(list1)
        list1.remove(1)
        test_for_each(list1)
        list1.insert(3, 213)
        test_for_each(list1)
        list1.copy()

    def test_dict(self):
        dict1 = {1: 11, 2: 23, 33: "1232"}
        dict2 = {"": "", None: 1}
        fromkeys = dict1.fromkeys({1, 2, 3, 4})
        test_for_each(fromkeys)
        dict1.update(dict2)
        test_for_each(dict1)

    def test_tuple(self):
        tuple1 = (1, 2, 3)
        test_for_each(tuple1)

    def test_set(self):
        set1 = {1, 2, 3, 4, 5, 6, 6}
        test_for_each(set1)

    def test_thread(self):
        pass

    def test_lock(self):
        temp = 0

        def test_lock_func(lock: Lock, name: str):
            nonlocal temp
            if lock is not None:
                lock.acquire()
            for i in range(10000):
                temp += 1
            if lock is not None:
                lock.release()

        thread1 = Thread(name='thread1', target=test_lock_func, args=(self.lock, 'thread1'))
        thread2 = Thread(name='thread2', target=test_lock_func, args=(self.lock, 'thread2'))
        thread1.start()
        thread2.start()
        thread1.join()
        thread2.join()
        print(f'temp is {temp}')

        temp = 0
        thread3 = Thread(name='thread3', target=test_lock_func, args=(None, 'thread3'))
        thread4 = Thread(name='thread4', target=test_lock_func, args=(None, 'thread4'))
        thread3.start()
        thread4.start()
        thread3.join()
        thread4.join()
        print(f'temp is {temp}')

    def test_semaphore(self):
        def get_semaphore(se: Semaphore, name: str):
            if se is not None:
                se.acquire()
            print(f"{name} has get semaphore\n")
            time.sleep(4)
            if se is not None:
                se.release()

        threads = []
        for i in range(20):
            thread_name = f'thread{i}'
            thread = Thread(name=thread_name, target=get_semaphore, args=(self.semaphore, thread_name))
            thread.start()
            threads.append(thread)

        for thread in threads:
            thread.join()

    def test_barrier(self):
        threads = []

        def get_barrier_func(ba: Barrier, name):
            ba.wait()
            print(f'{name} finished')

        for i in range(15):
            thread_name = f'thread{i}'
            thread = Thread(name=thread_name, target=get_barrier_func, args=(self.barrier, thread_name))
            thread.start()
            threads.append(thread)
        self.barrier.wait()
        for thread in threads:
            thread.join()

    def test_restaurant(self):
        cooker_event = Event()
        waiter_event = Event()
        cookers = []
        waiters = []

        def cooker_func():
            pass


def test_for_each(target):
    if isinstance(target, list):
        print(f'this is a list!')
        for item in target:
            print(f'item is {item}')
        for index, item in enumerate(target):
            print(f'index is {index}, item is {item}')
    elif isinstance(target, dict):
        print("this is a dict")
        for key in target.keys():
            print(f'key is {key}, val is {target[key]}')
        for item in target:
            print(f'item is {item}')
    elif isinstance(target, set):
        print("this is a set")
        for item in target:
            print(f'item is {item}')
    else:
        print('this is a tuple')
        for item in target:
            print(f'item is {item}')


if __name__ == '__main__':
    class_ = Class06("test", 24, "testCo")
    class_.test_barrier()
