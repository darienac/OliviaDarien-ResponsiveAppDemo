package com.example.oliviadarien_responsiveappdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.oliviadarien_responsiveappdemo.ui.theme.OliviaDarienResponsiveAppDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OliviaDarienResponsiveAppDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ResizerAppLayout(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun DemoHeader(label: String) {
    Text(label, fontSize=32.sp, fontWeight=FontWeight.Bold, lineHeight=40.sp, modifier=Modifier.padding(24.dp, 8.dp, 8.dp, 16.dp))
}

@Composable
fun ResizeableDemo() {
    Column(
        modifier=Modifier.fillMaxWidth()
    ) {
        DemoHeader("Content Goes Here")
    }
}

@Composable
fun ResizerSlider(label: String, subLabel: String, value: Float, onValueChange: (Float)->Unit) {
    Column() {
        Row() {
            Text(label, fontSize=24.sp, fontWeight=FontWeight.Bold, modifier=Modifier.padding(40.dp, 0.dp, 0.dp, 0.dp))
            Text(subLabel, fontSize=18.sp, modifier=Modifier.padding(20.dp, 0.dp, 0.dp, 0.dp))
        }
        Slider(value=value, onValueChange=onValueChange, modifier=Modifier.padding(24.dp, 8.dp))
    }
}

@Composable
fun ResizerAppLayout(modifier: Modifier = Modifier) {
    var resizeWidth by remember {mutableFloatStateOf(1f)}
    var resizeHeight by remember {mutableFloatStateOf(1f)}
    var boxSize by remember {mutableStateOf(IntSize.Zero)}

    Column(
        modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.secondaryContainer),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(modifier=Modifier.fillMaxWidth().weight(1f).background(MaterialTheme.colorScheme.secondaryContainer)) {
            Box(
                modifier=Modifier.fillMaxWidth(resizeWidth).fillMaxHeight(resizeHeight).background(MaterialTheme.colorScheme.background)
                    .onSizeChanged {
                        boxSize = it
                    }
            ) {
                ResizeableDemo()
            }
        }
        ElevatedCard(modifier=Modifier.padding(8.dp)) {
            Spacer(Modifier.height(16.dp))
            val density = LocalDensity.current
            val dpWidth = with(density) {boxSize.width.toDp()}
            val dpHeight = with(density) {boxSize.height.toDp()}
            ResizerSlider("Width", dpWidth.toString(), value=resizeWidth, onValueChange={resizeWidth=it})
            ResizerSlider("Height", dpHeight.toString(), value=resizeHeight, onValueChange={resizeHeight=it})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OliviaDarienResponsiveAppDemoTheme {
        ResizerAppLayout()
    }
}