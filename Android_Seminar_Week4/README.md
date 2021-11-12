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


