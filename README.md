# marketplace-authentication-service

<!--## Documentation
### Login
- api: http://apigw-maxis.nagadpay.com/authentication-service/endpoint/oauth/login
- request type: POST
- request body: 
```
{
	"username":"shovon",
	"password":"password"
}
```
- response (For Valid Credential):
```
{
    "result": {
        "code": "201",
        "result": "SUCCESS",
        "message": "Login Successful",
        "userName": "Shovon Ghosh",
        "userPhone": "+8801841999776",
        "userImage": "data:image/jpeg;base64,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",
        "roleList": [
            {
                "id": "5ff6f59d2f4ee618beef158e",
                "name": "admin",
                "displayName": "Admin"
            }
        ],
        "authResponse": {
            "id_token": "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJTaG92b24gR2hvc2giLCJleHAiOjE2MTE0ODUyMzUsImlhdCI6MTYxMTQ4NDMzNSwiZW1haWwiOiJzaG92b24xMDEwQGdtYWlsLmNvbSJ9.WZfqFMNwDKlxRDq-yTXBIsDWFW85EIcrI0QEGhOZJRkVxU9_lZug1dK-BPbvXsis",
            "expires_in": 899,
            "refresh_token": "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJzaG92b24iLCJleHAiOjE2MTE0ODc5MzUsImlhdCI6MTYxMTQ4NDMzNX0.55OVG602278gBw5Kb2c1wX_qu0GBOFkI5qWVQXw-osFtN_OCJFRSSjF_Qpj2qX_L"
        }
    }
}
```

- response (For Invalid Credential):
```
{
    "result": {
        "code": "501",
        "result": "FAILURE",
        "message": "The given user Id or password is incorrect",
        "userName": null,
        "userPhone": null,
        "userImage": null,
        "roleList": null,
        "authResponse": {
            "id_token": "",
            "expires_in": 0,
            "refresh_token": ""
        }
    }
}
```
-->
