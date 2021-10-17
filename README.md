## 요구사항

1. URL을 입력 받는다.
2. URL 접속 후 받은 HTML 문자열을 출력한다.
3. 영어 및 숫자만 출력한다.
4. 숫자-오름차순으로, 영문-대문자/소문자 순서로 출력한다.
5. 영어-숫자 순으로 출력한다.
6. 입력 받은 출력 묶음 단위로 묶음 단위 내 값을 몫으로, 그 외는 나머지로 출력한다.

## 패키지 구조
<pre>
com.wemakeprice.htmlfiltering
    └ controller
    └ domain
    └ exception
    └ service
        └ impl
    └ utils
</pre>

## 실행 방법

Spring Boot Application 실행 후 [http://localhost:8080/index.html](http://localhost:8080/index.html) 에 접속
