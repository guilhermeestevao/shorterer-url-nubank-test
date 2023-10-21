package test.example.takehome

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Form() {
    var text by remember { mutableStateOf("") }

        OutlinedTextField(
            value = text,
            onValueChange = {text = it}

        )

}

@Composable
fun ListAlias(){

}