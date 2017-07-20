import time     # import the time library for the sleep function
import brickpi3 # import the BrickPi3 drivers
from picamera import PiCamera
import cv2
import math 
from scipy import ndimage
from PIL import Image
import PIL.ImageOps
from numpy import average


from Functions import *


BP = brickpi3.BrickPi3() 


def forward(deltaTime, speed):
    t_end = time.time() + deltaTime
    while time.time() < t_end:
        BP.set_motor_power(BP.PORT_A, speed)
        BP.set_motor_power(BP.PORT_D, speed)

def stop():
    BP.set_motor_power(BP.PORT_A, 0)
    BP.set_motor_power(BP.PORT_D, 0)

def left(deltaTime, speed):
    t_end = time.time() + deltaTime
    while time.time() < t_end:
        BP.set_motor_power(BP.PORT_A, speed)
        BP.set_motor_power(BP.PORT_D, -speed)
    stop()

def right(deltaTime, speed):
    t_end = time.time() + deltaTime
    while time.time() < t_end:
        BP.set_motor_power(BP.PORT_D, speed)
        BP.set_motor_power(BP.PORT_A, -speed)
    stop()

def correct():
    camera = PiCamera()
    camera.resolution = (640,480)
    camera.capture('Images/image.jpg')

    commando = "EMPTY"
    horizontalTreshold = 0.03 # slope
    stopTreshold = 0.5 # ratio screen
    adjustThreshold = 1 # degrees
    symmetryThreshold = 100 # pixels

    img = cv2.imread('Images/image.jpg')
    height, width, channels = img.shape

    # Perform line detection
    threshold1 = 0;
    threshold2 = 100;
    gray = cv2.cvtColor(img,cv2.COLOR_BGR2GRAY)
    print("GRAY DONE")
    edges = cv2.Canny(gray, threshold1, threshold2, apertureSize = 3)
    print("CANNY DONE")
    lines = cv2.HoughLinesP(edges, rho = 1, theta = 1*np.pi/1800,
                            threshold = 200, minLineLength = 200,
                            maxLineGap = 100)
    print("HOUGH LINES DONE")
    
    # Edit the found lines
    lines_long = make_long_lines (lines, width, height)
    print("MAKE LONG LINES DONE")
    lines_single = merge_long_lines(img, lines_long)
    print("MERGE LINE DONE")

    (commando, degrees) = checkStraight(lines_single, img,
                                      horizontalTreshold,
                                      adjustThreshold)
    print("CHECKED STRAIGHT DONE");
    
    
    if (commando == "LEFT"):
        (deltaTime, speed) = calculateTimeSpeedFromDegrees(degrees);
        left(deltaTime, speed)
    elif (commando == "RIGHT"):
        (deltaTime, speed) = calculateTimeSpeedFromDegrees(degrees);
        right(deltaTime, speed)
        
        
def calculateTimeSpeedFromDegrees(degrees):
    time = float(0.25*degrees)
    speed = 20
    return (time, speed)


deltaTime = 2
speed = 50
forward(deltaTime, speed)
stop()
correct()
stop()

