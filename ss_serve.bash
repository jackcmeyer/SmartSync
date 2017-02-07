#!/bin/bash


trap handler SIGINT

handler()
{
    echo "kill -9 $PID1"
    kill -9 $PID1
    echo "kill -9 $PID2"
    kill -9 $PID2
    echo "kill -9 $PID3"
    kill -9 $PID3
    echo "kill -9 $PID4"
    kill -9 $PID4
    echo "kill -9 $PID5"
    kill -9 $PID5
}

echo "Starting SmartSync services..."

echo "java -jar service1.jar param param param"
sleep 60 &
PID1=$!
echo $PID1

echo "java -jar service2.jar param param param"
sleep 60 &
PID2=$!
echo $PID2

echo "java -jar service3.jar param param param"
sleep 60 &
PID3=$!
echo $PID3

echo "java -jar service4.jar param param param"
sleep 60 &
PID4=$!
echo $PID4

echo "java -jar service5.jar param param param"
sleep 60 &
PID5=$!
echo $PID5

wait
