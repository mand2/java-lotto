# 로또
## 진행 방법
* 로또 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

### step 1
- [x] 입력한 값이 null 혹은 빈 문자열 일 때 0 반환  
    InputValue 구현
- [x] 구분자가 없고, 숫자 하나만 있을 때
    StringSplitter 구현      
  - [x] 👉 해당 숫자 반환  
    
- 정해진 구분자(, :)인 경우 
    - [x] 문자열 분리
    - [x] 분리한 각 숫자의 합 반환
    - [x] 분리한 값이 숫자가 아닐 때 예외처리(RuntimeException)
    - [x] 분리한 값이 음수일 때 예외처리(RuntimeException)

- 커스텀 구분자 인 경우
    - [x] 구분자 파악 (// \n 사이의 문자)
    - [x] 위의 **정해진 구분자**와 같은 로직으로 개발
    - 커스텀 지정인 경우 정해진 구분자(, :) 에 추가된 경우인듯??

----

### step 2
- [x] review 피드백 반영
  - util성 라이브러리 삭제 (NumberUtils)
  - 사용하지 않는 생성자 삭제
  - 테스트를 위한 메서드 삭제
  - 문자열 상수로 관리
  - Null, 빈문자열 용 테스트로 변경
  - PositiveNumber 객체 추가

- 구입금액 입력
  - [x] 입력값 VIEW (입력해주세요, 몇 장 구매)
  - 구입금액 객체
    - [x] 입력값 (null, 빈문자열, 1000 원 단위 아닌 경우, 최소 1개 이상 구매필요.) 체크
    - [x] validation 맞으면 `#개 구매했습니다` 아니면 `예외 메세지` 출력.
    - [x] 게임 몇 개를 자동으로 구매했는지 넘기기.
  
- 로또 번호 셋(List)  
  - [x] 게임 횟수에 따른 로또게임 list 생성,
  - 게임 당
    - 게임 interface 생성, (자동, 수동 게임객체가 구체화하면 될 듯.)   
        1게임당 6개의 숫자, 입력된 숫자 보여주는 메서드. 오름차순 메서드.
      - [x] 발행시 오름차순.
      - [x] 발행 후 list print. `[]` 형태로.  
          예) `[2, 8, 9, 18, 19, 21]` 👉 `lottoNumberSet.value().toString()`
      - [x] 결과 값(몇 등??)  ?? 잘 모르게씀,,  
      - 게임A와 게임B의 모든 숫자가 같더라도 다른 게임임.(중복으로 입력해서 n배씩 받아가는 경우 있음.) 
      - 자동 게임인 경우
        - [x] 로또번호 발행(자동 발행) 객체 👉 게임 객체 하나에 넣어줌.
        - [x] 발행시 겹치는 숫자 없어야 함.
  
- 당첨번호 입력
  - [x] ResultView :: 입력값 VIEW
  - 입력값 체크 => 얘를 아예 따로 만드는게 나을듯!! 수동 번호 입력할 때에도 사용됨.
    - LottoNumber set으로 변수 입력값 체크
      - [x] null 
      - [x] 빈문자열  
      - [x] 6개 개수 맞는지
      - [x] 숫자형 + 양수 인지, 로또 범위값에 해당하는 숫자인가
      - [x] 겹치는 숫자 없는지
    - [x] tokenizer : **`,[빈칸]`** 으로 구분한다.
      👉 제대로된 입력값이 아니라면 `예외 메세지` 출력.
    
  - [X] 당첨번호 객체 생성
    - [x] 저장시 오름차순으로!!
  - [x] 결과확인 객체: 비교대상인 `로또번호`가 `당첨번호`와 몇개나 맞는지 확인. 
  
- 당첨 통계
  - `당첨번호 객체`와, `결과확인` 객체 => 당첨통계 객체에 추가
  - 당첨통계 객체 생성  
    - 당첨금 객체 생성
      - [x] 기본구현: (등수, 상금) 형식으로.
    - [x] 등수 + 몇개인지 구하는 메서드
    - [x] 당청금 합계 구하기
    - [x] 구입한 금액과 비교하여 수익률 계산
  - [x] ResultView :: 수익률 VIEW.

----

### step3
- [x] 피드백 반영
  - LottoGenerator의 `shuffle()` 명칭 변경 (행위에 맞게 변경하기)
  - LottoNumberBoard **stream**으로 사용하기
  
  👀 생각해 볼 부분 
    - 자동 | 수동인 경우 행동을 따로 빼야하나? step2 와 같이 LottoGenerator에서 처리할지

- 2등 구현하기
  - [x] 기본구현: 2등 = bonusBall 과 맞으면 + 5개 맞음.
    - [x] 보너스볼
        - 기존 당첨번호와 겹치지 않아야 함
        - 숫자 입력 확인.
    
  - [x] 리팩토링 및 테스트
    - 결과값 계산 확인
   
  - [x] 당첨번호+보너스볼 질문은 InputView로

----

### step4
-  피드백 반영
  - [x] WinningStatistics `from` 수정
  - [x] LottoNumber `compare` 간결하게
  - [x] WinningRank 사용하지 않은 변수 삭제
  - [x] WinningNumber unmodifiable 컬렉션 이중으로 호출 X
  
- 수동 구현  
  - [x] 수동구매 개수 입력
    - [x] `Round`로 포장
  - [x] 구매번호 입력
  - [x] 자동+수동 generate 확인 
  - [x] LottoNumberSet 자체를 set으로만 써보기..?
  - `Application` 추가

- 추가 피드백
  - [x] LottoNumberSet 생성할 때부터 변경 불가능하도록 변경하기.
  - [ ] LottoGenerator `lottoNumbers`의 타입 변경
  - [ ] LottoGenerator Lottos로 인스턴스 두번 생성하지 말고 리스트 합쳐서 넘기기
  - [ ] WinningStatistics 프린트 시 정렬방법 변경 
  
