package com.example.myapplication.apipackage

import com.google.gson.annotations.SerializedName
data class Item(val id: Int, val name: String, val cost: Float, val descrip: String)
data class Customer(val id: Int, val phone: String, val name: String, val password: String, val birthday: String, val visited: Int, val credits: Int)
data class Employee(var id: Int, var password: String, var name: String, var wage: Int, var role: String, var hours: Int, var tips: Double, var compmeals: Int)
data class Order(val id: Int, val tableNum: Int, val entree: String, val side: String, val drink: String, val orderTotal: Double)
data class Ingredient(var id: Int, var food: String, var foodnum: String)
data class Table(var number: Int, val tableStatus: String, val needHelp: Boolean, val needRefill: Boolean)
data class ResponseBase(@SerializedName("error") val error: Boolean, val message: String)
data class ResponseItem(@SerializedName("error") val error: Boolean, val message: String, val item: Item)
data class ResponseItems(@SerializedName("error") val error: Boolean, val message: String, val items: List<Item>)
data class ResponseCustomer(@SerializedName("error") val error: Boolean, val message: String, val customer: Customer)
data class ResponseCustomers(@SerializedName("error") val error: Boolean, val message: String, val customers: List<Customer>)
data class ResponseEmployee(val error: Boolean, val message: String, val employee: Employee)
data class ResponseEmployees(@SerializedName("error") val error: Boolean, val message: String, val employees: List<Employee>)
data class ResponseOrder(@SerializedName("error") val error: Boolean, val message: String, val order: Order)
data class ResponseOrders(@SerializedName("error") val error: Boolean, val message: String, val orders: List<Order>)
data class ResponseIngredients(val error: Boolean, val ingts: List<Ingredient>)
data class ResponseIngredient( val error: Boolean, val message: String, val ing: Ingredient)
data class ResponseTables(@SerializedName("error") val error: Boolean, val message: String, val ingredients: List<Table>)
data class ResponseTable(@SerializedName("error") val error: Boolean, val message: String, val Ingredient: Table)

