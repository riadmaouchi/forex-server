#! /bin/bash

# run server
echo " -- launching FOREX SERVER"
cd /tmp/FOREX-SERVER/lib
runningJar=$(ls forex-server.jar)

echo " -- launching FOREX SERVER : java -jar ${runningJar}"
java -jar ${runningJar}