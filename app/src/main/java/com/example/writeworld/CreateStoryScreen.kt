
package com.example.writeworld

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel


private val CreateBackground = Color(0xFF081317)
private val CreateAccent = Color(0xFF25A6B6)
private val CreateText = Color(0xFFF2FBFC)
private val CreateSecondary = Color(0xFF9CB8BC)


@Composable
fun CreateStoryScreen(
    storyViewModel: CreateStoryViewModel = viewModel()
) {
    val state = storyViewModel.uiState


    var genreExpanded by remember {
        mutableStateOf(false)
    }

    val storyTypes = listOf(
        "Книга", "Рассказ", "Фанфик", "Статья"
    )

    val genres = listOf(
        "Фэнтези",
        "Фантастика",
        "Романтика",
        "Детектив",
        "Приключения",
        "Драма",
        "Ужасы",
        "Другое"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CreateBackground)
            .verticalScroll(rememberScrollState())
            .imePadding()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {

        Text(
            text = "Новое произведение",
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = CreateText
        )

        Text(
            text = "Дайте своей истории начало",
            fontSize = 15.sp,
            color = CreateSecondary
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Название",
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold,
            color = CreateText
        )

        OutlinedTextField(
            value = state.title,
            onValueChange = {
                storyViewModel.updateTitle(it)
            },
            placeholder = {
                Text("Введите название произведения")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(14.dp)
        )

        Text(
            text = "Тип произведения",
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold,
            color = CreateText
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            storyTypes.chunked(2).forEach { types ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    types.forEach { type ->
                        FilterChip(
                            selected = state.selectedType == type,
                            onClick = {
                                storyViewModel.selectType(type)
                            },
                            label = {
                                Text(type)
                            },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp)
                        )
                    }
                }
            }
        }

        Text(
            text = "Жанр",
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold,
            color = CreateText
        )

        Box {
            OutlinedButton(
                onClick = {
                    genreExpanded = true
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp)
            ) {
                Text(
                    text = "${state.selectedGenre}  ▾",
                    modifier = Modifier.padding(6.dp)
                )
            }

            DropdownMenu(
                expanded = genreExpanded,
                onDismissRequest = {
                    genreExpanded = false
                }
            ) {
                genres.forEach { genre ->
                    DropdownMenuItem(
                        text = { Text(genre) },
                        onClick = {
                            storyViewModel.selectGenre(genre)
                            genreExpanded = false
                        }
                    )
                }
            }
        }

        Text(
            text = "Описание",
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold,
            color = CreateText
        )

        OutlinedTextField(
            value = state.description,
            onValueChange = {
                storyViewModel.updateDescription(it)
            },
            placeholder = {
                Text("О чём будет ваше произведение?")
            },
            modifier = Modifier.fillMaxWidth(),
            minLines = 4,
            maxLines = 6,
            shape = RoundedCornerShape(14.dp)
        )

        Button(
            onClick = {
                storyViewModel.saveDraft()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = CreateAccent,
                contentColor = CreateBackground
            )
        ) {
            Text(
                text = "Сохранить черновик",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

        if (state.resultMessage.isNotEmpty()) {
            Text(
                text = state.resultMessage,
                color = CreateAccent,
                fontSize = 14.sp
            )
        }

        if (state.savedDraftCount > 0) {
            Text(
                text = "Черновиков в памяти: ${state.savedDraftCount}",
                color = CreateSecondary,
                fontSize = 13.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
    }
}
