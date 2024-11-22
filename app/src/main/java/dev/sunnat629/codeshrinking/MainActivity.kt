package dev.sunnat629.codeshrinking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import dev.sunnat629.codeshrinking.ui.theme.CodeShrinkingTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CodeShrinkingTheme {
                GitHubUserScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GitHubUserScreen() {
    val coroutineScope = rememberCoroutineScope()
    var username by remember { mutableStateOf("") }
    var user by remember { mutableStateOf<User?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("GitHub User Profile") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("GitHub Username") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    coroutineScope.launch {
                        try {
                            user = RetrofitInstance.api.getUser(username)
                            errorMessage = null
                        } catch (e: Exception) {
                            errorMessage = "Failed to fetch user data: ${e.message}"
                            user = null
                        }
                    }
                }
            ) {
                Text("Fetch Profile")
            }
            Spacer(modifier = Modifier.height(16.dp))

            if (user != null) {
                UserProfile(user!!)
            } else if (errorMessage != null) {
                Text(text = errorMessage!!, color = Color.Red, textAlign = TextAlign.Center)
            }
        }
    }
}

@Composable
fun UserProfile(user: User) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            model = user.profilePic,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = user.name ?: "Unknown", style = MaterialTheme.typography.headlineMedium)
        Text(text = user.email ?: "No Email", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Public Repos: ${user.publicRepos}")
        Text("Public Gists: ${user.publicGists}")
        Text("Followers: ${user.followers}")
        Text("Following: ${user.following}")
    }
}