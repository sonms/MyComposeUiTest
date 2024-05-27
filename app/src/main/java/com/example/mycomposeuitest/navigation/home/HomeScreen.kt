package com.example.mycomposeuitest.navigation.home

import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.mycomposeuitest.model.culture.ApiService
import com.example.mycomposeuitest.model.culture.CulturalEventDataViewModel
import com.example.mycomposeuitest.model.culture.CulturalEventRequestData
import com.example.mycomposeuitest.model.culture.CulturalEventResponseData
import com.example.mycomposeuitest.ui.theme.MyComposeUiTestTheme


@Composable
fun HomeScreen(viewModel: CulturalEventDataViewModel) { //등록한 위치를 기준으로 문화 추천 + 랜덤 안전 추천?
    val data by viewModel.data.observeAsState()
    //val viewModel: CulturalEventDataViewModel = viewModel()
    val culturalEventRequestData = CulturalEventRequestData(
        key = "",
        type = "",
        service = "",
        startIndex = 0,
        endIndex = 1,
        codeName = null,
        title = null,
        date = null
    )
    viewModel.fetchData(culturalEventRequestData)
    // 데이터 로드를 시작합니다.
    if (data != null) {
        //Text(text = "ID: ${data!!.id}, Name: ${data!!.name}")
        LaunchedEffect(Unit) {
            viewModel.fetchData(culturalEventRequestData)
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "home")
            initSetCultureRow(list = data!!)
        }
    } else {
        // 데이터 로딩 중일 때 표시할 내용
        CircularProgressIndicator()
    }

}


@Composable
fun initSetCultureRow(list: List<CulturalEventResponseData>) {
    LazyRow {
        items(list) { cultureData ->
            CultureRowItem(cultureData)
        }
    }
}

@Composable
fun CultureRowItem(data : CulturalEventResponseData) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(8.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(
            modifier = Modifier.wrapContentSize(),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                modifier = Modifier.wrapContentSize(),
                painter = rememberAsyncImagePainter(data.mainImg),
                contentDescription = "event main image"
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(data.title, style = TextStyle(color = White, fontSize = 16.sp))
            }
        }
    }
}



/*
@Preview(showBackground = true)
@Composable
fun preView() {
    //val imgViewModel: ImgViewModel by viewModels()
    // 더미 ViewModel 인스턴스 생성
    val viewModel = CulturalEventDataViewModel(ApiService)

    // 미리보기에 사용할 더미 데이터 생성
    val dummyData = listOf(
        CulturalEventResponseData(*/
/* dummy data *//*
),
        CulturalEventResponseData(*/
/* dummy data *//*
),
        CulturalEventResponseData(*/
/* dummy data *//*
)
    )

    // 미리보기 화면
    MyComposeUiTestTheme {
        HomeScreen(viewModel = CulturalEventDataViewModel(apiService = ApiService?))
    }
}*/
