package com.antor.domain.use_cases

import com.antor.domain.common.Resource
import com.antor.domain.model.UserModel
import com.antor.data.repository.ApiRepository
import com.antor.domain.model.TodoModel
import com.antor.domain.model.toTodoModel
import com.antor.domain.model.toUserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTodoListUseCase @Inject constructor(
    private val repository: ApiRepository
) {
    operator fun invoke(): Flow<Resource<List<TodoModel>>> = flow {

        try {
            emit(Resource.Loading())

            val response = repository.getTodoList()
            val list = if (response.isEmpty()) emptyList() else response.map {
                it.toTodoModel()
            }
            emit(Resource.Success(data = list))

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown Error"))

        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Check Internet Connection!!!"))
        }

    }
}