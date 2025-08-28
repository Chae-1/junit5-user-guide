# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 프로젝트 개요

JUnit 5 학습을 위한 Gradle 기반 Java 프로젝트입니다. JUnit 5 테스팅 개념을 설명하는 교육 자료와 예제들이 챕터별로 한국어 문서와 함께 구성되어 있습니다.

## 빌드 시스템 및 명령어

이 프로젝트는 Gradle을 사용하며 주요 명령어는 다음과 같습니다:

### 빌드 및 테스트
- `./gradlew build` - 프로젝트 빌드 및 모든 테스트 실행
- `./gradlew test` - JUnit Platform을 사용한 테스트 실행
- `./gradlew clean` - 빌드 결과물 정리
- `./gradlew jar` - JAR 파일 생성
- `./gradlew check` - 모든 검증 작업 실행

### 개발 명령어
- `./gradlew classes` - 메인 클래스 컴파일
- `./gradlew testClasses` - 테스트 클래스 컴파일
- `./gradlew javadoc` - Javadoc 문서 생성

## 프로젝트 구조

```
src/
├── main/java/
│   ├── Calculator.java              # 메인 애플리케이션 클래스
│   ├── chapter1/README.md           # JUnit 5 개요 문서 (한국어)
│   ├── chapter2/README.md           # 테스트 작성 문서 (한국어)
│   └── chapter3/                    # 추가 챕터 내용
└── test/java/
    └── CalculateTest.java           # 기본 JUnit 5 테스트 예제
```

## 테스팅 프레임워크

- **JUnit 5 (Jupiter)**: 메인 테스팅 프레임워크 (버전 5.10.0)
- 테스트 설정: build.gradle에서 `useJUnitPlatform()` 사용
- 주요 테스트 import: `org.junit.jupiter.api.Test`와 `org.junit.jupiter.api.Assertions.*`

## 개발 참고사항

- JUnit 5 개념을 설명하는 한국어 교육 콘텐츠 포함
- README 파일로 이론적 설명이 포함된 챕터별 구성
- 테스팅 데모를 위한 기본 Calculator 클래스 제공
- 복잡한 빌드 설정이나 추가 테스팅 프레임워크 없는 단순한 구조