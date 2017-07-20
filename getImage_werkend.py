from six.moves import urllib
import requests
import time;
import os
import Navigate

def download_very_big_image():
    os.remove("snapshot.jpg")
    url = 'http://10.10.4.236:5000/snapshot.jpg'
    filename = 'snapshot.jpg'
    conn = urllib.request.urlopen(url)
    output = open(filename, 'wb') #binary flag needed for Windows     
    output.write(conn.read())
    output.close()
    conn.close()

def getStatus ():
    url = 'http://10.10.4.236:5000/test'
    r = requests.get(url)
    #print r.content
    #return r.content
    if (r.content == "False"):
        return False
    elif (r.content == "True"):
        return True
    else:
        return None

#download_very_big_image()

requests.get('http://10.10.4.236:5000/changeRoute/flfrfrflflfrfrflf')
r2 = requests.get('http://10.10.4.236:5000/getRoute')


deltaTime = 2 * 1000; # multiply by 1000 to send as int
speed = 50
 
#requests.post("http://10.10.4.236:5000/correctLeft/8505/20/")
requests.get('http://10.10.4.236:5000/setStopVariable=True')
deltaTimeRequest = 1
startTime = time.time()
i = 0
while True:
        
    status = getStatus()
    print("start ronde " + str(i) + " = " + str(status))
    if status:
        download_very_big_image() 
        commando = Navigate.correct()
        if str(commando) == "None":
            requests.get('http://10.10.4.236:5000/setStopVariable=False')
        elif commando == "No lines found":
            print "No lines found 1"
            download_very_big_image()
            commando = Navigate.correct()
            if str(commando) == "None":
                requests.get('http://10.10.4.236:5000/setStopVariable=False')
            elif str(commando) == "No lines found":
                print "No lines found 2"
                requests.post('http://10.10.4.236:5000/backward/500/20/')
         
        status = getStatus()
           
    if not status:  
        print "Moving"
        requests.get('http://10.10.4.236:5000/nextMove') 
           
    time.sleep(deltaTimeRequest)
    startTime = time.time()
    i = i + 1
    
    
    
