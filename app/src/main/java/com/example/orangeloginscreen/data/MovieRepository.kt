package com.example.orangeloginscreen.data

import com.example.orangeloginscreen.data.source.MovieLocalDataSourceImpl
import com.example.orangeloginscreen.domain.model.Movie

class MovieRepository(
    private val dao: MovieLocalDataSourceImpl
) {

    suspend fun generateListOfMovies(){
        val movies = mutableListOf<Movie>()
        for(i in 1..3) {
            movies.add(
                Movie(
                    urlBanner = "https://i.imgur.com/RoF2unX.jpeg",
                    urlImage = "https://occ-0-1723-1722.1.nflxso.net/dnm/api/v6/E8vDc_W8CLv7-yMQu8KMEC7Rrr8/AAAABXbxYu1KviGXvvSCuJBzbntwYXzwdT0fHOyAaMZcFAv8qbBXQdt3crW8WLELiMQ_m3g8VPlwBZRndSVplED4MJMEzgr4.jpg?r=7f3",
                    name = "Blade Runner",
                    rate = "8.1",
                    synopsis = "No século 21, uma corporação desenvolve clones humanos para serem usados como escravos em colônias fora da Terra," +
                            " identificados como replicantes. Em 2019, um ex-policial é acionado para caçar um grupo fugitivo vivendo disfarçado em Los Angeles."
                )
            )

            movies.add(
                Movie(
                    urlBanner = "https://i.imgur.com/L4lSiKU.jpeg",
                    urlImage = "https://i.imgur.com/L4lSiKU.jpeg",
                    name = "Distrito 9",
                    rate = "7.9",
                    synopsis = "Trinta anos atrás, alienígenas chegam à Terra em busca de refúgio, pois seu planeta estava morrendo." +
                            " Eles passam a viver separados dos humanos em uma área chamada Distrito 9, na África do Sul, e comandados" +
                            " pela multinacional United, que não se importa com o bem-estar dos alienígenas, mas que está disposta a tudo" +
                            " para dominar a tecnologia que eles possuem. Quando um agente em campo contrai um vírus que altera o seu DNA," +
                            " o Distrito 9 passa a ser o único lugar em que ele pode se esconder."
                )
            )

            movies.add(
                Movie(
                    urlBanner = "https://i.imgur.com/4Rssqo8.jpeg",
                    urlImage = "https://i.imgur.com/4Rssqo8.jpeg",
                    name = "Star Wars - Nova Esperança",
                    rate = "8.6",
                    synopsis = "Luke Skywalker une forças com um Cavaleiro Jedi, um piloto arrogante, um Wookiee e dois dróides para salvar a" +
                            " galáxia da estação de batalha destruidora do mundo do Império, enquanto também tenta resgatar a Princesa Leia do misterioso Darth Vader."
                )
            )
        }
        dao.add(movies)
    }

    fun findById(id: String): Movie? {
        return dao.find(id)
    }

    suspend fun listAllMovies(): List<Movie>{
        return dao.listAll()
    }

    fun clearMovieList(){
        dao.clear()
    }
}