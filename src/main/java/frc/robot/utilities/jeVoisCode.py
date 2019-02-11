import libjevois as jevois
import cv2
import numpy as np
import math
import json
#4060/distCentroids = distToReflectTape (in)
class Robotics:
        #count=0
        rollingm_1 = []
        rollingm_2 = []
        rollingm_3 = []
        rollingm_4 = []
        #creates a new font
        font = cv2.FONT_HERSHEY_SIMPLEX
        def process(self, inframe, outframe):
            #gets the width and height of the image
            def getHeight(image):
                    if(np.size(image, 0) != null)
                        height = np.size(image, 0)
                    else
                        height = 1
                    return height
            def getWidth(image):
                    if(np.size(image, 1) != null)
                        width = np.size(image, 1)
                    else
                        width = 1
                    return width
            #finds the center between the center of the image and center of the targets
            def findDistBetweenCenters(median, thresh):
                    #creates a circle at the center of the image
                    cv2.circle(median,(int(getWidth(median)/2), int(getHeight(median)/2)), 5, (255,255,255), -1)
                    #finds the moments of the threshold image
                    M = cv2.moments(thresh)
                    #if statement prevents a divide by zero error
                    try:
                        if(M["m00"] !=0):
                            #Finds the x and y cordinates of the center of the two vision targets
                            cX = int(M["m10"]/M["m00"])
                            cY = int(M["m01"]/M["m00"])
                            #creates a circle at the center of the vision targets
                            #cv2.circle(median,(cX, cY), 5, (255,255,255), -1)
                            #draws a line between the center of the screen and the center of the targets
                            #cv2.line(median, (cX,cY),(int(getWidth(median)/2), int(getHeight(median)/2)), (255,0,0),5)
                            #finds the distance between the center of the screen and the center of the targets (pixels)
                            dist = math.sqrt(math.pow((cX-(getWidth(median)/2)),2)+math.pow((cY-(getHeight(median)/2)),2))
                            #puts text on the screen regarding distance between the center of the screen and the vision targets (pixels)
                            #cv2.putText(median,'Distance: ' + str(int(dist))+ ' (pixels)' ,(0,int(getHeight(median))-35), font, .5,(255,255,255),1,cv2.LINE_AA)
                    except:
                        jevois.LINFO("Failed to get X,Y coordinates of the vision targets")
            def createContours(image, grayImage) :
                    #generates contours on the grayscale image
                    contours, hierarchy = cv2.findContours(grayImage, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)
                    #draws those contours on the median image
                    #cv2.drawContours(image, contours,-1,(100,255,100), 3)
                    return contours
            def distContours(contours) :
                    #in case there is more than two vison targets detected
                    if(len(contours)==2):
                            #seperate the contours
                            cnt0=contours[0]
                            cnt1=contours[1]
                            #creates moments for both contours
                            mom0 = cv2.moments(cnt0)
                            mom1 = cv2.moments(cnt1)
                            #prevents a divide by zero error
                            if(mom0['m00'] != 0 and mom1['m00'] != 0):
                                    #finds the cordinates of the center of the vision targets
                                    ccX0 = int(mom0['m10']/mom0['m00'])
                                    ccX1 = int(mom1['m10']/mom1['m00'])
                                    ccY0 = int(mom0['m01']/mom0['m00'])
                                    ccY1 = int(mom1['m01']/mom1['m00'])
                                    #calculates the distance between the centers of the targets (pixels)
                                    centroidDistance=math.sqrt(math.pow((ccX1-ccX0),2)+math.pow((ccY1-ccY0),2))
                                    #puts the distance between centroids on the screen in text form (pixels)
                                    #cv2.putText(median,'Distance between centroids: ' + str(int(centroidDistance)) + ' pixels' ,(0,int(getHeight(median))-20), font, .5,(255,255,255),1,cv2.LINE_AA)
                                    #puts the distance between the camera and the targets on the screen (inches)
                                    #cv2.putText(median,'Distance to tape: ' + str(int(4060/centroidDistance))+' in' ,(0,int(getHeight(median))-5), font, .5,(255,255,255),1,cv2.LINE_AA)
                                    #creates a line through the contours
                                    """rows,cols = median.shape[:2]
                                    [vx,vy,x,y] = cv2.fitLine(cnt0, cv2.DIST_L2,0,0.01,0.01)
                                    lefty = int((-x*vy/vx) + y)
                                    righty = int(((cols-x)*vy/vx)+y)
                                    #cv2.line(median,(0,lefty),(x,y),(250,0,100),2)
                                    rows1,cols1 = median.shape[:2]
                                    [vx1,vy1,x1,y1] = cv2.fitLine(cnt1, cv2.DIST_L2,0,0.01,0.01)
                                    lefty1 = int((-x1*vy1/vx1) + y1)
                                    righty1 = int(((cols1-x1)*vy1/vx1)+y1)
                                    #cv2.line(median,(cols1-1,righty1),(0,lefty1),(250,0,100),2)
                                    #create triangle
                                    #cv2.line(median,(x,y),(x,lefty),(250,0,100),2)
                                    distAdj = y- lefty
                                    distH = math.sqrt(math.pow((cols-1-x),2)+math.pow((lefty-y),2))
                                    angle = math.degrees(math.acos(distAdj/distH))
                                    #cv2.putText(median,'Angle 1: ' + str(angle) ,(0,20), font, .5,(255,255,255),1,cv2.LINE_AA)
                                    #cv2.line(median,(x1,y1),(x1,righty1),(250,0,100),2)
                                    distAdj1 = y1- righty1
                                    distH1 = math.sqrt(math.pow((cols1-1-x1),2)+math.pow((righty1-y1),2))
                                    angle1 = math.degrees(math.acos(distAdj1/distH1))
                                    #cv2.putText(median,'Angle 2: ' + str(angle1) ,(0,40), font, .5,(255,255,255),1,cv2.LINE_AA)
                                    #creates a rectangle around the contour
                                    rect = cv2.minAreaRect(contours[0])
                                    #creates the bounding box around the vision targets
                                    box = cv2.boxPoints(rect)
                                    #putting the box height (I think) and putting it on screen
                                    #cv2.putText(median,'Box: '+str((math.sqrt((box[0][0]-box[1][0])*(box[0][0]-box[1][0])+(box[0][1]-box[1][1])*(box[0][1]-box[1][1]))/math.sqrt((box[2][0]-box[1][0])*(box[2][0]-box[1][0])+(box[2][1]-box[1][1])*(box[2][1]-box[1][1])))),(0,int(getHeight(median))-50), font, .5,(255,255,255),1,cv2.LINE_AA)"""
            def serialStuff():
                #stuff = {"Hello" : 1}
                #json_stuff = json.dumps(stuff)
                jevois.sendSerial("Hello ")
            def jamesCode(contours,img):
                    width=getWidth(img)
                    height=getHeight(img)
                    if(len(contours)==2):
                            rect0 = cv2.minAreaRect(contours[0])
                            box0 = cv2.boxPoints(rect0)
                            box0 = np.int0(box0)
                            rect1 = cv2.minAreaRect(contours[1])
                            box1 = cv2.boxPoints(rect1)
                            box1 = np.int0(box1)
                            m_1=999
                            m_2=1
                            m_3=999
                            m_4=1
                            for a in range(4):
                                    #box0[a][0] must be less than 999
                                    if(box0[a][0]<m_1):
                                            m_1=box0[a][0]
                                    if(box0[a][0]>m_2):
                                            m_2=box0[a][0]
                                    if(box1[a][0]<m_3):
                                            m_3=box1[a][0]
                                    if(box1[a][0]>m_4):
                                            m_4=box1[a][0]
                            #Swap values
                            if(m_1>m_3):
                                    hold_1=m_1
                                    hold_2=m_2
                                    m_1=m_3
                                    m_2=m_4
                                    m_3=hold_1
                                    m_4=hold_2
                            
                            if(len(self.rollingm_1)<9):
                                    self.rollingm_1.append(m_1)
                                    self.rollingm_2.append(m_2)
                                    self.rollingm_3.append(m_3)
                                    self.rollingm_4.append(m_4)
                            else:
                                    self.rollingm_1.pop(0)
                                    self.rollingm_2.pop(0)
                                    self.rollingm_3.pop(0)
                                    self.rollingm_4.pop(0)
                                    self.rollingm_1.append(m_1)
                                    self.rollingm_2.append(m_2)
                                    self.rollingm_3.append(m_3)
                                    self.rollingm_4.append(m_4)
                            m_1=sum(self.rollingm_1)/float(len(self.rollingm_1))-int(width)/2
                            m_2=sum(self.rollingm_2)/float(len(self.rollingm_2))-int(width)/2
                            m_3=sum(self.rollingm_3)/float(len(self.rollingm_3))-int(width)/2
                            m_4=sum(self.rollingm_4)/float(len(self.rollingm_4))-int(width)/2
                            m=0
                            dm=0.02;
                            c=int(width)
                            cm1m = c/m_1-m
                            cm2m = c/m_2-m
                            cm3m = c/m_3-m
                            cm4m = c/m_4-m
                            cmwhat1 = math.pow(((1+m/cm1m+1+m/cm2m)/2-(1+m/cm3m+1+m/cm4m)/2),2)
                            cmwhat2 = math.pow((1/2/cm1m+1/2/cm2m)-(1/2/cm3m+1/2/cm4m),2)
                            b=11.31/math.sqrt(cmwhat1+cmwhat2)
                            #jevois.LINFO(str(m_1))
                            #jevois.LINFO(str(m_2))
                            #jevois.LINFO(str(m_3))
                            #jevois.LINFO(str(m_4))
                            rrrt1=math.pow((m*b*(c/m_1-c/m_2)/(c/m_1-m)/(c/m_2-m)),2)
                            rrrt2=math.pow(((b/(c/m_1-m))-(b/(c/m_2-m))),2)
                            #jevois.LINFO("r1 "+str(rrrt1))
                            #jevois.LINFO("r2 "+str(rrrt2))
                            rrrt=math.sqrt(rrrt1+rrrt2)
                            #jevois.LINFO(str(rrrt))
                            #jevois.LINFO(str(b))
                            if(rrrt!=0):
                                    mb = math.pow((m*b*(c/m_3-c/m_4)/(c/m_3-m)/(c/m_4-m),2)
                                    mb1 = math.pow(((b/(c/m_3-m))-(b/(c/m_4-m))),2)
                                    m_d=math.sqrt((mb))+mb1)/rrrt
                            else:
                                    m_d=999
                            if(m_d>1):
                                mb = math.pow((m*b*(c/m_3-c/m_4)/(c/m_3-m)/(c/m_4-m),2)
                                mb1 = math.pow(((b/(c/m_3-m))-(b/(c/m_4-m))),2)
                                why = (mb)+(mb1)/rrrt
                                    while(m_d>1):
                                            m+=dm
                                            if(math.sqrt(why>m_d):
                                                    dm=0-dm
                                                    m+=dm
                                            else:
                                                    m_d=math.sqrt(why)
                                            b=11.31/math.sqrt(cmwhat1+cmwhat2)
                            else:
                                    while(m_d<1):
                                            m+=dm
                                            if(math.sqrt(why)<m_d):
                                                    dm=0-dm
                                                    m+=dm
                                            else:
                                                    m_d=math.sqrt(why)
                                            b=11.31/math.sqrt(cmwhat1+cmwhat2)
                            """for a in range(10):
                                    cv2.line(median,(46+4*a,int(height)-7-8*a),(48+4*a,int(height)-11-8*a),(100,0,0),1)
                                    cv2.line(median,(44-4*a,int(height)-7-8*a),(42-4*a,int(height)-11-8*a),(100,0,0),1)
                            for a in range(7):
                                    cv2.line(median,(9+12*a,int(height)-5),(9+12*a,int(height)-85),(100,100,100),1)
                                    cv2.line(median,(5,int(height)-5-12*a),(85,int(height)-5-12*a),(100,100,100),1)
                            #cv2.line(median,(45,int(height)-5),(45,int(height)-85),(255,255,255),1)"""
                            #cv2.line(median,(int(45-(b/(m-c/m_1)+b/(m-c/m_2)+b/(m-c/m_3)+b/(m-c/m_4))/4),int(height)-5),(int(45-(b/(m-c/m_1)+b/(m-c/m_2)+b/(m-c/m_3)+b/(m-c/m_4))/4),int(height)-85),(255,255,0),1)
                            #cv2.line(median,(5,int(int(height)-5-((b-m*b/(m-c/m_1))+(b-m*b/(m-c/m_2))+(b-m*b/(m-c/m_3))+(b-m*b/(m-c/m_4)))/4)),(85,int(int(height)-5-((b-m*b/(m-c/m_1))+(b-m*b/(m-c/m_2))+(b-m*b/(m-c/m_3))+(b-m*b/(m-c/m_4)))/4)),(255,255,0),1)
                            x_dist1=(b-m*b/(m-c/m_1))
                            x_dist2=(b-m*b/(m-c/m_2))
                            x_dist3=(b-m*b/(m-c/m_3))
                            x_dist4=(b-m*b/(m-c/m_4))
                            x_distance=int((x_dist1+x_dist2+x_dist3+x_dist4)/4)
                            y_distance=int((b/(m-c/m_1)+b/(m-c/m_2)+b/(m-c/m_3)+b/(m-c/m_4))/4)
                            #cv2.putText(median,str(int(x_distance)) ,(45-x_distance-5,int(height)-87), font, .16,(255,255,0),1,cv2.LINE_AA)
                            #cv2.putText(median,str(int(y_distance)) ,(87,int(height)-2-y_distance), font, .16,(255,255,0),1,cv2.LINE_AA)
                            #cv2.line(median,(int(45-b/(m-c/m_1)),int(int(height)-5-(b-m*b/(m-c/m_1)))),(int(45-b/(m-c/m_2)),int(int(height)-5-(b-m*b/(m-c/m_2)))),(0,255,0),2)
                            #cv2.line(median,(int(45-b/(m-c/m_3)),int(int(height)-5-(b-m*b/(m-c/m_3)))),(int(45-b/(m-c/m_4)),int(int(height)-5-(b-m*b/(m-c/m_4)))),(0,255,0),2)
                            #cv2.line(median,(5,int(height)-5),(85,int(height)-5),(255,255,255),2)
                            #cv2.line(median,(5,int(height)-5),(5,int(height)-85),(255,255,255),2)
                            #cv2.line(median,(85,int(height)-5),(85,int(height)-85),(255,255,255),2)
                            #cv2.line(median,(5,int(height)-85),(85,int(height)-85),(255,255,255),2)
                            """for a in range(7):
                                    cv2.line(median,(9+12*a,int(height)-5),(9+12*a,int(height)-7),(255,255,255),1)
                                    cv2.line(median,(9+12*a,int(height)-83),(9+12*a,int(height)-85),(255,255,255),1)
                                    cv2.line(median,(5,int(height)-5-12*a),(7,int(height)-5-12*a),(255,255,255),1)
                                    cv2.line(median,(83,int(height)-5-12*a),(85,int(height)-5-12*a),(255,255,255),1)"""
            #creates an image
            img = inframe.getCvBGR()
            #this value is for getting the lower barrier for detecting the green
            lower_green = np.array([0,200,0])
            #this value is for getting the upper barrier for detecting the green
            upper_green = np.array([255,255,160])
            #creates an image mask (basically a black and white image) with black being the background and white being the vision targets
            mask = cv2.inRange(img,lower_green, upper_green)
            #makes it so that the image is just the green vision targets
            res = cv2.bitwise_and(img,img,mask= mask)
            #filters out noise
            median = cv2.medianBlur(res,5)
            #creates a grayscale image from median
            gray_image = cv2.cvtColor(median, cv2.COLOR_BGR2GRAY)
            #creates a threshold image based of the grayscale image
            ret, thresh = cv2.threshold(gray_image, 127,255,0)
            #gets the height of the image
            findDistBetweenCenters(median, thresh)
            contours = createContours(median, gray_image)
            distContours(contours)
            #jamesCode(contours,img)
            serialStuff()
            """for c in contours :
                     cnt = c
                     area = cv2.contourArea(cnt)
                     #jevois.LINFO(str(area))
                     rect = cv2.minAreaRect(cnt)
                     box = cv2.boxPoints(rect)
                     box = np.int0(box)
                     cv2.drawContours(median,[box],0,(0,0,255),2)
                     leftmost = tuple(cnt[cnt[:,:,0].argmin()][0])
                     rightmost = tuple(cnt[cnt[:,:,0].argmax()][0])
                     cv2.circle(median,leftmost,5,(255,0,0),-1)
                     cv2.circle(median,rightmost,5,(255,0,0),-1)
                     cv2.line(median,leftmost,rightmost,(255,255,255),5)
                     mom = cv2.moments(cnt)
                     if(mom['m00'] != 0):
                             ccX = int(mom['m10']/mom['m00'])
                             ccY = int(mom['m01']/mom['m00'])
                             cv2.circle(median,(ccX, ccY), 5, (0,0,255), -1)
                        #jevois.LINFO(str(contours))
            #cv2.imwrite("frames/frame%d.png"%self.count,img)
            #self.count+=1
            #displays the image on the video feed"""
            outframe.sendCv(median)
        