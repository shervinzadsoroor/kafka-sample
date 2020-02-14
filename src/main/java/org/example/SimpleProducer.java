package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Scanner;

public class SimpleProducer {

    public static void main(String[] args) throws Exception {

        // Check arguments length value
        if (args.length == 0) {
            System.out.println("Enter topic name");
            return;
        }

        //Assign topicName to string variable
        String topicName = args[0];

        // create instance for properties to access producer configs
        Properties props = new Properties();

        //Assign localhost id
        props.put("bootstrap.servers", "localhost:9092");

        //Set acknowledgements for producer requests.
        props.put("acks", "all");

        //If the request fails, the producer can automatically retry,
        props.put("retries", 0);

        //Specify buffer size in config
        props.put("batch.size", 16384);

        //Reduce the no of requests less than 0
        props.put("linger.ms", 1);

        //The buffer.memory controls the total amount of memory available to the producer for buffering.
        props.put("buffer.memory", 33554432);

        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer
                <>(props);

//        for (int i = 0; i < 10; i++) {
//            producer.send(new ProducerRecord<>(topicName, Integer.toString(i), Integer.toString(i)));
//        }
        Scanner scanner  = new Scanner(System.in);
        while (true) {
            System.out.println("type:");
            String message = scanner.nextLine();
            if (message.equals("end")){
                producer.close();
                break;
            }
            producer.send(new ProducerRecord<>(topicName,message));
        }
      //  System.out.println("Message sent successfully");
      //  producer.close();
    }
}
