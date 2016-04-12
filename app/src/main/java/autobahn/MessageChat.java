package autobahn;

/**
 * Created by inftel08 on 8/4/16.
 */
public class MessageChat {

    private String action;
    private int id;
    private String name;
    private String type;
    private String status;
    private String description;

    public MessageChat() {
        this.id = 0;
        this.action = "add";
        this.status = "Off";
    }

    public MessageChat(String action, int id,  String name, String type, String status, String description) {
        this.id = 0;
        this.action = "add";
        this.name = name;
        this.type = type;
        this.status = "Off";
        this.description = description;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
//        return "{" +
//                "action='" + action + '\'' +
//                ", id=" + id +
//                ", name='" + name + '\'' +
//                ", type='" + type + '\'' +
//                ", status='" + status + '\'' +
//                ", description='" + description + '\'' +
//                '}';

       return "{\"action\":\"" + action +
        "\",\"id\":"+id+
        ", \"name\":\"" + name
                + "\",\"type\":\""+type+"\",\"status\":\""+
                status+"\",\"description\":\""+
                description + "\"}";
    }
}
