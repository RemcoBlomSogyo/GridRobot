
from picamera import PiCamera


def correct():
    camera = PiCamera()
    camera.capture('Images/image.jpg')
