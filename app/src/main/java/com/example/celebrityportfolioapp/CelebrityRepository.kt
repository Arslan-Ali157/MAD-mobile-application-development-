package com.example.celebrityportfolioapp

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

/**
 * Repository class that abstracts access to the data source.
 */
class CelebrityRepository(private val celebrityDao: CelebrityDao) {

    // Flow of all celebrities from the database
    val allCelebrities: Flow<List<Celebrity>> = celebrityDao.getAllCelebrities()

    suspend fun updateLikes(celebrityId: Int) {
        celebrityDao.updateLikes(celebrityId)
    }

    suspend fun clearAndPopulate() {
        celebrityDao.deleteAll()
        val initialCelebrities = listOf(
            Celebrity(
                name = "Cristiano Ronaldo",
                image = "https://media.gettyimages.com/id/2172436174/photo/al-ettifaq-v-al-nassr-saudi-pro-league.jpg?s=612x612&w=gi&k=20&c=EAz2Lx0gJTBzVbLnU7t5SwsVY9f79aG96_TGp5WLQyA=",
                description = "Cristiano Ronaldo is a Portuguese professional footballer who plays as a forward for Saudi Pro League club Al Nassr and the Portugal national team. He is widely regarded as one of the greatest goal-scorers in football history.",
                likes = 9800
            ),
            Celebrity(
                name = "Lionel Messi",
                image = "https://cdn.britannica.com/35/238335-050-2CB2EB8A/Lionel-Messi-Argentina-Netherlands-World-Cup-Qatar-2022.jpg",
                description = "Lionel Messi is an Argentine professional footballer who captains both Major League Soccer club Inter Miami and the Argentina national team. He led Argentina to victory in the 2022 FIFA World Cup.",
                likes = 8900
            ),
            Celebrity(
                name = "Elon Musk",
                image = "https://www.shutterstock.com/image-photo/los-angeles-apr-13-elon-600w-2449367659.jpg",
                description = "Elon Musk is a business magnate and investor. He is the founder, CEO, and CTO of SpaceX and the CEO of Tesla, Inc. He is known for his vision of multi-planetary life and sustainable energy.",
                likes = 1250
            ),
            Celebrity(
                name = "Babar Azam",
                image = "https://media.gettyimages.com/id/2200098262/photo/pakistan-portraits-icc-champions-trophy-2025.jpg?s=1024x1024&w=gi&k=20&c=gJSoJVBeR4O8xLAzrtoSvGMhFwzXhwYLij56DF-dhQ4=",
                description = "Babar Azam is a Pakistani cricketer and one of the world’s leading batsmen. He is widely regarded for his elegant stroke play, consistency, and leadership, making him a key figure in modern international cricket",
                likes = 5400
            ),
            Celebrity(
                name = "Shah Rukh Khan",
                image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQyRH-Pt-eXF0OJe5wWdK7tft1sg5LCZijUVBfAKZnspA&s",
                description = "Shah Rukh Khan, also known as SRK, is an Indian actor and film producer. Referred to as the 'Baadshah of Bollywood' and 'King Khan', he is a global icon of Indian cinema with a career spanning over three decades.",
                likes = 7500
            ),
            Celebrity(
                name = "Virat Kohli",
                image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRRmbmiWi_W1PrjYzIBsC5Y2ejQuUTW4vyF198o6twxVvXq9HHYrodEWCw&s",
                description = "Virat Kohli is an Indian international cricketer and former captain of the Indian national cricket team. He is widely regarded as one of the greatest batsmen in history, known for his intensity and chasing skills.",
                likes = 6200
            )
        )
        celebrityDao.insertCelebrities(initialCelebrities)
    }

    /**
     * Populates the database with initial celebrity data if it's empty.
     */
    suspend fun populateData() {
        val existing = celebrityDao.getAllCelebrities().first()
        if (existing.isEmpty()) {
            clearAndPopulate()
        }
    }
}
