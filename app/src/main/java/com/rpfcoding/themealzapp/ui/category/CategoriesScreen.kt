package com.rpfcoding.themealzapp.ui.category

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.rpfcoding.themealzapp.domain.model.Category

@ExperimentalMaterialApi
@Composable
fun CategoriesScreen(
    viewModel: CategoriesViewModel = hiltViewModel()
) {

    val state = viewModel.state

    val context = LocalContext.current

    if (state.errorMsg == null) {
        LazyColumn {
            items(state.categories) { category ->
                CategoryItem(category = category) {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else if (state.errorMsg != null) {
            Text(
                text = state.errorMsg,
                color = MaterialTheme.colors.error
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun CategoryItem(
    category: Category,
    onItemClick: (name: String) -> Unit = {},
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        onClick = {
            onItemClick(category.name)
        },
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .animateContentSize()
        ) {

            Image(
                modifier = Modifier
                    .fillMaxHeight()
                    .size(90.dp)
                    .padding(8.dp)
                    .align(Alignment.CenterVertically),
                painter = rememberImagePainter(category.thumbUrl),
                contentDescription = null
            )

            Column(
                modifier = Modifier
                    .weight(0.8f)
                    .padding(8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = category.name,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h6
                )
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = category.description,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.subtitle2,
                        fontStyle = FontStyle.Italic,
                        maxLines = if (expanded) 10 else 3,
                        textAlign = TextAlign.Start
                    )
                }
            }

            Icon(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxHeight()
                    .align(
                        if(expanded) {
                            Alignment.Bottom
                        } else {
                            Alignment.CenterVertically
                        }
                    )
                    .clickable {
                        expanded = !expanded
                    },
                imageVector = if (expanded) {
                    Icons.Filled.KeyboardArrowUp
                } else Icons.Filled.KeyboardArrowDown,
                contentDescription = null,
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun previewCategoryItem() {
    CategoryItem(
        category = Category(
            id = "1",
            name = "Lorem",
            thumbUrl = "https://test.com",
            description = "Lorem Ipsum"
        )
    )
}