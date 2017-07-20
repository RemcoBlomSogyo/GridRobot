from __future__ import print_function # use python 3 syntax but make it compatible with python 2
from __future__ import division       #                           ''
from flask import Flask
from flask import render_template
from flask import Response
from camera import Camera

import time     # import the time library for the sleep function
import brickpi3 # import the BrickPi3 drivers

BP = brickpi3.BrickPi3() # Create an instance of the BrickPi3 class. BP will be the BrickPi3 object.

"Set Speed here"
speedA = 0
speedD = 0

posX = 0
posY = 0

app = Flask(__name__, static_url_path='')

print('Version 4 - Sogyo.nl Raspberry Pi webserver')
print('Setting up RPi server...')


@app.route('/')
def index():
    return app.send_static_file('index.html')

def gen(camera):
    """Video streaming generator function."""
    while True:
        frame = camera.get_frame()
        yield (b'--frame\r\n'
               b'Content-Type: image/jpeg\r\n\r\n' + frame + b'\r\n')

@app.route('/param=<route>')
def parameters(route):
    return 'Taking parameters'

@app.route('/move=<direction>')
def move(direction):
    return direction

@app.route('/forward', methods=['POST'])
def forw():
    BP.set_motor_power(BP.PORT_A, 15)
    BP.set_motor_power(BP.PORT_D, 15)
    return 'Moving forward'

@app.route('/left', methods=['POST'])
def left():
    BP.set_motor_power(BP.PORT_A, -15)
    BP.set_motor_power(BP.PORT_D, 15)
    return 'Moving left'

@app.route('/right', methods=['POST'])
def right():
    BP.set_motor_power(BP.PORT_A, 15)
    BP.set_motor_power(BP.PORT_D, -15)
    return 'Moving right'

@app.route('/backward', methods=['POST'])
def backw():
    BP.set_motor_power(BP.PORT_A, -15)
    BP.set_motor_power(BP.PORT_D, -15)
    return 'Moving backward'

@app.route('/stop', methods=['POST'])
def stop():
    BP.set_motor_power(BP.PORT_A, 0)
    BP.set_motor_power(BP.PORT_D, 0)
    return 'Stopping'

@app.route('/location')

def location():
    return 'Location: ' + str(posX) + ', ' + str(posY)

@app.route('/video_feed')
def video_feed():
    """Video streaming route. Put this in the src attribute of an img tag."""
    return Response(gen(Camera()),
                    mimetype='multipart/x-mixed-replace; boundary=frame')

if __name__ == '__main__':
    print('Ready.')
    app.run(debug=True, host='0.0.0.0')

def forward(param):
        BP.set_motor_power(BP.PORT_A, param)
        BP.set_motor_power(BP.PORT_D, param)

def backward(param):
        BP.set_motor_power(BP.PORT_A, -param)
        BP.set_motor_power(BP.PORT_D, -param)
