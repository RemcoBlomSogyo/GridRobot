import cv2
import numpy as np
from scipy import ndimage
from PIL import Image
import PIL.ImageOps
from numpy import average



img = cv2.imread('vloer2.png')
gray = cv2.cvtColor(img,cv2.COLOR_BGR2GRAY)

height, width, channels = img.shape
print height, width, channels


def line_intersection(line1, line2):
    xdiff = (line1[0][0] - line1[1][0], line2[0][0] - line2[1][0])
    ydiff = (line1[0][1] - line1[1][1], line2[0][1] - line2[1][1]) #Typo was here

    def det(a, b):
        return a[0] * b[1] - a[1] * b[0]

    div = det(xdiff, ydiff)
    if div == 0:
       return None;

    d = (det(*line1), det(*line2))
    x = det(d, xdiff) / div
    y = det(d, ydiff) / div
    
    if x < 0 or x > width or y < 0 or y > height:
        return None;

    return int(x), int(y)


i = 0;
j = 100;

edges = cv2.Canny(gray,i,j,apertureSize = 3)
#font = cv2.FONT_HERSHEY_SIMPLEX
#cv2.putText(edges, str(i) + ", " + str(j) ,(10,500), font, 4,(255,0,0),2,cv2.LINE_AA)


#inverted_image = PIL.ImageOps.invert(edges)
#cv2.imshow('inverted_image',inverted_image)
#cv2.waitKey(0)

#dilated=ndimage.binary_dilation(edges)

#cv2.imshow('edges',edges)
#cv2.waitKey(0)


#edges2=ndimage.binary_closing(edges, structure=np.ones((2,2))).astype(np.int)


minLineLength = 100
maxLineGap = 10
#lines = cv2.HoughLinesP(edges,1,np.pi/180,100,minLineLength,maxLineGap)
lines = cv2.HoughLinesP(edges,rho = 1,theta = 1*np.pi/1800,threshold = 200,minLineLength = 200,maxLineGap = 100)
lines_long = []
for x in range(0, len(lines)):
    for x1,y1,x2,y2 in lines[x]:
        a=(float(y2)-float(y1))/(float(x2)-float(x1))
        b=y1-a*x1
        
        if b<0:
            xStart=int(-b/a) 
            yStart=0
        elif b>height:
            xStart=int((height-b)/a)
            yStart=height
        else:
            xStart=0
            yStart=int(b)
        
        yTemp=int(a*width+b)
        if yTemp>=0 and yTemp<=height:
            yEnd=yTemp
            xEnd=width
        elif a>=0:
            xEnd = int((height - b)/a)
            yEnd = height
        elif a<0:
            xEnd=int(-b/a) 
            yEnd=0
             
        lines_long.append([xStart, yStart, xEnd, yEnd])   
        #cv2.line(img,(x1,y1),(x2,y2),(255,0,0),2)
        #cv2.line(img,(xStart,yStart),(xEnd,yEnd),(0,255,0),2)

varPixel = 30
lines_single = [];  
for z in range(0,len(lines_long)):
    if lines_long[z]!=None:
        for line_x1,line_y1,line_x2,line_y2 in [lines_long[z]]:
            group_lines = []
            for q in range(0,len(lines_long)):
                if lines_long[q]!=None:
                    for x1,y1,x2,y2 in [lines_long[q]]:
                        if (lines_long[q] != None and abs(line_x1 - x1) < varPixel and abs(line_y1 - y1) < varPixel
                            and abs(line_x2 - x2) < varPixel and abs(line_y2 - y2) < varPixel):
                            group_lines.append(lines_long[q]);
                            #[0,0,0,0] -> None ??
                            lines_long[q] = None;
                            
            xStartArray = []
            yStartArray = []
            xEndArray = []
            yEndArray = []
            
            for x in range(0, len(group_lines)):
                for x1,y1,x2,y2 in [group_lines[x]]:
                    xStartArray.append(x1)
                    yStartArray.append(y1)
                    xEndArray.append(x2)
                    yEndArray.append(y2)
                
            lines_single.append([np.average(xStartArray), np.average(yStartArray), np.average(xEndArray), np.average(yEndArray)])
            cv2.line(img,(int(np.average(xStartArray)), int(np.average(yStartArray))),(int(np.average(xEndArray)), int(np.average(yEndArray))),(0,255,0),2)

#afwijkende lijn weghalen
arrayA=[]
intersections=[]
for t in range(0,len(lines_single)):
    for x1,y1,x2,y2 in [lines_single[t]]:
        arrayA.append(round((float(y2)-float(y1))/(float(x2)-float(x1)),2))
        for r in range (0,len(lines_single)):
            for line_x1,line_y1,line_x2,line_y2 in [lines_single[r]]:
                intersectionPoint=line_intersection(([x1,y1],[x2,y2]),([line_x1,line_y1],[line_x2,line_y2]))

                if intersectionPoint!=None:
                    intersections.append(intersectionPoint)
                    print intersectionPoint[0]
                    print intersectionPoint[1]
                    cv2.line(img,(intersectionPoint[0], intersectionPoint[1]), (intersectionPoint[0] + 2, intersectionPoint[1] + 2),(0,0,255),5)


print intersections




cv2.imshow('hough',img)
cv2.waitKey(0)







