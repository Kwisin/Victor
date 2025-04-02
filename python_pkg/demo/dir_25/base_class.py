from abc import ABC, abstractmethod


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
