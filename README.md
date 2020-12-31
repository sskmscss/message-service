# kotlin-message-service

Install as maven project

## SUBSCRIBER ##
dapr run --components-path ./components --app-id subscriber --app-port 3000 --dapr-http-port 3005 -- \
  java -jar target/message-service-exec.jar  scm.service.Subscriber -p 3000
  
## PUBLISHER ##
dapr run --components-path ./components --app-id publisher --app-port 3010 --dapr-http-port 3006 -- \
  java -jar target/message-service-exec.jar  scm.service.Publisher -p 3010
  
## CHANGE STREAM ##  This will call one insertion for testing
dapr run --components-path ./components --app-id changestream --dapr-http-port 3007 -- \
  java -jar target/message-service-exec.jar  scm.service.ChangeStream
  
## Any other service than DAPR ## We can run without Dapr service
## PUBLISHER ##
java -jar target/message-service-exec.jar scm.service.Publisher -p 8085 -c kafka
  
## SUBSCRIBER ##
java -jar target/message-service-exec.jar scm.service.Subscriberr -p 8085 -c kafka

## FYI FOR AVAILABLE SERVICES ##
Mentioned -c or --class
** um **
** kafka **
** activemq **
** um **

## Need to install UM lib nClient.jar which is available in the src/lib/nClient.jar ##
mvn install:install-file -Dfile=nClient.jar -DgroupId=com.softwareag -DartifactId=nClient -Dversion=9.12 -Dpackaging=jar

