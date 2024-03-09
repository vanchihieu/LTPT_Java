import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "John");
        map.put("age", 25);
        map.put("isStudent", true);

        JSONObject jsonObject = JsonUtils.createJSONObjectFromMap(map);
        System.out.println(jsonObject.toString());
    }
}