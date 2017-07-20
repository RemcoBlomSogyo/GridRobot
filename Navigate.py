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
    #requests.post("http://10.10.4.236:5000/correctLeft/8505/20/")
    requests.post("http://10.10.4.236:5000/correctLeft/" 
                  + str(int(deltaTime*1000)) + "/" 
                  + str(speed) + "/")

def right(deltaTime, speed):
    requests.post("http://10.10.4.236:5000/correctRight/" 
                  + str(int(deltaTime*1000)) + "/" 
                  + str(int(speed)) + "/")

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
    edges = cv2.Canny(gray, threshold1, threshold2, apertureSize = 3)
    lines = cv2.HoughLinesP(edges, rho = 1, theta = 1*np.pi/1800,
                            threshold = 200, minLineLength = 120,
                            maxLineGap = 100)
    
    # Edit the found lines
    lines_long = make_long_lines (lines, width, height)
    if str(lines_long) == "No lines found":
        return "No lines found"
    lines_single = merge_long_lines(img, lines_long)

    (commando, degrees, horizontalLine) = checkStraight(lines_single, img,
                                      horizontalTreshold,
                                      adjustThreshold)
    
    Ycoordinate= (horizontalLine.A[1]+horizontalLine.B[1])/2
    relativeHeight = Ycoordinate/height
    
    upperTreshold=0.8
    lowerTreshold=0.9
    
    returnCommando="None"
    
    if relativeHeight<upperTreshold:
        print "Correcting forward"
        requests.post('http://10.10.4.236:5000/forward/300/20/')
        returnCommando="correcting"
    elif relativeHeight>lowerTreshold:
        print "Correcting backward"
        requests.post('http://10.10.4.236:5000/backward/300/20/')
        returnCommando="correcting"
    
    
    cmdPrint=""
    if commando=="LEFT":
        cmdPrint="Correcting left"
    elif commando=="RIGHT":
        cmdPrint="Correcting right"
    elif commando == None:
        cmdPrint="Correcting none"
    
    print cmdPrint, round(degrees,2)
    
    (deltaTime, speed) = calculateTimeSpeedFromDegrees(degrees);
    if degrees< 45:
        if (commando == "LEFT"):
            left(deltaTime, speed)
            returnCommando="correcting"
        elif (commando == "RIGHT"):
            right(deltaTime, speed)
            returnCommando="correcting"
    else:
        print "No horizontal lines found, going backwards"
        requests.post('http://10.10.4.236:5000/backward/500/20/')
    
    return returnCommando
        
        
def calculateTimeSpeedFromDegrees(degrees):
    #time = float(0.08*degrees)
    time = float(0.06*degrees)
    speed = 20
    return (time, speed)

