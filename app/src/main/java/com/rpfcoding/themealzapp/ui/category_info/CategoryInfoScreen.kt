package com.rpfcoding.themealzapp.ui.category_info

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.ramcosta.composedestinations.annotation.Destination
import com.rpfcoding.themealzapp.domain.model.Category
import kotlin.math.min

@Destination
@Composable
fun CategoryInfoScreen(
    category: Category,
) {
//    var profilePictureState by remember {
//        mutableStateOf(CategoryProfilePictureState.Normal)
//    }
//
//    val transition = updateTransition(targetState = profilePictureState, label = "")
//
//    val imageSizeDp by transition.animateDp(
//        targetValueByState = {
//            it.size
//        }, label = ""
//    )
//
//    val borderWidthDp by transition.animateDp(
//        targetValueByState = {
//            it.borderWidth
//        }, label = ""
//    )
//
//    val color by transition.animateColor(
//        targetValueByState = {
//            it.color
//        }, label = ""
//    )

    val scrollState = rememberLazyListState()
    val offset = min(
        1f, 1 - (
                scrollState.firstVisibleItemScrollOffset / 600f +
                        scrollState.firstVisibleItemIndex)
    )

    val imageSize by animateDpAsState(targetValue = max(100.dp, 150.dp * offset))

    Surface(
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Surface(
                elevation = 8.dp,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row {
                    Card(
                        modifier = Modifier
                            .padding(16.dp),
                        shape = CircleShape,
                        border = BorderStroke(width = 2.dp, color = Color.Red)
                    ) {
                        Image(
                            painter = rememberImagePainter(category.thumbUrl, builder = {
                                transformations(CircleCropTransformation())
                            }),
                            contentDescription = null,
                            modifier = Modifier
                                .size(imageSize)
                        )
                    }
                    Text(
                        text = category.name,
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            LazyColumn(
                state = scrollState,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                item {
                    Text(
                        text = category.description,
                        modifier = Modifier.padding(8.dp),
                        style = MaterialTheme.typography.h6,
                        fontStyle = FontStyle.Italic
                    )
                }
            }
        }
    }
}