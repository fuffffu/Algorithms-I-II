import java.util.HashMap;

public class WebTracker {
    private HashMap<String, HashMap<String, Integer>> userVisits; // 外层符号表

    public WebTracker() {
        userVisits = new HashMap<>();
    }

    // 用户访问网站
    public void visit(String userID, String siteID) {
        if (!userVisits.containsKey(userID)) {
            userVisits.put(userID, new HashMap<>()); // 创建内层符号表
        }

        HashMap<String, Integer> siteVisits = userVisits.get(userID);

        if (!siteVisits.containsKey(siteID)) {
            siteVisits.put(siteID, 0); // 初始化该网站的访问次数为 0
        }

        siteVisits.put(siteID, siteVisits.get(siteID) + 1); // 增加访问次数
    }

    // 查询用户访问某个网站的次数
    public int query(String userID, String siteID) {
        if (userVisits.containsKey(userID) && userVisits.get(userID).containsKey(siteID)) {
            return userVisits.get(userID).get(siteID);
        }
        return 0; // 没有访问过该网站
    }

    public static void main(String[] args) {
        WebTracker tracker = new WebTracker();

        tracker.visit("user1", "siteA");
        tracker.visit("user1", "siteA");
        tracker.visit("user1", "siteB");
        tracker.visit("user2", "siteA");

        System.out.println("user1 visited siteA: " + tracker.query("user1", "siteA")); // 2
        System.out.println("user1 visited siteB: " + tracker.query("user1", "siteB")); // 1
        System.out.println("user2 visited siteA: " + tracker.query("user2", "siteA")); // 1
        System.out.println("user1 visited siteC: " + tracker.query("user1", "siteC")); // 0
    }
}
