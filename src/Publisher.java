import java.util.ArrayList;
import java.util.List;

public class Publisher {
    String name;
    String id;
    List<Message> messageList;
    public Publisher(String name,String id){
        this.name=name;
        this.id=id;
        messageList=new ArrayList<>();
    }
    public  void addMessage(Message message)
    {
        messageList.add(message);
    }
}
