# Docker 빌드
## 스프링 빌드
```
```

## 도커 빌드
```
docker build --build-arg JAR_FILE=build/libs/*.jar -t spring_shop .
```

# Docker 실행
```
docker run -d -p 8080:8080 spring_shop
```