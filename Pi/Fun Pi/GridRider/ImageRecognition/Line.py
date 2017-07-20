import math 
import numpy as np
import cv2

class Line:
    
    def __init__(self, A, B):
        if (A[0] < B[0]):
            self.A = A
            self.B = B
        else:
            self.A = B
            self.B = A
        self.intersections = []
       
    def addIntersection(self, intersection):
       intersections.append(intersection);

    def getSlope(self):
        return (float(self.B[1])-float(self.A[1]))/(float(self.B[0])-float(self.A[0]));
    
    def getDegrees(self):
        overstaand= abs(self.getA()[1]-self.getB()[1])
        aanliggend=abs(self.getA()[0]-self.getB()[0])
        return (math.atan(float(overstaand)/float(aanliggend)))/np.pi*180.0;
    
    def getA(self):
        return self.A;
    
    def getB(self):
        return self.B;
    
    def draw(self, img, red, green, blue):
        cv2.line(img,(int(self.A[0]), int(self.A[1])),
                 (int(self.B[0]), int(self.B[1])),(red, green, blue),2)
