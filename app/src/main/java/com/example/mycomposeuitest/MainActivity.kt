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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
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
        Box(Modifier.padding(it)){//화면
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
            .padding(8.dp),
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
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        painterResource(id = item.icon),
                        contentDescription = item.title
                    )},
                label = {
                    Text(
                        modifier = Modifier.wrapContentSize(),
                        text = item.title)
                }
            )
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavItem.Home.screenRoute) {
        composable(BottomNavItem.Home.screenRoute) {
            //CalendarScreen()
        }
        composable(BottomNavItem.Culture.screenRoute) {
            //TimelineScreen()
        }
        composable(BottomNavItem.Safety.screenRoute) {
            //AnalysisScreen()
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
