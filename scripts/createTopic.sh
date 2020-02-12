cd ..
cd ..
cd ..
cd Downloads/kafka_2.12-2.4.0
./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 50 --topic myTopic2