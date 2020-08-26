# Docker 빌드
## MariDB실행
```
; 환경변수
; MYSQL_ROOT_PASSWORD: root 비밀번호
; MYSQL_DATABASE: 데이터베이스 이름
; MYSQL_USER: 생성한 데이터베이스를 관리할 계정
; MYSQL_PASSWORD: 생성한 계정의 비밀번호

sudo docker run --rm -d -p 3306:3306 --name mariadb -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=shop -e MYSQL_USER=abc -e MYSQL_PASSWORD=password mariadb:10.5.5
```

## 스프링 빌드
```
;MariaDB 실행되어 있지 않으면 빌드 실패
./gradlew clean
./gradlew build
```

## 도커 빌드
```
docker build -t spring_shop .
```

# Docker 실행
```
;MariaDB 실행되어 있지 않으면 빌드 실패
docker run -d -p 8080:8080 spring_shop
```

# 발생했던 오류
* [1] MariaDB <-> jdbc 버전 호환성 해결: jdbc 2.4.2버전으로 수정(출처:https://okky.kr/article/543428?note=1608578)
* [2] IDE에서 실행시 자동으로 수정하는 대소문자 오타 해결: 빌드버전을 실행하면서 오타 발견과 수정