# 2주차 과제 

### 1. 실행 화면
#### <홈>
* Button, TextView 활용 <br>
* FollowerRecyclerView, RepositoryRecyclerView 만들기 <br>
* 각각의 RecyclerView를 담고있는 Fragment 2개 만들기 <br>
* 각각의 버튼을 눌렀을 때 알맞은 RecyclerView가 있는 Fragment로 전환되게 하기 <br>
* default로 보이는 Fragment는 FollowerRecyclerView를 담은 Fragment <br>
* RepositoryRecyclerView의 아이템에는 레포지토리 제목과 설명만 담겨있도록 (설명이 길면 ellipsize 속성 활용) <br>
* 둘 중 하나의 RecyclerView는 GridLayout으로 만들기 <br>

<br>
<div>
<img src="https://user-images.githubusercontent.com/61824695/138404989-ab0af560-e00d-42a3-be97-6c2ac27a56d5.png" width="25%">
<img src="https://user-images.githubusercontent.com/61824695/138405058-ada976c5-09e6-4bb7-940c-12704974e195.png" width="25%">
</div>
<br>  

### 2. 구현한 코드
* RecyclerView를 GridLayout으로 만들기
```xml
<androidx.recyclerview.widget.RecyclerView
        android:id="@+id/rv_repository"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layoutManager="androidx.recyclerview.widget.GridLayoutManager"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:spanCount="2"
        tools:listitem="@layout/item_follower" />
```
* RecyclerView를 Layout에 배치하기 위해 layoutManager 사용
* spanCount : 한 줄에 최대 몇 개의 grid item이 들어가는지 설정
<br>

* 버튼 눌렀을 때 Fragment 전환하기
```kotlin
    private fun initTransactionEvent(){
        val fragmentFollower = FollowerFragment()
        val fragmentRepository = RepositoryFragment()

        supportFragmentManager.beginTransaction().add(R.id.container_follower_repo, fragmentFollower).commit()

        binding.btnFollower.setOnClickListener{
            supportFragmentManager.beginTransaction().replace(R.id.container_follower_repo, fragmentFollower).commit()
        }

        binding.btnRepository.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.container_follower_repo, fragmentRepository).commit()
        }
    }

```
<br>

* Fragment에서 뷰 결합 사용
```kotlin
    
class RepositoryFragment : Fragment() {
    private lateinit var repositoryAdapter : RepositoryAdapter
    private var _binding: FragmentRepositoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepositoryBinding.inflate(inflater, container, false)
        initAdapter()
        return binding.root
    }

    private fun initAdapter(){
        repositoryAdapter = RepositoryAdapter()

        binding.rvRepository.adapter = repositoryAdapter

        repositoryAdapter.repositoryList.addAll(
            listOf(
                RepositoryData("repo A", "description A"),
                RepositoryData("repo B", "description B"),
                RepositoryData("repo C", "description C"),
                RepositoryData("repo D", "description D")
            )
        )
        repositoryAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
```
1. 생성된 결합 클래스에 포함된 정적 inflate() 메서드를 호출 -> Fragment에서 사용할 결합 클래스 인스턴스 생성
2. getRoot() 메서드를 호출하거나 Kotlin 속성 구문을 사용하여 루트 뷰 참조
3. onCreateView() 메서드에서 루트 뷰를 반환하여 화면상의 활성 뷰로 만듦
<br>

* Glide 사용해 이미지 불러오기
```kotlin
    implementation 'com.github.bumptech.glide:glide:4.11.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.11.0'
```
```kotlin
    class FollowerViewHolder(private val binding: ItemFollowerBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(data : FollowerData){
            Glide.with(binding.root).load(data.img).into(binding.ivProfile)
            binding.tvName.text = data.name
            binding.tvIntroduce.text = data.introduction
        }
    }
```
* with() : Context를 가져올 곳
* load() : 이미지를 로드하는 방법
* into() : 이미지를 보여줄 View
* 인터넷 권한을 추가해야 함
<br>

### 3. 배운 내용
* ellipsize
  * Text가 TextView를 넘어갔을 때 표시할 방법
  * start : 앞부분을 ...으로 표시
  * end : 뒷부분을 ...으로 표시
  * middle : 중간부분을 ...으로 표시
  * none : ...없이 뒷부분 잘라서 표시 (default)
  * marquee : 흐르는 텍스트 효과
    * marqueeRepeatLimit = "marquee_forever" : 반복횟수 
    * focusable = "true" : 포커스가 있어야 흐르는 효과가 생김
    * maxLine = "1"에서는 작동 X, singleLine = "true"에서 작동  

