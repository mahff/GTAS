import serial
import pymysql.cursors
import json
import hashlib
import time
from datetime import datetime as dt
import datetime

class readSerial:
    def __init__(self):
        self.ser = serial.Serial('/dev/ttyACM0', 115200)
        
    def reading(self):
        while True:
            try:
                data = self.ser.readline()
                print(data)
                data = data.decode("utf-8")
                data = data[1:-2]
                print(data)
                return data
                            
            except:
                error = "data not receveid"
                print(error)
                return error

class MYDB:
    def __init__(self):
        self.connection = pymysql.connect(host='remotemysql.com',
                             user='bHykARFGFP',
                             password='GkIgyv0TrD',
                             db='bHykARFGFP',
                             charset='utf8mb4',
                             cursorclass=pymysql.cursors.DictCursor)
    def exitDB(self):
        self.connection.commit()
        self.connection.close()
        print("exit MYDB")

    def setInformation(self, num_etu, cours, arrive, debut, fin):
        try:
            with self.connection.cursor() as cursor:
                cursor.execute ("""INSERT INTO historique SET id_etudiant=%s, cours=TRIM(%s), arrivee=%s, debut=%s, fin=%s""", (num_etu, int(cours), arrive, debut, fin))
                self.connection.commit()
        except pymysql.InternalError as error:
            code, message = error.args
            print( ">>>>>>>>>>>>>", code, message)

    def getStudentInfo(self):
        try:
            self.cursor = self.connection.cursor()
            data = readSerial().reading()
            print("here"+data)
            sql = "SELECT num_etu FROM etudiant WHERE carte=TRIM(%s)"
            self.cursor.execute(sql, format(data))
            myresult = self.cursor.fetchone()
            print(myresult)
            return myresult
        except pymysql.InternalError as error:
            code, message = error.args
            print( ">>>>>>>>>>>>>", code, message)
    def getCourseInfo(self):
        try:
            num_etu = self.getStudentInfo()
            fil = str(num_etu).replace("{'num_etu': ", "").replace("}", "").replace("'", "")
            print(fil)
            ts = time.time()
            timestamp = datetime.datetime.fromtimestamp(ts).strftime('%Y-%m-%d %H:%M:%S')
            print(timestamp)
            sql = "SELECT matiere.id, begin, end from course, etudiant, matiere where (TRIM(etudiant.groupe)=TRIM(course.groupe) AND etudiant.num_etu=TRIM(%s)) AND (course.begin<=%s AND course.end>=%s)  AND (matiere.name=course.course)"
            print(sql)
            self.cursor.execute(sql, (format(fil), timestamp, timestamp))
            myresult = self.cursor.fetchall()
            print(myresult)
            if myresult:
                cours = myresult[0]["id"]
                debut = time.mktime(myresult[0]["begin"].timetuple())
                fin = time.mktime(myresult[0]["end"].timetuple())
                print(fin)
                self.setInformation(format(fil), cours, timestamp, dt.fromtimestamp(debut), dt.fromtimestamp(fin))
            return myresult
        except pymysql.InternalError as error:
            code, message = error.args
            print( ">>>>>>>>>>>>>", code, message)
        
            
if __name__ == '__main__':
    while True:
        db = MYDB().getCourseInfo()
    
