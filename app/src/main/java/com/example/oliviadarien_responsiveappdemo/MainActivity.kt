package com.example.oliviadarien_responsiveappdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapFlingBehavior
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.oliviadarien_responsiveappdemo.ui.theme.OliviaDarienResponsiveAppDemoTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import kotlin.random.Random

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

val DEMO_NAMES = arrayOf(
    "Demo 1",
    "Demo 2",
    "Demo 3"
)
val SIZE_NAMES = arrayOf(
    "Portrait Phone",
    "Landscape Phone",
    "Portrait Tablet"
)
val SIZE_DIMS = arrayOf(
    Pair(448.dp, 896.dp),
    Pair(896.dp, 448.dp),
    Pair(800.dp, 1280.dp)
)

@Composable
fun DemoHeader(label: String) {
    Text(label, fontSize=32.sp, fontWeight=FontWeight.Bold, lineHeight=40.sp, modifier=Modifier.padding(24.dp, 8.dp, 8.dp, 16.dp))
}

@Composable
fun Demo1Card(text: String, color: Color) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .size(104.dp, 50.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize().background(color)
        ) {
            Text(text = text, fontSize = 30.sp)
        }
    }
}


@Composable
fun Demo1(case: Int) {
    val cardInfo = listOf(
        Pair("1", Color.hsl(0f, 1f, 0.83f)),
        Pair("2", Color.hsl(32f, 1f, 0.83f)),
        Pair("3", Color.hsl(93f, 1f, 0.83f)),
        Pair("4", Color.hsl(190f, 1f, 0.83f)),
        Pair("5", Color.hsl(244f, 1f, 0.83f)),
        Pair("6", Color.hsl(190f, 1f, 0.83f)),
        Pair("7", Color.hsl(93f, 1f, 0.83f)),
        Pair("8", Color.hsl(32f, 1f, 0.83f)),
        Pair("9", Color.hsl(0f, 1f, 0.83f)),
    ).take(case) // take only the number of cards specified by case

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DemoHeader("Breakpoints")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            // create the cards
            for (index in cardInfo.indices) {
                Demo1Card(cardInfo[index].first, cardInfo[index].second)
            }
        }
    }
}

@Composable
fun Demo2(height: Dp, width: Dp) {
    // height 3) name, pic, description 2) name, description 1) name 0) nothing
    // width 2) john smith 1) john 0) nothing
    var name by remember {mutableStateOf("John Smith")}
    if(width >= 300.dp){
        name = "John Smith"
    }
    else if (width >= 200.dp){
        name = "John"
    }
    else{
        name = ""
    }

    Column(
        modifier=Modifier.fillMaxWidth(),
        horizontalAlignment=Alignment.CenterHorizontally
    ) {
        DemoHeader("Adaptive Design")
        Column(
            modifier = Modifier
                .padding(8.dp)
                .background(MaterialTheme.colorScheme.background)
                .fillMaxWidth()
            , horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                    if (height >= 230.dp && width >= 200.dp){
                        Text(text = name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 40.sp,
                            modifier = Modifier
                                .padding(16.dp))}
                    if (height >= 370.dp && width >= 200.dp){
                        Spacer(modifier = Modifier.width(16.dp))
                        Box(
                            modifier = Modifier
                                .padding(16.dp)
                                .size(100.dp)
                                .clip(CircleShape)
                                .background(Color.Red)
                        ) {
                    }}
                    if (height >= 390.dp && width >= 275.dp){
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(text = "This is information on John Smith",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(16.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun Demo3Card(color: Color = MaterialTheme.colorScheme.secondary) {
    Card(modifier=Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier=Modifier.padding(4.dp).size(48.dp)
                    .clip(CircleShape)
                    .background(color)
                    .padding(8.dp)
            )
            Column(modifier=Modifier.padding(8.dp, 0.dp)) {
                Text("Title", fontWeight=FontWeight.Bold, fontSize=20.sp)
                Text("Description")
            }
        }
    }
}

@Composable
fun Demo3() {
    Column(
        modifier=Modifier.fillMaxWidth()
    ) {
        DemoHeader("Resizeable Grid")
        LazyVerticalGrid(columns=GridCells.Adaptive(180.dp), contentPadding=PaddingValues(4.dp)) {
            items(64) {
                val random = Random(it)
                var color = Color.hsl(random.nextFloat() * 360f, 0.5f, 0.5f, 1f)
                Box(modifier=Modifier.padding(4.dp).width(180.dp)) {
                    Demo3Card(color=color)
                }
            }
        }
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
fun ButtonSelector(options: Array<String>, selected: Int, onSelectionChange: (Int)->Unit) {
    Row(modifier=Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement=Arrangement.spacedBy(8.dp)) {
        for (i in options.indices) {
            Button(onClick={onSelectionChange(i)}, modifier=Modifier.weight(1f), enabled=(i != selected)) {
                Text(options[i], fontSize=24.sp)
            }
        }
    }
}

@Composable
fun ResizerAppLayout(modifier: Modifier = Modifier) {
    var resizeWidth by rememberSaveable {mutableFloatStateOf(1f)}
    var resizeHeight by rememberSaveable {mutableFloatStateOf(1f)}
    var boxSize by remember {mutableStateOf(IntSize.Zero)}
    var fullBoxSize by remember {mutableStateOf(IntSize.Zero)}
    var selectedDemo by rememberSaveable {mutableIntStateOf(2)}

    Column(
        modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.secondaryContainer),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(modifier=Modifier.fillMaxWidth().weight(1f).background(MaterialTheme.colorScheme.secondaryContainer).onSizeChanged {
            fullBoxSize = it
        }) {
            Box(
                modifier=Modifier.fillMaxWidth(resizeWidth).fillMaxHeight(resizeHeight).background(MaterialTheme.colorScheme.background)
                    .onSizeChanged {
                        boxSize = it
                    }
            ) {
                when (selectedDemo) {
                    0 ->
                        if (with(LocalDensity.current) {boxSize.width.toDp()} >= 1260.dp ) {
                            Demo1(9)
                        }
                        else if (with(LocalDensity.current) {boxSize.width.toDp()} >= 1120.dp ) {
                            Demo1(8)
                        }
                        else if (with(LocalDensity.current) {boxSize.width.toDp()} >= 980.dp ){
                            Demo1(7)
                        }
                        else if (with(LocalDensity.current) {boxSize.width.toDp()} >= 840.dp ){
                            Demo1(6)
                        }
                        else if (with(LocalDensity.current) {boxSize.width.toDp()} >= 700.dp ) {
                            Demo1(5)
                        }
                        else if (with(LocalDensity.current) {boxSize.width.toDp()} >= 560.dp ){
                            Demo1(4)
                        }
                        else if (with(LocalDensity.current) {boxSize.width.toDp()} >= 420.dp ){
                            Demo1(3)
                        }
                        else if (with(LocalDensity.current) {boxSize.width.toDp()} >= 280.dp){
                            Demo1(2)
                        }
                        else if (with(LocalDensity.current) {boxSize.width.toDp()} >= 140.dp){
                            Demo1(1)
                        }
                        else{
                            Demo1(0)
                        }
                    1 -> Demo2((with(LocalDensity.current) {boxSize.height.toDp()}), (with(LocalDensity.current) {boxSize.width.toDp()}))
                    2 -> Demo3()
                }
            }
        }
        ElevatedCard(modifier=Modifier.padding(8.dp, 8.dp, 8.dp, 0.dp)) {
            Spacer(Modifier.height(16.dp))
            val density = LocalDensity.current
            val dpWidth = with(density) {boxSize.width.toDp()}
            val dpHeight = with(density) {boxSize.height.toDp()}
            val maxDpWidth = with(density) {fullBoxSize.width.toDp()}
            val maxDpHeight = with(density) {fullBoxSize.height.toDp()}
            ResizerSlider("Width", dpWidth.toString(), value=resizeWidth, onValueChange={resizeWidth=it})
            ResizerSlider("Height", dpHeight.toString(), value=resizeHeight, onValueChange={resizeHeight=it})
            var selectedSize = -1
            for (i in SIZE_DIMS.indices) {
                if (SIZE_DIMS[i].first == dpWidth && SIZE_DIMS[i].second == dpHeight) {
                    selectedSize = i
                    break
                }
            }
            ButtonSelector(SIZE_NAMES, selectedSize) {
                resizeWidth = SIZE_DIMS[it].first / maxDpWidth
                resizeHeight = SIZE_DIMS[it].second / maxDpHeight
                if (resizeWidth == 0f) {
                    resizeWidth = 1f
                }
                if (resizeHeight == 0f) {
                    resizeHeight = 1f
                }
            }
        }
        ButtonSelector(DEMO_NAMES, selectedDemo) { selectedDemo = it }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OliviaDarienResponsiveAppDemoTheme {
        Demo3()
    }
}