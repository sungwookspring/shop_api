FROM openjdk:11

# wait-for-it.sh's refernce: https://github.com/vishnubob/wait-for-it/blob/master/wait-for-it.sh
COPY wait-for-it.sh wait-for-it.sh

ARG JAR_FILE=build/libs/ShoppingMall-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# Entrypoint idea's refernce: https://www.hellojava.com/a/51263.html
ENTRYPOINT ["./wait-for-it.sh", "db:3306", "--", "java", "-jar", "app.jar"]