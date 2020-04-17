package com.example.myapplication.apipackage

import retrofit2.Call
import retrofit2.http.*

interface Api {
    @FormUrlEncoded
    @POST("createMenuItem")
    fun createMenuItem(
        @Field("id") id:Int,
        @Field("name") name:String,
        @Field("cost") cost:Float,
        @Field("descrip") descrip:String
    ):Call<ResponseBase>

    @FormUrlEncoded
    @POST("createOrder")
    fun createOrder(
        @Field("tableNum") email:String,
        @Field("entree") entree: String,
        @Field("side") side: String,
        @Field("drink") drink: String,
        @Field("orderTotal") orderTotal: Float
    ):Call<ResponseBase>

    @FormUrlEncoded
    @POST("createEmp")
    fun createEmp(
        @Field("id") id:Int,
        @Field("password") password:String,
        @Field("name") name: String,
        @Field("wage") wage: Int,
        @Field("role") role: String,
        @Field("hours") hours: Int,
        @Field("tips")tips:Double,
        @Field("compmeals")compmeals:Int
    ):Call<ResponseEmployee>

    @FormUrlEncoded
    @POST("empLogin")
    fun userLogin(
        @Field("id") id:Int,
        @Field("password") password: String
    ):Call<ResponseBase>

    @FormUrlEncoded
    @GET("getItem/{id}")
    fun getItem(
        @Path("id") id:Int
    ):Call<ResponseBase>

    @FormUrlEncoded
    @GET("createOrder/{id}")
    fun getOrder(
        @Path("id") id:Int
    ):Call<ResponseBase>

    @GET("getEmp/{id}")
    fun getEmp(
        @Path("id") id:Int
    ):Call<ResponseEmployee>

    @FormUrlEncoded
    @GET("getItems")
    fun getItems(
    ):Call<ResponseItems>

    @FormUrlEncoded
    @GET("getOrders")
    fun getOrders(
    ):Call<ResponseOrders>


    @GET("getAllEmp")
    fun getAllEmp(
    ):Call<ResponseEmployees>

    @FormUrlEncoded
    @PUT("updateItem/{id}")
    fun updateItem(
        @Path("id") id:Int,
        @Field("email") name:String,
        @Field("cost") cost: Float,
        @Field("descrip") descrip: String
    ):Call<ResponseItem>

    @FormUrlEncoded
    @PUT("changeOrder/{id}")
    fun changeOrder(
        @Path("id") id:Int,
        @Field("tableNum") email:Int,
        @Field("entree") entree: String,
        @Field("side") side: String,
        @Field("drink") drink: String,
        @Field("orderTotal") orderTotal: Float
    ):Call<ResponseOrder>

    @FormUrlEncoded
    @PUT("updateEmp/{id}")
    fun updateEmp(
        @Path("id") id:Int,
        @Field("name") name: String,
        @Field("wage") wage: Int,
        @Field("role") role: String,
        @Field("hours") hours: Int,
        @Field("tips")tips:Double,
        @Field("compmeals")compmeals:Int
    ):Call<ResponseEmployee>

    @FormUrlEncoded
    @DELETE("deleteItem/{id}")
    fun deleteItem(
        @Path("id") id:Int
    ):Call<ResponseBase>

    @DELETE("deleteOrder/{id}")
    fun deleteOrder(
        @Path("id") id:Int
    ):Call<ResponseBase>

    @FormUrlEncoded
    @DELETE("clearOrderQueue")
    fun clearOrderQueue(
    ):Call<ResponseBase>


    @DELETE("deleteEmployee/{id}")
    fun deleteEmployee(
        @Path("id") id:Int
    ):Call<ResponseEmployee>

    @FormUrlEncoded
    @POST("createcustomer")
    fun createcustomer(
        @Field("phone") phone:String,
        @Field("name") name:String,
        @Field("password") password:String,
        @Field("birthday") birthday:String,
        @Field("visited") visited:Int,
        @Field("credits")credits:Int
    ): Call<DefaultResponse>

    @FormUrlEncoded
    @POST("customerlogin")
    fun customerlogin(
        @Field("phone") phone:String,
        @Field("password") password: String
    ):Call<ResponseCustomer>


    @GET("allcustomers")
    fun allcustomers(
    ):Call<ResponseCustomers>


    @FormUrlEncoded
    @PUT("updatecustomer/{id}")
    fun updatecustomer(
        @Path("id") id:Int,
        @Field("phone") phone: String,
        @Field("name") name: String,
        @Field("password") password: String,
        @Field("birthday") birthday: String,
        @Field("visited") visited: Int,
        @Field("credits") credits: Int
    ):Call<ResponseCustomer>

    @FormUrlEncoded
    @PUT("updateCustomerPassword")
    fun updateCustomerPassword(
        @Field("currentpassword") currentpassword:String,
        @Field("newpassword") newpassword: String,
        @Field("phone") phone: String
    ):Call<ResponseBase>

    @FormUrlEncoded
    @DELETE("deleteuser/{id}")
    fun deleteuser(
        @Path("id") id:Int
    ):Call<ResponseBase>

    @FormUrlEncoded
    @POST("createingredient")
    fun createingredient(
        @Field("food") food:String,
        @Field("amount") amount:Int
    ):Call<ResponseBase>


    @GET("allingredients")
    fun getAllIngredients(
    ):Call<ResponseIngredients>

    @FormUrlEncoded
    @PUT("updateIngredient/{id}")
    fun updateIngredient(
        @Path("id") id:Int,
        @Field("name") name:String,
        @Field("foodnum") foodnum:String
    ):Call<ResponseIngredient>

    @FormUrlEncoded
    @DELETE("trashfood/{id}")
    fun trashfood(
        @Path("id") id:Int
    )

    @FormUrlEncoded
    @POST("createTable")
    fun createTable(
        @Field("number") number:Int,
        @Field("tableStatus") tableStatus:String,
        @Field("needHelp") needHelp:Boolean,
        @Field("needRefill") needRefill:Boolean
    ):Call<ResponseBase>

    @FormUrlEncoded
    @GET("allTables")
    fun allTables(
    ):Call<ResponseTables>

    @FormUrlEncoded
    @GET("getTable/{number}")
    fun getTable(
        @Path("number") number:Int
    ):Call<ResponseTable>

    @FormUrlEncoded
    @PUT("updateTable/{number}")
    fun updateTable(
        @Path("number") number:Int,
        @Field("tableStatus") tableStatus:String,
        @Field("needHelp") needHelp:Boolean,
        @Field("needRefill") needRefill:Boolean
    ):Call<ResponseTable>

    @FormUrlEncoded
    @DELETE("deleteTable/{number}")
    fun deleteTable(
        @Path("id") id:Int
    ):Call<ResponseBase>
}
