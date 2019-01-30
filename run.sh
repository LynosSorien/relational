mvn clean package -Dmaven.test.skip=true
mvn spring-boot:run -Dmaven.test.skip=true -Dspring-boot.run.profiles=$1
