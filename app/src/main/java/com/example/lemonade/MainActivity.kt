package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MakeLemonade()
                }
            }
        }
    }
}

@Composable
fun MakeLemonade() {
    var currentStep by remember {
        mutableStateOf(1)
    }
    var squeezeCount by remember {
        mutableStateOf(0)
    }
    when (currentStep) {
        1 -> LemonadeImageAndText(
            textLabelResourceId = (R.string.lemon_tree),
            drawableResourceId = (R.drawable.lemon_tree),
            contentDescriptionResourceId = (R.string.select_lemon),
            onImageClick = {
                currentStep = 2
                squeezeCount = (2..4).random()
            })
        2 -> LemonadeImageAndText(
            textLabelResourceId = (R.string.lemon),
            drawableResourceId = (R.drawable.lemon_squeeze),
            contentDescriptionResourceId = (R.string.lemon),
            onImageClick = {
                squeezeCount--
                if (squeezeCount == 0) {
                    currentStep = 3
                }
            })
        3 -> LemonadeImageAndText(
            textLabelResourceId = (R.string.glass),
            drawableResourceId = (R.drawable.lemon_drink),
            contentDescriptionResourceId = (R.string.drink_it),
            onImageClick = { currentStep = 4 })
        4 -> LemonadeImageAndText(
            textLabelResourceId = (R.string.empti),
            drawableResourceId = (R.drawable.lemon_restart),
            contentDescriptionResourceId = (R.string.empti),
            onImageClick = { currentStep = 1 })
    }
}

@Composable
fun LemonadeImageAndText(
    textLabelResourceId: Int,
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(textLabelResourceId),
            fontSize = 18.sp
        )
        Image(
            painter = painterResource(drawableResourceId),
            contentDescription = stringResource(contentDescriptionResourceId),
            modifier = Modifier
                .wrapContentSize()
                .clickable(
                    onClick = onImageClick
                )
                .padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        MakeLemonade()
    }
}