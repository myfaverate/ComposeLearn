package edu.tyut.alertdialoglearn

import android.content.Context
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.decode.SvgDecoder
import coil.request.Disposable
import coil.request.ImageRequest
import com.skydoves.landscapist.coil.CoilImage
import edu.tyut.alertdialoglearn.bean.ButtonState


private const val TAG: String = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Text(text = "Hello World！")
        }
    }
}

@Composable
fun MessageAlertDialog(){
    val openDialog: MutableState<Boolean> = remember {
        mutableStateOf(true)
    }
    if (openDialog.value){
        AlertDialog(
            onDismissRequest = {
                // 当用户点击对话框以外的地方或者按下系统返回键将会执行的代码
                openDialog.value = false
            },
            title = {
                Text(
                    text = "开启定位服务",
                    fontWeight = FontWeight.W700,
                    style = MaterialTheme.typography.titleMedium,
                )
            },
            text = {
                Text(
                    text = "这将意味着，我们会给您提供精准的位置服务，并且您将接受关于您订阅的位置信息",
                    fontSize = 16.sp,
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text(
                        text = "确定",
                        fontWeight = FontWeight.W700,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text(
                        text = "取消",
                        fontWeight = FontWeight.W700,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageAlertDialogTwo(){
    val openDialog: MutableState<Boolean> = remember {
        mutableStateOf(true)
    }
    if (openDialog.value){
        AlertDialog(
            onDismissRequest = {
                // 当用户点击对话框以外的地方或者按下系统返回键将会执行的代码
                openDialog.value = false
            },
        ){
            Row(
                modifier = Modifier.padding(all = 8.dp)
            ){
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        openDialog.value = false
                    }
                ){
                    Text(text = "必须接受！")
                }
            }
        }
    }
}

@Composable
fun MessageDialog(){
    var isShowDialog: Boolean by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ){
        Button(
            onClick = {
                isShowDialog = true
            }
        ) {
            Text(
                text = "弹窗",
            )
        }
    }
    if (isShowDialog){
        Dialog(
            onDismissRequest = {
                isShowDialog = false
            }
        ) {
            Box(
                modifier = Modifier
                    .size(300.dp)
                    .background(Color.White),
                contentAlignment = Alignment.Center,
            ){
                Column {
                    LinearProgressIndicator()
                    Text(text = "加载中...")
                }
            }
        }
    }
}

@Composable
fun MessageButton(){

    // 获取按钮的状态
    val interactionState: MutableInteractionSource = remember {
        MutableInteractionSource()
    }

    val (text: String, textColor: Color, buttonColor: Color) = when{
        interactionState.collectIsPressedAsState().value ->
            ButtonState(text = "Just Pressed", textColor = Color.Red, buttonColor = Color.Black)
        else -> ButtonState(text = "Just Normal", textColor = Color.White, buttonColor = Color.Red)
    }

    val context = LocalContext.current
    Button(
        interactionSource = interactionState,
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor
        ),
        onClick = {
            Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show()
        }
    ) {
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = "图标",
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        Text(text = text, color = textColor)
    }
}

@Composable
fun MessageCard(){
    val context: Context = LocalContext.current
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                Toast
                    .makeText(context, "Card", Toast.LENGTH_SHORT)
                    .show()
            },
        elevation = CardDefaults.cardElevation(10.dp)
    ){
        Column(
            modifier = Modifier.padding(15.dp)
        ){
            Text(
                text = buildAnnotatedString {
                    append("欢迎来到 ")
                    withStyle(
                        style = SpanStyle(fontWeight = FontWeight.W900, color = Color(0xFF45528B))
                    ){
                        append("Jetpack Compose 博物馆")
                    }
                }
            )
            Text(
                text = buildAnnotatedString {
                    append("您现在看的章节是 ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.W900)){
                        append("Card")
                    }
                },
                textDecoration = TextDecoration.Underline
            )
            // 设置点击事件
            val annotationString: AnnotatedString = buildAnnotatedString {
                append("click this")
                withStyle(style = SpanStyle(color = Color.Magenta, fontSize = 12.sp, fontWeight = FontWeight.Bold)){
                    append("learn")
                }
                append("Jump to the learning website")
                addStringAnnotation(
                    tag = "URL",
                    annotation = "https://www.baidu.com",
                    start = 12,
                    end = 17,
                )
            }
            Text(
                text = annotationString,
                modifier = Modifier.clickable {
                    val a: List<AnnotatedString.Range<String>> = annotationString.getStringAnnotations(
                        tag = "URL",
                        start = 12,
                        end = 17
                    )
                    Log.i(TAG, "MessageCard -> a: $a")
                }
            )
        }
    }
}

@Composable
fun FilledCardExample() {
    val context: Context = LocalContext.current
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .size(width = 240.dp, height = 100.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                Toast
                    .makeText(context, "Card1", Toast.LENGTH_SHORT)
                    .show()
            },
    ) {
        Text(
            text = "Filled",
            modifier = Modifier
                .padding(16.dp),
            textAlign = TextAlign.Center,
        )
    }
}


@Composable
fun FloatingActionButtonLearn(){
    val images: List<Int> = listOf(R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5)
    LazyColumn {
        items(images){ index: Int ->
            Surface(
                shape = CircleShape,
                border = BorderStroke(width = 5.dp, Color.Gray)
            ){
                Image(
                    painter = painterResource(id = index),
                    modifier = Modifier
                        .padding(5.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    contentDescription = ""
                )
            }
        }
    }
}

@Composable
fun CoilLearn(){
    // val imageUrl = "https://fastly.picsum.photos/id/133/300/300.jpg?hmac=QNG5UogJb89EMr5ynHF9s2vYRUGx4EVNFu1gJ2cS5Yg"
    // AsyncImage(model = "https://fastly.picsum.photos/id/133/300/300.jpg?hmac=QNG5UogJb89EMr5ynHF9s2vYRUGx4EVNFu1gJ2cS5Yg", contentDescription = "")
    // AsyncImage(
    //     model = ImageRequest.Builder(LocalContext.current)
    //         .data("https://fastly.picsum.photos/id/133/300/300.jpg?hmac=QNG5UogJb89EMr5ynHF9s2vYRUGx4EVNFu1gJ2cS5Yg")
    //         .crossfade(true)
    //         .build(),
    //     placeholder = painterResource(R.drawable.image1),
    //     contentDescription = "image",
    //     contentScale = ContentScale.Crop,
    //     modifier = Modifier.clip(CircleShape)
    // )
    // SubcomposeAsyncImage(
    //     model = imageUrl,
    //     contentDescription = ""
    // ) {
    //     val state = painter.state
    //     if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
    //         CircularProgressIndicator()
    //     } else {
    //         SubcomposeAsyncImageContent()
    //     }
    // }
    val gifUrl = "https://coil-kt.github.io/coil/images/coil_logo_black.svg"
    val context: Context = LocalContext.current
    val imageLoader: ImageLoader = ImageLoader.Builder(context)
        .components {
            // add(ImageDecoderDecoder.Factory())
            add(SvgDecoder.Factory())
        }
        .build()
    Image(
        painter = rememberAsyncImagePainter(model = gifUrl, imageLoader = imageLoader, onState = { state: AsyncImagePainter.State ->
            when(state){
                is AsyncImagePainter.State.Loading -> {
                    Log.i(TAG, "CoilLearn -> Loading...")
                }
                is AsyncImagePainter.State.Success -> {
                    Log.i(TAG, "CoilLearn -> Success...")
                }
                is AsyncImagePainter.State.Error -> {
                    Log.i(TAG, "CoilLearn -> Error...")
                }
                else -> {
                    Log.i(TAG, "CoilLearn -> State else...")
                }
            }
        }),
        contentDescription = "",
    )
}

@Composable
fun ZoomSvg(){
    val svgUrl = "https://coil-kt.github.io/coil/images/coil_logo_black.svg"
    val context: Context = LocalContext.current
    val imageLoader: ImageLoader = ImageLoader.Builder(context)
        .components {
            add(SvgDecoder.Factory())
        }
        .build()
    var flag: Boolean by remember {
        mutableStateOf(false)
    }
    val size: Dp by animateDpAsState(targetValue = if(flag) 450.dp else 50.dp, label = "zoom")
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            // CoilImage(
            //     imageModel = {
            //         svgUrl
            //     },
            //     contentDescription = "",
            //     modifier = Modifier
            //         .size(size)
            //         .clickable(
            //             onClick = {
            //                 flag = !flag
            //             },
            //             indication = null,
            //             interactionSource = MutableInteractionSource()
            //         ),
            //     imageLoader = imageLoader
            // )
            // Image(
            //     painter = rememberAsyncImagePainter(model = svgUrl, imageLoader = imageLoader),
            //     contentDescription = "",
            //     modifier = Modifier
            //         .size(size)
            //         .clickable(
            //             onClick = {
            //                 flag = !flag
            //             },
            //             indication = null,
            //             interactionSource = MutableInteractionSource()
            //         ),
            // )
            CoilImage(
                imageModel = { svgUrl },
                modifier = Modifier
                    .size(size)
                    .clickable(
                        onClick = {
                            flag = !flag
                        },
                        indication = null,
                        interactionSource = MutableInteractionSource()
                    ),
                imageLoader = {
                    imageLoader
                },
            )
        }
    }
}

@Preview
@Composable
fun PreviewMessageCard(){
    ZoomSvg()
}