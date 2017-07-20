from __future__ import print_function, division # use python 3 syntax but make it compatible with python 2
from flask import Flask, render_template
from flask import Response
from flask import send_file
"from camera_pi import Camera"
from snapshot import takeSnapshot

import time     # import the time library for the sleep function
import brickpi3 # import the BrickPi3 drivers

BP = brickpi3.BrickPi3() # Create an instance of the BrickPi3 class. BP will be the BrickPi3 object.

"Set Speed here"
speedA = 0
speedD = 0
"Set Starting position here"
posX = 0
posY = 0
"Declare log output file"
f = open('log', 'w')


app = Flask(__name__, static_url_path='')

print('Version 4 - Sogyo.nl Raspberry Pi webserver')
print('Setting up RPi server...')


"""Default page (either no subpage submitted or an unknown one)"""
@app.route('/')
def index():
    return app.send_static_file('index.html')


"""Camera function and return feed (/video_feed)"""
"def gen(camera):"
    """Video streaming generator function."""
"    while True:
"        frame = camera.get_frame()
"        yield (b'--frame\r\n'
"               b'Content-Type: image/jpeg\r\n\r\n' + frame + b'\r\n')

@app.route('/snapshot')
def snapshot():
    takeSnapshot()
    return send_file('snapshots/snapshot.jpg', mimetype='image/gif')

"@app.route('/video_feed')
"def video_feed():
    """Video streaming route. Put this in the src attribute of an img tag."""
"    return Response(gen(Camera()),
"                    mimetype='multipart/x-mixed-replace; boundary=frame')

@app.route('/param=<route>')
def parameters(route):
    return 'Taking parameters'

@app.route('/move=<direction>')
def move(direction):
    return direction

@app.route('/forward', methods=['POST'])
def forw():
    BP.set_motor_power(BP.PORT_A, 50)
    BP.set_motor_power(BP.PORT_D, 50)
    return 'Moving forward'

@app.route('/left', methods=['POST'])
def left():
    BP.set_motor_power(BP.PORT_A, 30)
    BP.set_motor_power(BP.PORT_D, -30)
    return 'Moving left'

@app.route('/right', methods=['POST'])
def right():
    BP.set_motor_power(BP.PORT_A, -30)
    BP.set_motor_power(BP.PORT_D, 30)
    return 'Moving right'

@app.route('/backward', methods=['POST'])
def backw():
    BP.set_motor_power(BP.PORT_A, -30)
    BP.set_motor_power(BP.PORT_D, -30)
    return 'Moving backward'

@app.route('/stop', methods=['POST'])
def stop():
    BP.set_motor_power(BP.PORT_A, 0)
    BP.set_motor_power(BP.PORT_D, 0)
    return 'Stopping'

@app.route('/location')

def location():
    return 'Location: ' + str(posX) + ', ' + str(posY)

if __name__ == '__main__':
    print('Ready.')
    app.run(debug=True, threaded=True, host='0.0.0.0')

def forward(param):
        BP.set_motor_power(BP.PORT_A, param)
        BP.set_motor_power(BP.PORT_D, param)

def backward(param):
        BP.set_motor_power(BP.PORT_A, -param)
        BP.set_motor_power(BP.PORT_D, -param)
