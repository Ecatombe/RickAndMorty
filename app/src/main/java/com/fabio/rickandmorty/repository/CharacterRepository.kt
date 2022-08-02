package com.fabio.rickandmorty.repository

import com.apollographql.apollo.api.Response
import com.fabio.rickandmorty.CharacterQuery
import com.fabio.rickandmorty.CharactersListQuery


interface CharacterRepository {

    suspend fun queryCharactersList(): Response<CharactersListQuery.Data>

    suspend fun queryCharacter(id: String): Response<CharacterQuery.Data>
}