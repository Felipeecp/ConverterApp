package com.luiz.converterapp.compose.converter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.luiz.converterapp.data.Conversion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConversionMenu(
    list: List<Conversion>,
    modifier: Modifier=Modifier,
    isLandScape: Boolean,
    convert:(Conversion) -> Unit
){

    var displayingText by rememberSaveable {
        mutableStateOf("Select the conversion type")
    }

    // Para definir a mesma largura para o dropdown e para o TextField
    var textFieldSize by remember {
        mutableStateOf(Size.Zero)
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    val icon = if(expanded)
            Icons.Filled.KeyboardArrowUp
        else
            Icons.Filled.KeyboardArrowDown


    Column() {
        if(!isLandScape){
            OutlinedTextField(
                value = displayingText,
                onValueChange = {displayingText=it},
                textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        textFieldSize = coordinates.size.toSize()
                    },
                label = {Text(text = "Conversion type")},
                trailingIcon = {
                    Icon(imageVector = icon, contentDescription = "icon",
                        modifier.clickable { expanded = !expanded })
                },
                readOnly = true
            )
        }else{
            OutlinedTextField(
                value = displayingText,
                onValueChange = {displayingText=it},
                textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .onGloballyPositioned { coordinates ->
                        textFieldSize = coordinates.size.toSize()
                    },
                label = {Text(text = "Conversion type")},
                trailingIcon = {
                    Icon(imageVector = icon, contentDescription = "icon",
                        modifier.clickable { expanded = !expanded })
                },
                readOnly = true
            )
        }


        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false },
            modifier = Modifier.width((with(LocalDensity.current){textFieldSize.width.toDp()}))
        ) {
            list.forEach { conversion ->
                DropdownMenuItem(
                    onClick = {
                        displayingText = conversion.description
                        expanded=false
                        convert(conversion)
                    },
                    text = {
                    Text(text = conversion.description,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                })
            }
        }
    }



}