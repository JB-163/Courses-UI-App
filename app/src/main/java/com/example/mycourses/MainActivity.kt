package com.example.mycourses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mycourses.ui.theme.MyCoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCoursesTheme {

                Box(modifier = Modifier.fillMaxSize()) {

                    // Performing Navigation
                    // 1. Create Nav Controller
                    val navController = rememberNavController()

                    // 2. Create NavHost
                    NavHost(navController = navController, startDestination = "Home Screen" ) {

                        // 3. Create Nav Graph
                        composable("Home Screen") {
                            HomeScreen(onDetailsClick = {
                                    title ->  navController.navigate("details/title=$title")
                            }, onAboutClick = {
                                navController.navigate("About Screen")
                            })
                        }

                        composable("About Screen") {
                            AboutScreen(
                                onNavigateBack = {navController.popBackStack()}
                            )
                        }

                        composable("details/title={title}",
                            arguments = listOf(
                                navArgument("title") {
                                    type = NavType.StringType
                                    nullable = true
                                }
                            ),
                        ){
                                backStackEntry -> val arguments = requireNotNull(backStackEntry.arguments)
                            val title = arguments.getString("title")
                            if (title != null) {
                                DetailsScreen(title = title, onNavigateBack = {
                                    navController.popBackStack()
                                })
                            }
                        }


                    }
                }

            }
        }
    }
}


// Screen 1
@Composable
fun HomeScreen(
    onDetailsClick : (title : String) -> Unit,
    onAboutClick: () -> Unit
) {
    Scaffold {
        paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            item {
                HomeAppBar(onAboutClick)
            }
            item {
                Spacer(modifier = Modifier.height(25.dp))
            }
            items(allCourses) {
                item ->  CourseCard(item,onClick = {onDetailsClick(item.title)})
            }
        }
    }
}

@Composable
private fun HomeAppBar(onAboutClick : () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Text(text = "My Courses",
            style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.weight(1f))

        TextButton(onClick = onAboutClick) {
            Text(text = "About")
        }
    }
}

// Screen 2
@Composable
fun AboutScreen(onNavigateBack : () -> Unit) {

    val udemyLink = LocalUriHandler.current

    Scaffold {
            paddingValues ->  Column(modifier = Modifier.padding(paddingValues)) {
        AboutAppBar(title = "About",onNavigateBack)
        Spacer(modifier = Modifier.height(20.dp))
        Column(modifier = Modifier
            .padding(8.dp)) {
            Text(text = "This App is a demonstration on how to perform " +
                    "navigation in jetpack compose")

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = { udemyLink.openUri("https://www.udemy.com/") }) {
                Text(text = "view udemy page")
            }
        }
            }
    }
}

@Composable
fun AboutAppBar(title : String, onNavigateBack: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 10.dp)) {

        IconButton(onClick = onNavigateBack) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Go Back" )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Text(text = title, style = MaterialTheme.typography.titleSmall)

    }
}

// Screen 3
@Composable
fun DetailsScreen(title: String, onNavigateBack: () -> Unit) {

    val courseChosen = allCourses.first { it.title == title }
    Scaffold {
            paddingValues -> Column(modifier = Modifier.padding(paddingValues)) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 10.dp)) {
            IconButton(onClick = onNavigateBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Go Back" )
            }
        }
        Image(painterResource(id = courseChosen.image), contentDescription ="image",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f),
            contentScale = ContentScale.Crop)

        Spacer(modifier = Modifier.height(20.dp))

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)) {

            Text(text = courseChosen.title, style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = courseChosen.desc, style = MaterialTheme.typography.bodyMedium)

        }
    }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseCard(item : Courses,onClick : () -> Unit) {
    Card(modifier = Modifier
        .padding(horizontal = 16.dp, vertical = 8.dp)
        .fillMaxWidth(),
        onClick = onClick) {
        Column {
            Image(painterResource(item.image), contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(16f / 9f),
                contentScale = ContentScale.Crop)
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)) {
                Text(text = item.title)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = item.desc, maxLines = 1, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}


