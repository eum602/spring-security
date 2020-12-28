## Postgres configuration

1. Start postgres entities
```shell
$ cd postgres-entities
$ docker-compose up
```
2. Optionally you can enter the docker:
```shell
$ docker exec -it your_docker_id sh
$ psql -h localhost -p 5432 -U jpatutorial -d springbootjpa
$ \c
$ \dt
$ SELECT * FROM kayak;
```