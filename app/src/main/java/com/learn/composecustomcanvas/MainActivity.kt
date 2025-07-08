package com.learn.composecustomcanvas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.learn.composecustomcanvas.component.CustomCanvasComponent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShowCustomCanvas()
            }
        }
    }
    @Composable
    fun ShowCustomCanvas() {

        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            var inputText by remember {
                mutableStateOf("")
            }
            var errorMessage by remember { mutableStateOf<String?>(null) }
            val value = inputText.toIntOrNull() ?: 0

            CustomCanvasComponent(
                indicatorValue = value,
                maxIndicatorValue = 100,
                backgroundIndicatorColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
                backgroundIndicatorStrokeWidth = 100f,
                foregroundIndicatorColor = MaterialTheme.colorScheme.primary,
                foregroundIndicatorStrokeWidth = 100f,
                bigTextFontSize = MaterialTheme.typography.bodyMedium.fontSize,
                bigTextColor = MaterialTheme.colorScheme.onSurface,
                bigTextSuffix = "GB",
                smallText = "Remaining",
                smallTextFontSize = MaterialTheme.typography.bodyLarge.fontSize,
                smallTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
            )

            TextField(
                value = inputText,
                onValueChange = {
                    if(it.isEmpty()){
                        inputText = it
                        errorMessage = null
                    }else{
                        val number = it.toIntOrNull()
                        if(number!=null && number >= 0 && number <= 100){
                            inputText = it
                        }else{
                            inputText = ""
                            errorMessage = "Please enter a number between 0 and 100"
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )


            )
            if(errorMessage!=null){
               Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = errorMessage ?: "",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )

            }

        }
}