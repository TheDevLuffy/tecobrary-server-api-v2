# 테코브러리(TECOBRARY) REST API

> 팀프로젝트가 완성되지 못할 상황을 대비하여 DDD를 학습하며 개인적으로 연습하기 위한 레포지토리입니다. 이 브랜치에서는 배포를 위한 Jenkins 학습과 자동 배포 Setting, 도커와 쿠버네티스를 이용한 무중단 배포까지 연습하는 것을 목표로 합니다.

## 개발환경
* Spring Framework 5.1.8.RELEASE
* Tomcat 9.0.22
* Gradle

## 브랜치 관리 전략

> 기본 브랜치 관리는 [여기](https://nvie.com/posts/a-successful-git-branching-model/) 의 문서를 기반으로 진행합니다.

1. 레포지토리에 한 단위의 작업 후 유지되는 브랜치는 master branch 와 develop branch 입니다.
2. 레포지토리의 develop 브랜치에 직접적으로 commit 하는 것은 최초 README.md 작성시에만 가능합니다.
3. 모든 기능 구현 및 이슈 dealing 은 develop branch 에서 생성한 새로운 branch 에서 진행합니다.
4. 로컬 레포지토리에서 생성하는 모든 브랜치명은 다음의 규칙을 따릅니다.
    - 문서 추가 및 수정 : ``docs/[issuNumber]/[issueName]``
    - 기능 구현 : ``feature/[issueNumber]/[issueName]``
    - 버그 수정 : ``fix/[issueNumber]/[issueName]``
5. 로컬의 브랜치를 리모트 브랜치로 push 후 마스터 브랜치로 Pull Request 시에는 4번의 명명 규칙을 title 로 지정하여 Squash 커밋이 4번의 명명 규칙에 따라 생성되도록 합니다.

## 커밋 규칙

> 기본 커밋 규칙은 [Udacity Git Commit Message Style Guide](https://udacity.github.io/git-styleguide/) 를 따릅니다.

1. 커밋 내용은 전부 한글로 작성합니다.
2. 함수 단위로 커밋을 작성합니다. (마스터 브랜치에는 Squash Commit 만 남게 합니다.)

## 기본 규칙
1. TDD 로 개발을 진행합니다.
2. 테스트 커버리지는 90% 를 목표로 합니다.
3. git commit 내역과 문서를 보고 미래의 작업자가 별다른 인수 인계 없이 코드 수정할 수 있도록 최대한 상세히 작성합니다.
4. 모든 코드 작성 주기는 1주일로 합니다.
5. 기본 규칙 4번을 바탕으로 프로젝트를 생성합니다.
6. 코드 작성 주기 1주일은 월, 화, 수요일은 새로운 코드를 작성하고 목, 금요일은 해당 주기에 작성된 코드를 리팩토링하며 테스트를 보완합니다.
7. README.md 이외의 문서들은 모두 Wiki 에서 작성합니다.

## 코드 작성 규칙

> 코드를 작성하면서 생기는 규칙들을 컨벤션화 하기 위해 정리합니다.

### 공통 규칙

1. 생성자
    - ``@NoArgsConstructor`` 는 항상 accessLevel 을 private 으로 지정합니다.
    - 다른 모든 생성자는 필요에 따라 직접 선언합니다.
    - 생성자의 인자 수가 5개 이상인 경우 ``@Builder`` 어노테이션을 이용해 빌더 패턴을 사용하도록 합니다.

2. 메서드
    - 책임을 확실히 합니다.
    - 정해진 레이어 이외에서 사용하지 못하도록 합니다. (Presentation, Application, Domain Layer)
    - 한 메서드는 5줄을 넘어가지 않도록 합니다.


### 프로덕션 코드

1. Model 클래스 규칙

2. Service 클래스 규칙

3. Controller 클래스 규칙

4. Dto 클래스 규칙

### 테스트 코드

1. Test 메서드
    - Test 어노테이션이 Test 메서드 바로 위에 위치합니다.
    - DisplayName 어노테이션이 Test 어노테이션 위에 위치합니다.

    ```
        @DisplayName("디스플레이 네임의 예시입니다.")
        @Test
        public void createTest() {
            // ...
        }
    ```

