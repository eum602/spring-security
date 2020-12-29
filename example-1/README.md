# Basic Spring Security

## Previous configuration

### Postgres configuration

1. Start postgres entities
```shell
$ cd postgres-entities
$ docker-compose up
```
2. Optionally you can enter the docker to check "users" table which will be created once spring is started:
```shell
$ docker exec -it your_docker_id sh
$ psql -h localhost -p 5432 -U jpatutorial -d springbootjpa
$ \c
$ \dt
$ SELECT * FROM users;
```

## How to Use

* Start the application
```shell script
mvn spring-boot:run
```

* Extract the password from the console:

```shell script
Using configured security password ==> password
```

* Setting environment variables:

```shell script
export USER=eum602
export PASSWORD=password # change this value accordingly, DO NOT COPY PASTE
```

* Extract message from hello controller with http ==> **comment** the following lines in application.propereties:

```shell script
#server.ssl.key-store-type=PKCS12
#server.ssl.key-store=certificate.p12
#server.ssl.key-store-password=12345678
```

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

## Configuring secure connection with https.

```shell script
cd certificate-generator
 ./1_create_private_key_and_pkcs12_public_certificate.sh # generates private key/pkcs12 public certificate => enter a password 12345678
./2_create_selfsigned_certificate.sh # generates selfsigned certificate
```

Update the password in src/main/resources/application.properties and uncomment the following:
```shell script
server.ssl.key-store-type=PKCS12
server.ssl.key-store=certificate.p12
server.ssl.key-store-password=12345678 #Update this password, do not copy-paste
```

## Accessing the hello controller

```shell script
curl -k -H "Authorization: Basic $encoded" https://localhost:8080/hello
```

```shell script
curl -k -u $USER:$PASSWORD https://localhost:8080/hello 
```

## Accessing the database information:

```shell script
curl -k -u $USER:$PASSWORD https://localhost:8080/users #only an example, this enpoint should NOT be exposed
```
