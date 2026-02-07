package com.example.starwarspersonaje.ui.base.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.constraintlayout.compose.ConstraintLayout

import androidx.constraintlayout.compose.Dimension
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.starwarspersonaje.R

@Composable
fun NoDataScreen(modifier: Modifier = Modifier) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animacion_espera))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        // Guías
        val startGuide = createGuidelineFromStart(0.1f)
        val endGuide = createGuidelineFromEnd(0.1f)
        val verticalCenterGuide = createGuidelineFromTop(0.5f)

        // Referencias
        val (animation, text) = createRefs()

        // Animación Lottie
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier.constrainAs(animation) {
                bottom.linkTo(verticalCenterGuide)
                start.linkTo(startGuide)
                end.linkTo(endGuide)
               width = Dimension.fillToConstraints
            height = Dimension.ratio("1:1")
            }
        )


        Text(
            text = stringResource(R.string.no_data), //estaba account create
            textAlign = TextAlign.Center,
            modifier = Modifier.constrainAs(text) {
                top.linkTo(verticalCenterGuide, margin = 12.dp)
                start.linkTo(startGuide)
               end.linkTo(endGuide)
                width = Dimension.fillToConstraints
            }
        )

    }
}

@Preview(showBackground = true)
@Composable
fun NoDataScreenPreview() {
    NoDataScreen()
}







