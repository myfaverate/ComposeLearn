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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import edu.tyut.textlearn.ui.theme.TextLearnTheme
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

    }
}


@Preview
@Composable
fun PreviewSlider(){
    TextTtfLearn()
}