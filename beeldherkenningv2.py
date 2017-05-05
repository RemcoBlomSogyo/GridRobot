import Image
import webbrowser
import time
from time import sleep
import matplotlib
import numpy as np
import matplotlib.pyplot as plt
import pylab as pl
from PIL import Image
import PIL.ImageOps  


# Your code as above

im = Image.open("vloer.png")
# webbrowser.open('vloer.png')
gray = im.convert('L')

for i in range(50, 110):
    # im = gray.point(lambda x: 0 if x<i else 255, '1')
    im = Image.open("vloer.png")
    im = PIL.ImageOps.invert(im)
    im.save("vloer2.png")
    im2 = Image.open("vloer2.png")
    im2 = gray.point(lambda x: 0 if x<i else 255, '1')
    plt.title(i)
    
    plt.imshow(im2)
    plt.pause(0.1)
    plt.draw()