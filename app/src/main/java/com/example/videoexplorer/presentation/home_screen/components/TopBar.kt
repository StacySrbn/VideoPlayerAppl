package com.example.videoexplorer.presentation.home_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.videoexplorer.R
import com.example.videoexplorer.presentation.Dimens.MediumSpace
import com.example.videoexplorer.presentation.Dimens.StandardSp

@Composable
fun TopBar(){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = StandardSp)
            .height(100.dp)
            .statusBarsPadding(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(id = R.drawable.video_ic_1),
                contentDescription = null,
                modifier = Modifier.size(StandardSp),
                colorFilter = ColorFilter.tint(colorResource(id = R.color.gold))
            )
            Spacer(modifier = Modifier.width(18.dp))
            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.gold)
            )
        }
        Image(
            modifier = Modifier.size(30.dp),
            imageVector = Icons.Rounded.Search,
            contentDescription = null,
            colorFilter = ColorFilter.tint(colorResource(id = R.color.gold))
        )

    }
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar()
}