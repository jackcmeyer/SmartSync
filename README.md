# SmartSync
Don't live harder, live smarter. 

## API Information

| Port | URL                   | Name                     | Description                                                                                                                                           |   |
|------|-----------------------|--------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------|---|
| 8000 | http://localhost:8000 | smartsync-api-gateway    | Handles filtering of requests and security as an edge service. This is where all requests should be made. For example, `http://localhost:8080/users/` |   |
| 8001 | http://localhost:8001 | [smartsync-user-service](https://github.com/jackcmeyer/SmartSync/tree/master/smartsync-user-service)   | The user microservice. This service handles user information.                                                                                         |   |
| 8888 | http://localhost:8888 | smartsync-config-service | The config service. This service handles configurations.                                                                                              |   |
| 8761 | http://localhost:8761 | smartsync-eureka-service | The eureka service. Handles microservice discovery.    
