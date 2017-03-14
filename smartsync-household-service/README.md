# SmartSync Household Service

## Description
The Smart Sync Household Service. Responsible for handling household information. 

## API Documentation

### GET `/households/`

#### Description
Gets all of the households that are currently registered as part of the application. 

#### Request Parameters
None

#### Example Request
`localhost:8000/households/`

#### Example Response Body
```json
[
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
  },
  {
    "householdId": 2,
    "householdName": "Nathan's House",
    "ownerId": "jack222",
    "firstAddressLine": "111 Main Street",
    "secondAddressLine": null,
    "city": "West Des Moines",
    "state": "IA",
    "zipCode": 50061,
    "lastUpdated": 1489099576000,
    "created": 1489099576000
  }
]
```

### POST `/users/`
#### Description
Adds a new household to the application

#### Request Body
| Name              | Type   | Description                                  |
|-------------------|--------|----------------------------------------------|
| householdName     | String | The nickname for the household.              |
| ownerId           | Number | The id of the owner of the household.        |
| firstAddressLine  | String | The first address line. Ex: 111 Main Street  |
| secondAddressLine | String | The second address line. Ex: Apt 111         |
| city              | String | The city where the household is located.     |
| state             | String | The state where the household is located.    |
| zipCode           | Number | The zip code where the household is located. |

#### Example Request
##### URL 
`localhost:8000/households/`

##### Request Body
```json
{
	"householdName": "Nathan's House",
	"ownerId": "jack222",
	"firstAddressLine": "111 Main Street",
	"city": "West Des Moines", 
	"state": "IA",
	"zipCode": "50061"
}
```

### GET `/households/{id}`

#### Description
Gets information about the household with the specific id.

#### Request Parameters
| Name              | Type   | Description                                  |
|-------------------|--------|----------------------------------------------|
| id                | Number | The id of the househod                       |

#### Example Request
`localhost:8000/households/1`

#### Example Response Body
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

### DELETE `/households/{id}
#### Description
Deletes the household with the specific id. Will return the deleted household. 

#### Request Parameters
| Name              | Type   | Description                                  |
|-------------------|--------|----------------------------------------------|
| id                | Number | The id of the household                       |

#### Example Request
`localhost:8000/households/1`

#### Example Response Body
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

### GET '/households/users/{userId}'

#### Description
Gets the hold which the user is a part of.

#### Request Parameters
| Name              | Type   | Description                                  |
|-------------------|--------|----------------------------------------------|
| userId                | Number | The user id to find the household for.                       |

#### Example Request
`localhost:8000/households/users/1`

#### Example Response Body
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

### POST /households/users/

#### Description
Adds a user to a household.

#### Request Parameters
| Name              | Type   | Description                                  |
|-------------------|--------|----------------------------------------------|
| householdId                | Number | The household id to add the user to.         |
| userId            | Number | The user id to add to the household          |

#### Example Request 
##### Request URL
`localhost:8000/households/users/`

##### Request Body
```json
{
  "userId": 3,
  "householdId": 3
}
```

#### Example Response Body
```json
{
  "id": 6,
  "userId": 3,
  "householdId": 3
}
```
### GET /households/{id}/users

#### Description
Gets the users apart of the household

#### Request Parameters
| Name              | Type   | Description                                  |
|-------------------|--------|----------------------------------------------|
| id                | Number | The household id to get the users for.         |

#### Example Request
`localhost:8000/households/{id}/users`

#### Example Response Body
```json
[
  {
    "userId": 3,
    "googleId": null,
    "fullName": "Jack Meyer",
    "givenName": "Jack",
    "familyName": "Meyer",
    "imageURL": "http://www.fakeurl.com",
    "email": "jackcmeyer@gmail.com",
    "role": 1,
    "created": "1489290709000",
    "lastUpdated": "1489290709000"
  }
]
```