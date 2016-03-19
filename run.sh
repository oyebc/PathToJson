#!/bin/bash

# Check to see if this process is already running
if [ -s ./running.pid ]; then
        RUNNING_PID=`cat running.pid`

    if [ `ps ax | awk '{print $1}' | egrep -c "^${RUNNING_PID}$"` -eq 1 ]; then
        echo ":::::Service already running. PID of running process is $RUNNING_PID"
        exit 1
    fi
fi

BASEDIR=$(dirname "$0")

#app dependencies
LIB="$BASEDIR/lib"

#app jar
APP="$BASEDIR/path-json-1.0.jar"

MAIN_CLASS="ai.maven.labs.TreeGenerator"


#java -cp "$LIB/*" $MAIN_CLASS

for jar in $LIB/*.jar; do
    echo "Adding jar:  [ $jar ] to classpath"
    CLASSPATH=$CLASSPATH:$jar
done

#add app jar to class path
echo "Adding jar:  [ $APP ] to classpath"
CLASSPATH=$CLASSPATH:$APP

#CLASSPATH=`echo $CLASSPATH | cut -c2-`

#no worries nohup is pointless here, heck you don't need the pid either, but leave the baggage here if ever needed
#nohup java -cp $CLASSPATH $MAIN_CLASS & echo $! > running.pid

#Start the main class
java -cp $CLASSPATH $MAIN_CLASS $1 $2