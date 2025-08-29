AssertJ
==

단언문을 작성할 수 있는 다양한 셋을 제공해주고 도움되는 에러 메시지, 코드 가독성 측면에서 우수하다.

## Core Guide

### Simple description
assertion이 수행되는 설명하는것이 종종 가치가 있을 수 있다. 

특히, boolean assertions일 경우에 실패하는 경우에 출력되는 메시지를 지정하는 메서드인데, 반드시 assertion을 호출하기 전에 정의해야한다.
그렇지 않으면, 중간에 실패된 assertion에 의해 취소되어 출력되지 않을 수 있다. 

### Overriding error message
overridingErrorMessage, withFailMessage()를 통해 항상 메시지를 오버라이딩할 수 있다.

Junit5와 마찬가지로 에러메시지와 관련해서 지연 로딩을 지원하기 위해 Supplier<String> 타입의 파라미터로 전달가능하다.

### Avoiding incorrect usage
AssertJ를 오용하지 않기 위해 다음 몇 가지를 기억해야한다.

#### Forgetting to call an assertion
assertThat() 메서드에 객체를 전달한 이후, assertion을 수행하지 않을 경우가 종종 발생한다.

이때, 정적 코드 분석 툴(SonarQube, SpotBugs)에서 정적 분석에 실패할 수 있다.

#### Calling as() after the assertion
as() 메서드를 모든 assertion이 호출되기 이전에 as()를 호출해야한다. 그렇지 않으면 무시된다.

#### Calling withFailMessage/overridingErrorMessage after the assertion
as() 메서드와 마찬가지로 assertion 전에 호출해야 무시되지 않는다.

#### Setting a comparator after the assertion
usingComparator 메서드를 모든 assertion이 실행되기 이전에 호출해야한다.

### Configuring AssertJ

### Controlling type formatting

Assertion 에러 메서지를 관련된 다양한 유형을 형식화하기 위해 Representation을 사용한다.

custom Representation을 등록하기 위해 다음과 같은 방법을 사용한다.

- Assertions.useRepresentation(myRepresentation)를 호출하여 global로 설정한다.
- assertThat(actual).withRepresentation(myRepresentation)를 호출하여 assertion 단위로 representation을 설정한다.
- 사용할 Representation을 Configuration에 등록한다.


### Iterable and array, Object, String/CharSequence assertions
각각 Java doc Reference를 참조하라.

- Object
  - https://www.javadoc.io/static/org.assertj/assertj-core/3.27.3/org/assertj/core/api/AbstractAssert.html#method.summary
- String/CharSequence
  - https://www.javadoc.io/static/org.assertj/assertj-core/3.27.3/org/assertj/core/api/AbstractCharSequenceAssert.html#method.summary

Array, Iterable Assertions을 잘 활용하기 위해 알아야할 기능은 다음과 같다.
- Different ways of checking iterables/arrays content(Array, Iterable의 내용을 확인하기 위한 Assertions)
  - contains
    - 정렬 순서에 상관없이 실제 array/iterable에 주어진 값들이 들어있는지 검증한다.
  - containsOnly
    - 정렬 순서, 중복에 상관 없이 주어진 값들로만 이뤄져있는 지 확인한다.
  - containsExactly
    - 정확한 정렬 순서에 맞게 주어진 값들이 실제 값에 들어가 있는지 확인한다. 
  - containsExactlyInAnyOrder
    - 정렬 순서에 상관없이 주어진 값들로만 구성되어 있는지 확인한다.
  - containsSequence
    - 정확한 순서대로 실제 값들이 주어진 값들로 구성되어 있고 순서 사이에 다른 값이 들어가 있지 않고 정확해야한다.
    - 
  - containsSubsequence
    -  
  - containsOnlyOnce
  - containsAnyOf
- Running assertions on some elements (any, all, none)
- Navigating to a given element to check it
- Filtering elements before asserting]
- Extracting/mapping elements before asserting
- Comparing elements with a specific comparator
