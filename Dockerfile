# This is a comment line! The docker build will FAIL if you put any code above the FROM command.
# The FROM command MUST be the first thing to get executed!
#FROM openjdk:latest
# Troubleshooting:
#     Ensure that docker desktop is running on your PC, eg. in the Taskbar
#     Build your project before running this dockerfile.
#     Delete existing docker containers.
#COPY target/classes /tmp
#COPY ./target/seMethods-0.1.0.3-jar-with-dependencies.jar /tmp
#WORKDIR /tmp
#CMD ["echo", "-----------------------------------------> Running TestJavaProject_JBT..."]
#ENTRYPOINT ["java", "com.napier.sem.App"]
#ENTRYPOINT ["java", "-jar", "TestJavaProject_JBT-0.1.0.1-jar-with-dependencies.jar"]
#CMD ["echo", "-----------------------------------------> ...Finished Dockerfile commands."]


FROM openjdk:latest
# Ensure both this and the ENTRYPOINT target the right .jar file in your ./target/ folder.
COPY ./target/semCoursework.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "semCoursework.jar", "sql:3306"]