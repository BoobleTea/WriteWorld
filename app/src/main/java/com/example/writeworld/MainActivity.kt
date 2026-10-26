
package com.example.writeworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.clickable

private val BackgroundColor = Color(0xFF081317)
private val CardColor = Color(0xFF122126)
private val AccentColor = Color(0xFF25A6B6)
private val AccentLightColor = Color(0xFF59C7D3)
private val TextColor = Color(0xFFF2FBFC)
private val SecondaryTextColor = Color(0xFF9CB8BC)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme(
                colorScheme = darkColorScheme(
                    primary = AccentColor,
                    background = BackgroundColor,
                    surface = CardColor,
                    onBackground = TextColor,
                    onSurface = TextColor
                )
            ) {
                WriteWorldScreen()
            }
        }
    }
}

@Composable
fun HomeScreen() {
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "WriteWorld",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = TextColor
            )

            Text(
                text = "W",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = AccentColor,
                modifier = Modifier
                    .background(
                        Color(0xFF10252A),
                        RoundedCornerShape(50)
                    )
                    .padding(horizontal = 15.dp, vertical = 10.dp)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Добро пожаловать!",
            fontSize = 23.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextColor
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Открой для себя новые истории",
            fontSize = 15.sp,
            color = SecondaryTextColor
        )

        Spacer(modifier = Modifier.height(24.dp))

        BasicTextField(
            value = searchText,
            onValueChange = { searchText = it },
            singleLine = true,
            textStyle = androidx.compose.ui.text.TextStyle(
                color = TextColor,
                fontSize = 16.sp
            ),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            CardColor,
                            RoundedCornerShape(14.dp)
                        )
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "⌕",
                        fontSize = 24.sp,
                        color = AccentColor
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Box(modifier = Modifier.weight(1f)) {
                        if (searchText.isEmpty()) {
                            Text(
                                text = "Название, автор или жанр...",
                                color = SecondaryTextColor,
                                fontSize = 15.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        innerTextField()
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Продолжить чтение",
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            color = TextColor
        )

        Spacer(modifier = Modifier.height(16.dp))

        ContinueReadingCard()
        Spacer(modifier = Modifier.height(30.dp))

        PopularBooksSection()
    }
}

@Composable
fun ContinueReadingCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                CardColor,
                RoundedCornerShape(18.dp)
            )
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(90.dp)
                    .height(125.dp)
                    .background(
                        Color(0xFF214148),
                        RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "📖",
                    fontSize = 36.sp
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Хроники забытого мира",
                    color = TextColor,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Автор: Alex Moon",
                    color = SecondaryTextColor,
                    fontSize = 13.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Фэнтези · Глава 7",
                    color = AccentColor,
                    fontSize = 13.sp
                )

                Spacer(modifier = Modifier.height(12.dp))


                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(7.dp)
                        .background(
                            Color(0xFF31484B),
                            RoundedCornerShape(50)
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.32f)
                            .fillMaxHeight()
                            .background(
                                AccentColor,
                                RoundedCornerShape(50)
                            )
                    )
                }


                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "32% прочитано",
                    color = SecondaryTextColor,
                    fontSize = 12.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = AccentColor,
                contentColor = TextColor
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Продолжить чтение",
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun BookCard(
    title: String,
    genre: String,
    emoji: String,
    coverColor: Color
) {
    Column(
        modifier = Modifier.width(135.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(175.dp)
                .background(
                    coverColor,
                    RoundedCornerShape(14.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = emoji,
                fontSize = 48.sp
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = title,
            color = TextColor,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = genre,
            color = SecondaryTextColor,
            fontSize = 12.sp
        )
    }
}

@Composable
fun PopularBooksSection() {
    Column {
        Text(
            text = "Популярные произведения",
            color = TextColor,
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            BookCard(
                title = "Тёмный лес",
                genre = "Фэнтези",
                emoji = "🌲",
                coverColor = Color(0xFF1E4A52)
            )

            BookCard(
                title = "За гранью",
                genre = "Фантастика",
                emoji = "🌌",
                coverColor = Color(0xFF255B66)
            )

            BookCard(
                title = "После нас",
                genre = "Романтика",
                emoji = "🌸",
                coverColor = Color(0xFF2D6B73)
            )

            BookCard(
                title = "Последняя глава",
                genre = "Детектив",
                emoji = "🔍",
                coverColor = Color(0xFF183B42)
            )
        }
    }
}

@Composable
fun WriteWorldScreen() {
    var selectedTab by remember {
        mutableIntStateOf(0)
    }

    val pages = listOf(
        "Главная",
        "Библиотека",
        "Создать",
        "Лента",
        "Поиск",
        "Профиль"
    )

    Scaffold(
        containerColor = BackgroundColor,

        bottomBar = {
            WriteWorldBottomBar(
                selectedTab = selectedTab,
                onTabSelected = { index ->
                    selectedTab = index
                }
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (selectedTab) {
                0 -> HomeScreen()

                2 -> CreateStoryScreen()

                else -> PlaceholderScreen(
                    title = pages[selectedTab]
                )
            }

        }
    }
}

@Composable
fun PlaceholderScreen(title: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = TextColor
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Раздел находится в разработке",
            fontSize = 15.sp,
            color = SecondaryTextColor
        )
    }
}

@Composable
fun WriteWorldBottomBar(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    val items = listOf(
        "⌂" to "Главная",
        "▤" to "Библиотека",
        "+" to "Создать",
        "☷" to "Лента",
        "⌕" to "Поиск",
        "◉" to "Профиль"
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(CardColor)
            .navigationBarsPadding()
            .padding(top = 8.dp, bottom = 6.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEachIndexed { index, item ->

            val isSelected = selectedTab == index
            val isCreate = index == 2

            val iconColor = when {
                isCreate -> BackgroundColor
                isSelected -> AccentColor
                else -> SecondaryTextColor
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onTabSelected(index)
                    }
                    .padding(vertical = 5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(
                            color = when {
                                isCreate -> AccentColor
                                isSelected -> AccentColor.copy(
                                    alpha = 0.15f
                                )
                                else -> Color.Transparent
                            },
                            shape = RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = item.first,
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold,
                        color = iconColor
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = item.second,
                    fontSize = 9.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = if (isSelected) {
                        AccentColor
                    } else {
                        SecondaryTextColor
                    }
                )
            }
        }
    }
}

