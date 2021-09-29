#!/bin/sh

IMAGE_NAME="assignment1"

if grep -q ${IMAGE_NAME} "$(docker ps -a)"
then
  echo "Docker container doesn't exist. Building."
else
  echo "Seems like you already have a container running. Shutting down and removing."
  docker stop $(docker ps -a -q) && docker rm $(docker ps -a -q)
    docker rmi ${IMAGE_NAME}
fi

echo "====================="
echo "Building project."
echo "====================="

mvn clean install

echo "====================="
echo "DONE BUILDING PROJECT"
echo "====================="

echo "====================="
echo "BUILDING DOCKER IMAGE"
echo "====================="

docker build . -t ${IMAGE_NAME}

echo "====================="
echo "FINISHED BUILDING IMAGE. RUNNING CONTAINER"
echo "====================="

docker run --name ${IMAGE_NAME} -d -p 8080:8080 ${IMAGE_NAME}

if grep -q ${IMAGE_NAME} "$(docker ps -a)"
then
  echo "There seems to have been a problem running the container. Ask Alex lol."
else
  echo "Container running."
fi
