package framgia.com.myeditor.data.model;

import com.google.gson.Gson;

public abstract class BaseModel implements Cloneable {

    public BaseModel clone() {
        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(this), this.getClass());
    }
}
