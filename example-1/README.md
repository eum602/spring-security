# Basic Spring Security

## Use

* Start the application
```shell script
mvn spring-boot:run
```

* Extract the password from the console:

```shell script
Using generated security password: c0142163-67e8-46bf-bb63-ff49746c8f99
```

* Setting environment variables:

```shell script
export USER=user
export PASSWORD=c0142163-67e8-46bf-bb63-ff49746c8f99 # change this value accordingly, DO NOT COPY PASTE
```

* Extract message from hello controller with:

```shell script
curl -u $USER:$PASSWORD http://localhost:8080/hello # Should obtain "hello"
```

* Obtaining an error because of unauthenticated user:

```shell script
curl http://localhost:8080/hello # should obtain ==>
 
{
  "timestamp": "2020-12-27T19:08:26.487+00:00",
  "status": 401,
  "error": "Unauthorized",
  "message": "",
  "path": "/hello"
}
```

* Authenticating using a header:

```shell script
encoded=`echo -n $USER:$PASSWORD | base64`
``` 

```shell script
curl -H "Authorization: Basic $encoded" localhost:8080/hello
```

