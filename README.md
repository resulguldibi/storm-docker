# storm-docker
mvn archetype:generate -DgroupId=com.resulguldibi.storm.samples -DartifactId=storm-topologies -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

--kafka perf test
https://gist.github.com/ueokande/b96eadd798fff852551b80962862bfb3
./kafka-producer-perf-test.sh --topic kafka-spout-test --num-records 500 --record-size 100 --throughput -1 --producer-props acks=1 bootstrap.servers=localhost:9092 buffer.memory=67108864 batch.size=64000

--topic partiton sayısı güncelleme
/kafka-topics.sh --bootstrap-server localhost:9092 --topic kafka-spout-test --alter --partitions 2

--topic detay görüntüleme
./kafka-topics.sh --bootstrap-server localhost:9092 --topic kafka-spout-test --describe

--örnek storm topolojini hazırlama
cd storm-topologies
mvn clean
mvn packace
