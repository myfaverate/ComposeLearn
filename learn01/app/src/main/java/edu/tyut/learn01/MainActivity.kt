package edu.tyut.learn01

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.tyut.learn01.bean.Message
import edu.tyut.learn01.bean.MsgData
import edu.tyut.learn01.ui.theme.Learn01Theme

private const val TAG: String = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
        }
    }
}

@Composable
fun MessageCard(message: Message){

    var isExpanded: Boolean by remember {
        mutableStateOf(false)
    }

    val surfaceColor by animateColorAsState(
        label = stringResource(id = R.string.color_change_animation_string),
        targetValue = if (isExpanded) colorResource(id = R.color.gray) else MaterialTheme.colorScheme.surface
    )

    // 形状设置
    Surface(
        shape = MaterialTheme.shapes.medium,
        tonalElevation = 5.dp,
        modifier = Modifier
            .padding(all = 8.dp)
            .clickable {
                isExpanded = !isExpanded
            },
        color = surfaceColor
    ){
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.image1),
                contentDescription = stringResource(id = R.string.head_portrait),
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .border(
                        1.5.dp,
                        MaterialTheme.colorScheme.secondary,
                        shape = CircleShape
                    ) // 添加边框
                    .clickable {
                        Log.i(TAG, "MessageCard: 头像点击...")
                    },
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.padding(horizontal = 8.dp))
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = message.author,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.padding(vertical = 4.dp))
                Text(
                    text = message.body,
                    color = colorResource(id = R.color.purple_200),
                    style = MaterialTheme.typography.bodyMedium,
                    // 默认情况显示一行
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    // compose 大小的动画效果
                    modifier = Modifier.animateContentSize()
                )
            }
        }
    }
}



@Composable
fun Conversation(messages: List<Message>){
    LazyColumn {
        items(messages){ message: Message ->
            MessageCard(message = message)
        }
    }
}

@Preview(name = "Light Mode")
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
)
@Composable
fun PreviewMessageCard(){
    Learn01Theme{
        Conversation(messages = MsgData.messages)
    }
}