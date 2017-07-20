from __future__ import print_function, division # use python 3 syntax but make it compatible with python 2
from flask import Flask, render_template
from flask import Response
from flask import send_file
from camera_pi import Camera
"from snapshot import takeSnapshot"

import time     # import the time library for the sleep function
import brickpi3 # import the BrickPi3 drivers

BP = brickpi3.BrickPi3() # Create an instance of the BrickPi3 class. BP will be the BrickPi3 object.

'''
def forward(bigTime, speed):
    deltaTime = bigTime / 1000.0;
    t_end = time.time() + deltaTime
    BP.set_motor_power(BP.PORT_A, speed+1.5)
    BP.set_motor_power(BP.PORT_D, speed)
    while time.time() < t_end:
        x=1
    stop()

def correctLeft(bigTime, speed):
    deltaTime = bigTime / 1000.0;
    t_end = time.time() + deltaTime
    BP.set_motor_power(BP.PORT_A, -speed)
    BP.set_motor_power(BP.PORT_D, speed)
    while time.time() < t_end:
        x=1
    stop()
    return 'Correcting left'

def correctRight(bigTime, speed):
    deltaTime = bigTime / 1000.0;
    t_end = time.time() + deltaTime
    BP.set_motor_power(BP.PORT_A, speed)
    BP.set_motor_power(BP.PORT_D, -speed)
    while time.time() < t_end:
        x=1
    stop()
    return 'Correcting right'

def stop():
    BP.set_motor_power(BP.PORT_A, 0)
    BP.set_motor_power(BP.PORT_D, 0)
    stop = True
    return 'Stopping'
'''

"Set Speed here"
speedA = 0
speedD = 0
"Set Starting position here"
posX = 0
posY = 0
"Declare log output file"
f = open('log', 'w')
"Movement completed"

isStop = "True"
routeArray=[]


app = Flask(__name__, static_url_path='')

print('Version 4 - Sogyo.nl Raspberry Pi webserver')
print('Setting up RPi server...')


"""Default page (either no subpage submitted or an unknown one)"""
@app.route('/')
def index():
    return app.send_static_file('index.html')


"""Camera function and return feed (/video_feed)"""
def gen(camera):
    """Video streaming generator function."""
    while True:
        frame = camera.get_frame()
        yield (b'--frame\r\n'
               b'Content-Type: image/jpg\r\n\r\n' + frame + b'\r\n')

def snap(camera):
    frame = camera.get_frame()
    yield (frame)

@app.route('/snapshot.jpg')
def snapshot():
    return Response(snap(Camera()),
                    mimetype='multipart/x-mixed-replace; boundary=frame')
    "takeSnapshot()"
    "return send_file('snapshots/snapshot.jpg', mimetype='image/jpg')"

@app.route('/video_feed')
def video_feed():
    """Video streaming route. Put this in the src attribute of an img tag."""
    return Response(gen(Camera()),
                    mimetype='multipart/x-mixed-replace; boundary=frame')

    
@app.route('/changeRoute/<inputroute>')
def changeRoute(inputroute):
    global routeArray
    routeArray = []
    for letter in inputroute:
        if letter is "f":
            routeArray.append("forward")
        elif letter is "l":
            routeArray.append("left")
        elif letter is "r":
            routeArray.append("right")

@app.route('/getRoute')
def getRoute():
    global routeArray
    return str(routeArray)


@app.route('/nextMove')
def nextMove():
    global isStop
    global routeArray

    command = routeArray[0]
    if command is "forward":
        forward(3150, 40)
    elif command is "left":
        correctLeft(4500, 20)
    elif command is "right":
        correctRight(4500, 20)
    routeArray.pop(0)
    isStop="True"
    time.sleep(1)

@app.route('/setStopVariable=<inputt>')
def setStopVariable(inputt):
    global isStop
    isStop=inputt

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

@app.route('/test')
def test():
    return Response(isStop, mimetype='text/xml')

@app.route('/correctLeft/<int:bigTime>/<int:speed>/', methods=['POST'])
def correctLeft(bigTime, speed):
    deltaTime = bigTime / 1000.0;
    t_end = time.time() + deltaTime
    BP.set_motor_power(BP.PORT_A, -speed)
    BP.set_motor_power(BP.PORT_D, speed)
    while time.time() < t_end:
        x=1
    stop()
    return 'Correcting left'

@app.route('/correctRight/<int:bigTime>/<int:speed>/', methods=['POST'])
def correctRight(bigTime, speed):
    deltaTime = bigTime / 1000.0;
    t_end = time.time() + deltaTime
    BP.set_motor_power(BP.PORT_A, speed)
    BP.set_motor_power(BP.PORT_D, -speed)
    while time.time() < t_end:
        x=1
    stop()
    return 'Correcting right'

@app.route('/forward/<int:bigTime>/<int:speed>/', methods=['POST'])
def forward(bigTime, speed):
    deltaTime = bigTime / 1000.0;
    t_end = time.time() + deltaTime
    BP.set_motor_power(BP.PORT_A, speed+2)
    BP.set_motor_power(BP.PORT_D, speed)
    while time.time() < t_end:
        x=1
    stop()
    return 'Moving forward'

@app.route('/backward/<int:bigTime>/<int:speed>/', methods=['POST'])
def backward(bigTime, speed):
    deltaTime = bigTime / 1000.0;
    t_end = time.time() + deltaTime
    BP.set_motor_power(BP.PORT_A, -speed-2)
    BP.set_motor_power(BP.PORT_D, -speed)
    while time.time() < t_end:
        x=1
    stop()
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
    stop = True
    return 'Stopping'

@app.route('/location')
def location():
    return 'Location: ' + str(posX) + ', ' + str(posY)

if __name__ == '__main__':
    print('Ready.')
    app.run(debug=True, threaded=True, host='0.0.0.0')



def backward(param):
        BP.set_motor_power(BP.PORT_A, -param)
        BP.set_motor_power(BP.PORT_D, -param)

        

