package com.example.task5

import android.content.Context
import java.util.*

class MadLibManager(private val context: Context) {
    private var storyTemplate: String = ""
    private val placeholders = mutableListOf<String>()
    private val answers = mutableMapOf<Int, String>()

    fun loadStory(resourceId: Int) {
        val inputStream = context.resources.openRawResource(resourceId)
        val scanner = Scanner(inputStream)
        val sb = StringBuilder()
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine()).append("\n")
        }
        storyTemplate = sb.toString()
        parsePlaceholders()
    }

    private fun parsePlaceholders() {
        placeholders.clear()
        val regex = "<([^>]+)>".toRegex()
        val matches = regex.findAll(storyTemplate)
        for (match in matches) {
            placeholders.add(match.groupValues[1])
        }
    }

    fun getPlaceholders(): List<String> = placeholders

    fun fillPlaceholder(index: Int, answer: String) {
        answers[index] = answer
    }

    fun getCompletedStory(): String {
        var completedStory = storyTemplate
        val regex = "<([^>]+)>".toRegex()
        var index = 0
        return regex.replace(storyTemplate) {
            val replacement = answers[index] ?: it.value
            index++
            replacement
        }
    }

    fun reset() {
        answers.clear()
    }
}
