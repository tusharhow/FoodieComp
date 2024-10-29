package com.example.foodiecomp.src.views.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.foodiecomp.src.components.AutoScrollingCarousel
import com.example.foodiecomp.src.components.CustomTextField


@SuppressLint("RememberReturnType")
@Composable
fun HomeScreen(navHostController: NavHostController) {
    val search = remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.size(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            AsyncImage(
                model = "file:///android_asset/menu.png",
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Row {
                AsyncImage(
                    model = "file:///android_asset/location.png",
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text(text = "Agrabad 435, Chittagong")
            }
            AsyncImage(
                model = "file:///android_asset/user.png",
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
        }

        Spacer(modifier = Modifier.size(16.dp*2))
        CustomTextField(

            value = search.value,
            onValueChange = {
                search.value = it

        }, label = "Search for restaurants",

            )
        Spacer(modifier = Modifier.size(16.dp*2))
        AutoScrollingCarousel(
            listOf("file:///android_asset/banner.webp","file:///android_asset/banner2.jpg","file:///android_asset/banner3.png")
        )

    }
}