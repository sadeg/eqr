import json
from datetime import datetime
from flask import Flask
from flask import jsonify, request
from sqlalchemy import Column, String, Integer, Float, desc, DateTime
from sqlalchemy.orm import sessionmaker
from sqlalchemy.ext.declarative  import declarative_base
from sqlalchemy import create_engine
import sys
import ast


# Create app
app = Flask(__name__)

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

class Report(Base):
    __tablename__ = "reports"
    id = Column(Integer, primary_key=True)
    record_datetime = Column(DateTime, nullable=False)
    e_id = Column(Integer, nullable=False)
    sex = Column(Integer, nullable=True)
    lng = Column(Integer, nullable=False)
    lat = Column(Integer, nullable=False)
    building_type = Column(Integer, nullable=True)
    building_floors = Column(String, nullable=True)
    floor = Column(String, nullable=True)
    building_condition = Column(Integer, nullable=True)
    felt = Column(Integer, nullable=False)
    location = Column(Integer, nullable=True)
    sleep = Column(Integer, nullable=True)
    people_felt = Column(Integer, nullable=True)
    vibration = Column(Integer, nullable=True)
    emotional_reaction = Column(Integer, nullable=True)
    people_emotional_reaction = Column(Integer, nullable=True)
    reaction = Column(Integer, nullable=True)
    reaction_comment = Column(String, nullable=True)
    walking = Column(Integer, nullable=True)
    little_movement = Column(Integer, nullable=True)
    sounds = Column(Integer, nullable=True)
    objects_movement = Column(Integer, nullable=True)
    frames_movement = Column(Integer, nullable=True)
    bulky_objects_movement = Column(Integer, nullable=True)
    heavy_objects_movement = Column(Integer, nullable=True)
    walls = Column(Integer, nullable=True)
    damages = Column(Integer, nullable=True)

engine = create_engine('sqlite:///data.db')
Base.metadata.create_all(engine)
Base.metadata.bind = engine
DBsession = sessionmaker(bind=engine)


@app.route("/getlastevents", methods=['GET'])
def get_last_events():
    r = []
    session = DBsession()
    es = session.execute('SELECT * FROM (SELECT * FROM events ORDER BY id DESC LIMIT 10) ORDER BY id ASC;')
    for e in es:
        r.append(dict(e))
    session.close()
    return jsonify(r)

@app.route("/report", methods=['post'])
def report():
    session = DBsession()
    print(request.data.decode("utf-8") , file=sys.stderr)
    d = ast.literal_eval(request.data.decode("utf-8"))
    e_id = d.get("e_id", None)
    sex = d.get("sex", None)
    lng = d.get("lng", None)
    lat = d.get("lat", None)
    building_type = d.get("building_type", None)
    building_floors = d.get("building_floors", None)
    floor = d.get("floor", None)
    building_condition = d.get("building_condition", None)
    felt = d.get("felt", None)
    location = d.get("location", None)
    sleep = d.get("sleep", None)
    people_felt = d.get("people_felt", None)
    vibration = d.get("vibration", None)
    emotional_reaction = d.get("emotional_reaction", None)
    people_emotional_reaction = d.get("people_emotional_reaction", None)
    reaction = d.get("reaction", None)
    reaction_comment = d.get("reaction_comment", None)
    walking = d.get("walking", None)
    little_movement = d.get("little_movement", None)
    sounds = d.get("sounds", None)
    objects_movement = d.get("objects_movement", None)
    frames_movement =d.get("frames_movement", None)
    bulky_objects_movement = d.get("bulky_objects_movement", None)
    heavy_objects_movement = d.get("heavy_objects_movement", None)
    walls = d.get("walls", None)
    damages = d.get("damages", None)

    #creating report object
    r = Report(record_datetime=datetime.now(), e_id=e_id,
               sex=sex, lng=lng, lat=lat,
               building_type=building_type, building_floors=building_floors, floor=floor,
               building_condition=building_condition, felt=felt, location=location,
               sleep=sleep, people_felt=people_felt, vibration=vibration,
               emotional_reaction=emotional_reaction, people_emotional_reaction=people_emotional_reaction, reaction=reaction,
               reaction_comment=reaction_comment, walking=walking, little_movement=little_movement,
               sounds=sounds, objects_movement=objects_movement, frames_movement=frames_movement,
               bulky_objects_movement=bulky_objects_movement, heavy_objects_movement=heavy_objects_movement, walls=walls,
               damages=damages,
               )
    print(r)
    session.add(r)
    session.commit()
    session.close()
    return "ok"


if __name__ == "__main__":
    # app.run(host="localhost", port=5000)
    app.run(host="0.0.0.0", port=5000)
