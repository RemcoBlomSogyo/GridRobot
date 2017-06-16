import time     # import the time library for the sleep function
import cv2
import math 
from scipy import ndimage
from PIL import Image
import PIL.ImageOps
from numpy import average
import requests


from Functions import *


def left(deltaTime, speed):
    print("in left")
    requests.post("http://10.10.4.236:5000/correctLeft/" 
                  + str(deltaTime*1000) + "/" 
                  + str(speed) + "/")

def right(deltaTime, speed):
    print("in right")
    requests.post("http://10.10.4.236:5000/correctRight/" 
                  + str(deltaTime*1000) + "/" 
                  + str(speed) + "/")

def correct():
    #camera = PiCamera()
    #camera.resolution = (640,480)
    #camera.capture('Images/image.jpg')

    commando = "EMPTY"
    horizontalTreshold = 0.03 # slope
    stopTreshold = 0.5 # ratio screen
    adjustThreshold = 1 # degrees
    symmetryThreshold = 100 # pixels

    img = cv2.imread('snapshot.jpg')
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
    
    print(commando)
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

