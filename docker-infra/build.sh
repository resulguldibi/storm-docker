source ../.env
DOCKER_DEFAULT_PLATFORM=$DOCKER_DEFAULT_PLATFORM
export DOCKER_DEFAULT_PLATFORM

docker build --platform=$DOCKER_DEFAULT_PLATFORM -t resulguldibi/nimbus -f nimbus/Dockerfile .
docker build --platform=$DOCKER_DEFAULT_PLATFORM -t resulguldibi/supervisor -f supervisor/Dockerfile .
docker build --platform=$DOCKER_DEFAULT_PLATFORM -t resulguldibi/ui -f ui/Dockerfile .
docker build --platform=$DOCKER_DEFAULT_PLATFORM -t resulguldibi/maven -f maven/Dockerfile .
docker build --platform=$DOCKER_DEFAULT_PLATFORM -t resulguldibi/kafka -f kafka/Dockerfile .
docker build --platform=$DOCKER_DEFAULT_PLATFORM -t resulguldibi/zookeeper -f zookeeper/Dockerfile .