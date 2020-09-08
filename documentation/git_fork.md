# 커맨드로 git fork
1. 기존 remote 삭제
    ```
    git remote rm origin
    ```
2. remote 추가
    ```
   git remote add [git주소]
   ```
3. add&push&commit
* push 할때 -u 인자를 꼭 설정
    ```
   git add *
   git commit -m "커밋 메세지"
   git push -u origin master
   ```