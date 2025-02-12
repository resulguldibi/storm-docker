# storm-docker

--RUN STORM

--run nimbus, supervisor, ui, kafka, zookeeper
docker-compose up -d nimbus supervisor ui

--prepare storm jar (maven prepares topology jar file and exports it to /docker-infra/maven/output folder)
docker-compose up -d maven

--deploy storm topology to nimbus

cd docker-infra/maven/output
docker cp -a storm-topologies-1.0.0.jar nimbus:/
docker exec -it -u root nimbus bash
storm jar storm-topologies-1.0.0.jar com.resulguldibi.storm.samples.KafkaSpoutTopologyMainNamedTopics


--KAFKA COMMANDS

--kafka perf test
https://gist.github.com/ueokande/b96eadd798fff852551b80962862bfb3
./kafka-producer-perf-test.sh --topic kafka-spout-test --num-records 500 --record-size 100 --throughput -1 --producer-props acks=1 bootstrap.servers=localhost:9092 buffer.memory=67108864 batch.size=64000

--alter partition count
/kafka-topics.sh --bootstrap-server localhost:9092 --topic kafka-spout-test --alter --partitions 2

--display topic details
./kafka-topics.sh --bootstrap-server localhost:9092 --topic kafka-spout-test --describe