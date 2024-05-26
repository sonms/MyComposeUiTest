package com.example.mycomposeuitest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.blue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mycomposeuitest.navigation.cultural_event.CultureEventScreen
import com.example.mycomposeuitest.navigation.home.HomeScreen
import com.example.mycomposeuitest.navigation.safety.SafetyScreen
import com.example.mycomposeuitest.ui.theme.MyComposeUiTestTheme

const val HOME = "HOME"
const val SAFETY = "SAFETY"
const val CULTURE = "CULTURE"
sealed class BottomNavItem(
    val title: String, val icon: Int, val screenRoute: String
) {
    data object Home : BottomNavItem("홈", R.drawable.home, HOME)
    data object Culture : BottomNavItem("문화", R.drawable.culture, CULTURE)
    data object Safety : BottomNavItem("안전", R.drawable.safety, SAFETY)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeUiTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    //Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun MainScreenView() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationMenu(
            navController = navController)
        }
    ) {
        Box(Modifier.padding(it)){//화면 contentview(framelayout)
            NavigationGraph(navController = navController)
        }
    }
}

@Composable
fun BottomNavigationMenu(navController: NavHostController) {
    val items = listOf<BottomNavItem>(
        BottomNavItem.Home,
        BottomNavItem.Culture,
        BottomNavItem.Safety
    )

    androidx.compose.material3.NavigationBar (
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        containerColor = Color.White,
        contentColor = Color.Black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route


        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.screenRoute,
                onClick = {
                    navController.navigate(item.screenRoute) {
                        //같은 탭을 한 번 더 탭하면 또 열리는 문제(백스택에 계속쌓이는)를 해결하기 위함
                        //탭을 선택시 백스택에 대상이 쌓이지않도록 그래프 시작대상으로 팝업
                        //아래 코드는 닫힐 때 어디로 이동될지 정해줌 여기서는 그래프의 가장시작점
                        //그래서 시작점 backstack은 없으므로 두 번 뒤로가기 시 앱종료
                        popUpTo(navController.graph.startDestinationId) {saveState = true}
                        //savestate특성에 의해 저장된 상태 복원여부 결정
                        //상태가 저장되지 않은경우 아무의미없음
                        //동일탭 하면 다시 로드x하고 이전 데이터와 사용자 상태 화면유지
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                //enabled = ,
                icon = {
                    Icon(
                        painterResource(id = item.icon),
                        contentDescription = item.title,
                        //tint = if (onClickedNavigationItem) Color.Blue else Color.Black
                    )},
                label = {
                    Text(
                        modifier = Modifier.wrapContentSize(),
                        text = item.title,
                        //color = if (onClickedNavigationItem) Color.Blue else Color.Black
                    )
                },
                colors = NavigationBarItemDefaults
                    .colors(
                        //여기가 이제 selected에 따른 icon색변경
                        selectedIconColor = Blue,
                        //누를 때 나오던 둥근 그림자 효과
                        //현재는 표면과 색을 동일시하여 이펙트 색없앰
                        indicatorColor = MaterialTheme.colorScheme.surface //MaterialTheme.colorScheme.surfaceColorAtElevation(LocalAbsoluteTonalElevation.current)
                    )
            )
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavItem.Home.screenRoute) {
        //탐색 시에 인수를 경로와 함께 전달하려면 "route/{argument}" 패턴에 따라 경로에 인수를 추가
        //route - BottomNavItem.Home.screenRoute
        //argument를 통해 특정 아이템을 클릭했는지 정보를 담을 수있음
        composable(BottomNavItem.Home.screenRoute) {
            HomeScreen()
        }
        composable(BottomNavItem.Culture.screenRoute) {
            CultureEventScreen()
        }
        composable(BottomNavItem.Safety.screenRoute) {
            SafetyScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun preView() {
    MyComposeUiTestTheme {
        MainScreenView()
    }
}

/*@Composable
fun TextTest() {
    Text(
        //foldin:modifier = Modifier.foldIn(animationSpec = tween(1000)) 점차나타나는효과
        //foldout:modifier = modifier = Modifier.foldOut(animationSpec = tween(1000)) 점차사라지는효과
        //then : 다른 Modifier와 조합하여 순차적으로 적용될 수 있도록 합니다. 즉, 첫 번째 Modifier를 적용한 후에 두 번째 Modifier를 적용합니다.
        //여러 가지 Modifier를 연결하여 사용할 때 특히 유용합니다.
        //modifier = Modifier.then(),
        *//*text: String,
    modifier: Modifier,
    color: Color,
    fontSize: TextUnit,
    fontStyle: FontStyle?,
    fontWeight: FontWeight?,
    fontFamily: FontFamily?,
    letterSpacing: TextUnit,
    textDecoration: TextDecoration?,
    textAlign: TextAlign?,
    lineHeight: TextUnit,
    overflow: TextOverflow,
    softWrap: Boolean,
    maxLines: Int,
    minLines: Int,
    onTextLayout: (TextLayoutResult) -> Unit,
    style: TextStyle*//*
        modifier = Modifier.wrapContentSize().padding(8.dp),
        text = "안녕",


    )
}

@Preview(showBackground = true)
@Composable
fun preView() {
    MyComposeUiTestTheme {
        TextTest()
    }
}*/
/*
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyComposeUiTestTheme {
        Greeting("Android")
    }
}*/
