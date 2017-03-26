# SmartSync
Don't live harder, live smarter. 

## To setup the project locally

### To Run SmartSync: 

1. Clone this repository using `git clone https://github.com/jackcmeyer/SmartSync.git`

2. Go to the project directory.

3. Go to the smartsync_communication_util directory and enter the following maven command:
  `mvn install:install-file -Dfile=smartsync-communication-util.jar -DgroupId=lib.communication -DartifactId=smartsync-communication-util -Dversion=2.0 -Dpackaging=jar`

4. In each subdirectory, run `mvn package`

5. In the main directory, run `./ss_serve.bash`. This will start each service. 

6. Make requests to `localhost:8000/*`

### To Setup for Development: 

1. Clone this repository using `git clone https://github.com/jackcmeyer/SmartSync.git`

2. With IntelliJ, open each subdirectory. DO NOT open the entire project folder.

* Disclaimer: This project was inteded to be developed in IntelliJ and we cannot guarantee that it works with any other IDE.


## API Information

| Port | URL                   | Name                     | Description                                                                                                                                           |   |
|------|-----------------------|--------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------|---|
| 8000 | http://localhost:8000 | smartsync-api-gateway    | Handles filtering of requests and security as an edge service. This is where all requests should be made. For example, `http://localhost:8080/users/` |   |
| 8001 | http://localhost:8001 | [smartsync-user-service](https://github.com/jackcmeyer/SmartSync/tree/master/smartsync-user-service)   | The user microservice. This service handles user information.                                                                                         |   |
| 8002 | http://localhost:8002 | [smartsync-household-service](https://github.com/jackcmeyer/SmartSync/tree/master/smartsync-household-service) | The household microservice. This serivce handles household information | |
| 8888 | http://localhost:8888 | smartsync-config-service | The config service. This service handles configurations.                                                                                              |   |
| 8761 | http://localhost:8761 | smartsync-eureka-service | The eureka service. Handles microservice discovery.    
