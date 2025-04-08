import random
import time

from demo.dir_25.base_class import Base
from typing import Optional, Union
import threading


class Class08(Base):

    def __init__(self, name: str, age: int, address: Optional[str], sex: Union[str, int]):
        self.name = name
        self.age = age
        if address is not None:
            self.address = address
        if isinstance(sex, str):
            print(f"sex is {sex}")
        elif isinstance(sex, int):
            self.sex = sex
        self.intro()

    def intro(self):
        print(f"init finish! name is {self.name}")

    def test_array(self):
        list1 = ["1", 2, 34, 5, 11]
        list1.remove(2)

        list2 = list()
        for index, item in enumerate(list1):
            print(f"index is {index}, item is {item}")
        for item in list1:
            print(f"for item in list1. item is {item}")
        for i in range(10):
            print()
        list_ = list1[0:1:1]

    def test_dict(self):
        dict1 = {1: 2, 3: 33, "11": 11}
        pop = dict1.pop(1)
        for item in dict1:
            print(f"item is {item}")
        for key in dict1.keys():
            print(f"key is {key}, value is {dict1[key]}")
        for index, item in enumerate(dict1):
            print(f"index is {index}, item is {item}")

    def test_tuple(self):
        pass

    def test_set(self):
        set1 = {1, 2, 3, 4, 1}
        union = set1.union({5, 2, 4, 6, 9})
        print(union)
        intersection = set1.intersection({1})
        print(intersection)

    def test_thread(self):
        lock = threading.Lock()
        i = 0

        def test_add():
            nonlocal i
            for index in range(1110000):
                i += 1

        thread1 = threading.Thread(target=test_add, name="thread1")
        thread1.start()
        thread2 = threading.Thread(target=test_add, name="thread2")
        thread2.start()
        thread1.join()
        thread2.join()
        print(i)

        def test_add_lock():
            nonlocal i
            for index in range(1110000):
                lock.acquire()
                i += 1
                lock.release()

        i = 0
        thread3 = threading.Thread(target=test_add_lock, name="thread3")
        thread3.start()
        thread4 = threading.Thread(target=test_add_lock, name="thread4")
        thread4.start()
        thread3.join()
        thread4.join()
        print(i)

        '''
        producer_event = threading.Event()
        consumer_event = threading.Event()
        can = None

        def consumer():
            nonlocal can
            while True:
                producer_event.wait()
                producer_event.clear()
                print(f"consumer {can}")
                consumer_event.set()

        def producer():
            nonlocal can
            for index in range(2):
                consumer_event.wait()
                consumer_event.clear()
                can = random.randint(0, 10)
                print(f"produce {can}")
                producer_event.set()

        consumer = threading.Thread(target=consumer, name="consumer")
        consumer.start()
        producer = threading.Thread(target=producer, name="producer")
        producer.start()
        consumer_event.set()

        consumer.join()
        producer.join()
        '''

        '''
        semaphore = threading.Semaphore(3)

        def test_semaphore(name: str, age: int):
            semaphore.acquire()
            print(name)

        threads = []
        for index in range(5):
            thread_name = f"thread{index}"
            thread = threading.Thread(target=test_semaphore, args=(thread_name, 0), name=thread_name)
            thread.start()
            threads.append(thread)

        for thread in threads:
            thread.join()
        '''

        '''
        barrier = threading.Barrier(3)

        def test_semaphore(name: str, age: int):
            print(name)
            barrier.wait()
            print(age)

        threads = []
        for index in range(4):
            thread_name = f"thread{index}"
            thread = threading.Thread(target=test_semaphore, args=(thread_name, index), name=thread_name)
            thread.start()
            threads.append(thread)

        for thread in threads:
            thread.join()
        '''

        condition = threading.Condition()
        # 模拟厨房的菜品列表
        dishes = []
        # 模拟餐厅的订单列表
        orders = []
        # 餐桌数量
        TABLE_COUNT = 3

        # 厨师类
        class Chef(threading.Thread):
            def __init__(self, name):
                threading.Thread.__init__(self)
                self.name = name

            def run(self):
                while True:
                    with condition:
                        # 当没有订单时，厨师等待
                        while len(orders) == 0:
                            print(f"{self.name} 厨师没有订单，等待中...")
                            condition.wait()
                        # 从订单列表中取出一个订单
                        order = orders.pop(0)
                        print(f"{self.name} 厨师开始制作 {order}")
                        # 模拟做菜时间
                        time.sleep(random.randint(1, 3))
                        # 菜做好了，添加到菜品列表
                        dishes.append(order)
                        print(f"{self.name} 厨师做好了 {order}")
                        # 通知服务员可以上菜了
                        condition.notify_all()

        # 服务员类
        class Waiter(threading.Thread):
            def __init__(self, name):
                threading.Thread.__init__(self)
                self.name = name

            def run(self):
                while True:
                    with condition:
                        # 当没有菜可送时，服务员等待
                        while len(dishes) == 0:
                            print(f"{self.name} 服务员没有菜可送，等待中...")
                            condition.wait()
                        # 从菜品列表中取出一个菜
                        dish = dishes.pop(0)
                        print(f"{self.name} 服务员开始送 {dish} 到餐桌")
                        # 模拟上菜时间
                        time.sleep(random.randint(1, 2))
                        print(f"{self.name} 服务员将 {dish} 送到了餐桌")
                        # 模拟顾客下单
                        if True:
                            new_order = random.choice(["鱼香肉丝", "宫保鸡丁", "麻婆豆腐"])
                            orders.append(new_order)
                            print(f"{self.name} 服务员收到顾客的 {new_order} 订单")
                            # 通知厨师有新订单了
                            condition.notify_all()

        # 创建厨师和服务员线程
        chef1 = Chef("张三")
        chef2 = Chef("李四")
        waiter1 = Waiter("王五")
        waiter2 = Waiter("赵六")

        # 启动线程
        chef1.start()
        chef2.start()
        waiter1.start()
        waiter2.start()
        with condition:
            orders.append("鱼香肉丝")
            condition.notify_all()

        # 等待线程结束（这里线程是无限循环，不会自然结束）
        chef1.join()
        chef2.join()
        waiter1.join()
        waiter2.join()
