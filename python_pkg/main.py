from demo.dir_25.sub_dir_03.class_24 import Class24

if __name__ == '__main__':
    c1 = Class24("test", 1)
    c1.test_dict()
    i = len(c1)
    c2 = Class24("test1", 3)
    c_ = c1 + c2
    c2.test_thread()
    print()
