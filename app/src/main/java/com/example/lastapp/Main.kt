package com.example.lastapp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lastapp.ui.calendar.CalendarScreen
import com.example.lastapp.ui.home.HomeScreen
import com.example.lastapp.ui.navigation.AppNavegation
import com.example.lastapp.ui.navigation.Calendar
import com.example.lastapp.ui.navigation.First
import com.example.lastapp.ui.navigation.Notification
import com.example.lastapp.ui.navigation.tabRowScreens
import com.example.lastapp.ui.notification.NotificationScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            enableEdgeToEdge()
                MyApp(modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    Surface {
        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination
        val currentScreen =
            tabRowScreens.find { it.route == currentDestination?.route } ?: First
        Scaffold(
            bottomBar = {
                TabRow(
                    allScreens = tabRowScreens,
                    onTabSelected = { newScreen ->
                        navController.navigateSingleTopTo(newScreen.route)
                    },
                    currentScreen = currentScreen,
                )
            },
        ) { paddingValues ->
            AppNavHost(
                navController = navController, modifier = Modifier.padding(paddingValues)
            )
        }
    }
}

@Composable
fun TabRow(
    allScreens: List<AppNavegation>,
    onTabSelected: (AppNavegation) -> Unit,
    currentScreen: AppNavegation
) {
    Surface(
        Modifier
            .height(TabHeight)
            .fillMaxWidth()

    ) {
        Row(Modifier
            .selectableGroup()
            .background(Color.DarkGray)) {
            allScreens.forEach { screen ->
                AppTab(
                    text = screen.route,
                    onSelected = { onTabSelected(screen) },
                    selected = currentScreen == screen
                )
            }
        }
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController, modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController, startDestination = First.route, modifier = modifier
    ) {
        composable(route = First.route) {
            HomeScreen(onClickSeeCalendar = {
                navController.navigateSingleTopTo(Calendar.route)
            }, onClickSeeNotifies = {
                navController.navigateSingleTopTo(Notification.route)
            })
        }
        composable(route = Calendar.route) {
            CalendarScreen(
                onClickSeeNotifies = {
                    navController.navigateSingleTopTo(Notification.route)
                },
                onClickSeeFirst = {
                    navController.navigateSingleTopTo(First.route)
                },
            )
        }
        composable(route = Notification.route) {
            NotificationScreen(onClickSeeCalendar = {
                navController.navigateSingleTopTo(Calendar.route)
            }, onClickSeeFirst = {
                navController.navigateSingleTopTo(First.route)
            })
        }

    }
}

@Composable
private fun AppTab(
    text: String,
    onSelected: () -> Unit,
    selected: Boolean
) {
    val color = Color(Color.hashCode())
    val durationMillis = if (selected) TabFadeInAnimationDuration else TabFadeOutAnimationDuration
    val animSpec = remember {
        tween<Color>(
            durationMillis = durationMillis,
            easing = LinearEasing,
            delayMillis = TabFadeInAnimationDelay
        )
    }
    val tabTintColor by animateColorAsState(
        targetValue = if (selected) color else color.copy(alpha = InactiveTabOpacity),
        animationSpec = animSpec
    )
    Row(
        modifier = Modifier
            .padding(16.dp)
            .animateContentSize()
            .height(TabHeight)
            .selectable(
                selected = selected,
                onClick = onSelected,
                role = Role.Tab,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(
                    bounded = false,
                    radius = Dp.Unspecified,
                    color = Color.Red
                )
            )
            .clearAndSetSemantics { contentDescription = text }
    ) {Modifier.padding(10.dp)
        if (selected) {
            Spacer(Modifier.width(12.dp))
            Text("")
        }
    }
}

private val TabHeight = 56.dp
private const val InactiveTabOpacity = 0.60f

private const val TabFadeInAnimationDuration = 150
private const val TabFadeInAnimationDelay = 100
private const val TabFadeOutAnimationDuration = 100
@Composable
private fun Greetings(
    days: Int,
    modifier: Modifier = Modifier,
    names: List<String> = List(days) { "Hello $it" }
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}

@Composable
private fun Greeting(name: String, modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(name)
    }
}

@Composable
private fun CardContent(name: String) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(
                text = name, style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            if (expanded) {
                Text(
                    text = ("Composem ipsum color sit lazy, " +
                            "padding theme elit, sed do bouncy. ").repeat(4),
                )
            }
        }
        IconButton(onClick = { expanded = !expanded }) {
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) = this.navigate(route) {
    popUpTo(
        this@navigateSingleTopTo.graph.findStartDestination().id
    ) {
        saveState = true
    }
    launchSingleTop = true
    restoreState = true
}

