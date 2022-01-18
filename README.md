# LifeManager
IES Project Group 5

### HTML/JavaScript Code

Location: /Prototype

### Web Server Code

Location: /WebServer

### Data Stream Generation Code

Location: /DataStream

sudo docker-compose up -d --build --remove-orphans --force-recreate

192.168.160.220
_________________________________________________________________________________-

docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.9-management

docker run --name mysql5 -e MYSQL_ROOT_PASSWORD=secret1 -e MYSQL_DATABASE=demo -e MYSQL_USER=demo -e MYSQL_PASSWORD=secret2 -p 33060:3306 -d mysql/mysql-server:5.7

java -jar target/WebServer-0.0.1-SNAPSHOT.jar

data_generator.py
