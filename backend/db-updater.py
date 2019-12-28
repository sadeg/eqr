import time
import requests
import xml.etree.ElementTree as ET
from datetime import datetime
from khayyam import JalaliDatetime
from sqlalchemy import Column, String, DateTime, Integer, Float ,ForeignKey
from sqlalchemy.orm import sessionmaker
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy import create_engine

Base = declarative_base()
class Event(Base):
    __tablename__ = "events"
    id = Column(Integer, primary_key=True)
    reg1 = Column(String, nullable=False)
    dis1 = Column(Integer, nullable=False)
    reg2 = Column(String, nullable=True)
    dis2 = Column(Integer, nullable=True)
    reg3 = Column(String, nullable=True)
    dis3 = Column(Integer, nullable=True)
    mag = Column(Float, nullable= False)
    dep = Column(Float, nullable= True)
    lng = Column(Float, nullable= False)
    lat = Column(Float, nullable= False)
    datetime = Column(DateTime, nullable=False)

url = "http://irsc.ut.ac.ir/events_list_fa.xml"
if __name__ == "__main__":
    engine = create_engine('sqlite:///data.db')
    Base.metadata.create_all(engine)
    Base.metadata.bind = engine
    DBsession = sessionmaker(bind=engine)
    session = DBsession()
    while(1):
        try:
            r = requests.get(url, stream=True)
            xml = r.content
            tree = ET.fromstring(xml)
            for item in tree:
                id = item.find("id").text
                reg1 = item.find("reg1").text
                dis1 = item.find("dis1").text
                reg2 = item.find("reg2").text
                dis2 = item.find("dis2").text
                reg3 = item.find("reg3").text
                dis3 = item.find("dis3").text
                mag = item.find("mag").text
                dep = item.find("dep").text
                lng = item.find("long").text
                lat = item.find("lat").text
                datetime = item.find("date").text
                jdt = JalaliDatetime.strptime(datetime, '%O/%P/%K\s%h:%r:%s.*')
                dt = jdt.todatetime()
                e = Event(id=int(id), reg1=reg1, dis1=int(dis1),
                          reg2=reg2, dis2=int(dis2), reg3=reg3,
                          dis3=int(dis3), mag=float(mag), dep=float(dep),
                          lng=float(lng[0:-2]), lat=float(lat[0:-2]),
                          datetime = dt)
                session.add(e)
                session.commit()
                session.close()
        except Exception as e:
            print(str(e))
        time.sleep(15*60)
