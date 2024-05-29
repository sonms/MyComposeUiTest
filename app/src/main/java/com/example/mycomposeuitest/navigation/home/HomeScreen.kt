package com.example.mycomposeuitest.navigation.home

import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.mycomposeuitest.MainScreenView
import com.example.mycomposeuitest.R
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Column (modifier = Modifier.wrapContentSize()) {
                Text(text = "지정한 지역 문화/관광 체험")
                initSetCultureRow(list = data!!)
            }

            Column (modifier = Modifier.wrapContentSize()) {
                Text(text = "무작위 안전")
                initSetCultureRow(list = data!!)
            }
        }
    } else {
        // 데이터 로딩 중일 때 표시할 내용
        CircularProgressIndicator()
    }

}


@Composable
fun initSetCultureRow(list: List<CulturalEventResponseData>) {
    LazyRow() {
        items(list) { cultureData ->
            CultureRowItem(cultureData)
        }
    }
}

@Composable
fun CultureRowItem(data : CulturalEventResponseData) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(data.mainImg)
            .size(coil.size.Size.ORIGINAL) // Set the target size to load the image at.
            .build()
    )
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(8.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.wrapContentSize(),
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                modifier = Modifier.wrapContentSize().width(200.dp).height(200.dp),
                painter = rememberAsyncImagePainter(painter),
                contentScale = ContentScale.Crop,
                contentDescription = "event main image"
            )
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(data.title, style = TextStyle(color = White, fontSize = 16.sp))
            }
        }
    }
}

//더미

@Composable
fun dummyHomeScreen(viewModel: CulturalEventDataViewModel?) { //등록한 위치를 기준으로 문화 추천 + 랜덤 안전 추천?
    /*val data by viewModel.data.observeAsState()
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
    viewModel.fetchData(culturalEventRequestData)*/
    val data = arrayListOf<String>("dkdkd", "ddldld", "ASddas", "asdf", "bbbbvbvx", "bmxmxmz")

    // 데이터 로드를 시작합니다.
    if (data != null) {
        //Text(text = "ID: ${data!!.id}, Name: ${data!!.name}")
        /*LaunchedEffect(Unit) {
            viewModel.fetchData(culturalEventRequestData)
        }*/
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Column (modifier = Modifier.wrapContentSize()) {
                Text(
                    modifier = Modifier.padding(bottom = 10.dp),
                    text = "지정한 지역 문화/관광 체험")
                dummySetCultureRow(list = data)
            }

            Column (modifier = Modifier.wrapContentSize().padding(top = 20.dp)) {
                Text(text = "무작위 안전")
                dummySetCultureRow(list = data)
            }
        }
    } else {
        // 데이터 로딩 중일 때 표시할 내용
        CircularProgressIndicator()
    }

}

@Composable
fun dummySetCultureRow(list: List<String>) {
    LazyRow() {
        items(list) { cultureData ->
            dummyCultureRowItem(cultureData)
        }
    }
}

@Composable
fun dummyCultureRowItem(data : String) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data("https://culture.seoul.go.kr/cmmn/file/getImage.do?atchFileId=59da975904c94facbf6989987c187525&amp;thumb=Y")
            .size(coil.size.Size.ORIGINAL) // Set the target size to load the image at.
            .build()
    )
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(8.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.wrapContentSize(),
            verticalArrangement = Arrangement.Center,

        ) {
            Image(
                modifier = Modifier.wrapContentSize().width(120.dp) // 이미지의 너비 설정
                    .height(120.dp).padding(8.dp), // 이미지의 높이 설정,
                painter = painterResource(R.drawable.mainjelly),//rememberAsyncImagePainter(painter),//painterResource(R.drawable.mainjelly),//painter,//rememberAsyncImagePainter(R.drawable.mainjelly),
                contentScale = ContentScale.Crop,
                contentDescription = "event main image"
            )
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(data, style = TextStyle(color = White, fontSize = 16.sp))
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun preView() {
    MyComposeUiTestTheme {
        //val imgViewModel: CulturalEventDataViewModel by viewmodels()
        dummyHomeScreen(viewModel = null)
    }
}
