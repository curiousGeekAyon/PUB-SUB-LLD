import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        // Get singleton instance of PubSubSystem
        PubSubSystem pubSubSystem = PubSubSystem.getPubSubSystem();

        // Create a Topic
        String description = "Detailed topic description on LLD basics.";
        String name = "LLD-1";
        String id = Objects.hash(name) + "";
        Topic topic = new Topic.TopicBuilder()
                .setDescription(description)
                .setName(name)
                .setId(id)
                .build();
        pubSubSystem.addTopic(topic);

        // Create Publisher 1
        String publisherName1 = "Ayon Sinha";
        String id1 = "1";
        Publisher publisher1 = new Publisher(publisherName1, id1);
        pubSubSystem.addPublisher(publisher1);

        // Create Publisher 2
        String publisherName2 = "Adhir Sinha";
        String id2 = "2";
        Publisher publisher2 = new Publisher(publisherName2, id2);
        pubSubSystem.addPublisher(publisher2);

        // Publishers subscribe to the topic
        pubSubSystem.subscribe(topic, publisher1);
        pubSubSystem.subscribe(topic, publisher2);

        // Send a message from Publisher 1
        String message1Description = "Message 1: Introduction to Low-Level Design.";
        Message message1 = new Message(message1Description, publisher1);
        pubSubSystem.addMessage(publisher1, topic, message1);

        // Send another message from Publisher 2
        String message2Description = "Message 2: Advanced concepts in Low-Level Design.";
        Message message2 = new Message(message2Description, publisher2);
        pubSubSystem.addMessage(publisher2, topic, message2);

        // Send a third message from Publisher 1
        String message3Description = "Message 3: Summary of Low-Level Design principles.";
        Message message3 = new Message(message3Description, publisher1);
        pubSubSystem.addMessage(publisher1, topic, message3);

        // Test unsubscribe and new message to verify no notification to unsubscribed
        pubSubSystem.unSubscribe(topic, publisher2);
        String message4Description = "Message 4: New design patterns update.";
        Message message4 = new Message(message4Description, publisher1);
        pubSubSystem.addMessage(publisher1, topic, message4);
    }
}
