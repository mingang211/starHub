####
# 📚 StarDev 항해18기 커뮤니티
#### 📌 [프로젝트 설명]
- 2024.01.12.~2024.01.18.(7일) | 미니 프로젝트 과제(이지수, 이신지, 민경현)
- 목표: Spring Boot를 사용해 CRUD에 기반한 다양한 기능을 가진 커뮤니티 만들기
- 진행: code convention에 따라 개별 기능을 구현한 후, 코드 리뷰를 통해 develop하는 방식으로 진행함
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

### 커밋 메시지 컨벤션
##
#### 1. 커밋 메세지는 제목+타입/본문(body)/꼬리말(footer)로 구성

(필수) 제목+type : 어떤 의도로 커밋했는지

(선택) 본문(body): 긴 설명이 필요한 경우에만 작성 무엇을 왜했는지

(선택) 꼬리말(footer): issue tracker ID를 명시하고 싶은 경우에 작성

#### 2. type 종류

Feat : 새로운 기능을 추가하는 경우

Fix : 버그를 고친경우

Docs : 문서를 수정한 경우

Style : 코드 포맷 변경, 세미콜론 누락, 코드 수정이 없는경우

Refactor : 코드 리펙토링

Test : 테스트 코드. 리펙토링 테스트 코드를 추가했을 때

Chore : 빌드 업무 수정, 패키지 매니저 수정

Rename : 파일명(or 폴더명) 을 수정한 경우

Remove : 코드(파일) 의 삭제가 있을 때.

Add : 코드나 테스트, 예제, 문서등의 추가 생성이 있는경우

Move : 코드의 이동이 있는 경우

Updated : 계정이나 버전 업데이트가 있을 때

Comment : 필요한 주석 추가 및 변경

#### 3. 제목은 한글로 작성하고 "고침","추가","변경"등의 명령조로 시작

ex) Feat: "추가 get data api 함수"

#### 4. 본문은 선택사항이며 부연설명이 필요하거나 커밋의 이유를 설명할 경우 무엇을 변경했는지 왜 변경했는지를 설명하고 제목과 구분되기 위해 공백 한 줄을 띄워서 작성

#### 5. 꼬리말도 선택사항이며 issue tracker id를 작성할 때 사용하고 형식은 "유형: #이슈번호"형식으로 작성-issue tracker 유형

Fixes : 이슈 수정중 (아직 해결되지 않은 경우)

Resolves : 이슈를 해결했을 때 사용

Ref : 참고할 이슈가 있을 때 사용

Related to : 해당 커밋에 관련된 이슈번호 (아직 해결되지 않은 경우)

#### 6. 커밋메세지 예시

Feat: "추가 get data api 함수" // 타입: 제목로그인 API 개발 

본문Resolves: #123   // 꼬리말 => 이슈 123을 해결했으며,

Ref: #456 //이슈 456 를 참고해야하며,

Related to: #48, #45 //현재 커밋에서 아직 이슈 48 과 45 가 해결

##
## 깃 플로우
##
1. 프로젝트의 메인 브랜치는 'main'
   
2. main branch를 직접 수정하지 않기 위해 develop branch생성함.
   
3. 각 팀원은 develop branch에서 클론 진행 후 레포지토리에서 개발을 진행.
   
4. '1기능 1커밋' 원칙을 지키며 개발을 진행.
   
5. 모든 기능 구현 후, develop branch에 push.
    
6. 팀원들과 코드 리뷰를 진행한 후 develop branch에 pull request를 생성.
    
7. develop branch가 안정화되면 main branch로 pull request를 생성하고 충돌을 확인한 후 main branch로 머지 진행.
#### 📌 [API 명세서 / Use Case Diagram / Entity Relationship Diagram]
[⭐️ 항해99 18기 Dev Star's API 명세서](https://experienced-equinox-093.notion.site/48ae768eca244b159c657654faa3eeec?v=747bfe44cac64f48800e0f76292787f8)
//[Figma](https://www.figma.com/file/T6cCr9BLrl6mL2wv9bWKkM/starboard?type=design&node-id=0-1&mode=design&t=VAQx5xeomcA4WSlM-0) // [Entity Relationship Diagram](https://playible.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F8303614e-6951-4071-a826-037e1e2a81b6%2F93dd3e7e-5a6f-4776-abab-14e2a61fd629%2F%25EC%258A%25A4%25ED%2581%25AC%25EB%25A6%25B0%25EC%2583%25B7_2024-01-17_234252.png?table=block&id=8b3562b8-c0da-4c72-afca-b36d416af02d&spaceId=8303614e-6951-4071-a826-037e1e2a81b6&width=950&userId=&cache=v2)
##
### 📌 [과제 회고]
#### **민경현**
저는 이번 프로젝트에서 Starboard부분을 맡았습니다. 게시판 생성,조회,수정,삭제는 빠르게 한 것 같은데
댓글 부분에서 하루, null값이 생기는 경우 처리하는 방법 하루 댓글에만 이틀이라는 시간을 써서 머리가 아팠는데
@JsonInclude(JsonInclude.Include.NON_NULL)을 사용해서 null값을 숨기고 Query문을 이용해서 댓글에 내가 원하는 정보만 보낼 수 있게 되어서 너무나 좋았습니다.
이번에 깃 관리를 맡아서 깃 흐름을 알 수 있어서 좋았고 http구현하는 방법은알겠는데 https는 익숙하지 않아서 다음에 시도해봐야 할 것 같습니다.  
#### 
