package tasks;

import org.json.JSONObject;

/**
 * Created by Setas on 9/8/2014.
 */
public class JSONDataAnalizer {

    private String result;

    public JSONDataAnalizer(String result){
        this.result=result;
    }

    public String analyze(){
        return result;
    }
}
