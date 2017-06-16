from six.moves import urllib
import requests

import Navigate

def download_very_big_image():
    url = 'http://10.10.4.236:5000/snapshot.jpg'
    filename = 'snapshot.jpg'
    conn = urllib.request.urlopen(url)
    output = open(filename, 'wb') #binary flag needed for Windows
    output.write(conn.read())
    output.close()



def getStatus ():
    url = 'http://10.10.4.236:5000/test'
    r = requests.get(url)
    print(r.content)


deltaTime = 2 * 1000;
speed = 50

download_very_big_image()
Navigate.correct()
getStatus()
