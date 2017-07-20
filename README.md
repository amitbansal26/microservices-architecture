**Proposed General Architecture **
==================================

The following can be used as general for Micro Services Architecture
Deployment.

<img src="./media/image1.jpg" width="624" height="531" />

**API Gateway**
---------------

API Gateway abstract’s out the calling details of the Micro services end
point and can be used as a single gateway for applying Security in Micro
Services World. Hence securing the system at entry.

### 

### 

### **Implementation of API Gateway**

Reference Implementation of API Gateway can be found at the following at
the flowing Git location. Placeholder of the Git repository is

<https://github.com/amitbansal26/microservices-architecture/tree/master/sample-api-gateway>

API Gateway in Spring Cloud can be implemented by using the Zuul Proxy
which can be selected from the starter template while creating a
project.

1.  application.yml needs to be incorporated with following changes

zuul:

routes:

service-name:

path: /path-to-service/\*\*

service-id: service-id {as defined in eureka server}

strip-prefix: **false**

**Service Discovery**
---------------------

Service Discovery is the basic building block of Micro Services
Architecture and this helps in decoupling micro services for scalability
without affecting the end user. This along with ribbon can be used for
load balancing the requests coming from client.

Spring Cloud Supports 2 ways in which the Service Discovery can be
implemented currently

1.  Eureka Server Approach

2.  Consul based Service Discovery

### **Implementation of Service Discovery (**Eureka Server Approach**)**

Reference implementation of Service Discovery can be found at the below
location

<https://github.com/amitbansal26/microservices-architecture/tree/master/eureka-server>

The Eureka Server can be enabled by adding the annotation
@EnableEurekaServer. The following needs to be added to the client
application which needs to connect with Eureka Server.

Client application.yml entry which needs to be added

eureka:

instance:

hostname: ${vcap.application.uris\[0\]:localhost}

non-secure-port-enabled: **false**

secure-port-enabled: **true**

status-page-url:
'https://${eureka.instance.hostName}:${server.port}/info'

health-check-url-path:
'https://${eureka.instance.hostName}:${server.port}/health'

home-page-url: 'https://${eureka.instance.hostName}:${server.port}/'

client:

service-url:

defaultZone: ${vcap.services.{eureka-service–name-given–in
cloudfoundry}.credentials.url:http://localhost:8761/eureka/}

### **Implementation of Service Discovery (Consul Based Approach)**

1.  Consul Installation

    Consul must be installed on the server. Consul is available as
    binary package by HashiCorp.

2.  A Consul Agent client must be available to all Spring Cloud
    Consul applications. Consul provides Service Discovery services via
    an HTTP API and DNS. Spring Cloud Consul leverages the HTTP API for
    service registration and discovery. This does not prevent non-Spring
    Cloud applications from leveraging the DNS interface. Consul Agent’s
    servers are run in a cluster that communicates via a gossip
    protocol and uses the Raft consensus protocol.

3.  Registering with Consul

    e.g.

    ***@SpringBootApplication***

    ***@EnableDiscoveryClient***

    ***@RestController***

    ***public class Application {…………….***

    ***application.yml***

    spring:

    cloud:

    consul:

    host: localhost // This would be the consul Agent Host .

    port: 8500

    Remember to setup consul server and agent and provide the required
    ip for the same using start\_join property. For more details refer
    to consul documentation.

**Config Server (Implementation)**
----------------------------------

Reference Implementation of config server can be found at the below
location

<https://github.com/amitbansal26/microservices-architecture/tree/master/config-server>

spring:

cloud:

config:

server:

git:

uri: https://amitb26j@gitlab.com/amitb26j/config-repo.git

password: '{cipher}AQCXWaJpjv0IhssVYgDmlZNaRNMGddvvex2j0a4L……….
username:
'{cipher}AQAtE3xoBUdxFG/uzOO1qRkiSoezoJE4t/Nmbh5qhBmaR7t5kM3C7/

Add the git repo details with usernames and password encrypted.

Also add the java keystore in class path through which these encrypted
entries can be decoded

Add the below in the client applications

| spring: |
|---------|
|         |
|         |
|         |
|         |
|         |

Another thing which can be done is dynamic updation of properties via
springCloudBus

For this we need a JMS provider and in this case rabbitmq can be used. A
standalone rabbitmq server is recommended. Spring cloud monitor
dependency is required in pom.xml

A webhook needs to be added in github so that the process can be
triggered for dynamic updates. Monitor endpoint needs to be provided in
the repo for the push event to successfully send notification to config
server which in turn would communicate with client applications via
springCloudBus Topic created in RabbitMQ Console and send the updated
properties in the config repo this would in turn refresh the same in
client application

<img src="./media/image2.png" width="624" height="106" />

This is how you can add webhooks to the gitlab/github repository.

**OAUTH2 Server (Implementation and Example)**
----------------------------------------------

OAuth defines four roles:

**Resource owner:** An entity capable of granting access to a protected
resource. When the resource owner is a person, it is referred to as an
end-user.

**resource server:** The server hosting the protected resources, capable
of accepting and responding to protected resource requests using access
tokens.

**client:** An application making protected resource requests on behalf
of the resource owner and with its authorization. The term "client" does
not imply any particular implementation characteristics (e.g., whether
the application executes on a server, a desktop, or other devices).

**authorizationserver:** The server issuing access tokens to the client
after successfully authenticating the resource owner and obtaining
authorization.

A reference implementation of Oauth2 Server can be found at the below
URL.

<https://github.com/amitbansal26/microservices-architecture/tree/master/oauth2-server>

Generate JWT token store by using the following command.

*keytool* -*genkeypair* -alias *jwtref* -*keyalg* RSA -*dname*
"CN=*jwt*, L=*Mumbai*, S=*Mumbai*, C=IN" -*keypass* {keypasswor}
-*keystore* jwtref.jks -*storepass* {storePass}

curl -X POST \\

http://localhost:9999/oauth/token \\

-H 'authorization: Basic d2ViX2FwcDpzZWNyZXQ=' \\

-H 'content-type: application/x-www-form-urlencoded' \\

-d
'username=amit&password=amit&grant\_type=password&client\_id=web\_app'

Hit this request and get the token. Use this token to access your
service.

ELK Stack

An elk stack implementation is there on the following repository

<https://github.com/amitbansal26/microservices-architecture/tree/master/logging-stack-microservices>

System comprises of ElasticSearch, LogStash, Kibana

**Elasticsearch** is a search engine based on Lucene. It provides a
distributed, multitenant-capable full-text search engine with an HTTP
web interface and schema-free JSON documents. Elasticsearch is developed
in Java and is released as open source under the terms of the Apache
License.

**LogStash**:

**Logstash** is part of the [Elastic
Stack](https://www.elastic.co/products) along with Elasticsearch and
Kibana. Logstash is an open source, server-side data processing pipeline
that ingests data from a multitude of sources simultaneously, transforms
it, and then sends it to your favorite "stash." Logstash has over 200
plugins, and you can write your own very easily as well.

**Kibana** is an open source data visualization plugin for
Elasticsearch. It provides visualization capabilities on top of the
content indexed on an Elasticsearch cluster. Users can create bar, line
and scatter plots, or pie charts and maps on top of large volumes of
data.

**LogSearch** :

PCF Operators can use the PCF Log Search tile to analyze logs from
different tiles and components running in the same PCF environment. It
is not intended to be a general purpose log analysis system, or to
centralize logs from multiple PCF Foundations.

After deploying you need to provide an appender in the logback.xml file
so that your logs are sent via Logstash to elasticsearch.

<img src="./media/image3.png" width="624" height="336" />

Add this file into the micro services you want to add logging to after
starting the elk stack . The current implementation has provided the
implementation as docker deployment .
