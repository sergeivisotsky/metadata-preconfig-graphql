@echo off

if "%1" == "all" (
    call docker stop db metadata-deployer metadata-app
    call docker rm -f db metadata-deployer metadata-app

    pushd ..\
        call mvn clean install -DskipTests -T1C
    popd

    call docker-compose up -d --build
)

if "%1" == "app" (
    call docker stop metadata-app
    call docker rm -f metadata-app

    pushd ..\metadata-app
        call mvn clean install -DskipTests -T1C
    popd

    call docker-compose up -d --no-deps --build metadata-app
)

if "%1" == "deployer" (
    call docker stop metadata-deployer
    call docker rm -f metadata-deployer

    pushd ..\metadata-deployer
        call mvn clean install -DskipTests -T1C
    popd

    call docker-compose up -d --no-deps --build metadata-deployer
)

if "%1" == "images" (
    call docker stop db metadata-deployer metadata-app
    call docker rm -f db metadata-deployer metadata-app
    call docker-compose up -d --build
)