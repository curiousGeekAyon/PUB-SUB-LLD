import java.util.*;

public class Topic {
    String name;
    String id;
    String description;
    List<Message> messageList;
    Set<Publisher> publisherSet;//subscriber set
    private Topic(String name, String id, String description) {
        this.name = name;
        this.id = id;
        this.description = description;
        messageList=new ArrayList<>();
        publisherSet= Collections.synchronizedSet(new HashSet<>());
    }
    public synchronized void subscribe(Publisher publisher)
    {
            publisherSet.add(publisher);
            System.out.println(publisher.name+" subscribed to "+this.name);
    }
    public synchronized void unsubscribe(Publisher publisher)
    {
        publisherSet.remove(publisher);
        System.out.println(publisher.name+" unsubscribed to "+this.name);
    }
    public void addMessage(Message message)
        {
                messageList.add(message);
                notify(message);
        }
    public void notify(Message message)
    {
            String publisherName=message.publisher.name;
            String content=message.description;
            synchronized (publisherSet) {
                for (Publisher publisher : publisherSet) {
                    if(!publisher.name.equals(publisherName)) {
                        System.out.println(publisher.name+" a new message Posted on "+this.name);
                        System.out.println(publisherName + " has posted on " + this.name);
                        System.out.println("Content :" + content);
                    }
                }
            }
    }
    public static  class TopicBuilder{
        private  String name;
        private  String id;
        private  String description;

        public TopicBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public  TopicBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public  TopicBuilder setDescription(String description) {
            this.description= description;
            return this;
        }
        public Topic build()
        {
            return new Topic(name,id,description);
        }
    }
}
