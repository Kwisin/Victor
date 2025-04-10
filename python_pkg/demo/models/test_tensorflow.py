import tensorflow as tf
import textwrap

# 加载MNIST数据集
(train_images, train_labels), (test_images, test_labels) = tf.keras.datasets.mnist.load_data()
list1 = list(list(train_images).pop())
for item in list1:
    print(item)
print(list(train_labels).pop())

# 数据预处理
# 将图像像素值缩放到0-1之间
# train_images = train_images / 255.0
# test_images = test_images / 255.0
# print(list(train_images).pop())
# print(list(test_images).pop())
#
# # 对标签进行one - hot编码
# train_labels = tf.keras.utils.to_categorical(train_labels)
# test_labels = tf.keras.utils.to_categorical(test_labels)
#
# # 构建简单的全连接神经网络模型
# model = tf.keras.models.Sequential([
#     # 将28x28的图像展平为一维向量c
#     tf.keras.layers.Flatten(input_shape=(28, 28)),
#     # 第一个全连接层，有128个神经元，使用ReLU激活函数
#     tf.keras.layers.Dense(128, activation='relu'),
#     # 输出层，有10个神经元，对应0-9的10个数字类别，使用softmax激活函数
#     tf.keras.layers.Dense(10, activation='softmax')
# ])
#
# # 编译模型
# model.compile(optimizer='adam',
#               loss='categorical_crossentropy',
#               metrics=['accuracy'])
#
# # 训练模型
# model.fit(train_images, train_labels, epochs=5, batch_size=64)
#
# # 评估模型
# test_loss, test_acc = model.evaluate(test_images, test_labels)
# print(f"Test accuracy: {test_acc}")
#
# model.save('/Users/xuzhiping/Downloads/java/Victor/python_pkg/test_model.keras')
