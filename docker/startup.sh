#!/bin/bash

if [ "$1" == "all" ]; then
    docker stop db metadata-deployer metadata-app
    docker rm -f db db metadata-deployer metadata-app

    pushd ../
        mvn clean install -DskipTests -T1C || exit
    popd || exit

    docker-compose up -d --build
fi

if [ "$1" == "images" ]; then
    docker stop db metadata-deployer metadata-app
    docker rm -f db db metadata-deployer metadata-app
    docker-compose up -d --build
fi