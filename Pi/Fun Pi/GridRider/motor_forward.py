from BrickPi import *

BrickPiSetup()

BrickPi.SensorType[PORT_1] = TYPE_SENSOR_TOUCH
BrickPi.MotorEnable[PORT_A] = 1
BrickPi.MotorEnable[PORT_D] = 1

BrickPiSetupSensors()

"Set Speed here"
speed = 50


while True:
        result = BrickPiUpdateValues()
        if not result:
                if BrickPi.Sensor[PORT_1]:
                        BrickPi.MotorSpeed[PORT_A] = speed
                        BrickPi.MotorSpeed[PORT_D] = speed
                else:
                        BrickPi.MotorSpeed[PORT_A] = 1
                        BrickPi.MotorSpeed[PORT_D] = 1
        time.sleep(0.01)
