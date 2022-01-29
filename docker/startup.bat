@echo off

if "%1" == "all" (
    call docker stop db metadata-deployer metadata-app
    call docker rm -f db db metadata-deployer metadata-app

    pushd ..\
        call mvn clean install -DskipTests -T1C
    popd

    call docker-compose up -d --build
)

if "%1" == "images" (
    call docker stop db metadata-deployer metadata-app
    call docker rm -f db db metadata-deployer metadata-app
    call docker-compose up -d --build
)