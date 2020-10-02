# 1. 프로젝트 목적
기존에 개발했던 shop프로젝트를 API로 형식으로 수정

# 2. 목표
- [x] RESTAPI 작성
- [x] DTO 작성
- [x] JPA 양방향 API 해결
- [x] JPA 성능 해결

<br>

# 3. 문서 목차
| 번호 | 요약 | 링크 |
|-----|------------|-----|
| 1 | 페이징 |  [링크](./documentation/주문조회페이징.md) |


<br>

# 4. RESTAPI 설계
| 기능 | HTTP 메소드 | URL |
|-----|------------|-----|
| 모든회원 조회 | GET |  /api/v2/members |
| 회원 등록 | POST |  /api/v2/members |
| 회원이름 수정 | PUT |  /api/v2/members/{id} |