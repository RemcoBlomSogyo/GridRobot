from picamera import PiCamera

camera = PiCamera()

def takeSnapshot():
    camera.resolution = (640,480)
    camera.capture('snapshots/snapshot.jpg')
    return
