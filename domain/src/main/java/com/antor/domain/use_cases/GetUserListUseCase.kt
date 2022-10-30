package com.antor.domain.use_cases

import com.antor.data.dto.user.toUserModel
import com.antor.domain.common.Resource
import com.antor.domain.model.UserModel
import com.antor.domain.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(
    private val repository: ApiRepository
) {
    operator fun invoke(): Flow<Resource<List<UserModel>>> = flow {

        try {
            emit(Resource.Loading())

            val response = repository.getUserList()
            val list = if (response.isEmpty()) emptyList() else response.map {
                it.toUserModel()
            }
            emit(Resource.Success(data = list))

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown Error"))

        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Check Internet Connection!!!"))
        }

    }
}