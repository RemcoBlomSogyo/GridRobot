import cv2
import numpy as np
from scipy import ndimage
from PIL import Image
import PIL.ImageOps


img = cv2.imread('vloer.png')
gray = cv2.cvtColor(img,cv2.COLOR_BGR2GRAY)

i = 0;
j = 100;

edges = cv2.Canny(gray,i,j,apertureSize = 3)
#font = cv2.FONT_HERSHEY_SIMPLEX
#cv2.putText(edges, str(i) + ", " + str(j) ,(10,500), font, 4,(255,0,0),2,cv2.LINE_AA)
inverted_image = PIL.ImageOps.invert(edges)

cv2.imshow('inverted_image',inverted_image)
cv2.waitKey(0)

#dilated=ndimage.binary_dilation(edges)

edges2=ndimage.binary_closing(edges, structure=np.ones((2,2))).astype(np.int)


cv2.imshow('edges2',edges2)
cv2.waitKey(0)


minLineLength = 100
maxLineGap = 10
#lines = cv2.HoughLinesP(edges,1,np.pi/180,100,minLineLength,maxLineGap)
lines = cv2.HoughLinesP(edges,rho = 1,theta = 1*np.pi/1800,threshold = 200,minLineLength = 200,maxLineGap = 100)
for x in range(0, len(lines)):
    for x1,y1,x2,y2 in lines[x]:
        cv2.line(img,(x1,y1),(x2,y2),(0,255,0),2)

cv2.imshow('hough',img)
cv2.waitKey(0)