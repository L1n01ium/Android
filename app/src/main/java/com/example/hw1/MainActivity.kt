package com.example.hw1

import android.os.Bundle
import android.widget.SeekBar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    CoroutineTestScreen()
                }
            }
        }
    }
}

@Composable
fun CoroutineTestScreen() {
    var sliderValue by remember { mutableFloatStateOf(1f) }
    var isRunning by remember { mutableStateOf(false) }
    var currentJob by remember { mutableStateOf<Job?>(null) }
    var progress by remember { mutableFloatStateOf(0f) }

    val scope = rememberCoroutineScope()
    val coroutineCount = sliderValue.roundToInt()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.coroutine_count_label, coroutineCount),
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        AndroidView(
            factory = { context ->
                SeekBar(context).apply {
                    max = 19
                    progress = 0f
                    setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                            if (!isRunning && fromUser) {
                                sliderValue = (progress + 1).toFloat()
                            }
                        }
                        override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                        override fun onStopTrackingTouch(seekBar: SeekBar?) {}
                    })
                }
            },
            update = { seekBar ->
                seekBar.progress = (sliderValue - 1).toInt()
                seekBar.isEnabled = !isRunning
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (isRunning) {
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        Button(
            onClick = {
                progress = 0f
                isRunning = true
                currentJob = scope.launch {
                    for (i in 1..coroutineCount) {
                        launch {
                            delay(1000)
                        }.join()

                        progress = i.toFloat() / coroutineCount
                    }
                    isRunning = false
                }
            },
            enabled = !isRunning,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.run_sequential))
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                progress = 0f
                isRunning = true
                currentJob = scope.launch {
                    var completed = 0
                    coroutineScope {
                        repeat(coroutineCount) {
                            launch {
                                delay(1000)
                                completed++
                                progress = completed.toFloat() / coroutineCount
                            }
                        }
                    }
                    isRunning = false
                }
            },
            enabled = !isRunning,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.run_parallel))
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                currentJob?.cancel()
                isRunning = false
                progress = 0f
            },
            enabled = isRunning,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.cancel_all))
        }
    }
}