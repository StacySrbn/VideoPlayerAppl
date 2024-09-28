package com.example.videoexplorer.presentation.details_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.videoexplorer.R
import com.example.videoexplorer.presentation.Dimens.MediumSpace
import com.example.videoexplorer.presentation.Dimens.SmallSpace
import com.example.videoexplorer.presentation.Dimens.StandardSp
import com.example.videoexplorer.util.navgraph.Route

@Composable
fun TopBarDetail(
    navController: NavController
){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = StandardSp)
            .height(100.dp)
            .statusBarsPadding(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Row(modifier = Modifier
            .clickable { navController.navigate(Route.HomeScreen.route) }
        ) {
            Image(
                modifier = Modifier.size(30.dp),
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = null,
                colorFilter = ColorFilter.tint(colorResource(id = R.color.gold))
            )
            Spacer(modifier = Modifier.width(SmallSpace))
            Text(
                text = stringResource(id = R.string.back_home),
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                color = colorResource(id = R.color.gold)
            )
        }



        Image(
            painter = painterResource(id = R.drawable.video_ic_1),
            contentDescription = null,
            modifier = Modifier.size(StandardSp),
            colorFilter = ColorFilter.tint(colorResource(id = R.color.gold))
        )

    }
}