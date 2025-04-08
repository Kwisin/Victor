import time

from demo.dir_25.base_class import Base
from typing import Optional
import string
import threading
import concurrent.futures

variable = 0
lock = threading.Lock()
r_lock = threading.RLock()
event = threading.Event()
condition = threading.Condition()


def lock():
    global variable
    thread = threading.Thread(target=add_val_semaphore, args=("thread", 0))
    thread1 = threading.Thread(target=add_val_semaphore, args=("thread1", 1))
    thread2 = threading.Thread(target=add_val_semaphore, args=("thread2", 2))
    thread.start()
    thread1.start()
    thread2.start()

    thread.join()
    thread1.join()
    thread2.join()
    print(variable)


semaphore = threading.Semaphore(2)


def add_val_semaphore(name, index):
    semaphore.acquire()
    global variable
    print(f"thread {name} is running,it's index is {index} \n")
    for i in range(10000000):
        variable += 1
    print(f"thread {name} has finished")
    semaphore.release()


def func_semaphore():
    threads = []
    for i in range(5):
        thread = threading.Thread(target=add_val_semaphore, args=(f"thread{i}", i))
        thread.start()
        threads.append(thread)
    for item in threads:
        item.join()


def event_producer():
    global variable
    while True:
        if variable >= 1:
            event.wait()
        variable += 1
        print(f"produce {variable}")
        event.set()


def event_consumer():
    global variable
    while True:
        time.sleep(2)
        if variable <= 0:
            event.wait()
        print(f"consumed {variable}")
        variable -= 1
        event.set()


def func_event():
    thread = threading.Thread(target=event_producer)
    thread.start()

    thread1 = threading.Thread(target=event_consumer)
    thread1.start()
    thread.join()
    thread1.join()


if __name__ == '__main__':
    func_event()
