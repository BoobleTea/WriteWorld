
package com.example.writeworld

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class CreateStoryUiState(
    val title: String = "",
    val description: String = "",
    val selectedType: String = "Книга",
    val selectedGenre: String = "Не выбран",
    val resultMessage: String = "",
    val savedDraftCount: Int = 0
)

data class StoryDraft(
    val title: String,
    val description: String,
    val type: String,
    val genre: String
)

class CreateStoryViewModel : ViewModel() {

    var uiState by mutableStateOf(CreateStoryUiState())
        private set

    private val drafts = mutableListOf<StoryDraft>()

    fun updateTitle(newTitle: String) {
        uiState = uiState.copy(
            title = newTitle,
            resultMessage = ""
        )
    }

    fun updateDescription(newDescription: String) {
        uiState = uiState.copy(
            description = newDescription,
            resultMessage = ""
        )
    }

    fun selectType(type: String) {
        uiState = uiState.copy(
            selectedType = type,
            resultMessage = ""
        )
    }

    fun selectGenre(genre: String) {
        uiState = uiState.copy(
            selectedGenre = genre,
            resultMessage = ""
        )
    }

    fun validateForm(): String? {
        return when {
            uiState.title.isBlank() ->
                "Введите название произведения"

            uiState.selectedGenre == "Не выбран" ->
                "Выберите жанр"

            uiState.description.isBlank() ->
                "Добавьте описание"

            else -> null
        }
    }

    fun saveDraft() {
        val error = validateForm()

        if (error != null) {
            uiState = uiState.copy(
                resultMessage = error
            )
            return
        }

        val draft = StoryDraft(
            title = uiState.title.trim(),
            description = uiState.description.trim(),
            type = uiState.selectedType,
            genre = uiState.selectedGenre
        )

        drafts.add(draft)

        uiState = uiState.copy(
            resultMessage = "Черновик сохранён в памяти приложения!",
            savedDraftCount = drafts.size
        )
    }
}
