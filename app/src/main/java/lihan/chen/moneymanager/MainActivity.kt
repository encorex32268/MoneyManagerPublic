package lihan.chen.moneymanager

import android.app.AlarmManager
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import lihan.chen.moneymanager.feature.add.presentation.AddNewExpenseScreen
import lihan.chen.moneymanager.feature.add.presentation.AddNewExpenseViewModel
import lihan.chen.moneymanager.feature.analysis.ui.AnalysisScreen
import lihan.chen.moneymanager.feature.analysis.ui.AnalysisViewModel
import lihan.chen.moneymanager.feature.chart.presentation.ChartScreen
import lihan.chen.moneymanager.feature.chart.presentation.ChartViewModel
import lihan.chen.moneymanager.feature.core.domain.navigation.Route
import lihan.chen.moneymanager.feature.core.domain.util.UiEvent
import lihan.chen.moneymanager.feature.core.presentation.bottomnavigation.BottomNavigationItem
import lihan.chen.moneymanager.feature.core.presentation.components.Texts
import lihan.chen.moneymanager.feature.core.util.MoneyManagerDateUtils
import lihan.chen.moneymanager.feature.edit.presentation.EditExpenseScreen
import lihan.chen.moneymanager.feature.edit.presentation.EditExpenseViewModel
import lihan.chen.moneymanager.feature.home.presentation.HomeScreen
import lihan.chen.moneymanager.feature.home.presentation.HomeViewModel
import lihan.chen.moneymanager.feature.profile.ui.ProfileScreen
import lihan.chen.moneymanager.feature.profile.ui.ProfileViewModel
import lihan.chen.moneymanager.feature.profile.ui.lightdarkmode.LightDarkModeScreen
import lihan.chen.moneymanager.feature.profile.ui.lightdarkmode.LightDarkModeViewModel
import lihan.chen.moneymanager.feature.profile.ui.lightdarkmode.Mode
import lihan.chen.moneymanager.feature.profile.ui.notification.NotificationScreen
import lihan.chen.moneymanager.feature.profile.ui.notification.NotificationViewModel
import lihan.chen.moneymanager.feature.settings.ui.SettingsScreen
import lihan.chen.moneymanager.ui.theme.MoneyManagerTheme

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = hiltViewModel<LightDarkModeViewModel>()
            val themeState by viewModel.state.collectAsState()
            val systemUiController = rememberSystemUiController()
            systemUiController.setStatusBarColor(
                color = if (themeState.mode is Mode.Light){
                    Color.White
                }else {
                    Color.Black
                }
            )
            MoneyManagerTheme(
                darkTheme = themeState.mode is Mode.Dark
            ) {
                val hostState = remember {
                    SnackbarHostState()
                }
                val naviController = rememberNavController()
                val currentBackStack by naviController.currentBackStackEntryAsState()
                val bottomNavigationItems = listOf(
                    BottomNavigationItem(
                        title = Route.Home,
                        titleResId = R.string.home,
                        selectedIcon = ImageVector.vectorResource(R.drawable.baseline_receipt_24_filled),
                        unSelectedIcon = ImageVector.vectorResource(R.drawable.baseline_receipt_24_outline),
                    ),
                    BottomNavigationItem(
                        title = Route.Chart,
                        titleResId = R.string.chart,
                        selectedIcon = ImageVector.vectorResource(R.drawable.baseline_pie_chart_24_filled),
                        unSelectedIcon = ImageVector.vectorResource(R.drawable.basline_pie_chart_24_outline),
                    ),
                    BottomNavigationItem(
                        title = Route.Analysis,
                        titleResId = R.string.analysis,
                        selectedIcon = ImageVector.vectorResource(R.drawable.baseline_insert_chart_24_fill),
                        unSelectedIcon = ImageVector.vectorResource(R.drawable.baseline_insert_chart_24_outline)
                    ),
                    BottomNavigationItem(
                        title = Route.Profile,
                        titleResId = R.string.profile,
                        selectedIcon = Icons.Filled.Person,
                        unSelectedIcon = Icons.Outlined.Person,
                    ),

                )
                var selectedIndex by rememberSaveable {
                    mutableIntStateOf(0)
                }
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    snackbarHost = {
                        SnackbarHost(hostState)
                    },
                    bottomBar = {
                        val currentRoute = currentBackStack?.destination?.route?:""
                        if (Route.checkIsMainPage(currentRoute)){
                            NavigationBar {
                                bottomNavigationItems.forEachIndexed { index, bottomNavigationItem ->
                                    NavigationBarItem(
                                        selected = selectedIndex == index,
                                        onClick = {
                                            selectedIndex = index
                                            naviController.navigate(bottomNavigationItem.title)
                                        },
                                        icon = {
                                            Icon(
                                                imageVector = if(selectedIndex == index)
                                                    bottomNavigationItem.selectedIcon
                                                else bottomNavigationItem.unSelectedIcon,
                                                contentDescription = null,
                                                tint = MaterialTheme.colorScheme.onSurface
                                            )
                                        },
                                        label = {
                                            Texts.BodySmall(
                                                text = stringResource(id = bottomNavigationItem.titleResId)
                                            )

                                        }
                                    )
                                }
                            }

                        }
                    }
                ) { paddingValues ->
                    NavHost(
                        navController =naviController,
                        startDestination = Route.Home
                    ){
                        composable(route = Route.Home){
                            val viewModel = hiltViewModel<HomeViewModel>()
                            val state by viewModel.state.collectAsState()
                            LaunchedEffect(Unit){
                                viewModel.getData()
                            }
                            HomeScreen(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(paddingValues),
                                state = state,
                                onNavigateToAdd = {
                                    naviController.navigate(
                                        route = Route.AddNew+
                                                "/${state.nowDateYear}/"+
                                                "${state.nowDateMonth}/"+
                                                "${state.nowDateDayOfMonth}/"+
                                                "${-1}"
                                    )
                                },
                                onNavigateToEdit = {
                                    it.id?.let { expenseId ->
                                        naviController.navigate(
                                            route = Route.Edit + "/${expenseId}"
                                        )
                                    }
                                },
                                onEvent = viewModel::onEvent
                            )
                        }
                        composable(
                            route = Route.AddNew + "/{year}/{month}/{day}/{id}",
                            arguments = listOf(
                                navArgument("id"){
                                    defaultValue = -1
                                    type = NavType.IntType
                                }
                            ),
                            enterTransition = {
                                slideIntoContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Up,
                                    animationSpec = tween(300)
                                )
                            },
                            exitTransition = {
                               slideOutOfContainer(
                                   AnimatedContentTransitionScope.SlideDirection.Down,
                                   animationSpec = tween(300)
                               )
                            },
                        ){backStackEntry->
                            val viewModel = hiltViewModel<AddNewExpenseViewModel>()
                            val state by viewModel.state.collectAsState()
                            LaunchedEffect(Unit){
                                backStackEntry.arguments?.let {
                                    viewModel.setDate(
                                        it.getString("year"),
                                        it.getString("month"),
                                        it.getString(("day")),
                                        it.getInt("id")
                                    )
                                }
                            }
                            LaunchedEffect(key1 = viewModel){
                                viewModel.uiEvent.collectLatest {
                                    when(it){
                                        is UiEvent.Failed -> Unit
                                        UiEvent.Success -> {
//                                            viewModel.resetState()
                                            delay(300L)
                                            naviController.popBackStack()
                                        }

                                        else -> Unit
                                    }
                                }
                            }
                            AddNewExpenseScreen(
                                state = state,
                                onEvent = viewModel::onEvent,
                                theme = themeState.mode?:Mode.Light
                            )
                        }
                        composable(
                            route = Route.Edit + "/{expenseId}",
                            arguments = listOf(
                                navArgument(
                                    "expenseId"
                                ){
                                    defaultValue = 0
                                    type = NavType.IntType
                                }
                            )
                        ){backStackEntry ->
                            val viewModel = hiltViewModel<EditExpenseViewModel>()
                            val state by viewModel.state.collectAsState()
                            LaunchedEffect(viewModel){
                                viewModel.uiEvent.collectLatest {
                                    when(it){
                                        is UiEvent.Failed -> Unit
                                        UiEvent.Success -> {
                                            naviController.popBackStack()
                                        }

                                        else -> Unit
                                    }
                                }
                            }
                            LaunchedEffect(Unit){
                                backStackEntry.arguments?.let {
                                    viewModel.setState(
                                       it.getInt("expenseId")
                                    )
                                }
                            }
                            EditExpenseScreen(
                                state = state,
                                onDelete = viewModel::onDelete,
                                onEdit = {
                                    state.currentExpense?.let {
                                        val timestamp = it.timestamp
                                        val dateString = MoneyManagerDateUtils.getStringDateFromLongWithoutDisplayName(timestamp)
                                        val year = dateString.split("/")[0]
                                        val month = dateString.split("/")[1]
                                        val dayOfMonth = dateString.split("/")[2]
                                        naviController.navigate(
                                            route = Route.AddNew+
                                                    "/${year}/"+
                                                    "${month}/"+
                                                    "${dayOfMonth}/"+
                                                    "${it.id}"
                                        )

                                    }
                                }
                            )
                        }
                        composable(
                            route = Route.Chart
                        ){
                            val viewModel = hiltViewModel<ChartViewModel>()
                            val state by viewModel.state.collectAsState()
                            LaunchedEffect(Unit){
                                viewModel.getDataFromDate()
                            }
                            ChartScreen(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(paddingValues),
                                state = state,
                                onDatePick = viewModel::getDataFromDate,
                                onChartClick = viewModel::onChartClick
                            )

                        }
                        composable(
                            route = Route.Analysis
                        ){
                            val viewModel = hiltViewModel<AnalysisViewModel>()
                            val state by viewModel.state.collectAsState()
                            AnalysisScreen(
                                state = state,
                                onEvent = viewModel::onEvent
                            )
                        }
                        composable(
                            route = Route.Settings
                        ){
                            SettingsScreen()
                        }
                        composable(
                            route = Route.Profile
                        ){
                            val defaultError = stringResource(id = R.string.defaultError)
                            val defaultSuccess = stringResource(id = R.string.outputSuccess)
                            val viewModel = hiltViewModel<ProfileViewModel>()

                            LaunchedEffect(viewModel){
                                viewModel.uiEvent.collectLatest {
                                    when(it){
                                        is UiEvent.Failed ->{
                                            hostState.showSnackbar(it.errorMessage?:defaultError)
                                        }
                                        UiEvent.Success -> {
                                            hostState.showSnackbar(defaultSuccess)
                                        }
                                    }
                                }
                            }
                            ProfileScreen(
                                onGoToNotification = {
                                    naviController.navigate(Route.Notification)
                                },
                                onOutputCSVClick = viewModel::csvOutput,
                                onGoToLightDarkMode = {
                                    naviController.navigate(Route.LightDarkMode)
                                }
                            )
                        }
                        composable(
                            route = Route.Notification
                        ){
                            val viewModel = hiltViewModel<NotificationViewModel>()
                            val state by viewModel.state.collectAsState()
                            NotificationScreen(
                                state = state,
                                onEvent = viewModel::onEvent,
                                onGoToSetting = {
                                    startActivity(
                                        Intent(
                                            Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM,
                                            Uri.parse("package:${packageName}")
                                        ).apply {
                                            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                        }
                                    )
                                    Toast.makeText(
                                        this@MainActivity,
                                        resources.getString(R.string.alarm_permission),
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            )
                        }
                        composable(
                            route = Route.LightDarkMode
                        ){
                            LightDarkModeScreen(
                                state = themeState,
                                onClick =viewModel::onClick
                            )
                        }

                    }
                }
            }
        }
    }
}

