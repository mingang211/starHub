####
# 📚 StarDev 항해18기 커뮤니티
### 📌 [프로젝트 설명]
-제작기간 : 2024.01.12. ~ 2024.01.18. (7 일)

-목표: Spring Boot 를 이용해 CRUD 및 댓글과 좋아요 기능을 지닌 커뮤니티 만들기 

-진행: code convention 에 따라 개별 기능을 구현한 후, 코드 리뷰를 통해 develop 하는 방식으로 진행

##
### 📌 [팀원 소개]
#### -3인 1조 팀 프로젝트

이지수 : 회원가입 + 로그인 + 개인정보 및 마이 페이지 / AWS EC2 HTTPS 배포 (RDS, ACM, Route 53, ALB)

민경현 : 게시판1(StarBoard) CRUD + 댓글

이신지 : 게시판2(StarShare) CRUD + 좋아요

##
### 📌 [기능 정의]
### <회원가입 & 로그인 & 개인정보 및 마이페이지>

#### 1. 회원가입

- Client 에서 name, email, password, major 입력

- e-mail(올바른 이메일 형식) 및 password(8~15자리, [a-z, A-Z, 0-9] + 특수문자) 유효성
  
#### 검사

- e-mail 중복 검사
  
- 패스워드 암호화 이후 회원 등록
  
#### 2. 로그인
   
- Client 에서 e-mail, password 입력
  
- Spring Security 의 Filter 를 거쳐 인증
  
- JWT 토큰 발급 후 발급한 토큰을 Cookie 에 담아 클라이언트에 전달
  
#### 3. 개인정보 및 마이 페이지
   
- 개인정보 조회 : user 정보 중 name, email, major 조회
  
- 개인정보 수정 : user 정보 중 name, major 수정 가능
  
- 마이페이지: user 가 작성한 모든 게시물(StarBoard + StarShare)을 보여줌
  
#### 공통: Spring Security 를 사용하여 토큰 검사 및 인증

        Spring Security Context Holder 에서 로그인 객체 전달받아 회원 정보 조회 및 확인 
        
        Http Status 코드와 함께 Dto 반환
        
        반환 시 null 값 반환하지 않도록 빈 객체 생성하여 반환
        
### <게시판1 StarBoard>
         
#### 1. 게시글 작성
   
- Client 에서 date, title, contents, imageUrl 입력
  
- 입력된 내용을 저장한 후 저장된 게시글을 Client 에 반환
  
#### 2. 게시글 상세 조회
   
- 게시글의 상세한 내용을 게시글에 등록된 모든 댓글과 함께 Client 에 반환
  
#### 3. 전체 게시글 조회
   
- 댓글을 제외한 게시글의 간략한 내용을 Client 에 반환
  
#### 4. 게시글 수정
   
- 게시글 작성자가 맞는지 확인 후 내용 수정 진행
  
- 수정된 게시글을 모든 댓글과 함께 Client 에 반환
  
#### 5. 게시글 삭제
   
- 게시글 존재 여부와 작성자가 맞는지 확인 후 게시글 삭제 진행
  
- Client 로 상태 코드와 삭제 성공 메시지 반환
  
#### 6. 게시글에 댓글 달기
   
- 선택한 게시글과 user 존재 여부 확인 후 댓글 등록
  
#### 7. 게시글 댓글 수정
   
- 선택한 게시글과 댓글, user 존재 여부 확인 후 댓글 수정
  
- 수정된 댓글을 dto 에 담아 반환
  
#### 8. 게시글 댓글 삭제
   
- 선택한 게시글과 댓글, user 존재 여부 확인 후 댓글 삭제
  
- Client 로 상태 코드와 삭제 성공 메시지 반환
  
### <게시판2 StarShare>

#### 1. 게시글 작성
   
- Client 에서 title, 입력
  
- 입력된 내용을 저장한 후 저장된 게시글을 Client 에 반환
  
#### 2. 게시글 상세 조회
   
- 게시글의 상세한 내용을 게시글에 등록된 좋아요 개수와 함께 Client 에 반환
  
#### 3. 게시글 전체 목록 조회
   
- 전체 게시글을 조회
  
#### 4. 게시글 수정
   
- 게시글 작성자가 맞는지 확인 후 내용 수정 진행
  
- 수정된 게시글을 등록된 좋아요 개수와 함께 Client 에 반환
  
#### 5. 게시글 삭제
   
- 게시글 존재 여부와 작성자가 맞는지 확인 후 게시글 삭제 진행
  
- Client 로 상태 코드와 삭제 성공 메시지 반환
  
#### 6. 선택한 게시글에 좋아요 추가
   
- user 와 게시글 확인
  
- 이미 등록된 좋아요일 경우 좋아요 취소
  
- 좋아요 개수와 게시글, 유저 id 가 포함된 dto 를 Client 에 반환
  
#### 7. 좋아요 삭제
   
- user 와 게시글, 좋아요 유무 확인 후, 등록된 좋아요가 있으면 좋아요 취소

##
### 📌 [Code Convention 정립]
#### 코드 컨벤션
1. 생성자명과 클래스명은 파스칼 케이스에 따라 작성함
   
2. 변수명과 메서드명은 카멜 케이스에 따라 작성하며, 메서드명은 동사로 시작함

3. 생성 메서드명의 경우, create로 시작함(e.g. enrollBoard() -> createBoard())

4. 조회 메서드명의 경우, 반환값에 따라 find 또는 read로 시작함

5. Boolean의 경우, 동사 + flag 구성으로 통일함(e.g. isNum, hasNum)

6. DI(Dependency Injection)의 경우, 생성자 주입 방식으로 통일함

7. 주석의 경우, 한 줄 ‘//‘ 또는 여러 줄 ‘/** */’로 통일함

8. magic number 사용을 지양함(e.g. str.equals(’보드’) -> str.equals(’보드 할당 변수명’))
##
#### 📌 [API 명세서 / Use Case Diagram / Entity Relationship Diagram]
[⭐️ 항해99 18기 Dev Star's API 명세서](https://experienced-equinox-093.notion.site/48ae768eca244b159c657654faa3eeec?v=747bfe44cac64f48800e0f76292787f8)
//[Figma](https://www.figma.com/file/T6cCr9BLrl6mL2wv9bWKkM/starboard?type=design&node-id=0-1&mode=design&t=VAQx5xeomcA4WSlM-0) // [Entity Relationship Diagram](https://playible.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F8303614e-6951-4071-a826-037e1e2a81b6%2F93dd3e7e-5a6f-4776-abab-14e2a61fd629%2F%25EC%258A%25A4%25ED%2581%25AC%25EB%25A6%25B0%25EC%2583%25B7_2024-01-17_234252.png?table=block&id=8b3562b8-c0da-4c72-afca-b36d416af02d&spaceId=8303614e-6951-4071-a826-037e1e2a81b6&width=950&userId=&cache=v2)
##
### 📌 [과제 회고]
#### **민경현** 👨🏻‍💻
저는 이번 프로젝트에서 Starboard부분을 맡았습니다. 게시판 생성,조회,수정,삭제는 빠르게 한 것 같은데
댓글 부분에서 하루, null값이 생기는 경우 처리하는 방법 하루 댓글에만 이틀이라는 시간을 써서 머리가 아팠는데
@JsonInclude(JsonInclude.Include.NON_NULL)을 사용해서 null값을 숨기고 Query문을 이용해서 댓글에 내가 원하는 정보만 보낼 수 있게 되어서 너무나 좋았습니다.
이번에 깃 관리를 맡아서 깃 흐름을 알 수 있어서 좋았고 http구현하는 방법은알겠는데 https는 익숙하지 않아서 다음에 시도해봐야 할 것 같습니다.  

#### **이신지** 👩🏻‍💻 
프론트와의 협업이 처음이라 모든 것이 신기했습니다. 버튼 하나하나 마법처럼 느껴졌어요. 함께 
프로젝트 해 주신 프론트분들 정말 감사합니다. 이번 협업을 통해 API를 잘 작성하고 지키는 것이 협업에
매우 중요하다는 것을 체감했고 어떻게 하면 원활한 의사소통을 할 수 있는지 배우는 기회가 된 것 
같습니다.
개선하고 싶은 점은 컨트롤러에서 적절한 에러를 반환하지 못해 에러가 터질 때마다 클라이언트쪽 
에러인지, 서버쪽 에러인지 구분하기가 힘들었습니다. 이 부분을 자세히 했다면 개발 시간을 좀 더 
단축시키고 에러를 더 빨리 잡아낼 수 있었을 텐데 아쉬움이 남습니다. 더불어 추가 기능으로 댓글 구현이
있었는데, 마음 같아서는 대댓글까지 도전해 보고 싶었는데 시간이 닿지 않아 아쉽습니다.  그 외에도 
일관적인 코드 작성과 연관관계 매핑, 배포에 대한 부분 등 프로젝트 하며 마주쳤던 여러 고비들을 이후 
스스로 채워나가려 합니다. 그리고 백엔드 팀원분들의 도움도 정말정말 많이 받았습니다. 너무 감사하고 
수고 많으셨습니다! : )
