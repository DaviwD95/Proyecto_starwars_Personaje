package com.example.nagivigationviewmodel.data.network


/**
 *
 *
 * sealed class BaseResult{
 *
 *     data class Succes<T>(var data : T) : BaseResult()//la maestra no tiene <T>
 *     data class Error (var exception: Exception) : BaseResult()
 *
 * }
 * Este formato exige el siguiente formato en el view model ç
 *
 * when(result){
 *                 is BaseResult.Error ->
 *                     {
 *                        state = state.copy(emailError = result.exception.message?:"error desconocido")
 *                     }
 *                 is BaseResult.Succes<*> -> {
 *                     state = state.copy(account = result.data as Account)
 *                 }
 *             }
 *
 */

sealed class BaseResult<out T>{

    data class Succes<T>(var data : T) : BaseResult<T>()
    data class Error (var exception: Exception) : BaseResult<Nothing>()

}