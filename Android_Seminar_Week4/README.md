# 4주차 과제 

### 1. 실행 화면
#### <POSTMAN 테스트>
* 회원가입 
<div>
<img src="https://user-images.githubusercontent.com/61824695/141474891-560605ed-028c-4b84-9673-e9c8c8fbbedb.png" width="50%">
</div>

* 로그인 
<div>
<img src="https://user-images.githubusercontent.com/61824695/141474850-8e7c64cc-5293-4a57-a816-bb79062e660b.png" width="50%">
</div>
<br>

#### <회원가입 완료>
<div>
<img src="https://user-images.githubusercontent.com/61824695/141475208-347ff6a7-ff20-4c30-bdf4-628cf4c28bb5.png" width="25%">
<img src="https://user-images.githubusercontent.com/61824695/141475271-6f9468bb-f46f-4b19-b8df-6457fa35a3d2.png" width="25%">
</div>
<br>  

#### <로그인 완료>
<div>
<img src="https://user-images.githubusercontent.com/61824695/141475079-bb753182-7c39-4cfe-b4dc-5efe152715b9.png" width="25%">
<img src="https://user-images.githubusercontent.com/61824695/141475558-2078e70a-5764-475d-86bd-8cb6b2251039.png" width="25%">
</div>
<br>  

### 2. 구현한 코드
* ServiceCreator
```kotlin
object ServiceCreator {
    private const val BASE_URL = "https://asia-northeast3-we-sopt-29.cloudfunctions.net/api/"

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val sampleService: SampleService = retrofit.create(SampleService::class.java)
}
```
<br>

* Interface
```kotlin
interface SampleService {
    @Headers("Content-Type: application/json")
    @POST("user/login")
    fun postLogin(
        @Body requestLoginData : RequestLoginData
    ) : Call<ResponseLoginData>

    @Headers("Content-Type: application/json")
    @POST("user/signup")
    fun postSignUp(
        @Body requestSignUpData : RequestSignUpData
    ) : Call<ResponseSignUpData>
}
```
<br>

* 회원가입(SingUpData) Request/Response
```kotlin
data class RequestSignUpData (
    val email: String,
    val password: String,
    val name: String
)
```

```kotlin
data class ResponseSignUpData(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : Data
){
    data class Data(
        val id : Int,
        val name : String,
        val email : String,
        val password: String
    )
}
```
<br>

* 로그인(SignInData) Request/Response
```kotlin
data class RequestLoginData(
    val email : String,
    val password: String
)
```

```kotlin
data class ResponseLoginData(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : Data
){
    data class Data(
        val id : Int,
        val name : String,
        val email : String
    )
}
```
<br>

* 회원가입 통신 요청
```kotlin
private fun initNetwork(){
        val requestSignUpData = RequestSignUpData(
            binding.etName.text.toString(),
            binding.etId.text.toString(),
            binding.etPw.text.toString()
        )

        val call: Call<ResponseSignUpData> = ServiceCreator.sampleService.postSignUp(requestSignUpData)

        call.enqueue(object: Callback<ResponseSignUpData> {
            override fun onResponse(
                call: Call<ResponseSignUpData>,
                response: Response<ResponseSignUpData>
            ) {
                if(response.isSuccessful) {
                    Toast.makeText(this@SignUpActivity, "회원가입에 성공했습니다.", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this@SignUpActivity, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseSignUpData>, t: Throwable) {
                Log.e("NetworkTest", "error:$t")
            }
        })
    }
```
<br>

* 로그인 통신 요청
```kotlin
private fun initNetWork(){
        val requestLoginData = RequestLoginData(
            binding.etId.text.toString(),
            binding.etPw.text.toString()
        )

        val call: Call<ResponseLoginData> = ServiceCreator.sampleService.postLogin(requestLoginData)

        call.enqueue(object : Callback<ResponseLoginData> {
            override fun onResponse(
                call: Call<ResponseLoginData>,
                response: Response<ResponseLoginData>
            ){
                if(response.isSuccessful){
                    //val data = response.body()?.data

                    Toast.makeText(this@SignInActivity, "${response.body()?.data?.email}님 반갑습니다.", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
                }else{
                    Toast.makeText(this@SignInActivity, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show()

                }
            }

            override fun onFailure(call: Call<ResponseLoginData>, t: Throwable){
                Log.e("NetworkTest", "error:$t")
            }
        })
    }
```
<br>

# 7주차 

### 1. 실행 화면


https://user-images.githubusercontent.com/61824695/146559943-2b63e740-6bd2-4bf0-9473-18338103a0b6.mp4


<br>

### 2. 구현한 코드
* 네비게이션 - xml
```xml
    <?xml version="1.0" encoding="utf-8"?>
    <navigation xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:id="@+id/nav_onboarding"
        app:startDestination="@id/onBoardingFragment1">

        <fragment
            android:id="@+id/onBoardingFragment1"
            android:name="com.example.android_seminar_week4.ui.onboarding.OnBoardingFragment1"
            android:label="fragment_on_boarding1"
            tools:layout="@layout/fragment_on_boarding1" >
            <action
                android:id="@+id/action_onBoardingFragment1_to_onBoardingFragment2"
                app:destination="@id/onBoardingFragment2" />
        </fragment>
        <fragment
            android:id="@+id/onBoardingFragment2"
            android:name="com.example.android_seminar_week4.ui.onboarding.OnBoardingFragment2"
            android:label="fragment_on_boarding2"
            tools:layout="@layout/fragment_on_boarding2" >
            <action
                android:id="@+id/action_onBoardingFragment2_to_onBoardingFragment3"
                app:destination="@id/onBoardingFragment3" />
        </fragment>
        <fragment
            android:id="@+id/onBoardingFragment3"
            android:name="com.example.android_seminar_week4.ui.onboarding.OnBoardingFragment3"
            android:label="fragment_on_boarding3"
            tools:layout="@layout/fragment_on_boarding3" />
    </navigation>
```
<br>

* 네비게이션 - class
```kotlin
    class OnBoardingFragment1 : Fragment() {
        private var _binding: FragmentOnBoarding1Binding? = null
        private val binding get() = _binding ?: error("Binding이 초기화되지 않았습니다.")

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            _binding = FragmentOnBoarding1Binding.inflate(layoutInflater, container, false)

            initNextBtnListener()

            return binding.root
        }

        private fun initNextBtnListener(){
            binding.btnNext.setOnClickListener {
                findNavController().navigate(R.id.action_onBoardingFragment1_to_onBoardingFragment2)
            }
        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }
```
<br>

* SharedPreference
```kotlin
package org.sopt.myapplication.util

import android.content.Context

object SoptSharedPreference {

    private const val STORAGE_KEY = "USER_AUTH"
    private const val AUTO_LOGIN = "AUTO_LOGIN"

    fun getAutoLogin(context: Context) : Boolean {
        val preferences = context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)
        return preferences.getBoolean(AUTO_LOGIN, false)
    }

    fun setAutoLogin(context: Context, value : Boolean) {
        val preferences = context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)
        preferences.edit()
            .putBoolean(AUTO_LOGIN, value)
            .apply()
    }

    fun removeAutoLogin(context: Context) {
        val preferences = context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)
        preferences.edit()
            .remove(AUTO_LOGIN)
            .apply()
    }

    fun clearStorage(context: Context) {
        val preferences = context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)
        preferences.edit()
            .clear()
            .apply()
    }
}
```
```kotlin
    private fun isAutoLogin(){
            if(SoptSharedPreference.getAutoLogin(this)){
                shortToast("자동로그인 완료")
                startActivity(Intent(this@SignInActivity, MainActivity::class.java))
                finish()
            }
    }
```
<br>

* Util
```kotlin
    fun Context.shortToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    @Px
    fun View.px(dp: Int) = (dp * resources.displayMetrics.density).roundToInt()
```
<br>

* 패키징
<div>
<img src="https://user-images.githubusercontent.com/61824695/146558733-afc58d1d-8c83-4664-9b26-a753e942ab3e.png" width="50%">
</div>
크게 data, ui, util로 나누었습니다. ui에는 화면별로 분류하였고, data는 request와 response를 분리하였습니다.
<br>

### 3. 배운 내용
* Util 클래스
  * 일종의 인스턴스에 대해 작고 반복적인 작업을 수행하는 정적 클래스
  * 자주 사용하는 함수들을 static하게 선언해서 모든 클래스에서 접근할 수 있게 모아둔 클래스
<br>

* 확장 함수
  * 기존 클래스에 매소드를 추가
  * 간단한 기능을 추가하고 싶을 때 사용
<br>

* NavigationComponent
  * 하나의 Activity내에서 Fragment간의 전환을 보다 심플하고 안정적으로 할 수 있게 도와주는 Jetpact Component
  * NavHostFragment가 Fragment 대상의 교체를 관리
  * 유연한 Action 관리
  * 보일러 플레이트 코드 완화  
<br>

* 영속성 데이터
  * 데이터를 생성한 애플리케이션의 실행이 종료되더라도 사라지지 않는 데이터
* SharedPreferences
  * 간단한 데이터를 key-value형식으로 저장할 수 있게 해주는 로컬 저장소
  * 기본 자료형의 읽기, 쓰기 지원
  * 애플리케이션 내부에서만 접근 가능
  * 비교적 간단한 데이터 저장에 용이   
