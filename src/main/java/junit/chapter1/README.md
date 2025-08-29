OverView
==
## 1.1 Junit5란 무엇인가?
이전 버전(JUnit4)와 달리 Junit5는 3개의 서브 프로젝트에 있는 서로 다른 모듈로 구성되어 있다.
```text
JUnit5 = JUnit Platform + Junit Jupiter + JUnit Vintage
```
### Junit Platform (환경)
- JVM에서 테스팅 프레임워크를 실행시키기 위한 기반을 제공한다.
- Platform에서 실행되는 테스팅 프레임워크를 개발하기 위한 TestEngine API도 정의되어 있다.
- 또한 커맨드 라인으로 Platform을 실행할 수 있는 Console Launcher와 하나 이상의 테스트 엔진을 사용해서 테스트 묶음을 실행하기 위한 Suite Engine을 제공한다.

### Junit Jupiter - Junit 5 (학습 대상)
- 테스트를 작성하기 위한 프로그래밍 모델과 확장 모델(Junit 5에서 확장을 위한)의 조합이다.
- Jupiter API를 기반으로 작성된 테스트를 Platform에서 실행하기 위해 필요한 TestEngine이 포함되어 있다.

### Junit Vintage - 이전 버전에서만 사용하는 API
- 이전 버전의 테스트를 Platform에서 실행하기 위해 필요한 TestEngine을 제공해준다. 

