# 3주차 과제 

### 1. 실행 화면
#### <로그인>
* 과제에 디자인 적용하기
* EditText에 selector 활용하기 (focus 되었을 때, 안 되었을 때)
* 깃허브 로고는 피그마나 제플린에서 export 받아오기 
* 간단한 도형들(입력창, 버튼)은 ShapeDrawable로 직접 만들기
<br>

<div>
<img src="https://user-images.githubusercontent.com/61824695/139580859-e9ffa504-d0bc-452e-a997-6227bafbc254.png" width="25%">
<img src="https://user-images.githubusercontent.com/61824695/139580833-ae5c1286-ebd7-4ff7-8875-941b58aefa12.png" width="25%">
</div>
<br>

#### <회원가입>
* 과제에 디자인 적용하기
* EditText에 selector 활용하기 (focus 되었을 때, 안 되었을 때)
* 간단한 도형들(입력창, 버튼)은 ShapeDrawable로 직접 만들기
<br>

<div>
<img src="https://user-images.githubusercontent.com/61824695/139580864-7413dddd-b199-4cac-90f6-691d1a6e0dbc.png" width="25%">
<img src="https://user-images.githubusercontent.com/61824695/139580879-3a592040-18ce-4dc9-93f7-e473dfa703e8.png" width="25%">
</div>
<br>  

#### <프로필 화면>
* 과제에 디자인 적용하기
* Button에 selector 활용하기 (선택 되었을 때, 안 되었을 때)
* 이미지의 경우 Glide의 CircleCrop 기능 활용해서 넣어주기
* 아이콘은 svg나 이미지(png, jpg)등으로 export해서 사용
* 하단에 BottomNavigation 넣어주기
<br>

<div>
<img src="https://user-images.githubusercontent.com/61824695/139580911-baee36db-c65b-404e-99b4-ccc9534b0f99.png" width="25%">
<img src="https://user-images.githubusercontent.com/61824695/139580930-e8ccadec-1030-47fe-9d9e-fc6074e666bd.png" width="25%">
</div>
<br>  

#### <홈 화면>
* 과제에 디자인 적용하기
* 3차 세미나에서 배웠던 TabLayout + ViewPager2 넣어주기
* 탭 레이아웃 인디케이터 및 텍스트는 피그마나 제플린의 색상대로
<br>

<div>
<img src="https://user-images.githubusercontent.com/61824695/139580958-ebab679f-a547-4eb5-b228-46cc2b199793.png" width="25%">
<img src="https://user-images.githubusercontent.com/61824695/139580967-eade2e07-2963-4b68-95b1-cec2ac74c5b7.png" width="25%">
</div>
<br>  

### 2. 구현한 코드
* EditText에 selector 활용하기 (focus 되었을 때, 안 되었을 때)
```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:drawable="@drawable/rectangle_border_pink"
        android:state_focused="true" />
    <item
        android:drawable="@drawable/rectangle_fill_border_gray"
        android:state_focused="false" />
</selector>
```
* state_focus="true"는 EditText가 눌렸을 때 사용, 사용자가 입력 중인지 확인할 수 있음
<br>

* 원 모양으로 이미지 받아오기
```kotlin
Glide.with(binding.root).load(data.img).circleCrop().into(binding.ivProfile)
```
<br>

* BottomNavigation 
```xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:id="@+id/menu_profile"
        android:icon="@drawable/ic_profile"
        android:title="프로필"
        />

    <item
        android:id="@+id/menu_home"
        android:icon="@drawable/ic_home"
        android:title="홈"
        />

    <item
        android:id="@+id/menu_camera"
        android:icon="@drawable/ic_camera"
        android:title="카메라"
        />
</menu>
```

```kotlin
class MainActivity : AppCompatActivity() {
    private lateinit var sampleViewPagerAdapter: SampleViewPagerAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        initAdapter()
        initBottomNavi()

        setContentView(binding.root)
    }

    private fun initAdapter(){
        val fragmentList = listOf(ProfileFragment(), HomeFragment(), CameraFragment())

        sampleViewPagerAdapter = SampleViewPagerAdapter(this)
        sampleViewPagerAdapter.fragments.addAll(fragmentList)

        binding.vpSample.adapter = sampleViewPagerAdapter
    }


    private fun initBottomNavi(){
        binding.vpSample.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                binding.bnvSample.menu.getItem(position).isChecked = true
            }
        })

        binding.bnvSample.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_profile -> {
                    binding.vpSample.currentItem = FIRST_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                R.id.menu_home -> {
                    binding.vpSample.currentItem = SECOND_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                else -> {
                    binding.vpSample.currentItem = THIRD_FRAGMENT
                    return@setOnItemSelectedListener true
                }
            }
        }
    }

    companion object {
        const val FIRST_FRAGMENT = 0
        const val SECOND_FRAGMENT = 1
        const val THIRD_FRAGMENT = 2
    }
}
```
* OnPageChangeCallback : ViewPager2의 화면 전환을 감지하는 추상 클래스
* OnItemSelectedListener : BottomNavigation으 Item들이 선택 되었을 때 호출되는 리스너

<br>

* TabLayout
```xml
<com.google.android.material.tabs.TabLayout
    android:id="@+id/tl_follow"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="32dp"
    app:tabSelectedTextColor="@color/pink"
    app:tabIndicatorColor="@color/pink"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toBottomOf="@+id/tv_github"/>
```
* tab SelectedTextColor : 탭이 선택되었을 때 글자 색
* tabIndicatorColor : Indicator 색
```kotlin
private fun initTabLayout(){
    val tabLable = listOf("팔로잉", "팔로워")

    TabLayoutMediator(binding.tlFollow, binding.vpFollow) {tab, position ->
        tab.text = tabLable[position]
    }.attach()
}
```
* TabLayoutMeditor : ViewPager2와 TabLayout 연동
<br>

* ViewPager2
 ```xml
<androidx.viewpager2.widget.ViewPager2
    android:id="@+id/vp_follow"
    android:layout_width="match_parent"
    android:layout_height="0dp"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toBottomOf="@+id/tl_follow" />
```
```kotlin
class SampleViewPagerAdapter(fragmentActivity : FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
val fragments = mutableListOf<Fragment>()

override fun getItemCount(): Int = fragments.size

override fun createFragment(position: Int): Fragment = fragments[position]
}
```
* getItemCount : Adapter가 가지고 있는 data set 안에서의 전체 아이템 수 리턴
* createFragment : 특정 포지션에 연결된 새로운 Fragment를 제공하는 기능을 가진 메소드
```kotlin
private fun initAdapter(){
    val fragmentList = listOf(FollowingFragment(), HomeFollowerFragment())

    sampleTabViewPagerAdapter = SampleTabViewPagerAdapter(this)
    sampleTabViewPagerAdapter.fragmentList.addAll(fragmentList)

    binding.vpFollow.adapter = sampleTabViewPagerAdapter
}
```
* ViewPager2와 Adapter 연동
<br>

### 3. 배운 내용
* ViewPager2의 장점
  * 수평, 수직 스크롤 모두 지원
  * LTR, RTL 모두 지원
  * 리사이클러 뷰 기반으로 동작 (리사이클러 뷰 어댑터 또한 사용 가능)
  * notifyDataSetChanged 이용해서 UI 업데이트(Fragment 교체) 가능
  * DiffUtil 사용 가능
<br>

* BottomNavigation
  * ViewPager2와 연동하여 서브화면들을 전환
  * 마테리얼 디자인 가이드상 BottomNavigation으로 표현하는 화면은 3개 이상 5개 이하
  * BottomNavigation이 보여주는 화면은 동등한 중요도(깊이)를 가짐 
<br>

* TabLayout
  * 컨텐츠 전환 및 탐색을 위한 탭 레이아웃
  * 상단탭을 만들 때 주로 사용
  * BottomNavigationView에 비해 위치 이동이 자유로움
  * CoordinatorLayout, AppbarLayout과 함께 사용하면 알아서 크기와 위치 조절
  * 텍스트, 아이콘 뿐 아니라, 커스텀뷰를 메뉴로 설정 가능 
<br>

* selector 속성의 종류
  * state_pressed 
  * state_focused 
  * state_selected
  * state_checkable
  * state_checked
  * state_enabled
<br>

* Glide 
  * CenterCrop 
    * 이미지가 ImageView의 사이즈보다 클 때, ImageView의 크기에 맞춰 이미지 크기를 조정 후 자름
    * ImageView가 꽉 채워지지만, 전체 이미지가 표시되지 않을 수 있음
  * FitCenter
    * 이미지가 ImageView의 사이즈와 다를 때, ImageView 크기와 같거나 작도록 이미지 크기를 조정
    * 이미지가 완전히 표시되지만, 전체 ImageView를 채우지 않을 수도 있음
  * CircleCrop
    * 이미지가 원으로 변경  



