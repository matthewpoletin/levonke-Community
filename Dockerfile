FROM openjdk:8
ADD build/libs/levonke-1.0.0-SNAPSHOT.jar levonke-Community.jar
EXPOSE 8442
ENTRYPOINT ["java", "-jar", "levonke-Community.jar"]
