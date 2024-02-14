package lihan.chen.moneymanager.feature

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TesterPreview() {
    val padding = 30.dp
    var isStart by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = Unit) {
        isStart = true
    }

    val animation by animateFloatAsState(
        targetValue = if (isStart) 1f else 0f, label = "",
        animationSpec = tween(1000)
    )
    val numbers = listOf(
        600,300,1010,3000,250,
        800,500,5000,10000,7000,
        1000,3000
    )

    val sum = numbers.sum()
    val maxValue = numbers.sortedByDescending { it }[0]
    val height = 80.dp
    val months = 12
    val textMeasurer = rememberTextMeasurer()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(16.dp)
            .drawBehind {
                drawY()
                drawX()
                repeat(months){
                    val x = this.size.width / numbers.size
                    val y = this.size.height / numbers.size
                    drawText(
                        textMeasurer.measure(
                            text = (it+1).toString()
                        ),
                        topLeft = Offset( x = 50f +  x  * it , y = this.size.height)
                    )
                    drawLine(
                        color = Color.Red.copy(0.2f),
                        start = Offset( x = 60f +  x  * it , y = this.size.height),
                        end = Offset( x = 60f +  x  * it , y = 0 + y * it),
                        strokeWidth = 20.dp.toPx()
                    )
                }
            }
    ) {
//        Chart(chartListData = getMockRangeList(),
//            animationType = AnimationType.Bouncy(10F),
//            chartStyle = ChartStyle.BarChartStyle(
//                chartBrush = listOf(Pink40, Purple80),
//                barCornerRadius = 20F,
//                chartValueTextColor = Color.Black
//            )
//        )


    }
//    val textMeasurer = rememberTextMeasurer()
//    Box(
//        modifier = Modifier
//            .fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ){
//        Column(
//             modifier = Modifier
//                 .fillMaxWidth()
//                 .padding(16.dp)
//        ) {
//            numbers.forEachIndexed { index, i ->
//                Canvas(modifier = Modifier.fillMaxWidth()){
//                    val length = (i / this.size.width) * this.size.width
//                    val start = Offset(0f, padding.toPx() * index)
//                    val end = Offset(length, padding.toPx() * index)
//                    drawLine(
//                        color = Color.Red.copy(alpha = 0.3f),
//                        start = Offset(0f, padding.toPx() * index),
//                        end = Offset(length, padding.toPx() * index),
//                        cap = StrokeCap.Round,
//                        strokeWidth = 25.dp.toPx()
//                    )
//                    drawText(
//                        textMeasurer = textMeasurer,
//                        text =  i.toString(),
//                        topLeft = Offset(
//                            (start.x + end.x) / 2,
//                            (start.y + end.y) / 2
//                        )
//                    )
//                }
//
//            }
//        }
//    }

//    numbers.forEachIndexed { index, number ->
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(height)
//                .drawWithCache {
//                    val width = ((number.toFloat() / sum)) * size.width
//                    onDrawBehind {
//                        drawLine(
//                            color = Color.Red.copy(alpha = 0.3f),
//                            start = Offset(16.dp.toPx(), (height / 2).toPx()),
//                            end = Offset(width * animation, (height / 2).toPx()),
//                            strokeWidth = 40.dp.toPx(),
//                            cap = StrokeCap.Round
//                        )
//                    }
//                }
//        )
//    }

}

private fun DrawScope.drawY() {
    drawLine(
        color = Color.Black,
        start = Offset(
            x = 0f, y = this.size.height
        ),
        end = Offset(
            x = 0f, y = 0f
        ),
        strokeWidth = 2.dp.toPx(),
    )
}

private fun DrawScope.drawX() {
    drawLine(
        color = Color.Black,
        start = Offset(
            x = 0f, y = this.size.height
        ),
        end = Offset(
            x = this.size.width, y = this.size.height
        ),
        strokeWidth = 2.dp.toPx(),
    )
}


@Preview(showSystemUi = true)
@Composable
fun TesterPreviewPreview() {
    TesterPreview()


}