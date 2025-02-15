package com.example.fieldwise.ui.screen.lessons

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fieldwise.R
import com.example.fieldwise.ui.theme.FieldWiseTheme
import com.example.fieldwise.ui.theme.InterFontFamily
import com.example.fieldwise.ui.widget.MainButton
import com.example.fieldwise.ui.widget.MainButtonType
import com.example.fieldwise.ui.screen.profile_creation.globalUsername
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

@Composable
fun ExerciseCompleteScreen(modifier: Modifier = Modifier, Finish: () -> Unit, type: String?) {

    //animation for background image
    val backgroundAn = remember { Animatable(100f) }
    val checkAn = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        launch {  backgroundAn.animateTo(
            targetValue = 6000f,
            animationSpec = tween(
                durationMillis = 1500
            )
        ) }

        launch {  checkAn.animateTo(
            targetValue = 200f,
            animationSpec = tween(
                durationMillis = 1000
            )
        )}
    }

    //animation for streak

    val streakAn = remember { Animatable(1f) }

    LaunchedEffect(Unit) {
        streakAn.animateTo(
            targetValue = 1.3f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 900,
                    delayMillis = 0
                ), repeatMode = RepeatMode.Reverse
            )
        )
        streakAn.animateTo(
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 900,
                    delayMillis = 0
                ), repeatMode = RepeatMode.Reverse
            )
        )
    }

    //content
    Column(
        modifier = modifier
            .fillMaxSize().background(Color(0xFF073748))
            .padding(20.dp, 50.dp, 20.dp, 0.dp).testTag("CompleteExerciseScreen"),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize() // Make the Box take the full available size
        ) {
            // Background image (fixed)
            Image(
                painter = painterResource(id = R.drawable.bg1),
                contentDescription = "Complete background",
                modifier = Modifier
                    .size(backgroundAn.value.dp)
                    .fillMaxSize() // Make sure the image takes the full size of the Box
                    .align(Alignment.Center) // Center the image
            )
            Column(
                modifier = modifier.fillMaxHeight()
                    .padding(start = 20.dp, end = 20.dp, top = 170.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ){

                Image(
                    painter = painterResource(id=R.drawable.checkicon_blue),
                    contentDescription = "Check Icon Blue",
                    modifier = Modifier
                        .size(checkAn.value.dp)
                )
                Spacer(modifier = Modifier.height(75.dp))
                Text(
                    text = "Your exercise is complete",
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = InterFontFamily
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Almost there !",
                    color = Color(0x80FFFFFF),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = InterFontFamily
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
                Image(
                    painter = painterResource(id=R.drawable.streak_inc),
                    contentDescription = "Streak Increase by 1",
                    modifier = Modifier
                        .size(50.dp)
                        .graphicsLayer {
                            scaleX = streakAn.value
                            scaleY = streakAn.value
                        }
                )
                Spacer(modifier = Modifier.height(100.dp))
                MainButton(button = "Return to Home", onClick = {
                    val database = Firebase.database
                    val userListSnapshot = database.reference.child("Leaderboard")
                    userListSnapshot.get().addOnSuccessListener { dataSnapshot ->
                        dataSnapshot.children.forEach { userSnapshot ->
                            val name = userSnapshot.child("Name").getValue(String::class.java) ?: ""
                            if (globalUsername == name) {
                                val currentStreak = userSnapshot.child("Streak").getValue(Int::class.java) ?: 0
                                val updatedStreak = currentStreak + 1
                                userSnapshot.ref.child("Streak").setValue(updatedStreak)
                            }
                        }
                    }
                    Finish() }, mainButtonType = MainButtonType.GREEN, isEnable = true)

            }}

    }
}



@Preview(showBackground = true)
@Composable
fun ExerciseCompletePreview() {
    FieldWiseTheme {
        ExerciseCompleteScreen(
            Finish = {},
            type = ""
        )
    }
}

