package edu.tyut.textlearn

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.ContentAlpha
import androidx.wear.compose.material.LocalContentAlpha
import java.lang.reflect.Field

private const val TAG: String = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TextTtfLearn()
        }
    }
}

@Composable
fun SliderLearn(){
    var progress: Float by remember {
        mutableFloatStateOf(0F)
    }
    Slider(
        value = progress,
        colors = SliderDefaults.colors(
            thumbColor = Color.Magenta,
            activeTickColor = Color(0xFF0079D3)
        ),
        onValueChange = { value: Float ->
            Log.i(TAG, "SliderLearn -> value: $value")
            progress = value
        }
    )
}

@Composable
fun TextLearn(){
    Column {
        Text(text = stringResource(id = R.string.the_book_of_songs), style = MaterialTheme.typography.bodyLarge)
        Text(text = stringResource(id = R.string.the_book_of_songs), style = MaterialTheme.typography.bodyMedium)
        Text(text = stringResource(id = R.string.the_book_of_songs), style = MaterialTheme.typography.bodySmall)
        Text(
            text = stringResource(id = R.string.the_book_of_songs),
            style = TextStyle(
                fontWeight = FontWeight.W900,
                fontSize = 20.sp,
                letterSpacing = 8.sp,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(text = "每天摸鱼", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Start)
        Text(text = "每天摸鱼", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        Text(text = "每天摸鱼", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End)
        Text(text = "每天摸鱼".repeat(15), modifier = Modifier.background(Color.Cyan), lineHeight = 30.sp)
        Text(text = "Hello World!", fontFamily = FontFamily.Cursive)
        Text(text = "Hello World!", fontFamily = FontFamily.Monospace)
        Text(text = "Hello World!", fontFamily = FontFamily.Serif)
        Text(text = "Hello World!", fontFamily = FontFamily.SansSerif)
        Text(text = "Hello World!", fontFamily = FontFamily(
            Font(R.font.consola, FontWeight.W700)
        ))
        val context: Context = LocalContext.current
        Text(
            text = "Hello World!",
            fontFamily = FontFamily(
                Font(R.font.jetbrainsmono_bold, FontWeight.W700)
            ),
            modifier = Modifier.clickable(
                onClick = {
                    Toast.makeText(context,
                        "文本点击", Toast.LENGTH_SHORT).show()
                },
                indication = null,
                interactionSource = MutableInteractionSource()
            ),
        )
    }
}

data class FontDate(val fontId: Int, val fontName: String)

@Composable
fun TextTtfLearn(){
    val context: Context = LocalContext.current
    val fontList: MutableList<FontDate> = mutableListOf()
    val fields: Array<Field> = R.font::class.java.fields
    try {
        for (field in fields){
            if (java.lang.reflect.Modifier.isStatic(field.modifiers)){
                val fontId = field.getInt(null)
                val fontName = field.name
                fontList.add(FontDate(fontId = fontId, fontName = fontName))
            }
        }
    }catch (e: Exception){
        Log.e(TAG, "TextTtfLearn: ", e)
    }
    fontList.forEach{ fontDate: FontDate ->
        Log.i(TAG, "TextTtfLearn -> fontDate: $fontDate")
    }
    LazyColumn{
        items(fontList){ fontDate: FontDate ->
            Text(
                text = "Hello World！fontName: ${fontDate.fontName}",
                fontFamily = FontFamily(
                    Font(fontDate.fontId, FontWeight.W700)
                ),
                modifier = Modifier.clickable(
                    onClick = {
                        Toast.makeText(context,
                            "click", Toast.LENGTH_SHORT).show()
                    },
                    indication = null,
                    interactionSource = MutableInteractionSource()
                ),
            )
        }
    }
}


@Composable
fun HyperTextLearn(){
    Column (
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment =Alignment.CenterHorizontally
    ){
        Text(
            text = buildAnnotatedString {
                append("您现在观看的章节是 ")
                withStyle(
                    style = SpanStyle(fontWeight = FontWeight.W900)
                ){
                    append("Text")
                }
            }
        )
    }
}

@Composable
fun ClickableHyperTextLearn(){
    val text: AnnotatedString = buildAnnotatedString {
        append("勾选即代表同意")
        pushStringAnnotation(
            tag = "tag",
            annotation = "一个用户协议啦🤣!"
        )
        withStyle(
            style = SpanStyle(
                color = Color(0xFF0E9FF2),
                fontWeight = FontWeight.Bold
            )
        ){
            append("用户协议")
        }
        pop()
        // addStringAnnotation(
        //     tag = "tag",
        //     annotation = "一个用户协议啦🤣!",
        //     start = 8,
        //     end = 12,
        // )
    }
    ClickableText(
        text = text,
        onClick = { offset: Int ->
            Log.i(TAG, "ClickableHyperTextLearn -> Hi, 你按了第 $offset 位的文字")
            text.getStringAnnotations(
                tag = "tag",
                start = offset - 1,
                end = offset
            ).firstOrNull()?.apply {
                Log.i(TAG, "ClickableHyperTextLearn -> 用户协议超链接: $offset")

            }
        }
    )
}

@Composable
fun UserConsentAgreementDialog(){
    // 弹窗展示的字符串
    val content: MutableState<String> = remember {
        mutableStateOf("")
    }
    // 是否展示弹窗口
    val isOpenDialog: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }
    val annotatedString: AnnotatedString = buildAnnotatedString {
        append("勾选即代表同意")
        pushStringAnnotation(
            tag = "tag",
            annotation = stringResource(id = R.string.content)
        )
        withStyle(
            style = SpanStyle(
                color = Color(0xFF0E9FF2),
                fontWeight = FontWeight.Bold
            )
        ){
            append("用户协议")
        }
        pop()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 15.dp),
        contentAlignment = Alignment.BottomCenter
    ){
        ClickableText(
            text = annotatedString,
            onClick = { offset: Int ->
                annotatedString.getStringAnnotations(
                    tag = "tag",
                    start = offset - 1,
                    end = offset
                ).firstOrNull()?.apply {
                    isOpenDialog.value = true
                    content.value = item
                }
            }
        )
    }
    if (isOpenDialog.value){
        AlertDialog(
            onDismissRequest = {
                isOpenDialog.value = false
            },
            title = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ){
                    Text(text = "用户协议", style = MaterialTheme.typography.bodyMedium)
                }
            },
            text = {
                Text(text = content.value)
            },
            confirmButton = {
                Button(
                    onClick = {
                        isOpenDialog.value = false
                    }
                ) {
                    Text(text = "确认")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        isOpenDialog.value = false
                    }
                ) {
                    Text(text = "取消")
                }
            }
        )
    }
}

@Composable
fun SelectionTextLearn(){
    SelectionContainer {
        Column {
            Text(text = "每天摸鱼", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Start)
            Text(text = "每天摸鱼", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            Text(text = "每天摸鱼", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End)
            CompositionLocalProvider(
                value = LocalContentAlpha provides ContentAlpha.high
            ) {
                Text("这里是high强调效果")
            }
            CompositionLocalProvider(
                value = LocalContentAlpha provides ContentAlpha.medium
            ) {
                Text("这里是medium强调效果")
            }
            CompositionLocalProvider(
                value = LocalContentAlpha provides ContentAlpha.disabled
            ) {
                Text("这里是disabled强调效果")
            }
        }
    }
}

@Composable
fun TextFieldLearn(){
    var text: String by remember {
        mutableStateOf("")
    }
    TextField(
        value = text,
        onValueChange = { context: String ->
            Log.i(TAG, "TextFieldLearn: $context")
            text = context
        },
        singleLine = true,
        label = {
            Text(text = "邮箱")
        },
        leadingIcon = {
            Row {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "")
                Text(text = "联系人")
            }
        },
        trailingIcon = {
            Row {
                Icon(imageVector = Icons.AutoMirrored.Filled.Send, contentDescription = "")
                Text(text = "发送")
            }
        },
        placeholder = {
            Text(text = "请输入邮箱")
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color(0xFF0079D3),
            cursorColor = Color.Green,
        )
    )
}

@Composable
fun PasswordTextField(){
    var text: String by remember {
        mutableStateOf("")
    }
    var passwordHidden: Boolean by remember {
        mutableStateOf(false)
    }
    TextField(
        value = text,
        onValueChange = {
            text = it
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    passwordHidden = !passwordHidden
                }
            ) {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "")
            }
        },
        label = {
            Text(text = "密码")
        },
        visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None
    )
}

@Composable
fun BasicTextFieldLearn(){
    var text: String by remember {
        mutableStateOf("")
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD3D3D3)),
        contentAlignment = Alignment.Center
    ){
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
            },
            modifier = Modifier
                .background(Color.White, CircleShape)
                .height(35.dp)
                .fillMaxWidth(),
            decorationBox = { innerTextField: @Composable () -> Unit ->
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 10.dp)
                ){
                    IconButton(
                        onClick = {

                        }
                    ){
                        Icon(imageVector = Icons.Filled.Face, contentDescription = "")
                    }
                    Box(
                        modifier = Modifier.weight(1F),
                        contentAlignment = Alignment.CenterStart
                    ){
                        innerTextField()
                    }
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.Send, contentDescription = "")
                    }
                }
            }
        )
    }
}


@Composable
fun BasicTextFieldLearnTwo(){
    var text: String by remember {
        mutableStateOf("")
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF27A875)),
        contentAlignment = Alignment.Center
    ){
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
            },
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth(),
            decorationBox = { innerTextField: @Composable () -> Unit ->
                Column(
                    modifier = Modifier.padding(vertical = 10.dp)
                ){
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Favorite, contentDescription = "")
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Build, contentDescription = "")
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Call, contentDescription = "")
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Check, contentDescription = "")
                        }
                    }
                    Box (
                        modifier = Modifier.padding(horizontal = 10.dp)
                    ){
                        innerTextField()
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ){
                        TextButton(onClick = { /*TODO*/ }) {
                            Text(text = "发送")
                        }
                        Spacer(modifier = Modifier.padding(horizontal = 10.dp))
                        TextButton(onClick = { /*TODO*/ }) {
                            Text(text = "关闭")
                        }
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun PreviewSlider(){
    BasicTextFieldLearnTwo()
}
// pull_request1
