package com.example.unitconverter

import android.icu.number.NumberFormatter.UnitWidth
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme

class MainActivity : ComponentActivity() { // Main Entry Point of Composable Functions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { // To define the layout
            UnitConverterTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {

    var inputVal by remember { mutableStateOf("") }
    var outputVal by remember { mutableStateOf("Result: 0.0") }
    var inputExpand = remember { mutableStateOf<Boolean>(false) }
    var outputExpand by remember { mutableStateOf(false) }
    var inputUnit by remember { mutableStateOf("Meter") }
    var outputUnit by remember { mutableStateOf("Meter") }
    var iConversionfactor by remember { mutableStateOf(1.0) }
    var oConversionfactor by remember { mutableStateOf(1.0) }

    fun convertUnit(){
        var inputValDouble = inputVal.toDoubleOrNull() ?: 0.0
        var ans = inputValDouble * iConversionfactor * oConversionfactor
        outputVal = "Result: $ans"
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text("Unit Converter", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputVal, onValueChange = {
            inputVal = it
            convertUnit()
        }, label = {Text("Enter Value")})

        Spacer(modifier = Modifier.height(16.dp))

        Row{
            // Input BOX
            Box() {
                Button(onClick = {
                    inputExpand.value = !inputExpand.value
                }){
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(expanded = inputExpand.value, onDismissRequest = {inputExpand.value = false}) {
                    DropdownMenuItem(text = { Text("Kilometer") }, onClick = {
                        inputUnit = "Kilometer"
                        iConversionfactor = 1000.0
                        inputExpand.value = false
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text("Meter") }, onClick = {
                        inputUnit = "Meter"
                        iConversionfactor = 1.0
                        inputExpand.value = false
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text("Centimeter") }, onClick = {
                        inputUnit = "Centimeter"
                        iConversionfactor = 0.01
                        inputExpand.value = false
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text("Millimeter") }, onClick = {
                        inputUnit = "Millimeter"
                        iConversionfactor = 0.001
                        inputExpand.value = false
                        convertUnit()
                    })
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Output BOX
            Box() {
                Button(onClick = {
                    outputExpand = !outputExpand
                }){
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(expanded = outputExpand, onDismissRequest = {outputExpand = false}) {
                    DropdownMenuItem(text = { Text("Kilometer") }, onClick = {
                        outputUnit = "Kilometer"
                        oConversionfactor = 0.001
                        outputExpand = false
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text("Meter") }, onClick = {
                        outputUnit = "Meter"
                        oConversionfactor = 1.0
                        outputExpand = false
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text("Centimeter") }, onClick = {
                        outputUnit = "Centimeter"
                        oConversionfactor = 100.0
                        outputExpand = false
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text("Millimeter") }, onClick = {
                        outputUnit = "Millimeter"
                        oConversionfactor = 1000.0
                        outputExpand = false
                        convertUnit()
                    })
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(outputVal, fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}