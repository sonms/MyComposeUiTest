package com.example.mycomposeuitest.model.culture

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CulturalEventDataViewModel(private val apiService: ApiService) : ViewModel() {
    private val _data = MutableLiveData<List<CulturalEventResponseData>>()
    val data: LiveData<List<CulturalEventResponseData>> = _data

    fun fetchData(culturalEventRequestData : CulturalEventRequestData) {
        viewModelScope.launch {
            try {
                val response = apiService.fetchData(culturalEventRequestData)
                if (response.isSuccessful) {
                    _data.value = response.body()
                } else {
                    // Handle error
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}

/*data class LayerImage(var img: Int, var name:String, var checked: Boolean) {
    companion object {
        val imgListSaver = listSaver<LayerImage, Any>(
            save = { listOf(it.img, it.name ,it.checked) },
            restore = { LayerImage(it[0] as Int, it[1] as String, it[2] as Boolean) }
        )

        //복원, 저장
        // checkedImageIndices 값을 저장하고 화면 회전 시 복원하기 위한 Saver
        val checkedImageIndicesSaver = Saver<MutableList<Int>, List<Int>>(
            save = { it.toList() }, // MutableList를 List로 변환하여 저장
            restore = { it.toMutableList() } // List를 MutableList로 복원
        )
    }
}

class ImgViewModel : ViewModel() {
    var imgList = mutableStateListOf<LayerImage>()
        private set
    var idxList = mutableStateListOf<Int>()
        private set

    init {
        //imgList.add(LayerImage(R.drawable.body, "body", false))
        imgList.add(LayerImage(R.drawable.arms, "arms", false))
        imgList.add(LayerImage(R.drawable.ears, "ears", false))
        imgList.add(LayerImage(R.drawable.eyebrows, "eyebrows", false))
        imgList.add(LayerImage(R.drawable.eyes, "eyes", false))
        imgList.add(LayerImage(R.drawable.glasses, "glasses", false))
        imgList.add(LayerImage(R.drawable.hat, "hat", false))
        imgList.add(LayerImage(R.drawable.mouth, "mouth", false))
        imgList.add(LayerImage(R.drawable.mustache, "mustache", false))
        imgList.add(LayerImage(R.drawable.nose, "nose", false))
        imgList.add(LayerImage(R.drawable.shoes, "shoes", false))
    }

    fun ChangeVisibility(index: Int, isChecked : Boolean) {
        imgList[index].checked = isChecked
    }

    fun c(index: Int) {
        idxList.add(index)
    }

    fun d(index: Int) {
        idxList.remove(index)
    }
}*/