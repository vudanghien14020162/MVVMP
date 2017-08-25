package demo.exercise.pclap.demomvvpp.data.model;

import com.google.gson.Gson;

/**
 * Created by PC Lap on 8/25/2017.
 */
public abstract class BaseModel implements Cloneable {

    public BaseModel clone() throws CloneNotSupportedException{

        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(this), this.getClass());
    }
}
