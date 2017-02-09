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
}

echo "Starting SmartSync services..."

# Run maven package on services
echo "Running Maven packages..."
cd config-service
mvn package
cd ../eureka-service
mvn package
cd ../hystrix-dashboard
mvn package
cd ../zipkin-service
mvn package
cd ..

echo "java -jar config-service/target/config-service-0.0.1-SNAPSHOT.jar"
java -jar config-service/target/config-service-0.0.1-SNAPSHOT.jar &
PID1=$!
echo $PID1
sleep 10

echo "java -jar eureka-service/target/eureka-service-0.0.1-SNAPSHOT.jar"
java -jar eureka-service/target/eureka-service-0.0.1-SNAPSHOT.jar &
PID2=$!
echo $PID2
sleep 10

echo "java -jar hystrix-dashboard/target/hystrix-dashboard-0.0.1-SNAPSHOT.jar"
java -jar hystrix-dashboard/target/hystrix-dashboard-0.0.1-SNAPSHOT.jar &
PID3=$!
echo $PID3

echo "java -jar zipkin-service/target/zipkin-service-0.0.1-SNAPSHOT.jar"
java -jar zipkin-service/target/zipkin-service-0.0.1-SNAPSHOT.jar &
PID4=$!
echo $PID4

wait
