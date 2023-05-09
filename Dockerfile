FROM amazoncorretto:17

MAINTAINER celinavillarroel

COPY target/celinavillarroel-0.0.1-SNAPSHOT.jar celinavillarroel-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/celinavillarroel-0.0.1-SNAPSHOT.jar"]