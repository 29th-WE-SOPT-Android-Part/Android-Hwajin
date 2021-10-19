# 1주차 과제 

### 1. 실행 화면
#### <로그인>
* 아이디, 비밀번호 입력이 모두 되어있을 때만 로그인 버튼을 눌렀을 때 홈화면으로 이동 <br>
* 만일 모든 입력이 되어있지 않으면 "로그인 실패"라는 토스트 메시지 출력 <br>
* 비밀번호 입력 내용을 가려지도록 <br>
* 모든 EditTextView는 미리보기 글씨가 있도록 <br>
* 회원가입 버튼을 눌렀을 때는 회원가입 화면으로 이동 <br>

<br>
<div>
<img src="https://user-images.githubusercontent.com/61824695/136700040-4e092d6d-ea33-4357-b5f8-b6ec1d066ff5.png" width="25%">
<img src="https://user-images.githubusercontent.com/61824695/136700061-721cd90c-17d9-4525-ba90-6880d381f36c.png" width="25%">
<img src="https://user-images.githubusercontent.com/61824695/136700076-d6ff9b4d-47cc-40fe-84a9-1286c80a22e3.png" width="25%">
</div>
<br>  

#### <회원가입>
* 이름, 아이디, 비밀번호 입력이 모두 되어있을 때만 회어ㅜㄴ가입 완료버튼을 눌렀을 때 다시 로그인화면으로 이동 (finish 활용) <br>
* 비밀번호는 입력 내용이 가려지도록 <br>
* 모든 EditTextView는 미리보기 글씨가 있도록 <br>
* 만일 모든 입력이 되어있지 않으면 "입력되지 않은 정보가 있습니다"라는 토스트 메시지 출력<br>

<br>
<div>
<img src="https://user-images.githubusercontent.com/61824695/136700195-9733285c-2612-4b38-8a92-8da8b46a8c30.png" width="25%">
<img src="https://user-images.githubusercontent.com/61824695/136700221-84ff2b4e-a711-47c5-89d9-7ebaef00cc23.png" width="25%">
</div>
<br>  

#### <홈>
* 홈화면 이동시 "ooo님 환영합니다"이라는 토스트가 뜨도록 <br>

<br>
<div>
<img src="https://user-images.githubusercontent.com/61824695/136700354-2fc9c283-8d3a-4116-88d1-3289a228314e.png" width="25%">
<img src="https://user-images.githubusercontent.com/61824695/136700255-bbdd887c-a41b-4da2-b48c-149e543e26fe.png" width="25%">
</div>
<br>  

### 2. 구현한 코드
```xml
<EditText
        android:id="@+id/et_pw"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginTop="12dp"
        android:layout_marginEnd="24dp"
        android:background="@drawable/rectangle_border_pink"
        android:ems="10"
        android:hint="비밀번호를 입력해주세요."
        android:inputType="textPassword"
        android:padding="15dp"
        android:textSize="12sp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="@+id/tv_pw"
        app:layout_constraintTop_toBottomOf="@+id/tv_pw" />
```
* hint를 이용해 edittext에 어떤 내용을 써야할지 미리보기로 보여줌
* inputType을 이용해 비밀번호 입력시 내용이 가려지도록
<br>

```kotlin
binding.btnLogin.setOnClickListener {
            val id = binding.etId.text
            val pw = binding.etPw.text
            if(id.isEmpty() || pw.isEmpty()) {
                Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }
```
 * isEmpty()를 통해 edittext에 입력이 있는지 확인
 * Toast를 이용하여 로그인 실패시 실패했다고 알려줌
 <br>
 
 ```kotlin
 buildFeatures {
        viewBinding true
    }
```
* ViewBinding을 사용하기 위해 build.gradle에 추가

```
  private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Toast.makeText(this, "정화진님 환영합니다", Toast.LENGTH_SHORT).show()
    }
```
* xml에 접근 가능하도록 binding 객체를 생성
* binding에서 생성한 root뷰를 setContentView

### 3. 배운 내용
* ViewBinding
  * 각 XML 레이아웃 파일마다 Binding 클래스를 생성하는 기능
  * findngViewById를 사용해서 접근 -> binding.ooo로 접근
  * Binding class의 이름은 xml파일 이름을 UpperCamelCase 표기법으로 바꾸고 뒤에 Binding을 붙인 것과 같음
    * ex) activity_main -> ActivityMain
