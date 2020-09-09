# 프로젝트 목적
기존에 개발했던 shop프로젝트를 API로 형식으로 수정

# 목표
- [ ] RESTAPI 작성
- [ ] DTO 작성
- [ ] JPA 양방향 API 해결
- [ ] JPA 성능 해결

# RESTAPI 설계
| 기능 | HTTP 메소드 | URL |
|-----|------------|-----|
| 모든회원 조회 | GET |  /api/v2/members |
| 회원 등록 | POST |  /api/v2/members |
| 회원이름 수정 | PUT |  /api/v2/members/{id} |