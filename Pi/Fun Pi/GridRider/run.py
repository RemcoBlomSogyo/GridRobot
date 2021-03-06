from BrickPi import *

BrickPiSetup()

BrickPi.SensorType[PORT_1] = TYPE_SENSOR_TOUCH
BrickPi.MotorEnable[PORT_A] = 1
BrickPi.MotorEnable[PORT_D] = 1

BrickPiSetupSensors()

while True:
        result = BrickPiUpdateValues()
        if not result:
                if BrickPi.Sensor[PORT_1]:
                        BrickPi.MotorSpeed[PORT_A] = 400
                        BrickPi.MotorSpeed[PORT_D] = -400
                else:
                        BrickPi.MotorSpeed[PORT_A] = 0
                        BrickPi.MotorSpeed[PORT_D] = 0
        time.sleep(0.01)
