# JVM(Java Virtual Machine) 자바 가상 머신

### JVM(Java Virtual Machine) 자바 가상 머신

자바 바이트 코드를 실행할 수 있는 주체
운영체제 위에서 동작하는 프로세스
자바 코드를 컴파일해서 얻은 바이트 코드를 해당 운영체제가 이해할 수 있는 기계어로 바꿔 실행시켜주는 역할

### JVM 구성

자바에서 소스를 작성하면 .java 파일이 생성됨. (소스파일)
소스파일을 자바 컴파일러가 컴파일하면 .class 파일이 생성됨.(클래스파일,바이트 코드)

- **Class Loader**
클래스파일들을 엮어서 JVM이 운영체제로부터 할당 받은 메모리 영역인 ***Runtime Data Area로 적재***하는 역할
자바 애플리케이션이 실행 중일 때 이런 작업이 수행됨.
- **Execution Engine**
Class Loader에 의해 메모리에 적재된 클래스(바이트 코드)들을 기계어로 변경해 명령어 단위로 실행하는 역할
명령어를 하나 하나 실행하는 인터프리터(Interpreter) 방식이 있고 JIT(Just-In-Time) 컴파일러를 이용하는 방식이 있음.
    - JIT 컴파일러는 적절한 시간에 전체 바이트 코드를 네이티브 코드로 변경해서 Execution Engine이 네이티브로 컴파일된 코드를 실행하는 것으로 성능을 높이는 방식이다.
- **Garbage Collector**
Heap 메모리 영역에 생성(적재)된 객체들 중에 참조되지 않는 객체들을 탐색 후 제거하는 역할
- **Runtime Data Area**
JVM의 메모리 영역으로 자바 애플리케이션을 실행할 때 사용되는 데이터들을 적재하는 영역이다.
자바 런타임 메모리(Runtime Data Area) 구조
    - **Method Area(메서드 영역)**
    클래스 멤버 변수의 이름, 데이터 타입, 접근 제어자 정보 같은 필드 정보와 메서드의 이름, 리턴 타입, 파라미터, 접근 제어자 정보 같은 메서드 정보, Type 정보(Interface인지 Class인지), Constant Pool(상수 풀 : 문자 상수, 타입, 필드, 객체 참조가 저장됨), (??) ***static 변수(클래스 변수), final class 변수 등이 생성되는 영역.***
        
        *논리적으로 Heap에 포함
        
        *Heap Area의 PermGen이라는 영역에 속한 영역
        
        *Java 8 이후로는 Metaspace라는 OS가 관리하는 영역으로 옮겨짐.
        
        ![Untitled](JVM(Java%20Virtual%20Machine)%20%E1%84%8C%E1%85%A1%E1%84%87%E1%85%A1%20%E1%84%80%E1%85%A1%E1%84%89%E1%85%A1%E1%86%BC%20%E1%84%86%E1%85%A5%E1%84%89%E1%85%B5%E1%86%AB%2079ac6607d0124d70971261f5bd952867/Untitled.png)
        
    - **Heap Area**
    new 키워드로 생성된 객체와 배열이 생성되는 영역
    메서드 영역에 로드된 클래스만 생성이 가능
    GC가 참조되지 않는 메모리를 확인하고 제거하는 영역
    - **Stack Area(스택 영역)** -> call stack, excution stack
    지역 변수, 파라미터, 리턴 값, 연산에 사용되는 임시 값 등이 생성되는 영역
    - **PC Register**
    Thread(스레드)가 생성될 때마다 생성되는 영역으로 Program Counter 즉, 현재 스레드가 실행되는 부분의 주소와 명령을 저장하고 있는 영역이다.(* CPU의 레지스터와 다름)
    이것을 이용해서 스레드를 돌아가면서 수행할 수 있게 한다.
    - **Native Method Stack**
    자바 외 언어로 작성된 네이티브 코드를 위한 메모리 영역이다.
    보통 C/C++ 등의 코드를 수행하기 위한 스택이다. (JNI)
- * 스레드가 생성되었을 때 기준으로
메서드 영역과 힙 영역은 모든 스레드가 공유
스택 영역과 PC 레지스터, Native Method Stack은 각각의 스레드마다 생성되고 공유되지 않는다.

<참조>

[https://jithub.tistory.com/40](https://jithub.tistory.com/40)

[https://jeong-pro.tistory.com/148](https://jeong-pro.tistory.com/148)