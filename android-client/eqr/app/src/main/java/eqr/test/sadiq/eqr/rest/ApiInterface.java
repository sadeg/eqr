package eqr.test.sadiq.eqr.rest;

import java.util.ArrayList;

import eqr.test.sadiq.eqr.model.Event;
import eqr.test.sadiq.eqr.model.Report;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("getlastevents")
    Call<ArrayList<Event>> getLastEvents();

    @POST("report")
    @FormUrlEncoded
    Call<String> report(@Field("e_id") Integer eId,
                          @Field("sex") Integer sex,
                          @Field("lng") Integer lng,
                          @Field("lat") Integer lat,
                          @Field("building_type") Integer buildingType,
                          @Field("building_floors") Integer buildingFloors,
                          @Field("floor") Integer floor,
                          @Field("building_condition") Integer buildingCondition,
                          @Field("felt") Integer felt,
                          @Field("location") Integer location,
                          @Field("sleep") Integer sleep,
                          @Field("people_felt") Integer peopleFelt,
                          @Field("vibration") Integer vibration,
                          @Field("emotional_reaction") Integer emotionalReaction,
                          @Field("people_emotional_reaction") Integer peopleEmotionalReaction,
                          @Field("reaction") Integer reaction,
                          @Field("reaction_comment") String reactionComment,
                          @Field("walking") Integer walking,
                          @Field("little_movement") Integer littleMovement,
                          @Field("sounds") Integer sounds,
                          @Field("objects_movement") Integer objectsMovement,
                          @Field("frames_movement") Integer framesMovement,
                          @Field("bulky_objects_movement") Integer bulkyObjectsMovement,
                          @Field("heavy_objects_movement") Integer heavyObjectsMovement,
                          @Field("walls") Integer walls,
                          @Field("damages") Integer damages);
}
