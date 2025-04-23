import random
import time

from demo.dir_25.base_class import Base
from typing import Optional, Union
import threading


class Class23(Base):
    def __init__(self, name: str, age: int, gender: Optional[bool] = None):
        self.name = name
        self.age = age
        self.mutex = 0
        if gender is not None:
            self.gender = gender
        self.intro()

    def intro(self):
        print(f"my name is {self.name},age is {self.age}, gender is {self.gender}")

    def test_array(self):
        list1 = [1]
        print(f"list length is {len(list1)}")

        list1.append(2)
        list1.extend([1, 2, 3, 4])
        list1.insert(3, 10)

        list1.remove(1)
        index_item = list1[1]
        print(f"index_item is {index_item}")
        pop_item = list1.pop(1)
        print(f"pop_item is {pop_item}")

        for item in list1:
            print(f"item is {item}")

        for index, item in enumerate(list1):
            print(f"index is {index}, item is {item}")
            isinstance(item, tuple)

    def test_dict(self):
        dict1 = {1: "11", "2": 22}
        print(f"dict length is {len(dict1)}")

        dict1[3] = "3333"
        dict1.pop(1)

        for key in dict1.keys():
            print(f"key is {key}, val is {dict1[key]}")
        for item in dict1.items():
            print(f"key is {item[0]} val is {item[1]}")
        for index, item in enumerate(dict1):
            print(f"index is {index}, key is {item}")

    def test_tuple(self):
        tuple1 = (1, 2, 3, 4)
        print(f"tuple1 length is {len(tuple1)}")

        for index, item in enumerate(tuple1):
            print(f"index is {index}, item is {item}")
        for item in tuple1:
            print(f"item is {item}")

    def test_set(self):
        set1 = {1, 2, 3}
        print(f"set1 length is {len(set1)}")
        set1.add(3)
        set1.add(4)
        set1.remove(3)
        set1.update({4, 5, 6})
        set2 = {5, 6, 7}
        set__union = set2.union(set1)
        set__intersection = set2.intersection(set1)
        set__difference = set2.difference(set1)
        # set2.difference_update(set1)
        symmetric_difference = set2.symmetric_difference(set1)
        set2.discard(1)
        print(f"")

    def test_thread(self):
        # self.test_lock_thread()
        # self.test_semaphore_thread()
        self.test_condition_thread()

        # condition = threading.Condition()
        # condition.wait_for()
        # event = threading.Event()
        # event.wait()

    def test_lock_thread(self):
        mutex = self.mutex

        def test_lock_func(thread_name: str, lock: Optional[threading.Lock] = None):
            nonlocal mutex
            if lock is not None:
                lock.acquire()
            for i in range(10000):
                mutex += 1
            if lock is not None:
                lock.release()
            print(f"{thread_name} finish!")

        test_thread1 = threading.Thread(target=test_lock_func, name='thread_test1', args=('thread_test1', None))
        test_thread2 = threading.Thread(target=test_lock_func, name='thread_test2', args=('thread_test2', None))
        test_thread1.start()
        test_thread2.start()
        test_thread1.join()
        test_thread2.join()
        print(f"without lock result is {mutex}")

        mutex = 0
        threading_lock = threading.Lock()
        test_thread3 = threading.Thread(target=test_lock_func, name='thread_test3',
                                        args=('thread_test3', threading_lock))
        test_thread4 = threading.Thread(target=test_lock_func, name='thread_test4',
                                        args=('thread_test4', threading_lock))
        test_thread3.start()
        test_thread4.start()
        test_thread3.join()
        test_thread4.join()
        print(f"with lock result is {mutex}")

    def test_semaphore_thread(self):

        def semaphore_func(name: str, se: Optional[threading.Semaphore] = None):
            if se is not None:
                se.acquire()
            print(f"{name} has acquired semaphore")
            time.sleep(4)
            if se is not None:
                se.release()

        semaphore = threading.Semaphore(5)
        threads = []
        for i in range(10):
            thread_name = f"thread{i}"
            thread = threading.Thread(target=semaphore_func, name=thread_name, args=(thread_name, semaphore))
            thread.start()
            threads.append(thread)

        for item in threads:
            item.join()

    def test_barrier_thread(self):
        def finish_func():
            print("oh, i'm finish!")

        barrier = threading.Barrier(5, action=finish_func)

        def barrier_func(name: str, se: Optional[threading.Barrier] = None):
            print(f"{name} has acquired barrier")
            if se is not None:
                se.wait()

        threads = []
        for i in range(10):
            thread_name = f"thread{i}"
            thread = threading.Thread(target=barrier_func, name=thread_name, args=(thread_name, barrier))
            thread.start()
            threads.append(thread)

        for item in threads:
            item.join()

    def test_event_thread(self):
        mutex = self.mutex
        consumer_event = threading.Event()
        producer_event = threading.Event()

        mutex = 0

        def consumer():
            nonlocal mutex
            while True:
                if mutex <= 10:
                    consumer_event.wait()
                print("consumed!")
                mutex -= 10
                producer_event.set()
                consumer_event.clear()

        def producer():
            nonlocal mutex
            while True:
                if mutex > 10:
                    producer_event.wait()
                mutex += 1
                time.sleep(10)
                consumer_event.set()
                producer_event.clear()

        test_thread1 = threading.Thread(target=consumer, name='thread_test1')
        test_thread2 = threading.Thread(target=producer, name='thread_test2')
        test_thread1.start()
        test_thread2.start()
        test_thread1.join()
        test_thread2.join()

    def test_condition_thread(self):
        mutex = self.mutex
        condition = threading.Condition()

        def consumer_predicate():
            return mutex >= 10

        def producer_predicate():
            return mutex < 10

        def producer():
            nonlocal mutex
            while True:
                condition.acquire()
                condition.wait_for(predicate=producer_predicate)
                mutex += 1
                print(f"produced! {mutex}")
                time.sleep(1)
                condition.notify_all()
                condition.release()

        def consumer():
            nonlocal mutex
            while True:
                condition.acquire()
                condition.wait_for(predicate=consumer_predicate)
                mutex -= 10
                print("consumed!")
                condition.notify_all()
                condition.release()

        test_thread1 = threading.Thread(target=consumer, name='thread_test1')
        test_thread2 = threading.Thread(target=producer, name='thread_test2')
        test_thread1.start()
        test_thread2.start()
        test_thread1.join()
        test_thread2.join()
