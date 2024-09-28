package com.example.videoexplorer.presentation.details_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.videoexplorer.R
import com.example.videoexplorer.domain.model.MyVideo
import com.example.videoexplorer.presentation.Dimens
import com.example.videoexplorer.presentation.Dimens.MediumSpace
import com.example.videoexplorer.presentation.Dimens.StandardSp
import com.example.videoexplorer.presentation.details_screen.DetailsViewModel

@Composable
fun DetailsBody(
    video: MyVideo,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    canGoPrevious: Boolean,
    canGoNext: Boolean
){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = MediumSpace)
    ) {
        Text(
            text = video.title,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = colorResource(id = R.color.gold)
        )
        Spacer(modifier = Modifier.height(MediumSpace))
        Text(
            text = "Duration: ${video.duration} sec",
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            color = colorResource(id = R.color.light_yellow)
        )

        Spacer(modifier = Modifier.height(100.dp))

        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Button(
                modifier = Modifier
                    .wrapContentSize()
                    .clip(RoundedCornerShape(10.dp)),
                onClick = onPreviousClick,
                enabled = canGoPrevious,
                colors = buttonColors(
                    containerColor = colorResource(id = R.color.enabled_button),
                    disabledContainerColor = colorResource(id = R.color.disabled_button)
                )
            ) {
                Text(
                    text = stringResource(id = R.string.previous_video),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(id = R.color.gold)
                )

            }
            Button(
                modifier = Modifier
                    .wrapContentSize()
                    .clip(RoundedCornerShape(10.dp)),
                onClick = onNextClick,
                enabled = canGoNext,
                colors = buttonColors(
                    containerColor = colorResource(id = R.color.enabled_button),
                    disabledContainerColor = colorResource(id = R.color.disabled_button)
                )
            ) {
                Text(
                    text = stringResource(id = R.string.next_video),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(id = R.color.gold)
                )
            }

        }

    }
}
