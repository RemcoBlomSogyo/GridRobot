import cv2
import math 
from scipy import ndimage
from PIL import Image
import PIL.ImageOps
from numpy import average
from Functions import *
from Intersection import *

commando = "EMPTY"
horizontalTreshold = 0.03 # slope
stopTreshold = 0.5 # ratio screen
adjustThreshold = 2 # degrees
symmetryThreshold = 100 # pixels

img = cv2.imread('image5.jpg')
height, width, channels = img.shape
print("height =", height, ", width =", width)

# Perform line detection
threshold1 = 0;
threshold2 = 100;
gray = cv2.cvtColor(img,cv2.COLOR_BGR2GRAY)
edges = cv2.Canny(gray, threshold1, threshold2, apertureSize = 3)
lines = cv2.HoughLinesP(edges, rho = 1, theta = 1*np.pi/1800, threshold = 200, minLineLength = 200, maxLineGap = 100)

# Edit the found lines
lines_long = make_long_lines (lines, width, height)
lines_single = merge_long_lines(img, lines_long)

# Check what to do (stop, left, right)
commando = calculateCommando(lines_single, img, height, width, horizontalTreshold, stopTreshold, adjustThreshold)
print(commando)

# Check adjustment necessary, based on vertical lines?
maxNeg = -9999999
lineMaxNeg = None
minPos = 99999999
lineMinPos = None
half = width / 2
for line in lines_single:
    x = None;
    if line.getA()[1] == 0:
        x = line.getA()[0] - half
    elif line.getB()[1] == 0:
        x = line.getB()[0] - half
    
    if (x != None and x < 0 and x > maxNeg):
        maxNeg = x
        lineMaxNeg = line
    elif(x != None and x > 0 and x < minPos):
        minPos = x
        lineMinPos = line

if lineMaxNeg != None and lineMinPos != None:        
    lineMaxNeg.draw(img, 0, 0, 0)
    lineMinPos.draw(img, 255, 255, 255)
    # Check if they are approximately the same distance of the middle
    print("difference = ", abs(minPos + maxNeg))
    if abs(minPos + maxNeg) > symmetryThreshold:
        print("CORRIGEER")
    else:
        print("GOING STRAIGHT!")
elif lineMaxNeg != None and lineMinePos == None:
    lineMaxNeg.draw(img, 0, 0, 0)
    print("CORRIGEER NAAR LINKS")
elif lineMaxNeg == None and lineMinPos != None:
    lineMinPos.draw(img, 255, 255, 255)
    print("CORRIGEER NAAR RECHTS")
elif lineMaxNeg == None and lineMinPos == None:
    print("???")

#show image
cv2.imshow('hough', img)
cv2.waitKey(0)
