from flask import Flask, request, jsonify
import tensorflow as tf
import numpy as np

app = Flask(__name__)

# 加载模型
model = tf.keras.models.load_model('/Users/xuzhiping/Downloads/java/Victor/python_pkg/test_model.keras')


@app.route('/predict', methods=['POST'])
def predict():
    # 获取请求中的数据
    data = request.get_json(force=True)
    input_data = np.array(data['input'])

    # 进行预测
    predictions = model.predict(input_data)

    # 返回预测结果
    return jsonify({'predictions': predictions.tolist()})


if __name__ == '__main__':
    app.run(debug=True)
