import java.util.HashMap;

public class PubSubSystem {
    HashMap<String ,Topic>topicHashMap=new HashMap<>();
    HashMap<String ,Publisher>publisherMap=new HashMap<>();
    private static volatile PubSubSystem pubSubSystem;

    public synchronized  void addMessage(Publisher publisher,Topic topic,Message message)
    {
        addMessageInTopic(topic,message);
        addMessageInPublisher(publisher,message);
    }
    private PubSubSystem(){

    }
    public static PubSubSystem getPubSubSystem()
        {
            if(pubSubSystem==null)
            {
                synchronized (PubSubSystem.class) {
                    pubSubSystem = new PubSubSystem();
                }
            }
            return pubSubSystem;
        }
    private   void addMessageInTopic(Topic topic,Message message)
        {
            if(!topicHashMap.containsKey(topic.id))
            {
                String id=topic.id;
                topicHashMap.put(id,topic);
            }
            topic.addMessage(message);

        }
    private   void addMessageInPublisher(Publisher publisher,Message message)
    {
        if(!publisherMap.containsKey(publisher.id))
        {
            String id=publisher.id;
            publisherMap.put(id,publisher);
        }
        publisher.addMessage(message);

    }
    public void subscribe(Topic topic,Publisher publisher)
    {
        topic.subscribe(publisher);
    }
    public void unSubscribe(Topic topic,Publisher publisher)
        {
            topic.unsubscribe(publisher);
        }
    public void addPublisher(Publisher publisher)
    {
        publisherMap.put(publisher.id,publisher);
    }
    public void addTopic(Topic topic)
    {
        topicHashMap.put(topic.id,topic);
    }
}
