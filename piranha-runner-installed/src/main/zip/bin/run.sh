#!/bin/bash

cd ..

if [ -z ${JAVA_HOME} ]; then
    echo Using default java
    JAVA_BIN=java 
else
    echo Using JAVA_HOME: ${JAVA_HOME}
    JAVA_BIN=${JAVA_HOME}/bin/java
fi

${JAVA_BIN} -jar lib/piranha-runner-installed.jar &

PID=$!
echo $PID >> tmp/piranha.pid
wait $PID
