# SmartSync User Service

## Description
The Smart Sync User Service. Responsbile for handling service information

## API Documentation

### GET `users/`

#### Description
Gets all of the users currently registered as part of the application. 

#### Request Parameters
None

#### Example Request
`localhost:8000/users/`

#### Example Response Body
```json
[
  {
    "userId": "jack111",
    "fullName": "Jack Meyer",
    "givenName": "Jack",
    "familyName": "Meyer",
    "imageURL": "https://www.fakeurl.com",
    "email": "jackcmeyer1@gmail.com",
    "role": 0,
    "created": 1488752840000,
    "lastUpdated": 1488752840000
  },
  {
    "userId": "jack222",
    "fullName": "Jack Meyer",
    "givenName": "Jack",
    "familyName": "Meyer",
    "imageURL": "https://www.fakeurl.com",
    "email": "jackcmeyer2@gmail.com",
    "role": 0,
    "created": 1488749764000,
    "lastUpdated": 1488749764000
  }
]
```

### POST `users/`
#### Description
Adds a new service to the application

#### Request Body
| Name       | Type    | Description                                                                                             |
|------------|---------|---------------------------------------------------------------------------------------------------------| 
| userId     | String  | The service's google account id, also used for a unique identifier for internal use.                       |
| fullName   | String  | The service's full name.                                                                                   |
| givenName  | String  | The service's first name.                                                                                  |
| familyName | String  | The service's last name.                                                                                   |
| imageURL   | String  | A url to the service's profile picture on google accounts, also used for account picture for internal use. |
| email      | String  | The service's google email, also will be service's email for internal use.                                    |
| role       | Integer | An integer which will reperesent the service's role. 0 for normal service. 1 for super service.                  |

#### Example Request 
##### URL
`localhost:8000/users`

##### Request Body
```json
 {
    "userId": "jack111",
    "fullName": "Jack Meyer",
    "givenName": "Jack",
    "familyName": "Meyer",
    "imageURL": "https://www.fakeurl.com",
    "email": "jackcmeyer@gmail.com",
    "role": 0
  }
```

### GET `users/{id}`
#### Description
Gets information about the service with the specific id.

#### Request Parameters
| Name       | Type    | Description                                                                                             |
|------------|---------|---------------------------------------------------------------------------------------------------------| 
| id     | String  | The service's google account id, also used for a unique identifier for internal use.     

#### Example Request
`localhost:8000/users/jack111`

#### Example Response Body
```json
 {
    "userId": "jack111",
    "fullName": "Jack Meyer",
    "givenName": "Jack",
    "familyName": "Meyer",
    "imageURL": "https://www.fakeurl.com",
    "email": "jackcmeyer1@gmail.com",
    "role": 0,
    "created": 1488752840000,
    "lastUpdated": 1488752840000
  }
  ```
  
### DELETE `users/{id}`
#### Description
Deletes the service with the specific id. Will return the deleted service. 

#### Request Parameters
| Name       | Type    | Description                                                                                             |
|------------|---------|---------------------------------------------------------------------------------------------------------| 
| id     | String  | The service's google account id, also used for a unique identifier for internal use.     

#### Example Request
`localhost:8000/users/jack111`

#### Example Response Body
```json
 {
    "userId": "jack111",
    "fullName": "Jack Meyer",
    "givenName": "Jack",
    "familyName": "Meyer",
    "imageURL": "https://www.fakeurl.com",
    "email": "jackcmeyer1@gmail.com",
    "role": 0,
    "created": 1488752840000,
    "lastUpdated": 1488752840000
  }
  ```
  
### GET `users/{id}/households`
### Description
Gets the household the service is a part of

#### Request Parameters
| Name       | Type    | Description                                                                                             |
|------------|---------|---------------------------------------------------------------------------------------------------------| 
| id     | String  | The service's google account id, also used for a unique identifier for internal use.     

#### Example Request
`localhost:8000/users/1/household`

### Example Resopnse
```json
{
  "householdId": 1,
  "householdName": "Jack's House",
  "ownerId": "jack111",
  "firstAddressLine": "111 Lynn Ave",
  "secondAddressLine": null,
  "city": "Ames",
  "state": "IA",
  "zipCode": 50014,
  "lastUpdated": 1489096349000,
  "created": 1489096349000
}
```
