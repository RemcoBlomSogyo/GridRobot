import cv2
import math 
from scipy import ndimage
from PIL import Image
import PIL.ImageOps
from numpy import average
from Functions import *
from Intersection import *

commando = "STANDARD"
horizontalTreshold = 0.03 # slope
stopTreshold = 0.5 # ratio screen
adjustThreshold = 2 # degrees

img = cv2.imread('image5.jpg')
height, width, channels = img.shape
print "height =", height, ", width =", width

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
print commando 

#show image
cv2.imshow('hough', img)
cv2.waitKey(0)
     
        

'''
#afwijkende lijn weghalen
arrayA=[]
intersections=[]
for t in range(0,len(lines_single)):                                                    # eerste lijn
    line = lines_single[t]
    x1 = line.A[0]
    y1 = line.A[1]
    x2 = line.B[0]
    y2 = line.B[1]
    arrayA.append(round((float(y2)-float(y1))/(float(x2)-float(x1)),2))
    for r in range (0,len(lines_single)):                                               # tweede lijn
        line2 = lines_single[r]
        line_x1 = line2.A[0]
        line_y1 = line2.A[1]
        line_x2 = line2.B[0]
        line_y2 = line2.B[1]
        intersectionPoint=line_intersection(([x1,y1],[x2,y2]),([line_x1,line_y1],[line_x2,line_y2]), width, height)
           
        if intersectionPoint!=None:
            existsNot = True;
            for i in range(0,len(intersections)):                                       # intersections
                intersection = intersections[i]
                if intersectionPoint == intersection.point:
                    existsNot=False                    
                    break;
                    
            if (existsNot):
                intersection = Intersection(intersectionPoint, line, line2)
                intersections.append(intersection)
                print intersectionPoint[0]
                print intersectionPoint[1]
                cv2.line(img,(intersectionPoint[0], intersectionPoint[1]), (intersectionPoint[0] + 2, intersectionPoint[1] + 2),(0,0,255),5)
     
print intersections[0].line1
''' 
