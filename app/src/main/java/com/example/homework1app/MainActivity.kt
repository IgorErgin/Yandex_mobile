package com.example.homework1app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.homework1app.ui.theme.Homework1AppTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

class MainActivity : ComponentActivity() {
    @Composable
    fun UserForm() {

        // rememberSaveable сохраняет значения при повороте экрана
        var name by rememberSaveable { mutableStateOf("") }
        var age by rememberSaveable { mutableStateOf(25f) }
        var gender by rememberSaveable { mutableStateOf("Мужской") }
        var subscribed by rememberSaveable { mutableStateOf(false) }
        var showSummary by rememberSaveable { mutableStateOf(false) }
        val scrollState = rememberScrollState()

        //Контейнер, который адаптируется под портрет/ландшафт
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_avatar),
                    contentDescription = "avatar",
                    modifier = Modifier.size(100.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(stringResource(R.string.name_label)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "${stringResource(R.string.age_label)}: ${age.toInt()}")
                Slider(
                    value = age,
                    onValueChange = { age = it },
                    valueRange = 1f..100f
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(stringResource(R.string.gender_label))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = gender == "Мужской", onClick = { gender = "Мужской" })
                    Text("Мужской")
                    Spacer(modifier = Modifier.width(8.dp))
                    RadioButton(selected = gender == "Женский", onClick = { gender = "Женский" })
                    Text("Женский")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = subscribed, onCheckedChange = { subscribed = it })
                    Text(stringResource(R.string.subscribe_label))
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { showSummary = true },
                    enabled = name.isNotBlank(),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(R.string.submit_label))
                }

                if (showSummary) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Имя: $name\nВозраст: ${age.toInt()}\nПол: $gender\nПодписка: ${if (subscribed) "да ✅" else "нет ❌"}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }

    @Preview(showSystemUi = true)
    @Composable
    fun PreviewUserForm() {
        UserForm()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Homework1AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UserForm()
                }
            }
        }
    }
}
