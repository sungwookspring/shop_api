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

# 발생했던 오류
* [1] MariaDB <-> jdbc 버전 호환성 해결: jdbc 2.4.2버전으로 수정(출처:https://okky.kr/article/543428?note=1608578)
* [2] IDE에서 실행시 자동으로 수정하는 대소문자 오타 해결: 빌드버전을 실행하면서 오타 발견과 수정