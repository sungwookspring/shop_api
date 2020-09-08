* 수정은 put method사용
* 보통 수정은 수정만 처리하고 리턴하지 않으나 결과를 확인하기 위해 리턴

```java
; controller

@PutMapping("/api/v2/members/{id}")
public UpdateMemberResponse updateMemberV2(@PathVariable("id") Long id, @RequestBody @Valid UpdateMemberRequset request){
    memberService.update(id, request.getName());
    Member updateMember = memberService.findMemberById(id);

    return new UpdateMemberResponse(updateMember.getId(), updateMember.getName());
}
```