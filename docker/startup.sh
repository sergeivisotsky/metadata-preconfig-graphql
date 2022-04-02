#!/bin/bash

if [ "$1" == "all" ]; then
    docker stop db metadata-deployer metadata-app
    docker rm -f db metadata-deployer metadata-app

    pushd ../
        mvn clean install -DskipTests -T1C || exit
    popd || exit

    docker-compose up -d --build
fi

if [ "$1" == "app" ]; then
    docker stop metadata-app
    docker rm -f metadata-app

    # shellcheck disable=SC2164
    pushd ../metadata-app
        mvn clean install -DskipTests -T1C || exit
    popd || exit

    docker-compose up -d --no-deps --build metadata-app
fi

if [ "$1" == "deployer" ]; then
    docker stop metadata-deployer
    docker rm -f metadata-deployer

    # shellcheck disable=SC2164
    pushd ../metadata-deployer
        mvn clean install -DskipTests -T1C || exit
    popd || exit

    docker-compose up -d --no-deps --build metadata-deployer
fi

if [ "$1" == "images" ]; then
    docker stop db metadata-deployer metadata-app
    docker rm -f db metadata-deployer metadata-app
    docker-compose up -d --build
fi