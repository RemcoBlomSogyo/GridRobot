import cv2
import numpy as np

img = cv2.imread('vloer.png')
gray = cv2.cvtColor(img,cv2.COLOR_BGR2GRAY)

for i in range(0, 100, 10):
    for j in range(0, 100, 10):
        edges = cv2.Canny(gray,i,j,apertureSize = 3)
        font = cv2.FONT_HERSHEY_SIMPLEX
        cv2.putText(edges, str(i) + ", " + str(j) ,(10,500), font, 4,(255,0,0),2,cv2.LINE_AA)
        cv2.imshow('edges',edges)
        cv2.waitKey(0)

minLineLength = 30
maxLineGap = 10
lines = cv2.HoughLinesP(edges,1,np.pi/180,15,minLineLength,maxLineGap)
for x in range(0, len(lines)):
    for x1,y1,x2,y2 in lines[x]:
        cv2.line(img,(x1,y1),(x2,y2),(0,255,0),2)

#cv2.imshow('hough',img)
#cv2.waitKey(0)