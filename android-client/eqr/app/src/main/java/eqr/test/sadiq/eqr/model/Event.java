package eqr.test.sadiq.eqr.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Event {
    @SerializedName("datetime")
    private String datetime;
    @SerializedName("dep")
    private Integer dep;
    @SerializedName("dis1")
    private Integer dis1;
    @SerializedName("dis2")
    private Integer dis2;
    @SerializedName("dis3")
    private Integer dis3;
    @SerializedName("id")
    private Integer id;
    @SerializedName("lat")
    private Double lat;
    @SerializedName("lng")
    private Double lng;
    @SerializedName("mag")
    private Double mag;
    @SerializedName("reg1")
    private String reg1;
    @SerializedName("reg2")
    private String reg2;
    @SerializedName("reg3")
    private String reg3;
    static private int selected_position;
    static private long selected_id;

    public Event(String datetime, Integer dep, Integer dis1, Integer dis2, Integer dis3, Integer id,
                 Double lat, Double lng, double mag, String reg1, String reg2, String reg3) {
        this.datetime = datetime;
        this.dep = dep;
        this.dis1 = dis1;
        this.dis2 = dis2;
        this.dis3 = dis3;
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.mag = mag;
        this.reg1 = reg1;
        this.reg2 = reg2;
        this.reg3 = reg3;
        this.selected_position = -1;
        this.selected_id = -1;
    }

    public static long getSelected_id() {
        return selected_id;
    }

    public static void setSelected_id(long selected_id) {
        Event.selected_id = selected_id;
    }

    public String getDateTime() {
        return datetime;
    }

    public Integer getDep() {
        return dep;
    }

    public Integer getDis1() {
        return dis1;
    }

    public Integer getDis2() {
        return dis2;
    }

    public Integer getDis3() {
        return dis3;
    }

    public Integer getId() {
        return id;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }

    public Double getMag() {
        return mag;
    }

    public String getReg1() {
        return reg1;
    }

    public String getReg2() {
        return reg2;
    }

    public String getReg3() {
        return reg3;
    }


    public int getSelected_position() {
        return selected_position;
    }

    public void setSelected_position(int selected_position) {
        this.selected_position = selected_position;
    }
}
