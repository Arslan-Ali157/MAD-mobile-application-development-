package com.example.task5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.task5.ui.theme.Task5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val madLibManager = MadLibManager(this)
        madLibManager.loadStory(R.raw.tarzan)

        enableEdgeToEdge()
        setContent {
            Task5Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MadLibApp(madLibManager, Modifier.padding(innerPadding))
                }
            }
        }
    }
}

enum class Screen {
    Welcome, FillWords, Story
}

@Composable
fun MadLibApp(madLibManager: MadLibManager, modifier: Modifier = Modifier) {
    var currentScreen by remember { mutableStateOf(Screen.Welcome) }

    when (currentScreen) {
        Screen.Welcome -> WelcomeScreen(
            modifier = modifier,
            onStart = { currentScreen = Screen.FillWords }
        )
        Screen.FillWords -> FillWordsScreen(
            madLibManager = madLibManager,
            modifier = modifier,
            onComplete = { currentScreen = Screen.Story }
        )
        Screen.Story -> StoryScreen(
            madLibManager = madLibManager,
            modifier = modifier,
            onRestart = {
                madLibManager.reset()
                currentScreen = Screen.Welcome
            }
        )
    }
}

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier, onStart: () -> Unit) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "MAD LIB",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        // Logo Placeholder simulating the image in the prompt
        Surface(
            modifier = Modifier.size(180.dp).padding(16.dp),
            color = Color.White,
            border = androidx.compose.foundation.BorderStroke(2.dp, Color.Black)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("MAD", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
                Text("☺", fontSize = 60.sp)
                Text("LIBS", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
            }
        }
        
        Text(
            text = "Welcome to Mad Libs! I will ask you to fill in words to complete a story. You won't be able to see the whole story until you are done filling in all the missing words. That's part of the fun. Click below to get started...",
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(top = 24.dp, bottom = 32.dp)
        )
        
        Button(
            onClick = onStart,
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray, contentColor = Color.Black),
            modifier = Modifier.fillMaxWidth().height(60.dp)
        ) {
            Text("GET STARTED!", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun FillWordsScreen(
    madLibManager: MadLibManager,
    modifier: Modifier = Modifier,
    onComplete: () -> Unit
) {
    val placeholders = madLibManager.getPlaceholders()
    var currentIndex by remember { mutableIntStateOf(0) }
    var textInput by remember { mutableStateOf("") }

    val currentPlaceholder = placeholders.getOrNull(currentIndex) ?: ""
    val wordsLeft = placeholders.size - currentIndex

    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Fill in the words to\ncomplete the story!",
            fontSize = 28.sp,
            lineHeight = 34.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
        )
        
        Text(
            text = "$wordsLeft word(s) left",
            modifier = Modifier.align(Alignment.End).padding(bottom = 8.dp)
        )
        
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = textInput,
                onValueChange = { textInput = it },
                placeholder = { Text(currentPlaceholder) },
                modifier = Modifier.weight(1f),
                singleLine = true
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    if (textInput.isNotBlank()) {
                        madLibManager.fillPlaceholder(currentIndex, textInput)
                        textInput = ""
                        if (currentIndex + 1 < placeholders.size) {
                            currentIndex++
                        } else {
                            onComplete()
                        }
                    }
                },
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray, contentColor = Color.Black)
            ) {
                Text("OK")
            }
        }
        
        Text(
            text = "please type a/an $currentPlaceholder",
            fontSize = 12.sp,
            modifier = Modifier.align(Alignment.End).padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Surface(
            color = Color.Gray,
            shape = CircleShape,
            modifier = Modifier.padding(bottom = 32.dp)
        ) {
            Text(
                text = "Great! Keep going!",
                color = Color.White,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
            )
        }
    }
}

@Composable
fun StoryScreen(
    madLibManager: MadLibManager,
    modifier: Modifier = Modifier,
    onRestart: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Your Mad Lib Story!",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp, bottom = 24.dp)
        )

        Box(
            modifier = Modifier.weight(1f).fillMaxWidth()
        ) {
            Text(
                text = madLibManager.getCompletedStory(),
                fontSize = 16.sp,
                lineHeight = 22.sp,
                modifier = Modifier.verticalScroll(rememberScrollState())
            )
        }

        Button(
            onClick = onRestart,
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray, contentColor = Color.Black),
            modifier = Modifier.padding(top = 24.dp, bottom = 16.dp).fillMaxWidth()
        ) {
            Text("MAKE ANOTHER STORY", fontWeight = FontWeight.Bold)
        }
    }
}
