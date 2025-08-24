OverView
==
## 1.1 Junit5란 무엇인가?
이전 버전(JUnit4)와 달리 Junit5는 3개의 서브 프로젝트에 있는 서로 다른 모듈로 구성되어 있다.
```text
JUnit5 = JUnit Platform + Junit Jupiter + JUnit Vintage
```
### Junit Platform
- JVM에서 테스팅 프레임워크를 실행시키기 위한 기반을 제공한다.
- Platform에서 실행되는 테스팅 프레임워크를 개발하기 위한 TestEngine API도 정의되어 있다.
- Console Launcher 커맨드라인