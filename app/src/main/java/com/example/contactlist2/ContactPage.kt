package com.example.contactlist2

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size

@Composable
fun ContactPage() {
    val contact = getFakeData()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(Color.LightGray)
    ) {
        LazyColumn(
            content = {
                itemsIndexed(contact) { index: Int, item: ContactInfo ->
                    ContactCards(item = item)
                }
            }
        )
    }
}

@Composable
fun ContactCards(item: ContactInfo) {
    var showDialog by remember { mutableStateOf(false) }

    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = BorderStroke(1.dp, Color.Blue),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { showDialog = true }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val painter = when (item.image) {
                is ContactImage.Drawable -> {
                    rememberAsyncImagePainter(model = item.image.resId)
                }
                is ContactImage.Url -> {
                    rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(item.image.url)
                            .size(Size.ORIGINAL)
                            .build()
                    )
                }
                else -> null
            }
            if (painter != null) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .size(90.dp)
                        .border(2.dp, Color.Blue, CircleShape)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = item.name,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                Text(
                    text = item.phoneNO,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(text = "Contact Details", fontWeight = FontWeight.Bold)
            },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val dialogPainter = when (item.image) {
                        is ContactImage.Drawable -> rememberAsyncImagePainter(model = item.image.resId)
                        is ContactImage.Url -> rememberAsyncImagePainter(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(item.image.url)
                                .size(Size.ORIGINAL)
                                .build()
                        )
                        else -> null
                    }

                    if (dialogPainter != null) {
                        Image(
                            painter = dialogPainter,
                            contentDescription = null,
                            modifier = Modifier
                                .size(120.dp)
                                .border(2.dp, Color.Blue, CircleShape)
                                .clip(CircleShape)
                                .padding(8.dp),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Name: ${item.name}", fontWeight = FontWeight.Bold)
                    Text(text = "Phone Number: ${item.phoneNO}")
                    Text(text = "Address: ${item.address}")
                }
            },
            confirmButton = {
                FilledTonalButton(
                    onClick = { showDialog = false },
                    colors = ButtonDefaults.buttonColors(Color.Red)
                ) {
                    Text("Close", color = Color.White)
                }
            }
        )
    }
}