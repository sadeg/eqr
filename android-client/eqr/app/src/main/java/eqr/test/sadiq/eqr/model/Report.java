package eqr.test.sadiq.eqr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Report {

    @SerializedName("e_id")
    @Expose
    private Integer eId;
    @SerializedName("sex")
    @Expose
    private Integer sex;
    @SerializedName("lng")
    @Expose
    private Integer lng;
    @SerializedName("lat")
    @Expose
    private Integer lat;
    @SerializedName("building_type")
    @Expose
    private Integer buildingType;
    @SerializedName("building_floors")
    @Expose
    private Integer buildingFloors;
    @SerializedName("floor")
    @Expose
    private Integer floor;
    @SerializedName("building_condition")
    @Expose
    private Integer buildingCondition;
    @SerializedName("felt")
    @Expose
    private Integer felt;
    @SerializedName("location")
    @Expose
    private Integer location;
    @SerializedName("sleep")
    @Expose
    private Integer sleep;
    @SerializedName("people_felt")
    @Expose
    private Integer peopleFelt;
    @SerializedName("vibration")
    @Expose
    private Integer vibration;
    @SerializedName("emotional_reaction")
    @Expose
    private Integer emotionalReaction;
    @SerializedName("people_emotional_reaction")
    @Expose
    private Integer peopleEmotionalReaction;
    @SerializedName("reaction")
    @Expose
    private Integer reaction;
    @SerializedName("reaction_comment")
    @Expose
    private String reactionComment;
    @SerializedName("walking")
    @Expose
    private Integer walking;
    @SerializedName("little_movement")
    @Expose
    private Integer littleMovement;
    @SerializedName("sounds")
    @Expose
    private Integer sounds;
    @SerializedName("objects_movement")
    @Expose
    private Integer objectsMovement;
    @SerializedName("frames_movement")
    @Expose
    private Integer framesMovement;
    @SerializedName("bulky_objects_movement")
    @Expose
    private Integer bulkyObjectsMovement;
    @SerializedName("heavy_objects_movement")
    @Expose
    private Integer heavyObjectsMovement;
    @SerializedName("walls")
    @Expose
    private Integer walls;
    @SerializedName("damages")
    @Expose
    private Integer damages;

    public Integer getEId() {
        return eId;
    }

    public void setEId(Integer eId) {
        this.eId = eId;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getLng() {
        return lng;
    }

    public void setLng(Integer lng) {
        this.lng = lng;
    }

    public Integer getLat() {
        return lat;
    }

    public void setLat(Integer lat) {
        this.lat = lat;
    }

    public Integer getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(Integer buildingType) {
        this.buildingType = buildingType;
    }

    public Integer getBuildingFloors() {
        return buildingFloors;
    }

    public void setBuildingFloors(Integer buildingFloors) {
        this.buildingFloors = buildingFloors;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getBuildingCondition() {
        return buildingCondition;
    }

    public void setBuildingCondition(Integer buildingCondition) {
        this.buildingCondition = buildingCondition;
    }

    public Integer getFelt() {
        return felt;
    }

    public void setFelt(Integer felt) {
        this.felt = felt;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public Integer getSleep() {
        return sleep;
    }

    public void setSleep(Integer sleep) {
        this.sleep = sleep;
    }

    public Integer getPeopleFelt() {
        return peopleFelt;
    }

    public void setPeopleFelt(Integer peopleFelt) {
        this.peopleFelt = peopleFelt;
    }

    public Integer getVibration() {
        return vibration;
    }

    public void setVibration(Integer vibration) {
        this.vibration = vibration;
    }

    public Integer getEmotionalReaction() {
        return emotionalReaction;
    }

    public void setEmotionalReaction(Integer emotionalReaction) {
        this.emotionalReaction = emotionalReaction;
    }

    public Integer getPeopleEmotionalReaction() {
        return peopleEmotionalReaction;
    }

    public void setPeopleEmotionalReaction(Integer peopleEmotionalReaction) {
        this.peopleEmotionalReaction = peopleEmotionalReaction;
    }

    public Integer getReaction() {
        return reaction;
    }

    public void setReaction(Integer reaction) {
        this.reaction = reaction;
    }

    public String getReactionComment() {
        return reactionComment;
    }

    public void setReactionComment(String reactionComment) {
        this.reactionComment = reactionComment;
    }

    public Integer getWalking() {
        return walking;
    }

    public void setWalking(Integer walking) {
        this.walking = walking;
    }

    public Integer getLittleMovement() {
        return littleMovement;
    }

    public void setLittleMovement(Integer littleMovement) {
        this.littleMovement = littleMovement;
    }

    public Integer getSounds() {
        return sounds;
    }

    public void setSounds(Integer sounds) {
        this.sounds = sounds;
    }

    public Integer getObjectsMovement() {
        return objectsMovement;
    }

    public void setObjectsMovement(Integer objectsMovement) {
        this.objectsMovement = objectsMovement;
    }

    public Integer getFramesMovement() {
        return framesMovement;
    }

    public void setFramesMovement(Integer framesMovement) {
        this.framesMovement = framesMovement;
    }

    public Integer getBulkyObjectsMovement() {
        return bulkyObjectsMovement;
    }

    public void setBulkyObjectsMovement(Integer bulkyObjectsMovement) {
        this.bulkyObjectsMovement = bulkyObjectsMovement;
    }

    public Integer getHeavyObjectsMovement() {
        return heavyObjectsMovement;
    }

    public void setHeavyObjectsMovement(Integer heavyObjectsMovement) {
        this.heavyObjectsMovement = heavyObjectsMovement;
    }

    public Integer getWalls() {
        return walls;
    }

    public void setWalls(Integer walls) {
        this.walls = walls;
    }

    public Integer getDamages() {
        return damages;
    }

    public void setDamages(Integer damages) {
        this.damages = damages;
    }

}