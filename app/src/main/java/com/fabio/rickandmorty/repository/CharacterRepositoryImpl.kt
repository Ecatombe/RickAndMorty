package com.fabio.rickandmorty.repository

import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.fabio.rickandmorty.CharacterQuery
import com.fabio.rickandmorty.CharactersListQuery
import com.fabio.rickandmorty.networking.RickAndMortyApi
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val webService: RickAndMortyApi
) : CharacterRepository {

    override suspend fun queryCharactersList(): Response<CharactersListQuery.Data> {
        return webService.getApolloClient().query(CharactersListQuery()).await()
    }

    override suspend fun queryCharacter(id: String): Response<CharacterQuery.Data> {
        return webService.getApolloClient().query(CharacterQuery(id)).await()
    }
}