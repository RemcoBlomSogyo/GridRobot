from __future__ import print_function # use python 3 syntax but make it compatible with python 2
from __future__ import division       #                           ''

import time     # import the time library for the sleep function
import brickpi3 # import the BrickPi3 drivers

BP = brickpi3.BrickPi3() # Create an instance of the BrickPi3 class. BP will be the BrickPi3 object.

"Set Speed here"
speedA = 0
speedD = 0

def forward(param):
        speedA = param
        speedD = param

def backward(param):
        speedA = -param
        speedD = -param


while True:
        BP.set_motor_power(BP.PORT_A, speedA)
        BP.set_motor_power(BP.PORT_D, speedD)
        time.sleep(0.01)
