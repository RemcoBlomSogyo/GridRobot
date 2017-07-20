from BrickPi import *

BrickPiSetup()

BrickPi.MotorEnable[PORT_A] = 1
BrickPi.MotorEnable[PORT_D] = 1

BrickPiSetupSensors()

"Set Speed here"
speed = 50

def turnLeft(amount):
        for i in range(1,amount):
                result = BrickPiUpdateValues()
                if not result:
                        BrickPi.MotorSpeed[PORT_A] = speed
                        BrickPi.MotorSpeed[PORT_D] = -speed
                time.sleep(0.1)

def turnRight(amount):
        for i in range(1,amount):
                result = BrickPiUpdateValues()
                if not result:
                        BrickPi.MotorSpeed[PORT_A] = -speed
                        BrickPi.MotorSpeed[PORT_D] = speed
                time.sleep(0.1)

def forward(amount):
        for i in range(1,amount):
                result = BrickPiUpdateValues()
                if not result:
                        BrickPi.MotorSpeed[PORT_A] = speed
                        BrickPi.MotorSpeed[PORT_D] = speed
                time.sleep(0.1)

def turbo(amount):
        for i in range(1,amount):
                result = BrickPiUpdateValues()
                if not result:
                        BrickPi.MotorSpeed[PORT_A] = 3*speed
                        BrickPi.MotorSpeed[PORT_D] = 3*speed
                time.sleep(0.1)
